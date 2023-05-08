package SWTeam2.vocabulary.SWTeam2.service;

import SWTeam2.vocabulary.SWTeam2.dto.VocaDto;
import SWTeam2.vocabulary.SWTeam2.entity.UserEntity;
import SWTeam2.vocabulary.SWTeam2.entity.VocaEntity;
import SWTeam2.vocabulary.SWTeam2.repository.UserRepository;
import SWTeam2.vocabulary.SWTeam2.repository.VocaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service

public class TestService {
    @Autowired
    private VocaRepository vocaRepository;
    private UserRepository userRepository;

    public TestService(VocaRepository vocaRepository){
        this.vocaRepository = vocaRepository;
    }
    public List<VocaDto> makeProblem (List<VocaDto> list, int cnt){ //문제를 만드는 로직. 유틸함수
        int wrongcnt = (int)(Math.random()*(cnt/3)+3);
        ArrayList<Integer> wronglist = new ArrayList<>();
        for(int i=0;i<wrongcnt;i++){
            int flag = 0;
            while(true){
                int rand = (int)(Math.random()*(cnt-1));
                if(wronglist.contains(rand)){
                    continue;
                }else{
                    wronglist.add(rand);
                    flag ++;
                }
                if(flag == 2){
                    break;
                }
            }
            int start = wronglist.get(wronglist.size()-2);
            int end = wronglist.get(wronglist.size()-1);

            String temp = list.get(start).getVocamean();
            list.get(start).setVocamean(list.get(end).getVocamean());
            list.get(end).setVocamean(temp);

            list.get(start).setLabel(0);
            list.get(end).setLabel(0);
        }
        return list;
    }

    public List<VocaDto>getTestWords(int wordcnt){ //30개 테스트 보는거
        List<VocaEntity> allWords = vocaRepository.findAll();
        Collections.shuffle(allWords); //DB에 있는 모든 단어들을 섞음
        allWords = allWords.subList(0,wordcnt);
        List<VocaDto>DtoList = new ArrayList<>();
        for(VocaEntity vocaEntity : allWords){
            VocaDto vocaDto = VocaDto.builder()
                    .id(vocaEntity.getId())
                    .voca(vocaEntity.getVoca())
                    .vocamean(vocaEntity.getVocamean())
                    .label(1)
                    .build();
            DtoList.add(vocaDto);
        }
        return makeProblem(DtoList,wordcnt); //섞은 리스트의 앞 wordcnt개를 반환
    }

    public void updateUserLevel(UserEntity userEntity){
        userRepository.save(userEntity);
    }


    //모든 단어장
    public List<VocaDto>getStudyWords(int wordcnt){
        List<VocaEntity> allWords = vocaRepository.findAll();
        Collections.shuffle(allWords); //DB에 있는 모든 단어들을 섞음
        allWords = allWords.subList(0,wordcnt);

        List<VocaDto>DtoList = new ArrayList<>();
        for(VocaEntity vocaEntity : allWords){
            VocaDto noticeDto = VocaDto.builder()
                    .id(vocaEntity.getId())
                    .voca(vocaEntity.getVoca())
                    .vocamean(vocaEntity.getVocamean())
                    .label(1)
                    .build();
            DtoList.add(noticeDto);
        }
        return DtoList; //섞은 리스트의 앞 wordcnt개를 반환
    }
    //섞은 test
    public List<VocaDto>getStudyTestWords(List<String> wordlist,int wordcnt){
        List<VocaDto>DtoList = new ArrayList<>();
        for(String vocaId : wordlist){
            Optional<VocaEntity> vocaEntity = vocaRepository.findById(Integer.parseInt(vocaId));
            VocaDto vocaDto = VocaDto.builder()
                    .id(vocaEntity.get().getId())
                    .voca(vocaEntity.get().getVoca())
                    .vocamean(vocaEntity.get().getVocamean())
                    .label(1)
                    .build();
            DtoList.add(vocaDto);
        }
        Collections.shuffle(DtoList);
        return makeProblem(DtoList, wordcnt); //섞은 리스트의 앞 wordcnt개를 반환
    }


}
