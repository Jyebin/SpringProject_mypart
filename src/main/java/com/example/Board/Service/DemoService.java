//package com.example.Board.Service;
//
//import com.example.Board.entity.DemoEntity;
//import com.example.Board.entity.EventEntity;
//import com.example.Board.repository.DemoRepository;
//import com.example.Board.repository.EventRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class DemoService {
//    private final DemoRepository demoRepository;
//
//    public DemoService(DemoRepository demoRepository) {
//        this.demoRepository = demoRepository;
//    }
//    public List<DemoEntity>getAllDemos(){ //모든 사건을 갖고옴
//        return demoRepository.findAll();
//    }
//    public long getDemoCount(){
//        return demoRepository.count();
//    }
//}
