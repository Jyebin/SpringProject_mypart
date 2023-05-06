package SWTeam2.vocabulary.SWTeam2.repository;

import SWTeam2.vocabulary.SWTeam2.entity.VocaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface VocaRepository extends JpaRepository<VocaEntity,Integer> {
    //추가, 삭제, 수정
    VocaEntity save(VocaEntity vocaEntity);
    //조회
    Optional<VocaEntity>findById(int id);
    List<VocaEntity> findAll();
    int countByVocaAndVocamean(String voca, String vocamean);
    List<VocaEntity>findByVocaContainingOrVocamean(String voca, String vocamean);

}
