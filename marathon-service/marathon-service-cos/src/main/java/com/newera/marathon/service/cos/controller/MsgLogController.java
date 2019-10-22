package com.newera.marathon.service.cos.controller;


import com.newera.marathon.dto.cos.inquiry.XfaceCosMsgLogListInquiryRequestDTO;
import com.newera.marathon.dto.cos.inquiry.XfaceCosMsgLogListInquiryResponseDTO;
import com.newera.marathon.dto.cos.maintenance.XfaceCosMsgLogAdditionRequestDTO;
import com.newera.marathon.dto.cos.maintenance.XfaceCosMsgLogAdditionResponseDTO;
import com.newera.marathon.dto.cos.maintenance.XfaceCosMsgLogModifyRequestDTO;
import com.newera.marathon.dto.cos.maintenance.XfaceCosMsgLogModifyResponseDTO;
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
    public XfaceCosMsgLogListInquiryResponseDTO msgLogListInquiry(@Valid @RequestBody XfaceCosMsgLogListInquiryRequestDTO requestDTO){
        XfaceCosMsgLogListInquiryResponseDTO responseDTO = msgLogService.doMsgLogListInquiry(requestDTO);
        return responseDTO;
    }

    @BusinessLogger(key = "CMS",value = "msgLogAddition")
    @PostMapping("/cos/msg/log/addition")
    public XfaceCosMsgLogAdditionResponseDTO msgLogAddition(@Valid @RequestBody XfaceCosMsgLogAdditionRequestDTO requestDTO){
        XfaceCosMsgLogAdditionResponseDTO responseDTO = msgLogService.doMsgLogAddition(requestDTO);
        return responseDTO;
    }

    @BusinessLogger(key = "CMS",value = "msgLogModify")
    @PostMapping("/cos/msg/log/modify")
    public XfaceCosMsgLogModifyResponseDTO msgLogModify(@Valid @RequestBody XfaceCosMsgLogModifyRequestDTO requestDTO){
        XfaceCosMsgLogModifyResponseDTO responseDTO = msgLogService.doMsgLogModify(requestDTO);
        return responseDTO;
    }
}
