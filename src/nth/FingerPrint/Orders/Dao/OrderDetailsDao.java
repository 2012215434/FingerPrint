package nth.FingerPrint.Orders.Dao;

import javax.annotation.Resource;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

import nth.FingerPrint.Files.po.ShoppingCart;
import nth.FingerPrint.Orders.po.OrderDetails;
@Component
public class OrderDetailsDao {
	@Resource
	private HibernateTemplate hibernateTemplate;
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public void save(OrderDetails o) {
		System.out.println(o);
		hibernateTemplate.save(o);	
	}
	
	public String findShoppingCartID(String orderID){
		
		String hql = "from OrderDetails o where o.OrderID = ?";
		OrderDetails o = (OrderDetails) hibernateTemplate.find(hql,orderID).get(0);
		return o.getShoppingCartID();
	}

	public String findPath(int userID, String shoppingCartID) {
		String str = "from ShoppingCart sh where sh.userID = ? and sh.shoppingCartID = ?";
		ShoppingCart sh = (ShoppingCart) hibernateTemplate.find(str,userID,shoppingCartID).get(0);
		return sh.getStorePath();
	}

}
