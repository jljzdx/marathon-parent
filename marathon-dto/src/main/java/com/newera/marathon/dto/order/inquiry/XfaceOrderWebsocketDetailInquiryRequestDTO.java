package com.newera.marathon.dto.order.inquiry;

import com.spaking.boot.starter.core.model.PageModel;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class XfaceOrderWebsocketDetailInquiryRequestDTO {

    @NotNull(message = "分页条件不能为空")
    private PageModel page;
    /**
     * 客户端ID，也可以是客户ID
     */
    private String clientId;
}