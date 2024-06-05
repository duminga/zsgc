package com.example.score.service;


import com.example.score.model.Stu;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StuService {
    // 获取所有的学生基本信息
    List<Stu> getAllStu();
    // 新增或更新学生
    void saveStu(Stu stu);
    // 获取指定ID的学生
    Stu getStuById(long id);
    // 删除指定ID的学生
    void deleteStuById(long id);
    // 查找某个学生
    List<Stu> findByName(String name);
    // 更新
    void updateStu(Stu stu);
    // 分页
    Page<Stu> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
