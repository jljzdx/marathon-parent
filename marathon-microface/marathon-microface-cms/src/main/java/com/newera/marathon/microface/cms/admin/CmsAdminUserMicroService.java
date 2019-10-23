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
        fallbackFactory = CmsAdminUserMicroService.AdminUserMicroServiceImpl.class
)
public interface CmsAdminUserMicroService {
    @PostMapping("/cms/admin/user/inquiry/page")
    XfaceCmsAdminUserInquiryPageResponseDTO userInquiryPage(@Valid @RequestBody XfaceCmsAdminUserInquiryPageRequestDTO requestDTO);

    @PostMapping("/cms/admin/user/addition")
    XfaceCmsAdminUserAdditionResponseDTO userAddition(@Valid @RequestBody XfaceCmsAdminUserAdditionRequestDTO requestDTO);

    @PostMapping("/cms/admin/user/modify/inquiry")
    XfaceCmsAdminUserModifyInquiryResponseDTO userModifyInquiry(@Valid @RequestBody XfaceCmsAdminUserModifyInquiryRequestDTO requestDTO);

    @PostMapping("/cms/admin/user/modify")
    XfaceCmsAdminUserModifyResponseDTO userModify(@Valid @RequestBody XfaceCmsAdminUserModifyRequestDTO requestDTO);

    @PostMapping("/cms/admin/user/base/info/modify/inquiry")
    XfaceCmsAdminUserBaseInfoModifyInquiryResponseDTO userBaseInfoModifyInquiry(@Valid @RequestBody XfaceCmsAdminUserBaseInfoModifyInquiryRequestDTO requestDTO);

    @PostMapping("/cms/admin/user/base/info/modify")
    XfaceCmsAdminUserBaseInfoModifyResponseDTO userBaseInfoModify(@Valid @RequestBody XfaceCmsAdminUserBaseInfoModifyRequestDTO requestDTO);

    @PostMapping("/cms/admin/user/modify/status")
    XfaceCmsAdminUserModifyStatusResponseDTO userModifyStatus(@Valid @RequestBody XfaceCmsAdminUserModifyStatusRequestDTO requestDTO);

    @PostMapping("/cms/admin/user/modify/password")
    XfaceCmsAdminUserModifyPasswordResponseDTO userModifyPassword(@Valid @RequestBody XfaceCmsAdminUserModifyPasswordRequestDTO requestDTO);

    @PostMapping("/cms/admin/user/reset/password")
    XfaceCmsAdminUserResetPasswordResponseDTO userResetPassword(@Valid @RequestBody XfaceCmsAdminUserResetPasswordRequestDTO requestDTO);

    @PostMapping("/cms/admin/user/left/menu/inquiry")
    XfaceCmsAdminLeftMenuInquiryResponseDTO leftMenuInquiry(@Valid @RequestBody XfaceCmsAdminLeftMenuInquiryRequestDTO requestDTO);

    @PostMapping("/cms/admin/user/permissions/inquiry")
    XfaceCmsAdminPermissionsInquiryResponseDTO permissionsInquiry(@Valid @RequestBody XfaceCmsAdminPermissionsInquiryRequestDTO requestDTO);

