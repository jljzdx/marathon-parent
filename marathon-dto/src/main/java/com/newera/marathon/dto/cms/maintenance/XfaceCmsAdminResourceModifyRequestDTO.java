package com.newera.marathon.dto.cms.maintenance;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class XfaceCmsAdminResourceModifyRequestDTO {


    @NotNull(message = "资源ID不能为空")
    private Integer id;

    @NotBlank(message = "资源名称不能为空")
    @Size(max = 255, message= "资源名称长度不能超过255位")
    private String name;

    @Size(max = 255, message= "权限码长度不能超过255位")
    private String permission;

    @Size(max = 255, message= "图标长度不能超过255位")
    private String icon;

    @Size(max = 255, message= "访问路径长度不能超过255位")
    private String url;

    @NotNull(message = "显示顺序不能为空")
    private Integer priority;

    @NotNull(message = "类型不能为空")
    private Integer type;

    @NotBlank(message = "更新人不能为空")
    @Size(max = 255, message= "更新人长度不能超过255位")
    private String modifyOperator;
}
