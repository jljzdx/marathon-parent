package com.newera.marathon.web.zy.controller;

import com.newera.marathon.common.web.ListResponseConverter;
import com.newera.marathon.dto.order.inquiry.XfaceOrderWebsocketDetailInquiryRequestDTO;
import com.newera.marathon.dto.order.inquiry.XfaceOrderWebsocketDetailInquiryResponseDTO;
import com.newera.marathon.dto.order.maintenance.XfaceOrderWebSocketSendAllRequestDTO;
import com.newera.marathon.dto.order.maintenance.XfaceOrderWebsocketSendAllResponseDTO;
import com.newera.marathon.microface.order.OrderWebsocketMicroService;
import com.spaking.boot.starter.core.annotation.NeedAuthorize;
import com.spaking.boot.starter.core.model.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class WebSocketController {

    @Autowired
    private OrderWebsocketMicroService orderWebsocketMicroService;

    @RequestMapping("/ws/list/html")
    public String wsList(){
        return "ws/wsList";
    }
    @RequestMapping("/ws/add/html")
    public String wsAdd(){
        return "ws/wsAdd";
    }



    @NeedAuthorize(value = "ws:list")
    @PostMapping("/ws/inquiry/page")
    @ResponseBody
    public Map websocketInquiryPage(Long page, Long limit, String clientId){
        XfaceOrderWebsocketDetailInquiryRequestDTO requestDTO = new XfaceOrderWebsocketDetailInquiryRequestDTO();
        //组装分页对象
        PageModel pageModel = new PageModel();
        pageModel.setCurrent(page);
        pageModel.setSize(limit);
        requestDTO.setPage(pageModel);
        //查询条件
        requestDTO.setClientId(clientId);
        //调用微服务
        XfaceOrderWebsocketDetailInquiryResponseDTO responseDTO = orderWebsocketMicroService.websocketInquiryPage(requestDTO);
        //重组响应对象
        return ListResponseConverter.listResponseConverter(responseDTO.getTransactionStatus().isSuccess(),
                responseDTO.getTransactionStatus().getErrorCode(),
                responseDTO.getTransactionStatus().getReplyText(),
                responseDTO.getPage().getTotal(),
                responseDTO.getDataList());
    }

    @NeedAuthorize(value = "ws:send")
    @PostMapping("/ws/send/all")
    @ResponseBody
    public XfaceOrderWebsocketSendAllResponseDTO websocketSendAll(XfaceOrderWebSocketSendAllRequestDTO requestDTO){
        XfaceOrderWebsocketSendAllResponseDTO responseDTO = orderWebsocketMicroService.websocketSendAll(requestDTO);
        return responseDTO;
    }
}
