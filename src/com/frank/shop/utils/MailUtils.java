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
	 * �����ʼ��ķ���
	 * 
	 * @param to
	 *            :������
	 * @param code
	 *            :������
	 */
	public static void sendMail(String to, String code) {
		/**
		 * 1.��ȡһ��Session���� 2.����һ�������ʼ������Message 3.�����ʼ�Transport
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
		// �����ʼ�����
		Message message = new MimeMessage(session);

		try {
			// ���÷�����
			message.setFrom(new InternetAddress("Service@shop.com"));
			// �����ռ���
			message.addRecipient(RecipientType.TO, new InternetAddress(to));
			// ���ñ���
			message.setSubject("����Frank�Ĺ����̳��Ĺٷ��ʼ���");
			message.setContent(
					"<h1>�����̳�Frnk�ٷ������ʼ�!������������ɼ������!</h1><h3><a href='http://192.168.100.105:8080/myShop/user_active.action?code="
							+ code
							+ "'>http://192.168.100.105:8080/myShop/user_active.action?code="
							+ code + "</a></h3>", "text/html;charset=UTF-8");
			// �����ʼ�
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
