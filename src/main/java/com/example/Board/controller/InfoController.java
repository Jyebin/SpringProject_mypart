package com.example.Board.controller;


import com.example.Board.Service.InfoService;
import com.example.Board.entity.InfoEntity;
import com.example.Board.exception.*;
import com.example.Board.repository.InfoInput;
import com.example.Board.repository.InfoRepository;
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
public class InfoController {
    InfoRepository infoRepository;
    @Autowired
    private InfoService infoService;

    public InfoController(InfoService infoService) {
        this.infoService = infoService;
    }

    @GetMapping("/api/info")
    public List<InfoInput> getAllInfo() {
        return infoService.getAllInfo();
    }

    @GetMapping("/api/info/{id}")
    public ResponseEntity<?> getInfo(@PathVariable(value = "id") Long id) {
        Optional<InfoEntity> optionalInfo = infoService.getInfoById(id);
        if (optionalInfo.isPresent()) {
            infoService.addViewCount(id);
            return ResponseEntity.ok(optionalInfo.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ExceptionHandler(InfoNotFoundException.class)
    public ResponseEntity<String> handlerInfoNotFoundException(InfoNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/api/info/{id}") // 수정
    public void updateInfo(@PathVariable Long id, @RequestBody InfoInput infoInput) {
        infoService.updateInfo(id, infoInput);
    }

    @ExceptionHandler(AlreadyDeletedException.class)
    public ResponseEntity<String> handlerAlreadyDeletedException(AlreadyDeletedException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.OK);
    }

    @DeleteMapping("/api/info/{id}") //제보 글 한개씩 삭제
    public void deleteInfo(@PathVariable Long id) {
        infoService.deleteInfo(id);
    }

    @GetMapping("/api/info/latest/{size}") //최신 목록 반환(size에는 개수)
    public Page<InfoEntity> infoLatest(@PathVariable int size) {

        Page<InfoEntity> infoList
                = infoRepository.findAll(
                PageRequest.of(0, size, Sort.Direction.DESC, "regDate")); //0번째 페이지에서 size개의 공지를 갖고와 regDate를 기준으로 내림차순 정리
        return infoList;
    }

    @ExceptionHandler(DuplicateInfoException.class)
    public ResponseEntity<?> handlerDuplicateInfoException(DuplicateInfoException duplicateInfoException) {
        return new ResponseEntity(duplicateInfoException.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/api/info") //제보 글 작성
    public ResponseEntity<Object> addInfo(@RequestBody @Valid InfoInput infoInput, Errors errors) {
        if(errors.hasErrors()) {
            List<ResponseError> responseErrors = new ArrayList<>();
            errors.getAllErrors().stream().forEach(e -> {
                responseErrors.add(ResponseError.of((FieldError)e));
            });
            return new ResponseEntity<>(responseErrors, HttpStatus.BAD_REQUEST);
        }
        try {
            infoService.addInfo(infoInput);
        } catch (DuplicateInfoException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok().build();
    }
    @GetMapping("/api/info/{id}/likes")
    public ResponseEntity<Void>addLike(@PathVariable Long id){
        infoService.addLike(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @GetMapping("/api/info/{id}/hates")
    public ResponseEntity<Void>addHates(@PathVariable Long id){
        infoService.addHates(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
