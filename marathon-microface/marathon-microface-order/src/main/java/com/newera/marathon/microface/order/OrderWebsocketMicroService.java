package com.newera.marathon.microface.order;

import com.newera.marathon.dto.order.inquiry.XfaceOrderWebsocketDetailInquiryRequestDTO;
import com.newera.marathon.dto.order.inquiry.XfaceOrderWebsocketDetailInquiryResponseDTO;
import com.newera.marathon.dto.order.maintenance.XfaceOrderWebSocketSendAllRequestDTO;
import com.newera.marathon.dto.order.maintenance.XfaceOrderWebsocketSendAllResponseDTO;
import com.spaking.boot.starter.core.model.TransactionStatus;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(
        name = ServerName.APPLICATION_NAME,
        fallbackFactory = OrderWebsocketMicroService.WebsocketMicroServiceImpl.class
)
public interface OrderWebsocketMicroService {

    @PostMapping("/order/websocket/inquiry/page")
    XfaceOrderWebsocketDetailInquiryResponseDTO websocketInquiryPage(XfaceOrderWebsocketDetailInquiryRequestDTO requestDTO);

    @PostMapping("/order/websocket/send/all")
    public XfaceOrderWebsocketSendAllResponseDTO websocketSendAll(XfaceOrderWebSocketSendAllRequestDTO requestDTO);

    @Component
    class WebsocketMicroServiceImpl implements FallbackFactory<OrderWebsocketMicroService> {
        private Logger logger = LoggerFactory.getLogger(WebsocketMicroServiceImpl.class);
        @Override
        public OrderWebsocketMicroService create(Throwable throwable) {
            logger.error("fallback reason:{}",throwable.getMessage());
            return new OrderWebsocketMicroService(){
                @Override
                public XfaceOrderWebsocketDetailInquiryResponseDTO websocketInquiryPage(XfaceOrderWebsocketDetailInquiryRequestDTO requestDTO) {
                    XfaceOrderWebsocketDetailInquiryResponseDTO responseDTO = new XfaceOrderWebsocketDetailInquiryResponseDTO();
                    TransactionStatus transactionStatus = new TransactionStatus();
                    transactionStatus.setError("Call remote(websocketInquiryPage) service error.", ServerName.APPLICATION_NAME);
                    responseDTO.setTransactionStatus(transactionStatus);
                    return responseDTO;
                }

                @Override
                public XfaceOrderWebsocketSendAllResponseDTO websocketSendAll(XfaceOrderWebSocketSendAllRequestDTO requestDTO) {
                    XfaceOrderWebsocketSendAllResponseDTO responseDTO = new XfaceOrderWebsocketSendAllResponseDTO();
                    TransactionStatus transactionStatus = new TransactionStatus();
                    transactionStatus.setError("Call remote(websocketSendAll) service error.", ServerName.APPLICATION_NAME);
                    responseDTO.setTransactionStatus(transactionStatus);
                    return responseDTO;
                }
            };
        }
    }
}
