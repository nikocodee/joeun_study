import React, { useState } from "react";
import { useCart } from "../contexts/CartContext";
import { Button, Form, Table } from "react-bootstrap";

function CartPage() {
  const { cart, updateQuantity, removeFromCart } = useCart();

  return (
    <div className="p-4">
      <h4>🛒 장바구니</h4>
      <Table bordered hover>
        <thead>
          <tr>
            <th>상품</th>
            <th>수량</th>
            <th>가격</th>
            <th>합계</th>
            <th>삭제</th>
          </tr>
        </thead>
        <tbody>
          {cart.map((item) => (
            <tr key={item.id}>
              <td>{item.name}</td>
              <td>
                <Form.Control
                  type="number"
                  min="1"
                  value={item.quantity}
                  onChange={(e) =>
                    updateQuantity(item.id, parseInt(e.target.value))
                  }
                />
              </td>
              <td>{item.price.toLocaleString()}원</td>
              <td>{(item.price * item.quantity).toLocaleString()}원</td>
              <td>
                <Button
                  variant="danger"
                  onClick={() => removeFromCart(item.id)}
                >
                  삭제
                </Button>
              </td>
            </tr>
          ))}
        </tbody>
      </Table>
    </div>
  );
}

export default CartPage;
