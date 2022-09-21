package com.ruoyi.system.core;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.base.CaseFormat;
import com.ht.tohka.common.core.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * 通用查询service
 *
 * @param <T>
 */
public class QueryService<T> {
    @Autowired
    protected BaseMapper<T> mapper;

    /**
     * 通用查询，只要知道字段即可构建简单的and查询
     * 当size==0时表示不分页
     *
     * @param queryParams
     * @return
     */
    public PageResult<T> query(Map<String, Object> queryParams) {
        int page = getInt(queryParams, "page", 1);
        int size = getInt(queryParams, "size", 10);
        queryParams.remove("page");
        queryParams.remove("size");
        QueryWrapper<T> wrapper = new QueryWrapper<>();
        queryParams.forEach((key, value) -> {
            // 空的参数不参与查询
            if (value == null || !StringUtils.hasText(value.toString())) {
                return;
            }
            String[] strArr = key.split("_");
            String col = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, strArr[0]);
            if (strArr.length == 2) {
                switch (strArr[1]) {
                    case "like":
                        wrapper.like(col, value);
                        break;
                    case "likeLeft":
                        wrapper.likeLeft(col, value);
                        break;
                    case "likeRight":
                        wrapper.likeRight(col, value);
                        break;
                    case "ge":
                        wrapper.ge(col, value);
                        break;
                    case "gt":
                        wrapper.gt(col, value);
                        break;
                    case "le":
                        wrapper.le(col, value);
                        break;
                    case "lt":
                        wrapper.lt(col, value);
                        break;
                    case "eq":
                        wrapper.eq(col, value);
                        break;
                    case "oda":
                        wrapper.orderByAsc(col);
                        break;
                    case "odd":
                        wrapper.orderByDesc(col);
                        break;
                }
            } else {
                wrapper.eq(col, value);
            }
        });
        PageHelper.startPage(page, size);
        return PageResult.of((Page<T>) mapper.selectList(wrapper));
    }

    private Integer getInt(Map<String, Object> queryParams, String key, Integer defaultValue) {
        Object obj = queryParams.get(key);
        return obj == null ? defaultValue : Integer.parseInt(obj.toString());
    }
}

