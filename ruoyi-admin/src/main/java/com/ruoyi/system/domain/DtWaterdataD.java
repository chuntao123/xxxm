package com.ruoyi.system.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;


/**
 * 二类数据对象 dt_waterdata_d
 *
 * @author wkf
 * @date 2022-07-30
 */
public class DtWaterdataD
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long wdid;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String stid;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long omid;

    /** $column.columnComment */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date datatm;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private BigDecimal z;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private BigDecimal q;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String wptn;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String msqmt;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private BigDecimal tmdelay;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String syncfl;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String otherdata;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String errmsg;

    /** $column.columnComment */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date createtm;

    /** $column.columnComment */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Date moditm;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String ishuman;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private BigDecimal xsmxv;

    /** 二类标记信息 */
    private List<Xj56swzJixy> xj56swzJixyList;

    public void setWdid(Long wdid)
    {
        this.wdid = wdid;
    }

    public Long getWdid()
    {
        return wdid;
    }
    public void setStid(String stid)
    {
        this.stid = stid;
    }

    public String getStid()
    {
        return stid;
    }
    public void setOmid(Long omid)
    {
        this.omid = omid;
    }

    public Long getOmid()
    {
        return omid;
    }
    public void setDatatm(Date datatm)
    {
        this.datatm = datatm;
    }

    public Date getDatatm()
    {
        return datatm;
    }
    public void setZ(BigDecimal z)
    {
        this.z = z;
    }

    public BigDecimal getZ()
    {
        return z;
    }
    public void setQ(BigDecimal q)
    {
        this.q = q;
    }

    public BigDecimal getQ()
    {
        return q;
    }
    public void setWptn(String wptn)
    {
        this.wptn = wptn;
    }

    public String getWptn()
    {
        return wptn;
    }
    public void setMsqmt(String msqmt)
    {
        this.msqmt = msqmt;
    }

    public String getMsqmt()
    {
        return msqmt;
    }
    public void setTmdelay(BigDecimal tmdelay)
    {
        this.tmdelay = tmdelay;
    }

    public BigDecimal getTmdelay()
    {
        return tmdelay;
    }
    public void setSyncfl(String syncfl)
    {
        this.syncfl = syncfl;
    }

    public String getSyncfl()
    {
        return syncfl;
    }
    public void setOtherdata(String otherdata)
    {
        this.otherdata = otherdata;
    }

    public String getOtherdata()
    {
        return otherdata;
    }
    public void setErrmsg(String errmsg)
    {
        this.errmsg = errmsg;
    }

    public String getErrmsg()
    {
        return errmsg;
    }
    public void setCreatetm(Date createtm)
    {
        this.createtm = createtm;
    }

    public Date getCreatetm()
    {
        return createtm;
    }
    public void setModitm(Date moditm)
    {
        this.moditm = moditm;
    }

    public Date getModitm()
    {
        return moditm;
    }
    public void setIshuman(String ishuman)
    {
        this.ishuman = ishuman;
    }

    public String getIshuman()
    {
        return ishuman;
    }
    public void setXsmxv(BigDecimal xsmxv)
    {
        this.xsmxv = xsmxv;
    }

    public BigDecimal getXsmxv()
    {
        return xsmxv;
    }

    public List<Xj56swzJixy> getXj56swzJixyList()
    {
        return xj56swzJixyList;
    }

    public void setXj56swzJixyList(List<Xj56swzJixy> xj56swzJixyList)
    {
        this.xj56swzJixyList = xj56swzJixyList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("wdid", getWdid())
            .append("stid", getStid())
            .append("omid", getOmid())
            .append("datatm", getDatatm())
            .append("z", getZ())
            .append("q", getQ())
            .append("wptn", getWptn())
            .append("msqmt", getMsqmt())
            .append("tmdelay", getTmdelay())
            .append("syncfl", getSyncfl())
            .append("otherdata", getOtherdata())
            .append("errmsg", getErrmsg())
            .append("createtm", getCreatetm())
            .append("moditm", getModitm())
            .append("ishuman", getIshuman())
            .append("xsmxv", getXsmxv())
            .append("xj56swzJixyList", getXj56swzJixyList())
            .toString();
    }
}
