package com.example.score.repository;

import com.example.score.model.Stu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StuRepository extends JpaRepository<Stu, Long> {
    List<Stu> findAllByName(String name);
}