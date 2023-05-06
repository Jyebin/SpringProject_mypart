package SWTeam2.vocabulary.SWTeam2.dto;

import SWTeam2.vocabulary.SWTeam2.auth.Authority;
import lombok.Data;

@Data
public class LoginRequestDto {
    private String email;
    private String password;
    private String name;
    private Authority authority;
}
