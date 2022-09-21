package com.ruoyi.system.controller;


import com.ht.tohka.common.core.Result;
import com.ruoyi.system.domain.Raindata;
import com.ruoyi.system.mapper.RaindataMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/system/raindata")
public class RaindataController {
    @Autowired
    RaindataMapper raindataMapper;

    @PreAuthorize("@ss.hasPermi('system:d:list')")
    @GetMapping("/list")
    public Result list(int stid, int hour){
        String time = LocalDateTime.now().minusHours(hour).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        List<Raindata> list = raindataMapper.getraindata(stid,time);
        return Result.success(list);
    }

}
