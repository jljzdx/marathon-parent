package com.newera.marathon.service.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newera.marathon.common.constant.OtherConstant;
import com.newera.marathon.common.model.ApplicationError;
import com.newera.marathon.common.utils.PasswordUtil;
import com.newera.marathon.common.utils.StringBlankFormat;
import com.newera.marathon.dto.cms.inquiry.*;
import com.newera.marathon.dto.cms.maintenance.*;
import com.newera.marathon.mq.pojo.MailSend;
import com.newera.marathon.mq.producer.MailSendProducer;
import com.newera.marathon.service.cms.entity.AdminRole;
import com.newera.marathon.service.cms.entity.AdminUser;
import com.newera.marathon.service.cms.entity.AdminUserRole;
import com.newera.marathon.service.cms.mapper.AdminUserMapper;
import com.newera.marathon.service.cms.mapper.AdminUserRoleMapper;
import com.newera.marathon.service.cms.service.AdminUserRoleService;
import com.newera.marathon.service.cms.service.AdminUserService;
import com.newera.marathon.service.cms.vo.LeftMenuListVO;
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
public class AdminUserServiceImpl extends ServiceImpl<AdminUserMapper, AdminUser> implements AdminUserService {
    @Autowired
    private AdminUserMapper adminUserMapper;
    @Autowired
    private AdminUserRoleMapper adminUserRoleMapper;
    @Autowired
    private AdminUserRoleService adminUserRoleService;
    @Autowired
    private MailSendProducer mailSendProducer;

