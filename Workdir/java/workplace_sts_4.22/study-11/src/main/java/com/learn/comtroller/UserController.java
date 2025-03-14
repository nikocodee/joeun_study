package com.learn.comtroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learn.service.UserService;
import com.learn.vo.UserVO;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping
	public List<UserVO> getAllUsers(@RequestParam String name){
		List<UserVO> result = null;
		//map운 인터페이스여서 new 못하고 하단에 있는애들을 생성
//		Map<String, Object> elem1 = new HashMap<>();
//		elem1.put("name", name);
//		Map<String, Object> elem2 = new HashMap<>();
//		elem2.put("name", "test2");
//		//지금은 순서대로 보이지만 데이터 많아지면 순서 보장 못함
//		//LinkedList 써야함
//		result = new ArrayList<>();
//		result.add(elem1);
//		result.add(elem2);
		
		result = userService.getAllUsers();
		
		return result;
	}

	//데이터를 객체안에 숨겨서 가지고 감
	@PostMapping
	public String createUser(@RequestBody UserVO userVO){
		String result = null;
//		Map<String, Object> elem = new HashMap<>();
//		elem.put("name", userName);
//		result = elem;
		int cnt = userService.createUser(userVO);
		result = "Result : " + cnt;
		return result;
	}
	
	@GetMapping("/{id}")
	public UserVO getUserById(@PathVariable Long id){
		UserVO result = null;
//		Map<String, Object> elem = new HashMap<>();
//		elem.put("id", id);
//		result = elem;
		result = userService.getUserById(id);
		return result;
	}
	
	@PostMapping("/{id}")
	public String updateUser(@PathVariable Long id, @RequestBody UserVO userVO){
		String result = null;
//		Map<String, Object> elem = new HashMap<>();
//		elem.put("id", id);
//		elem.put("name", name);
//		result = elem;
		int cnt = userService.updateUser(id, userVO);
		result = "Update : " + cnt;
		return result;
	}

	@PostMapping("/delete")
	public String deleteUser(@RequestBody UserVO userVO){
		String result = null;
//		Map<String, Object> elem = new HashMap<>();
//		elem.put("id", id);
//		result = elem;
		int cnt = userService.deleteUser(userVO.getId());
		result = "Delete : " + cnt + "(" + userVO.getId() + ")";
		return result;
	}

}
