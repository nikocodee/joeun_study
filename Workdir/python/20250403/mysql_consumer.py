from kafka import KafkaConsumer
from sqlalchemy import create_engine, Table, Column, Integer, String, MetaData, insert
import json

# Kafka Consumer 설정
consumer = KafkaConsumer(
    'user-actions',
    bootstrap_servers='localhost:9092',
    group_id='mariadb-consumer-group',
    value_deserializer=lambda m: json.loads(m.decode('utf-8'))
)

# SQLAlchemy를 통한 MariaDB 연결
engine = create_engine("mysql+pymysql://root:rootpw@localhost:3306/mydb")
metadata = MetaData()

# 테이블 정의
user_actions = Table(
    'user_actions', metadata,
    Column('id', Integer, primary_key=True),
    Column('user_id', Integer),
    Column('action', String(255))
)

print("✅ MariaDB Kafka Consumer 실행 중...")

# 메시지 소비
with engine.connect() as conn:
    for message in consumer:
        data = message.value
        print(f"📩 수신 메시지: {data}")
        stmt = insert(user_actions).values(user_id=data['user_id'], action=data['action'])
        conn.execute(stmt)
        conn.commit()