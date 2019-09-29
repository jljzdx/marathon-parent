package com.newera.marathon.service.cust.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newera.marathon.dto.cust.maintenance.XfaceCustCustomerLoginRequestDTO;
import com.newera.marathon.dto.cust.maintenance.XfaceCustCustomerLoginResponseDTO;
import com.newera.marathon.dto.cust.maintenance.XfaceCustCustomerRegisterRequestDTO;
import com.newera.marathon.dto.cust.maintenance.XfaceCustCustomerRegisterResponseDTO;
import com.newera.marathon.service.cust.entity.Customer;
import com.newera.marathon.service.cust.mapper.CustomerMapper;
import com.newera.marathon.service.cust.model.ApplicationError;
import com.newera.marathon.service.cust.service.CustomerService;
import com.spaking.boot.starter.core.exception.BaseException;
import com.spaking.boot.starter.core.model.TransactionStatus;
import com.spaking.boot.starter.core.utils.JWTUtil;
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

        String userName = requestDTO.getUserName();
        //判断两次密码是否一致
        if(!requestDTO.getPassword().equals(requestDTO.getVerifyPassword())){
            throw new BaseException(ApplicationError.PASSWWORD_UNSAME.getMessage(),ApplicationError.PASSWWORD_UNSAME.getCode());
        }
        //判断用户名是否存在
        QueryWrapper<Customer> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name",userName).or().eq("phone",userName).or().eq("email",userName);
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
        customer2.setCreateOperator(userName);
        //添加操作
        Boolean result = save(customer2);
        if (!result) {
            throw new BaseException(ApplicationError.ADDITION_FAILED.getMessage(), ApplicationError.ADDITION_FAILED.getCode());
        }
        responseDTO.setTransactionStatus(transactionStatus);
        log.info("doCustomerRegister end");
        return responseDTO;
    }

    @Override
    public XfaceCustCustomerLoginResponseDTO doCustomerLogin(XfaceCustCustomerLoginRequestDTO requestDTO) {
        log.info("doCustomerLogin start");
        XfaceCustCustomerLoginResponseDTO responseDTO = new XfaceCustCustomerLoginResponseDTO();
        TransactionStatus transactionStatus = new TransactionStatus();
        String loginAccount = requestDTO.getLoginAccount();
        String password = requestDTO.getPassword();

        QueryWrapper<Customer> wrapper = new QueryWrapper<>();
        wrapper.eq("user_name",loginAccount).or().eq("phone",loginAccount).or().eq("email",loginAccount);
        Customer customer = customerMapper.selectOne(wrapper);
        //判断登陆账号是否存在
        if(null == customer){
            throw new BaseException(ApplicationError.LOGIN_ACCOUNT_UNEXIST.getMessage(), ApplicationError.LOGIN_ACCOUNT_UNEXIST.getCode());
        }
        //判断密码是否正确
        String inputPassword = DigestUtils.sha1DigestAsHex(password);
        log.info("inputPassword>>>>>>>>>"+inputPassword);
        if(!inputPassword.equals(customer.getPassword())){
            throw new BaseException(ApplicationError.LOGIN_PASSWWORD_ERROR.getMessage(), ApplicationError.LOGIN_PASSWWORD_ERROR.getCode());
        }
        responseDTO.setCustomerId(customer.getId());
        responseDTO.setUserName(customer.getUserName());
        responseDTO.setToken(JWTUtil.genToken(customer.getId().toString()));
        responseDTO.setTransactionStatus(transactionStatus);
        log.info("doCustomerLogin end");
        return responseDTO;
    }
}
