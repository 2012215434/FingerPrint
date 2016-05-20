package nth.FingerPrint.weixin.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import nth.FingerPrint.Orders.Service.OrderService;
import nth.FingerPrint.Users.Service.UserService;
import nth.FingerPrint.Users.po.User;
import nth.FingerPrint.weixin.menu.Menu;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ifp.wechat.constant.ConstantWeChat;
import com.ifp.wechat.entity.message.resp.Article;
import com.ifp.wechat.entity.message.resp.NewsMessage;
import com.ifp.wechat.entity.message.resp.TextMessage;
import com.ifp.wechat.service.MessageService;
import com.ifp.wechat.util.MessageUtil;
/**
 * 处理微信核心业务的service
 * @author caspar.chen
 *
 */
@Service
public class WechatService {

	//public static Logger log = Logger.getLogger(WechatService.class);

	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return String
	 */
	public String processWebchatRequest(HttpServletRequest request) {
		String respMessage = null;
		try {
			// xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			System.out.println("requestMap ===" + requestMap);
			// 消息类型
			String msgType = requestMap.get("MsgType");

			TextMessage textMessage = (TextMessage) MessageService
					.bulidBaseMessage(requestMap,
							ConstantWeChat.RESP_MESSAGE_TYPE_TEXT);
			NewsMessage newsMessage = (NewsMessage) MessageService
					.bulidBaseMessage(requestMap,
							ConstantWeChat.RESP_MESSAGE_TYPE_NEWS);

			String respContent = "";
			/*******************一指打印事件定义********************/
			
			
			/*// 文本消息
			if (msgType.equals(ConstantWeChat.REQ_MESSAGE_TYPE_TEXT)) {
				// 接收用户发送的文本消息内容
				String content = requestMap.get("Content");
				// 创建图文消息
				List<Article> articleList = new ArrayList<Article>();
				// 单图文消息
				if ("1".equals(content)) {
					Article article = new Article();
					article.setTitle("我是一条单图文消息");
					article.setDescription("我是描述信息，哈哈哈哈哈哈哈。。。");
					article.setPicUrl("http://yzdy.ngrok.com/web/index.html");
					article.setUrl("http://yzdy.ngrok.com/web/index.html");
					articleList.add(article);
					// 设置图文消息个数
					newsMessage.setArticleCount(articleList.size());
					// 设置图文消息包含的图文集合
					newsMessage.setArticles(articleList);
					// 将图文消息对象转换成xml字符串
					respMessage = MessageService.bulidSendMessage(newsMessage,
							ConstantWeChat.RESP_MESSAGE_TYPE_NEWS);
				}
				// 多图文消息
				else if ("3".equals(content)) {

					Article article1 = new Article();
					article1.setTitle("我是一条多图文消息");
					article1.setDescription("");
					article1.setPicUrl("http://yzdy.ngrok.com/web/index.html");
					article1.setUrl("http://yzdy.ngrok.com/web/index.html");

					Article article2 = new Article();
					article2.setTitle("我是一条多图文消息 ");
					article2.setDescription("");
					article2.setPicUrl("http://yzdy.ngrok.com/web/index.html");
					article2.setUrl("http://yzdy.ngrok.com/web/index.html");

					Article article3 = new Article();
					article3.setTitle("我是一条多图文消息");
					article3.setDescription("");
					article3.setPicUrl("http://yzdy.ngrok.com/web/index.html");
					article3.setUrl("http://yzdy.ngrok.com/web/index.html");

					articleList.add(article1);
					articleList.add(article2);
					articleList.add(article3);
					newsMessage.setArticleCount(articleList.size());

					newsMessage.setArticles(articleList);
					respMessage = MessageService.bulidSendMessage(newsMessage,
							ConstantWeChat.RESP_MESSAGE_TYPE_NEWS);
				}
			} else if (msgType.equals(ConstantWeChat.REQ_MESSAGE_TYPE_VOICE)) {
				textMessage.setContent("您说的是：" + requestMap.get("Recognition"));
				respMessage = MessageService.bulidSendMessage(textMessage,
						ConstantWeChat.RESP_MESSAGE_TYPE_TEXT);
				// 事件处理开始
			} else*/ 
			if (msgType.equals(ConstantWeChat.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");

				if (eventType.equals(ConstantWeChat.EVENT_TYPE_SUBSCRIBE)) {
					// 关注
					respContent = "感谢您关注一指打印,我们将向您提供最权威的网络打印服务！！！\n";
					StringBuffer contentMsg = new StringBuffer();
					contentMsg.append("带给您不一样的打印体验。").append("\n\n");
					respContent = respContent + contentMsg.toString();

				} else if (eventType
						.equals(ConstantWeChat.EVENT_TYPE_UNSUBSCRIBE)) {
					// 取消关注,用户接受不到我们发送的消息了，可以在这里记录用户取消关注的日志信息

				} else if (eventType.equals(ConstantWeChat.EVENT_TYPE_CLICK)) {

					
					// 事件KEY值，与创建自定义菜单时指定的KEY值对应
					String eventKey = requestMap.get("EventKey");

					// 自定义菜单点击事件
					if (eventKey.equals(Menu.KEY_ORDER)) {
						String openID = requestMap.get("FromUserName");
						
						User u=new User();
						u.setAssociationWeChat(openID);
						System.out.println(openID);
						
						UserService userService = new UserService();
						User uu = userService.getUserByOpenID(u);
						System.out.println(uu.getUserID());
						
						OrderService orderService = new OrderService();
						String res = orderService.queryAllOrder(uu).toString();
						//respContent = "我是一条多图文消息,KEY_INFO"+str;
						System.out.println("res   "+res);
						respContent = res;
					} else if (eventKey.equals(Menu.KEY_INFO)) {
						respContent = "我是一条多图文消息,KEY_INFO";
					}
				}
				textMessage.setContent(respContent);
				respMessage = MessageService.bulidSendMessage(textMessage,
						ConstantWeChat.RESP_MESSAGE_TYPE_TEXT);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return respMessage;
	}
}
