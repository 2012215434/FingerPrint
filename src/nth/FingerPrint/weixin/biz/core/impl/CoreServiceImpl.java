package nth.FingerPrint.weixin.biz.core.impl;

import java.util.Date;  
import java.util.Map;  
  
import javax.servlet.http.HttpServletRequest;  
  
import org.springframework.stereotype.Service;  

import com.ifp.wechat.constant.ConstantWeChat;
import com.ifp.wechat.entity.message.resp.TextMessage;
import com.ifp.wechat.util.MessageUtil;

@Service 
public class CoreServiceImpl{  
/*  
    public static Logger log = Logger.getLogger(CoreServiceImpl.class);  */
      
      
    public String processRequest(HttpServletRequest request) {  
        String respMessage = null;  
        try {  
            // xml�������  
            Map<String, String> requestMap = MessageUtil.parseXml(request);  
  
            // ���ͷ��ʺţ�open_id��  
            String fromUserName = requestMap.get("FromUserName");  
            // �����ʺ�  
            String toUserName = requestMap.get("ToUserName");  
            // ��Ϣ����  
            String msgType = requestMap.get("MsgType");  
  
            TextMessage textMessage = new TextMessage();  
            textMessage.setToUserName(fromUserName);  
            textMessage.setFromUserName(toUserName);  
            textMessage.setCreateTime(new Date().getTime());  
            textMessage.setMsgType(ConstantWeChat.RESP_MESSAGE_TYPE_TEXT);  
            textMessage.setFuncFlag(0);  
            // �ı���Ϣ  
            if (msgType.equals(ConstantWeChat.REQ_MESSAGE_TYPE_TEXT)) {  
                // �����û����͵��ı���Ϣ����  
                String content = requestMap.get("Content");  
  
                if ("1".equals(content)) {  
                    textMessage.setContent("1�Ǻܺõ�");  
                    // ���ı���Ϣ����ת����xml�ַ���  
                    respMessage = MessageUtil.textMessageToXml(textMessage);  
                }else if ("2".equals(content)) {  
                    textMessage.setContent("�Ҳ���2��");  
                    // ���ı���Ϣ����ת����xml�ַ���  
                    respMessage = MessageUtil.textMessageToXml(textMessage);  
                }  
            }   
              
              
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return respMessage;  
    }  
  
  
}  