package com.example.Board.controller;

import com.example.Board.dto.InfoDto;
import com.example.Board.entity.InfoEntity;
import com.example.Board.exception.DuplicateNoticeException;
import com.example.Board.exception.ResponseError;
import com.example.Board.service.InfoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequiredArgsConstructor
public class InfoController {
    @Autowired
    private InfoService infoService;
    @PostMapping("/api/info") //글 작성
    public ResponseEntity<Object>addInfo(@RequestBody @Valid InfoDto infoDto, Errors errors){
        if(errors.hasErrors()){
            List<ResponseError> responseErrors = new ArrayList<>();
            errors.getAllErrors().stream().forEach(e->{
                responseErrors.add(ResponseError.of((FieldError)e));
            });
            return new ResponseEntity<>(responseErrors, HttpStatus.BAD_REQUEST);
        }
        try{
            infoService.addInfo(infoDto);
        }catch(DuplicateNoticeException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok().build();
    }
    @GetMapping("/api/info/{id}") //제보 id별로 조회
    public ResponseEntity<?>getInfoById(@PathVariable(value = "id")Long id){
        Optional<InfoDto>infoDtoId = infoService.getInfoById(id);
        if(infoDtoId.isPresent()){
            infoService.increaseViews(id);
            return ResponseEntity.ok().body(infoDtoId.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/api/info/{id}") //수정
    public ResponseEntity<?>updateInfo(@PathVariable(value = "id")Long id, @RequestBody InfoDto infoDto){
        Optional<InfoEntity>updateInfo = infoService.updateInfo(id, infoDto);
        if(updateInfo.isPresent()){
            return ResponseEntity.ok().body(updateInfo);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/api/info") //제보 전체 조회
    public ResponseEntity<?>listAllInfo(@RequestParam(required = false)String keyword){
        List<InfoDto>infoDtos = infoService.listAllinfo(keyword);
        Map<String,Object> result = new HashMap<>();
        result.put("data",infoDtos);
        result.put("count",infoDtos.size());

        return ResponseEntity.ok().body(result);
    }
    @DeleteMapping("/api/info/{id}") //제보 글 삭제
    public ResponseEntity<?>deleteInfoId(@PathVariable(value = "id")Long id){
        Long deleteinfo = infoService.deleteInfoId(id);
        if(deleteinfo == null){
            return ResponseEntity.ok().body(deleteinfo);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
