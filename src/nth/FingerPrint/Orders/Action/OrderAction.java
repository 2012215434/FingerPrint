package nth.FingerPrint.Orders.Action;

/***********************************************************************
 * Module:  OrderAction.java
 * Author:  Administrator
 * Purpose: Defines the Class OrderAction
 ***********************************************************************/
import nth.FingerPrint.common.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nth.FingerPrint.Files.common.util2Get;
import nth.FingerPrint.Orders.Service.OrderDetailsService;
import nth.FingerPrint.Orders.Service.OrderService;
import nth.FingerPrint.Orders.po.Order;
import nth.FingerPrint.Orders.po.OrderDetails;
import nth.FingerPrint.Users.po.User;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Controller
public class OrderAction {
	@Resource
	OrderService orderService = new OrderService();
	@Resource
	OrderDetailsService orderDetailsService = new OrderDetailsService();

	public OrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public OrderDetailsService getOrderDetailsService() {
		return orderDetailsService;
	}

	public void setOrderDetailsService(OrderDetailsService orderDetailsService) {
		this.orderDetailsService = orderDetailsService;
	}

	@RequestMapping(value = "/generateOrder.do", method = RequestMethod.POST)
	@ResponseBody
	public String generateOrder(HttpServletRequest request) throws Exception {

		String userID = request.getParameter("userID");
		String shoppingCartID = request.getParameter("shoppingCartID");
		String appKey = request.getParameter("appkey");
		String page = request.getParameter("pages");
		
		if (appKey.equals("FingerPrint")) {
			VerifyingCode v = new VerifyingCode();

			Order order = new Order();
			order.setUserID(Integer.valueOf(userID));
			
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date  date =  df.parse(df.format(new Date()));
			Timestamp t = new Timestamp(date.getTime()); 
			System.out.println(t);
			order.setSubmitTime(t);
			System.out.println(Integer.parseInt(page) * 0.1);
			order.setOrderTotalAmount(Integer.parseInt(page) * 0.1);
			order.setOrderID(util2Get.getUUID());
			order.setVerificationCode(v.generateVerifyingCode());
			order.setIsPay(0);
			order.setIsValidity(0);
			String code = orderService.save(order);
			
			/*多个购物车上传文件的时候，更改代码*/
			OrderDetails o = new OrderDetails();
			o.setOrderID(order.getOrderID());
			o.setShoppingCartID(shoppingCartID);
			System.out.println(order.getOrderID());
			System.out.println(shoppingCartID);
			orderDetailsService.save(o);
			

			System.out.println("okkkkkk");
			return "{\"state\":true," + "\"code\":\"" + code + "\"}";
		}
		return "{\"state\":false}";
	}

	/**
	 * @param appKey
	 * @param UserID
	 * @pdOid adef5014-784c-4add-b54a-cd24c5c16dd6
	 */
	@RequestMapping("/queryAllOrder.do")
	@ResponseBody
	public String queryAllOrder(@RequestParam("appkey")String appKey,
			@RequestParam("userID")String userID,@RequestParam("page") String page) {
		
		if(appKey.equals(ApplicationKey.appKey)){
			User u=new User();
			u.setUserID(Integer.valueOf(userID));
			PageBean p=orderService.showOrderList(Integer.valueOf(page), u);
			String RJson=new Gson().toJson(p);
			System.out.println(RJson);
			return RJson;
		}
		return null;
	}

	/**
	 * @param appKey
	 * @param UserID
	 * @param OrderID
	 * @pdOid 7d9061ee-5e58-4a5f-9f4a-6eb922ff8575
	 */
	public String deleteOrder(String appKey, String UserID, String OrderID) {
		// TODO: implement
		return null;
	}

	/**
	 * @param appKey
	 * @param userID
	 * @pdOid a98b4803-bfea-460e-a574-c1154476124d
	 */
	public List<Order> queryUnfinishedOrder(String appKey, String userID) {
		// TODO: implement
		return null;
	}

	/**
	 * @param appKey
	 * @param order
	 * @pdOid d86c3b3c-881d-4a43-a027-fa4f19815cb9
	 */
	public String updateOrder(String appKey, Order order) {
		// TODO: implement
		return null;
	}

}