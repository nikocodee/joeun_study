import React, { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import {
  Container,
  Row,
  Col,
  Card,
  Button,
  Image,
  Badge,
  Spinner,
  Form,
  Toast,
  ToastContainer,
} from "react-bootstrap";
import axios from "axios";
import "./ProductDetail.css";

function ProductDetail() {
  const { id } = useParams(); // URL에서 상품 ID 가져오기
  const navigate = useNavigate();
  const [product, setProduct] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [quantity, setQuantity] = useState(1);
  const [showToast, setShowToast] = useState(false);
  const [toastMessage, setToastMessage] = useState("");

  useEffect(() => {
    // 상품 상세 정보를 가져오는 함수
    const fetchProductDetail = async () => {
      setLoading(true);
      try {
        // Spring Boot API 서버에서 상품 상세 정보 가져오기
        const response = await axios.get(
          `http://localhost:8080/api/products/${id}`
        );
        setProduct(response.data);
        setLoading(false);
      } catch (error) {
        console.error("상품 상세 정보를 불러오는데 실패했습니다:", error);
        setError(
          "상품 정보를 불러오는데 실패했습니다. 잠시 후 다시 시도해주세요."
        );

        // 에러 발생 시 더미 데이터 사용
        const dummyProduct = {
          id: parseInt(id),
          name: `상품 ${id}`,
          price: 10000 * parseInt(id),
          description:
            "이 상품에 대한 상세 설명입니다. 다양한 용도로 사용할 수 있는 제품으로, 품질이 우수하고 내구성이 뛰어납니다. 고객 만족도가 매우 높은 제품입니다.",
          imageUrl: "https://picsum.photos/500",
          category: "일반",
          stock: 100,
          rating: 4.5,
          specs: [
            { key: "제조사", value: "샘플 제조사" },
            { key: "원산지", value: "대한민국" },
            { key: "재질", value: "고급 소재" },
            { key: "사이즈", value: "표준" },
          ],
          active: true,
        };
        setProduct(dummyProduct);
        setLoading(false);
      }
    };

    fetchProductDetail();
  }, [id]);

  // 목록으로 돌아가기
  const handleGoBack = () => {
    navigate(-1); // 브라우저 히스토리에서 이전 페이지로 이동
  };

  // 수량 변경 처리
  const handleQuantityChange = (newQuantity) => {
    if (newQuantity >= 1 && newQuantity <= (product?.stock || 100)) {
      setQuantity(newQuantity);
    }
  };

  // 장바구니에 추가
  const addToCart = (goToCart = false) => {
    // 실제로는 로컬 스토리지나 상태 관리 라이브러리에 저장
    try {
      // 여기서는 간단히 로컬 스토리지에 저장하는 방식으로 구현
      const cartItems = JSON.parse(localStorage.getItem("cartItems") || "[]");

      // 이미 장바구니에 있는지 확인
      const existingItemIndex = cartItems.findIndex(
        (item) => item.productId === product.id
      );

      if (existingItemIndex !== -1) {
        // 이미 있으면 수량만 증가
        cartItems[existingItemIndex].quantity += quantity;
      } else {
        // 새 상품 추가
        cartItems.push({
          id: Date.now(), // 임시 ID 생성
          productId: product.id,
          name: product.name,
          price: product.price,
          quantity: quantity,
          image: product.imageUrl,
        });
      }

      localStorage.setItem("cartItems", JSON.stringify(cartItems));

      setToastMessage("상품이 장바구니에 추가되었습니다.");
      setShowToast(true);

      if (goToCart) {
        // 장바구니 페이지로 이동
        setTimeout(() => {
          navigate("/cart");
        }, 1000);
      }
    } catch (error) {
      console.error("장바구니에 추가하는데 실패했습니다:", error);
      setToastMessage("장바구니에 추가하는데 실패했습니다.");
      setShowToast(true);
    }
  };

  if (loading) {
    return (
      <Container className="text-center my-5">
        <Spinner animation="border" variant="primary" />
        <p className="mt-3">상품 정보를 불러오는 중입니다...</p>
      </Container>
    );
  }

  if (error && !product) {
    return (
      <Container className="text-center my-5">
        <div className="alert alert-danger">{error}</div>
        <Button variant="outline-primary" onClick={handleGoBack}>
          목록으로 돌아가기
        </Button>
      </Container>
    );
  }

  return (
    <Container className="product-detail py-4">
      {/* 토스트 메시지 */}
      <ToastContainer position="top-end" className="p-3">
        <Toast
          show={showToast}
          onClose={() => setShowToast(false)}
          delay={3000}
          autohide
        >
          <Toast.Header>
            <strong className="me-auto">알림</strong>
          </Toast.Header>
          <Toast.Body>{toastMessage}</Toast.Body>
        </Toast>
      </ToastContainer>

      {error && <div className="alert alert-warning mb-4">{error}</div>}

      <Button
        variant="outline-secondary"
        onClick={handleGoBack}
        className="mb-4"
      >
        ← 목록으로 돌아가기
      </Button>

      <Card>
        <Card.Body>
          <Row>
            {/* 상품 이미지 */}
            <Col md={5} className="product-image-container mb-4 mb-md-0">
              <Image
                src={product.imageUrl}
                alt={product.name}
                fluid
                className="product-detail-image"
              />
            </Col>

            {/* 상품 정보 */}
            <Col md={7}>
              <Badge bg="secondary" className="mb-2">
                {product.category}
              </Badge>
              <h2 className="product-detail-title">{product.name}</h2>

              <div className="product-detail-price mb-3">
                {product.price.toLocaleString()}원
              </div>

              <div className="product-detail-rating mb-3">
                평점: {product.rating}점
                <div className="stars">
                  {[...Array(5)].map((_, i) => (
                    <span
                      key={i}
                      className={
                        i < Math.floor(product.rating) ? "star filled" : "star"
                      }
                    >
                      ★
                    </span>
                  ))}
                </div>
              </div>

              <div className="product-detail-stock mb-3">
                재고: {product.stock}개
              </div>

              <div className="quantity-selection mb-4">
                <Form.Label>수량</Form.Label>
                <div className="d-flex align-items-center">
                  <Button
                    variant="outline-secondary"
                    onClick={() => handleQuantityChange(quantity - 1)}
                    disabled={quantity <= 1}
                  >
                    -
                  </Button>
                  <Form.Control
                    type="number"
                    min="1"
                    max={product.stock}
                    value={quantity}
                    onChange={(e) =>
                      handleQuantityChange(parseInt(e.target.value) || 1)
                    }
                    className="mx-2 text-center"
                    style={{ width: "70px" }}
                  />
                  <Button
                    variant="outline-secondary"
                    onClick={() => handleQuantityChange(quantity + 1)}
                    disabled={quantity >= product.stock}
                  >
                    +
                  </Button>
                </div>
              </div>

              <div className="product-detail-actions mb-4">
                <Button
                  variant="primary"
                  size="lg"
                  className="me-2"
                  onClick={() => addToCart(false)}
                >
                  장바구니에 담기
                </Button>
                <Button
                  variant="danger"
                  size="lg"
                  onClick={() => addToCart(true)}
                >
                  바로 구매하기
                </Button>
              </div>
            </Col>
          </Row>
        </Card.Body>
      </Card>

      {/* 상품 상세 설명 */}
      <Card className="mt-4">
        <Card.Header>
          <h3>상품 상세정보</h3>
        </Card.Header>
        <Card.Body>
          <div className="product-detail-description mb-4">
            <h4>상품 설명</h4>
            <p>{product.description}</p>
          </div>

          {/* <div className="product-detail-specs">
            <h4>상품 스펙</h4>
            <table className="table">
              <tbody>
                {product.specs && product.specs.map((spec, index) => (
                  <tr key={index}>
                    <th width="30%">{spec.key}</th>
                    <td>{spec.value}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div> */}
        </Card.Body>
      </Card>

      {/* 관련 상품 추천 섹션 (추가 기능으로 구현 가능) */}
      <div className="related-products mt-5">
        <h3 className="mb-4">관련 상품</h3>
        <p className="text-muted">해당 기능은 추후 구현 예정입니다.</p>
      </div>
    </Container>
  );
}

export default ProductDetail;
