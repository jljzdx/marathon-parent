package com.newera.marathon.dto.cos.maintenance;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class XfaceCosSendMailRequestDTO {

    @ApiModelProperty(value = "收件人",required = true)
    @NotBlank(message = "收件人不能为空")
    private String toMail;

    @ApiModelProperty(value = "主题",required = true)
    @NotBlank(message = "主题不能为空")
    private String subject;

    @ApiModelProperty(value = "内容",required = true)
    @NotBlank(message = "内容不能为空")
    private String content;

    @ApiModelProperty(value = "抄送人，多个用英文逗号隔开",required = true)
    private String cc;

    @ApiModelProperty(value = "附件路径，当type=3时必填",required = true)
    private String filePath;

    @ApiModelProperty(value = "邮件类型（1/2/3：普通邮件/HTML邮件/附件邮件）",required = true)
    @NotNull(message = "邮件类型不能为空")
    private Integer type;

}
