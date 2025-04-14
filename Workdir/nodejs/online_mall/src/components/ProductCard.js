import React from "react";
import { Card } from "react-bootstrap";

const ProductCard = ({ product }) => {
  return (
    <Card style={{ width: "180px", margin: "10px" }}>
      <Card.Img
        variant="top"
        src={product.imageUrl}
        style={{
          width: "100px",
          height: "100px",
          objectFit: "cover",
          margin: "auto",
          marginTop: "10px",
        }}
      />
      <Card.Body>
        <Card.Title style={{ fontWeight: "bold" }}>{product.name}</Card.Title>
        <Card.Text>{product.price.toLocaleString()}원</Card.Text>
      </Card.Body>
    </Card>
  );
};

export default ProductCard;
