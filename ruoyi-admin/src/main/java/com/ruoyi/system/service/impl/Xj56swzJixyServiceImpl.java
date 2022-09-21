package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.Xj56swzJixyMapper;
import com.ruoyi.system.domain.Xj56swzJixy;
import com.ruoyi.system.service.IXj56swzJixyService;

/**
 * 二类标记Service业务层处理
 *
 * @author wkf
 * @date 2022-07-28
 */
@Service
public class Xj56swzJixyServiceImpl implements IXj56swzJixyService
{
    @Autowired
    private Xj56swzJixyMapper xj56swzJixyMapper;

    /**
     * 查询二类标记
     *
     * @param stid 二类标记主键
     * @return 二类标记
     */
    @Override
    public Xj56swzJixy selectXj56swzJixyByStid(String stid)
    {
        return xj56swzJixyMapper.selectXj56swzJixyByStid(stid);
    }

    /**
     * 查询二类标记列表
     *
     * @param xj56swzJixy 二类标记
     * @return 二类标记
     */
    @Override
    public List<Xj56swzJixy> selectXj56swzJixyList(Xj56swzJixy xj56swzJixy)
    {
        return xj56swzJixyMapper.selectXj56swzJixyList(xj56swzJixy);
    }

    /**
     * 查询三类标记列表
     *
     * @param xj56swzJixy 三类标记
     * @return 三类标记
     */
    @Override
    public List<Xj56swzJixy> selectXj56swzJixythreeList(Xj56swzJixy xj56swzJixy)
    {
        return xj56swzJixyMapper.selectXj56swzJixythreeList(xj56swzJixy);
    }

    /**
     * 新增二类标记
     *
     * @param xj56swzJixy 二类标记
     * @return 结果
     */
    @Override
    public int insertXj56swzJixy(Xj56swzJixy xj56swzJixy)
    {
        return xj56swzJixyMapper.insertXj56swzJixy(xj56swzJixy);
    }

    /**
     * 修改二类标记
     *
     * @param xj56swzJixy 二类标记
     * @return 结果
     */
    @Override
    public int updateXj56swzJixy(Xj56swzJixy xj56swzJixy)
    {
        return xj56swzJixyMapper.updateXj56swzJixy(xj56swzJixy);
    }

    /**
     * 批量删除二类标记
     *
     * @param stids 需要删除的二类标记主键
     * @return 结果
     */
    @Override
    public int deleteXj56swzJixyByStids(String[] stids)
    {
        return xj56swzJixyMapper.deleteXj56swzJixyByStids(stids);
    }

    /**
     * 删除二类标记信息
     *
     * @param stid 二类标记主键
     * @return 结果
     */
    @Override
    public int deleteXj56swzJixyByStid(String stid)
    {
        return xj56swzJixyMapper.deleteXj56swzJixyByStid(stid);
    }
}
