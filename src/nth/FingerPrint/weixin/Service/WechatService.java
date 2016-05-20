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
 * ����΢�ź���ҵ���service
 * @author caspar.chen
 *
 */
@Service
public class WechatService {

	//public static Logger log = Logger.getLogger(WechatService.class);

	/**
	 * ����΢�ŷ���������
	 * 
	 * @param request
	 * @return String
	 */
	public String processWebchatRequest(HttpServletRequest request) {
		String respMessage = null;
		try {
			// xml�������
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			System.out.println("requestMap ===" + requestMap);
			// ��Ϣ����
			String msgType = requestMap.get("MsgType");

			TextMessage textMessage = (TextMessage) MessageService
					.bulidBaseMessage(requestMap,
							ConstantWeChat.RESP_MESSAGE_TYPE_TEXT);
			NewsMessage newsMessage = (NewsMessage) MessageService
					.bulidBaseMessage(requestMap,
							ConstantWeChat.RESP_MESSAGE_TYPE_NEWS);

			String respContent = "";
			/*******************һָ��ӡ�¼�����********************/
			
			
			/*// �ı���Ϣ
			if (msgType.equals(ConstantWeChat.REQ_MESSAGE_TYPE_TEXT)) {
				// �����û����͵��ı���Ϣ����
				String content = requestMap.get("Content");
				// ����ͼ����Ϣ
				List<Article> articleList = new ArrayList<Article>();
				// ��ͼ����Ϣ
				if ("1".equals(content)) {
					Article article = new Article();
					article.setTitle("����һ����ͼ����Ϣ");
					article.setDescription("����������Ϣ����������������������");
					article.setPicUrl("http://yzdy.ngrok.com/web/index.html");
					article.setUrl("http://yzdy.ngrok.com/web/index.html");
					articleList.add(article);
					// ����ͼ����Ϣ����
					newsMessage.setArticleCount(articleList.size());
					// ����ͼ����Ϣ������ͼ�ļ���
					newsMessage.setArticles(articleList);
					// ��ͼ����Ϣ����ת����xml�ַ���
					respMessage = MessageService.bulidSendMessage(newsMessage,
							ConstantWeChat.RESP_MESSAGE_TYPE_NEWS);
				}
				// ��ͼ����Ϣ
				else if ("3".equals(content)) {

					Article article1 = new Article();
					article1.setTitle("����һ����ͼ����Ϣ");
					article1.setDescription("");
					article1.setPicUrl("http://yzdy.ngrok.com/web/index.html");
					article1.setUrl("http://yzdy.ngrok.com/web/index.html");

					Article article2 = new Article();
					article2.setTitle("����һ����ͼ����Ϣ ");
					article2.setDescription("");
					article2.setPicUrl("http://yzdy.ngrok.com/web/index.html");
					article2.setUrl("http://yzdy.ngrok.com/web/index.html");

					Article article3 = new Article();
					article3.setTitle("����һ����ͼ����Ϣ");
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
				textMessage.setContent("��˵���ǣ�" + requestMap.get("Recognition"));
				respMessage = MessageService.bulidSendMessage(textMessage,
						ConstantWeChat.RESP_MESSAGE_TYPE_TEXT);
				// �¼�����ʼ
			} else*/ 
			if (msgType.equals(ConstantWeChat.REQ_MESSAGE_TYPE_EVENT)) {
				// �¼�����
				String eventType = requestMap.get("Event");

				if (eventType.equals(ConstantWeChat.EVENT_TYPE_SUBSCRIBE)) {
					// ��ע
					respContent = "��л����עһָ��ӡ,���ǽ������ṩ��Ȩ���������ӡ���񣡣���\n";
					StringBuffer contentMsg = new StringBuffer();
					contentMsg.append("��������һ���Ĵ�ӡ���顣").append("\n\n");
					respContent = respContent + contentMsg.toString();

				} else if (eventType
						.equals(ConstantWeChat.EVENT_TYPE_UNSUBSCRIBE)) {
					// ȡ����ע,�û����ܲ������Ƿ��͵���Ϣ�ˣ������������¼�û�ȡ����ע����־��Ϣ

				} else if (eventType.equals(ConstantWeChat.EVENT_TYPE_CLICK)) {

					
					// �¼�KEYֵ���봴���Զ���˵�ʱָ����KEYֵ��Ӧ
					String eventKey = requestMap.get("EventKey");

					// �Զ���˵�����¼�
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
						//respContent = "����һ����ͼ����Ϣ,KEY_INFO"+str;
						System.out.println("res   "+res);
						respContent = res;
					} else if (eventKey.equals(Menu.KEY_INFO)) {
						respContent = "����һ����ͼ����Ϣ,KEY_INFO";
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
