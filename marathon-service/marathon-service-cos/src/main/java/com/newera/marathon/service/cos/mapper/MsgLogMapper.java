package com.newera.marathon.service.cos.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.newera.marathon.service.cos.entity.MsgLog;

/**
 * <p>
 * MQ消息日志，用来确保消息100%投递成功 Mapper 接口
 * </p>
 *
 * @author MicroBin
 * @since 2019-10-15
 */
public interface MsgLogMapper extends BaseMapper<MsgLog> {

}
