import axios from "axios";

export const fetchProductsByCategory = async (categoryId) => {
  const response = await axios.get(
    `http://localhost:8080/api/product?category=${categoryId}`
  );
  const data = response.data;
  return data;
};
