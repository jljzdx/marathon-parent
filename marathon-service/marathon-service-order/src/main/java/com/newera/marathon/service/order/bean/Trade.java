package com.newera.marathon.service.order.bean;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

//@Document(indexName = "trade", shards = 3, replicas = 1)
@Builder
@Data
public class Trade {

    private String orderNb;

    private BigDecimal amount;

    //@Field(analyzer = "hanlp", type = FieldType.Text)
    private String pName;

    private String createTime;

    //@Field(analyzer = "hanlp", type = FieldType.Text)
    private String remark;

    private Integer status;
}
