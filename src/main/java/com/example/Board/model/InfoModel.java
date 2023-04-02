package com.example.Board.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class InfoModel { //제보하기
    private int id;
    private String title;
    private String content;
    private LocalDateTime regDate;
}
