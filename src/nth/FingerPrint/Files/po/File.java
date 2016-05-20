package nth.FingerPrint.Files.po;

import java.sql.Timestamp;

//@Table(name="XXX",catalog="fingerprint")
public class File {
	   private String fileID;
	   private String userID;
	   private String fileName;
	   private int filePages;
	   private Timestamp time;
	   private String filePath;
	public String getFileID() {
		return fileID;
	}
	public void setFileID(String fileID) {
		this.fileID = fileID;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public int getFilePages() {
		return filePages;
	}
	public void setFilePages(int filePages) {
		this.filePages = filePages;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

}
