from kafka import KafkaConsumer
import json

# Kafka Consumer 설정
consumer = KafkaConsumer(
    'user-actions',  # 구독할 토픽 이름
    bootstrap_servers='localhost:9092',
    auto_offset_reset='earliest',  # 처음부터 읽기
    enable_auto_commit=True,
    group_id='my-consumer-group',
    value_deserializer=lambda m: json.loads(m.decode('utf-8'))
)

print("✅ Kafka Consumer 시작됨. 메시지 수신 대기 중...")

# 무한 반복으로 Kafka 메시지 수신
for message in consumer:
    data = message.value
    print(f"📩 수신된 메시지: {data}")

