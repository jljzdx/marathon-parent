package com.newera.marathon.mq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TopicDemoConsumer extends BaseConsumer {

    //模拟三个用户接收
    @RabbitListener(queues = {"queue_weather"})
    public void getMessageA(String msg){
        System.out.println("A用户想看天气接收到:" + msg);
    }
    @RabbitListener(queues = {"queue_news"})
    public void getMessageB(Message message){  //我们也可以用Message作为参数接收
        byte[] body = message.getBody();
        String msg = new String(body);
        System.out.println("B用户想看新闻接收到:" + msg);
    }
    @RabbitListener(queues = {"queue_news_weather"})
    public void getMessageC(String msg){
        System.out.println("C用户想看新闻和天气接收到:" + msg);
    }
}
