package org.zerock.mallapi.domain;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Entity
@Table(name = "tbl_todo")
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
// 데이터베이스 엔티티(Entity)
// DB 데이블과 직접 연결된 객체
public class Todo { 
    // 기본키 생성
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tno;
    
    private String title;

    private String writer;

    private boolean complete;

    private LocalDate dueDate;

    public void changeTitle(String title){
        this.title = title;
    }

    public void changeComplete(boolean complete){
        this.complete = complete;
    }

    public void changeDueDate(LocalDate dueDate){
        this.dueDate = dueDate;
    }
}
