package SWTeam2.vocabulary.SWTeam2.controller;

import SWTeam2.vocabulary.SWTeam2.auth.CustomUserDetail;
import SWTeam2.vocabulary.SWTeam2.dto.VocaDto;
import SWTeam2.vocabulary.SWTeam2.entity.UserEntity;
import SWTeam2.vocabulary.SWTeam2.entity.VocaEntity;
import SWTeam2.vocabulary.SWTeam2.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class TestController {
    @Autowired
    private TestService testService;

    @GetMapping("/api/testwords")
    public ResponseEntity<?> getTestWords(@AuthenticationPrincipal CustomUserDetail user, @PathVariable(value = "wordcnt") int wordcnt) {
        UserEntity userEntity = user.getUser();
        int level = userEntity.getLevel();
        List<VocaDto> randomVocaDtos;
        if (level == 0) {
            wordcnt = 30;
        } else if (level == 1 && wordcnt > 15) {
            return ResponseEntity.ok().body("하 수준의 단어 개수인 15개가 넘었습니다.");
        } else if (level == 2 && wordcnt > 20) {
            return ResponseEntity.ok().body("중 수준의 단어 개수인 20개가 넘었습니다.");
        } else if (level == 3 && wordcnt > 25) {
            return ResponseEntity.ok().body("상 수준의 단어 개수인 25개가 넘었습니다.");
        }
        randomVocaDtos = testService.getTestWords(wordcnt);
        Map<String, Object> result = new HashMap<>();
        result.put("voca", randomVocaDtos);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("api/testresult")
    public ResponseEntity<?> getTestResult(@AuthenticationPrincipal CustomUserDetail user, @RequestBody Map<String, String> param) {
        List<String> arrid = Arrays.asList(param.get("wrongVoca").split(","));
        UserEntity userEntity = user.getUser();
        if (arrid.size() >= 20) { //level 하
            userEntity.setLevel(1);
        } else if (arrid.size() < 20 && arrid.size() >= 10) {
            userEntity.setLevel(2);
        } else {
            userEntity.setLevel(3);
        }
        testService.updateUserLevel(userEntity);
        return ResponseEntity.ok().build();
    }
}

