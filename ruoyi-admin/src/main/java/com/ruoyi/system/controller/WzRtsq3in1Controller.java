package com.ruoyi.system.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson2.JSONObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.WzRtsq3in1;
import com.ruoyi.system.service.IWzRtsq3in1Service;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * rtsqController
 *
 * @author wkf
 * @date 2022-07-29
 */
@RestController
@RequestMapping("/system/rtsq3in1")
public class WzRtsq3in1Controller extends BaseController
{
    @Autowired
    private IWzRtsq3in1Service wzRtsq3in1Service;

    /**
     * 查询rtsq列表
     */
    @PreAuthorize("@ss.hasPermi('system:rtsq3in1:list')")
    @GetMapping("/list")
    public JSONObject list(int csid, int hour)
    {
        String time = LocalDateTime.now().minusHours(hour).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        return wzRtsq3in1Service.selectWzRtsq3in1List(csid,time);
    }

    /**
     * 查询rtsq列表
     */
    @PreAuthorize("@ss.hasPermi('system:rtsq3in1:list')")
    @GetMapping("/menulist")
    public TableDataInfo selectWzRtsq3in1menuList()
    {
        return getDataTable(wzRtsq3in1Service.selectWzRtsq3in1menuList());
    }

    /**
     * 查询报表特殊值
     */
    @PreAuthorize("@ss.hasPermi('system:rtsq3in1:list')")
    @GetMapping("/speciallist")
    public TableDataInfo selectWzRtsq3in1specialList(@RequestParam(name = "zd") List zd,@RequestParam(name = "starttime") String starttime,@RequestParam(name = "endtime") String endtime)
    {
        return getDataTable(wzRtsq3in1Service.selectWzRtsq3in1specialList(zd,starttime,endtime));
    }

    /**
     * 查询报表数据
     */
    @PreAuthorize("@ss.hasPermi('system:rtsq3in1:list')")
    @GetMapping("/bblist")
    public List<Map<String,Object>> selectWzRtsq3in1bbList(@RequestParam(name = "url") String url)
    {
        return wzRtsq3in1Service.selectWzRtsq3in1bbList(url);
    }

//    /**
//     * 导出rtsq列表
//     */
//    @PreAuthorize("@ss.hasPermi('system:rtsq3in1:export')")
//    @Log(title = "rtsq", businessType = BusinessType.EXPORT)
//    @PostMapping("/export")
//    public void export(HttpServletResponse response, int csid,String time)
//    {
//        List<WzRtsq3in1> list = wzRtsq3in1Service.selectWzRtsq3in1List(csid,time);
//        ExcelUtil<WzRtsq3in1> util = new ExcelUtil<WzRtsq3in1>(WzRtsq3in1.class);
//        util.exportExcel(response, list, "rtsq数据");
//    }

    /**
     * 获取rtsq详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:rtsq3in1:query')")
    @GetMapping(value = "/{senid}")
    public AjaxResult getInfo(@PathVariable("senid") Long senid)
    {
        return AjaxResult.success(wzRtsq3in1Service.selectWzRtsq3in1BySenid(senid));
    }

    /**
     * 新增rtsq
     */
    @PreAuthorize("@ss.hasPermi('system:rtsq3in1:add')")
    @Log(title = "rtsq", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WzRtsq3in1 wzRtsq3in1)
    {
        return toAjax(wzRtsq3in1Service.insertWzRtsq3in1(wzRtsq3in1));
    }

    /**
     * 修改rtsq
     */
    @PreAuthorize("@ss.hasPermi('system:rtsq3in1:edit')")
    @Log(title = "rtsq", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WzRtsq3in1 wzRtsq3in1)
    {
        return toAjax(wzRtsq3in1Service.updateWzRtsq3in1(wzRtsq3in1));
    }

    /**
     * 删除rtsq
     */
    @PreAuthorize("@ss.hasPermi('system:rtsq3in1:remove')")
    @Log(title = "rtsq", businessType = BusinessType.DELETE)
	@DeleteMapping("/{senids}")
    public AjaxResult remove(@PathVariable Long[] senids)
    {
        return toAjax(wzRtsq3in1Service.deleteWzRtsq3in1BySenids(senids));
    }
}
