package com.newera.marathon.microface.cms.admin;

import com.newera.marathon.dto.cms.inquiry.XfaceCmsAdminResourceLoopInquiryRequestDTO;
import com.newera.marathon.dto.cms.inquiry.XfaceCmsAdminResourceLoopInquiryResponseDTO;
import com.newera.marathon.dto.cms.inquiry.XfaceCmsAdminResourceModifyInquiryRequestDTO;
import com.newera.marathon.dto.cms.inquiry.XfaceCmsAdminResourceModifyInquiryResponseDTO;
import com.newera.marathon.dto.cms.maintenance.*;
import com.newera.marathon.microface.cms.ServerName;
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
        fallbackFactory = AdminResourceMicroService.AdminResourceMicroServiceImpl.class
)
public interface AdminResourceMicroService {
    @PostMapping("/cms/admin/resource/inquiry/loop")
    XfaceCmsAdminResourceLoopInquiryResponseDTO resourceInquiryLoop(@Valid @RequestBody XfaceCmsAdminResourceLoopInquiryRequestDTO requestDTO);

    @PostMapping("/cms/admin/resource/addition")
    XfaceCmsAdminResourceAdditionResponseDTO resourceAddition(@Valid @RequestBody XfaceCmsAdminResourceAdditionRequestDTO requestDTO);

    @PostMapping("/cms/admin/resource/modify/inquiry")
    XfaceCmsAdminResourceModifyInquiryResponseDTO resourceModifyInquiry(@Valid @RequestBody XfaceCmsAdminResourceModifyInquiryRequestDTO requestDTO);

    @PostMapping("/cms/admin/resource/modify")
    XfaceCmsAdminResourceModifyResponseDTO resourceModify(@Valid @RequestBody XfaceCmsAdminResourceModifyRequestDTO requestDTO);

    @PostMapping("/cms/admin/resource/modify/status")
    XfaceCmsAdminResourceModifyStatusResponseDTO resourceModifyStatus(@Valid @RequestBody XfaceCmsAdminResourceModifyStatusRequestDTO requestDTO);

    @Component
    class AdminResourceMicroServiceImpl implements FallbackFactory<AdminResourceMicroService> {
        private Logger logger = LoggerFactory.getLogger(AdminResourceMicroServiceImpl.class);
        @Override
        public AdminResourceMicroService create(Throwable throwable) {
            logger.error("fallback reason:{}",throwable.getMessage());
            return new AdminResourceMicroService(){
                @Override
                public XfaceCmsAdminResourceLoopInquiryResponseDTO resourceInquiryLoop(@Valid XfaceCmsAdminResourceLoopInquiryRequestDTO requestDTO) {
                    XfaceCmsAdminResourceLoopInquiryResponseDTO responseDTO = new XfaceCmsAdminResourceLoopInquiryResponseDTO();
                    TransactionStatus transactionStatus = new TransactionStatus();
                    transactionStatus.setError("Call remote(resourceInquiryPage) service error.", ServerName.APPLICATION_NAME);
                    responseDTO.setTransactionStatus(transactionStatus);
                    return responseDTO;
                }

                @Override
                public XfaceCmsAdminResourceAdditionResponseDTO resourceAddition(@Valid XfaceCmsAdminResourceAdditionRequestDTO requestDTO) {
                    XfaceCmsAdminResourceAdditionResponseDTO responseDTO = new XfaceCmsAdminResourceAdditionResponseDTO();
                    TransactionStatus transactionStatus = new TransactionStatus();
                    transactionStatus.setError("Call remote(resourceAddition) service error.", ServerName.APPLICATION_NAME);
                    responseDTO.setTransactionStatus(transactionStatus);
                    return responseDTO;
                }

                @Override
                public XfaceCmsAdminResourceModifyInquiryResponseDTO resourceModifyInquiry(@Valid XfaceCmsAdminResourceModifyInquiryRequestDTO requestDTO) {
                    XfaceCmsAdminResourceModifyInquiryResponseDTO responseDTO = new XfaceCmsAdminResourceModifyInquiryResponseDTO();
                    TransactionStatus transactionStatus = new TransactionStatus();
                    transactionStatus.setError("Call remote(resourceModifyInquiry) service error.", ServerName.APPLICATION_NAME);
                    responseDTO.setTransactionStatus(transactionStatus);
                    return responseDTO;
                }

                @Override
                public XfaceCmsAdminResourceModifyResponseDTO resourceModify(@Valid XfaceCmsAdminResourceModifyRequestDTO requestDTO) {
                    XfaceCmsAdminResourceModifyResponseDTO responseDTO = new XfaceCmsAdminResourceModifyResponseDTO();
                    TransactionStatus transactionStatus = new TransactionStatus();
                    transactionStatus.setError("Call remote(resourceModify) service error.", ServerName.APPLICATION_NAME);
                    responseDTO.setTransactionStatus(transactionStatus);
                    return responseDTO;
                }

                @Override
                public XfaceCmsAdminResourceModifyStatusResponseDTO resourceModifyStatus(@Valid XfaceCmsAdminResourceModifyStatusRequestDTO requestDTO) {
                    XfaceCmsAdminResourceModifyStatusResponseDTO responseDTO = new XfaceCmsAdminResourceModifyStatusResponseDTO();
                    TransactionStatus transactionStatus = new TransactionStatus();
                    transactionStatus.setError("Call remote(resourceModifyStatus) service error.", ServerName.APPLICATION_NAME);
                    responseDTO.setTransactionStatus(transactionStatus);
                    return responseDTO;
                }
            };
        }
    }
}
