package com.kh.ssuper.member.model.vo;

import java.sql.Date;

public class Member {
	private int userNo;
	private String userId;
	private String userPwd;
	private String userName;
	private String email;
	private String interest;
	private Date enrollDate;
	private Date modifyDate;
	private String status;
	
	public Member() {
		super();
	}

	public Member(int userNo, String userId, String userPwd, String userName, String email, String interest,
			Date enrollDate, Date modifyDate, String status) {
		super();
		this.userNo = userNo;
		this.userId = userId;
		this.userPwd = userPwd;
		this.userName = userName;
		this.email = email;
		this.interest = interest;
		this.enrollDate = enrollDate;
		this.modifyDate = modifyDate;
		this.status = status;
	}

	public final int getUserNo() {
		return userNo;
	}

	public final void setUserNo(int userNo) {
		this.userNo = userNo;
	}


	public final String getUserId() {
		return userId;
	}

	public final void setUserId(String userId) {
		this.userId = userId;
	}

	public final String getUserPwd() {
		return userPwd;
	}

	public final void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public final String getUserName() {
		return userName;
	}

	public final void setUserName(String userName) {
		this.userName = userName;
	}

	public final String getEmail() {
		return email;
	}

	public final void setEmail(String email) {
		this.email = email;
	}

	public final String getInterest() {
		return interest;
	}

	public final void setInterest(String interest) {
		this.interest = interest;
	}

	public final Date getEnrollDate() {
		return enrollDate;
	}

	public final void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}

	public final Date getModifyDate() {
		return modifyDate;
	}

	public final void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public final String getStatus() {
		return status;
	}

	public final void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Member [userNo=" + userNo + ", userId=" + userId + ", userPwd=" + userPwd + ", userName=" + userName
				+ ", email=" + email + ", interest=" + interest + ", enrollDate=" + enrollDate + ", modifyDate="
				+ modifyDate + ", status=" + status + "]";
	}

	
	
}