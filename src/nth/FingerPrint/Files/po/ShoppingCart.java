package nth.FingerPrint.Files.po;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="T_ShoppingCart",catalog="fingerprint")
public class ShoppingCart {
	@Id
	 private String shoppingCartID;
	 private int userID;
	 private int documentPages;
	 private String printFormat;
	 private double eachPagePrice;
	 private int printCopies;
	 private double eachUnitPrice;
	 private double amount;
	 private String storePath;
	 private String notes;
	public String getShoppingCartID() {
		return shoppingCartID;
	}
	public void setShoppingCartID(String shoppingCartID) {
		this.shoppingCartID = shoppingCartID;
	}
	
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getDocumentPages() {
		return documentPages;
	}
	public void setDocumentPages(int documentPages) {
		this.documentPages = documentPages;
	}
	public String getPrintFormat() {
		return printFormat;
	}
	public void setPrintFormat(String printFormat) {
		this.printFormat = printFormat;
	}
	public double getEachPagePrice() {
		return eachPagePrice;
	}
	public void setEachPagePrice(double eachPagePrice) {
		this.eachPagePrice = eachPagePrice;
	}
	public int getPrintCopies() {
		return printCopies;
	}
	public void setPrintCopies(int printCopies) {
		this.printCopies = printCopies;
	}
	public double getEachUnitPrice() {
		return eachUnitPrice;
	}
	public void setEachUnitPrice(double eachUnitPrice) {
		this.eachUnitPrice = eachUnitPrice;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getStorePath() {
		return storePath;
	}
	public void setStorePath(String storePath) {
		this.storePath = storePath;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}

}
