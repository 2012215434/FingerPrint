package nth.FingerPrint.Orders.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import nth.FingerPrint.Files.dao.FileInDao;
import nth.FingerPrint.Files.po.ShoppingCart;
import nth.FingerPrint.Orders.Dao.OrderDetailsDao;
import nth.FingerPrint.Orders.Dao.OrdersDao;
import nth.FingerPrint.Orders.po.Order;
import nth.FingerPrint.Orders.po.OrderVO;
import nth.FingerPrint.Users.Service.UserService;
import nth.FingerPrint.Users.po.User;
import nth.FingerPrint.common.Page;
import nth.FingerPrint.common.PageBean;

import org.springframework.stereotype.Component;

import com.sun.org.apache.bcel.internal.generic.NEW;

@Component
public class OrderService {
	@Resource
	OrdersDao orderDao = new OrdersDao();
	@Resource
	OrderDetailsDao orderDetailDao= new OrderDetailsDao();
	@Resource
	FileInDao fileInDao =new FileInDao();

	public FileInDao getFileInDao() {
		return fileInDao;
	}

	public void setFileInDao(FileInDao fileInDao) {
		this.fileInDao = fileInDao;
	}

	public OrderDetailsDao getOrderDetailDao() {
		return orderDetailDao;
	}

	public void setOrderDetailDao(OrderDetailsDao orderDetailDao) {
		this.orderDetailDao = orderDetailDao;
	}

	public OrdersDao getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrdersDao orderDao) {
		this.orderDao = orderDao;
	}
	
	public boolean addOrder(Order order){
		try{
			orderDao.addOrder(order);
		}catch(Exception e){
			return false;
		}
		return true;
	}
	
	public boolean updateOrder(Order order){
		try{
			orderDao.updateOrder(order);
		}catch(Exception e){
			return false;
		}
		return true;
	}
	
	public List<Order> queryAllOrder(User user){
		if(orderDao.queryAllOrders(user).isEmpty()){
			return null;
		}else{
			return orderDao.queryAllOrders(user);
		}
	}
	
	public PageBean showOrderList(int page,User user) {
		

		List list = new ArrayList();//最终的list
		
		List list2 = new ArrayList();//中间list
		
		List<Order> list3 = new ArrayList<Order>();
		
		list3 = (List<Order>) orderDao.queryAllOrders(user);
		
		List<OrderVO> list4=new ArrayList<OrderVO>();
		
		
		for(int i=0;i<list3.size();i++){
			Order o=(Order) list3.get(i);
			
			String shoppingCartID=orderDetailDao.findShoppingCartID(o.getOrderID());
			ShoppingCart shop=fileInDao.findShoppingCart(shoppingCartID);
			
			
			int copies=shop.getPrintCopies();
			int pages=shop.getDocumentPages();
			
			OrderVO orderVO=new OrderVO();
			orderVO.setCancelReson(o.getCancelReson());
			orderVO.setCopies(copies);
			orderVO.setIsPay(o.getIsPay());
			orderVO.setIsValidity(o.getIsValidity());
			orderVO.setOrderID(o.getOrderID());
			orderVO.setOrderTotalAmount(o.getOrderTotalAmount());
			orderVO.setDocumentPages(pages);
			orderVO.setPayTime(o.getPayTime());
			orderVO.setPayTimeLimit(o.getPayTimeLimit());
			orderVO.setSubmitTime(o.getSubmitTime());
			orderVO.setUserID(o.getUserID());
			orderVO.setVerificationCode(o.getVerificationCode());
			list4.add(orderVO);
		}
		
		
		
		
		for(int i=0;i<list4.size();i++){
			OrderVO objects = (OrderVO) list4.get(i);
			list.add(objects);
		}
		
		if(list.size()<((page-1)*10+1)){
			showOrderList(page-1,user);
		}
		
		if(list.size()>10){
			if(list.size()>(page*10)){
				for(int i=0;i<10;i++){
					list2.add(list.get((page-1)*10+i));
				}
			}else{
				for(int i=0;i<list.size()-((page-1)*10);i++){
					list2.add(list.get((page-1)*10+i));
				}
			}
		}else{
			list2 = list;
		}
				
		
		PageBean pageBean = new PageBean(list2,list2.size(),list.size()%10==0?list.size()/10:list.size()/10+1,page,10);
		
		return pageBean;
	}
	
	public boolean deleteOrder(Order order){
		try{
			orderDao.deleteOrder(order);
		}catch(Exception e ){
			return false;
		}
		return true;
	}
	
	public List<Order> queryUnfinishedOrder(User user){
		if(orderDao.queryUnfinishedOrder(user).isEmpty()){
			return null;
		}else{
			return orderDao.queryUnfinishedOrder(user);
		}
	}

	public String save(Order order) {
		return orderDao.addOrder(order);
	}
	
}
