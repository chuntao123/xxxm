package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * ${subTable.functionName}对象 wz_cs
 *
 * @author wkf
 * @date 2022-07-27
 */
public class WzCs extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 站点ID */
    private String csid;

    /** 站点名字 */
    @Excel(name = "站点名字")
    private String csname;

    /** 经度 */
    @Excel(name = "经度")
    private String ceslo;

    /** 纬度 */
    @Excel(name = "纬度")
    private String cntla;

    /** 状态 */
    @Excel(name = "状态")
    private Long state;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String summary;

    public void setCsid(String csid)
    {
        this.csid = csid;
    }

    public String getCsid()
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
    public void setCeslo(String ceslo)
    {
        this.ceslo = ceslo;
    }

    public String getCeslo()
    {
        return ceslo;
    }
    public void setCntla(String cntla)
    {
        this.cntla = cntla;
    }

    public String getCntla()
    {
        return cntla;
    }
    public void setState(Long state)
    {
        this.state = state;
    }

    public Long getState()
    {
        return state;
    }
    public void setSummary(String summary)
    {
        this.summary = summary;
    }

    public String getSummary()
    {
        return summary;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("csid", getCsid())
            .append("csname", getCsname())
            .append("ceslo", getCeslo())
            .append("cntla", getCntla())
            .append("state", getState())
            .append("summary", getSummary())
            .toString();
    }
}
