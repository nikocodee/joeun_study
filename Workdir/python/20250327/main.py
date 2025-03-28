# main.py

from fastapi import FastAPI, Request, Response, Form, Cookie
from fastapi.responses import JSONResponse
from session_manager import SessionManager

app = FastAPI()

SESSION_COOKIE_NAME = "session_id"

# 📌 세션 생성 (로그인)
@app.post("/session/create")
async def create_session(response: Response, username: str = Form(...)):
    session_data = {"username": username}
    session_id = SessionManager.create_session(session_data)
    response.set_cookie(key=SESSION_COOKIE_NAME, value=session_id, httponly=True)
    return {"message": f"Session created for {username}"}


# 📌 세션 조회
@app.get("/session/read")
async def read_session(session_id: str = Cookie(default=None)):
    if not session_id:
        return {"message": "No session cookie found"}

    data = SessionManager.get_session(session_id)
    if data:
        return {"session_data": data}
    return {"message": "Session not found or expired"}


# 📌 세션 수정
@app.post("/session/update")
async def update_session(new_username: str = Form(...), session_id: str = Cookie(default=None)):
    if not session_id:
        return {"message": "No session ID found in cookies"}

    data = SessionManager.get_session(session_id)
    if data:
        data["username"] = new_username
        SessionManager.update_session(session_id, data)
        return {"message": f"Session updated to {new_username}"}

    return {"message": "Session not found"}


# 📌 세션 삭제 (로그아웃)
@app.get("/session/delete")
async def delete_session(response: Response, session_id: str = Cookie(default=None)):
    if session_id:
        result = SessionManager.delete_session(session_id)
        response.delete_cookie(key=SESSION_COOKIE_NAME)
        return {"message": f"Session deleted {result}"}
    return {"message": "No session to delete"}