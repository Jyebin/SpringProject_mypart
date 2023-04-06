package com.example.Board.controller;

import com.example.Board.Service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SearchController {
    @Autowired
    private SearchService searchService;
    @GetMapping("/api/search")
    public List<String> search(@RequestParam String keyword){
        return searchService.search(keyword);
    }
}
