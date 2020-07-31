package com.newera.marathon.mq.consumer;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class TopicDemoConsumer extends BaseConsumer
{

    @RabbitListener(queues = {"queue_weather"})
    public void getMessageA(Message message, Channel channel)
    {
        System.out.println("A用户想看天气接收到:" + new String(message.getBody()));
        try
        {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @RabbitListener(queues = {"queue_news"})
    public void getMessageB(Message message, Channel channel)
    {
        System.out.println("B用户想看新闻接收到:" + new String(message.getBody()));
        try
        {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @RabbitListener(queues = {"queue_news_weather"})
    public void getMessageC(Message message, Channel channel)
    {
        System.out.println("C用户想看新闻和天气接收到:" + new String(message.getBody()));
        try
        {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
