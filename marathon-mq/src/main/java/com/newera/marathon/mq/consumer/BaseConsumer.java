package com.newera.marathon.mq.consumer;

import com.newera.marathon.dto.cos.inquiry.XfaceCosMsgLogListInquiryRequestDTO;
import com.newera.marathon.dto.cos.inquiry.XfaceCosMsgLogListInquiryResponseDTO;
import com.newera.marathon.dto.cos.inquiry.XfaceCosMsgLogListInquiryResponseSubDTO;
import com.newera.marathon.dto.cos.maintenance.XfaceCosMsgLogModifyRequestDTO;
import com.newera.marathon.dto.cos.maintenance.XfaceCosMsgLogModifyResponseDTO;
import com.newera.marathon.microface.cos.CosMsgLogMicroService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
public class BaseConsumer {

    @Autowired
    private CosMsgLogMicroService cosMsgLogMicroService;

    /**
     * 更新状态为已消费
     * @param msgId
     */
    public void msgLogModify(String msgId){
        XfaceCosMsgLogModifyRequestDTO msgLogModifyRequestDTO = new XfaceCosMsgLogModifyRequestDTO();
        msgLogModifyRequestDTO.setMsgId(msgId);
        msgLogModifyRequestDTO.setStatus(4);
        XfaceCosMsgLogModifyResponseDTO msgLogModifyResponseDTO = cosMsgLogMicroService.msgLogModify(msgLogModifyRequestDTO);
        if(!msgLogModifyResponseDTO.getTransactionStatus().isSuccess()){
            log.error("【邮件队列消费者】：更新状态为'已消费'失败");
        }
    }

    /**
     * 幂等性判断
     * @param msgId
     * @return
     */
    public boolean idempotent(String msgId){

        XfaceCosMsgLogListInquiryRequestDTO msgLogListInquiryRequestDTO = new XfaceCosMsgLogListInquiryRequestDTO();
        msgLogListInquiryRequestDTO.setMsgId(msgId);
        XfaceCosMsgLogListInquiryResponseDTO msgLogListInquiryResponseDTO = cosMsgLogMicroService.msgLogListInquiry(msgLogListInquiryRequestDTO);
        List<XfaceCosMsgLogListInquiryResponseSubDTO> responseSubDTOS = msgLogListInquiryResponseDTO.getDataList();
        if (null == responseSubDTOS || responseSubDTOS.size() != 1 || responseSubDTOS.get(0).getStatus() == 4) {
            log.error("【邮件队列消费者】幂等性问题，消息Id：{}", msgId);
            return false;
        }
        return true;
    }
}
