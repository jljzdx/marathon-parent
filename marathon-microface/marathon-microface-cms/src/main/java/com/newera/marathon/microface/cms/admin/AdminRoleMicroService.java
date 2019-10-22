package com.newera.marathon.microface.cms.admin;

import com.newera.marathon.dto.cms.inquiry.*;
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
        fallbackFactory = AdminRoleMicroService.AdminRoleMicroServiceImpl.class
)
public interface AdminRoleMicroService {
    @PostMapping("/cms/admin/role/inquiry/select")
    XfaceCmsAdminRoleSelectInquiryResponseDTO roleInquirySelect();

    @PostMapping("/cms/admin/role/inquiry/page")
    XfaceCmsAdminRoleInquiryPageResponseDTO roleInquiryPage(@Valid @RequestBody XfaceCmsAdminRoleInquiryPageRequestDTO requestDTO);

    @PostMapping("/cms/admin/role/addition")
    XfaceCmsAdminRoleAdditionResponseDTO roleAddition(@Valid @RequestBody XfaceCmsAdminRoleAdditionRequestDTO requestDTO);

    @PostMapping("/cms/admin/role/modify/inquiry")
    XfaceCmsAdminRoleModifyInquiryResponseDTO roleModifyInquiry(@Valid @RequestBody XfaceCmsAdminRoleModifyInquiryRequestDTO requestDTO);

    @PostMapping("/cms/admin/role/modify")
    XfaceCmsAdminRoleModifyResponseDTO roleModify(@Valid @RequestBody XfaceCmsAdminRoleModifyRequestDTO requestDTO);

    @PostMapping("/cms/admin/role/modify/status")
    XfaceCmsAdminRoleModifyStatusResponseDTO roleModifyStatus(@Valid @RequestBody XfaceCmsAdminRoleModifyStatusRequestDTO requestDTO);

    @PostMapping("/cms/admin/role/auth/inquiry")
    XfaceCmsAdminRoleAuthInquiryResponseDTO roleAuthInquiry(@Valid @RequestBody XfaceCmsAdminRoleAuthInquiryRequestDTO requestDTO);

    @PostMapping("/cms/admin/role/auth")
    XfaceCmsAdminRoleAuthResponseDTO roleAuth(@Valid @RequestBody XfaceCmsAdminRoleAuthRequestDTO requestDTO);

    @Component
    class AdminRoleMicroServiceImpl implements FallbackFactory<AdminRoleMicroService> {
        private Logger logger = LoggerFactory.getLogger(AdminRoleMicroServiceImpl.class);
        @Override
        public AdminRoleMicroService create(Throwable throwable) {
            logger.error("fallback reason:{}",throwable.getMessage());
            return new AdminRoleMicroService(){
                @Override
                public XfaceCmsAdminRoleSelectInquiryResponseDTO roleInquirySelect() {
                    XfaceCmsAdminRoleSelectInquiryResponseDTO responseDTO = new XfaceCmsAdminRoleSelectInquiryResponseDTO();
                    TransactionStatus transactionStatus = new TransactionStatus();
                    transactionStatus.setError("Call remote(roleInquirySelect) service error.", ServerName.APPLICATION_NAME);
                    responseDTO.setTransactionStatus(transactionStatus);
                    return responseDTO;
                }

                @Override
                public XfaceCmsAdminRoleInquiryPageResponseDTO roleInquiryPage(@Valid XfaceCmsAdminRoleInquiryPageRequestDTO requestDTO) {
                    XfaceCmsAdminRoleInquiryPageResponseDTO responseDTO = new XfaceCmsAdminRoleInquiryPageResponseDTO();
                    TransactionStatus transactionStatus = new TransactionStatus();
                    transactionStatus.setError("Call remote(roleInquiryPage) service error.", ServerName.APPLICATION_NAME);
                    responseDTO.setTransactionStatus(transactionStatus);
                    return responseDTO;
                }

                @Override
                public XfaceCmsAdminRoleAdditionResponseDTO roleAddition(@Valid XfaceCmsAdminRoleAdditionRequestDTO requestDTO) {
                    XfaceCmsAdminRoleAdditionResponseDTO responseDTO = new XfaceCmsAdminRoleAdditionResponseDTO();
                    TransactionStatus transactionStatus = new TransactionStatus();
                    transactionStatus.setError("Call remote(roleAddition) service error.", ServerName.APPLICATION_NAME);
                    responseDTO.setTransactionStatus(transactionStatus);
                    return responseDTO;
                }

                @Override
                public XfaceCmsAdminRoleModifyInquiryResponseDTO roleModifyInquiry(@Valid XfaceCmsAdminRoleModifyInquiryRequestDTO requestDTO) {
                    XfaceCmsAdminRoleModifyInquiryResponseDTO responseDTO = new XfaceCmsAdminRoleModifyInquiryResponseDTO();
                    TransactionStatus transactionStatus = new TransactionStatus();
                    transactionStatus.setError("Call remote(roleModifyInquiry) service error.", ServerName.APPLICATION_NAME);
                    responseDTO.setTransactionStatus(transactionStatus);
                    return responseDTO;
                }

                @Override
                public XfaceCmsAdminRoleModifyResponseDTO roleModify(@Valid XfaceCmsAdminRoleModifyRequestDTO requestDTO) {
                    XfaceCmsAdminRoleModifyResponseDTO responseDTO = new XfaceCmsAdminRoleModifyResponseDTO();
                    TransactionStatus transactionStatus = new TransactionStatus();
                    transactionStatus.setError("Call remote(roleModify) service error.", ServerName.APPLICATION_NAME);
                    responseDTO.setTransactionStatus(transactionStatus);
                    return responseDTO;
                }

                @Override
                public XfaceCmsAdminRoleModifyStatusResponseDTO roleModifyStatus(@Valid XfaceCmsAdminRoleModifyStatusRequestDTO requestDTO) {
                    XfaceCmsAdminRoleModifyStatusResponseDTO responseDTO = new XfaceCmsAdminRoleModifyStatusResponseDTO();
                    TransactionStatus transactionStatus = new TransactionStatus();
                    transactionStatus.setError("Call remote(roleModifyStatus) service error.", ServerName.APPLICATION_NAME);
                    responseDTO.setTransactionStatus(transactionStatus);
                    return responseDTO;
                }

                @Override
                public XfaceCmsAdminRoleAuthInquiryResponseDTO roleAuthInquiry(@Valid XfaceCmsAdminRoleAuthInquiryRequestDTO requestDTO) {
                    XfaceCmsAdminRoleAuthInquiryResponseDTO responseDTO = new XfaceCmsAdminRoleAuthInquiryResponseDTO();
                    TransactionStatus transactionStatus = new TransactionStatus();
                    transactionStatus.setError("Call remote(roleAuthInquiry) service error.", ServerName.APPLICATION_NAME);
                    responseDTO.setTransactionStatus(transactionStatus);
                    return responseDTO;
                }

                @Override
                public XfaceCmsAdminRoleAuthResponseDTO roleAuth(@Valid XfaceCmsAdminRoleAuthRequestDTO requestDTO) {
                    XfaceCmsAdminRoleAuthResponseDTO responseDTO = new XfaceCmsAdminRoleAuthResponseDTO();
                    TransactionStatus transactionStatus = new TransactionStatus();
                    transactionStatus.setError("Call remote(roleAuth) service error.", ServerName.APPLICATION_NAME);
                    responseDTO.setTransactionStatus(transactionStatus);
                    return responseDTO;
                }
            };
        }
    }
}