    @Component
    class AdminUserMicroServiceImpl implements FallbackFactory<CmsAdminUserMicroService> {
        private Logger logger = LoggerFactory.getLogger(AdminUserMicroServiceImpl.class);
        @Override
        public CmsAdminUserMicroService create(Throwable throwable) {
            logger.error("fallback reason:{}",throwable.getMessage());
            return new CmsAdminUserMicroService(){
                @Override
                public XfaceCmsAdminUserInquiryPageResponseDTO userInquiryPage(@Valid XfaceCmsAdminUserInquiryPageRequestDTO requestDTO) {
                    XfaceCmsAdminUserInquiryPageResponseDTO responseDTO = new XfaceCmsAdminUserInquiryPageResponseDTO();
                    TransactionStatus transactionStatus = new TransactionStatus();
                    transactionStatus.setError("Call remote(userInquiryPage) service error.", ServerName.APPLICATION_NAME);
                    responseDTO.setTransactionStatus(transactionStatus);
                    return responseDTO;
                }

                @Override
                public XfaceCmsAdminUserAdditionResponseDTO userAddition(@Valid XfaceCmsAdminUserAdditionRequestDTO requestDTO) {
                    XfaceCmsAdminUserAdditionResponseDTO responseDTO = new XfaceCmsAdminUserAdditionResponseDTO();
                    TransactionStatus transactionStatus = new TransactionStatus();
                    transactionStatus.setError("Call remote(userAddition) service error.", ServerName.APPLICATION_NAME);
                    responseDTO.setTransactionStatus(transactionStatus);
                    return responseDTO;
                }

                @Override
                public XfaceCmsAdminUserModifyInquiryResponseDTO userModifyInquiry(@Valid XfaceCmsAdminUserModifyInquiryRequestDTO requestDTO) {
                    XfaceCmsAdminUserModifyInquiryResponseDTO responseDTO = new XfaceCmsAdminUserModifyInquiryResponseDTO();
                    TransactionStatus transactionStatus = new TransactionStatus();
                    transactionStatus.setError("Call remote(userModifyInquiry) service error.", ServerName.APPLICATION_NAME);
                    responseDTO.setTransactionStatus(transactionStatus);
                    return responseDTO;
                }

                @Override
                public XfaceCmsAdminUserModifyResponseDTO userModify(@Valid XfaceCmsAdminUserModifyRequestDTO requestDTO) {
                    XfaceCmsAdminUserModifyResponseDTO responseDTO = new XfaceCmsAdminUserModifyResponseDTO();
                    TransactionStatus transactionStatus = new TransactionStatus();
                    transactionStatus.setError("Call remote(userModify) service error.", ServerName.APPLICATION_NAME);
                    responseDTO.setTransactionStatus(transactionStatus);
                    return responseDTO;
                }

                @Override
                public XfaceCmsAdminUserBaseInfoModifyInquiryResponseDTO userBaseInfoModifyInquiry(@Valid XfaceCmsAdminUserBaseInfoModifyInquiryRequestDTO requestDTO) {
                    XfaceCmsAdminUserBaseInfoModifyInquiryResponseDTO responseDTO = new XfaceCmsAdminUserBaseInfoModifyInquiryResponseDTO();
                    TransactionStatus transactionStatus = new TransactionStatus();
                    transactionStatus.setError("Call remote(userBaseInfoModifyInquiry) service error.", ServerName.APPLICATION_NAME);
                    responseDTO.setTransactionStatus(transactionStatus);
                    return responseDTO;
                }

                @Override
                public XfaceCmsAdminUserBaseInfoModifyResponseDTO userBaseInfoModify(@Valid XfaceCmsAdminUserBaseInfoModifyRequestDTO requestDTO) {
                    XfaceCmsAdminUserBaseInfoModifyResponseDTO responseDTO = new XfaceCmsAdminUserBaseInfoModifyResponseDTO();
                    TransactionStatus transactionStatus = new TransactionStatus();
                    transactionStatus.setError("Call remote(userBaseInfoModify) service error.", ServerName.APPLICATION_NAME);
                    responseDTO.setTransactionStatus(transactionStatus);
                    return responseDTO;
                }

                @Override
                public XfaceCmsAdminUserModifyStatusResponseDTO userModifyStatus(@Valid XfaceCmsAdminUserModifyStatusRequestDTO requestDTO) {
                    XfaceCmsAdminUserModifyStatusResponseDTO responseDTO = new XfaceCmsAdminUserModifyStatusResponseDTO();
                    TransactionStatus transactionStatus = new TransactionStatus();
                    transactionStatus.setError("Call remote(userModifyStatus) service error.", ServerName.APPLICATION_NAME);
                    responseDTO.setTransactionStatus(transactionStatus);
                    return responseDTO;
                }

                @Override
                public XfaceCmsAdminUserModifyPasswordResponseDTO userModifyPassword(@Valid XfaceCmsAdminUserModifyPasswordRequestDTO requestDTO) {
                    XfaceCmsAdminUserModifyPasswordResponseDTO responseDTO = new XfaceCmsAdminUserModifyPasswordResponseDTO();
                    TransactionStatus transactionStatus = new TransactionStatus();
                    transactionStatus.setError("Call remote(userModifyPassword) service error.", ServerName.APPLICATION_NAME);
                    responseDTO.setTransactionStatus(transactionStatus);
                    return responseDTO;
                }

                @Override
                public XfaceCmsAdminUserResetPasswordResponseDTO userResetPassword(@Valid XfaceCmsAdminUserResetPasswordRequestDTO requestDTO) {
                    XfaceCmsAdminUserResetPasswordResponseDTO responseDTO = new XfaceCmsAdminUserResetPasswordResponseDTO();
                    TransactionStatus transactionStatus = new TransactionStatus();
                    transactionStatus.setError("Call remote(userResetPassword) service error.", ServerName.APPLICATION_NAME);
                    responseDTO.setTransactionStatus(transactionStatus);
                    return responseDTO;
                }

                @Override
                public XfaceCmsAdminLeftMenuInquiryResponseDTO leftMenuInquiry(@Valid XfaceCmsAdminLeftMenuInquiryRequestDTO requestDTO) {
                    XfaceCmsAdminLeftMenuInquiryResponseDTO responseDTO = new XfaceCmsAdminLeftMenuInquiryResponseDTO();
                    TransactionStatus transactionStatus = new TransactionStatus();
                    transactionStatus.setError("Call remote(leftMenuInquiry) service error.", ServerName.APPLICATION_NAME);
                    responseDTO.setTransactionStatus(transactionStatus);
                    return responseDTO;
                }

                @Override
                public XfaceCmsAdminPermissionsInquiryResponseDTO permissionsInquiry(@Valid XfaceCmsAdminPermissionsInquiryRequestDTO requestDTO) {
                    XfaceCmsAdminPermissionsInquiryResponseDTO responseDTO = new XfaceCmsAdminPermissionsInquiryResponseDTO();
                    TransactionStatus transactionStatus = new TransactionStatus();
                    transactionStatus.setError("Call remote(permissionsInquiry) service error.", ServerName.APPLICATION_NAME);
                    responseDTO.setTransactionStatus(transactionStatus);
                    return responseDTO;
                }
            };
        }
    }
}
