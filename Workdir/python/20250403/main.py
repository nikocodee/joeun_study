from fastapi import FastAPI, WebSocket
from kafka import KafkaConsumer
from threading import Thread
import json
import asyncio

app = FastAPI()

KAFKA_BROKER = 'localhost:9092'
TOPIC_NAME = 'user-actions'

# 모든 연결된 WebSocket 클라이언트를 저장
websocket_clients = set()

@app.websocket("/ws")
async def websocket_endpoint(websocket: WebSocket):
    """
    WebSocket 연결 처리
    """
    await websocket.accept()
    websocket_clients.add(websocket)
    print("🌐 WebSocket 클라이언트 연결됨")

    try:
        while True:
            # 클라이언트로부터의 메시지를 무시하고 유지만 함
            await websocket.receive_text()
    except Exception as e:
        print("❌ WebSocket 연결 해제:", e)
    finally:
        websocket_clients.remove(websocket)
        await websocket.close()

def kafka_consumer_loop():
    """
    Kafka Consumer 백그라운드 실행 → 메시지를 WebSocket으로 브로드캐스트
    """
    consumer = KafkaConsumer(
        TOPIC_NAME,
        bootstrap_servers=KAFKA_BROKER,
        group_id="ws-consumer-group",
        auto_offset_reset='earliest',
        enable_auto_commit=True,
        value_deserializer=lambda m: json.loads(m.decode('utf-8'))
    )

    print("✅ Kafka Consumer 실행 중...")

    for message in consumer:
        data = message.value
        print(f"📩 Kafka 메시지 수신: {data}")
        # 비동기 WebSocket 브로드캐스트 실행
        asyncio.run(send_to_websockets(data))


async def send_to_websockets(data):
    """
    모든 연결된 WebSocket 클라이언트에 메시지 전송
    """
    disconnected = set()

    for ws in websocket_clients:
        try:
            await ws.send_text(json.dumps(data))
        except Exception as e:
            print("❌ WebSocket 전송 실패:", e)
            disconnected.add(ws)

    # 연결이 끊긴 클라이언트 제거
    for ws in disconnected:
        websocket_clients.remove(ws)

@app.on_event("startup")
def start_kafka_consumer():
    """
    FastAPI 서버 시작 시 Kafka Consumer 스레드 실행
    """
    thread = Thread(target=kafka_consumer_loop)
    thread.daemon = True
    thread.start()