# main.py
from fastapi import FastAPI

# 서버 객체 생성
app = FastAPI()

# 1. GET 요청: 기본 데이터를 조회하는 API
# path variable 필수 요소에만 사용
@app.get("/items/{item_id}")
def read_item(item_id: int, q: str = None):
    return {"item_id": item_id, "q": int(q)}

# 2. POST 요청: 새로운 아이템을 추가하는 API (JSON으로 보냄)
@app.post("/items/")
def create_item(name: str, description: str = None):
    return {"name": name, "description": description}

# 3. PUT 요청: 기존 아이템을 수정하는 API
@app.put("/items/{item_id}")
def update_item(item_id: int, name: str):
    return {"item_id": item_id, "name": name}

# 4. DELETE 요청: 특정 아이템을 삭제하는 API
@app.delete("/items/{item_id}")
def delete_item(item_id: int):
    return {"message": f"Item {item_id} deleted"}