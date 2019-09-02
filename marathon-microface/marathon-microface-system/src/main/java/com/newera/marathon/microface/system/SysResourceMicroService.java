package com.newera.marathon.microface.system;

import com.newera.marathon.dto.system.inquiry.XfaceSysResourceLoopInquiryRequestDTO;
import com.newera.marathon.dto.system.inquiry.XfaceSysResourceLoopInquiryResponseDTO;
import com.newera.marathon.dto.system.inquiry.XfaceSysResourceModifyInquiryRequestDTO;
import com.newera.marathon.dto.system.inquiry.XfaceSysResourceModifyInquiryResponseDTO;
import com.newera.marathon.dto.system.maintenance.*;
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
        name = SysServer.APPLICATION_NAME,
        fallbackFactory = SysResourceMicroService.SysResourceMicroServiceImpl.class
)
public interface SysResourceMicroService {
    @PostMapping("/sys/resource/inquiry/loop")
    XfaceSysResourceLoopInquiryResponseDTO sysResourceInquiryLoop(@Valid @RequestBody XfaceSysResourceLoopInquiryRequestDTO requestDTO);

    @PostMapping("/sys/resource/addition")
    public XfaceSysResourceAdditionResponseDTO sysResourceAddition(@Valid @RequestBody XfaceSysResourceAdditionRequestDTO requestDTO);

    @PostMapping("/sys/resource/modify/inquiry")
    public XfaceSysResourceModifyInquiryResponseDTO sysResourceModifyInquiry(@Valid @RequestBody XfaceSysResourceModifyInquiryRequestDTO requestDTO);

    @PostMapping("/sys/resource/modify")
    public XfaceSysResourceModifyResponseDTO sysResourceModify(@Valid @RequestBody XfaceSysResourceModifyRequestDTO requestDTO);

    @PostMapping("/sys/resource/modify/status")
    public XfaceSysResourceModifyStatusResponseDTO sysResourceModifyStatus(@Valid @RequestBody XfaceSysResourceModifyStatusRequestDTO requestDTO);

    @Component
    class SysResourceMicroServiceImpl implements FallbackFactory<SysResourceMicroService> {
        private Logger logger = LoggerFactory.getLogger(SysResourceMicroServiceImpl.class);
        @Override
        public SysResourceMicroService create(Throwable throwable) {
            logger.error("fallback reason:{}",throwable.getMessage());
            return new SysResourceMicroService(){
                @Override
                public XfaceSysResourceLoopInquiryResponseDTO sysResourceInquiryLoop(@Valid XfaceSysResourceLoopInquiryRequestDTO requestDTO) {
                    XfaceSysResourceLoopInquiryResponseDTO responseDTO = new XfaceSysResourceLoopInquiryResponseDTO();
                    TransactionStatus transactionStatus = new TransactionStatus();
                    transactionStatus.setError("Call remote(sysResourceInquiryPage) service error.",SysServer.APPLICATION_NAME);
                    responseDTO.setTransactionStatus(transactionStatus);
                    return responseDTO;
                }

                @Override
                public XfaceSysResourceAdditionResponseDTO sysResourceAddition(@Valid XfaceSysResourceAdditionRequestDTO requestDTO) {
                    XfaceSysResourceAdditionResponseDTO responseDTO = new XfaceSysResourceAdditionResponseDTO();
                    TransactionStatus transactionStatus = new TransactionStatus();
                    transactionStatus.setError("Call remote(sysResourceAddition) service error.",SysServer.APPLICATION_NAME);
                    responseDTO.setTransactionStatus(transactionStatus);
                    return responseDTO;
                }

                @Override
                public XfaceSysResourceModifyInquiryResponseDTO sysResourceModifyInquiry(@Valid XfaceSysResourceModifyInquiryRequestDTO requestDTO) {
                    XfaceSysResourceModifyInquiryResponseDTO responseDTO = new XfaceSysResourceModifyInquiryResponseDTO();
                    TransactionStatus transactionStatus = new TransactionStatus();
                    transactionStatus.setError("Call remote(sysResourceModifyInquiry) service error.",SysServer.APPLICATION_NAME);
                    responseDTO.setTransactionStatus(transactionStatus);
                    return responseDTO;
                }

                @Override
                public XfaceSysResourceModifyResponseDTO sysResourceModify(@Valid XfaceSysResourceModifyRequestDTO requestDTO) {
                    XfaceSysResourceModifyResponseDTO responseDTO = new XfaceSysResourceModifyResponseDTO();
                    TransactionStatus transactionStatus = new TransactionStatus();
                    transactionStatus.setError("Call remote(sysResourceModify) service error.",SysServer.APPLICATION_NAME);
                    responseDTO.setTransactionStatus(transactionStatus);
                    return responseDTO;
                }

                @Override
                public XfaceSysResourceModifyStatusResponseDTO sysResourceModifyStatus(@Valid XfaceSysResourceModifyStatusRequestDTO requestDTO) {
                    XfaceSysResourceModifyStatusResponseDTO responseDTO = new XfaceSysResourceModifyStatusResponseDTO();
                    TransactionStatus transactionStatus = new TransactionStatus();
                    transactionStatus.setError("Call remote(sysResourceModifyStatus) service error.",SysServer.APPLICATION_NAME);
                    responseDTO.setTransactionStatus(transactionStatus);
                    return responseDTO;
                }
            };
        }
    }
}
