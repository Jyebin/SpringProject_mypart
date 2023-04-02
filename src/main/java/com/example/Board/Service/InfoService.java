package com.example.Board.Service;

import com.example.Board.entity.InfoEntity;
import com.example.Board.exception.AlreadyDeletedException;
import com.example.Board.exception.DuplicateInfoException;
import com.example.Board.exception.InfoNotFoundException;
import com.example.Board.repository.InfoInput;
import com.example.Board.repository.InfoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InfoService {
    @Autowired
    private InfoRepository infoRepository;
    public Page<InfoEntity> getLatestInfoList(int size) {
        return infoRepository.findAll(PageRequest.of(0, size, Sort.Direction.DESC, "regDate"));
    }

    public InfoService(InfoRepository infoRepository) {
        this.infoRepository = infoRepository;
    }
    public Optional<InfoEntity> getInfoById(Long id) {
        return infoRepository.findById(id);
    }
    @Transactional
    public List<InfoInput> getAllInfo() {
        List<InfoInput> infoList = new ArrayList<>();
        List<InfoEntity> allInfo = infoRepository.findAll();
        for(InfoEntity info : allInfo){
            info.addViewCount(); // 조회수 증가
            InfoInput infoInput = new InfoInput(info.getViewCount());
            infoList.add(infoInput);
        }
        return infoList;
    }
    public List<InfoInput> addLike(Long id){ //좋아요 증가
        Optional<InfoEntity>optionalInfo = infoRepository.findById(id);
        if(optionalInfo.isPresent()){
            InfoEntity info = optionalInfo.get();
            info.addLikes(1L);
            infoRepository.save(info);
        }
        return null;
    }
    public List<InfoInput> addHates(Long id){ //싫어요 증가
        Optional<InfoEntity>optionalInfo = infoRepository.findById(id);
        if(optionalInfo.isPresent()){
            InfoEntity info = optionalInfo.get();
            info.addHates(1L);
            infoRepository.save(info);
        }
        return null;
    }
    public void updateInfo(Long id, InfoInput infoInput) {
        InfoEntity info = infoRepository.findById(id)
                .orElseThrow(() -> new InfoNotFoundException("교통 제보하기의 글이 존재하지 않습니다."));
        info.setTitle(infoInput.getTitle());
        info.setContent(infoInput.getContent());
        info.setUpdateDate(LocalDateTime.now());
        infoRepository.save(info);
    }
    @Transactional
    public void addViewCount(Long id) {
        Optional<InfoEntity> optionalInfo = infoRepository.findById(id);
        if (optionalInfo.isPresent()) {
            InfoEntity info = optionalInfo.get();
            info.addViewCount();
            infoRepository.save(info);
        }
    }

    public void deleteInfo(Long id) {
        List<InfoEntity> infoList = infoRepository.findAll();
        if (!infoList.isEmpty()) { //일주일이 지난 글은 자동 삭제
            LocalDateTime oneWeekAgo = LocalDateTime.now().minusWeeks(1);
            for (InfoEntity info : infoList) {
                if (!info.isDeleted() && info.getRegDate().isBefore(oneWeekAgo)) {
                    info.setDeleted(true);
                    info.setDeletedDate(LocalDateTime.now());
                }
            }
            infoRepository.saveAll(infoList);
        }
        InfoEntity info = infoRepository.findById(id)
                .orElseThrow(() -> new InfoNotFoundException("교통 제보하기의 글이 존재하지 않습니다."));
        if(info.isDeleted()) {
            throw new AlreadyDeletedException("이미 삭제된 교통 제보 글입니다.");
        }
        info.setDeleted(true);
        info.setDeletedDate(LocalDateTime.now());
        infoRepository.save(info);
    }

    public void addInfo(InfoInput infoInput) {
        LocalDateTime checkDate = LocalDateTime.now().minusMinutes(1); // 1분 전의 시간
        int infoCount = infoRepository.countByTitleAndContentAndRegDateIsGreaterThanEqual(
                infoInput.getTitle(),
                infoInput.getContent(),
                checkDate
        );
        if (infoCount > 0) {
            throw new DuplicateInfoException("1분 이내에 동일한 내용의 제보 글이 등록되었습니다."); //제목이나 내용이 달라야함. id만 다르면 안됨
        }
        InfoEntity info = InfoEntity.builder()
                .title(infoInput.getTitle())
                .content(infoInput.getContent())
                .viewCount(0)
                .likes(0)
                .hates(0)
                .regDate(LocalDateTime.now())
                .build();
        infoRepository.save(info);
    }
    public InfoEntity increaseViewCount(Long id) {
        Optional<InfoEntity> optionalInfo = infoRepository.findById(id);
        if (optionalInfo.isPresent()) {
            InfoEntity info = optionalInfo.get();
            info.addViewCount();
            return infoRepository.save(info);
        }
        return null;
    }


}
