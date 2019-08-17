package com.newera.marathon.common.model;

import lombok.Data;

import java.util.Date;

@Data
public class BaseEntity {

    /**
     * 创建时间
     */
    public Date gmtCreate;

    /**
     * 创建人
     */
    public String createOperator;

    /**
     * 更新时间
     */
    public Date gmtModify;

    /**
     * 更新人
     */
    public String modifyOperator;
}
