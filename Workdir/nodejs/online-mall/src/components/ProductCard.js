import React from "react";

//부모 상태를 바꿈
const ProductCard = ({ name, price, onAddToCart }) => {
  return (
    <div
      style={{
        border: "1px solid #ddd",
        padding: "10px",
        margin: "10px",
        backgroundColor: "#fff",
      }}
    >
      <h2>{name}</h2>
      <p>Price: {price}</p>
      <button onClick={onAddToCart}>Add to Cart</button>
    </div>
  );
};

export default ProductCard;
