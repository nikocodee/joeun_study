import React, { useState } from "react";
import { Container, Table, Form, Button } from "react-bootstrap";
import { createBoard } from "../services/boardService";
import { useNavigate } from "react-router-dom";

const BoardForm = () => {
  const [formData, setFormData] = useState({
    title: "",
    writer: "작성자123", // 예제용, 실제 환경에서는 로그인 사용자 정보 사용
    content: "",
  });
  const [files, setFiles] = useState([]);
  const [fileNames, setFileNames] = useState([]);
  const navigate = useNavigate();

  // 입력 값 변경 핸들러
  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  // 파일 추가 핸들러
  const handleFileChange = (e) => {
    const newFiles = [...files, ...e.target.files];
    setFiles(newFiles);
    const arrFileName = newFiles.map((file) => file.name);
    const uniqueFileNames = new Set(arrFileName);
    setFileNames([...uniqueFileNames]);
    console.log("arrFileName", arrFileName);
    // const set = new Set([...files, ...e.target.files]);
    // console.log(set);
    // setFiles([...set]);
  };

  // 게시글 등록
  const handleSubmit = async (e) => {
    e.preventDefault();
    const data = new FormData();
    data.append("title", formData.title);
    data.append("writer", formData.writer);
    data.append("content", formData.content);
    files.forEach((file) => data.append("file", file));

    await createBoard(data);
    alert("게시글이 등록되었습니다.");
    navigate("/");
  };

  return (
    <Container>
      <h2>게시글 등록</h2>
      <Form onSubmit={handleSubmit}>
        <Table striped bordered>
          <tbody>
            <tr>
              <th>제목</th>
              <td>
                <Form.Control
                  type="text"
                  name="title"
                  value={formData.title}
                  onChange={handleChange}
                  required
                />
              </td>
            </tr>
            <tr>
              <th>작성자</th>
              <td>
                <Form.Control
                  type="text"
                  name="writer"
                  value={formData.writer}
                  readOnly
                />
              </td>
            </tr>
            <tr>
              <th>본문</th>
              <td>
                <Form.Control
                  type="textarea"
                  rows={5}
                  name="content"
                  value={formData.content}
                  onChange={handleChange}
                  required
                />
              </td>
            </tr>
            <tr>
              <th>첨부파일</th>
              <td>
                <Form.Control
                  type="file"
                  multiple
                  onChange={handleFileChange}
                />
                <ul>
                  {fileNames.map((file, index) => (
                    <li key={index}>{file}</li>
                  ))}
                </ul>
              </td>
            </tr>
          </tbody>
        </Table>
        <Button type="submit" variant="primary">
          등록
        </Button>
      </Form>
    </Container>
  );
};

export default BoardForm;
