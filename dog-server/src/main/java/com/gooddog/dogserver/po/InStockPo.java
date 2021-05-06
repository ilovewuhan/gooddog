package com.gooddog.dogserver.po;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import lombok.ToString;

/**
 * @author zlh
 * @date 2021/4/28 14:33
 * @description
 */
@Data
@ToString
public class InStockPo {

    @Excel(name = "商品名称")
    private String drugName;

    @Excel(name = "生产厂商（西药/中成药时必填）")
    private String drugManu;

    @Excel(name = "规格")
    private String drugSpec;
}
