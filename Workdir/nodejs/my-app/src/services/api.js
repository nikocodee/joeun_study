import axios from "axios";

const api = axios.create({
  baseURL: "http://localhost:8080", // Spring Boot API 서버 URL
});

// Request 인터셉터를 추가하여 Authorization 헤더에 JWT 추가
api.interceptors.request.use((config) => {
  const token = localStorage.getItem("jwt");
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

export default api;
