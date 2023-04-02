package com.example.Board.repository;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Builder
@NoArgsConstructor
//@AllArgsConstructor
@Data

public class NoticeInput {


    private LocalDateTime regDate;
    private LocalDateTime updateDate;

    @NotEmpty(message = "제목은 필수 항목 입니다.")
    @Size(min = 1, max = 100, message = "제목은 1자 이상 100자 이하로 입력해주세요.")
    private String title;

    @NotEmpty(message = "내용은 필수 항목 입니다.")
    @Size(min = 10, max = 100, message = "제목은 10자 이상 100자 이하로 입력해주세요.")
    private String content;
    public NoticeInput(LocalDateTime regDate, LocalDateTime updateDate, String title, String content) {
        this.regDate = regDate;
        this.updateDate = updateDate;
        this.title = title;
        this.content = content;
    }
}
