package com.newera.marathon.service.cust.controller;

import com.newera.marathon.dto.cust.maintenance.XfaceCustCustomerLoginRequestDTO;
import com.newera.marathon.dto.cust.maintenance.XfaceCustCustomerLoginResponseDTO;
import com.newera.marathon.dto.cust.maintenance.XfaceCustCustomerRegisterRequestDTO;
import com.newera.marathon.dto.cust.maintenance.XfaceCustCustomerRegisterResponseDTO;
import com.newera.marathon.service.cust.service.CustomerService;
import com.spaking.boot.starter.core.annotation.BusinessLogger;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService sysUserService;

    @BusinessLogger(key = "CUST",value = "customerRegister")
    @ApiOperation(value="客户注册", notes="客户注册")
    @ApiImplicitParam(name = "requestDTO", value = "入参对象", dataType = "XfaceCustCustomerRegisterRequestDTO")
    @PostMapping("/customer/register")
    public XfaceCustCustomerRegisterResponseDTO customerRegister(@Valid @RequestBody XfaceCustCustomerRegisterRequestDTO requestDTO){
        XfaceCustCustomerRegisterResponseDTO responseDTO = sysUserService.doCustomerRegister(requestDTO);
        return responseDTO;
    }

    @BusinessLogger(key = "CUST",value = "customerLogin")
    @ApiOperation(value="客户登陆", notes="客户登陆")
    @ApiImplicitParam(name = "requestDTO", value = "入参对象", dataType = "XfaceCustCustomerLoginRequestDTO")
    @PostMapping("/customer/login")
    public XfaceCustCustomerLoginResponseDTO customerLogin(@Valid @RequestBody XfaceCustCustomerLoginRequestDTO requestDTO){
        XfaceCustCustomerLoginResponseDTO responseDTO = sysUserService.doCustomerLogin(requestDTO);
        return responseDTO;
    }
}
