package SWTeam2.vocabulary.SWTeam2.repository;

import SWTeam2.vocabulary.SWTeam2.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,String> {
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByNameAndEmail(String name, String email);
    Optional<UserEntity> findByNameAndEmailAndLevelAndStudyVocaCountAndTier(String name, String email, int level, int studyVocaCount, int tier);
}
