package com.newera.marathon.dto.cms.inquiry;

import com.spaking.boot.starter.core.model.PageModel;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class XfaceCmsAdminUserInquiryPageRequestDTO {

    @NotNull(message = "分页条件不能为空")
    private PageModel page;

    @Size(max = 255, message= "用户名长度不能超过255位")
    private String userName;

    @Size(max = 19, message= "开始时间不能超过19位")
    private String startDateTime;

    @Size(max = 19, message= "结束时间不能超过19位")
    private String endDateTime;
}
