package com.ruoyi.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.ruoyi.system.domain.WzRtsqAll;
import com.ruoyi.system.service.IWzRtsqAllService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * VIEWController
 *
 * @author wkf
 * @date 2022-07-27
 */
@RestController
@RequestMapping("/system/all")
public class WzRtsqAllController extends BaseController
{
    @Autowired
    private IWzRtsqAllService wzRtsqAllService;

    /**
     * 查询VIEW列表
     */
    @PreAuthorize("@ss.hasPermi('system:all:list')")
    @GetMapping("/list")
    public TableDataInfo list(WzRtsqAll wzRtsqAll)
    {
        startPage();
        List<WzRtsqAll> list = wzRtsqAllService.selectWzRtsqAllList(wzRtsqAll);
        return getDataTable(list);
    }

    /**
     * 导出VIEW列表
     */
    @PreAuthorize("@ss.hasPermi('system:all:export')")
    @Log(title = "VIEW", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, WzRtsqAll wzRtsqAll)
    {
        List<WzRtsqAll> list = wzRtsqAllService.selectWzRtsqAllList(wzRtsqAll);
        ExcelUtil<WzRtsqAll> util = new ExcelUtil<WzRtsqAll>(WzRtsqAll.class);
        util.exportExcel(response, list, "VIEW数据");
    }

    /**
     * 获取VIEW详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:all:query')")
    @GetMapping()
    public AjaxResult getInfo()
    {
        return AjaxResult.success(wzRtsqAllService.selectWzRtsqAllBySummary());
    }

    /**
     * 新增VIEW
     */
    @PreAuthorize("@ss.hasPermi('system:all:add')")
    @Log(title = "VIEW", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody WzRtsqAll wzRtsqAll)
    {
        return toAjax(wzRtsqAllService.insertWzRtsqAll(wzRtsqAll));
    }

    /**
     * 修改VIEW
     */
    @PreAuthorize("@ss.hasPermi('system:all:edit')")
    @Log(title = "VIEW", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody WzRtsqAll wzRtsqAll)
    {
        return toAjax(wzRtsqAllService.updateWzRtsqAll(wzRtsqAll));
    }

    /**
     * 删除VIEW
     */
    @PreAuthorize("@ss.hasPermi('system:all:remove')")
    @Log(title = "VIEW", businessType = BusinessType.DELETE)
	@DeleteMapping("/{summarys}")
    public AjaxResult remove(@PathVariable String[] summarys)
    {
        return toAjax(wzRtsqAllService.deleteWzRtsqAllBySummarys(summarys));
    }
}
