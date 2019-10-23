package com.newera.marathon.job.service;

import com.newera.marathon.dto.cos.inquiry.XfaceCosMsgLogListInquiryRequestDTO;
import com.newera.marathon.dto.cos.inquiry.XfaceCosMsgLogListInquiryResponseDTO;
import com.newera.marathon.dto.cos.inquiry.XfaceCosMsgLogListInquiryResponseSubDTO;
import com.newera.marathon.dto.cos.maintenance.XfaceCosMsgLogModifyRequestDTO;
import com.newera.marathon.dto.cos.maintenance.XfaceCosMsgLogModifyResponseDTO;
import com.newera.marathon.microface.cos.CosMsgLogMicroService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * 任务Handler示例（Bean模式）
 *
 * 开发步骤：
 * 1、继承"IJobHandler"：“com.xxl.job.core.handler.IJobHandler”；
 * 2、注册到Spring容器：添加“@Component”注解，被Spring容器扫描为Bean实例；
 * 3、注册到执行器工厂：添加“@JobHandler(value="自定义jobhandler名称")”注解，注解value值对应的是调度中心新建任务的JobHandler属性的值。
 * 4、执行日志：需要通过 "XxlJobLogger.log" 打印执行日志；
 *
 */

@JobHandler(value="msgLogJobHandler")
@Component
@Slf4j
public class MsgLogJobHandler extends IJobHandler {
    @Autowired
    private CosMsgLogMicroService cosMsgLogMicroService;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    // 最大投递次数
    private static final int MAX_TRY_COUNT = 3;
    @Override
    public ReturnT<String> execute(String param) throws Exception {
        log.info("MsgLogJobHandler start ..............");
        XfaceCosMsgLogListInquiryRequestDTO msgLogListInquiryRequestDTO = new XfaceCosMsgLogListInquiryRequestDTO();
        msgLogListInquiryRequestDTO.setStatus(1);
        //查询所有状态为"投递中"的消息
        XfaceCosMsgLogListInquiryResponseDTO msgLogListInquiryResponseDTO = cosMsgLogMicroService.msgLogListInquiry(msgLogListInquiryRequestDTO);
        List<XfaceCosMsgLogListInquiryResponseSubDTO> responseSubDTOS = msgLogListInquiryResponseDTO.getDataList();
        responseSubDTOS.forEach(w->{
            if (w.getTryCount() >= MAX_TRY_COUNT) {
                log.info("MsgId：{}",w.getMsgId());
                XfaceCosMsgLogModifyRequestDTO msgLogModifyRequestDTO = new XfaceCosMsgLogModifyRequestDTO();
                msgLogModifyRequestDTO.setMsgId(w.getMsgId());
                msgLogModifyRequestDTO.setStatus(3);
                //更新状态为投递失败
                XfaceCosMsgLogModifyResponseDTO msgLogModifyResponseDTO = cosMsgLogMicroService.msgLogModify(msgLogModifyRequestDTO);
                if(!msgLogModifyResponseDTO.getTransactionStatus().isSuccess()){
                    log.error("【消息定时任务】：更新状态为'投递失败'失败");
                }
                log.info("超过最大重试次数, 消息投递失败, msgId: {}", w.getMsgId());
            }else{
                XfaceCosMsgLogModifyRequestDTO msgLogModifyRequestDTO = new XfaceCosMsgLogModifyRequestDTO();
                msgLogModifyRequestDTO.setMsgId(w.getMsgId());
                msgLogModifyRequestDTO.setTryCount(w.getTryCount()+1);
                //更新重试次数+1
                XfaceCosMsgLogModifyResponseDTO msgLogModifyResponseDTO = cosMsgLogMicroService.msgLogModify(msgLogModifyRequestDTO);
                if(!msgLogModifyResponseDTO.getTransactionStatus().isSuccess()){
                    log.error("【消息定时任务】：更新重试次数失败");
                }
                Message message = MessageBuilder.withBody(w.getMsgBody().getBytes()).build();
                message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);// 消息持久化
                message.getMessageProperties().setContentType(MessageProperties.CONTENT_TYPE_JSON);
                //消息唯一ID
                CorrelationData correlationData = new CorrelationData(w.getMsgId());
                //指定交换机，指定routing key，发送消息的内容
                rabbitTemplate.convertAndSend(w.getExchange(), w.getRoutingKey(),message,correlationData);
            }
        });
        log.info("MsgLogJobHandler end ..............");
        return SUCCESS;
    }
}
