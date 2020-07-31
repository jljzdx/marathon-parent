package com.newera.marathon.mq.producer;

import com.newera.marathon.common.utils.RandomUtil;
import com.newera.marathon.mq.config.QueueDemoConfig;
import com.newera.marathon.mq.pojo.MailSend;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DirectDemoProducer extends BaseProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void directProducer() {
        MailSend mailSend = new MailSend();
        //生成消息ID
        String msgId = RandomUtil.UUID32();
        mailSend.setMsgId(msgId);
        mailSend.setToMail("9960413141@qq.com");
        mailSend.setContent("content");
        mailSend.setSubject("subject");
        mailSend.setType(1);
        mailSend.setCc("cc");

        //用于发送回调的消息ID
        CorrelationData correlationData = new CorrelationData(msgId);
        //发送
        rabbitTemplate.convertAndSend(QueueDemoConfig.DIRECT_EXCHANGE, QueueDemoConfig.DIRECT_ROUTING_KEY, mailSend, new MessagePostProcessor(){

            @Override
            public Message postProcessMessage(Message message) throws AmqpException
            {
                message.getMessageProperties().setHeader("direct", "direct-header");
                // 消息持久化，当我们对性能要求比较高的情况下，可以设置为NON_PERSISTENT（不持久化），可以接受丢失一些数据
                message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
                message.getMessageProperties().setMessageId(msgId);
                return message;
            }
        }, correlationData);
    }
}
