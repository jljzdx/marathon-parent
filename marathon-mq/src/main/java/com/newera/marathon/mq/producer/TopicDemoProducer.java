package com.newera.marathon.mq.producer;

import com.newera.marathon.mq.config.QueueDemoConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TopicDemoProducer extends BaseProducer
{

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendNews()
    {
        rabbitTemplate.convertAndSend(QueueDemoConfig.EXCHANGE_TOPIC_KAIFENG, "kaifeng.news", "开封今年粮食产量提升10%");
    }

    public void sendWeather()
    {
        rabbitTemplate.convertAndSend(QueueDemoConfig.EXCHANGE_TOPIC_KAIFENG, "kaifeng.weather", "开封明天白天多云15℃");
    }
}
