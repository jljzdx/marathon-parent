package com.newera.marathon.mq.producer;

import com.alibaba.fastjson.JSON;
import com.newera.marathon.common.utils.RandomUtil;
import com.newera.marathon.mq.config.RabbitConfig;
import com.newera.marathon.mq.pojo.MailSend;
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
public class FanoutDemoProducer extends BaseProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendNews() {
        MailSend mailSend = new MailSend();
        //生成消息ID
        String msgId = RandomUtil.UUID32();
        mailSend.setMsgId(msgId);
        mailSend.setToMail("9960413141@qq.com");
        mailSend.setContent("content");
        mailSend.setSubject("subject");
        mailSend.setType(1);
        mailSend.setCc("cc");
        //消息体
        String body = JSON.toJSONString(mailSend);
        log.info("【测试队列生产者】消息体：{}",body);

        Message message = MessageBuilder.withBody(body.getBytes()).build();
        message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);// 消息持久化
        message.getMessageProperties().setContentType(MessageProperties.CONTENT_TYPE_JSON);
        //消息唯一ID
        CorrelationData correlationData = new CorrelationData(mailSend.getMsgId());
        //指定交换机，指定routing key，发送消息
        rabbitTemplate.convertAndSend(RabbitConfig.TEST_EXCHANGE, RabbitConfig.TEST_ROUTING_KEY, message, correlationData);
    }
}
