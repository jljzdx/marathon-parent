package com.newera.marathon.microface.system;

import com.newera.marathon.dto.system.inquiry.XfaceSysUserInquiryPageRequestDTO;
import com.newera.marathon.dto.system.inquiry.XfaceSysUserInquiryPageResponseDTO;
import com.newera.marathon.dto.system.inquiry.XfaceSysUserModifyInquiryRequestDTO;
import com.newera.marathon.dto.system.inquiry.XfaceSysUserModifyInquiryResponseDTO;
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
        fallbackFactory = SysUserMicroService.SysUserMicroServiceImpl.class
)
public interface SysUserMicroService {
    @PostMapping("/sys/user/inquiry/page")
    XfaceSysUserInquiryPageResponseDTO sysUserInquiryPage(@Valid @RequestBody XfaceSysUserInquiryPageRequestDTO requestDTO);

    @PostMapping("/sys/user/addition")
    XfaceSysUserAdditionResponseDTO sysUserAddition(@Valid @RequestBody XfaceSysUserAdditionRequestDTO requestDTO);

    @PostMapping("/sys/user/modify/inquiry")
    XfaceSysUserModifyInquiryResponseDTO sysUserModifyInquiry(@Valid @RequestBody XfaceSysUserModifyInquiryRequestDTO requestDTO);

    @PostMapping("/sys/user/modify")
    XfaceSysUserModifyResponseDTO sysUserModify(@Valid @RequestBody XfaceSysUserModifyRequestDTO requestDTO);

    @PostMapping("/sys/user/modify/base/info")
    public XfaceSysUserBaseInfoModifyResponseDTO sysUserBaseInfoModify(@Valid @RequestBody XfaceSysUserBaseInfoModifyRequestDTO requestDTO);

    @PostMapping("/sys/user/modify/status")
    XfaceSysUserModifyStatusResponseDTO sysUserModifyStatus(@Valid @RequestBody XfaceSysUserModifyStatusRequestDTO requestDTO);

    @PostMapping("/sys/user/modify/password")
    XfaceSysUserModifyPasswordResponseDTO sysUserModifyPassword(@Valid @RequestBody XfaceSysUserModifyPasswordRequestDTO requestDTO);
    @PostMapping("/sys/user/reset/password")
    public XfaceSysUserResetPasswordResponseDTO sysUserResetPassword(@Valid @RequestBody XfaceSysUserResetPasswordRequestDTO requestDTO);
    @Component
    class SysUserMicroServiceImpl implements FallbackFactory<SysUserMicroService> {
        private Logger logger = LoggerFactory.getLogger(SysUserMicroServiceImpl.class);
        @Override
        public SysUserMicroService create(Throwable throwable) {
            logger.error("fallback reason:{}",throwable.getMessage());
            return new SysUserMicroService(){
                @Override
                public XfaceSysUserInquiryPageResponseDTO sysUserInquiryPage(@Valid XfaceSysUserInquiryPageRequestDTO requestDTO) {
                    XfaceSysUserInquiryPageResponseDTO responseDTO = new XfaceSysUserInquiryPageResponseDTO();
                    TransactionStatus transactionStatus = new TransactionStatus();
                    transactionStatus.setError("Call remote(sysUserInquiryPage) service error.",SysServer.APPLICATION_NAME);
                    responseDTO.setTransactionStatus(transactionStatus);
                    return responseDTO;
                }

                @Override
                public XfaceSysUserAdditionResponseDTO sysUserAddition(@Valid XfaceSysUserAdditionRequestDTO requestDTO) {
                    XfaceSysUserAdditionResponseDTO responseDTO = new XfaceSysUserAdditionResponseDTO();
                    TransactionStatus transactionStatus = new TransactionStatus();
                    transactionStatus.setError("Call remote(sysUserAddition) service error.",SysServer.APPLICATION_NAME);
                    responseDTO.setTransactionStatus(transactionStatus);
                    return responseDTO;
                }

                @Override
                public XfaceSysUserModifyInquiryResponseDTO sysUserModifyInquiry(@Valid XfaceSysUserModifyInquiryRequestDTO requestDTO) {
                    XfaceSysUserModifyInquiryResponseDTO responseDTO = new XfaceSysUserModifyInquiryResponseDTO();
                    TransactionStatus transactionStatus = new TransactionStatus();
                    transactionStatus.setError("Call remote(sysUserModifyInquiry) service error.",SysServer.APPLICATION_NAME);
                    responseDTO.setTransactionStatus(transactionStatus);
                    return responseDTO;
                }

                @Override
                public XfaceSysUserModifyResponseDTO sysUserModify(@Valid XfaceSysUserModifyRequestDTO requestDTO) {
                    XfaceSysUserModifyResponseDTO responseDTO = new XfaceSysUserModifyResponseDTO();
                    TransactionStatus transactionStatus = new TransactionStatus();
                    transactionStatus.setError("Call remote(sysUserModify) service error.",SysServer.APPLICATION_NAME);
                    responseDTO.setTransactionStatus(transactionStatus);
                    return responseDTO;
                }

                @Override
                public XfaceSysUserBaseInfoModifyResponseDTO sysUserBaseInfoModify(@Valid XfaceSysUserBaseInfoModifyRequestDTO requestDTO) {
                    XfaceSysUserBaseInfoModifyResponseDTO responseDTO = new XfaceSysUserBaseInfoModifyResponseDTO();
                    TransactionStatus transactionStatus = new TransactionStatus();
                    transactionStatus.setError("Call remote(sysUserBaseInfoModify) service error.",SysServer.APPLICATION_NAME);
                    responseDTO.setTransactionStatus(transactionStatus);
                    return responseDTO;
                }

                @Override
                public XfaceSysUserModifyStatusResponseDTO sysUserModifyStatus(@Valid XfaceSysUserModifyStatusRequestDTO requestDTO) {
                    XfaceSysUserModifyStatusResponseDTO responseDTO = new XfaceSysUserModifyStatusResponseDTO();
                    TransactionStatus transactionStatus = new TransactionStatus();
                    transactionStatus.setError("Call remote(sysUserModifyStatus) service error.",SysServer.APPLICATION_NAME);
                    responseDTO.setTransactionStatus(transactionStatus);
                    return responseDTO;
                }

                @Override
                public XfaceSysUserModifyPasswordResponseDTO sysUserModifyPassword(@Valid XfaceSysUserModifyPasswordRequestDTO requestDTO) {
                    XfaceSysUserModifyPasswordResponseDTO responseDTO = new XfaceSysUserModifyPasswordResponseDTO();
                    TransactionStatus transactionStatus = new TransactionStatus();
                    transactionStatus.setError("Call remote(sysUserModifyPassword) service error.",SysServer.APPLICATION_NAME);
                    responseDTO.setTransactionStatus(transactionStatus);
                    return responseDTO;
                }

                @Override
                public XfaceSysUserResetPasswordResponseDTO sysUserResetPassword(@Valid XfaceSysUserResetPasswordRequestDTO requestDTO) {
                    XfaceSysUserResetPasswordResponseDTO responseDTO = new XfaceSysUserResetPasswordResponseDTO();
                    TransactionStatus transactionStatus = new TransactionStatus();
                    transactionStatus.setError("Call remote(sysUserResetPassword) service error.",SysServer.APPLICATION_NAME);
                    responseDTO.setTransactionStatus(transactionStatus);
                    return responseDTO;
                }
            };
        }
    }
}
