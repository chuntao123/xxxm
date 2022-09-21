package com.ruoyi.system.domain;
import java.math.BigDecimal;

public class SwjLedVO extends SwjLed implements Comparable<SwjLedVO> {

    public Boolean getOutOfLimit() { // 是否超过警戒水位
        if (this.warn == null) {
            return false;
        }
        return this.warn.compareTo(BigDecimal.ZERO) > 0;
    }

    @Override
    public int compareTo(SwjLedVO other) {
        if (other == null) {
            return -1;
        }
        if (getOutOfLimit()) {
            if (other.getOutOfLimit()) {
                return compareSeqNo(other);
            }
            return -1;
        } else {
            if (other.getOutOfLimit()) {
                return 1;
            }
            return compareSeqNo(other);
        }
    }

    private int compareSeqNo(SwjLedVO other) {
        if (this.seqNo != null && other.seqNo != null) {
            return this.seqNo.compareTo(other.seqNo);
        } else {
            if (other.seqNo == null) {
                return -1;
            }
        }
        return 1;
    }

}
