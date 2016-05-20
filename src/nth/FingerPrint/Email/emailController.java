package nth.FingerPrint.Email;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.annotation.Resource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nth.FingerPrint.Email05.MailSenderInfo;
import nth.FingerPrint.Email05.SimpleMailSender;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


public class emailController {

	public static void getEmail(String userName) throws Exception {

		String toMail = userName;
		/*String userName1 = "fingerprinthost@outlook.com";
		String password = "fp992983141";*/
		String userName1 = "fingerprinthost@163.com";
		String password = "fingerprint163";
		/*String userName = "fingerprintcompany@163.com";
		String password = "fingerprint";*/
		/*String userName = "fingerprinthost@sina.cn";
		String password = "fingerprintsina";*/

		DESPlus des = new DESPlus("cao+zhang+li");// 自定义密钥
		String registerID = des.encrypt(toMail);
		String url = "http://59.68.29.68:8080/FingerPrint/validate.do?UID="
				+ registerID;// 待会用户点在邮箱中点击这个链接回到你的网站。
		
		/*test  test  test  test  test test*/
		/*Properties props = new Properties();
		props.setProperty("mail.smtp.host", "smtp.163.com");*/
		//props.setProperty("mail.smtp.host", "smtp.outlook.com");
		/*	props.setProperty("mail.smtp.auth", "true");
		
		Session session=Session.getInstance(props, new javax.mail.Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                 return new javax.mail.PasswordAuthentication("fingerprinthost@163.com", "fingerprint163");
            }
          });
		
		
		Authenticator authenticator = new MyAuthenticator(userName1, password);

		javax.mail.Session session1 = javax.mail.Session.getDefaultInstance(
				props, authenticator);
		session1.setDebug(true);*/

	/*	try {
			Address from = new InternetAddress(userName1);
			Address to = new InternetAddress(toMail);

			MimeMessage msg = new MimeMessage(session1);
			msg.setFrom(from);
			msg.setSubject("指印用户注册验证！！！");
			msg.setSentDate(new Date());
			msg.setContent("<a href='" + url + "'>点击" + url + "完成注册</a>",
					"text/html;charset=utf-8");
			msg.setRecipient(RecipientType.TO, to);

			Transport.send(msg);
		} catch (MessagingException e) {
			e.printStackTrace();
		}*/
		
		/*test test test test test*/
	     //这个类主要是设置邮件  
	     MailSenderInfo mailInfo = new MailSenderInfo();   
	     mailInfo.setMailServerHost("smtp.163.com");   
	     mailInfo.setMailServerPort("25");   
	     mailInfo.setValidate(true);   
	     mailInfo.setUserName(userName1);   //自己的邮箱
	     mailInfo.setPassword(password);//自己的邮箱密码，用于验证      
	     
	     mailInfo.setFromAddress(userName1);  ///自己的邮箱
	     mailInfo.setToAddress(toMail);   ///对方的邮箱
	     mailInfo.setSubject("指印账号激活链接");   
	     mailInfo.setContent("<a href='" + url + "'>点击" + url + "完成注册</a>");
	     
	     //这个类主要来发送邮件  
	     SimpleMailSender sms = new SimpleMailSender();  
	     //sms.sendTextMail(mailInfo);//发送文体格式   
	     sms.sendHtmlMail(mailInfo);//发送html格式
	
	}
}
