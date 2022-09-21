package com.ruoyi.system.mapper;

import org.apache.ibatis.annotations.Select;

public interface TotalMapper {
    @Select("select count(*) total from dt_waterdata_d WHERE DATATM BETWEEN #{arg0} AND #{arg1} AND STID = 10000003 AND extract(minute from DATATM) = 0")
    int total(String starttime,String endtime);
}
