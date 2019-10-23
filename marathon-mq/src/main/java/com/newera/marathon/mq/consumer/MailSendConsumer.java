package com.newera.marathon.mq.consumer;

import com.alibaba.fastjson.JSONObject;
import com.newera.marathon.dto.cos.maintenance.XfaceCosMailSendRequestDTO;
import com.newera.marathon.dto.cos.maintenance.XfaceCosMailSendResponseDTO;
import com.newera.marathon.microface.cos.CosMicroService;
import com.newera.marathon.microface.cos.CosMsgLogMicroService;
import com.newera.marathon.mq.config.RabbitConfig;
import com.newera.marathon.mq.pojo.MailSend;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class MailSendConsumer extends BaseConsumer {

    @Autowired
    private CosMicroService cosMicroService;
    @Autowired
    private CosMsgLogMicroService cosMsgLogMicroService;

    @RabbitListener(queues = RabbitConfig.MAIL_QUEUE)
    public void receive(Message message, Channel channel) {
        String json = new String(message.getBody());
        log.info("【邮件队列消费者】消息体: {}", json);
        MailSend mailSend = JSONObject.parseObject(json, MailSend.class);
        //幂等性判断
        if(!idempotent(mailSend.getMsgId())) return;
        //发送邮件
        XfaceCosMailSendRequestDTO cosMailSendRequestDTO = new XfaceCosMailSendRequestDTO();
        BeanUtils.copyProperties(mailSend, cosMailSendRequestDTO);
        XfaceCosMailSendResponseDTO cosMailSendResponseDTO = cosMicroService.sendMail(cosMailSendRequestDTO);
        if (cosMailSendResponseDTO.getTransactionStatus().isSuccess()) {
            //更新状态为已消费
            msgLogModify(mailSend.getMsgId());
            //告诉服务器收到这条消息已经被当前消费者消费了，可以在队列安全删除，这样后面就不会再重发了，否则消息服务器以为这条消息没处理掉，后续还会再发
            //第二个参数是消息的标识，false只确认当前一个消息收到，true确认所有consumer获得的消息
            try {
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            } catch (IOException e) {
                log.error("ack failed");
                e.printStackTrace();
            }
            log.info("receiver success");
        } else {
            //channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);//有了这行，就会一直重复调用消费者
            log.error("receiver failed,cause:{}", cosMailSendResponseDTO.getTransactionStatus().getReplyText());
            throw new RuntimeException(cosMailSendResponseDTO.getTransactionStatus().getReplyText());
        }
    }
}
