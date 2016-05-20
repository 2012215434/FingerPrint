package nth.FingerPrint.Orders.Service;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import nth.FingerPrint.Orders.Dao.OrderDetailsDao;
import nth.FingerPrint.Orders.po.OrderDetails;
@Component
public class OrderDetailsService {
	@Resource
	OrderDetailsDao orderDetailsDao=new OrderDetailsDao();

	public OrderDetailsDao getOrderDetailsDao() {
		return orderDetailsDao;
	}

	public void setOrderDetailsDao(OrderDetailsDao orderDetailsDao) {
		this.orderDetailsDao = orderDetailsDao;
	}

	public void save(OrderDetails o) {
		orderDetailsDao.save(o);
	}

}
