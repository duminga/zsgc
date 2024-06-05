package com.example.score.service;



import com.example.score.model.Stu;
import com.example.score.repository.StuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StuServiceimpl implements StuService {
    @Autowired
    private StuRepository stuRepository;

    @Override
    public List<Stu> getAllStu(){
        return stuRepository.findAll();
    }
    @Override
    public void saveStu(Stu stu){
        this.stuRepository.save(stu);
    }
    @Override
    public Stu getStuById(long id){
        // 调用数据访问层查找指定ID的学生，返回Optional==（选择)对象
        Optional<Stu> optional = stuRepository.findById(id);
        Stu stu = null;
        // 如果存在指定id的学生
        if (optional.isPresent()) {
            // 从Optional对象中获取学生对象
            stu = optional.get();
        } else {
            // 否则抛出运行时异常
            throw new RuntimeException("找不到学生ID :: " + id);
        }
        return stu;
    }

    // 删除指定id的学生
    @Override
    public void deleteStuById(long id){
        this.stuRepository.deleteById(id);
    }
    @Override
    public Page<Stu> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        //设置排序参数，升序ASC/降序DESC？
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();

        //根据页号/每页记录数/排序依据返回某指定页面数据。
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

        return this.stuRepository.findAll(pageable);
    }
    @Override
    public List<Stu> findByName(String name) {
        List<Stu> goodsNames = stuRepository.findAllByName(name);
        return goodsNames;
    }
    @Override
    public void updateStu(Stu stu) {
        stuRepository.save(stu);
    }
}
