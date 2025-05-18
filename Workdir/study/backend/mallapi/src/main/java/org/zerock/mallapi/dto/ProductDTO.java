package org.zerock.mallapi.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    
    private Long pno;

    private String pname;

    private int price;

    private String pdesc;

    private boolean delFlag;

    //새롭게 서버에 보내지는 실제 파일 데이터
    @Builder.Default
    private List<MultipartFile> files = new ArrayList<>();

    //업로드된 파일 이름
    @Builder.Default
    private List<String> uploadFileNames = new ArrayList<>();

}
