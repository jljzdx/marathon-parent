package com.newera.marathon.service.cos.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.newera.marathon.common.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * MQ消息日志
 * </p>
 *
 * @author MicroBin
 * @since 2019-10-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("COS_MSG_LOG")
public class MsgLog extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 消息唯一标识
     */
    private String msgId;

    /**
     * 消息参数，json串
     */
    private String msgBody;

    /**
     * 交换机
     */
    private String exchange;

    /**
     * 路由键
     */
    private String routingKey;

    /**
     * 已重新投递次数
     */
    private Integer tryCount;

    /**
     * 1/2/3/4：投递中/投递成功/投递失败/已消费
     */
    private Integer status;


}
