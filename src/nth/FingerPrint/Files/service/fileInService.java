package nth.FingerPrint.Files.service;

import javax.annotation.Resource;

import nth.FingerPrint.Files.dao.FileInDao;
import nth.FingerPrint.Files.po.ShoppingCart;
import org.springframework.stereotype.Component;

@Component
public class fileInService {
	@Resource
	private FileInDao fileInDao = new FileInDao();
	private double eachPagePrice=0.1;
	
	public FileInDao getFileInDao() {
		return fileInDao;
	}

	public void setFileInDao(FileInDao fileInDao) {
		this.fileInDao = fileInDao;
	}

	public int fileUpload(int userID, String fileID, String storePath, int totalPages) {
		ShoppingCart sh = new ShoppingCart();
		sh.setUserID(userID);
		sh.setShoppingCartID(fileID);
		sh.setEachPagePrice(eachPagePrice);
		sh.setDocumentPages(totalPages);
		sh.setEachUnitPrice(totalPages*eachPagePrice);
		sh.setStorePath(storePath);
		
		return fileInDao.saveUpload(sh,storePath);
	}
	


/*	public ShoppingCart returnPath(String userID) {
		
		return fileInDao.queryStorePath(userID);
	}*/


	public String updateDocument(ShoppingCart sh) {
		return fileInDao.updateDocument(sh);
	}

	public ShoppingCart findShoppingCart(String storePath, int userID) {
		// TODO Auto-generated method stub
		return fileInDao.findShoppingCart(storePath,userID);
	}

}
