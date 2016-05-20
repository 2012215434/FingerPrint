package nth.FingerPrint.weixin.menu;

import com.google.gson.Gson;

/**
 * Created by lmy on 2014/11/30.
 */
public class Menu {
	public final static String TYPE_CLICK = "click";// 点击类型
	public final static String TYPE_VIEW = "view";// 跳转url
	public final static String TYPE_SCAN = "scancode_push";// 扫描二维码类型

	public final static String KEY_PRINT = "key_print";// 我要打印事件
	public final static String KEY_BIND = "key_bind";// 绑定账号事件
	public final static String KEY_INFO = "key_info";// 个人信息事件
	public final static String KEY_ORDER = "key_order";// 我要订单事件
	public final static String KEY_SCAN = "key_scan";// 扫一扫事件

	public final static String URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxd0bc49cb010c5d7d&redirect_uri=http%3a%2f%2fyzdy.ngrok.com%2fweb%2fm_index.html&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect";//扫一扫事件

	Token token = Token.getInstance();

	public boolean createMenu() {
		if (!token.valid()) {
			token.updata();
		}
		String url = WechatApi.MENU_CREATE + "?access_token="
				+ token.getToken();
		System.out.println(initMenu());
		String result = MyHttpClient.post(url, initMenu());
		System.out.println(result);
		MenuMsgEntity menuMsgEntity = new Gson().fromJson(result,
				MenuMsgEntity.class);
		if (menuMsgEntity.getErrcode() == 0)
			return true;
		else
			return false;
	}

	public boolean deleteMenu() {
		if (!token.valid()) {
			token.updata();
		}
		String url = WechatApi.MENU_DELETE + "?access_token="
				+ token.getToken();
		String result = MyHttpClient.get(url);
		MenuMsgEntity menuMsgEntity = new Gson().fromJson(result,
				MenuMsgEntity.class);
		if (menuMsgEntity.getErrcode() == 0)
			return true;
		else
			return false;
	}

	public String getMenu() {
		if (!token.valid()) {
			token.updata();
		}
		String url = WechatApi.MENU_GET + "?access_token=" + token.getToken();
		String result = MyHttpClient.get(url);
		return result;
	}

	public String initMenu() {
		 return "{\"button\":[" +
	                createSubButton("我要打印", TYPE_VIEW, URL) + "," +
	                "{\"name\": \"我的信息\",\"sub_button\":[" + createSubButton("个人信息", TYPE_CLICK, KEY_INFO) +","+ createSubButton("我的订单", TYPE_CLICK, KEY_ORDER) +
	                "]}," +
	                createSubButton("扫一扫", TYPE_SCAN, KEY_SCAN) +
	                "]}";
    }

	private String createButton(String name, String type, String keyOrUrl) {
		String menu = "{\"type\":\"" + type + "\"," + "\"name\":\"" + name
				+ "\",";
		if (type.equals(TYPE_CLICK))
			menu += "\"key\":\"" + keyOrUrl + "\"}";
		else if (type.equals(TYPE_VIEW))
			menu += "\"url\":\"" + keyOrUrl + "\"}";
		return menu;
	}

	private String createSubButton(String name, String type, String keyOrUrl) {
		String menu = "{\"type\":\"" + type + "\"," + "\"name\":\"" + name
				+ "\",";
		if (type.equals(TYPE_CLICK) || type.equals(TYPE_SCAN))
			menu += "\"key\":\"" + keyOrUrl + "\",";
		else if (type.equals(TYPE_VIEW))
			menu += "\"url\":\"" + keyOrUrl + "\",";
		menu += "\"sub_button\":[]}";
		return menu;
	}

	/*private class MenuMsgEntity {
		private int errcode;
		private String errmsg;

		public int getErrcode() {
			return errcode;
		}

		public void setErrcode(int errcode) {
			this.errcode = errcode;
		}

		public String getErrmsg() {
			return errmsg;
		}

		public void setErrmsg(String errmsg) {
			this.errmsg = errmsg;
		}
	}*/
}
