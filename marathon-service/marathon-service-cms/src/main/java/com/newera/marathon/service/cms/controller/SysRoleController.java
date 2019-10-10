package com.newera.marathon.service.cms.controller;


import com.newera.marathon.dto.system.inquiry.*;
import com.newera.marathon.dto.system.maintenance.*;
import com.newera.marathon.service.cms.service.SysRoleService;
import com.spaking.boot.starter.core.annotation.BusinessLogger;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author MicroBin
 * @since 2019-04-20
 */
@RestController
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;


    @BusinessLogger(key = "CMS",value = "sysRoleInquirySelect")
    @ApiOperation(value="查询所有角色--下拉", notes="查询所有角色--下拉")
    @PostMapping("/sys/role/inquiry/select")
    public XfaceSysRoleInquirySelectResponseDTO sysRoleInquirySelect(){
        XfaceSysRoleInquirySelectResponseDTO responseDTO = sysRoleService.doSysRoleInquirySelect();
        return responseDTO;
    }

    @BusinessLogger(key = "CMS",value = "sysRoleInquiryPage")
    @ApiOperation(value="分页查询角色", notes="分页查询角色")
    @ApiImplicitParam(name = "requestDTO", value = "入参对象", dataType = "XfaceSysRoleInquiryPageRequestDTO")
    @PostMapping("/sys/role/inquiry/page")
    public XfaceSysRoleInquiryPageResponseDTO sysRoleInquiryPage(@Valid @RequestBody XfaceSysRoleInquiryPageRequestDTO requestDTO){
        XfaceSysRoleInquiryPageResponseDTO responseDTO = sysRoleService.doSysRoleInquiryPage(requestDTO);
        return responseDTO;
    }

    @BusinessLogger(key = "CMS",value = "sysRoleAddition")
    @PostMapping("/sys/role/addition")
    public XfaceSysRoleAdditionResponseDTO sysRoleAddition(@Valid @RequestBody XfaceSysRoleAdditionRequestDTO requestDTO){
        XfaceSysRoleAdditionResponseDTO responseDTO = sysRoleService.doSysRoleAddition(requestDTO);
        return responseDTO;
    }

    @BusinessLogger(key = "CMS",value = "sysRoleModifyInquiry")
    @PostMapping("/sys/role/modify/inquiry")
    public XfaceSysRoleModifyInquiryResponseDTO sysRoleModifyInquiry(@Valid @RequestBody XfaceSysRoleModifyInquiryRequestDTO requestDTO){
        XfaceSysRoleModifyInquiryResponseDTO responseDTO = sysRoleService.doSysRoleModifyInquiry(requestDTO);
        return responseDTO;
    }

    @BusinessLogger(key = "CMS",value = "sysRoleModify")
    @PostMapping("/sys/role/modify")
    public XfaceSysRoleModifyResponseDTO sysRoleModify(@Valid @RequestBody XfaceSysRoleModifyRequestDTO requestDTO){
        XfaceSysRoleModifyResponseDTO responseDTO = sysRoleService.doSysRoleModify(requestDTO);
        return responseDTO;
    }

    @BusinessLogger(key = "CMS",value = "sysRoleModifyStatus")
    @PostMapping("/sys/role/modify/status")
    public XfaceSysRoleModifyStatusResponseDTO sysRoleModifyStatus(@Valid @RequestBody XfaceSysRoleModifyStatusRequestDTO requestDTO){
        XfaceSysRoleModifyStatusResponseDTO responseDTO = sysRoleService.doSysRoleModifyStatus(requestDTO);
        return responseDTO;
    }

    @BusinessLogger(key = "CMS",value = "sysRoleAuthInquiry")
    @PostMapping("/sys/role/auth/inquiry")
    public XfaceSysRoleAuthInquiryResponseDTO sysRoleAuthInquiry(@Valid @RequestBody XfaceSysRoleAuthInquiryRequestDTO requestDTO){
        XfaceSysRoleAuthInquiryResponseDTO responseDTO = sysRoleService.doSysRoleAuthInquiry(requestDTO);
        return responseDTO;
    }

    @BusinessLogger(key = "CMS",value = "sysRoleAuth")
    @PostMapping("/sys/role/auth")
    public XfaceSysRoleAuthResponseDTO sysRoleAuth(@Valid @RequestBody XfaceSysRoleAuthRequestDTO requestDTO){
        XfaceSysRoleAuthResponseDTO responseDTO = sysRoleService.doSysRoleAuth(requestDTO);
        return responseDTO;
    }
}
