package com.example.score.repository;

import com.example.score.model.Tea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TeaRepository extends JpaRepository<Tea, Long> {
    List<Tea> findAllByName(String name);
}