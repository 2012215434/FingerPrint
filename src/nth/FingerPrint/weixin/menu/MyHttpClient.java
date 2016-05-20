package nth.FingerPrint.weixin.menu;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by lmy on 2014/11/27.
 */
public class MyHttpClient {

    public static String get(String url){
        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
        HttpGet request = new HttpGet(url);
        String result = "";
        try {
            HttpResponse response = defaultHttpClient.execute(request);
            if (response.getStatusLine().getStatusCode() == 200) {
                result = EntityUtils.toString(response.getEntity());
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    public static String post(String url,String json){
        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost(url);
        String result = "";
        try {
            httppost.setEntity(new StringEntity(json, HTTP.UTF_8));
            HttpResponse response = defaultHttpClient.execute(httppost);
            if (response.getStatusLine().getStatusCode() == 200) {
                result = EntityUtils.toString(response.getEntity());
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
