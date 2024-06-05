package com.example.score.service;




import com.example.score.model.Tea;
import com.example.score.repository.TeaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeaServiceimpl implements TeaService {
    @Autowired
    private TeaRepository teaRepository;

    @Override
    public List<Tea> getAllTea(){
        return teaRepository.findAll();
    }
    @Override
    public void saveTea(Tea tea){
        this.teaRepository.save(tea);
    }
    @Override
    public Tea getTeaById(long id){
        // 调用数据访问层查找指定ID的教师，返回Optional==（选择)对象
        Optional<Tea> optional = teaRepository.findById(id);
        Tea tea = null;
        // 如果存在指定id的教师
        if (optional.isPresent()) {
            // 从Optional对象中获取教师对象
            tea = optional.get();
        } else {
            // 否则抛出运行时异常
            throw new RuntimeException("找不到教师ID :: " + id);
        }
        return tea;
    }

    // 删除指定id的教师
    @Override
    public void deleteTeaById(long id){
        this.teaRepository.deleteById(id);
    }
    @Override
    public Page<Tea> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        //设置排序参数，升序ASC/降序DESC？
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();

        //根据页号/每页记录数/排序依据返回某指定页面数据。
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

        return this.teaRepository.findAll(pageable);
    }
    @Override
    public List<Tea> findByName(String name) {
        List<Tea> goodsNames = teaRepository.findAllByName(name);
        return goodsNames;
    }
    @Override
    public void updateTea(Tea tea) {
        teaRepository.save(tea);
    }
}
