import React, { useState, useEffect } from "react";
import { Nav, Spinner } from "react-bootstrap";
import { Link } from "react-router-dom";
import axios from "axios";
import "./Sidebar.css";

function Sidebar() {
  const [categories, setCategories] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchCategory = async () => {
      setLoading(true);
      setError(null);
      try {
        const response = await axios.get(
          "http://localhost:8080/api/categories"
        );
        setCategories(response.data);
        setLoading(false);
      } catch (err) {
        console.log("Axios Error : ", err);
        setError("카테고리 정보 에러!!!");
        const dummyCategory = [
          { id: 1, name: "패션" },
          { id: 2, name: "가전" },
          { id: 3, name: "식품" },
          { id: 4, name: "생활" },
          { id: 5, name: "스포츠" },
          { id: 6, name: "도서" },
          { id: 7, name: "취미" },
          { id: 8, name: "뷰티" },
        ];
        setCategories(dummyCategory);
        setLoading(false);
      }
    };

    fetchCategory();
  }, []);

  if (loading) {
    return <div>로딩 중...</div>;
  }

  return (
    <Nav className="flex-column sidebar-menu">
      {error && <div className="alert alert-danger mb-4">{error}</div>}
      {categories.map((category) => (
        <Nav.Link
          key={category.id}
          as={Link}
          to={`/category/${category.id}`}
          className="sidebar-item"
        >
          {category.name}
        </Nav.Link>
      ))}
    </Nav>
  );
}

export default Sidebar;
