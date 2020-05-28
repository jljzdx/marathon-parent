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
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
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
    public void receive(@Payload String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag, @Header(AmqpHeaders.REDELIVERED) boolean reDelivered) throws IOException {
        log.info("【邮件队列消费者】消息体: {}", message);
        MailSend mailSend = JSONObject.parseObject(message, MailSend.class);
        //幂等性判断
        if(!idempotent(mailSend.getMsgId())) return;
        //发送邮件
        XfaceCosMailSendRequestDTO cosMailSendRequestDTO = new XfaceCosMailSendRequestDTO();
        BeanUtils.copyProperties(mailSend, cosMailSendRequestDTO);
        XfaceCosMailSendResponseDTO cosMailSendResponseDTO = cosMicroService.sendMail(cosMailSendRequestDTO);
        if (cosMailSendResponseDTO.getTransactionStatus().isSuccess()) {
            //更新状态为已消费
            msgLogModify(mailSend.getMsgId());
            //basicReject和basicNack的区别：basicReject一次只能拒绝一条消息；basicNack一次可以拒绝多条消息
            //multiple：批量确认（true:将一次性拒绝所有小于deliveryTag的消息）；requeue：重新入列
            try {
                channel.basicAck(tag, false);
            } catch (IOException e) {
                if(reDelivered){
                    log.info("消息已重复处理失败：{}",message);
                    channel.basicReject(tag,false);
                }else{
                    log.error("消息处理失败",e);
                    //重新入队一次
                    channel.basicNack(tag,false,true);
                }
                e.printStackTrace();
            }
            log.info("receiver success");
        } else {
            channel.basicNack(tag, false, false);
            log.error("receiver failed,cause:{}", cosMailSendResponseDTO.getTransactionStatus().getReplyText());
            throw new RuntimeException(cosMailSendResponseDTO.getTransactionStatus().getReplyText());
        }
    }
}