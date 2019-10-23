package com.newera.marathon.microface.cos;

import com.newera.marathon.dto.cos.maintenance.*;
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
        fallbackFactory = CosMicroService.SendMicroServiceImpl.class
)
public interface CosMicroService {
    @PostMapping("/cos/sms/send")
    XfaceCosSendSmsResponseDTO sendSms(@Valid @RequestBody XfaceCosSendSmsRequestDTO requestDTO);

    @PostMapping("/cos/sms/code/check")
    XfaceCosCheckSmsCodeResponseDTO checkSmsCode(@Valid @RequestBody XfaceCosCheckSmsCodeRequestDTO requestDTO);

    @PostMapping("/cos/generate/captcha")
    XfaceCosGenearteCaptchaResponseDTO generateCaptcha();

    @PostMapping("/cos/mail/send")
    XfaceCosMailSendResponseDTO sendMail(@Valid @RequestBody XfaceCosMailSendRequestDTO requestDTO);

    @Component
    class SendMicroServiceImpl implements FallbackFactory<CosMicroService> {
        private Logger logger = LoggerFactory.getLogger(SendMicroServiceImpl.class);
        @Override
        public CosMicroService create(Throwable throwable) {
            logger.error("fallback reason:{}",throwable.getMessage());
            return new CosMicroService(){
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
                public XfaceCosGenearteCaptchaResponseDTO generateCaptcha() {
                    XfaceCosGenearteCaptchaResponseDTO responseDTO = new XfaceCosGenearteCaptchaResponseDTO();
                    TransactionStatus transactionStatus = new TransactionStatus();
                    transactionStatus.setError("Call remote(generateCaptcha) service error.", ServerName.APPLICATION_NAME);
                    responseDTO.setTransactionStatus(transactionStatus);
                    return responseDTO;
                }

                @Override
                public XfaceCosMailSendResponseDTO sendMail(@Valid XfaceCosMailSendRequestDTO requestDTO) {
                    XfaceCosMailSendResponseDTO responseDTO = new XfaceCosMailSendResponseDTO();
                    TransactionStatus transactionStatus = new TransactionStatus();
                    transactionStatus.setError("Call remote(sendMail) service error.", ServerName.APPLICATION_NAME);
                    responseDTO.setTransactionStatus(transactionStatus);
                    return responseDTO;
                }
            };
        }
    }
}
