import axios from "axios";
import React, { useEffect, useState } from "react";
import { Button, Card } from "react-bootstrap";
import { useParams } from "react-router-dom";

const ProductDetailPage = ({ onAddToCart }) => {
  const { productId } = useParams();
  const [product, setProduct] = useState(null);

  useEffect(() => {
    axios
      .get(`http://localhost:8080/api/product/category=${productId}`)
      .then((res) => setProduct(res.data));
  }, [productId]);

  if (!product) return <div>로딩 중...</div>;

  return (
    <Card className="p-4 m-4">
      {/* <Card.Imgsrc={product.imageUrl}
        style={{ width: '200px', height: '200px', objectFit: 'cover' }}
      /> */}
      <Card.Body>
        <Card.Title>{product.name}</Card.Title>
        <Card.Text>{product.description}</Card.Text>
        <Card.Text>
          <strong>{product.price.toLocaleString()}원</strong>
        </Card.Text>
        <Button variant="primary" onClick={() => onAddToCart(product)}>
          장바구니 담기
        </Button>
      </Card.Body>
    </Card>
  );
};

export default ProductDetailPage;
