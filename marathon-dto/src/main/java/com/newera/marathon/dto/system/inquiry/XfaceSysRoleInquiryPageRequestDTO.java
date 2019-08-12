package com.newera.marathon.dto.system.inquiry;

import com.spaking.boot.starter.core.model.PageModel;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class XfaceSysRoleInquiryPageRequestDTO {

    @NotNull(message = "分页条件不能为空")
    private PageModel page;

    @Size(max = 50, message= "角色名称长度不能超过50位")
    private String roleName;
}
