from fastapi import FastAPI, File, UploadFile

app = FastAPI()

# 파일 업로드를 처리하는 API 예시
@app.post("/upload/")
async def upload_file(file: UploadFile = File(...)):
    content = await file.read()  # 파일 내용을 읽음
    return {"filename": file.filename, "content_size": len(content)}