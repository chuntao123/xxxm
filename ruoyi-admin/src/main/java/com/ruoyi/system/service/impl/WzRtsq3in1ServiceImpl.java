package com.ruoyi.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.system.domain.Special_values;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.WzRtsq3in1Mapper;
import com.ruoyi.system.domain.WzRtsq3in1;
import com.ruoyi.system.service.IWzRtsq3in1Service;

/**
 * rtsqService业务层处理
 *
 * @author wkf
 * @date 2022-07-29
 */
@Service
public class WzRtsq3in1ServiceImpl implements IWzRtsq3in1Service
{
    @Autowired
    private WzRtsq3in1Mapper wzRtsq3in1Mapper;
    /**
     * 查询rtsq
     *
     * @param senid rtsq主键
     * @return rtsq
     */
    @Override
    public WzRtsq3in1 selectWzRtsq3in1BySenid(Long senid)
    {
        return wzRtsq3in1Mapper.selectWzRtsq3in1BySenid(senid);
    }

    /**
     * 查询菜单
     */
    @Override
    public List<WzRtsq3in1> selectWzRtsq3in1menuList()
    {
        return wzRtsq3in1Mapper.selectWzRtsq3in1menuList();
    }


    /**
     * 查询报表特殊值
     */
    @Override
    public List<Special_values> selectWzRtsq3in1specialList(List zd, String starttime, String endtime){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("item", zd);
        map.put("starttime",starttime);
        map.put("endtime", endtime);

        return wzRtsq3in1Mapper.selectWzRtsq3in1specialList(map);
    }


    /**
     * 查询报表数据
     */
    @Override
    public List<Map<String,Object>> selectWzRtsq3in1bbList(String url){


        return wzRtsq3in1Mapper.selectWzRtsq3in1bbList(url);
    }
    /**
     * 查询rtsq列表
     *
     * @param wzRtsq3in1 rtsq
     * @return rtsq
     */
    @Override
    public JSONObject selectWzRtsq3in1List(int csid, String time)
    {
        JSONObject jsonObject=new JSONObject();
        List<WzRtsq3in1> wzRtsq3in1s = wzRtsq3in1Mapper.selectWzRtsq3in1List(csid,time);
        Map<String, List<WzRtsq3in1>> map = wzRtsq3in1s.stream().collect(Collectors.groupingBy(WzRtsq3in1::getSenname));
        map.forEach((key,val)->{
            if (key.contains("坝上水位")){
                jsonObject.put("坝上水位",val);
            }
            if (key.contains("坝下水位")){
                jsonObject.put("坝下水位",val);
            }
            if (key.contains("出库流量")){
                jsonObject.put("出库流量",val);
            }
            if (key.contains("入库流量")){
                jsonObject.put("入库流量",val);
            }
            if (key.contains("全厂有功")){
                jsonObject.put("全厂有功",val);
            }

        });

        return jsonObject;
    }

    /**
     * 新增rtsq
     *
     * @param wzRtsq3in1 rtsq
     * @return 结果
     */
    @Override
    public int insertWzRtsq3in1(WzRtsq3in1 wzRtsq3in1)
    {
        return wzRtsq3in1Mapper.insertWzRtsq3in1(wzRtsq3in1);
    }

    /**
     * 修改rtsq
     *
     * @param wzRtsq3in1 rtsq
     * @return 结果
     */
    @Override
    public int updateWzRtsq3in1(WzRtsq3in1 wzRtsq3in1)
    {
        return wzRtsq3in1Mapper.updateWzRtsq3in1(wzRtsq3in1);
    }

    /**
     * 批量删除rtsq
     *
     * @param senids 需要删除的rtsq主键
     * @return 结果
     */
    @Override
    public int deleteWzRtsq3in1BySenids(Long[] senids)
    {
        return wzRtsq3in1Mapper.deleteWzRtsq3in1BySenids(senids);
    }

    /**
     * 删除rtsq信息
     *
     * @param senid rtsq主键
     * @return 结果
     */
    @Override
    public int deleteWzRtsq3in1BySenid(Long senid)
    {
        return wzRtsq3in1Mapper.deleteWzRtsq3in1BySenid(senid);
    }
}
