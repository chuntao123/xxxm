package com.ruoyi.system.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TableDataVO {
    public List<SwjLedVO> list;
    public Integer tableSize;
}
