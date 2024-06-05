package com.example.score.service;

import com.example.score.model.Score;
import com.example.score.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScoreServiceimpl implements ScoreService {
    @Autowired
    private ScoreRepository scoreRepository;

    @Override
    public List<Score> getAllScore(){
        return scoreRepository.findAll();
    }
    @Override
    public void saveScore(Score score){
        this.scoreRepository.save(score);
    }
    @Override
    public Score getScoreById(long id){
        // 调用数据访问层查找指定ID的学生，返回Optional==（选择)对象
        Optional<Score> optional = scoreRepository.findById(id);
        Score score = null;
        // 如果存在指定id的学生
        if (optional.isPresent()) {
            // 从Optional对象中获取学生对象
            score = optional.get();
        } else {
            // 否则抛出运行时异常
            throw new RuntimeException("找不到学生ID :: " + id);
        }
        return score;
    }

    // 删除指定id的学生
    @Override
    public void deleteScoreById(long id){
        this.scoreRepository.deleteById(id);
    }
    @Override
    public Page<Score> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        //设置排序参数，升序ASC/降序DESC？
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();

        //根据页号/每页记录数/排序依据返回某指定页面数据。
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

        return this.scoreRepository.findAll(pageable);
    }
    @Override
    public List<Score> findByName(String name) {
        List<Score> goodsNames = scoreRepository.findAllByName(name);
        return goodsNames;
    }
    @Override
    public void updateScore(Score score) {
        scoreRepository.save(score);
    }
}
