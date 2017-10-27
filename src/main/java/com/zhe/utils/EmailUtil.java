package com.zhe.utils;

import javax.mail.Session;
import java.net.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * ClassName: EmailUtil
 * Function:
 * Reason: TODO ADD REASON(可选).
 * date: 2017年03月06日 15:02
 *
 * @author caijun.wei
 * @since JDK 1.7
 */
public class EmailUtil  {

    private static Properties props = null;

    public static void setProps( Properties props ) {
        EmailUtil.props =props;
    }

    /**
     * 发送邮件
     * @param title:标题
     * @param content:地址
     * @param addrs:收件人
     */
    public static void sendEmail(String title, String content, String ...addrs) {
        sendEmail(title, content, addrs, null, null);
    }

    /**
     * 发送邮件
     * @param title:标题
     * @param content:内容
     * @param addrs:收件人
     * @param ccs:抄送人
     * @param bccs:密抄送人(秘密抄送，其他抄送人看不见)
     */
    public static void sendEmail(String title,String content, String[] addrs, String[] ccs, String[] bccs) {
        if(props == null) {
            throw new NullPointerException("使用前请先初始化配置");
        }
        try {
            Session mailSession = Session.getInstance(props, new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    String userName = props.getProperty("mail.user");
                    String passWord = props.getProperty("mail.password");
                    return new PasswordAuthentication(userName, passWord);
                }
            });

            MimeMessage message = new MimeMessage(mailSession);
            InternetAddress form = new InternetAddress(props.getProperty("mail.user"));
            message.setFrom(form);

            if(addrs != null) {  //设置收件人
                for(String addr:addrs) {
                    InternetAddress to = new InternetAddress(addr);
                    message.setRecipient(MimeMessage.RecipientType.TO, to);
                }
            }

            if(ccs != null) {  //设置抄送人
                for(String cc:ccs) {
                    InternetAddress to = new InternetAddress(cc);
                    message.setRecipient(MimeMessage.RecipientType.CC, to);
                }
            }

            if(bccs != null) {  //设置密抄送人，其他抄送人不能看见
                for(String cc:bccs) {
                    InternetAddress to = new InternetAddress(cc);
                    message.setRecipient(MimeMessage.RecipientType.BCC, to);
                }
            }

            message.setSubject(title);  //设置标题
            message.setContent(content, "text/html;charset=UTF-8"); //设置内容
            Transport.send(message);//发送邮件
        }catch (Exception e) {
            throw new RuntimeException(String.format("发送邮件失败:%s", e.getMessage()), e);
        }
    }

}
