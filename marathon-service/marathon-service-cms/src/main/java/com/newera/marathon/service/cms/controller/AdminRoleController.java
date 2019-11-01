package com.newera.marathon.service.cms.controller;


import com.newera.marathon.dto.cms.inquiry.*;
import com.newera.marathon.dto.cms.maintenance.*;
import com.newera.marathon.service.cms.service.AdminRoleService;
import com.spaking.boot.starter.core.annotation.AvoidRepeatSubmit;
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
public class AdminRoleController {

    @Autowired
    private AdminRoleService adminRoleService;

    @BusinessLogger(key = "CMS",value = "roleInquirySelect")
    @ApiOperation(value="查询所有角色--下拉", notes="查询所有角色--下拉")
    @PostMapping("/cms/admin/role/inquiry/select")
    public XfaceCmsAdminRoleSelectInquiryResponseDTO roleInquirySelect(){
        XfaceCmsAdminRoleSelectInquiryResponseDTO responseDTO = adminRoleService.doRoleInquirySelect();
        return responseDTO;
    }

    @BusinessLogger(key = "CMS",value = "roleInquiryPage")
    @ApiOperation(value="分页查询角色", notes="分页查询角色")
    @ApiImplicitParam(name = "requestDTO", value = "入参对象", dataType = "XfaceCmsAdminRoleInquiryPageRequestDTO")
    @PostMapping("/cms/admin/role/inquiry/page")
    public XfaceCmsAdminRoleInquiryPageResponseDTO roleInquiryPage(@Valid @RequestBody XfaceCmsAdminRoleInquiryPageRequestDTO requestDTO){
        XfaceCmsAdminRoleInquiryPageResponseDTO responseDTO = adminRoleService.doRoleInquiryPage(requestDTO);
        return responseDTO;
    }

    @AvoidRepeatSubmit
    @BusinessLogger(key = "CMS",value = "roleAddition")
    @PostMapping("/cms/admin/role/addition")
    public XfaceCmsAdminRoleAdditionResponseDTO roleAddition(@Valid @RequestBody XfaceCmsAdminRoleAdditionRequestDTO requestDTO){
        XfaceCmsAdminRoleAdditionResponseDTO responseDTO = adminRoleService.doRoleAddition(requestDTO);
        return responseDTO;
    }

    @BusinessLogger(key = "CMS",value = "roleModifyInquiry")
    @PostMapping("/cms/admin/role/modify/inquiry")
    public XfaceCmsAdminRoleModifyInquiryResponseDTO roleModifyInquiry(@Valid @RequestBody XfaceCmsAdminRoleModifyInquiryRequestDTO requestDTO){
        XfaceCmsAdminRoleModifyInquiryResponseDTO responseDTO = adminRoleService.doRoleModifyInquiry(requestDTO);
        return responseDTO;
    }

    @BusinessLogger(key = "CMS",value = "roleModify")
    @PostMapping("/cms/admin/role/modify")
    public XfaceCmsAdminRoleModifyResponseDTO roleModify(@Valid @RequestBody XfaceCmsAdminRoleModifyRequestDTO requestDTO){
        XfaceCmsAdminRoleModifyResponseDTO responseDTO = adminRoleService.doRoleModify(requestDTO);
        return responseDTO;
    }

    @BusinessLogger(key = "CMS",value = "roleModifyStatus")
    @PostMapping("/cms/admin/role/modify/status")
    public XfaceCmsAdminRoleModifyStatusResponseDTO roleModifyStatus(@Valid @RequestBody XfaceCmsAdminRoleModifyStatusRequestDTO requestDTO){
        XfaceCmsAdminRoleModifyStatusResponseDTO responseDTO = adminRoleService.doRoleModifyStatus(requestDTO);
        return responseDTO;
    }

    @BusinessLogger(key = "CMS",value = "roleAuthInquiry")
    @PostMapping("/cms/admin/role/auth/inquiry")
    public XfaceCmsAdminRoleAuthInquiryResponseDTO roleAuthInquiry(@Valid @RequestBody XfaceCmsAdminRoleAuthInquiryRequestDTO requestDTO){
        XfaceCmsAdminRoleAuthInquiryResponseDTO responseDTO = adminRoleService.doRoleAuthInquiry(requestDTO);
        return responseDTO;
    }

    @BusinessLogger(key = "CMS",value = "roleAuth")
    @PostMapping("/cms/admin/role/auth")
    public XfaceCmsAdminRoleAuthResponseDTO roleAuth(@Valid @RequestBody XfaceCmsAdminRoleAuthRequestDTO requestDTO){
        XfaceCmsAdminRoleAuthResponseDTO responseDTO = adminRoleService.doRoleAuth(requestDTO);
        return responseDTO;
    }
}
