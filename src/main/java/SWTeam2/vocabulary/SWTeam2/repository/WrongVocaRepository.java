package SWTeam2.vocabulary.SWTeam2.repository;

import SWTeam2.vocabulary.SWTeam2.entity.UserEntity;
import SWTeam2.vocabulary.SWTeam2.entity.WrongVocaEntity;
import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WrongVocaRepository extends JpaRepository<WrongVocaEntity, Integer> {
    WrongVocaEntity save(WrongVocaEntity wrongVocaEntity);
    List<WrongVocaEntity>findByUser(UserEntity userEntity);
}
