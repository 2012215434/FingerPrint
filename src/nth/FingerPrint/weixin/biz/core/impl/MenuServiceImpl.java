package nth.FingerPrint.weixin.biz.core.impl;

import net.sf.json.JSONObject;  

import org.springframework.stereotype.Service;  

import com.ifp.wechat.util.WeixinUtil;;
@Service
public class MenuServiceImpl {  
  
    //public static Logger log = Logger.getLogger(MenuServiceImpl.class);  
  
    // �˵�������POST�� ��100����/�죩  
    public static String MENU_CREATE = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";  
  
    public String createMenu(String jsonMenu) {  
        String resultStr = "";  
        // ���ýӿڻ�ȡtoken  
        String token = WeixinUtil.getToken();  
        if (token != null) {  
            // ���ýӿڴ����˵�  
            int result = createMenu(jsonMenu, token);  
            // �жϲ˵��������  
            if (0 == result) {  
                resultStr = "�˵������ɹ�";  
                //log.info(resultStr);  
            } else {  
                resultStr = "�˵�����ʧ�ܣ������룺" + result;  
                //log.error(resultStr);  
            }  
        }  
  
        return resultStr;  
    }  
  
  
    /** 
     * �����˵� 
     *  
     * @param jsonMenu 
     *            �˵���json��ʽ 
     * @param accessToken 
     *            ��Ч��access_token 
     * @return 0��ʾ�ɹ�������ֵ��ʾʧ�� 
     */  
    public static int createMenu(String jsonMenu, String accessToken) {  
  
        int result = 0;  
        // ƴװ�����˵���url  
        String url = MENU_CREATE.replace("ACCESS_TOKEN", accessToken);  
        // ���ýӿڴ����˵�  
        JSONObject jsonObject = WeixinUtil.httpsRequest(url, "POST", jsonMenu);  
  
        if (null != jsonObject) {  
            if (0 != jsonObject.getInt("errcode")) {  
                result = jsonObject.getInt("errcode");  
                //log.error("�����˵�ʧ�� errcode:" + jsonObject.getInt("errcode")  
                       // + "��errmsg:" + jsonObject.getString("errmsg"));  
            }  
        }  
  
        return result;  
    }  
  
    public static void main(String[] args) {  
        // ����һ�����ϲ˵���json��ʽ����\����ת���  
        String jsonMenu = "{\"button\":[{\"name\":\"��������\",\"sub_button\":[{\"key\":\"11\",\"name\":\"����Ԥ��\",\"type\":\"click\"},{\"key\":\"12\",\"name\":\"������ѯ\",\"type\":\"click\"}]},{\"name\":\"���Ǵ�\",\"sub_button\":[{\"key\":\"21\",\"name\":\"�ö���Ŷ\",\"type\":\"click\"},{\"key\":\"22\",\"name\":\"����ʶ��\",\"type\":\"click\"}]},{\"name\":\"��������\",\"sub_button\":[{\"key\":\"33\",\"name\":\"��ĬЦ��\",\"type\":\"click\"},{\"name\":\"View���͵�\",\"type\":\"view\",\"url\":\"http://m.baidu.com\"}]}]}";  
        MenuServiceImpl impl = new MenuServiceImpl();  
        impl.createMenu(jsonMenu);  
    }  
  
}  