package com.newera.marathon.microface.cms.system;

import com.newera.marathon.dto.system.inquiry.*;
import com.newera.marathon.dto.system.maintenance.*;
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
        fallbackFactory = SysRoleMicroService.SysRoleMicroServiceImpl.class
)
public interface SysRoleMicroService {
    @PostMapping("/sys/role/inquiry/select")
    public XfaceSysRoleSelectInquiryResponseDTO sysRoleInquirySelect();

    @PostMapping("/sys/role/inquiry/page")
    public XfaceSysRoleInquiryPageResponseDTO sysRoleInquiryPage(@Valid @RequestBody XfaceSysRoleInquiryPageRequestDTO requestDTO);

    @PostMapping("/sys/role/addition")
    public XfaceSysRoleAdditionResponseDTO sysRoleAddition(@Valid @RequestBody XfaceSysRoleAdditionRequestDTO requestDTO);

    @PostMapping("/sys/role/modify/inquiry")
    public XfaceSysRoleModifyInquiryResponseDTO sysRoleModifyInquiry(@Valid @RequestBody XfaceSysRoleModifyInquiryRequestDTO requestDTO);

    @PostMapping("/sys/role/modify")
    public XfaceSysRoleModifyResponseDTO sysRoleModify(@Valid @RequestBody XfaceSysRoleModifyRequestDTO requestDTO);

    @PostMapping("/sys/role/modify/status")
    public XfaceSysRoleModifyStatusResponseDTO sysRoleModifyStatus(@Valid @RequestBody XfaceSysRoleModifyStatusRequestDTO requestDTO);

    @PostMapping("/sys/role/auth/inquiry")
    public XfaceSysRoleAuthInquiryResponseDTO sysRoleAuthInquiry(@Valid @RequestBody XfaceSysRoleAuthInquiryRequestDTO requestDTO);

    @PostMapping("/sys/role/auth")
    public XfaceSysRoleAuthResponseDTO sysRoleAuth(@Valid @RequestBody XfaceSysRoleAuthRequestDTO requestDTO);

    @Component
    class SysRoleMicroServiceImpl implements FallbackFactory<SysRoleMicroService> {
        private Logger logger = LoggerFactory.getLogger(SysRoleMicroServiceImpl.class);
        @Override
        public SysRoleMicroService create(Throwable throwable) {
            logger.error("fallback reason:{}",throwable.getMessage());
            return new SysRoleMicroService(){
                @Override
                public XfaceSysRoleSelectInquiryResponseDTO sysRoleInquirySelect() {
                    XfaceSysRoleSelectInquiryResponseDTO responseDTO = new XfaceSysRoleSelectInquiryResponseDTO();
                    TransactionStatus transactionStatus = new TransactionStatus();
                    transactionStatus.setError("Call remote(sysRoleInquirySelect) service error.", ServerName.APPLICATION_NAME);
                    responseDTO.setTransactionStatus(transactionStatus);
                    return responseDTO;
                }

                @Override
                public XfaceSysRoleInquiryPageResponseDTO sysRoleInquiryPage(@Valid XfaceSysRoleInquiryPageRequestDTO requestDTO) {
                    XfaceSysRoleInquiryPageResponseDTO responseDTO = new XfaceSysRoleInquiryPageResponseDTO();
                    TransactionStatus transactionStatus = new TransactionStatus();
                    transactionStatus.setError("Call remote(sysRoleInquiryPage) service error.", ServerName.APPLICATION_NAME);
                    responseDTO.setTransactionStatus(transactionStatus);
                    return responseDTO;
                }

                @Override
                public XfaceSysRoleAdditionResponseDTO sysRoleAddition(@Valid XfaceSysRoleAdditionRequestDTO requestDTO) {
                    XfaceSysRoleAdditionResponseDTO responseDTO = new XfaceSysRoleAdditionResponseDTO();
                    TransactionStatus transactionStatus = new TransactionStatus();
                    transactionStatus.setError("Call remote(sysRoleAddition) service error.", ServerName.APPLICATION_NAME);
                    responseDTO.setTransactionStatus(transactionStatus);
                    return responseDTO;
                }

                @Override
                public XfaceSysRoleModifyInquiryResponseDTO sysRoleModifyInquiry(@Valid XfaceSysRoleModifyInquiryRequestDTO requestDTO) {
                    XfaceSysRoleModifyInquiryResponseDTO responseDTO = new XfaceSysRoleModifyInquiryResponseDTO();
                    TransactionStatus transactionStatus = new TransactionStatus();
                    transactionStatus.setError("Call remote(sysRoleModifyInquiry) service error.", ServerName.APPLICATION_NAME);
                    responseDTO.setTransactionStatus(transactionStatus);
                    return responseDTO;
                }

                @Override
                public XfaceSysRoleModifyResponseDTO sysRoleModify(@Valid XfaceSysRoleModifyRequestDTO requestDTO) {
                    XfaceSysRoleModifyResponseDTO responseDTO = new XfaceSysRoleModifyResponseDTO();
                    TransactionStatus transactionStatus = new TransactionStatus();
                    transactionStatus.setError("Call remote(sysRoleModify) service error.", ServerName.APPLICATION_NAME);
                    responseDTO.setTransactionStatus(transactionStatus);
                    return responseDTO;
                }

                @Override
                public XfaceSysRoleModifyStatusResponseDTO sysRoleModifyStatus(@Valid XfaceSysRoleModifyStatusRequestDTO requestDTO) {
                    XfaceSysRoleModifyStatusResponseDTO responseDTO = new XfaceSysRoleModifyStatusResponseDTO();
                    TransactionStatus transactionStatus = new TransactionStatus();
                    transactionStatus.setError("Call remote(sysRoleModifyStatus) service error.", ServerName.APPLICATION_NAME);
                    responseDTO.setTransactionStatus(transactionStatus);
                    return responseDTO;
                }

                @Override
                public XfaceSysRoleAuthInquiryResponseDTO sysRoleAuthInquiry(@Valid XfaceSysRoleAuthInquiryRequestDTO requestDTO) {
                    XfaceSysRoleAuthInquiryResponseDTO responseDTO = new XfaceSysRoleAuthInquiryResponseDTO();
                    TransactionStatus transactionStatus = new TransactionStatus();
                    transactionStatus.setError("Call remote(sysRoleAuthInquiry) service error.", ServerName.APPLICATION_NAME);
                    responseDTO.setTransactionStatus(transactionStatus);
                    return responseDTO;
                }

                @Override
                public XfaceSysRoleAuthResponseDTO sysRoleAuth(@Valid XfaceSysRoleAuthRequestDTO requestDTO) {
                    XfaceSysRoleAuthResponseDTO responseDTO = new XfaceSysRoleAuthResponseDTO();
                    TransactionStatus transactionStatus = new TransactionStatus();
                    transactionStatus.setError("Call remote(sysRoleAuth) service error.", ServerName.APPLICATION_NAME);
                    responseDTO.setTransactionStatus(transactionStatus);
                    return responseDTO;
                }
            };
        }
    }
}
