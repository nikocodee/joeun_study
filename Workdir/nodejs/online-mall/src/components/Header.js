import React from "react";

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
    </header>
  );
}

export default Header;
