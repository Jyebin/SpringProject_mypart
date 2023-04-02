//package com.example.Board.controller;
//
//import com.example.Board.Service.EventService;
//import com.example.Board.entity.EventEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//
//import java.util.List;
//
//@Controller
//public class EventController {
//    private final EventService eventService;
//    public EventController(EventService eventService){
//        this.eventService = eventService;
//    }
//    @GetMapping("/api/event")
//    public List<EventEntity> getAllEvents(){
//        return eventService.getAllEvents();
//    }
//    @GetMapping("/api/event/numbers")
//    public long getEventNumbers(){ //사고의 총 개수 -> 메인 페이지에서 사용
//        return eventService.getEventCount();
//    }
//}
