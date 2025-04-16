import React, { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import { fetchProductsByCategory } from "../api/productApi";
import Sidebar from "../components/Sidebar";
import ProductList from "../components/ProductList";

function CategoryPage() {
  const { categoryId } = useParams();
  const [products, setProducts] = useState([]);

  useEffect(() => {
    //then 다음은 함수 이름을 등록
    // fetchProductsByCategory(categoryId).then((resp)=>(setProducts(resp)));
    fetchProductsByCategory(categoryId).then(setProducts);
  }, [categoryId]);

  return (
    <div style={{ display: "flex", marginTop: "20px", paddingBottom: "80px" }}>
      <Sidebar />
      <div style={{ flexGrow: 1, padding: "0 20px" }}>
        <h4>카테고리 {categoryId} 상품 목록</h4>
        <ProductList products={products} />
      </div>
    </div>
  );
}

export default CategoryPage;
