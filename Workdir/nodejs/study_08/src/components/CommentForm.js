import React, { useState } from "react";
import { Form, Button } from "react-bootstrap";
import { addComment } from "../services/commentService";

const CommentForm = ({ userId }) => {
  const [content, setContent] = useState("");
  const currentUser = "작성자123"; // 현재 로그인된 사용자 (예제)

  const handleSubmit = async (e) => {
    e.preventDefault();
    await addComment({ userId, writer: currentUser, content });
    setContent("");
    window.location.reload();
  };

  return (
    <Form onSubmit={handleSubmit} className="mt-3">
      <Form.Control
        as="textarea"
        rows={2}
        value={content}
        onChange={(e) => setContent(e.target.value)}
        placeholder="댓글을 입력하세요..."
      />
      <Button type="submit" variant="primary" className="mt-2">
        댓글 작성
      </Button>
    </Form>
  );
};

export default CommentForm;
