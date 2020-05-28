package com.newera.marathon.service.order.controller;

import com.newera.marathon.dto.order.inquiry.XfaceOrderWebsocketDetailInquiryRequestDTO;
import com.newera.marathon.dto.order.inquiry.XfaceOrderWebsocketDetailInquiryResponseDTO;
import com.newera.marathon.dto.order.maintenance.XfaceOrderWebSocketSendAllRequestDTO;
import com.newera.marathon.dto.order.maintenance.XfaceOrderWebsocketSendAllResponseDTO;
import com.newera.marathon.service.order.service.WebsocketService;
import com.spaking.boot.starter.core.annotation.BusinessLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class WebsocketController {

    @Autowired
    private WebsocketService websocketService;

    @BusinessLogger(key = "ORDER",value = "websocketInquiryPage")
    @PostMapping("/order/websocket/inquiry/page")
    public XfaceOrderWebsocketDetailInquiryResponseDTO websocketInquiryPage(@Valid @RequestBody XfaceOrderWebsocketDetailInquiryRequestDTO requestDTO){
        XfaceOrderWebsocketDetailInquiryResponseDTO responseDTO = websocketService.doOnlineDetailInquiryPage(requestDTO);
        return responseDTO;
    }

    @BusinessLogger(key = "ORDER",value = "websocketSendAll")
    @PostMapping("/order/websocket/send/all")
    public XfaceOrderWebsocketSendAllResponseDTO websocketSendAll(@Valid @RequestBody XfaceOrderWebSocketSendAllRequestDTO requestDTO){
        XfaceOrderWebsocketSendAllResponseDTO responseDTO = websocketService.doSendAll(requestDTO);
        return responseDTO;
    }
    @BusinessLogger(key = "ORDER",value = "rabbitmqTest")
    @PostMapping("/order/rabbitmq/test")
    public void rabbitmqTest(){
        websocketService.doRabbitmqTest();
    }
}
