package nth.FingerPrint.Users.po;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(schema="fingerprint",name="t_userinfo" )
public class User {
	@Id
	private int userID;
	private String userName;
	private String associationWeChat;
	private String headPicturePath;
	private String password;
	private String notes;
	private String nickName;
	private String registerTime;
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getAssociationWeChat() {
		return associationWeChat;
	}
	public void setAssociationWeChat(String associationWeChat) {
		this.associationWeChat = associationWeChat;
	}
	public String getHeadPicturePath() {
		return headPicturePath;
	}
	public void setHeadPicturePath(String headPicturePath) {
		this.headPicturePath = headPicturePath;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}
	
	
}
