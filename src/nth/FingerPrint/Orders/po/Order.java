package nth.FingerPrint.Orders.po;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/***********************************************************************
 * Module:  Order.java
 * Author:  Administrator
 * Purpose: Defines the Class Order
 ***********************************************************************/
/** @pdOid c0aedc48-8d64-42c2-8e60-b3e75f5c0265 */
@Entity
@Table(schema="fingerprint", name="t_order")
public class Order {
  @Id
   private String OrderID;
   
   private int UserID;
   
   private double OrderTotalAmount;
   
   private int IsPay;
   
   private Timestamp PayTimeLimit;
   
   private Timestamp PayTime;
  
   private String VerificationCode;
   
   private int IsValidity;
   
  private String CancelReson;
  
  private Timestamp SubmitTime;
   
   public Timestamp getSubmitTime() {
	return SubmitTime;
}

public void setSubmitTime(Timestamp submitTime) {
	SubmitTime = submitTime;
}

public String getOrderID() {
	return OrderID;
}

public void setOrderID(String orderID) {
	OrderID = orderID;
}



public int getUserID() {
	return UserID;
}

public void setUserID(int userID) {
	UserID = userID;
}

public double getOrderTotalAmount() {
	return OrderTotalAmount;
}

public void setOrderTotalAmount(double orderTotalAmount) {
	OrderTotalAmount = orderTotalAmount;
}

public int getIsPay() {
	return IsPay;
}

public void setIsPay(int isPay) {
	IsPay = isPay;
}

public Timestamp getPayTimeLimit() {
	return PayTimeLimit;
}

public void setPayTimeLimit(Timestamp payTimeLimit) {
	PayTimeLimit = payTimeLimit;
}

public Timestamp getPayTime() {
	return PayTime;
}

public void setPayTime(Timestamp payTime) {
	PayTime = payTime;
}

public String getVerificationCode() {
	return VerificationCode;
}

public void setVerificationCode(String verificationCode) {
	VerificationCode = verificationCode;
}

public int getIsValidity() {
	return IsValidity;
}

public void setIsValidity(int isValidity) {
	IsValidity = isValidity;
}

public String getCancelReson() {
	return CancelReson;
}

public void setCancelReson(String cancelReson) {
	CancelReson = cancelReson;
}
}