import React, { useState } from "react";
import { Table, Form, Button } from "react-bootstrap";
import { createBoard } from "../services/boardService";
import { useNavigate } from "react-router-dom";

const BoardForm = () => {
  const [formData, setFormData] = useState({
    title: "",
    writer: "작성자123", // 예제용, 실제 환경에서는 로그인 사용자 정보 사용
    content: "",
  });
  const [files, setFiles] = useState([]);
  const navigate = useNavigate();

  // 입력 값 변경 핸들러
  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  // 파일 추가 핸들러
  const handleFileChange = (e) => {
    const newFiles = Array.from(e.target.files); //배열로 변환
    const updatedFiles = [...files];

    newFiles.forEach((file) => {
      if (
        //some은 조건을 만족하는 요소가 있으면 true
        !updatedFiles.some((existingFile) => existingFile.name === file.name)
      ) {
        //배열에 추가
        updatedFiles.push(file);
      }
    });
    setFiles(updatedFiles);
    // setFiles([...files, ...e.target.files]);
  };

  // 게시글 등록
  const handleSubmit = async (e) => {
    e.preventDefault();
    const data = new FormData();
    data.append("title", formData.title);
    data.append("writer", formData.writer);
    data.append("content", formData.content);
    files.forEach((file) => data.append("files", file));

    await createBoard(data);
    alert("게시글이 등록되었습니다.");
    navigate("/");
  };

  return (
    <div>
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
                  as="textarea"
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
                  {files.map((file, index) => (
                    <li key={index}>{file.name}</li>
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
    </div>
  );
};

export default BoardForm;
