package nth.FingerPrint.Orders.Dao;

import java.util.List;

import javax.annotation.Resource;

import nth.FingerPrint.Orders.po.Order;
import nth.FingerPrint.Users.po.User;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

@Component
public class OrdersDao {
	@Resource
	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	public String addOrder(Order order){
		System.out.println(order.getSubmitTime());
		hibernateTemplate.save(order);
		return order.getVerificationCode();
	}
	
	public void updateOrder(Order order){
		hibernateTemplate.update(order);
	}
	
	public List<Order> queryAllOrders(User user){
		System.out.println(user.getUserID());
		String hql="from Order o where o.UserID=? order by o.SubmitTime desc ";
		return hibernateTemplate.find(hql, user.getUserID());
	}
	
	
	public boolean deleteOrder(Order order){
		try{
			hibernateTemplate.delete(order);
		}catch(Exception e){
			return false;
		}
		return true;
	}
	
	public List<Order> queryUnfinishedOrder(User user){
		String hql="from Order o where o.state='1' and o.UserID=?";
		return hibernateTemplate.find(hql, user.getUserID());
	}
	
	public String findCode(int userID,String code){
		String str = "from Order o where o.UserID = ? and o.VerificationCode = ?";
		Order o = (Order) hibernateTemplate.find(str,userID,code).get(0);
		if(o.getIsValidity()==0){
			o.setIsValidity(1);
			updateOrder(o);
			return o.getOrderID();
		}else{
			return null;
		}
	}
}
