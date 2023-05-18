package SWTeam2.vocabulary.SWTeam2.repository;

import SWTeam2.vocabulary.SWTeam2.entity.UserVocaEntity;
import SWTeam2.vocabulary.SWTeam2.entity.VocaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserVocaRepository extends JpaRepository<UserVocaEntity,Integer> {
    UserVocaEntity save(UserVocaEntity userVocaEntity);
    //조회
    Optional<UserVocaEntity> findById(int id);
    List<UserVocaEntity> findAll();
    int countByVocaAndVocamean(String voca, String vocamean);
    List<UserVocaEntity>findByVocaContainingOrVocamean(String voca, String vocamean);
    List<UserVocaEntity> findByIdGreaterThanEqual(int id);
}
