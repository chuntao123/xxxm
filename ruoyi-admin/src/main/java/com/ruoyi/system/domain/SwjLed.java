package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
@Data
public class SwjLed {
    public String rvnm;
    public String stnm;
    @JsonFormat(pattern = "HH:mm:ss")
    public Date waterDatatm;
    public BigDecimal waterZ;
    public BigDecimal waterQ;
    public BigDecimal warn;
    public String stadrShort;
    public Integer seqNo;

}
