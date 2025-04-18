import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { Container, Row, Col } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import './App.css';

// 컴포넌트 임포트
import Navbar from './components/Navbar';
import Sidebar from './components/Sidebar';
import ProductList from './components/ProductList';
import ProductDetail from './components/ProductDetail';
import ImageUploader from './components/ImageUploader';
import CategoryManager from './components/CategoryManager';
import Cart from './components/Cart';
import Footer from './components/Footer';
import AdminProductList from './components/AdminProductList';

function App() {
  return (
    <Router>
      <div className="app">
        {/* 상단 네비게이션 */}
        <Navbar />
        
        <Container fluid>
          <Row>
            {/* 좌측 사이드바 */}
            <Col xs={2} className="sidebar">
              <Sidebar />
            </Col>
            
            {/* 메인 컨텐츠 영역 */}
            <Col xs={10} className="main-content">
              <Routes>
                <Route path="/" element={<ProductList />} />
                <Route path="/category/:id" element={<ProductList />} />
                <Route path="/product/:id" element={<ProductDetail />} />
                <Route path="/cart" element={<Cart />} />
                <Route path="/image-upload" element={<ImageUploader />} />
                <Route path="/admin/categories" element={<CategoryManager />} />
                <Route path="/admin/products" element={<AdminProductList />} />
              </Routes>
            </Col>
          </Row>
        </Container>
        
        {/* 하단 푸터 */}
        <Footer />
      </div>
    </Router>
  );
}

export default App; 