package com.example.Board.repository;

import com.example.Board.entity.NoticeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface NoticeRepository extends JpaRepository<NoticeEntity, Long> {

    int countByTitleAndContentAndRegDateIsGreaterThanEqual(String title, String content, LocalDateTime regDate);

    Optional<Object> findByTitle(String title);
    @Query("SELECT n.title FROM NoticeEntity n WHERE LOWER(n.content) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<String> searchByContent(@Param("keyword") String keyword);
}
