package com.newera.marathon.service.cust.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.newera.marathon.dto.cust.maintenance.XfaceCustCustomerRegisterRequestDTO;
import com.newera.marathon.dto.cust.maintenance.XfaceCustCustomerRegisterResponseDTO;
import com.newera.marathon.service.cust.entity.Customer;

/**
 * <p>
 * 客户信息表 服务类
 * </p>
 *
 * @author MicroBin
 * @since 2019-09-27
 */
public interface CustomerService extends IService<Customer> {

    XfaceCustCustomerRegisterResponseDTO doCustomerRegister(XfaceCustCustomerRegisterRequestDTO requestDTO);
}
