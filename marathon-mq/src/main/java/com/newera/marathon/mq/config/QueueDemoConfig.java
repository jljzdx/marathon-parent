package com.newera.marathon.mq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class QueueDemoConfig
{

    //--------------Direct类型演示开始---------------//
    //Direct是一对一的方式，一条消息只能被一个消费者消费
    public static final String DIRECT_QUEUE = "test.queue";

    public static final String DIRECT_EXCHANGE = "test.exchange";

    public static final String DIRECT_ROUTING_KEY = "test.routing.key";

    @Bean
    public Queue testQueue()
    {
        return new Queue(DIRECT_QUEUE, true);
    }

    @Bean
    public DirectExchange testExchange()
    {
        return new DirectExchange(DIRECT_EXCHANGE, true, false);
    }

    @Bean
    public Binding testBinding()
    {
        return BindingBuilder.bind(testQueue()).to(testExchange()).with(DIRECT_ROUTING_KEY);
    }
    //--------------Direct类型演示结束---------------//

    //--------------Topic类型演示开始---------------//
    //“*” 匹配特定位置的任意文本，“#” 匹配所有规则
    //队列
    public static final String QUEUE_NEWS = "queue_news";

    public static final String QUEUE_WEATHER = "queue_weather";

    public static final String QUEUE_NEWS_WEATHER = "queue_news_weather";

    //交换机
    public static final String EXCHANGE_TOPIC_KAIFENG = "exchange_topic_kaifeng";

    //交换机与队列绑定的RoutingKey
    public static final String ROUTINGKEY_NEWS = "*.news";

    public static final String ROUTINGKEY_WEATHER = "*.weather";

    public static final String ROUTINGKEY_ALL = "kaifeng.*";

    //声明队列
    @Bean(QUEUE_NEWS)
    public Queue QUEUE_NEWS()
    {    //新闻的队列
        return new Queue(QUEUE_NEWS);
    }

    @Bean(QUEUE_WEATHER)
    public Queue QUEUE_WEATHER()
    {  //天气的队列
        return new Queue(QUEUE_WEATHER);
    }

    @Bean(QUEUE_NEWS_WEATHER)
    public Queue QUEUE_NEWS_WEATHER()
    {  //新闻和天气的队列
        return new Queue(QUEUE_NEWS_WEATHER);
    }

    //声明交换机
    @Bean(EXCHANGE_TOPIC_KAIFENG)
    public Exchange EXCHANGE_TOPIC_KAIFENG()
    {
        //声明了一个Topic类型的交换机，durable是持久化（重启rabbitmq这个交换机不会被自动删除）
        return ExchangeBuilder.topicExchange(EXCHANGE_TOPIC_KAIFENG).durable(true).build();
    }

    //声明news队列和交换机绑定关系，并且指定RoutingKey
    @Bean
    public Binding NEWS_BINDING_TOPIC(@Qualifier(QUEUE_NEWS) Queue queue, @Qualifier(EXCHANGE_TOPIC_KAIFENG) Exchange exchange)
    {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTINGKEY_NEWS).noargs();
    }

    //声明weather队列和交换机绑定关系，并且指定RoutingKey
    @Bean
    public Binding WEATHER_BINDING_TOPIC(@Qualifier(QUEUE_WEATHER) Queue queue, @Qualifier(EXCHANGE_TOPIC_KAIFENG) Exchange exchange)
    {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTINGKEY_WEATHER).noargs();
    }

    //声明news+weather队列和交换机绑定关系，并且指定RoutingKey
    @Bean
    public Binding NEWS_WEATHER_BINDING_TOPIC(@Qualifier(QUEUE_NEWS_WEATHER) Queue queue, @Qualifier(EXCHANGE_TOPIC_KAIFENG) Exchange exchange)
    {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTINGKEY_ALL).noargs();
    }
    //--------------Topic类型演示结束---------------//

    //--------------Fanout类型演示开始---------------//
    //队列
    public static final String QUEUE_A = "fanout.A";

    public static final String QUEUE_B = "fanout.B";

    public static final String QUEUE_C = "fanout.C";

    //交换机
    public static final String EXCHANGE_FANOUT_KAIFENG = "fanoutExchange";

    @Bean(name = QUEUE_A)
    public Queue AMessage()
    {
        return new Queue(QUEUE_A);
    }

    @Bean(name = QUEUE_B)
    public Queue BMessage()
    {
        return new Queue(QUEUE_B);
    }

    @Bean(name = QUEUE_C)
    public Queue CMessage()
    {
        return new Queue(QUEUE_C);
    }

    @Bean(EXCHANGE_FANOUT_KAIFENG)
    FanoutExchange fanoutExchange()
    {
        return new FanoutExchange(EXCHANGE_FANOUT_KAIFENG);//配置广播路由器
    }

    @Bean
    Binding bindingExchangeA(@Qualifier(QUEUE_A) Queue AMessage, @Qualifier(EXCHANGE_FANOUT_KAIFENG) FanoutExchange fanoutExchange)
    {
        return BindingBuilder.bind(AMessage).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeB(@Qualifier(QUEUE_B) Queue BMessage, @Qualifier(EXCHANGE_FANOUT_KAIFENG) FanoutExchange fanoutExchange)
    {
        return BindingBuilder.bind(BMessage).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeC(@Qualifier(QUEUE_C) Queue CMessage, @Qualifier(EXCHANGE_FANOUT_KAIFENG) FanoutExchange fanoutExchange)
    {
        return BindingBuilder.bind(CMessage).to(fanoutExchange);
    }
    //--------------Fanout类型演示结束---------------//
}
