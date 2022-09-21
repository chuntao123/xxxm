package com.ruoyi.system.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 二类标记对象 xj56swz_jixy
 *
 * @author wkf
 * @date 2022-07-28
 */
public class Xj56swzJixy extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long stid;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String stnm;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private BigDecimal lgtd;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private BigDecimal lttd;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String rvnm;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String bsnm;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String hnnm;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String stadr;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String stadrShort;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String sttp;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String xj;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String swjUserList;

    /** $column.columnComment */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date waterDatatm;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private BigDecimal waterZ;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private BigDecimal waterQ;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private BigDecimal waterWptn;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private BigDecimal waterTmdelay;

    /** 警戒水位 */
    @Excel(name = "警戒水位")
    private BigDecimal waterWarn;

    /** $column.columnComment */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date rainDatatm;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private BigDecimal rainRfday;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private BigDecimal rainRfxun;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private BigDecimal rainRfmon;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private BigDecimal rainRf;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private BigDecimal rainRfmi;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private BigDecimal rainRfall;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private BigDecimal rainTmdelay;

    /** 警戒雨位 */
    @Excel(name = "警戒雨位")
    private BigDecimal rainWarn;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String bak1;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String bak2;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Integer seqNo;

    public void setStid(Long stid)
    {
        this.stid = stid;
    }

    public Long getStid()
    {
        return stid;
    }
    public void setStnm(String stnm)
    {
        this.stnm = stnm;
    }

    public String getStnm()
    {
        return stnm;
    }
    public void setLgtd(BigDecimal lgtd)
    {
        this.lgtd = lgtd;
    }

    public BigDecimal getLgtd()
    {
        return lgtd;
    }
    public void setLttd(BigDecimal lttd)
    {
        this.lttd = lttd;
    }

    public BigDecimal getLttd()
    {
        return lttd;
    }
    public void setRvnm(String rvnm)
    {
        this.rvnm = rvnm;
    }

    public String getRvnm()
    {
        return rvnm;
    }
    public void setBsnm(String bsnm)
    {
        this.bsnm = bsnm;
    }

    public String getBsnm()
    {
        return bsnm;
    }
    public void setHnnm(String hnnm)
    {
        this.hnnm = hnnm;
    }

    public String getHnnm()
    {
        return hnnm;
    }
    public void setStadr(String stadr)
    {
        this.stadr = stadr;
    }

    public String getStadr()
    {
        return stadr;
    }
    public void setStadrShort(String stadrShort)
    {
        this.stadrShort = stadrShort;
    }

    public String getStadrShort()
    {
        return stadrShort;
    }
    public void setSttp(String sttp)
    {
        this.sttp = sttp;
    }

    public String getSttp()
    {
        return sttp;
    }
    public void setXj(String xj)
    {
        this.xj = xj;
    }

    public String getXj()
    {
        return xj;
    }
    public void setSwjUserList(String swjUserList)
    {
        this.swjUserList = swjUserList;
    }

    public String getSwjUserList()
    {
        return swjUserList;
    }
    public void setWaterDatatm(Date waterDatatm)
    {
        this.waterDatatm = waterDatatm;
    }

    public Date getWaterDatatm()
    {
        return waterDatatm;
    }
    public void setWaterZ(BigDecimal waterZ)
    {
        this.waterZ = waterZ;
    }

    public BigDecimal getWaterZ()
    {
        return waterZ;
    }
    public void setWaterQ(BigDecimal waterQ)
    {
        this.waterQ = waterQ;
    }

    public BigDecimal getWaterQ()
    {
        return waterQ;
    }
    public void setWaterWptn(BigDecimal waterWptn)
    {
        this.waterWptn = waterWptn;
    }

    public BigDecimal getWaterWptn()
    {
        return waterWptn;
    }
    public void setWaterTmdelay(BigDecimal waterTmdelay)
    {
        this.waterTmdelay = waterTmdelay;
    }

    public BigDecimal getWaterTmdelay()
    {
        return waterTmdelay;
    }
    public void setWaterWarn(BigDecimal waterWarn)
    {
        this.waterWarn = waterWarn;
    }

    public BigDecimal getWaterWarn()
    {
        return waterWarn;
    }
    public void setRainDatatm(Date rainDatatm)
    {
        this.rainDatatm = rainDatatm;
    }

    public Date getRainDatatm()
    {
        return rainDatatm;
    }
    public void setRainRfday(BigDecimal rainRfday)
    {
        this.rainRfday = rainRfday;
    }

    public BigDecimal getRainRfday()
    {
        return rainRfday;
    }
    public void setRainRfxun(BigDecimal rainRfxun)
    {
        this.rainRfxun = rainRfxun;
    }

    public BigDecimal getRainRfxun()
    {
        return rainRfxun;
    }
    public void setRainRfmon(BigDecimal rainRfmon)
    {
        this.rainRfmon = rainRfmon;
    }

    public BigDecimal getRainRfmon()
    {
        return rainRfmon;
    }
    public void setRainRf(BigDecimal rainRf)
    {
        this.rainRf = rainRf;
    }

    public BigDecimal getRainRf()
    {
        return rainRf;
    }
    public void setRainRfmi(BigDecimal rainRfmi)
    {
        this.rainRfmi = rainRfmi;
    }

    public BigDecimal getRainRfmi()
    {
        return rainRfmi;
    }
    public void setRainRfall(BigDecimal rainRfall)
    {
        this.rainRfall = rainRfall;
    }

    public BigDecimal getRainRfall()
    {
        return rainRfall;
    }
    public void setRainTmdelay(BigDecimal rainTmdelay)
    {
        this.rainTmdelay = rainTmdelay;
    }

    public BigDecimal getRainTmdelay()
    {
        return rainTmdelay;
    }
    public void setRainWarn(BigDecimal rainWarn)
    {
        this.rainWarn = rainWarn;
    }

    public BigDecimal getRainWarn()
    {
        return rainWarn;
    }
    public void setBak1(String bak1)
    {
        this.bak1 = bak1;
    }

    public String getBak1()
    {
        return bak1;
    }
    public void setBak2(String bak2)
    {
        this.bak2 = bak2;
    }

    public String getBak2()
    {
        return bak2;
    }
    public void setSeqNo(Integer seqNo)
    {
        this.seqNo = seqNo;
    }

    public Integer getSeqNo()
    {
        return seqNo;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("stid", getStid())
            .append("stnm", getStnm())
            .append("lgtd", getLgtd())
            .append("lttd", getLttd())
            .append("rvnm", getRvnm())
            .append("bsnm", getBsnm())
            .append("hnnm", getHnnm())
            .append("stadr", getStadr())
            .append("stadrShort", getStadrShort())
            .append("sttp", getSttp())
            .append("xj", getXj())
            .append("swjUserList", getSwjUserList())
            .append("waterDatatm", getWaterDatatm())
            .append("waterZ", getWaterZ())
            .append("waterQ", getWaterQ())
            .append("waterWptn", getWaterWptn())
            .append("waterTmdelay", getWaterTmdelay())
            .append("waterWarn", getWaterWarn())
            .append("rainDatatm", getRainDatatm())
            .append("rainRfday", getRainRfday())
            .append("rainRfxun", getRainRfxun())
            .append("rainRfmon", getRainRfmon())
            .append("rainRf", getRainRf())
            .append("rainRfmi", getRainRfmi())
            .append("rainRfall", getRainRfall())
            .append("rainTmdelay", getRainTmdelay())
            .append("rainWarn", getRainWarn())
            .append("bak1", getBak1())
            .append("bak2", getBak2())
            .append("seqNo", getSeqNo())
            .toString();
    }
}
