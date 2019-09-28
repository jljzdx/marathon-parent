package com.newera.marathon.service.cust.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newera.marathon.dto.cust.maintenance.XfaceCustCustomerRegisterRequestDTO;
import com.newera.marathon.dto.cust.maintenance.XfaceCustCustomerRegisterResponseDTO;
import com.newera.marathon.service.cust.entity.Customer;
import com.newera.marathon.service.cust.mapper.CustomerMapper;
import com.newera.marathon.service.cust.model.ApplicationError;
import com.newera.marathon.service.cust.service.CustomerService;
import com.spaking.boot.starter.core.exception.BaseException;
import com.spaking.boot.starter.core.model.TransactionStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.script.DigestUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 客户信息表 服务实现类
 * </p>
 *
 * @author MicroBin
 * @since 2019-09-27
 */
@Slf4j
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public XfaceCustCustomerRegisterResponseDTO doCustomerRegister(XfaceCustCustomerRegisterRequestDTO requestDTO) {
        log.info("doCustomerRegister start");
        XfaceCustCustomerRegisterResponseDTO responseDTO = new XfaceCustCustomerRegisterResponseDTO();
        TransactionStatus transactionStatus = new TransactionStatus();

        //判断两次密码是否一致
        if(!requestDTO.getPassword().equals(requestDTO.getVerifyPassword())){
            throw new BaseException(ApplicationError.PASSWWORD_UNSAME.getMessage(),ApplicationError.PASSWWORD_UNSAME.getCode());
        }
        //判断用户名是否存在
        QueryWrapper<Customer> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name",requestDTO.getUserName());
        Customer customer = customerMapper.selectOne(wrapper);
        if(null != customer){
            throw new BaseException(ApplicationError.USER_NAME_EXISTED.getMessage(),ApplicationError.USER_NAME_EXISTED.getCode());
        }
        //判断电话是否存在
        QueryWrapper<Customer> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("phone",requestDTO.getPhone());
        Customer customer1 = customerMapper.selectOne(wrapper1);
        if(null != customer1){
            throw new BaseException(ApplicationError.PHONE_EXISTED.getMessage(),ApplicationError.PHONE_EXISTED.getCode());
        }
        Customer customer2 = new Customer();
        BeanUtils.copyProperties(requestDTO,customer2);
        customer2.setPassword(DigestUtils.sha1DigestAsHex(requestDTO.getPassword()));
        customer2.setCreateOperator(requestDTO.getUserName());
        //添加操作
        Boolean result = save(customer2);
        if (!result) {
            throw new BaseException(ApplicationError.ADDITION_FAILED.getMessage(), ApplicationError.ADDITION_FAILED.getCode());
        }
        responseDTO.setTransactionStatus(transactionStatus);
        log.info("doCustomerRegister end");
        return responseDTO;
    }
}
