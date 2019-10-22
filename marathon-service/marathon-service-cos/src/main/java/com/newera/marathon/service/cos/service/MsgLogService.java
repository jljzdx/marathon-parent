package com.newera.marathon.service.cos.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.newera.marathon.dto.cos.inquiry.XfaceCosMsgLogListInquiryRequestDTO;
import com.newera.marathon.dto.cos.inquiry.XfaceCosMsgLogListInquiryResponseDTO;
import com.newera.marathon.dto.cos.maintenance.XfaceCosMsgLogAdditionRequestDTO;
import com.newera.marathon.dto.cos.maintenance.XfaceCosMsgLogAdditionResponseDTO;
import com.newera.marathon.dto.cos.maintenance.XfaceCosMsgLogModifyRequestDTO;
import com.newera.marathon.dto.cos.maintenance.XfaceCosMsgLogModifyResponseDTO;
import com.newera.marathon.service.cos.entity.MsgLog;

/**
 * <p>
 * MQ消息日志 服务类
 * </p>
 *
 * @author MicroBin
 * @since 2019-10-15
 */
public interface MsgLogService extends IService<MsgLog> {

    XfaceCosMsgLogListInquiryResponseDTO doMsgLogListInquiry(XfaceCosMsgLogListInquiryRequestDTO requestDTO);

    XfaceCosMsgLogAdditionResponseDTO doMsgLogAddition(XfaceCosMsgLogAdditionRequestDTO requestDTO);

    XfaceCosMsgLogModifyResponseDTO doMsgLogModify(XfaceCosMsgLogModifyRequestDTO requestDTO);
}
