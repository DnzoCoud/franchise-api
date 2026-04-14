package com.project.franchise.domain.repository;

import java.util.List;
import java.util.Optional;

public interface BaseRepository<T> {
    List<T> findAll();
    Optional<T> findById(Long id);
    T save(T entity);
}
