package com.newera.marathon.service.order.service.impl;

import com.newera.marathon.mq.producer.DirectDemoProducer;
import com.newera.marathon.mq.producer.FanoutDemoProducer;
import com.newera.marathon.mq.producer.TopicDemoProducer;
import com.newera.marathon.service.order.service.RabbitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RabbitServiceImpl implements RabbitService
{
    @Autowired
    private DirectDemoProducer directDemoProducer;

    @Autowired
    private FanoutDemoProducer fanoutDemoProducer;

    @Autowired
    private TopicDemoProducer topicDemoProducer;

    @Override
    public void doRabbitmqDirect()
    {
        log.info("doRabbitmqDirect start");
        directDemoProducer.directProducer();
        log.info("doRabbitmqDirect end");

    }

    @Override
    public void doRabbitmqFanout()
    {
        log.info("doRabbitmqFanout start");
        fanoutDemoProducer.fanoutProducer();
        log.info("doRabbitmqFanout end");

    }

    @Override
    public void doRabbitmqTopic(Integer type)
    {
        log.info("doRabbitmqTest start");
        if(type == 1)
        {
            topicDemoProducer.sendNews();
        }else
        {
            topicDemoProducer.sendWeather();
        }
        log.info("doRabbitmqTest end");

    }
}
