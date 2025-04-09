import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";

function Header() {
  return (
    <header
      // {{}} JSX에서 자바스크립트 표현식을 사용 괄호, 객체를 표현하는 괄호
      style={{
        backgroundColor: "#333",
        color: "#fff",
        padding: "10px",
        textAlign: "center",
      }}
    >
      <h1>Online Mall</h1>
      <nav>
        <Link to="/" style={{ color: "#fff", margin: "0 10px" }}>
          Home
        </Link>
        <Link to="/products" style={{ color: "#fff", margin: "0 10px" }}>
          Products
        </Link>
        <Link to="/cart" style={{ color: "#fff", margin: "0 10px" }}>
          Cart
        </Link>
      </nav>
    </header>
  );
}

export default Header;
