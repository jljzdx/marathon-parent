package com.newera.marathon.mq.consumer;

import com.newera.marathon.mq.config.QueueDemoConfig;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class DirectDemoConsumer extends BaseConsumer {


    @RabbitListener(queues = QueueDemoConfig.DIRECT_QUEUE)
    public void receive(@Payload String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag, @Header(AmqpHeaders.REDELIVERED) boolean reDelivered) throws IOException {
        log.info("【测试队列消费者】消息体: {}", message);
        //MailSend mailSend = JSONObject.parseObject(message, MailSend.class);
        channel.basicAck(tag, false);
    }
}
