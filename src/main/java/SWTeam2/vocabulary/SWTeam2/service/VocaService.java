package SWTeam2.vocabulary.SWTeam2.service;

import SWTeam2.vocabulary.SWTeam2.dto.VocaDto;
import SWTeam2.vocabulary.SWTeam2.entity.VocaEntity;
import SWTeam2.vocabulary.SWTeam2.exception.ExceptionMessage;
import SWTeam2.vocabulary.SWTeam2.repository.VocaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VocaService {
    @Autowired
    private VocaRepository vocaRepository;
    public VocaService(VocaRepository vocaRepository){
        this.vocaRepository = vocaRepository;
    }

    //단어 추가
    public void addVoca(VocaDto vocaDto){
        int vocaCount = vocaRepository.countByVocaAndVocamean(vocaDto.getVoca(),vocaDto.getVocamean());
        if(vocaCount>0){
            throw new ExceptionMessage("이미 저장된 단어입니다.");
        }else{
            vocaRepository.save(vocaDto.ToEntity());
        }
    }
    public Optional<VocaEntity> updateVoca(int id, VocaDto vocaDto){
        int presentVoca = vocaRepository.countByVocaAndVocamean(vocaDto.getVoca(),vocaDto.getVocamean());
        if(presentVoca>0){
            throw new ExceptionMessage("수정된 단어가 없습니다.");
        }
        VocaEntity vocaEntity = vocaRepository.findById(id)
                .orElseThrow(()->new ExceptionMessage("해당 단어를 찾을 수 없습니다."));
        vocaEntity.setVoca(vocaDto.getVoca());
        vocaEntity.setVocamean(vocaDto.getVocamean());
        vocaRepository.save(vocaEntity);
        return Optional.of(vocaEntity);
    }
    public List<VocaDto> getAllVoca(String keyword){ //검색 + 모든 단어 list 반환
        List<VocaEntity> all = null;
        if(keyword == null || keyword.isEmpty()){ //입력한 단어가 없으면
            all = vocaRepository.findAll(); //단어 목록 전부를 보여줌
        }else{
            all = vocaRepository.findByVocaContainingOrVocamean(keyword,keyword); //레포에서 일치하는 단어를 찾아 보여줌
        }
        List<VocaDto>DtoList = new ArrayList<>();

        for(VocaEntity vocaEntity : all){
            VocaDto vocaDto = VocaDto.builder()
                    .id(vocaEntity.getId())
                    .voca(vocaEntity.getVoca())
                    .vocamean(vocaEntity.getVocamean())
                    .build();
            DtoList.add(vocaDto);
        }
        return DtoList;
    }
    public Optional<VocaDto>getVocaById(int id){ //id뱔로 단어 조회
        Optional<VocaEntity>vocaEntity = vocaRepository.findById(id);
        Optional<VocaDto>vocaDto = Optional.ofNullable(VocaDto.builder()
                .id(vocaEntity.get().getId())
                .voca(vocaEntity.get().getVoca())
                .vocamean(vocaEntity.get().getVocamean())
                .build());
        return vocaDto;
    }
    public int deleteVoca(int id){
        Optional<VocaEntity>vocaEntity = vocaRepository.findById(id);
        if(!vocaEntity.isPresent()){ //단어가 DB에 존재하지 않는 경우
            throw new ExceptionMessage("삭제할 단어가 없습니다.");
        }else{
            vocaRepository.delete(vocaEntity.get()); //DB에 존재하면 삭제
            return vocaEntity.get().getId();
        }
    }

}
