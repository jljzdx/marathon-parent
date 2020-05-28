package com.newera.marathon.service.order.service;

import com.newera.marathon.dto.order.inquiry.XfaceOrderWebsocketDetailInquiryRequestDTO;
import com.newera.marathon.dto.order.inquiry.XfaceOrderWebsocketDetailInquiryResponseDTO;
import com.newera.marathon.dto.order.maintenance.XfaceOrderWebSocketSendAllRequestDTO;
import com.newera.marathon.dto.order.maintenance.XfaceOrderWebsocketSendAllResponseDTO;

public interface WebsocketService {
    XfaceOrderWebsocketDetailInquiryResponseDTO doOnlineDetailInquiryPage(XfaceOrderWebsocketDetailInquiryRequestDTO requestDTO);

    XfaceOrderWebsocketSendAllResponseDTO doSendAll(XfaceOrderWebSocketSendAllRequestDTO requestDTO);

    void doRabbitmqTest();
}
