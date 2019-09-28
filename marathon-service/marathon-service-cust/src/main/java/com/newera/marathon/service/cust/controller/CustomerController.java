package com.newera.marathon.service.cust.controller;

import com.newera.marathon.dto.cust.maintenance.XfaceCustCustomerRegisterRequestDTO;
import com.newera.marathon.dto.cust.maintenance.XfaceCustCustomerRegisterResponseDTO;
import com.newera.marathon.service.cust.service.CustomerService;
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
    private CustomerService sysUserService;

    @ApiOperation(value="客户注册", notes="客户注册")
    @ApiImplicitParam(name = "requestDTO", value = "入参对象", dataType = "XfaceCustCustomerRegisterRequestDTO")
    @PostMapping("/register")
    public XfaceCustCustomerRegisterResponseDTO customerRegister(@Valid @RequestBody XfaceCustCustomerRegisterRequestDTO requestDTO){
        XfaceCustCustomerRegisterResponseDTO responseDTO = sysUserService.doCustomerRegister(requestDTO);
        return responseDTO;
    }

}
