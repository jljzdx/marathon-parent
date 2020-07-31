package com.newera.marathon.mq.consumer;

import com.newera.marathon.mq.config.QueueDemoConfig;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class DirectDemoConsumer extends BaseConsumer
{

    /**
     * 总结：
     * basicReject和basicNack最大的区别：
     * 1、basicNack多了一个multiple（是否应用于多消息）参数，如果为true，将一次性拒绝所有小于deliveryTag的消息
     * 2、channel.basicReject(tag, false); 等同于 channel.basicNack(tag,false,false);
     * 3、channel.basicReject(tag, true); 等同于 channel.basicNack(tag,false,true);
     * 4、message.getMessageProperties().isRedelivered() 等同于 message.getMessageProperties().getRedelivered()
     * 5、如果代码报异常，就自动进行重试3次
     */
    @RabbitListener(queues = QueueDemoConfig.DIRECT_QUEUE)
    public void receive(Message message, Channel channel) throws IOException
    {
        log.info("【测试队列消费者】消息体: {}", new String(message.getBody()));
        //redelivered表示是否重复消费消息，也就是进入该方法的次数>1，则为true，所以第一次进来为false
        log.info("是否重复消费消息："+message.getMessageProperties().isRedelivered());
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        //如果requeue=true,就会马上入队，马上又被消费;requeue=false,消息也从队列中移除了
        //channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
        //如果requeue=true,就会马上入队，马上又被消费;requeue=false,消息也从队列中移除了
        //channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,false);
    }
}
