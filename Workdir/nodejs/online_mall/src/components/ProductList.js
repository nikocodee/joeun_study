import React from "react";
import { Col, Row } from "react-bootstrap";
import ProductCard from "./ProductCard";

function ProductList({ products }) {
  return (
    <Row>
      {products.map((product, idx) => (
        <Col key={product.id} xs={12} sm={6} md={4} lg={2}>
          <ProductCard product={product} />
        </Col>
      ))}
    </Row>
  );
}

export default ProductList;
