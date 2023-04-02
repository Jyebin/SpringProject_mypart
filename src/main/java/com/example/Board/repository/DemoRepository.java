//package com.example.Board.repository;
//
//import com.example.Board.entity.DemoEntity;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//public interface DemoRepository extends JpaRepository<DemoEntity, Long> {
//    Optional<DemoEntity> findByDemoDate(String demoDate);
//    @Query("SELECT d.demoName FROM DemoEntity d WHERE LOWER(d.demoContent) LIKE LOWER(CONCAT('%', :keyword, '%'))")
//    List<String> searchByContent(@Param("keyword") String keyword);
//}
