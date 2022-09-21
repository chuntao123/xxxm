package com.ruoyi.system.core.base;


import com.ht.tohka.common.core.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

public class QueryController<T> {
    @Autowired
    protected BaseService<T> baseService;


    @GetMapping("query")
    public Result pageQuery(@RequestParam Map<String, Object> queryParams) {
        return Result.success(baseService.query(queryParams));
    }

    @GetMapping("query/{id}")
    public Result queryById(@PathVariable String id) {
        return Result.success(baseService.selectById(id));
    }


}
