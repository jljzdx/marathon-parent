package com.newera.marathon.mq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class FanoutDemoConsumer extends BaseConsumer
{

    @RabbitListener(queues = "fanout.A")
    public void process3(Map message)
    {
        System.out.println("ReceiverA  : " + message.toString());
    }

    @RabbitListener(queues = "fanout.B")
    public void process4(Map message)
    {
        System.out.println("ReceiverB  : " + message.toString());
    }

    @RabbitListener(queues = "fanout.C")
    public void process5(Map message)
    {
        System.out.println("ReceiverC  : " + message.toString());
    }
}
