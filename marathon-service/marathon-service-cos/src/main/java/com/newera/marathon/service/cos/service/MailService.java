package com.newera.marathon.service.cos.service;

import javax.mail.MessagingException;

public interface MailService {
    /**
     * 发送文本邮件
     *
     * @param to
     * @param subject
     * @param content
     * @param cc 抄送
     */
    void sendSimpleMail(String to, String subject, String content, String... cc);

    /**
     * 发送HTML邮件
     *
     * @param to
     * @param subject
     * @param content
     * @throws MessagingException
     */
    void sendHtmlMail(String to, String subject, String content, String... cc) throws MessagingException;

    /**
     * 发送带附件的邮件
     *
     * @param to
     * @param subject
     * @param content
     * @param filePath
     * @throws MessagingException
     */
    void sendAttachmentsMail(String to, String subject, String content, String filePath, String... cc) throws MessagingException;

    /**
     * 发送正文中有静态资源的邮件
     *
     * @param to
     * @param subject
     * @param content
     * @param rscPath 对应资源的路径
     * @param rscId 资源的唯一 id
     * @throws MessagingException
     */
    void sendResourceMail(String to, String subject, String content, String rscPath, String rscId, String... cc) throws MessagingException;
}
