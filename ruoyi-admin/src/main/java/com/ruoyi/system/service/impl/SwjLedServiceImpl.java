package com.ruoyi.system.service.impl;


import com.ruoyi.system.core.base.BaseService;
import com.ruoyi.system.domain.SwjLed;
import com.ruoyi.system.domain.SwjLedVO;
import com.ruoyi.system.domain.TableDataVO;
import com.ruoyi.system.mapper.SwjLedMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * 水情信息
 */
@Service
public class SwjLedServiceImpl extends BaseService<SwjLed> {
    @Autowired
    SwjLedMapper swjLedMapper;

    @Value("${table.size}")
    public Integer tableSize;

    public TableDataVO list() {
        List<SwjLedVO> vos = swjLedMapper.list();
        Collections.sort(vos);
        return new TableDataVO(vos, tableSize);
    }
}
