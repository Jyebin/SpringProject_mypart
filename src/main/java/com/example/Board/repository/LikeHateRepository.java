package com.example.Board.repository;

import com.example.Board.entity.InfoEntity;
import com.example.Board.entity.LikeHateEntity;
import com.example.Board.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeHateRepository extends JpaRepository<LikeHateEntity,Integer> { //<엔티티클래스, pk타입>
    LikeHateEntity save(LikeHateEntity likeHateEntity);
    Optional<LikeHateEntity> findByUserAndBoard_Id(UserEntity user, long boardId);
    int countByBoard_IdAndIdentify(Long board_id, int identify);
}