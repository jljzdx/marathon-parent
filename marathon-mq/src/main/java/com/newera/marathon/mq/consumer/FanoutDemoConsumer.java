package com.newera.marathon.mq.consumer;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class FanoutDemoConsumer extends BaseConsumer
{

    @RabbitListener(queues = "fanout.A")
    public void process3(Message message, Channel channel)
    {
        System.out.println("ReceiverA  : " + new String(message.getBody()));
        String header = message.getMessageProperties().getHeader("fanout");
        System.out.println("Header : "+header);
        try
        {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }

    @RabbitListener(queues = "fanout.B")
    public void process4(Message message, Channel channel)
    {
        System.out.println("ReceiverB  : " + new String(message.getBody()));
        String header = message.getMessageProperties().getHeader("fanout");
        System.out.println("Header : "+header);
        try
        {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    @RabbitListener(queues = "fanout.C")
    public void process5(Message message, Channel channel)
    {
        System.out.println("ReceiverC  : " + new String(message.getBody()));
        String header = message.getMessageProperties().getHeader("fanout");
        System.out.println("Header : "+header);
        try
        {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
