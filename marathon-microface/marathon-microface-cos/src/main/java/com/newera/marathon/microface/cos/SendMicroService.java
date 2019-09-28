package com.newera.marathon.microface.cos;

import com.newera.marathon.dto.cos.maintenance.XfaceCosCheckSmsCodeRequestDTO;
import com.newera.marathon.dto.cos.maintenance.XfaceCosCheckSmsCodeResponseDTO;
import com.newera.marathon.dto.cos.maintenance.XfaceCosSendSmsRequestDTO;
import com.newera.marathon.dto.cos.maintenance.XfaceCosSendSmsResponseDTO;
import com.newera.marathon.dto.system.maintenance.XfaceGenearteCaptchaResponseDTO;
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
        fallbackFactory = SendMicroService.SendMicroServiceImpl.class
)
public interface SendMicroService {
    @PostMapping("/sms/send")
    XfaceCosSendSmsResponseDTO sendSms(@Valid @RequestBody XfaceCosSendSmsRequestDTO requestDTO);

    @PostMapping("/sms/code/check")
    XfaceCosCheckSmsCodeResponseDTO checkSmsCode(@Valid @RequestBody XfaceCosCheckSmsCodeRequestDTO requestDTO);

    @PostMapping("/generate/captcha")
    public XfaceGenearteCaptchaResponseDTO generateCaptcha();

    @Component
    class SendMicroServiceImpl implements FallbackFactory<SendMicroService> {
        private Logger logger = LoggerFactory.getLogger(SendMicroServiceImpl.class);
        @Override
        public SendMicroService create(Throwable throwable) {
            logger.error("fallback reason:{}",throwable.getMessage());
            return new SendMicroService(){
                @Override
                public XfaceCosSendSmsResponseDTO sendSms(@Valid XfaceCosSendSmsRequestDTO requestDTO) {
                    XfaceCosSendSmsResponseDTO responseDTO = new XfaceCosSendSmsResponseDTO();
                    TransactionStatus transactionStatus = new TransactionStatus();
                    transactionStatus.setError("Call remote(sendSms) service error.", ServerName.APPLICATION_NAME);
                    responseDTO.setTransactionStatus(transactionStatus);
                    return responseDTO;
                }

                @Override
                public XfaceCosCheckSmsCodeResponseDTO checkSmsCode(@Valid XfaceCosCheckSmsCodeRequestDTO requestDTO) {
                    XfaceCosCheckSmsCodeResponseDTO responseDTO = new XfaceCosCheckSmsCodeResponseDTO();
                    TransactionStatus transactionStatus = new TransactionStatus();
                    transactionStatus.setError("Call remote(checkSmsCode) service error.", ServerName.APPLICATION_NAME);
                    responseDTO.setTransactionStatus(transactionStatus);
                    return responseDTO;
                }

                @Override
                public XfaceGenearteCaptchaResponseDTO generateCaptcha() {
                    XfaceGenearteCaptchaResponseDTO responseDTO = new XfaceGenearteCaptchaResponseDTO();
                    TransactionStatus transactionStatus = new TransactionStatus();
                    transactionStatus.setError("Call remote(generateCaptcha) service error.", ServerName.APPLICATION_NAME);
                    responseDTO.setTransactionStatus(transactionStatus);
                    return responseDTO;
                }
            };
        }
    }
}
