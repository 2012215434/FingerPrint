package nth.FingerPrint.Orders.po;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema="fingerprint", name="t_orderdetails")
public class OrderDetails {
	@Id
	private int OrderDetailsSID;
	private String OrderID;
	private String ShoppingCartID;
	private String Notes;
	
	public int getOrderDetailsSID() {
		return OrderDetailsSID;
	}
	public void setOrderDetailsSID(int orderDetailsSID) {
		OrderDetailsSID = orderDetailsSID;
	}
	public String getOrderID() {
		return OrderID;
	}
	public void setOrderID(String orderID) {
		OrderID = orderID;
	}
	public String getShoppingCartID() {
		return ShoppingCartID;
	}
	public void setShoppingCartID(String shoppingCartID) {
		ShoppingCartID = shoppingCartID;
	}
	public String getNotes() {
		return Notes;
	}
	public void setNotes(String notes) {
		Notes = notes;
	}
}
