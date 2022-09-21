package com.ruoyi.system.controller;

import com.ruoyi.system.mapper.TotalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/system/page")
public class PageController {
    @Autowired
    private TotalMapper totalMapper;

    @PreAuthorize("@ss.hasPermi('system:d:list')")
    @GetMapping("/total")
    public int list(String starttime,String endtime)
    {

        return totalMapper.total(starttime,endtime);
    }
}
