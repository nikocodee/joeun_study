import React, { useState, useEffect } from 'react';
import { Navbar as BootstrapNavbar, Nav, Container, NavDropdown, Badge } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import './Navbar.css';

function Navbar() {
  const [cartItemCount, setCartItemCount] = useState(0);

  // 장바구니 아이템 개수 계산 및 업데이트
  useEffect(() => {
    // 초기 로드 시 장바구니 아이템 수 계산
    updateCartCount();

    // 로컬 스토리지 변경 이벤트 리스너 추가
    window.addEventListener('storage', updateCartCount);

    // 주기적으로 장바구니 개수 업데이트 (1초마다)
    const intervalId = setInterval(updateCartCount, 1000);

    // 컴포넌트 언마운트 시 정리
    return () => {
      window.removeEventListener('storage', updateCartCount);
      clearInterval(intervalId);
    };
  }, []);

  // 장바구니 아이템 개수 업데이트 함수
  const updateCartCount = () => {
    try {
      const cartItems = JSON.parse(localStorage.getItem('cartItems') || '[]');
      const count = cartItems.reduce((total, item) => total + item.quantity, 0);
      setCartItemCount(count);
    } catch (error) {
      console.error('장바구니 데이터를 불러오는데 실패했습니다:', error);
      setCartItemCount(0);
    }
  };

  return (
    <BootstrapNavbar bg="light" expand="lg" className="main-navbar">
      <Container>
        <BootstrapNavbar.Brand as={Link} to="/">쇼핑몰</BootstrapNavbar.Brand>
        <BootstrapNavbar.Toggle aria-controls="basic-navbar-nav" />
        <BootstrapNavbar.Collapse id="basic-navbar-nav">
          <Nav className="me-auto">
            <Nav.Link as={Link} to="/">홈</Nav.Link>
            <Nav.Link as={Link} to="/category/1">패션</Nav.Link>
            <Nav.Link as={Link} to="/category/2">가전</Nav.Link>
            <Nav.Link as={Link} to="/category/3">식품</Nav.Link>
            <Nav.Link as={Link} to="/category/4">생활</Nav.Link>
            <Nav.Link as={Link} to="/image-upload">이미지 업로드</Nav.Link>
            
            <NavDropdown title="관리자" id="admin-dropdown">
              <NavDropdown.Item as={Link} to="/admin/products">상품 관리</NavDropdown.Item>
              <NavDropdown.Item as={Link} to="/admin/categories">카테고리 관리</NavDropdown.Item>
            </NavDropdown>
          </Nav>
          
          {/* 장바구니 링크 (오른쪽에 위치) */}
          <Nav>
            <Nav.Link as={Link} to="/cart" className="cart-icon-link">
              <i className="bi bi-cart3"></i> 장바구니
              {cartItemCount > 0 && (
                <Badge pill bg="danger" className="cart-badge">
                  {cartItemCount}
                </Badge>
              )}
            </Nav.Link>
          </Nav>
        </BootstrapNavbar.Collapse>
      </Container>
    </BootstrapNavbar>
  );
}

export default Navbar; 