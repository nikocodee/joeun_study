import React, { useState, useEffect } from "react";
import { Row, Col, Card, Button, Modal } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import "./ProductList.css";
import ImageUploader from "./ImageUploader";

function ProductList() {
  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [showUploader, setShowUploader] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {
    // 상품 목록 API 호출
    const fetchProducts = async () => {
      setLoading(true);

      let response;
      try {
        // Spring Boot API 서버에서 상품 목록 가져오기
        if (category) {
          response = await axios.get("http://localhost:8080/api/products");
        } else {
          response = await axios.get(
            `http://localhost:8080/api/products/cat/${category}`
          );
        }
        setProducts(response.data);
        setLoading(false);
      } catch (error) {
        console.error("상품 데이터를 불러오는데 실패했습니다:", error);
        setError(
          "상품 목록을 불러오는데 실패했습니다. 잠시 후 다시 시도해주세요."
        );

        // 에러 발생 시 더미 데이터 사용
        const dummyProducts = [
          {
            id: 1,
            name: "상품 1",
            price: 10000,
            imageUrl: "https://via.placeholder.com/100",
            description: "상품 1에 대한 상세 설명입니다.",
            category: "기타",
            stock: 50,
            rating: 4.2,
            specs: [{ key: "제조사", value: "샘플 제조사" }],
            active: true,
          },
          {
            id: 2,
            name: "상품 2",
            price: 20000,
            imageUrl: "https://via.placeholder.com/100",
            description: "상품 2에 대한 상세 설명입니다.",
            category: "가전",
            stock: 30,
            rating: 4.5,
            specs: [{ key: "제조사", value: "샘플 제조사" }],
            active: true,
          },
          {
            id: 3,
            name: "상품 3",
            price: 30000,
            imageUrl: "https://via.placeholder.com/100",
            description: "상품 3에 대한 상세 설명입니다.",
            category: "패션",
            stock: 20,
            rating: 3.8,
            specs: [{ key: "원산지", value: "대한민국" }],
            active: true,
          },
          {
            id: 4,
            name: "상품 4",
            price: 40000,
            imageUrl: "https://via.placeholder.com/100",
            description: "상품 4에 대한 상세 설명입니다.",
            category: "식품",
            stock: 100,
            rating: 4.0,
            specs: [{ key: "유통기한", value: "2023-12-31" }],
            active: true,
          },
          {
            id: 5,
            name: "상품 5",
            price: 50000,
            imageUrl: "https://via.placeholder.com/100",
            description: "상품 5에 대한 상세 설명입니다.",
            category: "생활",
            stock: 15,
            rating: 4.7,
            specs: [{ key: "재질", value: "면 100%" }],
            active: true,
          },
          {
            id: 6,
            name: "상품 6",
            price: 60000,
            imageUrl: "https://via.placeholder.com/100",
            description: "상품 6에 대한 상세 설명입니다.",
            category: "기타",
            stock: 8,
            rating: 4.3,
            specs: [{ key: "사이즈", value: "FREE" }],
            active: true,
          },
        ];
        setProducts(dummyProducts);
        setLoading(false);
      }
    };

    fetchProducts();
  }, []);

  // 이미지 업로드 모달 열기/닫기
  const handleOpenUploader = () => setShowUploader(true);
  const handleCloseUploader = () => setShowUploader(false);

  // 상품 상세 페이지로 이동
  const handleProductClick = (productId) => {
    navigate(`/product/${productId}`);
  };

  if (loading) {
    return <div>로딩 중...</div>;
  }

  return (
    <div className="product-list">
      <div className="d-flex justify-content-between align-items-center mb-4">
        <h2>상품 목록</h2>
        <Button variant="primary" onClick={handleOpenUploader}>
          이미지 업로드
        </Button>
      </div>

      {error && <div className="alert alert-danger mb-4">{error}</div>}

      <Row xs={1} md={2} lg={6} className="g-4">
        {products.map((product) => (
          <Col key={product.id}>
            <Card
              className="product-card"
              onClick={() => handleProductClick(product.id)}
            >
              <Card.Img
                variant="top"
                src={product.imageUrl}
                style={{ width: "100px", height: "100px", objectFit: "cover" }}
              />
              <Card.Body>
                <Card.Title className="product-title">
                  {product.name}
                </Card.Title>
                <Card.Text className="product-price">
                  {product.price.toLocaleString()}원
                </Card.Text>
              </Card.Body>
            </Card>
          </Col>
        ))}
      </Row>

      {/* 이미지 업로드 모달 */}
      <Modal show={showUploader} onHide={handleCloseUploader} size="lg">
        <Modal.Header closeButton>
          <Modal.Title>이미지 업로드</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <ImageUploader />
        </Modal.Body>
      </Modal>
    </div>
  );
}

export default ProductList;
