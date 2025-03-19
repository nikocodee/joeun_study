import axios from "axios";

const API_URL = "http://localhost:8080/board"; // 백엔드 API 주소

export const fetchBoards = async (params) => {
  //{ params }는 키와 밸류를 같은 이름으로 지정
  const response = await axios.get(API_URL, { params });
  console.log("***** Server Response *****\n", response);
  // console.log("fetchBoards Call!!");
  // const dummy =
  // '{"data": {"content":[{"writer":"test1","title":"Test Title1"},{"writer":"test2","title":"Test Title2"}],"totalPages":1}}';
  // console.log(dummy);
  // const response = JSON.parse(dummy);
  // console.log(response.data);
  //data가 JSON.stringify()로 찍어봐서 JSON인지 List인지 확인하기
  return response.data;
};

export const fetchBoardDetail = async (id) => {
  const response = await axios.get(`${API_URL}/${id}`);
  // const dummy =
  // '{"data": {"content":{"writer":"test1","title":"Test Title1"},"totalPages":1}}';
  // const response = JSON.parse(dummy);
  return response.data;
};

export const updateBoard = async (id, boardData) => {
  const response = await axios.put(`${API_URL}/${id}`, boardData);
  return response.data;
};

export const createBoard = async (formData) => {
  const response = await axios.post(API_URL, formData, {
    headers: {
      "Content-Type": "multipart/form-data",
    },
  });
  return response.data;
};
