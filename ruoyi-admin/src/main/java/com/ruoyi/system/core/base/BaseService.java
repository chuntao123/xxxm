package com.ruoyi.system.core.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.system.core.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

public class BaseService<T> extends QueryService {
    @Autowired
    protected BaseMapper<T> baseMapper;

    public T selectById(Serializable id) {
        return baseMapper.selectById(id);
    }

    @Transactional
    public boolean updateById(T entity) {
        return baseMapper.updateById(entity) > 0;
    }

    @Transactional
    public boolean save(T entity) {
        return baseMapper.insert(entity) > 0;
    }

    @Transactional
    public void delete(Serializable id) {
        baseMapper.deleteById(id);
    }
}
