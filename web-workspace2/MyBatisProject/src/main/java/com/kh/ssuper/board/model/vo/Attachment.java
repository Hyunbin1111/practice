package com.kh.ssuper.board.model.vo;

import java.util.Date;

public class Attachment {

	private int fileNo;
	private int refBno;
	private String OriginName;
	private String changeName;
	private String filePath;
	private Date uploadDate;
	private int fileLevel;
	private String status;
	
	 
	public Attachment() {
		super();
	}
	
	public Attachment(int fileNo, int refBno, String originName, String changeName, String filePath, Date uploadDate,
			int fileLevel, String status) {
		super();
		this.fileNo = fileNo;
		this.refBno = refBno;
		OriginName = originName;
		this.changeName = changeName;
		this.filePath = filePath;
		this.uploadDate = uploadDate;
		this.fileLevel = fileLevel;
		this.status = status;
	}
	
	public final int getFileNo() {
		return fileNo;
	}
	public final void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}
	public final int getRefBno() {
		return refBno;
	}
	public final void setRefBno(int refBno) {
		this.refBno = refBno;
	}
	public final String getOriginName() {
		return OriginName;
	}
	public final void setOriginName(String originName) {
		OriginName = originName;
	}
	public final String getChangeName() {
		return changeName;
	}
	public final void setChangeName(String changeName) {
		this.changeName = changeName;
	}
	public final String getFilePath() {
		return filePath;
	}
	public final void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public final Date getUploadDate() {
		return uploadDate;
	}
	public final void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}
	public final int getFileLevel() {
		return fileLevel;
	}
	public final void setFileLevel(int fileLevel) {
		this.fileLevel = fileLevel;
	}
	public final String getStatus() {
		return status;
	}
	public final void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
