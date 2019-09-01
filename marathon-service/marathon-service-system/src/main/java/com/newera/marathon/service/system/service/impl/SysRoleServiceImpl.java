package com.newera.marathon.service.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newera.marathon.common.constant.OtherConstant;
import com.newera.marathon.common.utils.DateUtils;
import com.newera.marathon.dto.system.inquiry.*;
import com.newera.marathon.dto.system.maintenance.*;
import com.newera.marathon.service.system.entity.SysResource;
import com.newera.marathon.service.system.entity.SysRole;
import com.newera.marathon.service.system.entity.SysRoleResource;
import com.newera.marathon.service.system.mapper.SysResourceMapper;
import com.newera.marathon.service.system.mapper.SysRoleMapper;
import com.newera.marathon.service.system.mapper.SysRoleResourceMapper;
import com.newera.marathon.service.system.model.ApplicationError;
import com.newera.marathon.service.system.service.SysRoleResourceService;
import com.newera.marathon.service.system.service.SysRoleService;
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
    @Autowired
    private SysRoleResourceMapper sysRoleResourceMapper;
    @Autowired
    private SysResourceMapper sysResourceMapper;
    @Autowired
    private SysRoleResourceService sysRoleResourceService;

    @Override
    public XfaceSysRoleInquiryPageResponseDTO doSysRoleInquiryPage(XfaceSysRoleInquiryPageRequestDTO requestDTO) {
        log.info("doSysRoleInquiryPage start");
        XfaceSysRoleInquiryPageResponseDTO responseDTO = new XfaceSysRoleInquiryPageResponseDTO();
        TransactionStatus transactionStatus = new TransactionStatus();
        PageModel pageModel = requestDTO.getPage();
        Page<SysRole> page = new Page<>(pageModel.getCurrent(), pageModel.getSize());
        page.setDesc("gmt_create");
        //方式一
        //Page<XfaceSysRoleInquiryPageResponseSubDTO> page = new Page<>(pageModel.getCurrent(), pageModel.getSize());
        //List<XfaceSysRoleInquiryPageResponseSubDTO> dataList = sysRoleMapper.querySysRolePage(page, requestDTO);
        //方式二
        QueryWrapper<SysRole> wrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(requestDTO.getRoleName())){
            wrapper.like("role_name",requestDTO.getRoleName());
        }
        wrapper.select("id","role_name","available","remark","gmt_create");
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
    public XfaceSysRoleAuthInquiryResponseDTO doSysRoleAuthInquiry(XfaceSysRoleAuthInquiryRequestDTO requestDTO) {
        log.info("doSysRoleAuthInquiry start");
        XfaceSysRoleAuthInquiryResponseDTO responseDTO = new XfaceSysRoleAuthInquiryResponseDTO();
        TransactionStatus transactionStatus = new TransactionStatus();
        //查询所有资源
        QueryWrapper<SysResource> wrapper = new QueryWrapper<>();
        //wrapper.eq("system_id",requestDTO.getSystemId());
        wrapper.select("id","parent_id","name","available");
        wrapper.orderByAsc("priority");
        List<SysResource> all = sysResourceMapper.selectList(wrapper);
        //查询已授权的资源
        QueryWrapper<SysRoleResource> roleResourceWrapper = new QueryWrapper<>();
        roleResourceWrapper.eq("role_id",requestDTO.getRoleId());
        roleResourceWrapper.select("resource_id");
        List<SysRoleResource> roleResourceList = sysRoleResourceMapper.selectList(roleResourceWrapper);
        //循环所有资源，标记已授权的资源，并递归生成树
        List<XfaceSysRoleAuthInquiryResponseSubDTO> result = createTree(all, roleResourceList);
        responseDTO.setDataList(result);
        responseDTO.setTransactionStatus(transactionStatus);
        log.info("doSysRoleAuthInquiry end");
        return responseDTO;
    }

    public static void main(String[] args) {
        List<String> ids = new ArrayList<>();
        List<Integer> now = ids.stream().map(w->Integer.parseInt(w)).collect(Collectors.toList());
        now.forEach(System.out::println);
    }
    @Transactional
    @Override
    public XfaceSysRoleAuthResponseDTO doSysRoleAuth(XfaceSysRoleAuthRequestDTO requestDTO) {
        log.info("doSysRoleAuth start");
        XfaceSysRoleAuthResponseDTO responseDTO = new XfaceSysRoleAuthResponseDTO();
        TransactionStatus transactionStatus = new TransactionStatus();
        //查询已授权的资源
        QueryWrapper<SysRoleResource> roleResourceWrapper = new QueryWrapper<>();
        roleResourceWrapper.eq("role_id",requestDTO.getRoleId());
        roleResourceWrapper.select("id","resource_id");
        //原来的
        List<SysRoleResource> original = sysRoleResourceMapper.selectList(roleResourceWrapper);
        //现在的
        List<String> ids = Arrays.asList(requestDTO.getResourceIds().split(","));
        List<Integer> now = ids.stream().filter(x->StringUtils.isNotBlank(x)).map(w->Integer.parseInt(w)).collect(Collectors.toList());
        if(!CollectionUtils.isEmpty(original) && !CollectionUtils.isEmpty(now)){
            //original中不存在now的，删除
            List<SysRoleResource> deleteList = original.stream().filter(w->{
                return now.stream().noneMatch(x->x==w.getResourceId());
            }).collect(Collectors.toList());
            if(!CollectionUtils.isEmpty(deleteList)){
                //需要被删除的ids
                List<Integer> deleteIds = deleteList.stream().map(w->w.getId()).collect(Collectors.toList());
                //批量删除
                Boolean deleteSuccess = sysRoleResourceService.removeByIds(deleteIds);
            }
            //now中不存在original的，添加
            List<Integer> addResourceIdList = now.stream().filter(w->{
                return original.stream().noneMatch(x->x.getResourceId()==w);
            }).collect(Collectors.toList());
            if(!CollectionUtils.isEmpty(addResourceIdList)){
                //需要添加的数据
                Boolean addSuccess = batchInsertRoleResource(addResourceIdList, requestDTO.getRoleId(), requestDTO.getOperator());
            }
        }else if(CollectionUtils.isEmpty(original) && !CollectionUtils.isEmpty(now)){
            //直接添加数据
            Boolean addSuccess = batchInsertRoleResource(now, requestDTO.getRoleId(), requestDTO.getOperator());
        }else if(!CollectionUtils.isEmpty(original) && CollectionUtils.isEmpty(now)){
            //直接删除数据
            List<Integer> deleteIds = original.stream().map(w->w.getId()).collect(Collectors.toList());
            //批量删除
            Boolean deleteSuccess = sysRoleResourceService.removeByIds(deleteIds);
        }
        responseDTO.setTransactionStatus(transactionStatus);
        log.info("doSysRoleAuth end");
        return responseDTO;
    }
    public Boolean batchInsertRoleResource(List<Integer> resourceIdList,Integer roleId,String operator){
        List<SysRoleResource> addList = new ArrayList<>();
        resourceIdList.forEach(w->{
            SysRoleResource sysRoleResource = new SysRoleResource();
            sysRoleResource.setRoleId(roleId);
            sysRoleResource.setResourceId(w);
            sysRoleResource.setCreateOperator(operator);
            addList.add(sysRoleResource);
        });
        //批量添加
        return sysRoleResourceService.saveBatch(addList);
    }

    @Override
    public XfaceSysRoleInquirySelectResponseDTO doSysRoleInquirySelect() {
        log.info("doSysRoleInquirySelect start");
        XfaceSysRoleInquirySelectResponseDTO responseDTO = new XfaceSysRoleInquirySelectResponseDTO();
        TransactionStatus transactionStatus = new TransactionStatus();
        List<XfaceSysRoleInquirySelectResponseSubDTO> responseSubDTOS = new ArrayList<>();
        QueryWrapper<SysRole> wrapper = new QueryWrapper<>();
        wrapper.select("id","role_name","available");
        List<SysRole> sysRoles = list(wrapper);
        sysRoles.forEach(w->{
            XfaceSysRoleInquirySelectResponseSubDTO responseSubDTO = new XfaceSysRoleInquirySelectResponseSubDTO();
            responseSubDTO.setId(w.getId());
            responseSubDTO.setName(w.getRoleName());
            responseSubDTO.setAvailable(w.getAvailable());
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
        //更新操作
        Boolean result = updateById(sysRole);
        if(!result){
            throw new BaseException(ApplicationError.SYS_DELETE_FAILED.getMessage(), ApplicationError.SYS_DELETE_FAILED.getCode());
        }
        responseDTO.setTransactionStatus(transactionStatus);
        log.info("doSysRoleDelete end");
        return responseDTO;
    }

    public List<XfaceSysRoleAuthInquiryResponseSubDTO> createTree(List<SysResource> sysResourceList, List<SysRoleResource> roleResourceList){
        List<XfaceSysRoleAuthInquiryResponseSubDTO> list = new ArrayList<>();
        for (SysResource resource : sysResourceList) {
            if(resource.getParentId() == OtherConstant.RESOURCE_TOP_PARENT_ID){//判断是否是一级菜单
                XfaceSysRoleAuthInquiryResponseSubDTO treeObject = new XfaceSysRoleAuthInquiryResponseSubDTO();
                Boolean checked = roleResourceList.stream().anyMatch(w->w.getResourceId() == resource.getId());
                treeObject.setChecked(checked);
                treeObject.setId(resource.getId());
                treeObject.setTitle(resource.getName());
                treeObject.setDisabled(resource.getAvailable()==0 ? true : false);
                treeObject.setSpread(true);
                treeObject.setChildren(getChildren(resource.getId(),sysResourceList,roleResourceList));
                list.add(treeObject);
            }
        }
        return list;
    }
    public List<XfaceSysRoleAuthInquiryResponseSubDTO> getChildren(Integer parentId,List<SysResource> sysResourceList, List<SysRoleResource> roleResourceList){
        List<XfaceSysRoleAuthInquiryResponseSubDTO> list = new ArrayList<>();
        for (SysResource resource : sysResourceList) {
            if(resource.getParentId().equals(parentId)){
                XfaceSysRoleAuthInquiryResponseSubDTO treeObject = new XfaceSysRoleAuthInquiryResponseSubDTO();
                Boolean checked = roleResourceList.stream().anyMatch(w->w.getResourceId() == resource.getId());
                treeObject.setChecked(checked);
                treeObject.setId(resource.getId());
                treeObject.setTitle(resource.getName());
                treeObject.setDisabled(resource.getAvailable()==0 ? true : false);
                treeObject.setSpread(true);
                treeObject.setChildren(getChildren(resource.getId(),sysResourceList,roleResourceList));
                list.add(treeObject);
            }
        }
        return list;
    }
}
