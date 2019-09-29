package com.newera.marathon.dto.cust.maintenance;

import com.spaking.boot.starter.core.dto.GenericResponseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class XfaceCustCustomerLoginResponseDTO extends GenericResponseDTO {

    @ApiModelProperty(value = "客户ID")
    private Long customerId;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "令牌")
    private String token;
}
