package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.Raindata;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RaindataMapper {
    @Select("select x.stnm,x.rain_datatm,d.DATATM,d.STID,d.RF,d.RFMI,d.RFALL,d.TMDELAY from xj56swz_jixy x INNER JOIN dt_raindata_d d on x.stid=d.STID where x.stid=#{arg0} and datatm>=#{arg1} order by datatm asc")
    List<Raindata> getraindata(int stid, String datatm);

}
