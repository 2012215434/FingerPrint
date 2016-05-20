package nth.FingerPrint.weixin.controller;

import java.io.IOException;  
import java.io.OutputStream;
import java.io.PrintWriter;  
import java.io.UnsupportedEncodingException;  
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
  
import javax.annotation.Resource;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
  
import nth.FingerPrint.weixin.Service.WechatService;
import nth.FingerPrint.weixin.biz.core.impl.CoreServiceImpl;
import nth.FingerPrint.weixin.util.SignUtil;

import org.springframework.stereotype.Controller;  
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller 

public class WeixinController {  
  
   @Resource
   //private CoreServiceImpl coreServiceImpl = new CoreServiceImpl();
   private WechatService wechatService = new WechatService();  
     /**
     * ����ÿ�ζ�΢�ŵķ���
     *
     */
    @RequestMapping(value = "/weixin.do",method = RequestMethod.GET ) 
    @ResponseBody
    public void get(HttpServletRequest request, HttpServletResponse response) {  
        // ΢�ż���ǩ����signature����˿�������д��token�����������е�timestamp������nonce������  
        String signature = request.getParameter("signature");  
        // ʱ���  
        String timestamp = request.getParameter("timestamp");  
        // �����  
        String nonce = request.getParameter("nonce");  
        // ����ַ���  
        String echostr = request.getParameter("echostr"); 
        
        System.out.println("signature "+signature);
        System.out.println("timestamp "+timestamp);
        System.out.println("nonce "+nonce);
        System.out.println("echostr "+echostr);
        PrintWriter out = null;  
        try {  
            out = response.getWriter();  
         /*   // ͨ������signature���������У�飬��У��ɹ���ԭ������echostr���������ʧ��  
            if (SignUtil.checkSignature(signature, timestamp, nonce)) {  
            	System.out.println("echostr  "+ echostr);
            	out.print(echostr);  
            } */ 
            if (echostr != null && echostr.length() > 1){
            	System.out.println("echostr "+echostr);
            	out.print(echostr);
            }
            
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            out.close();  
            out = null;  
        } 
        
        // ���ú���ҵ���������Ϣ��������Ϣ  
/*        String respMessage = wechatService.processWebchatRequest(request);  
  
        // ��Ӧ��Ϣ  
        PrintWriter out1 = null;
        try {  
            out1 = response.getWriter();  
            out1.print(respMessage);  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            out1.close();  
            out1 = null;  
        }  */
        
        /*String result = null;
        if (echostr != null && echostr.length() > 1) {
            result = echostr;
        }
        System.out.println(result);
        try {
            OutputStream os = response.getOutputStream();
            os.write(result.getBytes("UTF-8"));
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }  
  
  @RequestMapping(value = "/weixin.do",method = RequestMethod.POST) 
  @ResponseBody
    public void post(HttpServletRequest request, HttpServletResponse response) {  
        try {  
            request.setCharacterEncoding("UTF-8");  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }  
        response.setCharacterEncoding("UTF-8");  
  
        // ���ú���ҵ���������Ϣ��������Ϣ  
        System.out.println("���óɹ�");
        String respMessage = wechatService.processWebchatRequest(request);  
  
        // ��Ӧ��Ϣ  
        PrintWriter out = null;  
        try {  
            out = response.getWriter();  
            out.print(respMessage);  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            out.close();  
            out = null;  
        }  
    } 
  
} 