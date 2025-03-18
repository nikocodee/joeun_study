import { useEffect, useState } from "react";
import { Table, Button, Form } from "react-bootstrap";
import { fetchBoardDetail, updateBoard } from "../services/boardService";
import { useParams } from "react-router-dom";
import CommentList from "../components/CommentList";

const BoardDetail = () => {
  const { id } = useParams();
  const [board, setBoard] = useState(null);
  const [isEditing, setIsEditing] = useState(false);
  const [editedContent, setEditedContent] = useState("");
  const currentUser = "작성자123"; // 현재 로그인된 사용자 (예제)

  useEffect(() => {
    const loadBoard = async () => {
      const data = await fetchBoardDetail(id);
      setBoard(data);
      setEditedContent(data.content);
    };
    loadBoard();
  }, [id]);

  const handleEdit = () => setIsEditing(true);
  const handleCancel = () => {
    setIsEditing(false);
    setEditedContent(board.content);
  };

  const handleSave = async () => {
    const updatedBoard = { ...board, content: editedContent };
    await updateBoard(id, updatedBoard);
    setBoard(updatedBoard);
    setIsEditing(false);
  };

  if (!board) return <p>Loading...</p>;

  return (
    <div>
      <h2>게시글 상세보기</h2>
      <Table striped bordered>
        <tbody>
          <tr>
            <th>제목</th>
            <td>{board.title}</td>
          </tr>
          <tr>
            <th>작성자</th>
            <td>{board.writer}</td>
          </tr>
          <tr>
            <th>작성일자</th>
            <td>{board.createdAt}</td>
          </tr>
          <tr>
            <th>본문</th>
            <td>
              {isEditing ? (
                <Form.Control
                  as="textarea"
                  rows={5}
                  value={editedContent}
                  onChange={(e) => setEditedContent(e.target.value)}
                />
              ) : (
                board.content
              )}
            </td>
          </tr>
        </tbody>
      </Table>

      {board.writer === currentUser && (
        <>
          {isEditing ? (
            <>
              <Button variant="success" onClick={handleSave}>
                저장
              </Button>
              <Button variant="secondary" onClick={handleCancel}>
                취소
              </Button>
            </>
          ) : (
            <Button variant="primary" onClick={handleEdit}>
              수정
            </Button>
          )}
        </>
      )}

      <h3>댓글</h3>
      <CommentList userId={id} />
    </div>
  );
};

export default BoardDetail;
