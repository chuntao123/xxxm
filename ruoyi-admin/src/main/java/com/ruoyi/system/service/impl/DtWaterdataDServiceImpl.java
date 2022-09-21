package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.system.domain.DtWaterdataDVo;
import com.ruoyi.system.domain.DtWaterdataDallVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.system.domain.Xj56swzJixy;
import com.ruoyi.system.mapper.DtWaterdataDMapper;
import com.ruoyi.system.domain.DtWaterdataD;
import com.ruoyi.system.service.IDtWaterdataDService;

/**
 * 二类数据Service业务层处理
 *
 * @author wkf
 * @date 2022-07-30
 */
@Service
public class DtWaterdataDServiceImpl implements IDtWaterdataDService
{
    @Autowired
    private DtWaterdataDMapper dtWaterdataDMapper;

    /**
     * 查询二类数据
     *
     * @param wdid 二类数据主键
     * @return 二类数据
     */
    @Override
    public DtWaterdataD selectDtWaterdataDByWdid(Long wdid)
    {
        return dtWaterdataDMapper.selectDtWaterdataDByWdid(wdid);
    }

    /**
     * 查询二类数据列表
     *
     * @param dtWaterdataD 二类数据
     * @return 二类数据
     */
    @Override
    public List<DtWaterdataD> selectDtWaterdataDList(int stid,String time)
    {

        return dtWaterdataDMapper.selectDtWaterdataDList(stid,time);
    }

    /**
     * 查询站点数据列表
     *
     * @param dtWaterdataD 站点数据
     * @return 站点数据
     */
    @Override
    public List<DtWaterdataDVo> selectDtWaterdataDVoList(int stid, String time)
    {

        return dtWaterdataDMapper.selectDtWaterdataDVoList(stid,time);
    }

    /**
     * 查询6H预测数据列表
     *
     * @param dtWaterdataD 6H预测数据
     * @return 6H预测数据
     */
    @Override
    public List<DtWaterdataDVo> selectDtWaterdataD6hVoList(int stid, String time)
    {

        return dtWaterdataDMapper.selectDtWaterdataD6hVoList(stid,time);
    }

    /**
     * 查询12H预测数据列表
     *
     * @param dtWaterdataD 12H预测数据
     * @return 12H预测数据
     */
    @Override
    public List<DtWaterdataDVo> selectDtWaterdataD12hVoList(int stid, String time)
    {

        return dtWaterdataDMapper.selectDtWaterdataD12hVoList(stid,time);
    }

    /**
     * 梧州实际值与预测数据列表
     *
     * @param DtWaterdataDVo 梧州实际值与预测数据
     * @return 梧州实际值与预测数据集合
     */
    @Override
    public List<DtWaterdataDallVo> selectDtWaterdataDallVoList(String starttime, String endtime,int start,int pagesize)
    {

        return dtWaterdataDMapper.selectDtWaterdataDallVoList(starttime,endtime,start,pagesize);
    }

    /**
     * 新增二类数据
     *
     * @param dtWaterdataD 二类数据
     * @return 结果
     */
    @Transactional
    @Override
    public int insertDtWaterdataD(DtWaterdataD dtWaterdataD)
    {
        int rows = dtWaterdataDMapper.insertDtWaterdataD(dtWaterdataD);
        insertXj56swzJixy(dtWaterdataD);
        return rows;
    }

    /**
     * 修改二类数据
     *
     * @param dtWaterdataD 二类数据
     * @return 结果
     */
    @Transactional
    @Override
    public int updateDtWaterdataD(DtWaterdataD dtWaterdataD)
    {
        dtWaterdataDMapper.deleteXj56swzJixyBySTID(dtWaterdataD.getWdid());
        insertXj56swzJixy(dtWaterdataD);
        return dtWaterdataDMapper.updateDtWaterdataD(dtWaterdataD);
    }

    /**
     * 批量删除二类数据
     *
     * @param wdids 需要删除的二类数据主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteDtWaterdataDByWdids(Long[] wdids)
    {
        dtWaterdataDMapper.deleteXj56swzJixyBySTIDs(wdids);
        return dtWaterdataDMapper.deleteDtWaterdataDByWdids(wdids);
    }

    /**
     * 删除二类数据信息
     *
     * @param wdid 二类数据主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteDtWaterdataDByWdid(Long wdid)
    {
        dtWaterdataDMapper.deleteXj56swzJixyBySTID(wdid);
        return dtWaterdataDMapper.deleteDtWaterdataDByWdid(wdid);
    }

    /**
     * 新增二类标记信息
     *
     * @param dtWaterdataD 二类数据对象
     */
    public void insertXj56swzJixy(DtWaterdataD dtWaterdataD)
    {
        List<Xj56swzJixy> xj56swzJixyList = dtWaterdataD.getXj56swzJixyList();
        Long wdid = dtWaterdataD.getWdid();
        if (StringUtils.isNotNull(xj56swzJixyList))
        {
            List<Xj56swzJixy> list = new ArrayList<Xj56swzJixy>();
            for (Xj56swzJixy xj56swzJixy : xj56swzJixyList)
            {
                xj56swzJixy.setStid(wdid);
                list.add(xj56swzJixy);
            }
            if (list.size() > 0)
            {
                dtWaterdataDMapper.batchXj56swzJixy(list);
            }
        }
    }
}
