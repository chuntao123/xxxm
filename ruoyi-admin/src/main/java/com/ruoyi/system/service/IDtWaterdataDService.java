package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.DtWaterdataD;
import com.ruoyi.system.domain.DtWaterdataDVo;
import com.ruoyi.system.domain.DtWaterdataDallVo;

/**
 * 二类数据Service接口
 *
 * @author wkf
 * @date 2022-07-30
 */
public interface IDtWaterdataDService
{
    /**
     * 查询二类数据
     *
     * @param wdid 二类数据主键
     * @return 二类数据
     */
    public DtWaterdataD selectDtWaterdataDByWdid(Long wdid);

    /**
     * 查询二类数据列表
     *
     * @param dtWaterdataD 二类数据
     * @return 二类数据集合
     */
    public List<DtWaterdataD> selectDtWaterdataDList(int stid,String time);

    /**
     * 查询站点数据列表
     *
     * @param dtWaterdataD 站点数据
     * @return 站点数据集合
     */
    public List<DtWaterdataDVo> selectDtWaterdataDVoList(int stid, String time);

    /**
     * 6h预测数据列表
     *
     * @param dtWaterdataD 6h预测数据
     * @return 6h预测数据集合
     */
    public List<DtWaterdataDVo> selectDtWaterdataD6hVoList(int stid, String time);

    /**
     * 12h预测数据列表
     *
     * @param dtWaterdataD 12h预测数据
     * @return 2h预测数据集合
     */
    public List<DtWaterdataDVo> selectDtWaterdataD12hVoList(int stid, String time);


    /**
     * 梧州实际值与预测数据列表
     *
     * @param DtWaterdataDVo 梧州实际值与预测数据
     * @return 梧州实际值与预测数据集合
     */
    public List<DtWaterdataDallVo> selectDtWaterdataDallVoList(String starttime, String endtime ,int start,int pagesize);


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
     * 批量删除二类数据
     *
     * @param wdids 需要删除的二类数据主键集合
     * @return 结果
     */
    public int deleteDtWaterdataDByWdids(Long[] wdids);

    /**
     * 删除二类数据信息
     *
     * @param wdid 二类数据主键
     * @return 结果
     */
    public int deleteDtWaterdataDByWdid(Long wdid);
}
