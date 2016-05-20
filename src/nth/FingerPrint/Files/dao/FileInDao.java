package nth.FingerPrint.Files.dao;

import javax.annotation.Resource;

import nth.FingerPrint.Files.po.ShoppingCart;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;

@Component
public class FileInDao {
	@Resource
	private HibernateTemplate hibernateTemplate;
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	public int saveUpload(ShoppingCart sh, String storePath) {
		hibernateTemplate.save(sh);
		return sh.getUserID();
	}
	public ShoppingCart queryStorePath(String userID, String fileID) {
		String str = "from ShoppingCart sh where sh.userID =? and sh.shoppingCartID = ?";
		return (ShoppingCart) hibernateTemplate.find(str,userID,fileID).get(0);
	}

	public String updateDocument(ShoppingCart sh) {
		hibernateTemplate.update(sh);
//		String str = "update ShoppingCart sho set sho.printFormat =? and sho.printCopies = ?";
//		hibernateTemplate.bulkUpdate(str,new Object[] { sh.getPrintFormat() ,
//				sh.getPrintCopies()});
		return "true";
	}
	public ShoppingCart findShoppingCart(String storePath, int userID) {
		// TODO Auto-generated method stub
		String str = "from ShoppingCart sh where  sh.userID = ? and sh.storePath = ?";
		System.out.println(storePath);
		return (ShoppingCart) hibernateTemplate.find(str,userID, storePath).get(0);
	}
	
	public ShoppingCart findShoppingCart(String shoppingCartID){
		String str = "from ShoppingCart sh where  sh.shoppingCartID = ?";
		return (ShoppingCart) hibernateTemplate.find(str,shoppingCartID).get(0);
	}

}
