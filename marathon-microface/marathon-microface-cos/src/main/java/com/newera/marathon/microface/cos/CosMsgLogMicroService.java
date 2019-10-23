package com.newera.marathon.microface.cos;

import com.newera.marathon.dto.cos.inquiry.XfaceCosMsgLogListInquiryRequestDTO;
import com.newera.marathon.dto.cos.inquiry.XfaceCosMsgLogListInquiryResponseDTO;
import com.newera.marathon.dto.cos.maintenance.XfaceCosMsgLogAdditionRequestDTO;
import com.newera.marathon.dto.cos.maintenance.XfaceCosMsgLogAdditionResponseDTO;
import com.newera.marathon.dto.cos.maintenance.XfaceCosMsgLogModifyRequestDTO;
import com.newera.marathon.dto.cos.maintenance.XfaceCosMsgLogModifyResponseDTO;
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
        fallbackFactory = CosMsgLogMicroService.MsgLogMicroServiceImpl.class
)
public interface CosMsgLogMicroService {
    @PostMapping("/cos/msg/log/list/inquiry")
    XfaceCosMsgLogListInquiryResponseDTO msgLogListInquiry(@Valid @RequestBody XfaceCosMsgLogListInquiryRequestDTO requestDTO);

    @PostMapping("/cos/msg/log/addition")
    XfaceCosMsgLogAdditionResponseDTO msgLogAddition(@Valid @RequestBody XfaceCosMsgLogAdditionRequestDTO requestDTO);

    @PostMapping("/cos/msg/log/modify")
    XfaceCosMsgLogModifyResponseDTO msgLogModify(@Valid @RequestBody XfaceCosMsgLogModifyRequestDTO requestDTO);

    @Component
    class MsgLogMicroServiceImpl implements FallbackFactory<CosMsgLogMicroService> {
        private Logger logger = LoggerFactory.getLogger(MsgLogMicroServiceImpl.class);
        @Override
        public CosMsgLogMicroService create(Throwable throwable) {
            logger.error("fallback reason:{}",throwable.getMessage());
            return new CosMsgLogMicroService(){
                @Override
                public XfaceCosMsgLogListInquiryResponseDTO msgLogListInquiry(XfaceCosMsgLogListInquiryRequestDTO requestDTO) {
                    XfaceCosMsgLogListInquiryResponseDTO responseDTO = new XfaceCosMsgLogListInquiryResponseDTO();
                    TransactionStatus transactionStatus = new TransactionStatus();
                    transactionStatus.setError("Call remote(msgLogListInquiry) service error.", ServerName.APPLICATION_NAME);
                    responseDTO.setTransactionStatus(transactionStatus);
                    return responseDTO;
                }

                @Override
                public XfaceCosMsgLogAdditionResponseDTO msgLogAddition(@Valid XfaceCosMsgLogAdditionRequestDTO requestDTO) {
                    XfaceCosMsgLogAdditionResponseDTO responseDTO = new XfaceCosMsgLogAdditionResponseDTO();
                    TransactionStatus transactionStatus = new TransactionStatus();
                    transactionStatus.setError("Call remote(msgLogAddition) service error.", ServerName.APPLICATION_NAME);
                    responseDTO.setTransactionStatus(transactionStatus);
                    return responseDTO;
                }

                @Override
                public XfaceCosMsgLogModifyResponseDTO msgLogModify(@Valid XfaceCosMsgLogModifyRequestDTO requestDTO) {
                    XfaceCosMsgLogModifyResponseDTO responseDTO = new XfaceCosMsgLogModifyResponseDTO();
                    TransactionStatus transactionStatus = new TransactionStatus();
                    transactionStatus.setError("Call remote(msgLogModify) service error.", ServerName.APPLICATION_NAME);
                    responseDTO.setTransactionStatus(transactionStatus);
                    return responseDTO;
                }
            };
        }
    }
}
