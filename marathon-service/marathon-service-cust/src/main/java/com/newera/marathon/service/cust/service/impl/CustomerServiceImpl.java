package com.newera.marathon.service.cust.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newera.marathon.service.cust.entity.Customer;
import com.newera.marathon.service.cust.mapper.CustomerMapper;
import com.newera.marathon.service.cust.service.CustomerService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 客户信息表 服务实现类
 * </p>
 *
 * @author MicroBin
 * @since 2019-09-27
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {

}
