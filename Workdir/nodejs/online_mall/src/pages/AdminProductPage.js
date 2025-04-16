import axios from "axios";
import React, { useEffect, useState } from "react";
import { Button, Form, Modal, Table } from "react-bootstrap";

const AdminProductPage = () => {
  const [products, setProducts] = useState([]);
  const [editing, setEditing] = useState(null);
  const categories = [
    {
      id: 1,
      name: "의류",
      subcategories: [
        { id: 101, name: "남성" },
        { id: 102, name: "여성" },
      ],
    },
    {
      id: 2,
      name: "전자제품",
      subcategories: [
        { id: 201, name: "노트북" },
        { id: 202, name: "모니터" },
      ],
    },
  ];

  const fetchProducts = () => {
    axios
      .get(`http://localhost:8080/api/products`)
      .then((res) => setProducts(res.data));
  };

  const handleSave = () => {
    if (!editing.name || !editing.price) {
      alert("상품명과 가격은 필수 입력입니다.");
      return;
    }

    const formData = new FormData();
    formData.append("name", editing.name);
    formData.append("price", editing.price);
    formData.append("categoryId", editing.categoryId);
    if (editing.imageFile) formData.append("image", editing.imageFile);

    const apiCall = editing.id
      ? axios.put(`http://localhost:8080/api/products/${editing.id}`, formData)
      : axios.post(`http://localhost:8080/api/products`, formData);

    apiCall.then(() => {
      fetchProducts();
      setEditing(null);
    });
  };

  const handleDelete = (id) => {
    axios
      .delete(`http://localhost:8080/api/products/${id}`)
      .then(fetchProducts);
  };

  useEffect(() => {
    fetchProducts();
  }, []);

  return (
    <div className="p-4">
      <h4>📦 관리자 상품 관리</h4>
      <Button onClick={() => setEditing({})}>상품 등록</Button>
      <Table className="mt-3" bordered hover>
        <thead>
          <tr>
            <th>상품명</th>
            <th>가격</th>
            <th>수정</th>
            <th>삭제</th>
          </tr>
        </thead>
        <tbody>
          {products.map((p) => (
            <tr key={p.id}>
              <td>{p.name}</td>
              <td>{p.price.toLocaleString()}원</td>
              <td>
                <Button onClick={() => setEditing(p)}>수정</Button>
              </td>
              <td>
                <Button variant="danger" onClick={() => handleDelete(p.id)}>
                  삭제
                </Button>
              </td>
            </tr>
          ))}
        </tbody>
      </Table>

      <Modal show={!!editing} onHide={() => setEditing(null)}>
        <Modal.Header closeButton>
          <Modal.Title>{editing?.id ? "상품 수정" : "상품 등록"}</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form.Group className="mb-3">
            <Form.Label>카테고리</Form.Label>
            <Form.Select
              value={editing.categoryId || ""}
              onChange={(e) =>
                setEditing({ ...editing, categoryId: parseInt(e.target.value) })
              }
            >
              <option value="">카테고리 선택</option>
              {categories.flatMap((cat) =>
                cat.subcategories.map((sub) => (
                  <option key={sub.id} value={sub.id}>
                    {cat.name} &gt; {sub.name}
                    {/* {cat.name} > {sub.name} */}
                  </option>
                ))
              )}
            </Form.Select>
          </Form.Group>
          <Form.Group className="mb-3">
            <Form.Label>상품명</Form.Label>
            <Form.Control
              value={editing?.name || ""}
              onChange={(e) => setEditing({ ...editing, name: e.target.value })}
            />
          </Form.Group>
          <Form.Group className="mb-3">
            <Form.Label>가격</Form.Label>
            <Form.Control
              type="number"
              value={editing?.price || ""}
              onChange={(e) =>
                setEditing({ ...editing, price: parseInt(e.target.value) })
              }
            />
          </Form.Group>
          <Form.Group className="mb-3">
            <Form.Label>이미지 URL</Form.Label>
            <Form.Control
              value={editing?.imageUrl || ""}
              onChange={(e) =>
                setEditing({ ...editing, imageUrl: e.target.value })
              }
            />
          </Form.Group>
          <Form.Group className="mb-3">
            <Form.Label>이미지 업로드</Form.Label>
            <Form.Control
              type="file"
              accept="image/*"
              onChange={(e) => {
                const file = e.target.files[0];
                const previewUrl = URL.createObjectURL(file);
                setEditing({ ...editing, imageFile: file, previewUrl });
              }}
            />
            {editing?.previewUrl && (
              <img
                src={editing.previewUrl}
                alt="preview"
                style={{ width: "100px", marginTop: "10px" }}
              />
            )}
          </Form.Group>
          <Button onClick={handleSave}>저장</Button>
        </Modal.Body>
      </Modal>
    </div>
  );
};

export default AdminProductPage;
