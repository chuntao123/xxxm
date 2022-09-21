package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;

import java.math.BigDecimal;
import java.util.Date;

public class DtWaterdataDallVo {
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


    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private BigDecimal z6h;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private BigDecimal z12h;

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

    public BigDecimal getZ6h() {
        return z6h;
    }

    public void setZ6h(BigDecimal z6h) {
        this.z6h = z6h;
    }

    public BigDecimal getZ12h() {
        return z12h;
    }

    public void setZ12h(BigDecimal z12h) {
        this.z12h = z12h;
    }

    @Override
    public String toString() {
        return "DtWaterdataDallVo{" +
                "stid='" + stid + '\'' +
                ", datatm=" + datatm +
                ", z=" + z +
                ", z6h=" + z6h +
                ", z12h=" + z12h +
                '}';
    }
}
