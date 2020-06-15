package com.newera.marathon.service.order.service.impl;

import com.newera.marathon.dto.order.inquiry.XfaceOrderWebsocketDetailInquiryRequestDTO;
import com.newera.marathon.dto.order.inquiry.XfaceOrderWebsocketDetailInquiryResponseDTO;
import com.newera.marathon.dto.order.maintenance.XfaceOrderWebSocketSendAllRequestDTO;
import com.newera.marathon.dto.order.maintenance.XfaceOrderWebsocketSendAllResponseDTO;
import com.newera.marathon.mq.producer.DirectDemoProducer;
import com.newera.marathon.mq.producer.FanoutDemoProducer;
import com.newera.marathon.mq.producer.TopicDemoProducer;
import com.newera.marathon.service.order.service.WebsocketService;
import com.spaking.boot.starter.core.model.TransactionStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WebsocketServiceImpl implements WebsocketService
{
    @Autowired
    private DirectDemoProducer directDemoProducer;
    @Autowired
    private FanoutDemoProducer fanoutDemoProducer;
    @Autowired
    private TopicDemoProducer topicDemoProducer;

    @Override
    public XfaceOrderWebsocketDetailInquiryResponseDTO doOnlineDetailInquiryPage(XfaceOrderWebsocketDetailInquiryRequestDTO requestDTO)
    {
        log.info("doOnlineDetailInquiry start");
        XfaceOrderWebsocketDetailInquiryResponseDTO responseDTO = new XfaceOrderWebsocketDetailInquiryResponseDTO();
        TransactionStatus transactionStatus = new TransactionStatus();
        responseDTO.setTransactionStatus(transactionStatus);
        log.info("doOnlineDetailInquiry end");
        return responseDTO;
    }

    @Override
    public XfaceOrderWebsocketSendAllResponseDTO doSendAll(XfaceOrderWebSocketSendAllRequestDTO requestDTO)
    {
        log.info("doSendAll start");
        XfaceOrderWebsocketSendAllResponseDTO responseDTO = new XfaceOrderWebsocketSendAllResponseDTO();
        TransactionStatus transactionStatus = new TransactionStatus();
        responseDTO.setTransactionStatus(transactionStatus);
        log.info("doSendAll end");
        return responseDTO;
    }

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
    public void doRabbitmqTopic()
    {
        log.info("doRabbitmqTest start");
        //fanoutDemoProducer.sendNews();
        log.info("doRabbitmqTest end");

    }
}
