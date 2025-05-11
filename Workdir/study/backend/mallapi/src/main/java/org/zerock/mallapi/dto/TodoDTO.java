package org.zerock.mallapi.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
// 데이터 전송 객체 (Data Transfer Object)
// DTO는 클라이언트와 데이터를 주고받을 때 사용되는 객체
// @Entity가 없으며 DB와 직접적인 연결이 없고
// 순수한 데이터 저장 역할만 수행
public class TodoDTO {
    private Long tno;

    private String title;

    private String writer;

    private boolean complete;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    
    private LocalDate dueDate;
}