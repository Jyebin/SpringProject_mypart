package SWTeam2.vocabulary.SWTeam2.controller;

import SWTeam2.vocabulary.SWTeam2.dto.VocaDto;
import SWTeam2.vocabulary.SWTeam2.entity.VocaEntity;
import SWTeam2.vocabulary.SWTeam2.service.TestService;
import SWTeam2.vocabulary.SWTeam2.service.VocaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class TestController {
    @Autowired
    private TestService testService;
    @GetMapping("/api/testwords")
    public ResponseEntity<?>getRandom30Words(){
        List<VocaEntity> randomVocaDtos = testService.getRandom30Words();
        Map<String, Object> result = new HashMap<>();
        result.put("voca",randomVocaDtos);
        return ResponseEntity.ok().body(result);
    }
}
