package nth.FingerPrint.Email05;


import javax.mail.*;  
    
public class MyAuthenticator extends Authenticator{  
    String userName=null;  
    String password=null;  
       
    public MyAuthenticator(){  
    }  
    public MyAuthenticator(String username, String password) {   
        this.userName = username;   
        this.password = password;   
    }   
    protected PasswordAuthentication getPasswordAuthentication(){  
        return new PasswordAuthentication(userName, password);  
    }  
    
    public static void main(String[] args){  
     //�������Ҫ�������ʼ�  
    	String url = "http://59.68.29.68:8080/FingerPrint/validate.do?UID=";// �����û����������е��������ӻص������վ��
     MailSenderInfo mailInfo = new MailSenderInfo();   
     mailInfo.setMailServerHost("smtp.163.com");   
     mailInfo.setMailServerPort("25");   
     mailInfo.setValidate(true);   
     mailInfo.setUserName("fingerprinthost@163.com");   //�Լ�������
     mailInfo.setPassword("fingerprint163");//�Լ����������룬������֤      
     
     mailInfo.setFromAddress("fingerprinthost@163.com");  ///�Լ�������
     mailInfo.setToAddress("929456610@qq.com");   ///�Է�������
     mailInfo.setSubject("java����!!!");   
     mailInfo.setContent("<a href='" + url + "'>���" + url + "���ע��</a>");
     
     //�������Ҫ�������ʼ�  
     SimpleMailSender sms = new SimpleMailSender();  
     //sms.sendTextMail(mailInfo);//���������ʽ   
     sms.sendHtmlMail(mailInfo);//����html��ʽ
       
   }  
}