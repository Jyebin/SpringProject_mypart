package SWTeam2.vocabulary.SWTeam2.service;

import SWTeam2.vocabulary.SWTeam2.entity.VocaEntity;
import SWTeam2.vocabulary.SWTeam2.repository.VocaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service

public class TestService {
    @Autowired
    private VocaRepository vocaRepository;
    public TestService(VocaRepository vocaRepository){
        this.vocaRepository = vocaRepository;
    }
    public List<VocaEntity> getRandom30Words(){ //level test때 사용되는 30개의 단어들
        List<VocaEntity> allWords = vocaRepository.findAll();
        Collections.shuffle(allWords); //DB에 있는 모든 단어들을 섞음
        return allWords.subList(0,30); //섞은 리스트의 앞 30개를 반환
    }
}
