package nth.FingerPrint.Files.po;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="T_UserInfo",catalog="fingerprint")
public class UserInfo {
	@Id
	private String userID;
	private String nickName;
	private String userName;
	private String associationWeChat;
	private String headPicturePath;
	private String password;
	private String registerTime;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	private String notes;
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
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
	public String getRegisterTime() {
		return registerTime;
	}
	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
}
