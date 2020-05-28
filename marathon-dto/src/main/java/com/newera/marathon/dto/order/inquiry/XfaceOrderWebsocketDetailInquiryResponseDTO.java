package com.newera.marathon.dto.order.inquiry;

import com.spaking.boot.starter.core.dto.GenericResponseDTO;
import com.spaking.boot.starter.core.model.PageModel;
import lombok.Data;

import java.util.List;

@Data
public class XfaceOrderWebsocketDetailInquiryResponseDTO extends GenericResponseDTO {

    private PageModel page;
    /**
     * 客户端连接数
     */
    private Integer count;

    private List<XfaceOrderWebsocketDetailInquiryResponseSubDTO> dataList;

}
