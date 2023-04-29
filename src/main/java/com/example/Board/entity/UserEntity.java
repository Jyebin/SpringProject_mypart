//package com.example.Board.entity;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//
//@Entity
//@Getter
//public class UserEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    @Column
//    private String email; //이메일 = 닉네임
//    @Column
//    private String password; //비밀번호
//
//    private UserEntity(){}
//
//    @Enumerated(EnumType.STRING)
//    private Authority authority;
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//}