import React, { useState, useEffect } from "react";
import { ListGroup } from "react-bootstrap";
import { fetchComments } from "../services/commentService";
import CommentForm from "./CommentForm";

const CommentList = (userId) => {
  const [comments, setCommnets] = useState([]);

  useEffect(() => {
    const loadCommnets = async () => {
      const data = await fetchComments(userId);
      setCommnets(data);
    };
    loadCommnets();
  }, [userId]);

  const renderComments = (comments, level = 0) => {
    if (level > 2) return null;

    return comments.map((comment) => (
      <ListGroup.Item
        key={comment.writer}
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
      <CommentForm userId={userId} />
    </>
  );
};

export default CommentList;
