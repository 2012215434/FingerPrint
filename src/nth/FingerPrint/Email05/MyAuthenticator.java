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
     //这个类主要是设置邮件  
    	String url = "http://59.68.29.68:8080/FingerPrint/validate.do?UID=";// 待会用户点在邮箱中点击这个链接回到你的网站。
     MailSenderInfo mailInfo = new MailSenderInfo();   
     mailInfo.setMailServerHost("smtp.163.com");   
     mailInfo.setMailServerPort("25");   
     mailInfo.setValidate(true);   
     mailInfo.setUserName("fingerprinthost@163.com");   //自己的邮箱
     mailInfo.setPassword("fingerprint163");//自己的邮箱密码，用于验证      
     
     mailInfo.setFromAddress("fingerprinthost@163.com");  ///自己的邮箱
     mailInfo.setToAddress("929456610@qq.com");   ///对方的邮箱
     mailInfo.setSubject("java测试!!!");   
     mailInfo.setContent("<a href='" + url + "'>点击" + url + "完成注册</a>");
     
     //这个类主要来发送邮件  
     SimpleMailSender sms = new SimpleMailSender();  
     //sms.sendTextMail(mailInfo);//发送文体格式   
     sms.sendHtmlMail(mailInfo);//发送html格式
       
   }  
}