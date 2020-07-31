package com.newera.marathon.service.order.service;

public interface RabbitService
{
    void doRabbitmqDirect();

    void doRabbitmqFanout();

    void doRabbitmqTopic(Integer type);
}
