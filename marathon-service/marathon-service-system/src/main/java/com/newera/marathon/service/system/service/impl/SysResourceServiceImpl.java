package com.newera.marathon.service.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newera.marathon.dto.system.inquiry.*;
import com.newera.marathon.dto.system.maintenance.*;
import com.newera.marathon.service.system.entity.SysResource;
import com.newera.marathon.service.system.mapper.SysResourceMapper;
import com.newera.marathon.service.system.model.ApplicationError;
import com.newera.marathon.service.system.service.SysResourceService;
import com.spaking.boot.starter.core.exception.BaseException;
import com.spaking.boot.starter.core.model.TransactionStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author MicroBin
 * @since 2019-08-14
 */
@Slf4j
@Service
public class SysResourceServiceImpl extends ServiceImpl<SysResourceMapper, SysResource> implements SysResourceService {
    @Autowired
    private SysResourceMapper sysResourceMapper;


    @Override
    public XfaceSysResourceLoopInquiryResponseDTO doSysResourceInquiryLoop(XfaceSysResourceLoopInquiryRequestDTO requestDTO) {
        log.info("doSysResourceInquiryPage start");
        XfaceSysResourceLoopInquiryResponseDTO responseDTO = new XfaceSysResourceLoopInquiryResponseDTO();
        TransactionStatus transactionStatus = new TransactionStatus();

        Integer parentId = requestDTO.getParentId();
        List<XfaceSysResourceLoopInquiryResponseSubDTO> result = sysResourceMapper.querySysResourceLoop(parentId);

        responseDTO.setDataList(result);
        responseDTO.setTransactionStatus(transactionStatus);
        log.info("doSysResourceInquiryPage end");
        return responseDTO;
    }

    @Override
    public XfaceSysResourceAdditionResponseDTO doSysResourceAddition(XfaceSysResourceAdditionRequestDTO requestDTO) {
        log.info("doSysResourceAddition start");
        XfaceSysResourceAdditionResponseDTO responseDTO = new XfaceSysResourceAdditionResponseDTO();
        TransactionStatus transactionStatus = new TransactionStatus();

        //判断同级的资源名称是否重复
        QueryWrapper<SysResource> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",requestDTO.getParentId());
        wrapper.eq("name",requestDTO.getName());
        SysResource resource1 = getOne(wrapper);
        if(null != resource1){
            throw new BaseException(ApplicationError.NAME_SAME.getMessage(), ApplicationError.NAME_SAME.getCode());
        }

        //查询父节点信息，组装parentIds
        QueryWrapper<SysResource> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("id",requestDTO.getParentId());
        SysResource parentResource = getOne(wrapper1);
        if(null == parentResource) {
            throw new BaseException(ApplicationError.NOT_EXIST_PARENTID.getMessage(), ApplicationError.NOT_EXIST_PARENTID.getCode());
        }
        String parentIds = parentResource.getParentIds()+requestDTO.getParentId()+"/";

        SysResource resource = new SysResource();
        BeanUtils.copyProperties(requestDTO,resource);
        resource.setParentIds(parentIds);
        save(resource);

        responseDTO.setTransactionStatus(transactionStatus);
        log.info("doSysResourceAddition end");
        return responseDTO;
    }

    @Override
    public XfaceSysResourceModifyInquiryResponseDTO doSysResourceModifyInquiry(XfaceSysResourceModifyInquiryRequestDTO requestDTO) {
        log.info("doSysResourceModifyInquiry start");
        XfaceSysResourceModifyInquiryResponseDTO responseDTO = new XfaceSysResourceModifyInquiryResponseDTO();
        TransactionStatus transactionStatus = new TransactionStatus();

        QueryWrapper<SysResource> wrapper = new QueryWrapper<>();
        wrapper.eq("id",requestDTO.getId());
        wrapper.select("id","name","permission","icon","url","priority","type","available");
        SysResource resource = getOne(wrapper);
        if(null != resource){
            BeanUtils.copyProperties(resource,responseDTO);
        }

        responseDTO.setTransactionStatus(transactionStatus);
        log.info("doSysResourceModifyInquiry end");
        return responseDTO;
    }

    @Override
    public XfaceSysResourceModifyResponseDTO doSysResourceModify(XfaceSysResourceModifyRequestDTO requestDTO) {
        log.info("doSysResourceModify start");
        XfaceSysResourceModifyResponseDTO responseDTO = new XfaceSysResourceModifyResponseDTO();
        TransactionStatus transactionStatus = new TransactionStatus();

        SysResource resource = new SysResource();
        BeanUtils.copyProperties(requestDTO, resource);
        //更新操作
        Boolean result = updateById(resource);
        if(!result){
            throw new BaseException(ApplicationError.SYS_MODIFY_FAILED.getMessage(), ApplicationError.SYS_MODIFY_FAILED.getCode());
        }
        responseDTO.setTransactionStatus(transactionStatus);
        log.info("doSysResourceModify end");
        return responseDTO;
    }

    @Override
    public XfaceSysResourceModifyStatusResponseDTO doSysResourceModifyStatus(XfaceSysResourceModifyStatusRequestDTO requestDTO) {
        log.info("doSysResourceModifyStatus start");
        XfaceSysResourceModifyStatusResponseDTO responseDTO = new XfaceSysResourceModifyStatusResponseDTO();
        TransactionStatus transactionStatus = new TransactionStatus();

        SysResource resource = new SysResource();
        BeanUtils.copyProperties(requestDTO, resource);
        //更新操作
        Boolean result = updateById(resource);
        if(!result){
            throw new BaseException(ApplicationError.SYS_DELETE_FAILED.getMessage(), ApplicationError.SYS_DELETE_FAILED.getCode());
        }

        responseDTO.setTransactionStatus(transactionStatus);
        log.info("doSysResourceModifyStatus end");
        return responseDTO;
    }

}