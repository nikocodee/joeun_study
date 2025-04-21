import React, { useState, useEffect } from "react";
import {
  Container,
  Table,
  Button,
  Form,
  InputGroup,
  Modal,
  Spinner,
  Alert,
} from "react-bootstrap";
import axios from "axios";
import "./AdminProductList.css";

function AdminProductList() {
  // 상품 목록 상태
  const [products, setProducts] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  // 검색 및 필터링 상태
  const [keyword, setKeyword] = useState("");
  const [selectedCategory, setSelectedCategory] = useState("");

  // 모달 상태
  const [showAddModal, setShowAddModal] = useState(false);
  const [showEditModal, setShowEditModal] = useState(false);
  const [showDeleteModal, setShowDeleteModal] = useState(false);
  const [currentProduct, setCurrentProduct] = useState(null);

  // 모달 폼 데이터
  const [formData, setFormData] = useState({
    name: "",
    description: "",
    price: 0,
    stock: 0,
    imageUrl: "",
    category: "기타",
    rating: 0,
    specs: [],
  });

  // API 상태
  const [apiLoading, setApiLoading] = useState(false);
  const [apiError, setApiError] = useState(null);
  const [apiSuccess, setApiSuccess] = useState(null);

  // 페이지 로드시 상품 목록 가져오기
  useEffect(() => {
    fetchProducts();
  }, []);

  // 상품 목록 가져오기
  const fetchProducts = async () => {
    setLoading(true);
    setError(null);

    try {
      const response = await axios.get("http://localhost:8080/api/products");
      setProducts(response.data);
      setLoading(false);
    } catch (err) {
      console.error("상품 목록을 불러오는데 실패했습니다:", err);
      setError(
        "상품 목록을 불러오는데 실패했습니다. 서버 연결을 확인해주세요."
      );
      setLoading(false);
    }
  };

  // 필터링된 상품 목록
  const filteredProducts = products.filter((product) => {
    const matchesSearch =
      product.name.toLowerCase().includes(keyword.toLowerCase()) ||
      product.description.toLowerCase().includes(keyword.toLowerCase());
    const matchesCategory =
      selectedCategory === "" || product.category === selectedCategory;

    return matchesSearch && matchesCategory;
  });

  // 카테고리 옵션
  const categories = ["패션", "가전", "식품", "생활", "기타"];

  // 모달 관련 핸들러
  const handleAddModalShow = () => {
    setFormData({
      name: "",
      description: "",
      price: 0,
      stock: 0,
      imageUrl: "https://picsum.photos/500",
      category: "기타",
      rating: 0,
      specs: [{ key: "", value: "" }],
    });
    setShowAddModal(true);
  };

  const handleEditModalShow = (product) => {
    setCurrentProduct(product);
    setFormData({
      ...product,
      // specs:
      //   product.specs.length > 0 ? product.specs : [{ key: "", value: "" }],
    });
    setShowEditModal(true);
  };

  const handleDeleteModalShow = (product) => {
    setCurrentProduct(product);
    setShowDeleteModal(true);
  };

  const handleModalClose = () => {
    setShowAddModal(false);
    setShowEditModal(false);
    setShowDeleteModal(false);
    setApiError(null);
    setApiSuccess(null);
  };

  // 폼 입력 처리
  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]:
        name === "price" || name === "stock" || name === "rating"
          ? Number(value)
          : value,
    });
  };

  // 스펙 입력 처리
  const handleSpecChange = (index, field, value) => {
    const newSpecs = [...formData.specs];
    newSpecs[index] = { ...newSpecs[index], [field]: value };
    setFormData({ ...formData, specs: newSpecs });
  };

  // 스펙 추가
  const handleAddSpec = () => {
    setFormData({
      ...formData,
      specs: [...formData.specs, { key: "", value: "" }],
    });
  };

  // 스펙 삭제
  const handleRemoveSpec = (index) => {
    const newSpecs = formData.specs.filter((_, i) => i !== index);
    setFormData({
      ...formData,
      specs: newSpecs.length > 0 ? newSpecs : [{ key: "", value: "" }],
    });
  };

  // 상품 추가
  const handleAddProduct = async () => {
    setApiLoading(true);
    setApiError(null);
    setApiSuccess(null);

    try {
      // 유효성 검사
      if (!formData.name || !formData.description || formData.price <= 0) {
        throw new Error("필수 입력값을 모두 입력해주세요.");
      }

      // 불필요한 빈 스펙 제거
      // const filteredSpecs = formData.specs.filter(
      //   (spec) => spec.key && spec.value
      // );

      const productData = {
        ...formData,
        // specs: filteredSpecs,
        // active: true,
      };

      const response = await axios.post(
        "http://localhost:8080/api/products",
        JSON.stringify(productData),
        {
          headers: {
            "Content-Type": "application/json",
          },
        }
      );

      setApiSuccess("상품이 성공적으로 추가되었습니다.");
      setApiLoading(false);

      // 1.5초 후 모달 닫기 및 목록 새로고침
      setTimeout(() => {
        handleModalClose();
        fetchProducts();
      }, 1500);
    } catch (err) {
      console.error("상품 추가에 실패했습니다:", err);
      setApiError(
        err.response?.data?.message ||
          err.message ||
          "상품 추가에 실패했습니다."
      );
      setApiLoading(false);
    }
  };

  // 상품 수정
  const handleUpdateProduct = async () => {
    setApiLoading(true);
    setApiError(null);
    setApiSuccess(null);

    try {
      // 유효성 검사
      if (!formData.name || !formData.description || formData.price <= 0) {
        throw new Error("필수 입력값을 모두 입력해주세요.");
      }

      // 불필요한 빈 스펙 제거
      // const filteredSpecs = formData.specs.filter(
      //   (spec) => spec.key && spec.value
      // );

      const productData = {
        ...formData,
        // specs: filteredSpecs,
      };

      const response = await axios.post(
        `http://localhost:8080/api/products/update`,
        JSON.stringify(productData),
        {
          headers: {
            "Content-Type": "application/json",
          },
        }
      );

      setApiSuccess("상품이 성공적으로 수정되었습니다.");
      setApiLoading(false);

      // 1.5초 후 모달 닫기 및 목록 새로고침
      setTimeout(() => {
        handleModalClose();
        fetchProducts();
      }, 1500);
    } catch (err) {
      console.error("상품 수정에 실패했습니다:", err);
      setApiError(
        err.response?.data?.message ||
          err.message ||
          "상품 수정에 실패했습니다."
      );
      setApiLoading(false);
    }
  };

  // 상품 삭제
  const handleDeleteProduct = async () => {
    setApiLoading(true);
    setApiError(null);
    setApiSuccess(null);

    try {
      await axios.get(
        `http://localhost:8080/api/products/delete/${currentProduct.id}`
      );

      setApiSuccess("상품이 성공적으로 삭제되었습니다.");
      setApiLoading(false);

      // 1.5초 후 모달 닫기 및 목록 새로고침
      setTimeout(() => {
        handleModalClose();
        fetchProducts();
      }, 1500);
    } catch (err) {
      console.error("상품 삭제에 실패했습니다:", err);
      setApiError(
        err.response?.data?.message ||
          err.message ||
          "상품 삭제에 실패했습니다."
      );
      setApiLoading(false);
    }
  };

  // 로딩 UI
  if (loading) {
    return (
      <Container className="text-center my-5">
        <Spinner animation="border" variant="primary" />
        <p className="mt-3">상품 정보를 불러오는 중입니다...</p>
      </Container>
    );
  }

  return (
    <Container className="admin-product-list py-4">
      <h2 className="mb-4">상품 관리</h2>

      {error && <Alert variant="danger">{error}</Alert>}

      {/* 검색 및 필터링 */}
      <div className="d-flex justify-content-between mb-4 flex-wrap">
        <div className="d-flex align-items-center mb-3 mb-md-0">
          <Form.Group controlId="categoryFilter" className="me-3">
            <Form.Select
              value={selectedCategory}
              onChange={(e) => setSelectedCategory(e.target.value)}
            >
              <option value="">모든 카테고리</option>
              {categories.map((category) => (
                <option key={category} value={category}>
                  {category}
                </option>
              ))}
            </Form.Select>
          </Form.Group>

          <InputGroup style={{ width: "250px" }}>
            <Form.Control
              placeholder="상품명 검색..."
              value={keyword}
              onChange={(e) => setKeyword(e.target.value)}
            />
            {keyword && (
              <Button
                variant="outline-secondary"
                onClick={() => setKeyword("")}
              >
                X
              </Button>
            )}
          </InputGroup>
        </div>

        <Button variant="primary" onClick={handleAddModalShow}>
          상품 추가
        </Button>
      </div>

      {/* 상품 목록 테이블 */}
      <div className="table-responsive">
        <Table striped bordered hover>
          <thead>
            <tr>
              <th width="5%">ID</th>
              <th width="15%">이미지</th>
              <th width="20%">상품명</th>
              <th width="10%">가격</th>
              <th width="10%">재고</th>
              <th width="10%">카테고리</th>
              <th width="10%">상태</th>
              <th width="20%">관리</th>
            </tr>
          </thead>
          <tbody>
            {filteredProducts.length > 0 ? (
              filteredProducts.map((product) => (
                <tr key={product.id}>
                  <td>{product.id}</td>
                  <td>
                    <img
                      src={product.imageUrl}
                      alt={product.name}
                      className="admin-product-thumbnail"
                    />
                  </td>
                  <td>{product.name}</td>
                  <td>{product.price.toLocaleString()}원</td>
                  <td>{product.stock}개</td>
                  <td>{product.category}</td>
                  <td>
                    <span
                      className={`status-badge ${
                        product.useYn === "Y" ? "active" : "inactive"
                      }`}
                    >
                      {product.useYn === "Y" ? "판매중" : "판매중지"}
                    </span>
                  </td>
                  <td>
                    <Button
                      variant="outline-primary"
                      size="sm"
                      className="me-2"
                      onClick={() => handleEditModalShow(product)}
                    >
                      수정
                    </Button>
                    <Button
                      variant="outline-danger"
                      size="sm"
                      onClick={() => handleDeleteModalShow(product)}
                    >
                      삭제
                    </Button>
                  </td>
                </tr>
              ))
            ) : (
              <tr>
                <td colSpan="8" className="text-center py-3">
                  상품이 없거나 검색 결과가 없습니다.
                </td>
              </tr>
            )}
          </tbody>
        </Table>
      </div>

      {/* 추가 모달 */}
      <Modal show={showAddModal} onHide={handleModalClose} size="lg">
        <Modal.Header closeButton>
          <Modal.Title>상품 추가</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          {apiError && <Alert variant="danger">{apiError}</Alert>}
          {apiSuccess && <Alert variant="success">{apiSuccess}</Alert>}

          <Form>
            <Form.Group className="mb-3">
              <Form.Label>상품명 *</Form.Label>
              <Form.Control
                type="text"
                name="name"
                value={formData.name}
                onChange={handleInputChange}
                required
              />
            </Form.Group>

            <Form.Group className="mb-3">
              <Form.Label>상품 설명 *</Form.Label>
              <Form.Control
                as="textarea"
                rows={3}
                name="description"
                value={formData.description}
                onChange={handleInputChange}
                required
              />
            </Form.Group>

            <div className="row">
              <div className="col-md-6">
                <Form.Group className="mb-3">
                  <Form.Label>가격 (원) *</Form.Label>
                  <Form.Control
                    type="number"
                    name="price"
                    min="0"
                    value={formData.price}
                    onChange={handleInputChange}
                    required
                  />
                </Form.Group>
              </div>
              <div className="col-md-6">
                <Form.Group className="mb-3">
                  <Form.Label>재고 수량 *</Form.Label>
                  <Form.Control
                    type="number"
                    name="stock"
                    min="0"
                    value={formData.stock}
                    onChange={handleInputChange}
                    required
                  />
                </Form.Group>
              </div>
            </div>

            <Form.Group className="mb-3">
              <Form.Label>이미지 URL</Form.Label>
              <Form.Control
                type="text"
                name="imageUrl"
                value={formData.imageUrl}
                onChange={handleInputChange}
              />
            </Form.Group>

            <div className="row">
              <div className="col-md-6">
                <Form.Group className="mb-3">
                  <Form.Label>카테고리</Form.Label>
                  <Form.Select
                    name="category"
                    value={formData.category}
                    onChange={handleInputChange}
                  >
                    {categories.map((category) => (
                      <option key={category} value={category}>
                        {category}
                      </option>
                    ))}
                  </Form.Select>
                </Form.Group>
              </div>
              <div className="col-md-6">
                <Form.Group className="mb-3">
                  <Form.Label>평점 (0-5)</Form.Label>
                  <Form.Control
                    type="number"
                    name="rating"
                    min="0"
                    max="5"
                    step="0.1"
                    value={formData.rating}
                    onChange={handleInputChange}
                  />
                </Form.Group>
              </div>
            </div>

            {/* <Form.Group className="mb-3">
              <Form.Label>상품 스펙</Form.Label>
              {formData.specs.map((spec, index) => (
                <div key={index} className="d-flex mb-2">
                  <Form.Control
                    className="me-2"
                    placeholder="항목"
                    value={spec.key}
                    onChange={(e) =>
                      handleSpecChange(index, "key", e.target.value)
                    }
                  />
                  <Form.Control
                    placeholder="값"
                    value={spec.value}
                    onChange={(e) =>
                      handleSpecChange(index, "value", e.target.value)
                    }
                  />
                  <Button
                    variant="outline-danger"
                    className="ms-2"
                    onClick={() => handleRemoveSpec(index)}
                  >
                    -
                  </Button>
                </div>
              ))}
              <Button
                variant="outline-secondary"
                size="sm"
                onClick={handleAddSpec}
              >
                스펙 추가
              </Button>
            </Form.Group> */}
          </Form>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleModalClose}>
            취소
          </Button>
          <Button
            variant="primary"
            onClick={handleAddProduct}
            disabled={apiLoading}
          >
            {apiLoading ? (
              <>
                <Spinner
                  as="span"
                  animation="border"
                  size="sm"
                  role="status"
                  aria-hidden="true"
                  className="me-2"
                />
                처리 중...
              </>
            ) : (
              "추가하기"
            )}
          </Button>
        </Modal.Footer>
      </Modal>

      {/* 수정 모달 */}
      <Modal show={showEditModal} onHide={handleModalClose} size="lg">
        <Modal.Header closeButton>
          <Modal.Title>상품 수정</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          {apiError && <Alert variant="danger">{apiError}</Alert>}
          {apiSuccess && <Alert variant="success">{apiSuccess}</Alert>}

          <Form>
            <Form.Group className="mb-3">
              <Form.Label>상품명 *</Form.Label>
              <Form.Control
                type="text"
                name="name"
                value={formData.name}
                onChange={handleInputChange}
                required
              />
            </Form.Group>

            <Form.Group className="mb-3">
              <Form.Label>상품 설명 *</Form.Label>
              <Form.Control
                as="textarea"
                rows={3}
                name="description"
                value={formData.description}
                onChange={handleInputChange}
                required
              />
            </Form.Group>

            <div className="row">
              <div className="col-md-6">
                <Form.Group className="mb-3">
                  <Form.Label>가격 (원) *</Form.Label>
                  <Form.Control
                    type="number"
                    name="price"
                    min="0"
                    value={formData.price}
                    onChange={handleInputChange}
                    required
                  />
                </Form.Group>
              </div>
              <div className="col-md-6">
                <Form.Group className="mb-3">
                  <Form.Label>재고 수량 *</Form.Label>
                  <Form.Control
                    type="number"
                    name="stock"
                    min="0"
                    value={formData.stock}
                    onChange={handleInputChange}
                    required
                  />
                </Form.Group>
              </div>
            </div>

            <Form.Group className="mb-3">
              <Form.Label>이미지 URL</Form.Label>
              <Form.Control
                type="text"
                name="imageUrl"
                value={formData.imageUrl}
                onChange={handleInputChange}
              />
            </Form.Group>

            <div className="row">
              <div className="col-md-6">
                <Form.Group className="mb-3">
                  <Form.Label>카테고리</Form.Label>
                  <Form.Select
                    name="category"
                    value={formData.category}
                    onChange={handleInputChange}
                  >
                    {categories.map((category) => (
                      <option key={category} value={category}>
                        {category}
                      </option>
                    ))}
                  </Form.Select>
                </Form.Group>
              </div>
              <div className="col-md-6">
                <Form.Group className="mb-3">
                  <Form.Label>평점 (0-5)</Form.Label>
                  <Form.Control
                    type="number"
                    name="rating"
                    min="0"
                    max="5"
                    step="0.1"
                    value={formData.rating}
                    onChange={handleInputChange}
                  />
                </Form.Group>
              </div>
            </div>

            {/* <Form.Group className="mb-3">
              <Form.Label>판매 상태</Form.Label>
              <Form.Check
                type="switch"
                id="active-switch"
                label={formData.useYn === "Y" ? "판매중" : "판매중지"}
                checked={formData.useYn === "Y"}
                onChange={(e) =>
                  setFormData({ ...formData, useYn: e.target.checked })
                }
              />
            </Form.Group> */}

            {/* <Form.Group className="mb-3">
              <Form.Label>상품 스펙</Form.Label>
              {formData.specs.map((spec, index) => (
                <div key={index} className="d-flex mb-2">
                  <Form.Control
                    className="me-2"
                    placeholder="항목"
                    value={spec.key}
                    onChange={(e) =>
                      handleSpecChange(index, "key", e.target.value)
                    }
                  />
                  <Form.Control
                    placeholder="값"
                    value={spec.value}
                    onChange={(e) =>
                      handleSpecChange(index, "value", e.target.value)
                    }
                  />
                  <Button
                    variant="outline-danger"
                    className="ms-2"
                    onClick={() => handleRemoveSpec(index)}
                  >
                    -
                  </Button>
                </div>
              ))}
              <Button
                variant="outline-secondary"
                size="sm"
                onClick={handleAddSpec}
              >
                스펙 추가
              </Button>
            </Form.Group> */}
          </Form>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleModalClose}>
            취소
          </Button>
          <Button
            variant="primary"
            onClick={handleUpdateProduct}
            disabled={apiLoading}
          >
            {apiLoading ? (
              <>
                <Spinner
                  as="span"
                  animation="border"
                  size="sm"
                  role="status"
                  aria-hidden="true"
                  className="me-2"
                />
                처리 중...
              </>
            ) : (
              "수정하기"
            )}
          </Button>
        </Modal.Footer>
      </Modal>

      {/* 삭제 확인 모달 */}
      <Modal show={showDeleteModal} onHide={handleModalClose}>
        <Modal.Header closeButton>
          <Modal.Title>상품 삭제</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          {apiError && <Alert variant="danger">{apiError}</Alert>}
          {apiSuccess && <Alert variant="success">{apiSuccess}</Alert>}

          {currentProduct && (
            <div>
              <p>다음 상품을 삭제하시겠습니까?</p>
              <p>
                <strong>상품명:</strong> {currentProduct.name}
              </p>
              <p>
                <strong>가격:</strong> {currentProduct.price.toLocaleString()}원
              </p>
              <p className="text-danger">이 작업은 되돌릴 수 없습니다.</p>
            </div>
          )}
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleModalClose}>
            취소
          </Button>
          <Button
            variant="danger"
            onClick={handleDeleteProduct}
            disabled={apiLoading}
          >
            {apiLoading ? (
              <>
                <Spinner
                  as="span"
                  animation="border"
                  size="sm"
                  role="status"
                  aria-hidden="true"
                  className="me-2"
                />
                처리 중...
              </>
            ) : (
              "삭제하기"
            )}
          </Button>
        </Modal.Footer>
      </Modal>
    </Container>
  );
}

export default AdminProductList;
