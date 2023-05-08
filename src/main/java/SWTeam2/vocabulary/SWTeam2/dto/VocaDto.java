package SWTeam2.vocabulary.SWTeam2.dto;

import SWTeam2.vocabulary.SWTeam2.entity.VocaEntity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class VocaDto {
    private Integer id;

    @NotEmpty(message = "단어를 입력해주세요.")
    @Size(min = 1, max =50, message = "영어 단어 글자 수를 1개에서 50개 사이로 입력해주세요.")
    private String voca;

    @NotEmpty(message = "뜻을 입력해주세요.")
    @Size(min=1,max=50, message = "영어 단어 글자 수를 1개에서 50개 사이로 입력해주세요.")
    private String vocamean;

    private Integer label;

    public VocaEntity ToEntity(){
        return new VocaEntity(this.voca, this.vocamean);
    }
}
