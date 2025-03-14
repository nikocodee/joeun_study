package com.learn.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.learn.vo.UserVO;

@Mapper
public interface UserMapper {
	List<UserVO> getAllUsers();
	//갯수로 반환
	int createUser(UserVO userVO);
	//@Param 매퍼xml에서 SQL 쿼리 안에 id라는 이름으로 사용할 수 있도록 지정
	UserVO getUserById(@Param("id") Long id);
	int updateUser(UserVO userVO);
	int deleteUser(@Param("id") Long id);
}
