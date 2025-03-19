import React, { useState, useEffect } from "react";
import { ListGroup } from "react-bootstrap";
import { fetchComments } from "../services/commentService";
import CommentForm from "./CommentForm";

const CommentList = ({ boardId }) => {
  const [comments, setComments] = useState([]);

  useEffect(() => {
    const loadComments = async () => {
      const data = await fetchComments(boardId);
      setComments(data);
    };
    loadComments();
  }, [boardId]);

  const renderComments = (comments, level = 0) => {
    if (level > 2) return null; // 댓글 3단계까지 제한

    return comments.map((comment) => (
      <ListGroup.Item
        key={comment.id}
        style={{ marginLeft: `${level * 20}px` }}
      >
        <strong>{comment.writer}</strong>: {comment.content}
        {comment.replies && renderComments(comment.replies, level + 1)}
      </ListGroup.Item>
    ));
  };

  return (
    <>
      <ListGroup>{renderComments(comments)}</ListGroup>
      <CommentForm boardId={boardId} />
    </>
  );
};

export default CommentList;
