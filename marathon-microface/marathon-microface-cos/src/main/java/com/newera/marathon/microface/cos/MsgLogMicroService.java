package com.newera.marathon.microface.cos;

import com.newera.marathon.dto.system.inquiry.XfaceMsgLogListInquiryRequestDTO;
import com.newera.marathon.dto.system.inquiry.XfaceMsgLogListInquiryResponseDTO;
import com.newera.marathon.dto.system.maintenance.XfaceMsgLogAdditionRequestDTO;
import com.newera.marathon.dto.system.maintenance.XfaceMsgLogAdditionResponseDTO;
import com.newera.marathon.dto.system.maintenance.XfaceMsgLogModifyRequestDTO;
import com.newera.marathon.dto.system.maintenance.XfaceMsgLogModifyResponseDTO;
import com.spaking.boot.starter.core.model.TransactionStatus;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient(
        name = ServerName.APPLICATION_NAME,
        fallbackFactory = MsgLogMicroService.MsgLogMicroServiceImpl.class
)
public interface MsgLogMicroService {
    @PostMapping("/cos/msg/log/list/inquiry")
    XfaceMsgLogListInquiryResponseDTO msgLogListInquiry(@Valid @RequestBody XfaceMsgLogListInquiryRequestDTO requestDTO);

    @PostMapping("/cos/msg/log/addition")
    XfaceMsgLogAdditionResponseDTO msgLogAddition(@Valid @RequestBody XfaceMsgLogAdditionRequestDTO requestDTO);

    @PostMapping("/cos/msg/log/modify")
    XfaceMsgLogModifyResponseDTO msgLogModify(@Valid @RequestBody XfaceMsgLogModifyRequestDTO requestDTO);

    @Component
    class MsgLogMicroServiceImpl implements FallbackFactory<MsgLogMicroService> {
        private Logger logger = LoggerFactory.getLogger(MsgLogMicroServiceImpl.class);
        @Override
        public MsgLogMicroService create(Throwable throwable) {
            logger.error("fallback reason:{}",throwable.getMessage());
            return new MsgLogMicroService(){
                @Override
                public XfaceMsgLogListInquiryResponseDTO msgLogListInquiry(XfaceMsgLogListInquiryRequestDTO requestDTO) {
                    XfaceMsgLogListInquiryResponseDTO responseDTO = new XfaceMsgLogListInquiryResponseDTO();
                    TransactionStatus transactionStatus = new TransactionStatus();
                    transactionStatus.setError("Call remote(msgLogListInquiry) service error.", ServerName.APPLICATION_NAME);
                    responseDTO.setTransactionStatus(transactionStatus);
                    return responseDTO;
                }

                @Override
                public XfaceMsgLogAdditionResponseDTO msgLogAddition(@Valid XfaceMsgLogAdditionRequestDTO requestDTO) {
                    XfaceMsgLogAdditionResponseDTO responseDTO = new XfaceMsgLogAdditionResponseDTO();
                    TransactionStatus transactionStatus = new TransactionStatus();
                    transactionStatus.setError("Call remote(msgLogAddition) service error.", ServerName.APPLICATION_NAME);
                    responseDTO.setTransactionStatus(transactionStatus);
                    return responseDTO;
                }

                @Override
                public XfaceMsgLogModifyResponseDTO msgLogModify(@Valid XfaceMsgLogModifyRequestDTO requestDTO) {
                    XfaceMsgLogModifyResponseDTO responseDTO = new XfaceMsgLogModifyResponseDTO();
                    TransactionStatus transactionStatus = new TransactionStatus();
                    transactionStatus.setError("Call remote(msgLogModify) service error.", ServerName.APPLICATION_NAME);
                    responseDTO.setTransactionStatus(transactionStatus);
                    return responseDTO;
                }
            };
        }
    }
}
