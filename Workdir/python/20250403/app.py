from fastapi import FastAPI
from pydantic import BaseModel
from kafka import KafkaProducer, KafkaConsumer
from threading import Thread
import json

# MongoDB
from pymongo import MongoClient

# MariaDB
from sqlalchemy import create_engine, Table, Column, Integer, String, MetaData, insert

# FastAPI 앱 초기화
app = FastAPI()

KAFKA_BROKER = 'localhost:9092'
TOPIC_NAME = 'user-actions'

# Kafka Producer 설정
producer = KafkaProducer(
    bootstrap_servers=KAFKA_BROKER,
    value_serializer=lambda v: json.dumps(v).encode('utf-8')
)

# 메시지 구조 정의
class Message(BaseModel):
    user_id: int
    action: str

# MongoDB 연결
mongo_client = MongoClient("mongodb://localhost:27017/")
mongo_db = mongo_client.kafka_logs
mongo_collection = mongo_db.user_actions

# MariaDB 연결 (SQLAlchemy)
engine = create_engine("mysql+pymysql://root:rootpw@localhost:3306/mydb")
metadata = MetaData()
user_actions = Table(
    'user_actions', metadata,
    Column('id', Integer, primary_key=True),
    Column('user_id', Integer),
    Column('action', String(255)),
)

@app.post("/publish/")
async def publish_message(message: Message):
    """
    Kafka 토픽에 메시지를 발행합니다.
    """
    producer.send(TOPIC_NAME, value=message.dict())
    producer.flush()
    return {"status": "Message sent", "data": message.dict()}


def consume_and_store():
    """
    Kafka 메시지를 수신하고 MongoDB와 MariaDB에 저장하는 백그라운드 처리 함수
    """
    consumer = KafkaConsumer(
        TOPIC_NAME,
        bootstrap_servers=KAFKA_BROKER,
        group_id="fastapi-consumer",
        auto_offset_reset='earliest',
        enable_auto_commit=True,
        value_deserializer=lambda m: json.loads(m.decode('utf-8'))
    )

    print("✅ Kafka Consumer 스레드 시작됨!")

    with engine.connect() as conn:
        for msg in consumer:
            data = msg.value
            print(f"📩 Kafka 수신 메시지: {data}")

            # ✅ MongoDB 저장
            mongo_collection.insert_one(data)

            # ✅ MariaDB 저장
            stmt = insert(user_actions).values(user_id=data['user_id'], action=data['action'])
            conn.execute(stmt)
            conn.commit()


@app.on_event("startup")
def startup_event():
    """
    FastAPI 시작 시 Kafka Consumer 백그라운드 스레드 실행
    """
    thread = Thread(target=consume_and_store)
    thread.daemon = True
    thread.start()