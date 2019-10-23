package com.newera.marathon.mq.producer;

import com.newera.marathon.dto.cos.maintenance.XfaceCosMsgLogAdditionRequestDTO;
import com.newera.marathon.dto.cos.maintenance.XfaceCosMsgLogAdditionResponseDTO;
import com.newera.marathon.microface.cos.CosMsgLogMicroService;
import com.newera.marathon.mq.config.RabbitConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class BaseProducer {

    @Autowired
    private CosMsgLogMicroService cosMsgLogMicroService;

    public void msgLogAddition(String msgId, String body){
        //消息入库
        XfaceCosMsgLogAdditionRequestDTO msgLogAdditionRequestDTO = new XfaceCosMsgLogAdditionRequestDTO();
        msgLogAdditionRequestDTO.setMsgId(msgId);
        msgLogAdditionRequestDTO.setMsgBody(body);
        msgLogAdditionRequestDTO.setExchange(RabbitConfig.MAIL_EXCHANGE);
        msgLogAdditionRequestDTO.setRoutingKey(RabbitConfig.MAIL_ROUTING_KEY);
        XfaceCosMsgLogAdditionResponseDTO msgLogAdditionResponseDTO = cosMsgLogMicroService.msgLogAddition(msgLogAdditionRequestDTO);
        if (!msgLogAdditionResponseDTO.getTransactionStatus().isSuccess()) {
            log.error("消息入库失败：{}",msgLogAdditionResponseDTO.getTransactionStatus().getReplyText());
        }
    }
}
