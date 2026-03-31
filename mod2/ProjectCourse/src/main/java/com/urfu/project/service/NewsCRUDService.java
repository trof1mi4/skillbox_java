package com.urfu.project.service;

import com.urfu.project.dto.NewsDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Service
public class NewsCRUDService implements CRUDService<NewsDto>{

    private final ConcurrentHashMap<Long, NewsDto> newsStorage;
    private final AtomicLong lastId;

    public NewsCRUDService() {
        this.newsStorage = new ConcurrentHashMap<>();
        this.lastId = new AtomicLong(1);
    }

    @Override
    public NewsDto getById(Long id) {
        return newsStorage.get(id);
    }

    @Override
    public Collection<NewsDto> getAll() {
        return newsStorage.values();
    }

    @Override
    public NewsDto create(NewsDto item) {
        Long newId = newsStorage.isEmpty() ? lastId.get() : lastId.incrementAndGet();
        item.setId(newId);
        newsStorage.put(item.getId(), item);
        return getById(newId);
    }

    @Override
    public void update(Long id, NewsDto item) {
        item.setId(id);
        newsStorage.put(item.getId(), item);
    }

    @Override
    public void delete(Long id) {
        newsStorage.remove(id);
    }
}