package com.newera.marathon.service.cms.mq.producer;

import com.alibaba.fastjson.JSON;
import com.newera.marathon.common.utils.RandomUtil;
import com.newera.marathon.dto.system.maintenance.XfaceMsgLogAdditionRequestDTO;
import com.newera.marathon.dto.system.maintenance.XfaceMsgLogAdditionResponseDTO;
import com.newera.marathon.microface.cos.MsgLogMicroService;
import com.newera.marathon.mq.config.RabbitConfig;
import com.newera.marathon.service.cms.mq.pojo.MailSend;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MailSendProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private MsgLogMicroService msgLogMicroService;

    public void send(MailSend mailSend) {
        //生成消息ID
        String msgId = RandomUtil.UUID32();
        mailSend.setMsgId(msgId);
        //消息体
        String body = JSON.toJSONString(mailSend);
        log.info("【邮件队列生产者】消息体：{}",body);
        //消息入库
        XfaceMsgLogAdditionRequestDTO msgLogAdditionRequestDTO = new XfaceMsgLogAdditionRequestDTO();
        msgLogAdditionRequestDTO.setMsgId(msgId);
        msgLogAdditionRequestDTO.setMsgBody(body);
        msgLogAdditionRequestDTO.setExchange(RabbitConfig.MAIL_EXCHANGE);
        msgLogAdditionRequestDTO.setRoutingKey(RabbitConfig.MAIL_ROUTING_KEY);
        XfaceMsgLogAdditionResponseDTO msgLogAdditionResponseDTO = msgLogMicroService.msgLogAddition(msgLogAdditionRequestDTO);
        if (!msgLogAdditionResponseDTO.getTransactionStatus().isSuccess()) {
            log.error("消息入库失败：{}",msgLogAdditionResponseDTO.getTransactionStatus().getReplyText());
        }

        Message message = MessageBuilder.withBody(body.getBytes()).build();
        message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);// 消息持久化
        message.getMessageProperties().setContentType(MessageProperties.CONTENT_TYPE_JSON);
        //消息唯一ID
        CorrelationData correlationData = new CorrelationData(mailSend.getMsgId());
        //指定交换机，指定routing key，发送消息
        rabbitTemplate.convertAndSend(RabbitConfig.MAIL_EXCHANGE, RabbitConfig.MAIL_ROUTING_KEY, message, correlationData);
    }
}
