package com.example.Board.repository;

import com.example.Board.entity.InfoEntity;
import com.example.Board.entity.NoticeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface InfoRepository extends JpaRepository<InfoEntity, Long> {
    //공지사항 등록
    InfoEntity save(InfoEntity infoEntity);
    //공지사항 조회
    Optional<InfoEntity>findById(Long id);
    List<InfoEntity> findAll();
    List<InfoEntity>findByTitleContainingOrContentContaining(String title, String content);
    int countByTitleAndContentAndRegDateIsGreaterThanEqual(String title, String content, LocalDateTime regDate);


}
