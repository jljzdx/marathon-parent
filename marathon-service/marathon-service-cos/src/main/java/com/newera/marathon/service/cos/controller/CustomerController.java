package com.newera.marathon.service.cos.controller;

import com.newera.marathon.dto.cust.maintenance.XfaceCustCustomerLoginRequestDTO;
import com.newera.marathon.dto.cust.maintenance.XfaceCustCustomerLoginResponseDTO;
import com.newera.marathon.dto.cust.maintenance.XfaceCustCustomerRegisterRequestDTO;
import com.newera.marathon.dto.cust.maintenance.XfaceCustCustomerRegisterResponseDTO;
import com.newera.marathon.microface.cust.CustomerMicroService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerMicroService customerMicroService;

    @ApiOperation(value="客户注册", notes="客户注册")
    @ApiImplicitParam(name = "requestDTO", value = "入参对象", dataType = "XfaceCustCustomerRegisterRequestDTO")
    @PostMapping("/register")
    public XfaceCustCustomerRegisterResponseDTO customerRegister(@Valid @RequestBody XfaceCustCustomerRegisterRequestDTO requestDTO){
        XfaceCustCustomerRegisterResponseDTO responseDTO = customerMicroService.customerRegister(requestDTO);
        return responseDTO;
    }

    @ApiOperation(value="客户登陆", notes="客户登陆")
    @ApiImplicitParam(name = "requestDTO", value = "入参对象", dataType = "XfaceCustCustomerLoginRequestDTO")
    @PostMapping("/login")
    public XfaceCustCustomerLoginResponseDTO customerLogin(@Valid @RequestBody XfaceCustCustomerLoginRequestDTO requestDTO){
        XfaceCustCustomerLoginResponseDTO responseDTO = customerMicroService.customerLogin(requestDTO);
        return responseDTO;
    }
}
