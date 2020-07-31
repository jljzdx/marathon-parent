package com.newera.marathon.service.order.controller;

import com.newera.marathon.service.order.service.RabbitService;
import com.spaking.boot.starter.core.annotation.BusinessLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RabbitController
{

    @Autowired
    private RabbitService rabbitService;

    @BusinessLogger(key = "ORDER", value = "rabbitmqTest")
    @PostMapping("/order/rabbitmq/direct")
    public void rabbitmqDirect()
    {
        rabbitService.doRabbitmqDirect();
    }

    @BusinessLogger(key = "ORDER", value = "rabbitmqTest")
    @PostMapping("/order/rabbitmq/fanout")
    public void rabbitmqFanout()
    {
        rabbitService.doRabbitmqFanout();
    }

    @BusinessLogger(key = "ORDER", value = "rabbitmqTest")
    @PostMapping("/order/rabbitmq/topic")
    public void rabbitmqTopic(@RequestParam("type")Integer type)
    {
        rabbitService.doRabbitmqTopic(type);
    }
}
