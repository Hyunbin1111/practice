package com.kh.ssuper.board.model.vo;

public class Reply {
	
	private int replyNo;
	private String replyContent;
	private int refBno;
	private String replyWriter;
	private String createDate;
	private String status;
	
	public Reply() {
		super();
	}
	
	public Reply(int replyNo, String replyContent, int refBno, String replyWriter, String createDate, String status) {
		super();
		this.replyNo = replyNo;
		this.replyContent = replyContent;
		this.refBno = refBno;
		this.replyWriter = replyWriter;
		this.createDate = createDate;
		this.status = status;
	}

	public final int getReplyNo() {
		return replyNo;
	}

	public final void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}

	public final String getReplyContent() {
		return replyContent;
	}

	public final void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public final int getRefBno() {
		return refBno;
	}

	public final void setRefBno(int refBno) {
		this.refBno = refBno;
	}

	public final String getReplyWriter() {
		return replyWriter;
	}

	public final void setReplyWriter(String replyWriter) {
		this.replyWriter = replyWriter;
	}

	public final String getCreateDate() {
		return createDate;
	}

	public final void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public final String getStatus() {
		return status;
	}

	public final void setStatus(String status) {
		this.status = status;
	}
	
	

}
