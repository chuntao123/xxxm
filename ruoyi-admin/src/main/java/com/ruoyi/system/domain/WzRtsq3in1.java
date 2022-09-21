package com.ruoyi.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;

/**
 * rtsq对象 wz_rtsq3in1
 *
 * @author wkf
 * @date 2022-07-29
 */
public class WzRtsq3in1
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long senid;

    /** $column.columnComment */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date time;


    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String csname;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String csid;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String senname;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private BigDecimal value;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCsname() {
        return csname;
    }

    public void setCsname(String csname) {
        this.csname = csname;
    }

    public String getCsid() {
        return csid;
    }

    public void setCsid(String csid) {
        this.csid = csid;
    }

    public String getSenname() {
        return senname;
    }

    public void setSenname(String senname) {
        this.senname = senname;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public void setSenid(Long senid)
    {
        this.senid = senid;
    }

    public Long getSenid()
    {
        return senid;
    }
    public void setTime(Date time)
    {
        this.time = time;
    }

    public Date getTime()
    {
        return time;
    }

//    @Override
//    public String toString() {
//        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
//            .append("senid", getSenid())
//            .append("time", getTime())
//            .append("v", getV())
//            .toString();
//    }


    @Override
    public String toString() {
        return "WzRtsq3in1{" +
                "senid=" + senid +
                ", time=" + time +
                ", csname='" + csname + '\'' +
                ", csid='" + csid + '\'' +
                ", senname='" + senname + '\'' +
                ", value=" + value +
                '}';
    }
}
