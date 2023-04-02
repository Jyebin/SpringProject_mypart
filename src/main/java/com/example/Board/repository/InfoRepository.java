package com.example.Board.repository;

import com.example.Board.entity.InfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface InfoRepository extends JpaRepository<InfoEntity, Long> {

    int countByTitleAndContentAndRegDateIsGreaterThanEqual(String title, String content, LocalDateTime regDate);

    Optional<Object> findByTitle(String title);
    @Query("SELECT i.title FROM InfoEntity i WHERE LOWER(i.content) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<String> searchByContent(@Param("keyword") String keyword);
}

