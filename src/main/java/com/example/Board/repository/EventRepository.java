//package com.example.Board.repository;
//
//import com.example.Board.entity.EventEntity;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//public interface EventRepository extends JpaRepository<EventEntity, Long> {
//    Optional<EventEntity> findByEventDate(String eventDate);
//    @Query("SELECT e.eventName FROM EventEntity e WHERE LOWER(e.eventContent) LIKE LOWER(CONCAT('%', :keyword, '%'))")
//    List<String> searchByContent(@Param("keyword") String keyword);
//}