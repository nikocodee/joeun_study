from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker, declarative_base

# MySQL 연결 정보 (환경에 맞게 수정)
DB_HOST = "localhost"
DB_PORT = "3306"
DB_DATABASE = "mydb"
DB_USER = "root"
DB_PASSWORD = "rootpw"
DATABASE_URL = f"mysql+pymysql://{DB_USER}:{DB_PASSWORD}@{DB_HOST}:{DB_PORT}/{DB_DATABASE}?charset=utf8mb4"

engine = create_engine(DATABASE_URL, echo=True, future=True)
SessionLocal = sessionmaker(autocommit=False, autoflush=False, bind=engine)
Base = declarative_base()