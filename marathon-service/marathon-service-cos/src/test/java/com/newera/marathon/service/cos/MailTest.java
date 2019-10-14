//package com.newera.marathon.service.cos;
//
//import com.newera.marathon.service.cos.service.MailService;
//import lombok.extern.slf4j.Slf4j;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import javax.mail.MessagingException;
//
///**
// * 问题：
// * 1、有时候要先ping smtp.163.com，然后再运行测试方法才能发送成功
// */
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {ServiceCosApplication.class})
////@Transactional//用于数据回滚
//@Slf4j
//public class MailTest {
//    @Autowired
//    private MailService mailService;
//    //附件路径
//    private static final String IMG_PATH = "/Users/microbin/Downloads/a.jpg";
//    // 发送对象
//    private static final String MAIL_TO = "996041341@qq.com";
//
//    @Test
//    public void sendSimpleMail() {
//        mailService.sendSimpleMail(MAIL_TO,"这是一封普通的邮件","这是一封普通的SpringBoot测试邮件");
//    }
//
//    @Test
//    public void sendHtmlMail() throws MessagingException {
//        StringBuilder html = new StringBuilder();
//        html.append("<body>");
//        html.append("<h3>Welcome To My Friend!</h3>");
//        html.append("</body>");
//        mailService.sendHtmlMail(MAIL_TO,"这是一HTML的邮件",html.toString());
//    }
//    @Test
//    public void sendAttachmentsMail() throws MessagingException {
//        mailService.sendAttachmentsMail(MAIL_TO,"这是一封带附件的邮件","邮件中有附件，请注意查收！",IMG_PATH);
//    }
//}