    @Override
    public XfaceCmsAdminUserInquiryPageResponseDTO doUserInquiryPage(XfaceCmsAdminUserInquiryPageRequestDTO requestDTO) {
        log.info("doUserInquiryPage start");
        XfaceCmsAdminUserInquiryPageResponseDTO responseDTO = new XfaceCmsAdminUserInquiryPageResponseDTO();
        TransactionStatus transactionStatus = new TransactionStatus();
        //分页查询
        PageModel pageModel = requestDTO.getPage();
        Page<XfaceCmsAdminUserInquiryPageResponseSubDTO> page = new Page<>(pageModel.getCurrent(), pageModel.getSize());
        page.setDesc("gmt_create");
        List<XfaceCmsAdminUserInquiryPageResponseSubDTO> result = adminUserMapper.queryUserPage(page, requestDTO);
        //查询角色名称
        result.forEach(w->{
            Integer userId = w.getId();
            List<AdminRole> roleList = adminUserRoleMapper.queryRoleListByUserId(userId);
            String roleNames = roleList.stream().map(x->x.getRoleName()).collect(Collectors.joining(","));
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
        log.info("doUserInquiryPage end");
        return responseDTO;

    }

    @Override
    public XfaceCmsAdminUserModifyInquiryResponseDTO doUserModifyInquiry(XfaceCmsAdminUserModifyInquiryRequestDTO requestDTO) {
        log.info("doUserModifyInquiry start");
        XfaceCmsAdminUserModifyInquiryResponseDTO responseDTO = new XfaceCmsAdminUserModifyInquiryResponseDTO();
        TransactionStatus transactionStatus = new TransactionStatus();
        //查询用户信息
        QueryWrapper<AdminUser> wrapper = new QueryWrapper<>();
        wrapper.eq("id",requestDTO.getId());
        wrapper.select("id","gender","user_name","mobile","real_name","email");
        AdminUser user = getOne(wrapper);
        if(null != user){
            BeanUtils.copyProperties(user,responseDTO);
        }
        //查询用户的所有角色，并复制数据
        List<AdminRole> roleList = adminUserRoleMapper.queryRoleListByUserId(requestDTO.getId());
        List<XfaceCmsAdminUserModifyInquiryResponseSubDTO> dataList = new ArrayList<>();
        roleList.forEach(w->{
            XfaceCmsAdminUserModifyInquiryResponseSubDTO responseSubDTO = new XfaceCmsAdminUserModifyInquiryResponseSubDTO();
            BeanUtils.copyProperties(w,responseSubDTO);
            dataList.add(responseSubDTO);
        });
        responseDTO.setDataList(dataList);
        responseDTO.setTransactionStatus(transactionStatus);
        log.info("doUserModifyInquiry end");
        return responseDTO;
    }

    @Transactional
    @Override
    public XfaceCmsAdminUserAdditionResponseDTO doUserAddition(XfaceCmsAdminUserAdditionRequestDTO requestDTO) {
        log.info("doUserAddition start");
        XfaceCmsAdminUserAdditionResponseDTO responseDTO = new XfaceCmsAdminUserAdditionResponseDTO();
        TransactionStatus transactionStatus = new TransactionStatus();
        //判断用户名是否存在
        QueryWrapper<AdminUser> wrapper = new QueryWrapper();
        wrapper.lambda().eq(AdminUser::getUserName,requestDTO.getUserName());
        Integer count = count(wrapper);
        if(count > 0){
            throw new BaseException(ApplicationError.USER_NAME_ALREADY_EXIST.getMessage(), ApplicationError.USER_NAME_ALREADY_EXIST.getCode());
        }
        //组装用户数据
        AdminUser user = new AdminUser();
        BeanUtils.copyProperties(requestDTO,user);
        user.setPassword(PasswordUtil.generate(user.getUserName()));
        //添加操作
        Boolean result = save(user);
        if(!result){
            throw new BaseException(ApplicationError.ADDITION_FAILED.getMessage(), ApplicationError.ADDITION_FAILED.getCode());
        }
        //组装用户角色数据
        List<AdminUserRole> userRoles = new ArrayList<>();
        String roles = requestDTO.getRoles();
        if(StringUtils.isNotBlank(roles)){
            Stream.of(roles.split(",")).filter(x-> StringUtils.isNotBlank(x)).forEach(w->{
                AdminUserRole userRole = new AdminUserRole();
                userRole.setRoleId(Integer.parseInt(w));
                userRole.setUserId(user.getId());
                userRoles.add(userRole);
            });
        }

        //批量插入用户角色
        if(userRoles.size() > 0){
            adminUserRoleService.doUserRoleBatchAddition(userRoles);
        }
        responseDTO.setTransactionStatus(transactionStatus);
        log.info("doUserAddition end");
        return responseDTO;
    }
    @Transactional
    @Override
    public XfaceCmsAdminUserModifyResponseDTO doUserModify(XfaceCmsAdminUserModifyRequestDTO requestDTO) {
        log.info("doUserModify start");
        XfaceCmsAdminUserModifyResponseDTO responseDTO = new XfaceCmsAdminUserModifyResponseDTO();
        TransactionStatus transactionStatus = new TransactionStatus();
        //组装用户数据
        AdminUser user = new AdminUser();
        BeanUtils.copyProperties(requestDTO, user);
        //更新操作
        Boolean result = updateById(user);
        if(!result){
            throw new BaseException(ApplicationError.MODIFY_FAILED.getMessage(), ApplicationError.MODIFY_FAILED.getCode());
        }
        //删除用户角色
        adminUserRoleMapper.deleteByUserId(requestDTO.getId());
        List<AdminUserRole> userRoles = new ArrayList<>();
        String roles = requestDTO.getRoles();
        if(StringUtils.isNotBlank(roles)){
            Stream.of(roles.split(",")).filter(x-> StringUtils.isNotBlank(x)).forEach(w->{
                AdminUserRole userRole = new AdminUserRole();
                userRole.setRoleId(Integer.parseInt(w));
                userRole.setUserId(user.getId());
                userRoles.add(userRole);
            });
        }

        //批量插入用户角色
        if(userRoles.size() > 0){
            adminUserRoleService.doUserRoleBatchAddition(userRoles);
        }
        responseDTO.setTransactionStatus(transactionStatus);
        log.info("doUserModify end");
        return responseDTO;
    }
    @Transactional
    @Override
    public XfaceCmsAdminUserBaseInfoModifyResponseDTO doUserBaseInfoModify(XfaceCmsAdminUserBaseInfoModifyRequestDTO requestDTO) {
        log.info("doUserBaseInfoModify start");
        XfaceCmsAdminUserBaseInfoModifyResponseDTO responseDTO = new XfaceCmsAdminUserBaseInfoModifyResponseDTO();
        TransactionStatus transactionStatus = new TransactionStatus();
        //组装用户数据
        AdminUser user = new AdminUser();
        BeanUtils.copyProperties(requestDTO, user);
        //更新操作
        Boolean result = updateById(user);
        if(!result){
            throw new BaseException(ApplicationError.MODIFY_FAILED.getMessage(), ApplicationError.MODIFY_FAILED.getCode());
        }
        responseDTO.setTransactionStatus(transactionStatus);
        log.info("doUserBaseInfoModify end");
        return responseDTO;
    }

    @Transactional
    @Override
    public XfaceCmsAdminUserModifyStatusResponseDTO doUserModifyStatus(XfaceCmsAdminUserModifyStatusRequestDTO requestDTO) {
        log.info("doUserDelete start");
        XfaceCmsAdminUserModifyStatusResponseDTO responseDTO = new XfaceCmsAdminUserModifyStatusResponseDTO();
        TransactionStatus transactionStatus = new TransactionStatus();
        //组装用户数据
        AdminUser user = new AdminUser();
        BeanUtils.copyProperties(requestDTO, user);
        //更新操作
        Boolean result = updateById(user);
        if(!result){
            throw new BaseException(ApplicationError.DELETE_FAILED.getMessage(), ApplicationError.DELETE_FAILED.getCode());
        }
        responseDTO.setTransactionStatus(transactionStatus);
        log.info("doUserDelete end");
        return responseDTO;
    }

    @Override
    public XfaceCmsAdminUserModifyPasswordResponseDTO doUserModifyPassword(XfaceCmsAdminUserModifyPasswordRequestDTO requestDTO) {
        log.info("doUserModifyPassword start");
        XfaceCmsAdminUserModifyPasswordResponseDTO responseDTO = new XfaceCmsAdminUserModifyPasswordResponseDTO();
        TransactionStatus transactionStatus = new TransactionStatus();
        //判断两次密码是否一致
        if(!requestDTO.getNewPassword().equalsIgnoreCase(requestDTO.getRepeatNewPassword())){
            throw new BaseException(ApplicationError.TWO_PASSWORD_ERROR.getMessage(), ApplicationError.TWO_PASSWORD_ERROR.getCode());
        }
        //判断原密码是否正确
        AdminUser user = adminUserMapper.selectById(requestDTO.getId());
        String password = user.getPassword();
        Boolean result = PasswordUtil.verify(requestDTO.getOldPassword(), password);
        if(!result){
            throw new BaseException(ApplicationError.OLD_PASSWORD_ERROR.getMessage(), ApplicationError.OLD_PASSWORD_ERROR.getCode());
        }
        //更新密码
        AdminUser updateUser = new AdminUser();
        updateUser.setId(requestDTO.getId());
        updateUser.setPassword(PasswordUtil.generate(requestDTO.getNewPassword()));
        updateUser.setModifyOperator(requestDTO.getModifyOperator());
        adminUserMapper.updateById(updateUser);
        responseDTO.setTransactionStatus(transactionStatus);
        log.info("doUserModifyPassword end");
        return responseDTO;
    }

    @Override
    public XfaceCmsAdminUserResetPasswordResponseDTO doUserResetPassword(XfaceCmsAdminUserResetPasswordRequestDTO requestDTO) {
        log.info("doUserResetPassword start");
        XfaceCmsAdminUserResetPasswordResponseDTO responseDTO = new XfaceCmsAdminUserResetPasswordResponseDTO();
        TransactionStatus transactionStatus = new TransactionStatus();
        //判断ID是否存在
        QueryWrapper<AdminUser> wrapper = new QueryWrapper<>();
        wrapper.eq("id",requestDTO.getId());
        wrapper.eq("locked",1);
        wrapper.select("id","user_name","email");
        AdminUser user = getOne(wrapper);
        if(null == user){
            throw new BaseException(ApplicationError.USERID_STATUS_INVALID.getMessage(), ApplicationError.USERID_STATUS_INVALID.getCode());
        }
        //重置密码
        AdminUser user2 = new AdminUser();
        user2.setId(requestDTO.getId());
        user2.setPassword(PasswordUtil.generate(user.getUserName()));
        user2.setModifyOperator(requestDTO.getModifyOperator());
        updateById(user2);
        //发送邮件
        MailSend mailSend = new MailSend();
        mailSend.setToMail(user.getEmail());
        mailSend.setSubject("后台用户密码重置");
        mailSend.setContent("亲爱的"+user.getUserName()+"用户，您的新密码为："+user.getUserName());
        mailSend.setType(1);
        mailSendProducer.send(mailSend);
        responseDTO.setTransactionStatus(transactionStatus);
        log.info("doUserResetPassword end");
        return responseDTO;
    }
    @Override
    public XfaceCmsAdminLeftMenuInquiryResponseDTO doLeftMenuInquiry(XfaceCmsAdminLeftMenuInquiryRequestDTO requestDTO) {
        log.info("doLeftMenuInquiry start");
        XfaceCmsAdminLeftMenuInquiryResponseDTO responseDTO = new XfaceCmsAdminLeftMenuInquiryResponseDTO();
        TransactionStatus transactionStatus = new TransactionStatus();
        //查询菜单
        List<LeftMenuListVO> list = adminUserMapper.queryLeftMenu(requestDTO.getUserName());
        //查询权限列表
        List<String> permissions = getPermissions(requestDTO.getUserName());
        //循环所有资源，标记已授权的资源，并递归生成树
        List<XfaceCmsAdminLeftMenuInquiryResponseSubDTO> responseSubDTOS = createTree(list);
        responseDTO.setDataList(responseSubDTOS);
        responseDTO.setPermissions(permissions);
        responseDTO.setTransactionStatus(transactionStatus);
        log.info("doLeftMenuInquiry end");
        return responseDTO;
    }
    @Override
    public XfaceCmsAdminPermissionsInquiryResponseDTO doPermissionsInquiry(XfaceCmsAdminPermissionsInquiryRequestDTO requestDTO) {
        log.info("doPermissionsInquiry start");
        XfaceCmsAdminPermissionsInquiryResponseDTO responseDTO = new XfaceCmsAdminPermissionsInquiryResponseDTO();
        TransactionStatus transactionStatus = new TransactionStatus();
        //查询权限列表
        List<String> permissions = getPermissions(requestDTO.getUserName());
        responseDTO.setPermissions(permissions);
        responseDTO.setTransactionStatus(transactionStatus);
        log.info("doPermissionsInquiry end");
        return responseDTO;
    }

    @Override
    public XfaceCmsAdminUserBaseInfoModifyInquiryResponseDTO doUserBaseInfoModifyInquiry(XfaceCmsAdminUserBaseInfoModifyInquiryRequestDTO requestDTO) {
        log.info("doUserBaseInfoModifyInquiry start");
        XfaceCmsAdminUserBaseInfoModifyInquiryResponseDTO responseDTO = new XfaceCmsAdminUserBaseInfoModifyInquiryResponseDTO();
        TransactionStatus transactionStatus = new TransactionStatus();
        //查询用户信息
        QueryWrapper<AdminUser> wrapper = new QueryWrapper<>();
        wrapper.eq("id",requestDTO.getId());
        wrapper.select("id","gender","user_name","mobile","real_name","email");
        AdminUser user = getOne(wrapper);
        if(null != user){
            BeanUtils.copyProperties(user,responseDTO);
        }
        //查询用户的所有角色，并复制数据
        List<AdminRole> roleList = adminUserRoleMapper.queryRoleListByUserId(requestDTO.getId());
        List<XfaceCmsAdminUserModifyInquiryResponseSubDTO> dataList = new ArrayList<>();
        roleList.forEach(w->{
            XfaceCmsAdminUserModifyInquiryResponseSubDTO responseSubDTO = new XfaceCmsAdminUserModifyInquiryResponseSubDTO();
            BeanUtils.copyProperties(w,responseSubDTO);
            dataList.add(responseSubDTO);
        });
        String roles = roleList.stream().map(w->w.getRoleName()).collect(Collectors.joining(","));
        responseDTO.setRoles(roles);
        responseDTO.setTransactionStatus(transactionStatus);
        log.info("doUserBaseInfoModifyInquiry end");
        return responseDTO;
    }

    public List<String> getPermissions(String userName){
        List<String> permissions = adminUserMapper.queryPermissions(userName);
        return permissions;
    }

    public List<XfaceCmsAdminLeftMenuInquiryResponseSubDTO> createTree(List<LeftMenuListVO> resourceList){
        List<XfaceCmsAdminLeftMenuInquiryResponseSubDTO> list = new ArrayList<>();
        for (LeftMenuListVO resource : resourceList) {
            String[] levels = resource.getParentIds().split("/");
            if(levels.length == OtherConstant.MENU_LEVEL){//判断是否是一级菜单
                XfaceCmsAdminLeftMenuInquiryResponseSubDTO treeObject = new XfaceCmsAdminLeftMenuInquiryResponseSubDTO();
                treeObject.setId(resource.getId());
                treeObject.setName(resource.getName());
                treeObject.setIcon(resource.getIcon());
                treeObject.setUrl(resource.getUrl());
                treeObject.setChildren(getChildren(resource.getId(),resourceList));
                list.add(treeObject);
            }
        }
        return list;
    }
    public List<XfaceCmsAdminLeftMenuInquiryResponseSubDTO> getChildren(Integer parentId, List<LeftMenuListVO> resourceList){
        List<XfaceCmsAdminLeftMenuInquiryResponseSubDTO> list = new ArrayList<>();
        for (LeftMenuListVO resource : resourceList) {
            if(resource.getParentId().equals(parentId)){
                XfaceCmsAdminLeftMenuInquiryResponseSubDTO treeObject = new XfaceCmsAdminLeftMenuInquiryResponseSubDTO();
                treeObject.setId(resource.getId());
                treeObject.setName(resource.getName());
                treeObject.setIcon(resource.getIcon());
                treeObject.setUrl(resource.getUrl());
                treeObject.setChildren(getChildren(resource.getId(),resourceList));
                list.add(treeObject);
            }
        }
        return list;
    }
}
