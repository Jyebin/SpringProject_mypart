package SWTeam2.vocabulary.SWTeam2.entity;

import SWTeam2.vocabulary.SWTeam2.auth.Authority;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String email; //이메일 = 닉네임

    @Column
    private String name;

    @Column
    private String password; //비밀번호

    @Column
    private int level = 0; //1 : 하, 2 : 중, 3 : 상

    @Column
    private int tier = 0; //bronze:0, silver:1, gold:2

    @Column
    private String passwordHint;

    @Column
    private Integer studyVocaCount; //공부한 개수

    public UserEntity(){}

    @Enumerated(EnumType.STRING)
    private Authority authority;
    public void setName(String name){
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Builder
    public UserEntity(String email, String password, String name, Authority authority){
        this.email = email;
        this.password = password;
        this.name = name;
        this.authority = authority;
    }
}