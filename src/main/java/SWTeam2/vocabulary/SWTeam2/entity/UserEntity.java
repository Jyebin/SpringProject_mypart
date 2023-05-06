package SWTeam2.vocabulary.SWTeam2.entity;

import SWTeam2.vocabulary.SWTeam2.auth.Authority;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
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

    public UserEntity(){}

    @Enumerated(EnumType.STRING)
    private Authority authority;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Builder
    public UserEntity(String email, String password, String name, )
}