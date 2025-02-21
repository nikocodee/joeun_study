# Python 3.9 기반 이미지 사용
FROM python:3.9

# 작업 디렉터리 설정
WORKDIR /app

# 필요한 패키지 설치
COPY app/requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt

# 애플리케이션 소스 코드 복사
COPY app /app

# FastAPI 서버 실행
CMD ["uvicorn", "main:app", "--host", "0.0.0.0", "--port", "8000", "--reload"]