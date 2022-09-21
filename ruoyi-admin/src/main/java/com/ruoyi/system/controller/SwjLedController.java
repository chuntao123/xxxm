package com.ruoyi.system.controller;

import com.ruoyi.system.core.base.QueryController;
import com.ruoyi.system.domain.SwjLed;
import com.ht.tohka.common.core.Result;
import com.ruoyi.system.service.impl.SwjLedServiceImpl;
import com.ruoyi.system.service.impl.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/zhexiantu/swj-led")
public class SwjLedController extends QueryController<SwjLed> {
    @Autowired
    SwjLedServiceImpl swjLedService;
    @Autowired
    WeatherService weatherService;

    /**
     * 获取列表里的数据
     *
     * @return
     */
    @PreAuthorize("@ss.hasPermi('system:d:list')")
    @GetMapping("list")
    public Result list() {
        return Result.success(swjLedService.list());
    }

}
