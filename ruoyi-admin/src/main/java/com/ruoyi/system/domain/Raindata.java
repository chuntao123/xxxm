package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

public class Raindata {
    public BigDecimal stid;
    public String stnm;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date rainDatatm;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date datatm;
    public BigDecimal rf;
    public BigDecimal rfmi;
    public BigDecimal rfall;
    public BigDecimal tmdelay;

    public BigDecimal getStid() {
        return stid;
    }

    public void setStid(BigDecimal stid) {
        this.stid = stid;
    }

    public String getStnm() {
        return stnm;
    }

    public void setStnm(String stnm) {
        this.stnm = stnm;
    }

    public Date getRainDatatm() {
        return rainDatatm;
    }

    public void setRainDatatm(Date rainDatatm) {
        this.rainDatatm = rainDatatm;
    }

    public Date getDatatm() {
        return datatm;
    }

    public void setDatatm(Date datatm) {
        this.datatm = datatm;
    }

    public BigDecimal getRf() {
        return rf;
    }

    public void setRf(BigDecimal rf) {
        this.rf = rf;
    }

    public BigDecimal getRfmi() {
        return rfmi;
    }

    public void setRfmi(BigDecimal rfmi) {
        this.rfmi = rfmi;
    }

    public BigDecimal getRfall() {
        return rfall;
    }

    public void setRfall(BigDecimal rfall) {
        this.rfall = rfall;
    }

    public BigDecimal getTmdelay() {
        return tmdelay;
    }

    public void setTmdelay(BigDecimal tmdelay) {
        this.tmdelay = tmdelay;
    }

    @Override
    public String toString() {
        return "Raindata{" +
                "stid=" + stid +
                ", stnm='" + stnm + '\'' +
                ", rainDatatm=" + rainDatatm +
                ", datatm=" + datatm +
                ", rf=" + rf +
                ", rfmi=" + rfmi +
                ", rfall=" + rfall +
                ", tmdelay=" + tmdelay +
                '}';
    }
}
