from kafka import KafkaConsumer
from pymongo import MongoClient
import json

# Kafka Consumer 설정
consumer = KafkaConsumer(
    'user-actions',
    bootstrap_servers='localhost:9092',
    group_id='mongo-consumer-group',
    value_deserializer=lambda m: json.loads(m.decode('utf-8'))
)

# MongoDB 연결
client = MongoClient("mongodb://localhost:27017/")
db = client.kafka_logs
collection = db.actions  # actions 컬렉션

print("✅ MongoDB Kafka Consumer 실행 중...")

for message in consumer:
    data = message.value
    print(f"📩 수신 메시지: {data}")
    # MongoDB에 삽입
    collection.insert_one(data)