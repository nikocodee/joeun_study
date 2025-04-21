import React, { useState, useEffect } from 'react';
import { Navbar as BootstrapNavbar, Nav, Container, NavDropdown, Badge, Spinner } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import axios from 'axios';
import './Navbar.css';

function Navbar() {
  const [cartItemCount, setCartItemCount] = useState(0);
  const [categories, setCategories] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  // 카테고리 정보 가져오기
  useEffect(() => {
    const fetchCategories = async () => {
      setLoading(true);
      setError(null);
      try {
        const response = await axios.get("http://localhost:8080/api/categories");
        setCategories(response.data);
        setLoading(false);
      } catch (err) {
        console.error("카테고리 정보를 가져오는데 실패했습니다:", err);
        setError("카테고리 로딩 실패");
        // 에러 발생 시 더미 데이터 사용
        const dummyCategories = [
          { id: 1, name: "패션" },
          { id: 2, name: "가전" },
          { id: 3, name: "식품" },
          { id: 4, name: "생활" },
          { id: 5, name: "스포츠" },
          { id: 6, name: "도서" },
          { id: 7, name: "취미" },
          { id: 8, name: "뷰티" },
        ];
        setCategories(dummyCategories);
        setLoading(false);
      }
    };

    fetchCategories();
  }, []);

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

  // 카테고리 드롭다운 렌더링
  const renderCategoryDropdown = () => {
    if (loading) {
      return (
        <NavDropdown.Item>
          <Spinner animation="border" size="sm" /> 로딩 중...
        </NavDropdown.Item>
      );
    }

    if (error && categories.length === 0) {
      return <NavDropdown.Item className="text-danger">{error}</NavDropdown.Item>;
    }

    return categories.map(category => (
      <NavDropdown.Item 
        key={category.id} 
        as={Link} 
        to={`/category/${category.id}`}
      >
        {category.name}
      </NavDropdown.Item>
    ));
  };

  return (
    <BootstrapNavbar bg="light" expand="lg" className="main-navbar">
      <Container>
        <BootstrapNavbar.Brand as={Link} to="/">쇼핑몰</BootstrapNavbar.Brand>
        <BootstrapNavbar.Toggle aria-controls="basic-navbar-nav" />
        <BootstrapNavbar.Collapse id="basic-navbar-nav">
          <Nav className="me-auto">
            <Nav.Link as={Link} to="/">홈</Nav.Link>
            
            <NavDropdown title="카테고리" id="category-dropdown">
              {renderCategoryDropdown()}
            </NavDropdown>
            
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