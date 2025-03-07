from fastapi import FastAPI, Form

app = FastAPI()

# 회원가입 폼 데이터를 처리하는 예시
@app.post("/signup/")
def signup(username: str = Form(...), password: str = Form(...)):
    return {"username": username, "password": password}