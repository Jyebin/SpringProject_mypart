package com.example.Board.controller;

import com.example.Board.Service.NoticeService;
import com.example.Board.entity.NoticeEntity;
import com.example.Board.exception.*;
import com.example.Board.repository.NoticeInput;
import com.example.Board.repository.NoticeRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ApiNoticeController {
    NoticeRepository noticeRepository;
    @Autowired
    private NoticeService noticeService;
    @GetMapping("/api/notice") //공지사항 글 전체 조회
    public List<NoticeInput> getAllNotices(){
        return noticeService.getAllNotices();
    }
    @GetMapping("/api/notice/{id}") //공지사항 글 id별 조회
    public ResponseEntity<?> getNotice(@PathVariable(value = "id") Long id) {
        Optional<NoticeEntity> optionalNotice = noticeService.getNoticeId(id);
        if (optionalNotice.isPresent()) {
            noticeService.increaseViews(id);
            return ResponseEntity.ok(optionalNotice.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ExceptionHandler(NoticeNotFoundException.class)
    public ResponseEntity<String> handlerNoticeNotFoundException(NoticeNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/api/notice/{id}") //공지사항 수정
    public void updateNotice(@PathVariable Long id, @RequestBody NoticeInput noticeInput) {
        noticeService.updateNotice(id, noticeInput);
    }

    @ExceptionHandler(AlreadyDeletedException.class)
    public ResponseEntity<String> handlerAlreadyDeletedException(AlreadyDeletedException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.OK);
    }

    @DeleteMapping("/api/notice/{id}") //공지사항 한개씩 삭제
    public void deleteNotice(@PathVariable Long id) {
        noticeService.deleteNotice(id);
    }

    @DeleteMapping("/api/notice/all") //모든 공지사항 삭제
    public void deleteNoticeAll() {
        noticeService.deleteNoticeAll();
    }

    @GetMapping("/api/notice/latest/{size}") //최신 공지사항 목록 반환
    public Page<NoticeEntity> noticeLatest(@PathVariable int size) {
        Page<NoticeEntity> noticeList = noticeRepository.findAll(PageRequest.of(0,size, Sort.Direction.DESC,"regDate"));
        return noticeList;
    }

    @ExceptionHandler(DuplicateNoticeException.class)
    public ResponseEntity<?> handlerDuplicateNoticeException(DuplicateNoticeException duplicateNoticeException) {
        return new ResponseEntity<>(duplicateNoticeException.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @PostMapping("/api/notice")
    public ResponseEntity<Object> addNotice(@RequestBody @Valid NoticeInput noticeInput, Errors errors) {
        if(errors.hasErrors()) {
            List<ResponseError> responseErrors = new ArrayList<>();
            errors.getAllErrors().stream().forEach(e -> {
                responseErrors.add(ResponseError.of((FieldError)e));
            });
            return new ResponseEntity<>(responseErrors, HttpStatus.BAD_REQUEST);
        }
        try {
            noticeService.addNotice(noticeInput);
        } catch (DuplicateNoticeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok().build();
    }

}