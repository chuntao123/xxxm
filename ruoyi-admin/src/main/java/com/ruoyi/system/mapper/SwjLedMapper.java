package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SwjLed;
import com.ruoyi.system.domain.SwjLedVO;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;

@Repository
public interface SwjLedMapper extends BaseMapper<SwjLed> {
    @Select("select * from swj_led")
    List<SwjLedVO> list();
}
