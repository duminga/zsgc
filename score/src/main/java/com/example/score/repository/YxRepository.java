package com.example.score.repository;

import com.example.score.model.Yx;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface YxRepository extends JpaRepository<Yx, Long> {
    List<Yx> findAllByName(String name);
}