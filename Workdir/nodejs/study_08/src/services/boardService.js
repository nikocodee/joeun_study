import axios from "axios";

const API_URL = "http://localhost:8080/api/users"; // 백엔드 API 주소

export const fetchBoards = async (params) => {
  //{ params }는 키와 밸류를 같은 이름으로 지정
  const response = await axios.get(API_URL, { params });
  //data가 JSON.stringify()로 찍어봐서 JSON인지 List인지 확인하기
  return response.data;
};

export const fetchBoardDetail = async (id) => {
  const response = await axios.get(`${API_URL}/${id}`);
  return response.data;
};
