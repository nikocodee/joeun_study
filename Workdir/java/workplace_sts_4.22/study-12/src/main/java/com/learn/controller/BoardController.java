package com.learn.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/board")
public class BoardController {

	// get방식은 query string으로 받음
	@GetMapping
	public Map<String, Object> getBoards(@RequestParam Map<String, Object> params) {
		Map<String, Object> result = new HashMap<>();
		List<Map<String, Object>> content = new ArrayList<>();

		Map<String, Object> map1 = new HashMap<>();
//		map1.put("content", params.get("searchKeyWord"));
//		map1.put("writer", params.get("name"));
//		map1.put("title", params.get("title"));
		map1.put("content", "test content1 ");
		map1.put("writer", "Writer name");
		map1.put("title", "title test ");
		map1.put("id", 1);

		Map<String, Object> map2 = new HashMap<>();
		map2.put("content", "test content2");
		map2.put("writer", "Writer name2");
		map2.put("title", "title test2");
		map2.put("id", 2);

		content.add(map1);
		content.add(map2);
		
		result.put("content", content);
		result.put("totalPages", 10);

		return result;
	}

	@GetMapping("/{id}")
	public Map<String, Object> getBoardById(@PathVariable long id) {
		Map<String, Object> result = new HashMap<>();
		result.put("content", "content text");
		result.put("writer", "writer test");
		result.put("title", "test title" + id);
		result.put("createdAt", "2025/05/05");
		result.put("views", 13);
		result.put("id", id);
		return result;
	}
}

//@RestController
//@RequestMapping("/board")
//public class BoardController {
//   
//   @GetMapping
//   public Map<String,Object> getBoards(@RequestParam Map<String, Object> params) {
//      Map<String,Object> result = new HashMap();
//      List<Map<String, Object>> content = new ArrayList<>();
//      
//      Map<String, Object> map1 = new HashMap<>();
//      try {
//         map1.put("content", Integer.valueOf((String) params.get("searchKeyWord")));
//      } catch (Exception e) {
//         map1.put("content", (String) params.get("searchKeyWord"));
//      }
//      map1.put("content", "test content1");
//      map1.put("writer","test Writer1");
//      map1.put("title","test Title1");
//      map1.put("id",1);
//      
//      Map<String, Object> map2 = new HashMap<>();
//      map2.put("content", "test Writer2");
//      map2.put("writer", "test Writer2");
//      map2.put("title", "test Title2");
//      map2.put("id", 2);
//      
//      content.add(map1);
//      content.add(map2);
//      
//      result.put("content", content);
//      result.put("totalPages", 100);
//      
//      return result;
//   }
//   
//   @GetMapping("/{id}")
//   public Map<String, Object> getBoardById(@PathVariable Long id) {
//      Map<String, Object> result = new HashMap<>();
//      result.put("content", "content text");
//      result.put("writer", "writer kjh");
//      result.put("title", "test title"+id);
//      result.put("createdAt", "2025/05/05"+id);
//      result.put("views",13);
//      result.put("id", id);
//      return result;
//   }
//}

