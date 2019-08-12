package com.newera.marathon.microface.system;

import com.newera.marathon.dto.system.maintenance.XfaceGenearteCaptchaResponseDTO;
import com.newera.marathon.dto.system.maintenance.XfaceSysLoginAuthRequestDTO;
import com.newera.marathon.dto.system.maintenance.XfaceSysLoginAuthResponseDTO;
import com.spaking.boot.starter.core.model.TransactionStatus;
import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = SysServer.APPLICATION_NAME,
        fallbackFactory = SysMicroService.SysMicroServiceImpl.class
)
public interface SysMicroService {
    @PostMapping({"/sys/login/auth"})
    XfaceSysLoginAuthResponseDTO sysLoginAuth(@RequestBody XfaceSysLoginAuthRequestDTO requestDTO);

    @PostMapping("/sys/generate/captcha")
    XfaceGenearteCaptchaResponseDTO generateCaptcha();

    @Component
    class SysMicroServiceImpl implements FallbackFactory<SysMicroService> {
        private Logger logger = LoggerFactory.getLogger(SysMicroServiceImpl.class);
        @Override
        public SysMicroService create(Throwable throwable) {
            logger.error("fallback reason:{}",throwable.getMessage());
            return new SysMicroService(){
                @Override
                public XfaceSysLoginAuthResponseDTO sysLoginAuth(XfaceSysLoginAuthRequestDTO requestDTO) {
                    XfaceSysLoginAuthResponseDTO responseDTO = new XfaceSysLoginAuthResponseDTO();
                    TransactionStatus transactionStatus = new TransactionStatus();
                    transactionStatus.setError("Call remote(sysLoginAuth) service error.",SysServer.APPLICATION_NAME);
                    responseDTO.setTransactionStatus(transactionStatus);
                    return responseDTO;
                }
                @Override
                public XfaceGenearteCaptchaResponseDTO generateCaptcha() {
                    XfaceGenearteCaptchaResponseDTO responseDTO = new XfaceGenearteCaptchaResponseDTO();
                    TransactionStatus transactionStatus = new TransactionStatus();
                    transactionStatus.setError("Call remote(generateCaptcha) service error.",SysServer.APPLICATION_NAME);
                    responseDTO.setTransactionStatus(transactionStatus);
                    return responseDTO;
                }
            };
        }
    }
}
