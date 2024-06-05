package com.example.score.service;

import com.example.score.model.Yx;
import org.springframework.data.domain.Page;

import java.util.List;

public interface YxService {
    // 获取所有的院系成绩
    List<Yx> getAllYx();
    // 新增或更新院系信息
    void saveYx(Yx yx);
    // 获取指定ID的院系
    Yx getYxById(long id);
    // 删除指定ID的院系
    void deleteYxById(long id);
    // 查找某个院系
    List<Yx> findByName(String name);
    // 更新
    void updateYx(Yx yx);
    // 分页
    Page<Yx> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
