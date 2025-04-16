import React from "react";
import { Nav } from "react-bootstrap";
import { Link } from "react-router-dom";

const Sidebar = () => {
  return (
    <div style={{ width: "200px" }}>
      <Nav className="flex-column bg-light p-3">
        <Nav.Link as={Link} to="/category/1">
          👚 의류
        </Nav.Link>
        <Nav.Link as={Link} to="/category/2">
          💻 전자제품
        </Nav.Link>
        <Nav.Link as={Link} to="/category/3">
          🍔 식품
        </Nav.Link>
      </Nav>
    </div>
  );
};

export default Sidebar;
