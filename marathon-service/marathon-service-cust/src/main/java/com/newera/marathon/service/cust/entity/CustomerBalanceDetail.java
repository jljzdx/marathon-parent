package com.newera.marathon.service.cust.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.newera.marathon.common.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 客户余额变动详情
 * </p>
 *
 * @author MicroBin
 * @since 2019-09-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("CUSTOMER_BALANCE_DETAIL")
public class CustomerBalanceDetail extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 客户主键
     */
    private Long customerId;

    /**
     * [1/2：订单/退货单]
     */
    private Integer source;

    /**
     * [相关单据ID]
     */
    private String sourceSn;

    /**
     * 0/1：支出/收入
     */
    private Integer flowWay;

    /**
     * 变动金额
     */
    private BigDecimal amount;

    /**
     * 剩余金额
     */
    private BigDecimal balance;


}
