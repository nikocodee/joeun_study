import React from "react";
import { Pagination } from "react-bootstrap";

const PaginationComponent = ({ totalPages, currentPage, onPageChange }) => {
  const pageItems = [];
  const maxPages = 10; // 한 번에 표시할 페이지 개수

  let startPage = Math.floor((currentPage - 1) / maxPages) * maxPages + 1;
  let endPage = Math.min(startPage + maxPages - 1, totalPages);

  if (currentPage > 1) {
    pageItems.push(
      <Pagination.First key="first" onClick={() => onPageChange(1)} />
    );
    pageItems.push(
      <Pagination.Prev
        key="prev"
        onClick={() => onPageChange(currentPage - 1)}
      />
    );
  }

  for (let i = startPage; i <= endPage; i++) {
    pageItems.push(
      <Pagination.Item
        key={i}
        active={i === currentPage}
        onClick={() => onPageChange(i)}
      >
        {i}
      </Pagination.Item>
    );
  }

  if (currentPage < totalPages) {
    pageItems.push(
      <Pagination.Next
        key="next"
        onClick={() => onPageChange(currentPage + 1)}
      />
    );
    pageItems.push(
      <Pagination.Last key="last" onClick={() => onPageChange(totalPages)} />
    );
  }

  return <Pagination>{pageItems}</Pagination>;
};

export default PaginationComponent;
