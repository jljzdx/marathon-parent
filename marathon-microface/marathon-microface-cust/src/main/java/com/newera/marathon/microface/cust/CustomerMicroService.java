package com.newera.marathon.microface.cust;

import com.newera.marathon.dto.cust.maintenance.XfaceCustCustomerLoginRequestDTO;
import com.newera.marathon.dto.cust.maintenance.XfaceCustCustomerLoginResponseDTO;
import com.newera.marathon.dto.cust.maintenance.XfaceCustCustomerRegisterRequestDTO;
import com.newera.marathon.dto.cust.maintenance.XfaceCustCustomerRegisterResponseDTO;
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
        fallbackFactory = CustomerMicroService.CustomerMicroServiceImpl.class
)
public interface CustomerMicroService {
    @PostMapping({"/cust/customer/register"})
    XfaceCustCustomerRegisterResponseDTO customerRegister(@Valid @RequestBody XfaceCustCustomerRegisterRequestDTO requestDTO);

    @PostMapping("/cust/customer/login")
    public XfaceCustCustomerLoginResponseDTO customerLogin(@Valid @RequestBody XfaceCustCustomerLoginRequestDTO requestDTO);

    @Component
    class CustomerMicroServiceImpl implements FallbackFactory<CustomerMicroService> {
        private Logger logger = LoggerFactory.getLogger(CustomerMicroServiceImpl.class);
        @Override
        public CustomerMicroService create(Throwable throwable) {
            logger.error("fallback reason:{}",throwable.getMessage());
            return new CustomerMicroService(){
                @Override
                public XfaceCustCustomerRegisterResponseDTO customerRegister(@Valid XfaceCustCustomerRegisterRequestDTO requestDTO) {
                    XfaceCustCustomerRegisterResponseDTO responseDTO = new XfaceCustCustomerRegisterResponseDTO();
                    TransactionStatus transactionStatus = new TransactionStatus();
                    transactionStatus.setError("Call remote(customerRegister) service error.", ServerName.APPLICATION_NAME);
                    responseDTO.setTransactionStatus(transactionStatus);
                    return responseDTO;
                }

                @Override
                public XfaceCustCustomerLoginResponseDTO customerLogin(@Valid XfaceCustCustomerLoginRequestDTO requestDTO) {
                    XfaceCustCustomerLoginResponseDTO responseDTO = new XfaceCustCustomerLoginResponseDTO();
                    TransactionStatus transactionStatus = new TransactionStatus();
                    transactionStatus.setError("Call remote(customerLogin) service error.", ServerName.APPLICATION_NAME);
                    responseDTO.setTransactionStatus(transactionStatus);
                    return responseDTO;
                }
            };
        }
    }
}
