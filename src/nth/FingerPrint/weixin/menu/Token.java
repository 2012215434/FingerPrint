package nth.FingerPrint.weixin.menu;

import com.google.gson.Gson;

/**
 * Created by lmy on 2014/11/27.
 */
public class Token {
    public final static long REQUEST_TIME = 180;//ÏìÓ¦Ê±¼ä,s
    private String token = "";
    private long expiresIn = 0;

    private static class RequestContainer {
        private static Token instance = new Token();
    }

    public static Token getInstance() {
        return RequestContainer.instance;
    }

    private Token() {
    }

    public void updata() {
        String url = WechatApi.TAKEN + "?grant_type=client_credential&appid=" + WechatApi.AppID + "&secret=" + WechatApi.AppSecret;
        System.out.println("url:" + url);
        String result = MyHttpClient.get(url);
        TokenData tokenData = new Gson().fromJson(result, TokenData.class);
        token = tokenData.getAccess_token();
        expiresIn = (tokenData.getExpires_in() - REQUEST_TIME) * 1000 + System.currentTimeMillis();
    }

    public String getToken() {
        return token;
    }

    public boolean valid() {
        if (expiresIn > System.currentTimeMillis()) return true;
        return false;
    }

    public long getExpiresIn() {
        return expiresIn;
    }
}
