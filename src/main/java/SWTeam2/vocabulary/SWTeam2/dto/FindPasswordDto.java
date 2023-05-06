package SWTeam2.vocabulary.SWTeam2.dto;


import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class FindPasswordDto {
    @NotEmpty
    private String name;
    @NotEmpty
    private String email;
}
