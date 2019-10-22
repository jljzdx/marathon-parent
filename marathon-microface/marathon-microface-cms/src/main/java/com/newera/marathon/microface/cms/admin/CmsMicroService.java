package com.newera.marathon.microface.cms.admin;

import com.newera.marathon.dto.cms.maintenance.XfaceCmsAdminLoginRequestDTO;
import com.newera.marathon.dto.cms.maintenance.XfaceCmsAdminLoginResponseDTO;
import com.newera.marathon.microface.cms.ServerName;
import com.spaking.boot.starter.core.model.TransactionStatus;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = ServerName.APPLICATION_NAME,
        fallbackFactory = CmsMicroService.CmsMicroServiceImpl.class
)
public interface CmsMicroService {
    @PostMapping({"/cms/login/auth"})
    XfaceCmsAdminLoginResponseDTO loginAuth(@RequestBody XfaceCmsAdminLoginRequestDTO requestDTO);

    @Component
    class CmsMicroServiceImpl implements FallbackFactory<CmsMicroService> {
        private Logger logger = LoggerFactory.getLogger(CmsMicroServiceImpl.class);
        @Override
        public CmsMicroService create(Throwable throwable) {
            logger.error("fallback reason:{}",throwable.getMessage());
            return new CmsMicroService(){
                @Override
                public XfaceCmsAdminLoginResponseDTO loginAuth(XfaceCmsAdminLoginRequestDTO requestDTO) {
                    XfaceCmsAdminLoginResponseDTO responseDTO = new XfaceCmsAdminLoginResponseDTO();
                    TransactionStatus transactionStatus = new TransactionStatus();
                    transactionStatus.setError("Call remote(sysLoginAuth) service error.", ServerName.APPLICATION_NAME);
                    responseDTO.setTransactionStatus(transactionStatus);
                    return responseDTO;
                }
            };
        }
    }
}
