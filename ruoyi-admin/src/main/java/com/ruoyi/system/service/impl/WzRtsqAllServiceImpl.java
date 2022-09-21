package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.system.domain.WzCs;
import com.ruoyi.system.mapper.WzRtsqAllMapper;
import com.ruoyi.system.domain.WzRtsqAll;
import com.ruoyi.system.service.IWzRtsqAllService;

/**
 * VIEWService业务层处理
 *
 * @author wkf
 * @date 2022-07-27
 */
@Service
public class WzRtsqAllServiceImpl implements IWzRtsqAllService
{
    @Autowired
    private WzRtsqAllMapper wzRtsqAllMapper;

    /**
     * 查询VIEW
     *
     * @param summary VIEW主键
     * @return VIEW
     */
    @Override
    public List<WzRtsqAll> selectWzRtsqAllBySummary()
    {
        return wzRtsqAllMapper.selectWzRtsqAllBySummary();
    }

    /**
     * 查询VIEW列表
     *
     * @param wzRtsqAll VIEW
     * @return VIEW
     */
    @Override
    public List<WzRtsqAll> selectWzRtsqAllList(WzRtsqAll wzRtsqAll)
    {
        return wzRtsqAllMapper.selectWzRtsqAllList(wzRtsqAll);
    }

    /**
     * 新增VIEW
     *
     * @param wzRtsqAll VIEW
     * @return 结果
     */
    @Transactional
    @Override
    public int insertWzRtsqAll(WzRtsqAll wzRtsqAll)
    {
        int rows = wzRtsqAllMapper.insertWzRtsqAll(wzRtsqAll);
        insertWzCs(wzRtsqAll);
        return rows;
    }

    /**
     * 修改VIEW
     *
     * @param wzRtsqAll VIEW
     * @return 结果
     */
    @Transactional
    @Override
    public int updateWzRtsqAll(WzRtsqAll wzRtsqAll)
    {
        wzRtsqAllMapper.deleteWzCsByCsid(wzRtsqAll.getSummary());
        insertWzCs(wzRtsqAll);
        return wzRtsqAllMapper.updateWzRtsqAll(wzRtsqAll);
    }

    /**
     * 批量删除VIEW
     *
     * @param summarys 需要删除的VIEW主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteWzRtsqAllBySummarys(String[] summarys)
    {
        wzRtsqAllMapper.deleteWzCsByCsids(summarys);
        return wzRtsqAllMapper.deleteWzRtsqAllBySummarys(summarys);
    }

    /**
     * 删除VIEW信息
     *
     * @param summary VIEW主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteWzRtsqAllBySummary(String summary)
    {
        wzRtsqAllMapper.deleteWzCsByCsid(summary);
        return wzRtsqAllMapper.deleteWzRtsqAllBySummary(summary);
    }

    /**
     * 新增${subTable.functionName}信息
     *
     * @param wzRtsqAll VIEW对象
     */
    public void insertWzCs(WzRtsqAll wzRtsqAll)
    {
        List<WzCs> wzCsList = wzRtsqAll.getWzCsList();
        String summary = wzRtsqAll.getSummary();
        if (StringUtils.isNotNull(wzCsList))
        {
            List<WzCs> list = new ArrayList<WzCs>();
            for (WzCs wzCs : wzCsList)
            {
                wzCs.setCsid(summary);
                list.add(wzCs);
            }
            if (list.size() > 0)
            {
                wzRtsqAllMapper.batchWzCs(list);
            }
        }
    }
}
