package nth.FingerPrint.weixin.menu;

/**
 * Created by lmy on 2014/11/27.
 */
public class TokenData {
    private String access_token = "";
    private long expires_in;

    public TokenData() {

    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public long getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(long expires_in) {
        this.expires_in = expires_in;
    }
}
