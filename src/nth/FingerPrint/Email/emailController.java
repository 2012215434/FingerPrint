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

		DESPlus des = new DESPlus("cao+zhang+li");// �Զ�����Կ
		String registerID = des.encrypt(toMail);
		String url = "http://59.68.29.68:8080/FingerPrint/validate.do?UID="
				+ registerID;// �����û����������е��������ӻص������վ��
		
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
			msg.setSubject("ָӡ�û�ע����֤������");
			msg.setSentDate(new Date());
			msg.setContent("<a href='" + url + "'>���" + url + "���ע��</a>",
					"text/html;charset=utf-8");
			msg.setRecipient(RecipientType.TO, to);

			Transport.send(msg);
		} catch (MessagingException e) {
			e.printStackTrace();
		}*/
		
		/*test test test test test*/
	     //�������Ҫ�������ʼ�  
	     MailSenderInfo mailInfo = new MailSenderInfo();   
	     mailInfo.setMailServerHost("smtp.163.com");   
	     mailInfo.setMailServerPort("25");   
	     mailInfo.setValidate(true);   
	     mailInfo.setUserName(userName1);   //�Լ�������
	     mailInfo.setPassword(password);//�Լ����������룬������֤      
	     
	     mailInfo.setFromAddress(userName1);  ///�Լ�������
	     mailInfo.setToAddress(toMail);   ///�Է�������
	     mailInfo.setSubject("ָӡ�˺ż�������");   
	     mailInfo.setContent("<a href='" + url + "'>���" + url + "���ע��</a>");
	     
	     //�������Ҫ�������ʼ�  
	     SimpleMailSender sms = new SimpleMailSender();  
	     //sms.sendTextMail(mailInfo);//���������ʽ   
	     sms.sendHtmlMail(mailInfo);//����html��ʽ
	
	}
}
