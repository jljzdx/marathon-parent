package com.newera.marathon.service.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newera.marathon.common.constant.OtherConstant;
import com.newera.marathon.common.model.ApplicationError;
import com.newera.marathon.common.utils.DateUtils;
import com.newera.marathon.dto.cms.inquiry.*;
import com.newera.marathon.dto.cms.maintenance.*;
import com.newera.marathon.service.cms.entity.AdminResource;
import com.newera.marathon.service.cms.entity.AdminRole;
import com.newera.marathon.service.cms.entity.AdminRoleResource;
import com.newera.marathon.service.cms.mapper.AdminResourceMapper;
import com.newera.marathon.service.cms.mapper.AdminRoleMapper;
import com.newera.marathon.service.cms.mapper.AdminRoleResourceMapper;
import com.newera.marathon.service.cms.service.AdminRoleResourceService;
import com.newera.marathon.service.cms.service.AdminRoleService;
import com.spaking.boot.starter.core.exception.BaseException;
import com.spaking.boot.starter.core.model.PageModel;
import com.spaking.boot.starter.core.model.TransactionStatus;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author MicroBin
 * @since 2019-04-20
 */
@Slf4j
@Service
public class AdminRoleServiceImpl extends ServiceImpl<AdminRoleMapper, AdminRole> implements AdminRoleService {
    @Autowired
    private AdminRoleResourceMapper adminRoleResourceMapper;
    @Autowired
    private AdminResourceMapper adminResourceMapper;
    @Autowired
    private AdminRoleResourceService adminRoleResourceService;

