package nth.FingerPrint.print.Service;

import javax.annotation.Resource;


import nth.FingerPrint.Orders.Dao.OrderDetailsDao;
import nth.FingerPrint.Orders.Dao.OrdersDao;

import org.springframework.stereotype.Component;

@Component
public class PrintService {
	@Resource
	private OrdersDao orderDao = new OrdersDao();
	@Resource
	private OrderDetailsDao orderDetailsDao  = new OrderDetailsDao();
	public OrdersDao getOrderDao() {
		return orderDao;
	}
	public void setOrderDao(OrdersDao orderDao) {
		this.orderDao = orderDao;
	}
	public OrderDetailsDao getOrderDetailsDao() {
		return orderDetailsDao;
	}
	public void setOrderDetailsDao(OrderDetailsDao orderDetailsDao) {
		this.orderDetailsDao = orderDetailsDao;
	}
	
	public String judgeCode(int userID,String code){
		
		String orderID = orderDao.findCode(userID, code);
		if(orderID==null||orderID.equals("")){
			return null;
		}else{
			
			String shoppingCartID = orderDetailsDao.findShoppingCartID(orderID);
			String storage = orderDetailsDao.findPath(userID,shoppingCartID);
			return storage;
		}	
	}


	

}
