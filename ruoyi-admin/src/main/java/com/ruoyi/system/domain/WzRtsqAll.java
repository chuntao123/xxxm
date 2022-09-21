package com.ruoyi.system.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * VIEW对象 wz_rtsq_all
 *
 * @author wkf
 * @date 2022-07-27
 */
public class WzRtsqAll extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String summary;

    /** 站点ID */
    @Excel(name = "站点ID")
    private Long csid;

    /** 站点名字 */
    @Excel(name = "站点名字")
    private String csname;

    /** 值 */
    @Excel(name = "值")
    private BigDecimal bssw;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date bsswtime;

    /** 值 */
    @Excel(name = "值")
    private BigDecimal bxsw;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date bxswtime;

    /** 值 */
    @Excel(name = "值")
    private BigDecimal ckll;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date cklltime;

    /** 值 */
    @Excel(name = "值")
    private BigDecimal rkll;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date rklltime;

    /** 值 */
    @Excel(name = "值")
    private BigDecimal qcyg;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date qcygtime;

    /** $table.subTable.functionName信息 */
    private List<WzCs> wzCsList;

    public void setSummary(String summary)
    {
        this.summary = summary;
    }

    public String getSummary()
    {
        return summary;
    }
    public void setCsid(Long csid)
    {
        this.csid = csid;
    }

    public Long getCsid()
    {
        return csid;
    }
    public void setCsname(String csname)
    {
        this.csname = csname;
    }

    public String getCsname()
    {
        return csname;
    }
    public void setBssw(BigDecimal bssw)
    {
        this.bssw = bssw;
    }

    public BigDecimal getBssw()
    {
        return bssw;
    }
    public void setBsswtime(Date bsswtime)
    {
        this.bsswtime = bsswtime;
    }

    public Date getBsswtime()
    {
        return bsswtime;
    }
    public void setBxsw(BigDecimal bxsw)
    {
        this.bxsw = bxsw;
    }

    public BigDecimal getBxsw()
    {
        return bxsw;
    }
    public void setBxswtime(Date bxswtime)
    {
        this.bxswtime = bxswtime;
    }

    public Date getBxswtime()
    {
        return bxswtime;
    }
    public void setCkll(BigDecimal ckll)
    {
        this.ckll = ckll;
    }

    public BigDecimal getCkll()
    {
        return ckll;
    }
    public void setCklltime(Date cklltime)
    {
        this.cklltime = cklltime;
    }

    public Date getCklltime()
    {
        return cklltime;
    }
    public void setRkll(BigDecimal rkll)
    {
        this.rkll = rkll;
    }

    public BigDecimal getRkll()
    {
        return rkll;
    }
    public void setRklltime(Date rklltime)
    {
        this.rklltime = rklltime;
    }

    public Date getRklltime()
    {
        return rklltime;
    }
    public void setQcyg(BigDecimal qcyg)
    {
        this.qcyg = qcyg;
    }

    public BigDecimal getQcyg()
    {
        return qcyg;
    }
    public void setQcygtime(Date qcygtime)
    {
        this.qcygtime = qcygtime;
    }

    public Date getQcygtime()
    {
        return qcygtime;
    }

    public List<WzCs> getWzCsList()
    {
        return wzCsList;
    }

    public void setWzCsList(List<WzCs> wzCsList)
    {
        this.wzCsList = wzCsList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("summary", getSummary())
            .append("csid", getCsid())
            .append("csname", getCsname())
            .append("bssw", getBssw())
            .append("bsswtime", getBsswtime())
            .append("bxsw", getBxsw())
            .append("bxswtime", getBxswtime())
            .append("ckll", getCkll())
            .append("cklltime", getCklltime())
            .append("rkll", getRkll())
            .append("rklltime", getRklltime())
            .append("qcyg", getQcyg())
            .append("qcygtime", getQcygtime())
            .append("wzCsList", getWzCsList())
            .toString();
    }
}
