package com.example.Board.Service;

import com.example.Board.entity.NoticeEntity;
import com.example.Board.exception.AlreadyDeletedException;
import com.example.Board.exception.DuplicateInfoException;
import com.example.Board.exception.DuplicateNoticeException;
import com.example.Board.exception.NoticeNotFoundException;
import com.example.Board.repository.NoticeInput;
import com.example.Board.repository.NoticeRepository;
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
public class NoticeService {

    @Autowired
    private NoticeRepository noticeRepository;
    public Page<NoticeEntity> getLatestNoticeList(int size) {
        return noticeRepository.findAll(PageRequest.of(0, size, Sort.Direction.DESC, "regDate"));
    }
    public NoticeService(NoticeRepository noticeRepository){
        this.noticeRepository = noticeRepository;
    }
    public NoticeEntity saveNotice(NoticeEntity notice) {
        // 공지사항을 등록하기 전에 중복 체크를 수행
        if (noticeRepository.findByTitle(notice.getTitle()).isPresent()) {
            throw new DuplicateInfoException("이미 등록된 제보글입니다.");
        }
        return noticeRepository.save(notice);
    }

    public Optional<NoticeEntity> getNoticeId(Long id) {
        return noticeRepository.findById(id);
    }
    @Transactional
    public List<NoticeInput> getAllNotices() {
        List<NoticeInput> noticeList = new ArrayList<>();
        List<NoticeEntity> allNotices = noticeRepository.findAll(); //데베에서 모든 정보 갖고오기
        for(NoticeEntity notice : allNotices){
            NoticeInput noticeInput = new NoticeInput(notice.getId(), notice.getRegDate(),notice.getUpdateDate(),notice.getTitle(),notice.getContent()); //조회수 증가하려면 ?
            noticeList.add(noticeInput);
        }
        return noticeList;
    }
    @Transactional
    public void increaseViews(Long id) {
        Optional<NoticeEntity> optionalNotice = noticeRepository.findById(id);
        if (optionalNotice.isPresent()) {
            NoticeEntity notice = optionalNotice.get();
            notice.increaseViews();
            noticeRepository.save(notice);
        }
    }
    public void updateNotice(Long id, NoticeInput noticeInput) {

        NoticeEntity notice = noticeRepository.findById(id)
                .orElseThrow(() -> new NoticeNotFoundException("공지사항의 글이 존재하지 않습니다."));
        notice.setTitle(noticeInput.getTitle());
        notice.setContent(noticeInput.getContent());
        notice.setUpdateDate(LocalDateTime.now());
        noticeRepository.save(notice);
    }
    public void deleteNotice(Long id) {
        NoticeEntity notice = noticeRepository.findById(id)
                .orElseThrow(() -> new NoticeNotFoundException("공지사항의 글이 존재하지 않습니다."));

        if(notice.isDeleted()) {
            throw new AlreadyDeletedException("이미 삭제된 공지사항의 글입니다.");
        }
        notice.setDeleted(true);
        notice.setDeletedDate(LocalDateTime.now());
        noticeRepository.save(notice);
    }
    public void deleteNoticeAll() {
        List<NoticeEntity> noticeList = noticeRepository.findAll(); //DB에서 모든 공지사항 갖고옴

        if(!noticeList.isEmpty()) {
            noticeList.stream().forEach(e -> { //목록을 반복하며 실행
                if(!e.isDeleted()) {
                    e.setDeleted(true);
                    e.setDeletedDate(LocalDateTime.now()); //삭제된 날짜를 지금으로
                }
            });
        }
        noticeRepository.saveAll(noticeList); //삭제해서 저장
    }
    public void addNotice(NoticeInput noticeInput) {
        LocalDateTime checkDate = LocalDateTime.now().minusMinutes(1); // 1분 전의 시간
        int noticeCount = noticeRepository.countByTitleAndContentAndRegDateIsGreaterThanEqual(
                noticeInput.getTitle(),
                noticeInput.getContent(),
                checkDate
        );
        if (noticeCount > 0) {
            throw new DuplicateNoticeException("1분 이내에 동일한 내용의 공지 글이 등록되었습니다."); //제목이나 내용이 달라야함. id만 다르면 안됨
        }
        NoticeEntity notice = NoticeEntity.builder()
                .title(noticeInput.getTitle())
                .content(noticeInput.getContent())
                .increaseCount(0)
                .regDate(LocalDateTime.now())
                .build();
        noticeRepository.save(notice);
    }
}
