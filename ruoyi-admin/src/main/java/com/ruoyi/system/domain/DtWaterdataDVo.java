package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;

import java.math.BigDecimal;
import java.util.Date;

public class DtWaterdataDVo {

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String stid;

    /** $column.columnComment */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date datatm;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private BigDecimal z;

    public String getStid() {
        return stid;
    }

    public void setStid(String stid) {
        this.stid = stid;
    }

    public Date getDatatm() {
        return datatm;
    }

    public void setDatatm(Date datatm) {
        this.datatm = datatm;
    }

    public BigDecimal getZ() {
        return z;
    }

    public void setZ(BigDecimal z) {
        this.z = z;
    }

    @Override
    public String toString() {
        return "DtWaterdataDVo{" +
                "stid='" + stid + '\'' +
                ", datatm=" + datatm +
                ", z=" + z +
                '}';
    }
}
