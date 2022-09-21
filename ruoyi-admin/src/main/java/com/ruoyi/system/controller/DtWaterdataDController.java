package com.ruoyi.system.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import com.ruoyi.system.domain.DtWaterdataDVo;
import com.ruoyi.system.domain.DtWaterdataDallVo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.DtWaterdataD;
import com.ruoyi.system.service.IDtWaterdataDService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 二类数据Controller
 *
 * @author wkf
 * @date 2022-07-30
 */
@RestController
@RequestMapping("/system/d")
public class DtWaterdataDController extends BaseController
{
    @Autowired
    private IDtWaterdataDService dtWaterdataDService;



    /**
     * 查询二类数据列表
     */
    @PreAuthorize("@ss.hasPermi('system:d:list')")
    @GetMapping("/list")
    public TableDataInfo list(int stid,int hour)
    {
        String time = LocalDateTime.now().minusHours(hour).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        List<DtWaterdataD> list = dtWaterdataDService.selectDtWaterdataDList(stid,time);
        return getDataTable(list);
    }

    /**
     * 查询站点数据列表
     */
    @PreAuthorize("@ss.hasPermi('system:d:list')")
    @GetMapping("/zdlist")
    public TableDataInfo zdlist(int stid,int hour)
    {
        String time = LocalDateTime.now().minusHours(hour).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        List<DtWaterdataDVo> list = dtWaterdataDService.selectDtWaterdataDVoList(stid,time);
        return getDataTable(list);
    }

    /**
     * 查询6h预测数据列表
     */
    @PreAuthorize("@ss.hasPermi('system:d:list')")
    @GetMapping("/zd6hlist")
    public TableDataInfo list6h(int stid,int hour)
    {
        String time = LocalDateTime.now().minusHours(hour).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        List<DtWaterdataDVo> list = dtWaterdataDService.selectDtWaterdataD6hVoList(stid,time);
        return getDataTable(list);
    }


    /**
     * 查询12h站点数据列表
     */
    @PreAuthorize("@ss.hasPermi('system:d:list')")
    @GetMapping("/zd12hlist")
    public TableDataInfo list12h(int stid,int hour)
    {
        String time = LocalDateTime.now().minusHours(hour).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        List<DtWaterdataDVo> list = dtWaterdataDService.selectDtWaterdataD12hVoList(stid,time);
        return getDataTable(list);
    }


    /**
     * 梧州实际值与预测数据列表
     */
    @PreAuthorize("@ss.hasPermi('system:d:list')")
    @GetMapping("/zdalllist")
    public TableDataInfo listall(String starttime, String endtime, int currentPage ,int pagesize)
    {
        int start = (currentPage-1) * pagesize;
        List<DtWaterdataDallVo> list = dtWaterdataDService.selectDtWaterdataDallVoList(starttime,endtime,start,pagesize);
        return getDataTable(list);
    }

//    /**
//     * 导出二类数据列表
//     */
//    @PreAuthorize("@ss.hasPermi('system:d:export')")
//    @Log(title = "二类数据", businessType = BusinessType.EXPORT)
//    @PostMapping("/export")
//    public void export(HttpServletResponse response, DtWaterdataD dtWaterdataD)
//    {
//        List<DtWaterdataD> list = dtWaterdataDService.selectDtWaterdataDList(dtWaterdataD);
//        ExcelUtil<DtWaterdataD> util = new ExcelUtil<DtWaterdataD>(DtWaterdataD.class);
//        util.exportExcel(response, list, "二类数据数据");
//    }

    /**
     * 获取二类数据详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:d:query')")
    @GetMapping(value = "/{wdid}")
    public AjaxResult getInfo(@PathVariable("wdid") Long wdid)
    {
        return AjaxResult.success(dtWaterdataDService.selectDtWaterdataDByWdid(wdid));
    }

    /**
     * 新增二类数据
     */
    @PreAuthorize("@ss.hasPermi('system:d:add')")
    @Log(title = "二类数据", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DtWaterdataD dtWaterdataD)
    {
        return toAjax(dtWaterdataDService.insertDtWaterdataD(dtWaterdataD));
    }

    /**
     * 修改二类数据
     */
    @PreAuthorize("@ss.hasPermi('system:d:edit')")
    @Log(title = "二类数据", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DtWaterdataD dtWaterdataD)
    {
        return toAjax(dtWaterdataDService.updateDtWaterdataD(dtWaterdataD));
    }

    /**
     * 删除二类数据
     */
    @PreAuthorize("@ss.hasPermi('system:d:remove')")
    @Log(title = "二类数据", businessType = BusinessType.DELETE)
	@DeleteMapping("/{wdids}")
    public AjaxResult remove(@PathVariable Long[] wdids)
    {
        return toAjax(dtWaterdataDService.deleteDtWaterdataDByWdids(wdids));
    }
}
