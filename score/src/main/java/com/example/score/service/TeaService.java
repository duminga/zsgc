package com.example.score.service;


import com.example.score.model.Tea;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TeaService {
    // 获取所有的教师信息
    List<Tea> getAllTea();
    // 新增或更新教师
    void saveTea(Tea tea);
    // 获取指定ID的教师
    Tea getTeaById(long id);
    // 删除指定ID的教师
    void deleteTeaById(long id);
    // 查找某个教师
    List<Tea> findByName(String name);
    // 更新
    void updateTea(Tea tea);
    // 分页
    Page<Tea> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
