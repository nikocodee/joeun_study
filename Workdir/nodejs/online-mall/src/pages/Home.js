import React, { useEffect, useState } from "react";
import ProductCard from "../components/ProductCard";

function Home() {
  // const products = [
  //   { id: 1, name: "Laptop", price: 1200 },
  //   { id: 2, name: "Phone", price: 800 },
  // ];

  const [products, setProducts] = useState([]);
  const [cartCount, setCartCount] = useState(0);

  useEffect(() => {
    const fetchProducts = () => {
      const data = [
        { id: 1, name: "Laptop", price: 1200 },
        { id: 2, name: "Phone", price: 800 },
        { id: 3, name: "Tablet", price: 400 },
      ];
      setProducts(data);
    };
    fetchProducts();
  }, []); // 빈 배열로 마운트 시 한 번만 실행

  function handleAddToCart() {
    setCartCount(cartCount + 1);
  }

  return (
    <div>
      <h1>Welcome to Online Mall</h1>
      <p>Cart Items: {cartCount}</p>
      <div className="product-list">
        {products.map((product) => (
          <ProductCard
            key={product.id}
            name={product.name}
            price={product.price}
            onAddToCart={handleAddToCart}
          />
        ))}
      </div>
    </div>
  );
}

export default Home;
