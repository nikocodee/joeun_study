# main.py
from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware

# 서버 객체 생성
app = FastAPI()

# CORS 미들웨어 추가
app.add_middleware(
    CORSMiddleware,
    allow_origins=["http://localhost:3000"],  # 모든 도메인 허용
    allow_credentials=True,
    allow_methods=["*"],  # 모든 HTTP 메서드 허용
    allow_headers=["*"],  # 모든 헤더 허용
)

# 1. GET 요청: 전체 게시물 목록 조회하는 API
@app.get("/board")
def getBoards():
    content = [{"writer":"Writer name","id":1,"title":"title test ","content":"test content1 "},{"writer":"Writer name2","id":2,"title":"title test2","content":"test content2"}]
    return {"content": content, "totalPages": 100}

# 2. GET 요청: 게시물 상세페이지 조회하는 API
# path variable 필수 요소에만 사용
@app.get("/board/{id}")
def getBoardById(id: int):
    content = {
    "createdAt": "2025/05/05",
    "writer": "writer test",
    "id": id,
    "title": f"test title {id}",
    "content": "content text",
    "views": 13
}
    return content
