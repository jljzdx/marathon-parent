package com.newera.marathon.service.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newera.marathon.common.model.ApplicationError;
import com.newera.marathon.dto.system.inquiry.*;
import com.newera.marathon.dto.system.maintenance.*;
import com.newera.marathon.service.cms.entity.SysResource;
import com.newera.marathon.service.cms.mapper.SysResourceMapper;
import com.newera.marathon.service.cms.service.SysResourceService;
import com.spaking.boot.starter.core.exception.BaseException;
import com.spaking.boot.starter.core.model.TransactionStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

        QueryWrapper<SysResource> wrapper = new QueryWrapper<>();
        //wrapper.eq("system_id",requestDTO.getSystemId());
        wrapper.select("id","parent_id","name","permission","icon","url","priority","type","available");
        wrapper.orderByAsc("priority");
        List<SysResource> all = list(wrapper);
        List<XfaceSysResourceLoopInquiryResponseSubDTO> result = new ArrayList<>();
        all.forEach(w->{
            XfaceSysResourceLoopInquiryResponseSubDTO responseSubDTO = new XfaceSysResourceLoopInquiryResponseSubDTO();
            BeanUtils.copyProperties(w,responseSubDTO);
            result.add(responseSubDTO);
        });
        responseDTO.setDataList(result);
        responseDTO.setTransactionStatus(transactionStatus);
        log.info("doSysResourceInquiryPage end");
        return responseDTO;
    }

    @Transactional
    @Override
    public XfaceSysResourceAdditionResponseDTO doSysResourceAddition(XfaceSysResourceAdditionRequestDTO requestDTO) {
        log.info("doSysResourceAddition start");
        XfaceSysResourceAdditionResponseDTO responseDTO = new XfaceSysResourceAdditionResponseDTO();
        TransactionStatus transactionStatus = new TransactionStatus();

        //判断同级的资源名称是否重复
        QueryWrapper<SysResource> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",requestDTO.getParentId());
        wrapper.eq("name",requestDTO.getName());
        wrapper.eq("available",1);
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

    @Transactional
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
            throw new BaseException(ApplicationError.MODIFY_FAILED.getMessage(), ApplicationError.MODIFY_FAILED.getCode());
        }
        responseDTO.setTransactionStatus(transactionStatus);
        log.info("doSysResourceModify end");
        return responseDTO;
    }

    @Transactional
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
            throw new BaseException(ApplicationError.DELETE_FAILED.getMessage(), ApplicationError.DELETE_FAILED.getCode());
        }

        responseDTO.setTransactionStatus(transactionStatus);
        log.info("doSysResourceModifyStatus end");
        return responseDTO;
    }

}
