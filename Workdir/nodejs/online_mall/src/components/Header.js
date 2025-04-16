import React from "react";
import { Nav, Navbar } from "react-bootstrap";
import { Link } from "react-router-dom";

function Header() {
  return (
    <Navbar bg="dark" variant="dark" expand="lg">
      <Navbar.Brand as={Link} to="/">
        MyShop
      </Navbar.Brand>
      <Nav className="me-auto">
        <Nav.Link as={Link} to="/category/1">
          의류
        </Nav.Link>
        <Nav.Link as={Link} to="/category/2">
          전자제품
        </Nav.Link>
        <Nav.Link as={Link} to="/category/3">
          식품
        </Nav.Link>
      </Nav>
    </Navbar>
  );
}

export default Header;
