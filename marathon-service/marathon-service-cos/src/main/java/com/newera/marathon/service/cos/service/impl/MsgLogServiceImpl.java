package com.newera.marathon.service.cos.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.newera.marathon.dto.cos.inquiry.XfaceCosMsgLogListInquiryRequestDTO;
import com.newera.marathon.dto.cos.inquiry.XfaceCosMsgLogListInquiryResponseDTO;
import com.newera.marathon.dto.cos.inquiry.XfaceCosMsgLogListInquiryResponseSubDTO;
import com.newera.marathon.dto.cos.maintenance.XfaceCosMsgLogAdditionRequestDTO;
import com.newera.marathon.dto.cos.maintenance.XfaceCosMsgLogAdditionResponseDTO;
import com.newera.marathon.dto.cos.maintenance.XfaceCosMsgLogModifyRequestDTO;
import com.newera.marathon.dto.cos.maintenance.XfaceCosMsgLogModifyResponseDTO;
import com.newera.marathon.service.cos.entity.MsgLog;
import com.newera.marathon.service.cos.mapper.MsgLogMapper;
import com.newera.marathon.service.cos.service.MsgLogService;
import com.spaking.boot.starter.core.model.TransactionStatus;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * MQ消息日志，用来确保消息100%投递成功 服务实现类
 * </p>
 *
 * @author MicroBin
 * @since 2019-10-15
 */
@Service
@Slf4j
public class MsgLogServiceImpl extends ServiceImpl<MsgLogMapper, MsgLog> implements MsgLogService {

    @Autowired
    private MsgLogMapper msgLogMapper;

    @Override
    public XfaceCosMsgLogListInquiryResponseDTO doMsgLogListInquiry(XfaceCosMsgLogListInquiryRequestDTO requestDTO) {
        log.info("doMsgLogListInquiry start");
        XfaceCosMsgLogListInquiryResponseDTO responseDTO = new XfaceCosMsgLogListInquiryResponseDTO();
        TransactionStatus transactionStatus = new TransactionStatus();
        //查询条件
        QueryWrapper<MsgLog> wrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(requestDTO.getMsgId())){
            wrapper.eq("msg_id",requestDTO.getMsgId());
        }
        if(null != requestDTO.getStatus()){
            wrapper.eq("status",requestDTO.getStatus());
        }
        //查询
        List<MsgLog> list = msgLogMapper.selectList(wrapper);
        //复制对象
        List<XfaceCosMsgLogListInquiryResponseSubDTO> responseSubDTOS = new ArrayList<>();
        list.forEach(w->{
            XfaceCosMsgLogListInquiryResponseSubDTO responseSubDTO = new XfaceCosMsgLogListInquiryResponseSubDTO();
            BeanUtils.copyProperties(w,responseSubDTO);
            responseSubDTOS.add(responseSubDTO);
        });
        responseDTO.setDataList(responseSubDTOS);
        responseDTO.setTransactionStatus(transactionStatus);
        log.info("doMsgLogListInquiry end");
        return responseDTO;
    }

    @Override
    public XfaceCosMsgLogAdditionResponseDTO doMsgLogAddition(XfaceCosMsgLogAdditionRequestDTO requestDTO) {
        log.info("doMsgLogAddition start");
        XfaceCosMsgLogAdditionResponseDTO responseDTO = new XfaceCosMsgLogAdditionResponseDTO();
        TransactionStatus transactionStatus = new TransactionStatus();
        //复制对象
        MsgLog msgLog = new MsgLog();
        BeanUtils.copyProperties(requestDTO,msgLog);
        //添加
        save(msgLog);
        responseDTO.setTransactionStatus(transactionStatus);
        log.info("doMsgLogAddition end");
        return responseDTO;
    }

    @Override
    public XfaceCosMsgLogModifyResponseDTO doMsgLogModify(XfaceCosMsgLogModifyRequestDTO requestDTO) {
        log.info("doMsgLogModify start");
        XfaceCosMsgLogModifyResponseDTO responseDTO = new XfaceCosMsgLogModifyResponseDTO();
        TransactionStatus transactionStatus = new TransactionStatus();
        //更新条件
        UpdateWrapper<MsgLog> wrapper = new UpdateWrapper<>();
        wrapper.eq("msg_id",requestDTO.getMsgId());
        //复制对象
        MsgLog msgLog = new MsgLog();
        BeanUtils.copyProperties(requestDTO, msgLog);
        //更新
        update(msgLog,wrapper);
        responseDTO.setTransactionStatus(transactionStatus);
        log.info("doMsgLogModify end");
        return responseDTO;
    }
}
