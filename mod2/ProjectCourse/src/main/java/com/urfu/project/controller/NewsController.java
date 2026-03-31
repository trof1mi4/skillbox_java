package com.urfu.project.controller;

import com.urfu.project.dto.NewsDto;
import com.urfu.project.service.NewsCRUDService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api")
public class NewsController {

    private final NewsCRUDService newsService;
    private final HashMap<String, String> response;

    public NewsController(NewsCRUDService newsService) {
        this.newsService = newsService;
        response = new HashMap<>();
    }

    @GetMapping("/news/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        var news = newsService.getById(id);
        if (news == null) {
            response.put("message", "Новость с ID " + id + " не найдена.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        return ResponseEntity.ok(news);
    }

    @GetMapping("/news")
    public ResponseEntity<?> getByAllNews() {
        return ResponseEntity.ok(newsService.getAll());
    }

    @PostMapping("/news")
    public ResponseEntity<?> createNews(@RequestBody NewsDto createdNews) {
        return ResponseEntity.ok(newsService.create(createdNews));
    }

    @PutMapping("/news")
    public ResponseEntity<?> updateNews(@RequestBody NewsDto news) {
        newsService.update(news.getId(), news);
        return ResponseEntity.ok(newsService.getById(news.getId()));
    }

    @DeleteMapping("/news/{id}")
    public ResponseEntity deleteNews(@PathVariable Long id) {
        var news = newsService.getById(id);
        if (news == null) {
            response.put("message", "Новость с ID " + id + " не найдена.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        newsService.delete(news.getId());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}