package com.kh.ssuper.board.model.vo;

import java.util.Date;
import java.util.List;

public class Board {
	private int boardNo;
	private int boardType;
	private String category;
	private String boardTitle;
	private String boardContent;
	private String boardWriter;
	private int count;
	private Date createDate;
	private String status;
	private String imagePath;
	private List<Reply> replyList;
	
	public Board() {
		super();
	}
	
	public Board(int boardNo, int boardType, String category, String boardTitle, String boardContent,
			String boardWriter, int count, Date createDate, String status, String imagePath, List<Reply> replyList) {
		super();
		this.boardNo = boardNo;
		this.boardType = boardType;
		this.category = category;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardWriter = boardWriter;
		this.count = count;
		this.createDate = createDate;
		this.status = status;
		this.imagePath = imagePath;
		this.replyList = replyList;
	}
	
	public final int getBoardNo() {
		return boardNo;
	}
	public final void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public final int getBoardType() {
		return boardType;
	}
	public final void setBoardType(int boardType) {
		this.boardType = boardType;
	}
	public final String getCategory() {
		return category;
	}
	public final void setCategory(String category) {
		this.category = category;
	}
	public final String getBoardTitle() {
		return boardTitle;
	}
	public final void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public final String getBoardContent() {
		return boardContent;
	}
	public final void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	public final String getBoardWriter() {
		return boardWriter;
	}
	public final void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}
	public final int getCount() {
		return count;
	}
	public final void setCount(int count) {
		this.count = count;
	}
	public final Date getCreateDate() {
		return createDate;
	}
	public final void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public final String getStatus() {
		return status;
	}
	public final void setStatus(String status) {
		this.status = status;
	}
	public final String getImagePath() {
		return imagePath;
	}
	public final void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public final List<Reply> getReplyList() {
		return replyList;
	}
	public final void setReplyList(List<Reply> replyList) {
		this.replyList = replyList;
	}

	
	
	


}
