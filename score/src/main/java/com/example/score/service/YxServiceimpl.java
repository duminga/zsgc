package com.example.score.service;




import com.example.score.model.Yx;

import com.example.score.repository.YxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class YxServiceimpl implements YxService {
    @Autowired
    private YxRepository yxRepository;

    @Override
    public List<Yx> getAllYx(){
        return yxRepository.findAll();
    }
    @Override
    public void saveYx(Yx yx){
        this.yxRepository.save(yx);
    }
    @Override
    public Yx getYxById(long id){
        // 调用数据访问层查找指定ID的院系，返回Optional==（选择)对象
        Optional<Yx> optional = yxRepository.findById(id);
        Yx yx = null;
        // 如果存在指定id的院系
        if (optional.isPresent()) {
            // 从Optional对象中获取院系对象
            yx = optional.get();
        } else {
            // 否则抛出运行时异常
            throw new RuntimeException("找不到院系ID :: " + id);
        }
        return yx;
    }

    // 删除指定id的院系
    @Override
    public void deleteYxById(long id){
        this.yxRepository.deleteById(id);
    }
    @Override
    public Page<Yx> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        //设置排序参数，升序ASC/降序DESC？
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortField).ascending()
                : Sort.by(sortField).descending();

        //根据页号/每页记录数/排序依据返回某指定页面数据。
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);

        return this.yxRepository.findAll(pageable);
    }
    @Override
    public List<Yx> findByName(String name) {
        List<Yx> goodsNames = yxRepository.findAllByName(name);
        return goodsNames;
    }
    @Override
    public void updateYx(Yx yx) {
        yxRepository.save(yx);
    }
}
