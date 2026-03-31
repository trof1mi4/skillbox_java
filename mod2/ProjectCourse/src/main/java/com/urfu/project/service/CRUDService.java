package com.urfu.project.service;

import com.urfu.project.dto.NewsDto;

import java.util.Collection;

public interface CRUDService <T> {
    T getById(Long id);
    Collection<T> getAll();
    NewsDto create(T item);
    void update(Long id, T item);
    void delete(Long id);
}
