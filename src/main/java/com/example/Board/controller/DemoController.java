//package com.example.Board.controller;
//
//import com.example.Board.Service.DemoService;
//import com.example.Board.Service.EventService;
//import com.example.Board.entity.DemoEntity;
//import com.example.Board.entity.EventEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import java.util.List;
//
//@Controller
//public class DemoController {
//    private final DemoService demoService;
//    public DemoController(DemoService demoService){
//        this.demoService = demoService;
//    }
//    @GetMapping("/api/demo")
//    public List<DemoEntity> getAllDemos(){
//        return demoService.getAllDemos();
//    }
//    @GetMapping("/api/demo/numbers") //시위의 총 개수 -> 메인 페이지에서 사용
//    public long getDemoNumbers(){
//        return demoService.getDemoCount();
//    }
//}
