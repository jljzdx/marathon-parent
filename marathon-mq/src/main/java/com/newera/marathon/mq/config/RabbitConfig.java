package com.newera.marathon.mq.config;

import com.newera.marathon.dto.cos.maintenance.XfaceCosMsgLogModifyRequestDTO;
import com.newera.marathon.dto.cos.maintenance.XfaceCosMsgLogModifyResponseDTO;
import com.newera.marathon.microface.cos.CosMsgLogMicroService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class RabbitConfig {
    @Autowired
    private CachingConnectionFactory connectionFactory;
    @Autowired
    private CosMsgLogMicroService cosMsgLogMicroService;
    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        // 消息是否成功发送到Exchange
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (ack) {
                String msgId = correlationData.getId();
                log.info("消息成功发送到Exchange，msgId：{}",msgId);
                XfaceCosMsgLogModifyRequestDTO requestDTO = new XfaceCosMsgLogModifyRequestDTO();
                requestDTO.setMsgId(msgId);
                requestDTO.setStatus(2);
                XfaceCosMsgLogModifyResponseDTO responseDTO = cosMsgLogMicroService.msgLogModify(requestDTO);
                if(!responseDTO.getTransactionStatus().isSuccess()){
                    log.error("【消费确认】：更新状态为'投递成功'失败");
                }
            } else {
                log.info("消息发送到Exchange失败, {}, cause: {}", correlationData, cause);
            }
        });
        // 触发setReturnCallback回调必须设置mandatory=true, 否则Exchange没有找到Queue就会丢弃掉消息, 而不会触发回调
        rabbitTemplate.setMandatory(true);
        // 消息是否从Exchange路由到Queue, 注意: 这是一个失败回调, 只有消息从Exchange路由到Queue失败才会回调这个方法
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            log.info("消息从Exchange路由到Queue失败: exchange: {}, route: {}, replyCode: {}, replyText: {}, message: {}", exchange, routingKey, replyCode, replyText, message);
        });

        return rabbitTemplate;
    }
    public static final String MAIL_QUEUE = "mail.queue";
    public static final String MAIL_EXCHANGE = "mail.exchange";
    public static final String MAIL_ROUTING_KEY = "mail.routing.key";
    @Bean
    public Queue mailQueue() {
        return new Queue(MAIL_QUEUE,true);
    }
    @Bean
    public DirectExchange mailExchange() {
        return new DirectExchange(MAIL_EXCHANGE, true, false);
    }
    @Bean
    public Binding mailBinding() {
        return BindingBuilder.bind(mailQueue()).to(mailExchange()).with(MAIL_ROUTING_KEY);
    }

}
