package com.example.score.service;

import com.example.score.model.Score;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ScoreService {
    // 获取所有的学生成绩
    List<Score> getAllScore();
    // 新增或更新学生成绩
    void saveScore(Score score);
    // 获取指定ID的学生
    Score getScoreById(long id);
    // 删除指定ID的学生
    void deleteScoreById(long id);
    // 查找某个学生
    List<Score> findByName(String name);
    // 更新
    void updateScore(Score score);
    // 分页
    Page<Score> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
