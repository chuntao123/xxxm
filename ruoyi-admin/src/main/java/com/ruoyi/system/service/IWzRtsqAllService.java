package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.WzRtsqAll;

/**
 * VIEWService接口
 *
 * @author wkf
 * @date 2022-07-27
 */
public interface IWzRtsqAllService
{
    /**
     * 查询VIEW
     *
     * @param summary VIEW主键
     * @return VIEW
     */
    public List<WzRtsqAll> selectWzRtsqAllBySummary();

    /**
     * 查询VIEW列表
     *
     * @param wzRtsqAll VIEW
     * @return VIEW集合
     */
    public List<WzRtsqAll> selectWzRtsqAllList(WzRtsqAll wzRtsqAll);

    /**
     * 新增VIEW
     *
     * @param wzRtsqAll VIEW
     * @return 结果
     */
    public int insertWzRtsqAll(WzRtsqAll wzRtsqAll);

    /**
     * 修改VIEW
     *
     * @param wzRtsqAll VIEW
     * @return 结果
     */
    public int updateWzRtsqAll(WzRtsqAll wzRtsqAll);

    /**
     * 批量删除VIEW
     *
     * @param summarys 需要删除的VIEW主键集合
     * @return 结果
     */
    public int deleteWzRtsqAllBySummarys(String[] summarys);

    /**
     * 删除VIEW信息
     *
     * @param summary VIEW主键
     * @return 结果
     */
    public int deleteWzRtsqAllBySummary(String summary);
}
