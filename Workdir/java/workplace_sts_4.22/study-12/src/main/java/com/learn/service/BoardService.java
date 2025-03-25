package com.learn.service;

import java.io.IOException;
import java.nio.file.Path;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.learn.mapper.BoardMapper;
import com.learn.vo.BoardVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardService {

	@Autowired
	private BoardMapper boardMapper;

	private final Path uploadPath = Path.of(System.getProperty("user.dir"), "uploads");

	// 생성자 주입
//	private final BoardMapper boardMapper;
//	
//	BoardService(BoardMapper boardMapper){
//		this.boardMapper = boardMapper;
//	}

	public List<BoardVO> getBoards() {
		List<BoardVO> result = null; 
		result = boardMapper.getBoards();
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
//
//		for (BoardVO board : result) {
//			Timestamp createdAt = board.getCreatedAt();
//			if (createdAt != null) {
//				String formattedDate = sdf.format(createdAt);
//				board.setFormattedDate(formattedDate);
//				System.out.println("Formatted Date: " + board.getFormattedDate());
//			}
//		}

		return result;
	}

	public BoardVO getBoardById(Long id) {
		BoardVO result = null;
		result = boardMapper.getBoardById(id);
//		if(result != null) {
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
//			Timestamp createAt = result.getCreatedAt();
//			if(createAt != null) {
//				String formattedDate = sdf.format(createAt);
//				result.setFormattedDate(formattedDate);
//			}
//		}
		return result;
	}

	public Map<String, Object> createBoard(BoardVO boardVO, List<MultipartFile> files) {
		Map<String, Object> result = new HashMap<>();
		int createCnt = boardMapper.createBoard(boardVO);
		result.put("result", createCnt);

		boolean cond1 = files != null;
		boolean cond2 = files.size() > 0;
		if (cond1 && cond2) {
			// file은 원소, files는 list
			for (MultipartFile file : files) {
				String randomUUID = UUID.randomUUID().toString();
				String originalFileName = file.getOriginalFilename();
				result.put(originalFileName, randomUUID);
				String Extension = originalFileName.substring(originalFileName.lastIndexOf("."));
				log.debug(randomUUID, "|", originalFileName, "|", Extension);
				log.info("writer", boardVO.getWriter());
				String saveFileName = randomUUID + Extension;
				Path target = uploadPath.resolve(saveFileName);

				try {
					// target에 파일 업로드
					file.transferTo(target);
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
//					e.printStackTrace(); //운영때는 하면 안됨
					log.error("createBoard IllegalStateException raise!!!" + e.getMessage());
				} catch (IOException e) {
					// TODO Auto-generated catch block
//					e.printStackTrace();
					log.error("createBoard IOException raise!!!" + e.getMessage());
				} catch (Exception e) {
//					e.printStackTrace();
					log.error("createBoard Exception raise!!!" + e.getMessage());
				}
			}
		}

		return result;
	}
}
