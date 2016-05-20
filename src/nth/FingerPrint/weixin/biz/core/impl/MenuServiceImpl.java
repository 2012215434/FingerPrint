package nth.FingerPrint.weixin.biz.core.impl;

import net.sf.json.JSONObject;  

import org.springframework.stereotype.Service;  

import com.ifp.wechat.util.WeixinUtil;;
@Service
public class MenuServiceImpl {  
  
    //public static Logger log = Logger.getLogger(MenuServiceImpl.class);  
  
    // 菜单创建（POST） 限100（次/天）  
    public static String MENU_CREATE = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";  
  
    public String createMenu(String jsonMenu) {  
        String resultStr = "";  
        // 调用接口获取token  
        String token = WeixinUtil.getToken();  
        if (token != null) {  
            // 调用接口创建菜单  
            int result = createMenu(jsonMenu, token);  
            // 判断菜单创建结果  
            if (0 == result) {  
                resultStr = "菜单创建成功";  
                //log.info(resultStr);  
            } else {  
                resultStr = "菜单创建失败，错误码：" + result;  
                //log.error(resultStr);  
            }  
        }  
  
        return resultStr;  
    }  
  
  
    /** 
     * 创建菜单 
     *  
     * @param jsonMenu 
     *            菜单的json格式 
     * @param accessToken 
     *            有效的access_token 
     * @return 0表示成功，其他值表示失败 
     */  
    public static int createMenu(String jsonMenu, String accessToken) {  
  
        int result = 0;  
        // 拼装创建菜单的url  
        String url = MENU_CREATE.replace("ACCESS_TOKEN", accessToken);  
        // 调用接口创建菜单  
        JSONObject jsonObject = WeixinUtil.httpsRequest(url, "POST", jsonMenu);  
  
        if (null != jsonObject) {  
            if (0 != jsonObject.getInt("errcode")) {  
                result = jsonObject.getInt("errcode");  
                //log.error("创建菜单失败 errcode:" + jsonObject.getInt("errcode")  
                       // + "，errmsg:" + jsonObject.getString("errmsg"));  
            }  
        }  
  
        return result;  
    }  
  
    public static void main(String[] args) {  
        // 这是一个符合菜单的json格式，“\”是转义符  
        String jsonMenu = "{\"button\":[{\"name\":\"生活助手\",\"sub_button\":[{\"key\":\"11\",\"name\":\"天气预报\",\"type\":\"click\"},{\"key\":\"12\",\"name\":\"公交查询\",\"type\":\"click\"}]},{\"name\":\"音智达\",\"sub_button\":[{\"key\":\"21\",\"name\":\"好东西哦\",\"type\":\"click\"},{\"key\":\"22\",\"name\":\"人脸识别\",\"type\":\"click\"}]},{\"name\":\"更多体验\",\"sub_button\":[{\"key\":\"33\",\"name\":\"幽默笑话\",\"type\":\"click\"},{\"name\":\"View类型的\",\"type\":\"view\",\"url\":\"http://m.baidu.com\"}]}]}";  
        MenuServiceImpl impl = new MenuServiceImpl();  
        impl.createMenu(jsonMenu);  
    }  
  
}  