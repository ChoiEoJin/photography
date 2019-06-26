package com.collabo.photography.common.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {
	public SendMail() {
		// TODO Auto-generated constructor stub
	}
	
	public static void sendAuthMail(String recipient,String subject ,String body) throws AddressException, MessagingException {
		String host = "smtp.gmail.com";

		final String id = "johannesedelstein";

		final String pwd = "dark429812!";

		int port = 465;

		Properties props = System.getProperties();

		props.put("mail.smtp.host", host); 

		props.put("mail.smtp.port", port); 

		props.put("mail.smtp.auth", "true"); 

		props.put("mail.smtp.ssl.enable", "true"); 

		props.put("mail.smtp.ssl.trust", host);

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() { 

			String un = id; 

			String pw = pwd; 


			protected javax.mail.PasswordAuthentication getPasswordAuthentication() { 

				return new javax.mail.PasswordAuthentication(un, pw); 

			} 

		}); 

		session.setDebug(true); //for debug



		Message mimeMessage = new MimeMessage(session); //MimeMessage 생성 

		mimeMessage.setFrom(new InternetAddress(recipient)); //발신자 셋팅 , 보내는 사람의 이메일주소를 한번 더 입력합니다. 이때는 이메일 풀 주소를 다 작성해주세요. 

		mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient)); //수신자셋팅 //.TO 외에 .CC(참조) .BCC(숨은참조) 도 있음 

		mimeMessage.setSubject(subject); //제목셋팅 

		mimeMessage.setText(body); //내용셋팅 

		Transport.send(mimeMessage); //javax.mail.Transport.send() 이용
	}
}
