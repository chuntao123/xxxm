package com.ruoyi.system.service;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.system.domain.Special_values;
import com.ruoyi.system.domain.WzRtsq3in1;

/**
 * rtsqService接口
 *
 * @author wkf
 * @date 2022-07-29
 */
public interface IWzRtsq3in1Service
{
    /**
     * 查询rtsq
     *
     * @param senid rtsq主键
     * @return rtsq
     */
    public WzRtsq3in1 selectWzRtsq3in1BySenid(Long senid);

    /**
     * 查询rtsq列表
     *
     * @param wzRtsq3in1 rtsq
     * @return rtsq集合
     */
    public JSONObject selectWzRtsq3in1List(int csid, String time);

    /**
     * 查询菜单列表
     */
    public List<WzRtsq3in1> selectWzRtsq3in1menuList();

    /**
     * 查询报表特殊值
     */
    public List<Special_values> selectWzRtsq3in1specialList(List zd, String starttime, String endtime);


    /**
     * 查询报表数据
     */
    public List<Map<String,Object>> selectWzRtsq3in1bbList(String url);


    /**
     * 新增rtsq
     *
     * @param wzRtsq3in1 rtsq
     * @return 结果
     */
    public int insertWzRtsq3in1(WzRtsq3in1 wzRtsq3in1);

    /**
     * 修改rtsq
     *
     * @param wzRtsq3in1 rtsq
     * @return 结果
     */
    public int updateWzRtsq3in1(WzRtsq3in1 wzRtsq3in1);

    /**
     * 批量删除rtsq
     *
     * @param senids 需要删除的rtsq主键集合
     * @return 结果
     */
    public int deleteWzRtsq3in1BySenids(Long[] senids);

    /**
     * 删除rtsq信息
     *
     * @param senid rtsq主键
     * @return 结果
     */
    public int deleteWzRtsq3in1BySenid(Long senid);
}
