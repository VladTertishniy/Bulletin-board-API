package com.example.bulletinboardapi.repository;

import com.example.bulletinboardapi.model.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdRepository extends JpaRepository<Ad, Long> {
    boolean deleteById (long id);
    // Ad findById(long id);

    default Ad findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(() -> new RuntimeException("This Ad was not found " + id));
    }
}
