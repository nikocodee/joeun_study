import React from 'react';
import { Container, Row, Col } from 'react-bootstrap';
import './Footer.css';

function Footer() {
  return (
    <footer className="footer">
      <Container>
        <Row>
          <Col md={4}>
            <h5>회사 정보</h5>
            <p>주소: 서울시 강남구 테헤란로 123</p>
            <p>전화: 02-123-4567</p>
            <p>이메일: info@shoppingmall.com</p>
          </Col>
          <Col md={4}>
            <h5>고객 서비스</h5>
            <ul className="list-unstyled">
              <li>공지사항</li>
              <li>자주 묻는 질문</li>
              <li>1:1 문의</li>
              <li>이용약관</li>
            </ul>
          </Col>
          <Col md={4}>
            <h5>소셜 미디어</h5>
            <ul className="list-unstyled">
              <li>페이스북</li>
              <li>인스타그램</li>
              <li>트위터</li>
              <li>유튜브</li>
            </ul>
          </Col>
        </Row>
        <Row className="mt-3">
          <Col className="text-center">
            <p className="copyright">
              © 2024 쇼핑몰. All rights reserved.
            </p>
          </Col>
        </Row>
      </Container>
    </footer>
  );
}

export default Footer; 