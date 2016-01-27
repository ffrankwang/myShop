package com.frank.shop.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtils {
	/**
	 * 发送邮件的方法
	 * 
	 * @param to
	 *            :发件人
	 * @param code
	 *            :激活码
	 */
	public static void sendMail(String to, String code) {
		/**
		 * 1.获取一个Session对象 2.创建一个代表邮件对象的Message 3.发送邮件Transport
		 */
		Properties props = new Properties();
		props.setProperty("mail.host", "localhost");
		Session session = Session.getInstance(props, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication("Service@shop.com", "admin");
			}

		});
		// 创建邮件对象
		Message message = new MimeMessage(session);

		try {
			// 设置发件人
			message.setFrom(new InternetAddress("Service@shop.com"));
			// 设置收件人
			message.addRecipient(RecipientType.TO, new InternetAddress(to));
			// 设置标题
			message.setSubject("来自Frank的购物商场的官方邮件。");
			message.setContent(
					"<h1>购物商城Frnk官方激活邮件!点下面链接完成激活操作!</h1><h3><a href='http://192.168.100.105:8080/myShop/user_active.action?code="
							+ code
							+ "'>http://192.168.100.105:8080/myShop/user_active.action?code="
							+ code + "</a></h3>", "text/html;charset=UTF-8");
			// 发送邮件
			Transport.send(message);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
