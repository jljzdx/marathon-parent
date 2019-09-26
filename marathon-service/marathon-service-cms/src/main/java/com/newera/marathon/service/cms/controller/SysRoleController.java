package com.newera.marathon.service.cms.controller;


import com.newera.marathon.dto.system.inquiry.*;
import com.newera.marathon.dto.system.maintenance.*;
import com.newera.marathon.service.cms.service.SysRoleService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/sys/role")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    @ApiOperation(value="查询所有角色--下拉", notes="查询所有角色--下拉")
    @PostMapping("/inquiry/select")
    public XfaceSysRoleInquirySelectResponseDTO sysRoleInquirySelect(){
        XfaceSysRoleInquirySelectResponseDTO responseDTO = sysRoleService.doSysRoleInquirySelect();
        return responseDTO;
    }

    @ApiOperation(value="分页查询角色", notes="分页查询角色")
    @ApiImplicitParam(name = "requestDTO", value = "入参对象", dataType = "XfaceSysRoleInquiryPageRequestDTO")
    @PostMapping("/inquiry/page")
    public XfaceSysRoleInquiryPageResponseDTO sysRoleInquiryPage(@Valid @RequestBody XfaceSysRoleInquiryPageRequestDTO requestDTO){
        XfaceSysRoleInquiryPageResponseDTO responseDTO = sysRoleService.doSysRoleInquiryPage(requestDTO);
        return responseDTO;
    }

    @PostMapping("/addition")
    public XfaceSysRoleAdditionResponseDTO sysRoleAddition(@Valid @RequestBody XfaceSysRoleAdditionRequestDTO requestDTO){
        XfaceSysRoleAdditionResponseDTO responseDTO = sysRoleService.doSysRoleAddition(requestDTO);
        return responseDTO;
    }

    @PostMapping("/modify/inquiry")
    public XfaceSysRoleModifyInquiryResponseDTO sysRoleModifyInquiry(@Valid @RequestBody XfaceSysRoleModifyInquiryRequestDTO requestDTO){
        XfaceSysRoleModifyInquiryResponseDTO responseDTO = sysRoleService.doSysRoleModifyInquiry(requestDTO);
        return responseDTO;
    }

    @PostMapping("/modify")
    public XfaceSysRoleModifyResponseDTO sysRoleModify(@Valid @RequestBody XfaceSysRoleModifyRequestDTO requestDTO){
        XfaceSysRoleModifyResponseDTO responseDTO = sysRoleService.doSysRoleModify(requestDTO);
        return responseDTO;
    }

    @PostMapping("/modify/status")
    public XfaceSysRoleModifyStatusResponseDTO sysRoleModifyStatus(@Valid @RequestBody XfaceSysRoleModifyStatusRequestDTO requestDTO){
        XfaceSysRoleModifyStatusResponseDTO responseDTO = sysRoleService.doSysRoleModifyStatus(requestDTO);
        return responseDTO;
    }

    @PostMapping("/auth/inquiry")
    public XfaceSysRoleAuthInquiryResponseDTO sysRoleAuthInquiry(@Valid @RequestBody XfaceSysRoleAuthInquiryRequestDTO requestDTO){
        XfaceSysRoleAuthInquiryResponseDTO responseDTO = sysRoleService.doSysRoleAuthInquiry(requestDTO);
        return responseDTO;
    }
    @PostMapping("/auth")
    public XfaceSysRoleAuthResponseDTO sysRoleAuth(@Valid @RequestBody XfaceSysRoleAuthRequestDTO requestDTO){
        XfaceSysRoleAuthResponseDTO responseDTO = sysRoleService.doSysRoleAuth(requestDTO);
        return responseDTO;
    }
}
