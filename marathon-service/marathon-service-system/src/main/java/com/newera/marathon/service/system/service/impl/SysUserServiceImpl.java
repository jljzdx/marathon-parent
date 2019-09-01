package com.newera.marathon.service.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newera.marathon.common.utils.PasswordUtil;
import com.newera.marathon.common.utils.StringBlankFormat;
import com.newera.marathon.dto.system.inquiry.*;
import com.newera.marathon.dto.system.maintenance.*;
import com.newera.marathon.service.system.entity.SysRole;
import com.newera.marathon.service.system.entity.SysUser;
import com.newera.marathon.service.system.entity.SysUserRole;
import com.newera.marathon.service.system.mapper.SysUserMapper;
import com.newera.marathon.service.system.mapper.SysUserRoleMapper;
import com.newera.marathon.service.system.model.ApplicationError;
import com.newera.marathon.service.system.service.SysUserRoleService;
import com.newera.marathon.service.system.service.SysUserService;
import com.spaking.boot.starter.core.exception.BaseException;
import com.spaking.boot.starter.core.model.PageModel;
import com.spaking.boot.starter.core.model.TransactionStatus;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;
    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Override
    public XfaceSysUserInquiryPageResponseDTO doSysUserInquiryPage(XfaceSysUserInquiryPageRequestDTO requestDTO) {
        log.info("doSysUserInquiryPage start");
        XfaceSysUserInquiryPageResponseDTO responseDTO = new XfaceSysUserInquiryPageResponseDTO();
        TransactionStatus transactionStatus = new TransactionStatus();
        //分页查询
        PageModel pageModel = requestDTO.getPage();
        Page<XfaceSysUserInquiryPageResponseSubDTO> page = new Page<>(pageModel.getCurrent(), pageModel.getSize());
        page.setDesc("gmt_create");
        List<XfaceSysUserInquiryPageResponseSubDTO> result = sysUserMapper.querySysUserPage(page, requestDTO);
        //查询角色名称
        result.forEach(w->{
            Integer userId = w.getId();
            List<SysRole> sysRoleList = sysUserRoleMapper.queryRoleListByUserId(userId);
            String roleNames = sysRoleList.stream().map(x->x.getRoleName()).collect(Collectors.joining(","));
            roleNames = StringBlankFormat.formatBlankString(roleNames);
            w.setRoleNames(roleNames);
            w.setEmail(StringBlankFormat.formatBlankString(w.getEmail()));
            w.setLastLoginTime(StringBlankFormat.formatBlankString(w.getLastLoginTime()));
            w.setLoginTime(StringBlankFormat.formatBlankString(w.getLoginTime()));
            w.setGmtCreate(StringBlankFormat.formatBlankString(w.getGmtCreate()));
        });
        pageModel.setTotal(page.getTotal());
        responseDTO.setPage(pageModel);
        responseDTO.setDataList(result);
        responseDTO.setTransactionStatus(transactionStatus);
        log.info("doSysUserInquiryPage end");
        return responseDTO;

    }

    @Override
    public XfaceSysUserModifyInquiryResponseDTO doSysUserModifyInquiry(XfaceSysUserModifyInquiryRequestDTO requestDTO) {
        log.info("doSysUserModifyInquiry start");
        XfaceSysUserModifyInquiryResponseDTO responseDTO = new XfaceSysUserModifyInquiryResponseDTO();
        TransactionStatus transactionStatus = new TransactionStatus();
        //查询用户信息
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("id",requestDTO.getId());
        wrapper.select("id","gender","user_name","mobile","real_name","email");
        SysUser sysUser = getOne(wrapper);
        if(null != sysUser){
            BeanUtils.copyProperties(sysUser,responseDTO);
        }
        //查询用户的所有角色，并复制数据
        List<SysRole> sysRoleList = sysUserRoleMapper.queryRoleListByUserId(requestDTO.getId());
        List<XfaceSysUserModifyInquiryResponseSubDTO> dataList = new ArrayList<>();
        sysRoleList.forEach(w->{
            XfaceSysUserModifyInquiryResponseSubDTO responseSubDTO = new XfaceSysUserModifyInquiryResponseSubDTO();
            BeanUtils.copyProperties(w,responseSubDTO);
            dataList.add(responseSubDTO);
        });
        responseDTO.setDataList(dataList);
        responseDTO.setTransactionStatus(transactionStatus);
        log.info("doSysUserModifyInquiry end");
        return responseDTO;
    }

    @Transactional
    @Override
    public XfaceSysUserAdditionResponseDTO doSysUserAddition(XfaceSysUserAdditionRequestDTO requestDTO) {
        log.info("doSysUserAddition start");
        XfaceSysUserAdditionResponseDTO responseDTO = new XfaceSysUserAdditionResponseDTO();
        TransactionStatus transactionStatus = new TransactionStatus();
        //判断用户名是否存在
        QueryWrapper<SysUser> wrapper = new QueryWrapper();
        wrapper.lambda().eq(SysUser::getUserName,requestDTO.getUserName());
        Integer count = count(wrapper);
        if(count > 0){
            throw new BaseException(ApplicationError.USER_NAME_ALREADY_EXIST.getMessage(), ApplicationError.USER_NAME_ALREADY_EXIST.getCode());
        }
        //组装用户数据
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(requestDTO,sysUser);
        sysUser.setPassword(PasswordUtil.generate(sysUser.getUserName()));
        //添加操作
        Boolean result = save(sysUser);
        if(!result){
            throw new BaseException(ApplicationError.SYS_ADDITION_FAILED.getMessage(), ApplicationError.SYS_ADDITION_FAILED.getCode());
        }
        //组装用户角色数据
        List<SysUserRole> sysUserRoles = new ArrayList<>();
        String roles = requestDTO.getRoles();
        if(StringUtils.isNotBlank(roles)){
            Stream.of(roles.split(",")).filter(x-> StringUtils.isNotBlank(x)).forEach(w->{
                SysUserRole sysUserRole = new SysUserRole();
                sysUserRole.setRoleId(Integer.parseInt(w));
                sysUserRole.setUserId(sysUser.getId());
                sysUserRoles.add(sysUserRole);
            });
        }

        //批量插入用户角色
        if(sysUserRoles.size() > 0){
            sysUserRoleService.doSysUserRoleBatchAddition(sysUserRoles);
        }
        responseDTO.setTransactionStatus(transactionStatus);
        log.info("doSysUserAddition end");
        return responseDTO;
    }
    @Transactional
    @Override
    public XfaceSysUserModifyResponseDTO doSysUserModify(XfaceSysUserModifyRequestDTO requestDTO) {
        log.info("doSysUserModify start");
        XfaceSysUserModifyResponseDTO responseDTO = new XfaceSysUserModifyResponseDTO();
        TransactionStatus transactionStatus = new TransactionStatus();
        //组装用户数据
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(requestDTO, sysUser);
        //更新操作
        Boolean result = updateById(sysUser);
        if(!result){
            throw new BaseException(ApplicationError.SYS_MODIFY_FAILED.getMessage(), ApplicationError.SYS_MODIFY_FAILED.getCode());
        }
        //删除用户角色
        sysUserRoleMapper.deleteByUserId(requestDTO.getId());
        List<SysUserRole> sysUserRoles = new ArrayList<>();
        String roles = requestDTO.getRoles();
        if(StringUtils.isNotBlank(roles)){
            Stream.of(roles.split(",")).filter(x-> StringUtils.isNotBlank(x)).forEach(w->{
                SysUserRole sysUserRole = new SysUserRole();
                sysUserRole.setRoleId(Integer.parseInt(w));
                sysUserRole.setUserId(sysUser.getId());
                sysUserRoles.add(sysUserRole);
            });
        }

        //批量插入用户角色
        if(sysUserRoles.size() > 0){
            sysUserRoleService.doSysUserRoleBatchAddition(sysUserRoles);
        }
        responseDTO.setTransactionStatus(transactionStatus);
        log.info("doSysUserModify end");
        return responseDTO;
    }
    @Transactional
    @Override
    public XfaceSysUserBaseInfoModifyResponseDTO doSysUserBaseInfoModify(XfaceSysUserBaseInfoModifyRequestDTO requestDTO) {
        log.info("doSysUserBaseInfoModify start");
        XfaceSysUserBaseInfoModifyResponseDTO responseDTO = new XfaceSysUserBaseInfoModifyResponseDTO();
        TransactionStatus transactionStatus = new TransactionStatus();
        //组装用户数据
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(requestDTO, sysUser);
        //更新操作
        Boolean result = updateById(sysUser);
        if(!result){
            throw new BaseException(ApplicationError.SYS_MODIFY_FAILED.getMessage(), ApplicationError.SYS_MODIFY_FAILED.getCode());
        }
        responseDTO.setTransactionStatus(transactionStatus);
        log.info("doSysUserBaseInfoModify end");
        return responseDTO;
    }

    @Transactional
    @Override
    public XfaceSysUserModifyStatusResponseDTO doSysUserModifyStatus(XfaceSysUserModifyStatusRequestDTO requestDTO) {
        log.info("doSysUserDelete start");
        XfaceSysUserModifyStatusResponseDTO responseDTO = new XfaceSysUserModifyStatusResponseDTO();
        TransactionStatus transactionStatus = new TransactionStatus();
        //组装用户数据
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(requestDTO, sysUser);
        //更新操作
        Boolean result = updateById(sysUser);
        if(!result){
            throw new BaseException(ApplicationError.SYS_DELETE_FAILED.getMessage(), ApplicationError.SYS_DELETE_FAILED.getCode());
        }
        responseDTO.setTransactionStatus(transactionStatus);
        log.info("doSysUserDelete end");
        return responseDTO;
    }

    @Override
    public XfaceSysUserModifyPasswordResponseDTO doSysUserModifyPassword(XfaceSysUserModifyPasswordRequestDTO requestDTO) {
        log.info("doSysUserModifyPassword start");
        XfaceSysUserModifyPasswordResponseDTO responseDTO = new XfaceSysUserModifyPasswordResponseDTO();
        TransactionStatus transactionStatus = new TransactionStatus();
        //判断两次密码是否一致
        if(!requestDTO.getNewPassword().equalsIgnoreCase(requestDTO.getRepeatNewPassword())){
            throw new BaseException(ApplicationError.TWO_PASSWORD_ERROR.getMessage(), ApplicationError.TWO_PASSWORD_ERROR.getCode());
        }
        //判断原密码是否正确
        SysUser sysUser = sysUserMapper.selectById(requestDTO.getId());
        String password = sysUser.getPassword();
        Boolean result = PasswordUtil.verify(requestDTO.getOldPassword(), password);
        if(!result){
            throw new BaseException(ApplicationError.OLD_PASSWORD_ERROR.getMessage(), ApplicationError.OLD_PASSWORD_ERROR.getCode());
        }
        //更新密码
        SysUser updateUser = new SysUser();
        updateUser.setId(requestDTO.getId());
        updateUser.setPassword(PasswordUtil.generate(requestDTO.getNewPassword()));
        updateUser.setModifyOperator(requestDTO.getModifyOperator());
        sysUserMapper.updateById(updateUser);
        responseDTO.setTransactionStatus(transactionStatus);
        log.info("doSysUserModifyPassword start");
        return responseDTO;
    }

    @Override
    public XfaceSysUserResetPasswordResponseDTO doSysUserResetPassword(XfaceSysUserResetPasswordRequestDTO requestDTO) {
        log.info("doSysUserResetPassword start");
        XfaceSysUserResetPasswordResponseDTO responseDTO = new XfaceSysUserResetPasswordResponseDTO();
        TransactionStatus transactionStatus = new TransactionStatus();
        //判断ID是否存在
        QueryWrapper<SysUser> wrapper = new QueryWrapper<>();
        wrapper.eq("id",requestDTO.getId());
        wrapper.eq("locked",1);
        wrapper.select("id","user_name");
        SysUser sysUser = getOne(wrapper);
        if(null == sysUser){
            throw new BaseException(ApplicationError.USERID_STATUS_INVALID.getMessage(), ApplicationError.USERID_STATUS_INVALID.getCode());
        }
        //重置密码
        SysUser sysUser2 = new SysUser();
        sysUser2.setId(requestDTO.getId());
        sysUser2.setPassword(PasswordUtil.generate(sysUser.getUserName()));
        sysUser2.setModifyOperator(requestDTO.getModifyOperator());
        updateById(sysUser2);
        responseDTO.setTransactionStatus(transactionStatus);
        log.info("doSysUserResetPassword start");
        return responseDTO;
    }
}
