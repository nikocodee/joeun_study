# session_manager.py

import uuid
import json
import redis

# Redis 클라이언트 설정
r = redis.Redis(host="localhost", port=6379, db=0, decode_responses=True)

SESSION_EXPIRE_SECONDS = 3600  # 1시간

class SessionManager:
    @staticmethod
    def create_session(data: dict) -> str:
        session_id = str(uuid.uuid4())
        r.setex(session_id, SESSION_EXPIRE_SECONDS, json.dumps(data))
        return session_id

    @staticmethod
    def get_session(session_id: str) -> dict | None:
        value = r.get(session_id)
        return json.loads(value) if value else None

    @staticmethod
    def update_session(session_id: str, data: dict) -> bool:
        if r.exists(session_id):
            r.setex(session_id, SESSION_EXPIRE_SECONDS, json.dumps(data))
            return True
        return False

    @staticmethod
    def delete_session(session_id: str) -> bool:
        return r.delete(session_id) > 0