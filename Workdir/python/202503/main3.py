from fastapi import FastAPI, File ,UploadFile, Form

app = FastAPI()

@app.post("/upload/")
async def create_user(username: str = Form(...), file: UploadFile = File(...)):
    content = await file.read()  # 파일 내용 읽기
    return {"username": username, "filename": file.filename, "content_size": len(content)}