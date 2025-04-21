import React, { useState, useEffect } from 'react';
import { Row, Col, Card, Button, Modal, Alert } from 'react-bootstrap';
import { useNavigate, useParams } from 'react-router-dom';
import axios from 'axios';
import './ProductList.css';
import ImageUploader from './ImageUploader';

function ProductList() {
  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [showUploader, setShowUploader] = useState(false);
  const [categoryName, setCategoryName] = useState('전체');
  const navigate = useNavigate();
  const { id: categoryId } = useParams(); // URL에서 카테고리 ID 가져오기

  useEffect(() => {
    // 상품 목록 API 호출
    const fetchProducts = async () => {
      setLoading(true);
      setError(null);
      
      try {
        let response;
        
        // 카테고리 ID가 있으면 해당 카테고리의 상품만 가져오기
        if (categoryId) {
          // category 파라미터로 카테고리 ID 전달
          response = await axios.get(`http://localhost:8080/api/categories/cat/${categoryId}`, {
            params: {
              category: categoryId
            }
          });
          
          // 카테고리 이름 가져오기
          try {
            const categoryResponse = await axios.get(`http://localhost:8080/api/categories/${categoryId}`);
            setCategoryName(categoryResponse.data.name);
          } catch (categoryError) {
            console.error('카테고리 정보를 불러오는데 실패했습니다:', categoryError);
            setCategoryName(`카테고리 ${categoryId}`);
          }
        } else {
          // 카테고리 ID가 없으면 모든 상품 가져오기
          response = await axios.get('http://localhost:8080/api/products');
          setCategoryName('전체');
        }
        
        setProducts(response.data);
        setLoading(false);
      } catch (error) {
        console.error('상품 데이터를 불러오는데 실패했습니다:', error);
        setError('상품 목록을 불러오는데 실패했습니다. 잠시 후 다시 시도해주세요.');
        
        // 에러 발생 시 더미 데이터 사용
        const dummyProducts = [
          { 
            id: 1, 
            name: '상품 1', 
            price: 10000, 
            imageUrl: 'https://picsum.photos/100',
            description: '상품 1에 대한 상세 설명입니다.',
            category: {
              key: '기타',
              value: 1
            },
            stock: 50,
            rating: 4.2,
            specs: [{ key: '제조사', value: '샘플 제조사' }],
            active: true
          },
          { 
            id: 2, 
            name: '상품 2', 
            price: 20000, 
            imageUrl: 'https://picsum.photos/100',
            description: '상품 2에 대한 상세 설명입니다.',
            category: {
              key: '가전',
              value: 2
            },
            stock: 30,
            rating: 4.5,
            specs: [{ key: '제조사', value: '샘플 제조사' }],
            active: true
          },
          { 
            id: 3, 
            name: '상품 3', 
            price: 30000, 
            imageUrl: 'https://picsum.photos/100',
            description: '상품 3에 대한 상세 설명입니다.',
            category: {
              key: '패션',
              value: 3
            },
            stock: 20,
            rating: 3.8,
            specs: [{ key: '원산지', value: '대한민국' }],
            active: true
          },
          { 
            id: 4, 
            name: '상품 4', 
            price: 40000, 
            imageUrl: 'https://picsum.photos/100',
            description: '상품 4에 대한 상세 설명입니다.',
            category: {
              key: '식품',
              value: 4
            },
            stock: 100,
            rating: 4.0,
            specs: [{ key: '유통기한', value: '2023-12-31' }],
            active: true
          },
          { 
            id: 5, 
            name: '상품 5', 
            price: 50000, 
            imageUrl: 'https://picsum.photos/100',
            description: '상품 5에 대한 상세 설명입니다.',
            category: {
              key: '생활',
              value: 5
            },
            stock: 15,
            rating: 4.7,
            specs: [{ key: '재질', value: '면 100%' }],
            active: true
          },
          { 
            id: 6, 
            name: '상품 6', 
            price: 60000, 
            imageUrl: 'https://picsum.photos/100',
            description: '상품 6에 대한 상세 설명입니다.',
            category: {
              key: '기타',
              value: 8
            },
            stock: 8,
            rating: 4.3,
            specs: [{ key: '사이즈', value: 'FREE' }],
            active: true
          },
        ];
        
        // 카테고리 ID가 있으면 해당 카테고리의 상품만 필터링
        if (categoryId) {
          const filteredProducts = dummyProducts.filter(
            product => String(product.category.value) === String(categoryId)
          );
          setProducts(filteredProducts);
          
          // 카테고리 이름 설정
          const categoryProduct = dummyProducts.find(
            product => String(product.category.value) === String(categoryId)
          );
          setCategoryName(categoryProduct ? categoryProduct.category.value : categoryId);
        } else {
          setProducts(dummyProducts);
          setCategoryName('전체');
        }
        
        setLoading(false);
      }
    };

    fetchProducts();
  }, [categoryId]); // categoryId가 변경될 때마다 데이터 다시 가져오기

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
        <h2>카테고리 {categoryName} 상품 목록</h2>
        <Button variant="primary" onClick={handleOpenUploader}>
          이미지 업로드
        </Button>
      </div>
      
      {error && <div className="alert alert-danger mb-4">{error}</div>}
      
      {products.length === 0 && !error && (
        <Alert variant="info">
          해당 카테고리에 상품이 없습니다.
        </Alert>
      )}
      
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
                style={{ width: '100px', height: '100px', objectFit: 'cover' }}
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