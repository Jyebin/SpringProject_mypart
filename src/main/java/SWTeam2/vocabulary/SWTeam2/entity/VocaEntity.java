package SWTeam2.vocabulary.SWTeam2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data

public class VocaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; //단어 번호
    @Column
    private String voca; //단어 이름
    @Column
    private String vocamean; //단어 뜻

}
