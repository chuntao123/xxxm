package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.DtWaterdataD;
import com.ruoyi.system.domain.DtWaterdataDVo;
import com.ruoyi.system.domain.DtWaterdataDallVo;
import com.ruoyi.system.domain.Xj56swzJixy;

/**
 * 二类数据Mapper接口
 *
 * @author wkf
 * @date 2022-07-30
 */
public interface DtWaterdataDMapper
{
    /**
     * 查询二类数据
     *
     * @param wdid 二类数据主键
     * @return 二类数据
     */
    public DtWaterdataD selectDtWaterdataDByWdid(Long wdid);

    /**
     * 根据id、时间查询站点数据列表
     *
     * @param DtWaterdataDVo 站点数据
     * @return 站点数据集合
     */
    public List<DtWaterdataDVo> selectDtWaterdataDVoList(int stid, String time);

    /**
     * 6h预测数据列表
     *
     * @param DtWaterdataDVo 6h预测数据
     * @return 6h预测数据集合
     */
    public List<DtWaterdataDVo> selectDtWaterdataD6hVoList(int stid, String time);


    /**
     * 12h预测数据列表
     *
     * @param DtWaterdataDVo 12h预测数据
     * @return 12h预测数据集合
     */
    public List<DtWaterdataDVo> selectDtWaterdataD12hVoList(int stid, String time);


    /**
     * 梧州实际值与预测数据列表
     *
     * @param DtWaterdataDVo 梧州实际值与预测数据
     * @return 梧州实际值与预测数据集合
     */
    public List<DtWaterdataDallVo> selectDtWaterdataDallVoList(String starttime, String endtime,int start,int pagesize);

    /**
     * 查询二类数据列表
     *
     * @param dtWaterdataD 二类数据
     * @return 二类数据集合
     */
    public List<DtWaterdataD> selectDtWaterdataDList(int stid,String time);

    /**
     * 新增二类数据
     *
     * @param dtWaterdataD 二类数据
     * @return 结果
     */
    public int insertDtWaterdataD(DtWaterdataD dtWaterdataD);

    /**
     * 修改二类数据
     *
     * @param dtWaterdataD 二类数据
     * @return 结果
     */
    public int updateDtWaterdataD(DtWaterdataD dtWaterdataD);

    /**
     * 删除二类数据
     *
     * @param wdid 二类数据主键
     * @return 结果
     */
    public int deleteDtWaterdataDByWdid(Long wdid);

    /**
     * 批量删除二类数据
     *
     * @param wdids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDtWaterdataDByWdids(Long[] wdids);

    /**
     * 批量删除二类标记
     *
     * @param wdids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteXj56swzJixyBySTIDs(Long[] wdids);

    /**
     * 批量新增二类标记
     *
     * @param xj56swzJixyList 二类标记列表
     * @return 结果
     */
    public int batchXj56swzJixy(List<Xj56swzJixy> xj56swzJixyList);


    /**
     * 通过二类数据主键删除二类标记信息
     *
     * @param wdid 二类数据ID
     * @return 结果
     */
    public int deleteXj56swzJixyBySTID(Long wdid);
}
