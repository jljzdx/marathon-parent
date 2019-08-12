package com.newera.marathon.service.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newera.marathon.common.utils.DateUtils;
import com.newera.marathon.dto.system.enumeration.SysUserStatus;
import com.newera.marathon.dto.system.inquiry.*;
import com.newera.marathon.dto.system.maintenance.*;
import com.newera.marathon.service.system.entity.SysRole;
import com.newera.marathon.service.system.mapper.SysRoleMapper;
import com.newera.marathon.service.system.model.ApplicationError;
import com.newera.marathon.service.system.service.SysRoleService;
import com.spaking.boot.starter.core.exception.BaseException;
import com.spaking.boot.starter.core.model.PageModel;
import com.spaking.boot.starter.core.model.TransactionStatus;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author MicroBin
 * @since 2019-04-20
 */
@Slf4j
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Override
    public XfaceSysRoleInquiryPageResponseDTO doSysRoleInquiryPage(XfaceSysRoleInquiryPageRequestDTO requestDTO) {
        log.info("doSysRoleInquiryPage start");
        XfaceSysRoleInquiryPageResponseDTO responseDTO = new XfaceSysRoleInquiryPageResponseDTO();
        TransactionStatus transactionStatus = new TransactionStatus();
        PageModel pageModel = requestDTO.getPage();
        Page<SysRole> page = new Page<>(pageModel.getCurrent(), pageModel.getSize());
        page.setDesc("id");
        //方式一
        //Page<XfaceSysRoleInquiryPageResponseSubDTO> page = new Page<>(pageModel.getCurrent(), pageModel.getSize());
        //List<XfaceSysRoleInquiryPageResponseSubDTO> dataList = sysRoleMapper.querySysRolePage(page, requestDTO);
        //方式二
        QueryWrapper<SysRole> wrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(requestDTO.getRoleName())){
            wrapper.like("role_name",requestDTO.getRoleName());
        }
        wrapper.select("id","role_name","status","remark","gmt_create");
        IPage<SysRole> iPage = page(page,wrapper);
        List<XfaceSysRoleInquiryPageResponseSubDTO> dataList = new ArrayList<>();
        iPage.getRecords().forEach(w->{
            XfaceSysRoleInquiryPageResponseSubDTO responseSubDTO = new XfaceSysRoleInquiryPageResponseSubDTO();
            BeanUtils.copyProperties(w, responseSubDTO);
            responseSubDTO.setGmtCreate(DateUtils.dateToStringFormat(w.getGmtCreate(),DateUtils.FORMAT1));
            dataList.add(responseSubDTO);
        });
        pageModel.setTotal(page.getTotal());
        responseDTO.setPage(pageModel);
        responseDTO.setDataList(dataList);
        responseDTO.setTransactionStatus(transactionStatus);
        log.info("doSysRoleInquiryPage end");
        return responseDTO;
    }

    @Override
    public XfaceSysRoleModifyInquiryResponseDTO doSysRoleModifyInquiry(XfaceSysRoleModifyInquiryRequestDTO requestDTO) {
        log.info("doSysRoleModifyInquiry start");
        XfaceSysRoleModifyInquiryResponseDTO responseDTO = new XfaceSysRoleModifyInquiryResponseDTO();
        TransactionStatus transactionStatus = new TransactionStatus();
        //查询条件
        QueryWrapper<SysRole> wrapper = new QueryWrapper<>();
        wrapper.eq("id",requestDTO.getId());
        wrapper.select("id","role_name","remark");
        //查询用户信息
        SysRole sysRole = getOne(wrapper);
        //复制对象属性
        if(null != sysRole){
            BeanUtils.copyProperties(sysRole,responseDTO);
        }
        responseDTO.setTransactionStatus(transactionStatus);
        log.info("doSysRoleModifyInquiry end");
        return responseDTO;
    }

    @Override
    public XfaceSysRoleInquirySelectResponseDTO doSysRoleInquirySelect() {
        log.info("doSysRoleInquirySelect start");
        XfaceSysRoleInquirySelectResponseDTO responseDTO = new XfaceSysRoleInquirySelectResponseDTO();
        TransactionStatus transactionStatus = new TransactionStatus();
        List<XfaceSysRoleInquirySelectResponseSubDTO> responseSubDTOS = new ArrayList<>();
        QueryWrapper<SysRole> wrapper = new QueryWrapper<>();
        wrapper.select("id","role_name","status");
        List<SysRole> sysRoles = list(wrapper);
        sysRoles.forEach(w->{
            XfaceSysRoleInquirySelectResponseSubDTO responseSubDTO = new XfaceSysRoleInquirySelectResponseSubDTO();
            responseSubDTO.setId(w.getId());
            responseSubDTO.setName(w.getRoleName());
            responseSubDTO.setStatus(w.getStatus());
            responseSubDTOS.add(responseSubDTO);
        });
        responseDTO.setDataList(responseSubDTOS);
        responseDTO.setTransactionStatus(transactionStatus);
        log.info("doSysRoleInquirySelect end");
        return responseDTO;
    }

    @Transactional
    @Override
    public XfaceSysRoleAdditionResponseDTO doSysRoleAddition(XfaceSysRoleAdditionRequestDTO requestDTO) {
        log.info("doSysRoleAddition start");
        XfaceSysRoleAdditionResponseDTO responseDTO = new XfaceSysRoleAdditionResponseDTO();
        TransactionStatus transactionStatus = new TransactionStatus();
        //判断用户名是否存在
        QueryWrapper<SysRole> wrapper = new QueryWrapper();
        wrapper.lambda().eq(SysRole::getRoleName,requestDTO.getRoleName());
        Integer count = count(wrapper);
        if(count > 0){
            throw new BaseException(ApplicationError.ROLE_NAME_ALREADY_EXIST.getMessage(), ApplicationError.ROLE_NAME_ALREADY_EXIST.getCode());
        }
        //组装用户数据
        SysRole sysRole = new SysRole();
        BeanUtils.copyProperties(requestDTO,sysRole);
        Date date = new Date();
        sysRole.setGmtCreate(date);
        sysRole.setGmtModify(date);
        sysRole.setStatus(SysUserStatus._1.getCode());
        //添加操作
        Boolean result = save(sysRole);
        if(!result){
            throw new BaseException(ApplicationError.SYS_ADDITION_FAILED.getMessage(), ApplicationError.SYS_ADDITION_FAILED.getCode());
        }
        responseDTO.setTransactionStatus(transactionStatus);
        log.info("doSysRoleAddition end");
        return responseDTO;
    }

    @Transactional
    @Override
    public XfaceSysRoleModifyResponseDTO doSysRoleModify(XfaceSysRoleModifyRequestDTO requestDTO) {
        log.info("doSysRoleModify start");
        XfaceSysRoleModifyResponseDTO responseDTO = new XfaceSysRoleModifyResponseDTO();
        TransactionStatus transactionStatus = new TransactionStatus();
        //组装用户数据
        SysRole sysRole = new SysRole();
        BeanUtils.copyProperties(requestDTO, sysRole);
        sysRole.setGmtModify(new Date());
        //更新操作
        Boolean result = updateById(sysRole);
        if(!result){
            throw new BaseException(ApplicationError.SYS_MODIFY_FAILED.getMessage(), ApplicationError.SYS_MODIFY_FAILED.getCode());
        }
        responseDTO.setTransactionStatus(transactionStatus);
        log.info("doSysRoleModify end");
        return responseDTO;
    }

    @Transactional
    @Override
    public XfaceSysRoleModifyStatusResponseDTO doSysRoleModifyStatus(XfaceSysRoleModifyStatusRequestDTO requestDTO) {
        log.info("doSysRoleDelete start");
        XfaceSysRoleModifyStatusResponseDTO responseDTO = new XfaceSysRoleModifyStatusResponseDTO();
        TransactionStatus transactionStatus = new TransactionStatus();
        //组装用户数据
        SysRole sysRole = new SysRole();
        BeanUtils.copyProperties(requestDTO, sysRole);
        sysRole.setGmtModify(new Date());
        //更新操作
        Boolean result = updateById(sysRole);
        if(!result){
            throw new BaseException(ApplicationError.SYS_DELETE_FAILED.getMessage(), ApplicationError.SYS_DELETE_FAILED.getCode());
        }
        responseDTO.setTransactionStatus(transactionStatus);
        log.info("doSysRoleDelete end");
        return responseDTO;
    }
}
