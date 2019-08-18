package com.newera.marathon.web.zy.system.controller;

import com.newera.marathon.common.web.ListResponseConverter;
import com.newera.marathon.dto.system.inquiry.XfaceSysResourceLoopInquiryRequestDTO;
import com.newera.marathon.dto.system.inquiry.XfaceSysResourceLoopInquiryResponseDTO;
import com.newera.marathon.microface.system.SysResourceMicroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class ResourceController {

    @Autowired
    private SysResourceMicroService sysResourceMicroService;
    @GetMapping("/resource/list/html")
    public String resourceListHtml(){
        return "resource/resourceList";
    }

    @PostMapping("/sys/resource/inquiry/page")
    @ResponseBody
    public Map sysResourceInquiryPage(Integer SystemId, Integer parentId){
        XfaceSysResourceLoopInquiryRequestDTO requestDTO = new XfaceSysResourceLoopInquiryRequestDTO();
        //查询条件
        requestDTO.setParentId(parentId);
        //调用微服务
        XfaceSysResourceLoopInquiryResponseDTO responseDTO = sysResourceMicroService.sysResourceInquiryLoop(requestDTO);
        //重组响应对象
        return ListResponseConverter.listResponseConverter(responseDTO.getTransactionStatus().isSuccess(),
                responseDTO.getTransactionStatus().getErrorCode(),
                responseDTO.getTransactionStatus().getReplyText(),
                responseDTO.getDataList());
    }
}
