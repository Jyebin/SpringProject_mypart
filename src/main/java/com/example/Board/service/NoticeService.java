package com.example.Board.service;

import com.example.Board.dto.NoticeDto;
import com.example.Board.entity.NoticeEntity;
import com.example.Board.exception.DuplicateNoticeException;
import com.example.Board.repository.NoticeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
//서비스 : 비즈니스 로직을 담당
//비즈니스 요구사항에 따라 데이터를 처리하고 필요한 데이터를 레포지토리 계층에 요청하여 데이터를 처리
public class NoticeService {
    @Autowired
    private NoticeRepository noticeRepository;

    public NoticeService(NoticeRepository noticeRepository){
        this.noticeRepository = noticeRepository;
    }
    public void addNotice(NoticeDto noticeDto) {
        LocalDateTime checkDate = LocalDateTime.now().minusMinutes(1); //1분 전의 시간
        int noticeCount = noticeRepository.countByTitleAndContentAndRegDateIsGreaterThanEqual(
                noticeDto.getTitle(),
                noticeDto.getContent(),
                checkDate
        );
        if (noticeCount > 0) {
            throw new DuplicateNoticeException("1분 이내에 동일한 내용의 공지 글이 등록되었습니다."); //제목이나 내용이 달라야함. id만 다르면 안됨
        }
        noticeRepository.save(noticeDto.ToEntity());
    }
    public Optional<NoticeDto> updateNotice(NoticeDto noticeDto){
        LocalDateTime updateTime = LocalDateTime.now().minusMinutes(1);
        int updateCount = noticeRepository.countByTitleAndContentAndRegDateIsGreaterThanEqual(
                noticeDto.getTitle(),
                noticeDto.getContent(),
                updateTime
        );
        if(updateCount > 0){
            throw new DuplicateNoticeException("수정된 내용이 없습니다.");
        }
        noticeRepository.save(noticeDto.ToEntity());
        return null;
    }
    public Long deleteId(Long id){
        Optional<NoticeEntity> noticeEntity = noticeRepository.findById(id);
        if(!noticeEntity.isPresent()){
            throw new DuplicateNoticeException("삭제할 내용이 없습니다.");
        }else{
            noticeRepository.delete(noticeEntity.get());
            return noticeEntity.get().getId();
        }
    }

    public List<NoticeDto> listAllNotice(String keyword){
        List<NoticeEntity> all = null;
        if(keyword==null||keyword.isEmpty()){
            all = noticeRepository.findAll();
        }else {
            all = noticeRepository.findByTitleContainingOrContentContaining(keyword,keyword);
        }
        List<NoticeDto> DtoList = new ArrayList<>();

        for(NoticeEntity noticeEntity : all){
            NoticeDto noticeDto = NoticeDto.builder()
                    .id(noticeEntity.getId())
                    .title(noticeEntity.getTitle())
                    .content(noticeEntity.getContent())
                    .regDate(noticeEntity.getRegDate())
                    .updateDate(noticeEntity.getUpdateDate())
                    .build();
            DtoList.add(noticeDto);
        }
        return DtoList;
    }
    public List<NoticeDto> noticeSearch(@Valid NoticeDto noticeDto){ //공지 게시판에서 키워드로 검색. 제목, 내용만 검색 가능
        List<NoticeEntity> all = noticeRepository.findAll();
        List<NoticeDto> DtoList = new ArrayList<>();
        for(NoticeEntity noticeEntity : all){
            NoticeDto Dto = NoticeDto.builder()
                    .title(noticeEntity.getTitle())
                    .content(noticeEntity.getContent())
                    .build();
            DtoList.add(Dto);
        }
        return DtoList;
    }
    public Optional<NoticeDto>getNoticeById(Long id){
        Optional<NoticeEntity> noticeEntity = noticeRepository.findById(id);
        Optional<NoticeDto> noticeDtoId = Optional.ofNullable(NoticeDto.builder()
                .id(noticeEntity.get().getId())
                .title(noticeEntity.get().getTitle())
                .content(noticeEntity.get().getContent())
                .regDate(noticeEntity.get().getRegDate())
                .updateDate(noticeEntity.get().getUpdateDate())
                .increaseCount(noticeEntity.get().getIncreaseCount())
                .deleted(noticeEntity.get().isDeleted())
                .build());
        return noticeDtoId;
    }


}
