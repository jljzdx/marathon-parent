package com.newera.marathon.mq.producer;

import com.newera.marathon.mq.config.QueueDemoConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
@Slf4j
public class FanoutDemoProducer extends BaseProducer
{
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void fanoutProducer()
    {
        String messageId = String.valueOf(UUID.randomUUID());
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        Map<String, Object> map = new HashMap<>();
        map.put("messageData", "message: testFanoutMessage");
        map.put("createTime", createTime);
        //用于发送回调的消息ID
        CorrelationData correlationId=new CorrelationData(messageId);
        //发送
        rabbitTemplate.convertAndSend(QueueDemoConfig.EXCHANGE_FANOUT_KAIFENG, null, map, new MessagePostProcessor(){
            @Override
            public Message postProcessMessage(Message message) throws AmqpException
            {
                message.getMessageProperties().setHeader("fanout", "fanout-header");
                message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT);
                message.getMessageProperties().setMessageId(messageId);
                return message;
            }
        }, correlationId);
    }
}
