package SWTeam2.vocabulary.SWTeam2.controller;

import SWTeam2.vocabulary.SWTeam2.auth.CustomUserDetail;
import SWTeam2.vocabulary.SWTeam2.dto.VocaDto;
import SWTeam2.vocabulary.SWTeam2.dto.WrongVocaDto;
import SWTeam2.vocabulary.SWTeam2.entity.UserEntity;
import SWTeam2.vocabulary.SWTeam2.entity.VocaEntity;
import SWTeam2.vocabulary.SWTeam2.entity.WrongVocaEntity;
import SWTeam2.vocabulary.SWTeam2.service.TestService;
import SWTeam2.vocabulary.SWTeam2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class TestController {
    @Autowired
    private TestService testService;
    @Autowired
    private UserService userService;
    @GetMapping("/api/testwords")
    public ResponseEntity<?> getTestWords() {
        List<VocaDto> randomVocaDtos;
        randomVocaDtos = testService.getTestWords(30);
        Map<String, Object> result = new HashMap<>();
        result.put("voca", randomVocaDtos);
        return ResponseEntity.ok().body(result);
    }
    @GetMapping("api/studywordtest") //단어 test
    public ResponseEntity<?>getStudyTestWords(@RequestParam("wordlist")String wordlist, @RequestParam("wordcnt")int wordcnt){
        List<VocaDto> randomVocaDtos;
        List<String>wordList = Arrays.asList(wordlist.split(","));
        randomVocaDtos = testService.getStudyTestWords(wordList,wordcnt);
        Map<String, Object>result = new HashMap<>();
        result.put("voca",randomVocaDtos);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("api/testresult")
    public ResponseEntity<?> getTestResult(@AuthenticationPrincipal CustomUserDetail user, @RequestBody Map<String, String> param) {
        List<String> arrid = Arrays.asList(param.get("wrongVoca").split(","));
        UserEntity userEntity = user.getUser();
        if (arrid.size() >= 20) { //level 하
            userEntity.setLevel(1);
        } else if (arrid.size() < 20 && arrid.size() >= 10) {
            userEntity.setLevel(2); //level 중
        } else {
            userEntity.setLevel(3); //level 상
        }
        System.out.print(arrid);
        testService.updateUserLevel(userEntity); //티어를 디폴트값으로 설정
        return ResponseEntity.ok().build();
    }
    @PostMapping("api/studyword") //학습 단어
    public ResponseEntity<?> getStudyWords(@PathVariable(value = "wordcnt") int wordcnt){
        List<VocaDto>vocaDtos = testService.getStudyWords(wordcnt);
        Map<String,Object>result = new HashMap<>();
        result.put("voca",vocaDtos);
        return ResponseEntity.ok().body(result);
    }
    @GetMapping("/api/wronglist") //틀린 단어 list
    public ResponseEntity<?>getwronglist(@AuthenticationPrincipal CustomUserDetail user){
        List<WrongVocaDto>wrongVocaDtoList = testService.wrongVocaList(user.getUser());
        if(wrongVocaDtoList == null){
            return ResponseEntity.notFound().build();
        }return ResponseEntity.ok(wrongVocaDtoList);
    }

}

