from database import SessionLocal
from models import User
from hashlib import sha256

db = SessionLocal()

sample_users = [
    ("홍길동", "hong@example.com", "pw1"),
    ("김철수", "kim@example.com", "pw2"),
    ("이영희", "lee@example.com", "pw3"),
    ("박지성", "park@example.com", "pw4"),
    ("최강창", "choi@example.com", "pw5"),
    ("유재석", "yoo@example.com", "pw6"),
    ("강호동", "kang@example.com", "pw7"),
    ("신동엽", "shin@example.com", "pw8"),
    ("하하", "haha@example.com", "pw9"),
    ("정준하", "jung@example.com", "pw10"),
]

for username, email, pw in sample_users:
    hashed_pw = sha256(pw.encode()).hexdigest()
    user = User(username=username, email=email, hashed_password=hashed_pw)
    db.add(user)

db.commit()
db.close()