    @Override
    public XfaceCmsAdminRoleInquiryPageResponseDTO doRoleInquiryPage(XfaceCmsAdminRoleInquiryPageRequestDTO requestDTO) {
        log.info("doRoleInquiryPage start");
        XfaceCmsAdminRoleInquiryPageResponseDTO responseDTO = new XfaceCmsAdminRoleInquiryPageResponseDTO();
        TransactionStatus transactionStatus = new TransactionStatus();
        PageModel pageModel = requestDTO.getPage();
        Page<AdminRole> page = new Page<>(pageModel.getCurrent(), pageModel.getSize());
        page.setDesc("gmt_create");
        //方式一
        //Page<XfaceCmsAdminRoleInquiryPageResponseSubDTO> page = new Page<>(pageModel.getCurrent(), pageModel.getSize());
        //List<XfaceCmsAdminRoleInquiryPageResponseSubDTO> dataList = sysRoleMapper.queryRolePage(page, requestDTO);
        //方式二
        QueryWrapper<AdminRole> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(requestDTO.getRoleName())) {
            wrapper.like("role_name", requestDTO.getRoleName());
        }
        wrapper.select("id", "role_name", "available", "remark", "gmt_create");
        IPage<AdminRole> iPage = page(page, wrapper);
        List<XfaceCmsAdminRoleInquiryPageResponseSubDTO> dataList = new ArrayList<>();
        iPage.getRecords().forEach(w -> {
            XfaceCmsAdminRoleInquiryPageResponseSubDTO responseSubDTO = new XfaceCmsAdminRoleInquiryPageResponseSubDTO();
            BeanUtils.copyProperties(w, responseSubDTO);
            responseSubDTO.setGmtCreate(DateUtils.dateToStringFormat(w.getGmtCreate(), DateUtils.FORMAT1));
            dataList.add(responseSubDTO);
        });
        pageModel.setTotal(page.getTotal());
        responseDTO.setPage(pageModel);
        responseDTO.setDataList(dataList);
        responseDTO.setTransactionStatus(transactionStatus);
        log.info("doRoleInquiryPage end");
        return responseDTO;
    }

    @Override
    public XfaceCmsAdminRoleModifyInquiryResponseDTO doRoleModifyInquiry(XfaceCmsAdminRoleModifyInquiryRequestDTO requestDTO) {
        log.info("doRoleModifyInquiry start");
        XfaceCmsAdminRoleModifyInquiryResponseDTO responseDTO = new XfaceCmsAdminRoleModifyInquiryResponseDTO();
        TransactionStatus transactionStatus = new TransactionStatus();
        //查询条件
        QueryWrapper<AdminRole> wrapper = new QueryWrapper<>();
        wrapper.eq("id", requestDTO.getId());
        wrapper.select("id", "role_name", "remark");
        //查询用户信息
        AdminRole role = getOne(wrapper);
        //复制对象属性
        if (null != role) {
            BeanUtils.copyProperties(role, responseDTO);
        }
        responseDTO.setTransactionStatus(transactionStatus);
        log.info("doRoleModifyInquiry end");
        return responseDTO;
    }

    @Override
    public XfaceCmsAdminRoleAuthInquiryResponseDTO doRoleAuthInquiry(XfaceCmsAdminRoleAuthInquiryRequestDTO requestDTO) {
        log.info("doRoleAuthInquiry start");
        XfaceCmsAdminRoleAuthInquiryResponseDTO responseDTO = new XfaceCmsAdminRoleAuthInquiryResponseDTO();
        TransactionStatus transactionStatus = new TransactionStatus();
        //查询所有资源
        QueryWrapper<AdminResource> wrapper = new QueryWrapper<>();
        //wrapper.eq("system_id",requestDTO.getSystemId());
        wrapper.select("id", "parent_id", "name", "available");
        wrapper.orderByAsc("priority");
        List<AdminResource> all = adminResourceMapper.selectList(wrapper);
        //查询已授权的资源
        QueryWrapper<AdminRoleResource> roleResourceWrapper = new QueryWrapper<>();
        roleResourceWrapper.eq("role_id", requestDTO.getRoleId());
        roleResourceWrapper.select("resource_id");
        List<AdminRoleResource> roleResourceList = adminRoleResourceMapper.selectList(roleResourceWrapper);
        //循环所有资源，标记已授权的资源，并递归生成树
        List<XfaceCmsAdminRoleAuthInquiryResponseSubDTO> result = createTree(all, roleResourceList);
        responseDTO.setDataList(result);
        responseDTO.setTransactionStatus(transactionStatus);
        log.info("doRoleAuthInquiry end");
        return responseDTO;
    }

    @Transactional
    @Override
    public XfaceCmsAdminRoleAuthResponseDTO doRoleAuth(XfaceCmsAdminRoleAuthRequestDTO requestDTO) {
        log.info("doRoleAuth start");
        XfaceCmsAdminRoleAuthResponseDTO responseDTO = new XfaceCmsAdminRoleAuthResponseDTO();
        TransactionStatus transactionStatus = new TransactionStatus();
        //查询已授权的资源
        QueryWrapper<AdminRoleResource> roleResourceWrapper = new QueryWrapper<>();
        roleResourceWrapper.eq("role_id", requestDTO.getRoleId());
        roleResourceWrapper.select("id", "resource_id");
        //原来的
        List<AdminRoleResource> original = adminRoleResourceMapper.selectList(roleResourceWrapper);
        //现在的
        List<String> ids = Arrays.asList(requestDTO.getResourceIds().split(","));
        List<Integer> now = ids.stream().filter(x -> StringUtils.isNotBlank(x)).map(w -> Integer.parseInt(w)).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(original) && !CollectionUtils.isEmpty(now)) {
            //original中不存在now的，删除
            List<AdminRoleResource> deleteList = original.stream().filter(w -> {
                return now.stream().noneMatch(x -> x == w.getResourceId());
            }).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(deleteList)) {
                //需要被删除的ids
                List<Integer> deleteIds = deleteList.stream().map(w -> w.getId()).collect(Collectors.toList());
                //批量删除
                Boolean deleteSuccess = adminRoleResourceService.removeByIds(deleteIds);
            }
            //now中不存在original的，添加
            List<Integer> addResourceIdList = now.stream().filter(w -> {
                return original.stream().noneMatch(x -> x.getResourceId() == w);
            }).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(addResourceIdList)) {
                //需要添加的数据
                Boolean addSuccess = batchInsertRoleResource(addResourceIdList, requestDTO.getRoleId(), requestDTO.getOperator());
            }
        } else if (CollectionUtils.isEmpty(original) && !CollectionUtils.isEmpty(now)) {
            //直接添加数据
            Boolean addSuccess = batchInsertRoleResource(now, requestDTO.getRoleId(), requestDTO.getOperator());
        } else if (!CollectionUtils.isEmpty(original) && CollectionUtils.isEmpty(now)) {
            //直接删除数据
            List<Integer> deleteIds = original.stream().map(w -> w.getId()).collect(Collectors.toList());
            //批量删除
            Boolean deleteSuccess = adminRoleResourceService.removeByIds(deleteIds);
        }
        responseDTO.setTransactionStatus(transactionStatus);
        log.info("doRoleAuth end");
        return responseDTO;
    }

    public Boolean batchInsertRoleResource(List<Integer> resourceIdList, Integer roleId, String operator) {
        List<AdminRoleResource> addList = new ArrayList<>();
        resourceIdList.forEach(w -> {
            AdminRoleResource roleResource = new AdminRoleResource();
            roleResource.setRoleId(roleId);
            roleResource.setResourceId(w);
            roleResource.setCreateOperator(operator);
            addList.add(roleResource);
        });
        //批量添加
        return adminRoleResourceService.saveBatch(addList);
    }

    @Override
    public XfaceCmsAdminRoleSelectInquiryResponseDTO doRoleInquirySelect() {
        log.info("doRoleInquirySelect start");
        XfaceCmsAdminRoleSelectInquiryResponseDTO responseDTO = new XfaceCmsAdminRoleSelectInquiryResponseDTO();
        TransactionStatus transactionStatus = new TransactionStatus();
        List<XfaceCmsAdminRoleSelectInquiryResponseSubDTO> responseSubDTOS = new ArrayList<>();
        QueryWrapper<AdminRole> wrapper = new QueryWrapper<>();
        wrapper.select("id", "role_name", "available");
        List<AdminRole> roles = list(wrapper);
        roles.forEach(w -> {
            XfaceCmsAdminRoleSelectInquiryResponseSubDTO responseSubDTO = new XfaceCmsAdminRoleSelectInquiryResponseSubDTO();
            responseSubDTO.setId(w.getId());
            responseSubDTO.setName(w.getRoleName());
            responseSubDTO.setAvailable(w.getAvailable());
            responseSubDTOS.add(responseSubDTO);
        });
        responseDTO.setDataList(responseSubDTOS);
        responseDTO.setTransactionStatus(transactionStatus);
        log.info("doRoleInquirySelect end");
        return responseDTO;
    }

    @Transactional
    @Override
    public XfaceCmsAdminRoleAdditionResponseDTO doRoleAddition(XfaceCmsAdminRoleAdditionRequestDTO requestDTO) {
        log.info("doRoleAddition start");
        XfaceCmsAdminRoleAdditionResponseDTO responseDTO = new XfaceCmsAdminRoleAdditionResponseDTO();
        TransactionStatus transactionStatus = new TransactionStatus();
        //判断用户名是否存在
        QueryWrapper<AdminRole> wrapper = new QueryWrapper();
        wrapper.lambda().eq(AdminRole::getRoleName, requestDTO.getRoleName());
        Integer count = count(wrapper);
        if (count > 0) {
            throw new BaseException(ApplicationError.ROLE_NAME_ALREADY_EXIST.getMessage(), ApplicationError.ROLE_NAME_ALREADY_EXIST.getCode());
        }
        //组装用户数据
        AdminRole role = new AdminRole();
        BeanUtils.copyProperties(requestDTO, role);
        //添加操作
        Boolean result = save(role);
        if (!result) {
            throw new BaseException(ApplicationError.ADDITION_FAILED.getMessage(), ApplicationError.ADDITION_FAILED.getCode());
        }
        responseDTO.setTransactionStatus(transactionStatus);
        log.info("doRoleAddition end");
        return responseDTO;
    }

    @Transactional
    @Override
    public XfaceCmsAdminRoleModifyResponseDTO doRoleModify(XfaceCmsAdminRoleModifyRequestDTO requestDTO) {
        log.info("doRoleModify start");
        XfaceCmsAdminRoleModifyResponseDTO responseDTO = new XfaceCmsAdminRoleModifyResponseDTO();
        TransactionStatus transactionStatus = new TransactionStatus();
        //组装用户数据
        AdminRole role = new AdminRole();
        BeanUtils.copyProperties(requestDTO, role);
        //更新操作
        Boolean result = updateById(role);
        if (!result) {
            throw new BaseException(ApplicationError.MODIFY_FAILED.getMessage(), ApplicationError.MODIFY_FAILED.getCode());
        }
        responseDTO.setTransactionStatus(transactionStatus);
        log.info("doRoleModify end");
        return responseDTO;
    }

    @Transactional
    @Override
    public XfaceCmsAdminRoleModifyStatusResponseDTO doRoleModifyStatus(XfaceCmsAdminRoleModifyStatusRequestDTO requestDTO) {
        log.info("doRoleDelete start");
        XfaceCmsAdminRoleModifyStatusResponseDTO responseDTO = new XfaceCmsAdminRoleModifyStatusResponseDTO();
        TransactionStatus transactionStatus = new TransactionStatus();
        //组装用户数据
        AdminRole role = new AdminRole();
        BeanUtils.copyProperties(requestDTO, role);
        //更新操作
        Boolean result = updateById(role);
        if (!result) {
            throw new BaseException(ApplicationError.DELETE_FAILED.getMessage(), ApplicationError.DELETE_FAILED.getCode());
        }
        responseDTO.setTransactionStatus(transactionStatus);
        log.info("doRoleDelete end");
        return responseDTO;
    }

    public List<XfaceCmsAdminRoleAuthInquiryResponseSubDTO> createTree(List<AdminResource> resourceList, List<AdminRoleResource> roleResourceList) {
        List<XfaceCmsAdminRoleAuthInquiryResponseSubDTO> list = new ArrayList<>();
        for (AdminResource resource : resourceList) {
            if (resource.getParentId() == OtherConstant.RESOURCE_TOP_PARENT_ID) {//判断是否是一级菜单
                XfaceCmsAdminRoleAuthInquiryResponseSubDTO treeObject = new XfaceCmsAdminRoleAuthInquiryResponseSubDTO();
                Boolean checked = roleResourceList.stream().anyMatch(w -> w.getResourceId() == resource.getId());
                treeObject.setChecked(checked);
                treeObject.setValue(resource.getId());
                treeObject.setName(resource.getName());
                treeObject.setDisabled(resource.getAvailable() == 0 ? true : false);
                treeObject.setList(getChildren(resource.getId(), resourceList, roleResourceList));
                list.add(treeObject);
            }
        }
        return list;
    }

    public List<XfaceCmsAdminRoleAuthInquiryResponseSubDTO> getChildren(Integer parentId, List<AdminResource> resourceList, List<AdminRoleResource> roleResourceList) {
        List<XfaceCmsAdminRoleAuthInquiryResponseSubDTO> list = new ArrayList<>();
        for (AdminResource resource : resourceList) {
            if (resource.getParentId().equals(parentId)) {
                XfaceCmsAdminRoleAuthInquiryResponseSubDTO treeObject = new XfaceCmsAdminRoleAuthInquiryResponseSubDTO();
                Boolean checked = roleResourceList.stream().anyMatch(w -> w.getResourceId() == resource.getId());
                treeObject.setChecked(checked);
                treeObject.setValue(resource.getId());
                treeObject.setName(resource.getName());
                treeObject.setDisabled(resource.getAvailable() == 0 ? true : false);
                treeObject.setList(getChildren(resource.getId(), resourceList, roleResourceList));
                list.add(treeObject);
            }
        }
        return list;
    }
}
