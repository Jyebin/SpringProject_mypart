package com.example.Board.repository;

import com.example.Board.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    List<User>findByUsernameAndPassword(String username, String password);

}
