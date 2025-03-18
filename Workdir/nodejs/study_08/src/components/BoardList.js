import React, { useState, useEffect } from "react";
import { Container, Table } from "react-bootstrap";
import { fetchBoards } from "../services/boardService";
import { useNavigate } from "react-router-dom";
import SearchBar from "./SearchBar";
import PaginationComponent from "./Pagination";

const BoardList = () => {
  const [boards, setBoards] = useState([]);
  const [totalPages, setTotalPages] = useState(1);
  const [currentPage, setCurrentPage] = useState(1);
  const [searchParams, setSearchParams] = useState({});
  const navigate = useNavigate();

  useEffect(() => {
    const loadBoards = async () => {
      const response = await fetchBoards({
        page: currentPage,
        ...searchParams,
      });
      setBoards(response.content);
      setTotalPages(response.totalPages);
    };
    loadBoards();
  }, [currentPage, searchParams]);

  return (
    <Container>
      <SearchBar onSearch={setSearchParams} />
      <Table striped bordered hover>
        <thead>
          <tr>
            <th>#</th>
            <th>제목</th>
            <th>작성자</th>
            <th>작성일자</th>
            <th>조회수</th>
          </tr>
        </thead>
        <tbody>
          {boards.map((board, index) => (
            <tr key={board.id}>
              <td>{index + 1}</td>
              <td
                onClick={() => navigate(`/board/${board.id}`)}
                style={{ cursor: "pointer", color: "blue" }}
              >
                {board.title}
              </td>
              <td>{board.writer}</td>
              <td>{board.createdAt}</td>
              <td>{board.views}</td>
            </tr>
          ))}
        </tbody>
      </Table>
      <PaginationComponent
        totalPages={totalPages}
        currentPage={currentPage}
        onPageChange={setCurrentPage}
      />
    </Container>
  );
};

export default BoardList;
