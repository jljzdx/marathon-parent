package com.newera.marathon.microface.cms.system;

import com.newera.marathon.dto.system.maintenance.XfaceSysLoginRequestDTO;
import com.newera.marathon.dto.system.maintenance.XfaceSysLoginResponseDTO;
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
        fallbackFactory = SysMicroService.SysMicroServiceImpl.class
)
public interface SysMicroService {
    @PostMapping({"/sys/login/auth"})
    XfaceSysLoginResponseDTO sysLoginAuth(@RequestBody XfaceSysLoginRequestDTO requestDTO);

    @Component
    class SysMicroServiceImpl implements FallbackFactory<SysMicroService> {
        private Logger logger = LoggerFactory.getLogger(SysMicroServiceImpl.class);
        @Override
        public SysMicroService create(Throwable throwable) {
            logger.error("fallback reason:{}",throwable.getMessage());
            return new SysMicroService(){
                @Override
                public XfaceSysLoginResponseDTO sysLoginAuth(XfaceSysLoginRequestDTO requestDTO) {
                    XfaceSysLoginResponseDTO responseDTO = new XfaceSysLoginResponseDTO();
                    TransactionStatus transactionStatus = new TransactionStatus();
                    transactionStatus.setError("Call remote(sysLoginAuth) service error.", ServerName.APPLICATION_NAME);
                    responseDTO.setTransactionStatus(transactionStatus);
                    return responseDTO;
                }
            };
        }
    }
}
