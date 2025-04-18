import React, { useState, useEffect } from 'react';
import { Container, Row, Col, Card, Button, Table, Form, Alert } from 'react-bootstrap';
import { Link, useNavigate } from 'react-router-dom';
import './Cart.css';

function Cart() {
  const [cartItems, setCartItems] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  // 장바구니 데이터 로드 (로컬 스토리지에서 가져옴)
  useEffect(() => {
    const loadCartItems = () => {
      try {
        // 로컬 스토리지에서 장바구니 데이터 로드
        const savedCartItems = JSON.parse(localStorage.getItem('cartItems') || '[]');
        setCartItems(savedCartItems);
        setLoading(false);
      } catch (error) {
        console.error('장바구니 데이터를 불러오는데 실패했습니다:', error);
        setError('장바구니 정보를 불러오는데 실패했습니다.');
        setLoading(false);
      }
    };

    // 0.5초 지연 (로딩 UI 표시 목적)
    setTimeout(() => {
      loadCartItems();
    }, 500);
  }, []);

  // 수량 변경 처리
  const handleQuantityChange = (id, newQuantity) => {
    if (newQuantity < 1) return;
    
    const updatedItems = cartItems.map(item => 
      item.id === id ? { ...item, quantity: newQuantity } : item
    );
    
    setCartItems(updatedItems);
    
    // 로컬 스토리지 업데이트
    localStorage.setItem('cartItems', JSON.stringify(updatedItems));
  };

  // 상품 삭제 처리
  const handleRemoveItem = (id) => {
    if (window.confirm('이 상품을 장바구니에서 삭제하시겠습니까?')) {
      const updatedItems = cartItems.filter(item => item.id !== id);
      setCartItems(updatedItems);
      
      // 로컬 스토리지 업데이트
      localStorage.setItem('cartItems', JSON.stringify(updatedItems));
    }
  };

  // 전체 삭제 처리
  const handleClearCart = () => {
    if (window.confirm('장바구니를 비우시겠습니까?')) {
      setCartItems([]);
      
      // 로컬 스토리지 업데이트
      localStorage.setItem('cartItems', JSON.stringify([]));
    }
  };

  // 상품 상세 페이지로 이동
  const handleProductClick = (productId) => {
    navigate(`/product/${productId}`);
  };

  // 총액 계산
  const calculateTotal = () => {
    return cartItems.reduce((total, item) => total + (item.price * item.quantity), 0);
  };

  // 쇼핑 계속하기
  const handleContinueShopping = () => {
    navigate('/');
  };

  // 주문하기
  const handleCheckout = () => {
    alert('주문 기능은 아직 구현되지 않았습니다.');
    // navigate('/checkout');
  };

  if (loading) {
    return (
      <Container className="cart-container py-4">
        <div className="text-center my-5">
          <div className="spinner-border text-primary" role="status">
            <span className="visually-hidden">로딩 중...</span>
          </div>
          <p className="mt-3">장바구니 정보를 불러오는 중입니다...</p>
        </div>
      </Container>
    );
  }

  if (error) {
    return (
      <Container className="cart-container py-4">
        <Alert variant="danger">{error}</Alert>
        <Button variant="primary" onClick={handleContinueShopping}>쇼핑 계속하기</Button>
      </Container>
    );
  }

  return (
    <Container className="cart-container py-4">
      <h2 className="mb-4">장바구니</h2>
      
      {cartItems.length === 0 ? (
        <div className="empty-cart text-center my-5">
          <i className="bi bi-cart-x empty-cart-icon"></i>
          <h3 className="mt-3">장바구니가 비어있습니다</h3>
          <p className="text-muted">장바구니에 담긴 상품이 없습니다.</p>
          <Button variant="primary" onClick={handleContinueShopping}>
            쇼핑 계속하기
          </Button>
        </div>
      ) : (
        <>
          <Table responsive className="cart-table">
            <thead>
              <tr>
                <th width="40%">상품정보</th>
                <th width="15%" className="text-center">수량</th>
                <th width="15%" className="text-end">단가</th>
                <th width="15%" className="text-end">상품금액</th>
                <th width="10%" className="text-center">삭제</th>
              </tr>
            </thead>
            <tbody>
              {cartItems.map((item) => (
                <tr key={item.id} className="cart-item">
                  <td>
                    <div className="cart-product-info d-flex align-items-center">
                      <div 
                        className="cart-product-image me-3" 
                        onClick={() => handleProductClick(item.productId)}
                      >
                        <img src={item.image} alt={item.name} className="product-thumbnail" />
                      </div>
                      <div 
                        className="cart-product-name"
                        onClick={() => handleProductClick(item.productId)}
                      >
                        {item.name}
                      </div>
                    </div>
                  </td>
                  <td className="text-center">
                    <div className="quantity-control d-flex align-items-center justify-content-center">
                      <Button 
                        variant="outline-secondary" 
                        size="sm"
                        onClick={() => handleQuantityChange(item.id, item.quantity - 1)}
                        disabled={item.quantity <= 1}
                      >
                        -
                      </Button>
                      <Form.Control
                        type="number"
                        min="1"
                        value={item.quantity}
                        onChange={(e) => handleQuantityChange(item.id, parseInt(e.target.value) || 1)}
                        className="quantity-input mx-2"
                      />
                      <Button 
                        variant="outline-secondary" 
                        size="sm"
                        onClick={() => handleQuantityChange(item.id, item.quantity + 1)}
                      >
                        +
                      </Button>
                    </div>
                  </td>
                  <td className="text-end unit-price-col">
                    {item.price.toLocaleString()}원
                  </td>
                  <td className="text-end total-price-col">
                    {(item.price * item.quantity).toLocaleString()}원
                  </td>
                  <td className="text-center">
                    <Button 
                      variant="outline-danger" 
                      size="sm"
                      onClick={() => handleRemoveItem(item.id)}
                    >
                      삭제
                    </Button>
                  </td>
                </tr>
              ))}
            </tbody>
            <tfoot>
              <tr className="cart-summary-row">
                <td colSpan="3" className="text-end fw-bold">총 상품금액:</td>
                <td className="text-end fw-bold total-amount">
                  {calculateTotal().toLocaleString()}원
                </td>
                <td></td>
              </tr>
            </tfoot>
          </Table>
          
          <div className="d-flex justify-content-between mb-4">
            <Button 
              variant="outline-secondary" 
              onClick={handleClearCart}
              disabled={cartItems.length === 0}
            >
              장바구니 비우기
            </Button>
            <Button variant="outline-primary" onClick={handleContinueShopping}>
              쇼핑 계속하기
            </Button>
          </div>
          
          <Card className="cart-summary">
            <Card.Body>
              <Row>
                <Col md={6} className="d-flex align-items-center justify-content-center mb-3 mb-md-0">
                  <div className="d-flex flex-column align-items-center">
                    <p className="mb-0">총 {cartItems.reduce((total, item) => total + item.quantity, 0)}개 상품</p>
                    <h4 className="mt-2">{calculateTotal().toLocaleString()}원</h4>
                  </div>
                </Col>
                <Col md={6}>
                  <Button 
                    variant="danger" 
                    size="lg" 
                    className="w-100"
                    onClick={handleCheckout}
                    disabled={cartItems.length === 0}
                  >
                    주문하기
                  </Button>
                </Col>
              </Row>
            </Card.Body>
          </Card>
        </>
      )}
    </Container>
  );
}

export default Cart; 