package SWTeam2.vocabulary.SWTeam2.dto;

import SWTeam2.vocabulary.SWTeam2.entity.UserEntity;
import SWTeam2.vocabulary.SWTeam2.entity.VocaEntity;
import SWTeam2.vocabulary.SWTeam2.entity.WrongVocaEntity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@Builder
public class WrongVocaDto {
    private Integer id;

    private String voca;

    private String vocamean;

    private UserEntity user;

    public WrongVocaEntity ToEntity() {
        return new WrongVocaEntity(this.id, this.user, this.voca, this.vocamean);
    }
}


