package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Xj56swzJixy;

/**
 * 二类标记Mapper接口
 *
 * @author wkf
 * @date 2022-07-28
 */
public interface Xj56swzJixyMapper
{
    /**
     * 查询二类标记
     *
     * @param stid 二类标记主键
     * @return 二类标记
     */
    public Xj56swzJixy selectXj56swzJixyByStid(String stid);

    /**
     * 查询二类标记列表
     *
     * @param xj56swzJixy 二类标记
     * @return 二类标记集合
     */
    public List<Xj56swzJixy> selectXj56swzJixyList(Xj56swzJixy xj56swzJixy);

    /**
     * 查询三类标记列表
     *
     * @param xj56swzJixy 三类标记
     * @return 三类标记集合
     */
    public List<Xj56swzJixy> selectXj56swzJixythreeList(Xj56swzJixy xj56swzJixy);

    /**
     * 新增二类标记
     *
     * @param xj56swzJixy 二类标记
     * @return 结果
     */
    public int insertXj56swzJixy(Xj56swzJixy xj56swzJixy);

    /**
     * 修改二类标记
     *
     * @param xj56swzJixy 二类标记
     * @return 结果
     */
    public int updateXj56swzJixy(Xj56swzJixy xj56swzJixy);

    /**
     * 删除二类标记
     *
     * @param stid 二类标记主键
     * @return 结果
     */
    public int deleteXj56swzJixyByStid(String stid);

    /**
     * 批量删除二类标记
     *
     * @param stids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteXj56swzJixyByStids(String[] stids);
}
