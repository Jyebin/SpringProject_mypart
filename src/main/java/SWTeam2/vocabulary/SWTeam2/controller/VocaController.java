package SWTeam2.vocabulary.SWTeam2.controller;

import SWTeam2.vocabulary.SWTeam2.dto.VocaDto;
import SWTeam2.vocabulary.SWTeam2.entity.VocaEntity;
import SWTeam2.vocabulary.SWTeam2.exception.ExceptionMessage;
import SWTeam2.vocabulary.SWTeam2.exception.ResponseError;
import SWTeam2.vocabulary.SWTeam2.service.VocaService;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
public class VocaController {
    @Autowired
    private VocaService vocaService;
    @PreAuthorize("admin")
    @PostMapping("/api/addvoca") //단어 추가
    public ResponseEntity<?>addVoca(@RequestBody @Valid VocaDto vocaDto, Errors errors){
        if(errors.hasErrors()){
            List<ResponseError>responseErrors = new ArrayList<>();
            errors.getAllErrors().stream().forEach(e->{
                responseErrors.add(ResponseError.of((FieldError) e));
            });
            return new ResponseEntity<>(responseErrors, HttpStatus.BAD_REQUEST);
        }try{
            vocaService.addVoca(vocaDto);
        }catch (ExceptionMessage e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok().build();
        }
    @PreAuthorize("admin")
    @PutMapping("/api/updatevoca/{id}") //단어 수정
    public ResponseEntity<?>updateVoca(@PathVariable(value = "id")int id, @RequestBody VocaDto vocaDto){
        Optional<VocaEntity>updateVoca = vocaService.updateVoca(id, vocaDto);
        if(updateVoca.isPresent()){
            return ResponseEntity.ok().body(updateVoca);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/api/allvoca") //단어장 전부 조회
    public ResponseEntity<?>getAllVoca(@PathVariable(required = false)String keyword){
        List<VocaDto> vocaDtos = vocaService.getAllVoca(keyword);
        Map<String, Object>result = new HashMap<>();
        result.put("voca",vocaDtos);

        return ResponseEntity.ok().body(result);
    }
    @GetMapping("/api/findvoca/{id}") //id별로 단어 조회
    public ResponseEntity<?>getVocaById(@PathVariable(value = "id")int id){
        Optional<VocaDto>vocaDtoId = vocaService.getVocaById(id);
        if(vocaDtoId.isPresent()){
            return ResponseEntity.ok().body(vocaDtoId.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @PreAuthorize("admin")
    @DeleteMapping("/api/deletevoca/{id}") //id별로 단어 삭제
    public ResponseEntity<?>deleteVoca(@PathVariable(value = "id")int id){
            long deleteVoca = vocaService.deleteVoca(id);
            if(deleteVoca == 0){
                return ResponseEntity.ok().body(deleteVoca);
            }else{
                return ResponseEntity.notFound().build();
            }
        }
}

