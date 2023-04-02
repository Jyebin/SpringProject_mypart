//package com.example.Board.Service;
//
//import com.example.Board.entity.EventEntity;
//import com.example.Board.repository.EventRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class EventService {
//    private final EventRepository eventRepository;
//
//    public EventService(EventRepository eventRepository) {
//        this.eventRepository = eventRepository;
//    }
//    public List<EventEntity>getAllEvents(){ //모든 사건을 갖고옴
//        return eventRepository.findAll();
//    }
//    public long getEventCount(){
//        return eventRepository.count();
//    }
//}
