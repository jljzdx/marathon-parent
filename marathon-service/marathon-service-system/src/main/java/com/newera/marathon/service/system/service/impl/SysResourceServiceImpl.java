package com.newera.marathon.service.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newera.marathon.common.constant.OtherConstant;
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
        List<XfaceSysResourceLoopInquiryResponseSubDTO> result = createTree(all);
        responseDTO.setDataList(result);
        responseDTO.setTransactionStatus(transactionStatus);
        log.info("doSysResourceInquiryPage end");
        return responseDTO;
    }
    public List<XfaceSysResourceLoopInquiryResponseSubDTO> createTree(List<SysResource> sysResourceList){
        List<XfaceSysResourceLoopInquiryResponseSubDTO> list = new ArrayList<>();
        for (SysResource resource : sysResourceList) {
            if(resource.getParentId() == OtherConstant.RESOURCE_TOP_PARENT_ID){//判断是否是一级菜单
                XfaceSysResourceLoopInquiryResponseSubDTO treeObject = new XfaceSysResourceLoopInquiryResponseSubDTO();
                treeObject.setId(resource.getId());
                treeObject.setName(resource.getName());
                treeObject.setPermission(resource.getPermission());
                treeObject.setIcon(resource.getIcon());
                treeObject.setUrl(resource.getUrl());
                treeObject.setPriority(resource.getPriority());
                treeObject.setType(resource.getType());
                treeObject.setAvailable(resource.getAvailable());
                treeObject.setChild(getChildren(resource.getId(),sysResourceList));
                list.add(treeObject);
            }
        }
        return list;
    }
    public List<XfaceSysResourceLoopInquiryResponseSubDTO> getChildren(Integer parentId,List<SysResource> sysResourceList){
        List<XfaceSysResourceLoopInquiryResponseSubDTO> list = new ArrayList<>();
        for (SysResource resource : sysResourceList) {
            if(resource.getParentId().equals(parentId)){
                XfaceSysResourceLoopInquiryResponseSubDTO treeObject = new XfaceSysResourceLoopInquiryResponseSubDTO();
                treeObject.setId(resource.getId());
                treeObject.setName(resource.getName());
                treeObject.setPermission(resource.getPermission());
                treeObject.setIcon(resource.getIcon());
                treeObject.setUrl(resource.getUrl());
                treeObject.setPriority(resource.getPriority());
                treeObject.setType(resource.getType());
                treeObject.setAvailable(resource.getAvailable());
                treeObject.setChild(getChildren(resource.getId(),sysResourceList));
                list.add(treeObject);
            }
        }
        return list;
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
            throw new BaseException(ApplicationError.SYS_MODIFY_FAILED.getMessage(), ApplicationError.SYS_MODIFY_FAILED.getCode());
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
            throw new BaseException(ApplicationError.SYS_DELETE_FAILED.getMessage(), ApplicationError.SYS_DELETE_FAILED.getCode());
        }

        responseDTO.setTransactionStatus(transactionStatus);
        log.info("doSysResourceModifyStatus end");
        return responseDTO;
    }

}
