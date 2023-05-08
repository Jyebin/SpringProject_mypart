//package SWTeam2.vocabulary.SWTeam2.entity;
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.NotNull;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@Entity
//@Setter
//@Getter
//@NoArgsConstructor
//public class WrongVocaEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//    @ManyToOne
//    @NotNull
//    UserEntity user;
//
//    @ManyToOne
//    VocaEntity voca;
//
//    @ManyToOne
//    WrongVocaEntity wrongVocaEntity;
//}
