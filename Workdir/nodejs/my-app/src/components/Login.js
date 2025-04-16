import React, { useState } from "react";
import { Container, Row, Col, Form, Button } from "react-bootstrap";

const Login = () => {
  const [username, setUsername] = useState("");

  const handleLogin = () => {
    alert(`Username: ${username}`);
  };

  return (
    <Container className="mt-5">
      <Row className="justify-content-center">
        <Col md={6}>
          <h2 className="text-center">로그인</h2>
          <Form>
            <Form.Group className="mb-3">
              <Form.Label>사용자 이름</Form.Label>
              <Form.Control
                type="text"
                placeholder="Enter your username"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
              />
            </Form.Group>
            <Button variant="success" onClick={handleLogin}>
              로그인
            </Button>
          </Form>
        </Col>
      </Row>
    </Container>
  );
};

export default Login;
