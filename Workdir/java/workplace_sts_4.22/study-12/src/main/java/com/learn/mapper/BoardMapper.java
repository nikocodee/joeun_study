package com.learn.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.learn.vo.BoardVO;

@Mapper
public interface BoardMapper {
	List<BoardVO> getBoards();
	BoardVO getBoardById();
	int createBoard();
}
