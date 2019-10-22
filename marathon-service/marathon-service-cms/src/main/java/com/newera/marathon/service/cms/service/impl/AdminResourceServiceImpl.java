package com.newera.marathon.service.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newera.marathon.common.model.ApplicationError;
import com.newera.marathon.dto.cms.inquiry.*;
import com.newera.marathon.dto.cms.maintenance.*;
import com.newera.marathon.service.cms.entity.AdminResource;
import com.newera.marathon.service.cms.mapper.AdminResourceMapper;
import com.newera.marathon.service.cms.service.AdminResourceService;
import com.spaking.boot.starter.core.exception.BaseException;
import com.spaking.boot.starter.core.model.TransactionStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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
public class AdminResourceServiceImpl extends ServiceImpl<AdminResourceMapper, AdminResource> implements AdminResourceService {

    @Override
    public XfaceCmsAdminResourceLoopInquiryResponseDTO doResourceInquiryLoop(XfaceCmsAdminResourceLoopInquiryRequestDTO requestDTO) {
        log.info("doResourceInquiryPage start");
        XfaceCmsAdminResourceLoopInquiryResponseDTO responseDTO = new XfaceCmsAdminResourceLoopInquiryResponseDTO();
        TransactionStatus transactionStatus = new TransactionStatus();

        QueryWrapper<AdminResource> wrapper = new QueryWrapper<>();
        //wrapper.eq("system_id",requestDTO.getSystemId());
        wrapper.select("id","parent_id","name","permission","icon","url","priority","type","available");
        wrapper.orderByAsc("priority");
        List<AdminResource> all = list(wrapper);
        List<XfaceCmsAdminResourceLoopInquiryResponseSubDTO> result = new ArrayList<>();
        all.forEach(w->{
            XfaceCmsAdminResourceLoopInquiryResponseSubDTO responseSubDTO = new XfaceCmsAdminResourceLoopInquiryResponseSubDTO();
            BeanUtils.copyProperties(w,responseSubDTO);
            result.add(responseSubDTO);
        });
        responseDTO.setDataList(result);
        responseDTO.setTransactionStatus(transactionStatus);
        log.info("doResourceInquiryPage end");
        return responseDTO;
    }

    @Transactional
    @Override
    public XfaceCmsAdminResourceAdditionResponseDTO doResourceAddition(XfaceCmsAdminResourceAdditionRequestDTO requestDTO) {
        log.info("doResourceAddition start");
        XfaceCmsAdminResourceAdditionResponseDTO responseDTO = new XfaceCmsAdminResourceAdditionResponseDTO();
        TransactionStatus transactionStatus = new TransactionStatus();

        //判断同级的资源名称是否重复
        QueryWrapper<AdminResource> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",requestDTO.getParentId());
        wrapper.eq("name",requestDTO.getName());
        wrapper.eq("available",1);
        AdminResource resource1 = getOne(wrapper);
        if(null != resource1){
            throw new BaseException(ApplicationError.NAME_SAME.getMessage(), ApplicationError.NAME_SAME.getCode());
        }

        //查询父节点信息，组装parentIds
        QueryWrapper<AdminResource> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("id",requestDTO.getParentId());
        AdminResource parentResource = getOne(wrapper1);
        if(null == parentResource) {
            throw new BaseException(ApplicationError.NOT_EXIST_PARENTID.getMessage(), ApplicationError.NOT_EXIST_PARENTID.getCode());
        }
        String parentIds = parentResource.getParentIds()+requestDTO.getParentId()+"/";

        AdminResource resource = new AdminResource();
        BeanUtils.copyProperties(requestDTO,resource);
        resource.setParentIds(parentIds);
        save(resource);

        responseDTO.setTransactionStatus(transactionStatus);
        log.info("doResourceAddition end");
        return responseDTO;
    }

    @Override
    public XfaceCmsAdminResourceModifyInquiryResponseDTO doResourceModifyInquiry(XfaceCmsAdminResourceModifyInquiryRequestDTO requestDTO) {
        log.info("doResourceModifyInquiry start");
        XfaceCmsAdminResourceModifyInquiryResponseDTO responseDTO = new XfaceCmsAdminResourceModifyInquiryResponseDTO();
        TransactionStatus transactionStatus = new TransactionStatus();

        QueryWrapper<AdminResource> wrapper = new QueryWrapper<>();
        wrapper.eq("id",requestDTO.getId());
        wrapper.select("id","name","permission","icon","url","priority","type","available");
        AdminResource resource = getOne(wrapper);
        if(null != resource){
            BeanUtils.copyProperties(resource,responseDTO);
        }

        responseDTO.setTransactionStatus(transactionStatus);
        log.info("doResourceModifyInquiry end");
        return responseDTO;
    }

    @Transactional
    @Override
    public XfaceCmsAdminResourceModifyResponseDTO doResourceModify(XfaceCmsAdminResourceModifyRequestDTO requestDTO) {
        log.info("doResourceModify start");
        XfaceCmsAdminResourceModifyResponseDTO responseDTO = new XfaceCmsAdminResourceModifyResponseDTO();
        TransactionStatus transactionStatus = new TransactionStatus();

        AdminResource resource = new AdminResource();
        BeanUtils.copyProperties(requestDTO, resource);
        //更新操作
        Boolean result = updateById(resource);
        if(!result){
            throw new BaseException(ApplicationError.MODIFY_FAILED.getMessage(), ApplicationError.MODIFY_FAILED.getCode());
        }
        responseDTO.setTransactionStatus(transactionStatus);
        log.info("doResourceModify end");
        return responseDTO;
    }

    @Transactional
    @Override
    public XfaceCmsAdminResourceModifyStatusResponseDTO doResourceModifyStatus(XfaceCmsAdminResourceModifyStatusRequestDTO requestDTO) {
        log.info("doResourceModifyStatus start");
        XfaceCmsAdminResourceModifyStatusResponseDTO responseDTO = new XfaceCmsAdminResourceModifyStatusResponseDTO();
        TransactionStatus transactionStatus = new TransactionStatus();

        AdminResource resource = new AdminResource();
        BeanUtils.copyProperties(requestDTO, resource);
        //更新操作
        Boolean result = updateById(resource);
        if(!result){
            throw new BaseException(ApplicationError.DELETE_FAILED.getMessage(), ApplicationError.DELETE_FAILED.getCode());
        }

        responseDTO.setTransactionStatus(transactionStatus);
        log.info("doResourceModifyStatus end");
        return responseDTO;
    }

}
