package com.newera.marathon.service.cos.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.newera.marathon.dto.system.inquiry.XfaceMsgLogListInquiryRequestDTO;
import com.newera.marathon.dto.system.inquiry.XfaceMsgLogListInquiryResponseDTO;
import com.newera.marathon.dto.system.maintenance.XfaceMsgLogAdditionRequestDTO;
import com.newera.marathon.dto.system.maintenance.XfaceMsgLogAdditionResponseDTO;
import com.newera.marathon.dto.system.maintenance.XfaceMsgLogModifyRequestDTO;
import com.newera.marathon.dto.system.maintenance.XfaceMsgLogModifyResponseDTO;
import com.newera.marathon.service.cos.entity.MsgLog;

/**
 * <p>
 * MQ消息日志，用来确保消息100%投递成功 服务类
 * </p>
 *
 * @author MicroBin
 * @since 2019-10-15
 */
public interface MsgLogService extends IService<MsgLog> {

    XfaceMsgLogListInquiryResponseDTO doMsgLogListInquiry(XfaceMsgLogListInquiryRequestDTO requestDTO);

    XfaceMsgLogAdditionResponseDTO doMsgLogAddition(XfaceMsgLogAdditionRequestDTO requestDTO);

    XfaceMsgLogModifyResponseDTO doMsgLogModify(XfaceMsgLogModifyRequestDTO requestDTO);
}
