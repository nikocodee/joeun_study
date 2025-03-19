import axios from "axios";

const API_URL = "http://localhost:8080/board";

export const fetchComments = async (userId) => {
  const response = await axios.get(`${API_URL}/${userId}/comment`);
  return response.data;
};

export const addComment = async (userId, commentData) => {
  const response = await axios.post(
    `${API_URL}/${userId}/comment`,
    commentData
  );
  return response.data;
};
