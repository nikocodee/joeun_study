import axios from "axios";

const API_URL = "http://localhost:8080/products";

//fetch 값을 쌍을 짓는다는 의미
export const fetchProducts = async (params) => {
  const response = await axios.get(API_URL, { params });
  return response.data;
};
