package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.WzRtsqAll;
import com.ruoyi.system.domain.WzCs;

/**
 * VIEWMapper接口
 *
 * @author wkf
 * @date 2022-07-27
 */
public interface WzRtsqAllMapper
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
     * 删除VIEW
     *
     * @param summary VIEW主键
     * @return 结果
     */
    public int deleteWzRtsqAllBySummary(String summary);

    /**
     * 批量删除VIEW
     *
     * @param summarys 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWzRtsqAllBySummarys(String[] summarys);

    /**
     * 批量删除${subTable.functionName}
     *
     * @param summarys 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWzCsByCsids(String[] summarys);

    /**
     * 批量新增${subTable.functionName}
     *
     * @param wzCsList ${subTable.functionName}列表
     * @return 结果
     */
    public int batchWzCs(List<WzCs> wzCsList);


    /**
     * 通过VIEW主键删除${subTable.functionName}信息
     *
     * @param summary VIEWID
     * @return 结果
     */
    public int deleteWzCsByCsid(String summary);
}
