package com.example.Board.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class LikeHateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @NotNull
    UserEntity user;

    @ManyToOne
    @NotNull
    InfoEntity board;

    @Column
    int identify; //버튼 누름 여부

}
