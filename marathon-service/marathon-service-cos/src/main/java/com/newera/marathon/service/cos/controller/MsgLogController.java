package com.newera.marathon.service.cos.controller;


import com.newera.marathon.dto.system.inquiry.XfaceMsgLogListInquiryRequestDTO;
import com.newera.marathon.dto.system.inquiry.XfaceMsgLogListInquiryResponseDTO;
import com.newera.marathon.dto.system.maintenance.XfaceMsgLogAdditionRequestDTO;
import com.newera.marathon.dto.system.maintenance.XfaceMsgLogAdditionResponseDTO;
import com.newera.marathon.dto.system.maintenance.XfaceMsgLogModifyRequestDTO;
import com.newera.marathon.dto.system.maintenance.XfaceMsgLogModifyResponseDTO;
import com.newera.marathon.service.cos.service.MsgLogService;
import com.spaking.boot.starter.core.annotation.BusinessLogger;
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
 * @since 2019-10-15
 */
@RestController
public class MsgLogController {

    @Autowired
    private MsgLogService msgLogService;


    @BusinessLogger(key = "CMS",value = "msgLogListInquiry")
    @PostMapping("/cos/msg/log/list/inquiry")
    public XfaceMsgLogListInquiryResponseDTO msgLogListInquiry(@Valid @RequestBody XfaceMsgLogListInquiryRequestDTO requestDTO){
        XfaceMsgLogListInquiryResponseDTO responseDTO = msgLogService.doMsgLogListInquiry(requestDTO);
        return responseDTO;
    }

    @BusinessLogger(key = "CMS",value = "msgLogAddition")
    @PostMapping("/cos/msg/log/addition")
    public XfaceMsgLogAdditionResponseDTO msgLogAddition(@Valid @RequestBody XfaceMsgLogAdditionRequestDTO requestDTO){
        XfaceMsgLogAdditionResponseDTO responseDTO = msgLogService.doMsgLogAddition(requestDTO);
        return responseDTO;
    }

    @BusinessLogger(key = "CMS",value = "msgLogModify")
    @PostMapping("/cos/msg/log/modify")
    public XfaceMsgLogModifyResponseDTO msgLogModify(@Valid @RequestBody XfaceMsgLogModifyRequestDTO requestDTO){
        XfaceMsgLogModifyResponseDTO responseDTO = msgLogService.doMsgLogModify(requestDTO);
        return responseDTO;
    }
}
