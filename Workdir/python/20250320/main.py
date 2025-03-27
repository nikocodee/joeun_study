# main.py
from fastapi import FastAPI, Request, Form, UploadFile, File
from fastapi.middleware.cors import CORSMiddleware
# 타입캐스팅하기 위한 import
from typing import List
import uuid
import os
from starlette.middleware.sessions import SessionMiddleware

# 서버 객체 생성
app = FastAPI()

app.add_middleware(SessionMiddleware, secret_key="super-secret-key111", max_age = 1800)

UPLOAD_FOLDER = "uploads"
os.makedirs(UPLOAD_FOLDER, exist_ok = True)

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
def getBoards(req: Request):
    req.session["userId"] = "test"
    content = [{"writer":"Writer name","id":1,"title":"title test ","content":"test content1 "},{"writer":"Writer name2","id":2,"title":"title test2","content":"test content2"}]
    return {"content": content, "totalPages": 100}

# 2. GET 요청: 게시물 상세페이지 조회하는 API
# path variable 필수 요소에만 사용
@app.get("/board/{id}")
def getBoardById(id: int, req: Request):
    content = {
    "createdAt": "2025/05/05",
    #"writer": "writer test",
    "writer": req.session.get("userId"),
    "id": id,
    "title": f"test title {id}",
    "content": "content text",
    "views": 13
}
    return content

# 3. POST 요청: 게시물 등록페이지 API
# ...은 정보가 여러개라는 뜻
@app.post("/board")
async def createBoard(req: Request, writer: str = Form(...), title: str = Form(...), content: str = Form(...), file: List[UploadFile] = File(...)):
    # 응답 기본 구조
    result = {"writer": writer, "Title": title, "Content": content, "Files":[]}
    # 파일 리스트 확인
    cond = len(file) > 0
    if(cond):
        for attach in file:
            # 파일 읽기
            fileBody = await attach.read()
            #fileJson = {"writer": writer, "Title": title, "File Size": len(fileBody), "file": attach.filename}
            #fileJson = {f"writer": writer, "Title": title, "File Size": len(fileBody), attach.filename: uuid.uuid4()}
            #result.append(fileJson)
            
            # UUID 기반 파일 이름 생성
            uuidName = uuid.uuid4()
            filePath = os.path.join(UPLOAD_FOLDER, str(uuidName))
            
            # with는 자동으로 close해 줌
            #with open(f"./uploads/{uuidName}_{attach.filename}", "wb") as f:
            #with open(f"./uploads/{uuidName}", "wb") as f:
            
            # 파일 정보 추가
            fileJson = {"original_name": attach.filename, "uuid_filename":  str(uuidName)}
            result["Files"].append(fileJson)
            
            # 파일 저장
            with open(filePath, "wb") as f:
                f.write(fileBody)
                print("Save OK!!!")
            
    req.session.clear()
    result["writer"] = req.session.get("userId")
    return result