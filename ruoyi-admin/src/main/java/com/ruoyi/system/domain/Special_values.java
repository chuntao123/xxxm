package com.ruoyi.system.domain;

import java.math.BigDecimal;

public class Special_values {
    private String senid;
    private BigDecimal max;
    private BigDecimal min;
    private BigDecimal avg;
    private BigDecimal total;

    public String getSenid() {
        return senid;
    }

    public void setSenid(String senid) {
        this.senid = senid;
    }

    public BigDecimal getMax() {
        return max;
    }

    public void setMax(BigDecimal max) {
        this.max = max;
    }

    public BigDecimal getMin() {
        return min;
    }

    public void setMin(BigDecimal min) {
        this.min = min;
    }

    public BigDecimal getAvg() {
        return avg;
    }

    public void setAvg(BigDecimal avg) {
        this.avg = avg;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Special_values{" +
                "senid='" + senid + '\'' +
                ", max=" + max +
                ", min=" + min +
                ", avg=" + avg +
                ", total=" + total +
                '}';
    }
}
