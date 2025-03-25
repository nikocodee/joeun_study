package com.learn.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.learn.vo.BoardVO;

@Mapper
public interface BoardMapper {

	List<BoardVO> getBoards();

	// param은 xml에 넘길 이름, Long id는 java가 주는 이름
	BoardVO getBoardById(@Param("id") Long id);

	int createBoard(BoardVO boardVO);
}
