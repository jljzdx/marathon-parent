package com.newera.marathon.service.cms.mq.pojo;

import lombok.Data;

@Data
public class MailSend {

    /**
     * 消息ID
     */
    private String msgId;

    /**
     * 收件人
     */
    private String toMail;

    /**
     * 主题
     */
    private String subject;

    /**
     * 内容
     */
    private String content;

    /**
     * 抄送人
     */
    private String cc;

    /**
     * 附件路径
     */
    private String filePath;

    /**
     * 邮件类型（1/2/3：普通邮件/HTML邮件/附件邮件）
     */
    private Integer type;
}
