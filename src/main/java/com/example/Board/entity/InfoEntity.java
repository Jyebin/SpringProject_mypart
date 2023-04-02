package com.example.Board.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class InfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //id
    @Column
    private String title; //제목
    @Column
    private String content; //내용
    @Column
    private LocalDateTime regDate; //날짜
    @Column
    private LocalDateTime updateDate; //수정날짜
    @Column
    private int likes; //좋아요 수
    public void addLikes(Long likes){
        this.likes++;
    }
    @Column
    private int hates; //싫어요 수
    public void addHates(Long hates){
        this.hates++;
    }

    @Column
    private boolean deleted;  // 삭제 여부

    @Column
    private LocalDateTime deletedDate;  // 삭제 날짜

    private int viewCount;
    public void addViewCount() {
        this.viewCount++;
    }
}
