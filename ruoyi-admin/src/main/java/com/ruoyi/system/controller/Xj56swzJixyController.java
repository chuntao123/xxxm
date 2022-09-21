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
import com.ruoyi.system.domain.Xj56swzJixy;
import com.ruoyi.system.service.IXj56swzJixyService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 二类标记Controller
 *
 * @author wkf
 * @date 2022-07-28
 */
@RestController
@RequestMapping("/system/swz")
public class Xj56swzJixyController extends BaseController
{
    @Autowired
    private IXj56swzJixyService xj56swzJixyService;

    /**
     * 查询二类标记列表
     */
    @PreAuthorize("@ss.hasPermi('system:swz:list')")
    @GetMapping("/list")
    public TableDataInfo list(Xj56swzJixy xj56swzJixy)
    {
        startPage();
        List<Xj56swzJixy> list = xj56swzJixyService.selectXj56swzJixyList(xj56swzJixy);
        return getDataTable(list);
    }


    /**
     * 查询三类标记列表
     */
    @PreAuthorize("@ss.hasPermi('system:swz:list')")
    @GetMapping("/threelist")
    public TableDataInfo threelist(Xj56swzJixy xj56swzJixy)
    {
        startPage();
        List<Xj56swzJixy> list = xj56swzJixyService.selectXj56swzJixythreeList(xj56swzJixy);
        return getDataTable(list);
    }
    /**
     * 导出二类标记列表
     */
    @PreAuthorize("@ss.hasPermi('system:swz:export')")
    @Log(title = "二类标记", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Xj56swzJixy xj56swzJixy)
    {
        List<Xj56swzJixy> list = xj56swzJixyService.selectXj56swzJixyList(xj56swzJixy);
        ExcelUtil<Xj56swzJixy> util = new ExcelUtil<Xj56swzJixy>(Xj56swzJixy.class);
        util.exportExcel(response, list, "二类标记数据");
    }

    /**
     * 获取二类标记详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:swz:query')")
    @GetMapping(value = "/{stid}")
    public AjaxResult getInfo(@PathVariable("stid") String stid)
    {
        return AjaxResult.success(xj56swzJixyService.selectXj56swzJixyByStid(stid));
    }

    /**
     * 新增二类标记
     */
    @PreAuthorize("@ss.hasPermi('system:swz:add')")
    @Log(title = "二类标记", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Xj56swzJixy xj56swzJixy)
    {
        return toAjax(xj56swzJixyService.insertXj56swzJixy(xj56swzJixy));
    }

    /**
     * 修改二类标记
     */
    @PreAuthorize("@ss.hasPermi('system:swz:edit')")
    @Log(title = "二类标记", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Xj56swzJixy xj56swzJixy)
    {
        return toAjax(xj56swzJixyService.updateXj56swzJixy(xj56swzJixy));
    }

    /**
     * 删除二类标记
     */
    @PreAuthorize("@ss.hasPermi('system:swz:remove')")
    @Log(title = "二类标记", businessType = BusinessType.DELETE)
	@DeleteMapping("/{stids}")
    public AjaxResult remove(@PathVariable String[] stids)
    {
        return toAjax(xj56swzJixyService.deleteXj56swzJixyByStids(stids));
    }
}
