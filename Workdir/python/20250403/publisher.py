from fastapi import FastAPI
from pydantic import BaseModel
from kafka import KafkaProducer
import json

app = FastAPI()

# Kafka Producer 설정 (Kafka 브로커가 localhost:9092 에서 실행 중)
producer = KafkaProducer(
    bootstrap_servers='localhost:9092',
    value_serializer=lambda v: json.dumps(v).encode('utf-8')
)

# 메시지 형식을 위한 모델 정의
class Message(BaseModel):
    user_id: int
    action: str

# 메시지 발행 엔드포인트
@app.post("/publish/")
async def publish_message(message: Message):
    """
    메시지를 Kafka 토픽으로 전송합니다.
    """
    topic_name = 'user-actions'

    # Kafka로 메시지 전송
    producer.send(topic_name, value=message.dict())
    producer.flush()

    return {"status": "Message sent to Kafka", "data": message.dict()}

