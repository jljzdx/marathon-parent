package com.newera.marathon.service.cms.mq.consumer;

import com.alibaba.fastjson.JSONObject;
import com.newera.marathon.dto.cos.maintenance.XfaceCosMailSendRequestDTO;
import com.newera.marathon.dto.cos.maintenance.XfaceCosMailSendResponseDTO;
import com.newera.marathon.dto.cos.inquiry.XfaceCosMsgLogListInquiryRequestDTO;
import com.newera.marathon.dto.cos.inquiry.XfaceCosMsgLogListInquiryResponseDTO;
import com.newera.marathon.dto.cos.inquiry.XfaceCosMsgLogListInquiryResponseSubDTO;
import com.newera.marathon.dto.cos.maintenance.XfaceCosMsgLogModifyRequestDTO;
import com.newera.marathon.dto.cos.maintenance.XfaceCosMsgLogModifyResponseDTO;
import com.newera.marathon.microface.cos.MsgLogMicroService;
import com.newera.marathon.microface.cos.SendMicroService;
import com.newera.marathon.mq.config.RabbitConfig;
import com.newera.marathon.service.cms.mq.pojo.MailSend;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@Slf4j
public class MailSendConsumer {

    @Autowired
    private SendMicroService  sendMicroService;
    @Autowired
    private MsgLogMicroService msgLogMicroService;

    @RabbitListener(queues = RabbitConfig.MAIL_QUEUE)
    public void receive(Message message, Channel channel) {
        String json = new String(message.getBody());
        log.info("【邮件队列消费者】消息体: {}", json);
        MailSend mailSend = JSONObject.parseObject(json, MailSend.class);
        //幂等性判断
        XfaceCosMsgLogListInquiryRequestDTO msgLogListInquiryRequestDTO = new XfaceCosMsgLogListInquiryRequestDTO();
        msgLogListInquiryRequestDTO.setMsgId(mailSend.getMsgId());
        XfaceCosMsgLogListInquiryResponseDTO msgLogListInquiryResponseDTO = msgLogMicroService.msgLogListInquiry(msgLogListInquiryRequestDTO);
        List<XfaceCosMsgLogListInquiryResponseSubDTO> responseSubDTOS = msgLogListInquiryResponseDTO.getDataList();
        if(null == responseSubDTOS || responseSubDTOS.size() != 1 || responseSubDTOS.get(0).getStatus()==4){
            log.error("【邮件队列消费者】幂等性问题，消息Id：{}",mailSend.getMsgId());
            return;
        }
        //发送邮件
        XfaceCosMailSendRequestDTO cosMailSendRequestDTO = new XfaceCosMailSendRequestDTO();
        BeanUtils.copyProperties(mailSend,cosMailSendRequestDTO);
        XfaceCosMailSendResponseDTO cosMailSendResponseDTO = sendMicroService.sendMail(cosMailSendRequestDTO);
        try {
            if(cosMailSendResponseDTO.getTransactionStatus().isSuccess()){
                //更新状态为已消费
                XfaceCosMsgLogModifyRequestDTO msgLogModifyRequestDTO = new XfaceCosMsgLogModifyRequestDTO();
                msgLogModifyRequestDTO.setMsgId(mailSend.getMsgId());
                msgLogModifyRequestDTO.setStatus(4);
                XfaceCosMsgLogModifyResponseDTO msgLogModifyResponseDTO = msgLogMicroService.msgLogModify(msgLogModifyRequestDTO);
                if(!msgLogModifyResponseDTO.getTransactionStatus().isSuccess()){
                    log.error("【邮件队列消费者】：更新状态为'已消费'失败");
                }
                //告诉服务器收到这条消息已经被当前消费者消费了，可以在队列安全删除，这样后面就不会再重发了，否则消息服务器以为这条消息没处理掉，后续还会再发
                //第二个参数是消息的标识，false只确认当前一个消息收到，true确认所有consumer获得的消息
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
                log.info("receiver success");
            }else{
                //channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);有了这行，就会一直重复调用消费者
                log.info("receiver fail,cause:{}",cosMailSendResponseDTO.getTransactionStatus().getReplyText());
            }
        } catch (IOException e) {
            log.info("receiver fail");
            e.printStackTrace();
        }
    }
}
