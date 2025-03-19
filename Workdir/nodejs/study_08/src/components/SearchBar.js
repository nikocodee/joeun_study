import React, { useState } from "react";
import { Form, Button, Row, Col } from "react-bootstrap";

//useState는 값이 변하면 다시 렌더링함
function SearchBar({ onSearch }) {
  const [searchParams, setSearchParams] = useState({
    // 초기값 지정
    writer: "",
    title: "",
    date: "",
    pageSize: "10",
  });

  //evt 이벤트 (에러인지 구분 안가니까 이것도 방법)
  const handleChange = (evt) => {
    //...searchParams 이전값 모두
    setSearchParams({ ...searchParams, [evt.target.name]: evt.target.value });
  };

  const handleSearch = () => {
    //부모에 있는 함수에 매개변수를 전달하는 방식
    onSearch(searchParams);
  };

  return (
    <Form className="mb-3">
      <Row>
        {/* 부트스트랩은 12개니까 컬럼 4개 */}
        <Col md={3}>
          <Form.Control
            type="text"
            placeholder="작성자"
            name="writer"
            value={searchParams.writer}
            onChange={handleChange}
          />
        </Col>
        <Col md={3}>
          <Form.Control
            type="text"
            placeholder="제목"
            name="title"
            value={searchParams.title}
            onChange={handleChange}
          />
        </Col>
        <Col md={3}>
          <Form.Control
            type="date"
            name="date"
            value={searchParams.date}
            onChange={handleChange}
          />
        </Col>
        <Col md={2}>
          <Form.Select
            name="pageSize"
            value={searchParams.pageSize}
            onChange={handleChange}
          >
            <option value="">선택표시</option>
            <option value="10">10개</option>
            <option value="20">20개</option>
            <option value="50">50개</option>
          </Form.Select>
        </Col>
        <Col md={1}>
          <Button variant="primary" onClick={handleSearch}>
            검색
          </Button>
        </Col>
      </Row>
    </Form>
  );
}

export default SearchBar;
