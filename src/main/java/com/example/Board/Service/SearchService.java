package com.example.Board.Service;
//import com.example.Board.repository.DemoRepository;
//import com.example.Board.repository.EventRepository;
import com.example.Board.repository.InfoRepository;
import com.example.Board.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SearchService {
//    @Autowired
//    private DemoRepository demoRepository;
//    @Autowired
//    private EventRepository eventRepository;
    @Autowired
    private NoticeRepository noticeRepository;
    @Autowired
    private InfoRepository infoRepository;

    public List<String> search(String keyword) {
        List<String> result = new ArrayList<>();
//        result.addAll(demoRepository.searchByContent(keyword));
//        result.addAll(eventRepository.searchByContent(keyword));
        result.addAll(noticeRepository.searchByContent(keyword));
        result.addAll(infoRepository.searchByContent(keyword));
        return result;
    }
}
