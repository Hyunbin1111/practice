package com.kh.ssuper.board.model.vo;

import java.util.Date;

public class Board {
	private int boardNo;
	private String boardTitle;
	private String boardWriter;
	private String boardContent;
	private int count;
	private Date createDate;
	private String category;
	private String imagePath;
	
	public Board() {
		super();
	}
	
	public Board(int boardNo, String boardTitle, String boardWriter, String boardContent, int count, Date createDate,
			String category, String imagePath) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardWriter = boardWriter;
		this.boardContent = boardContent;
		this.count = count;
		this.createDate = createDate;
		this.category = category;
		this.imagePath = imagePath;
	}
	
	public final int getBoardNo() {
		return boardNo;
	}
	public final void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public final String getBoardTitle() {
		return boardTitle;
	}
	public final void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public final String getBoardWriter() {
		return boardWriter;
	}
	public final void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}
	public final String getBoardContent() {
		return boardContent;
	}
	public final void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
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
	public final String getCategory() {
		return category;
	}
	public final void setCategory(String category) {
		this.category = category;
	}
	public final String getImagePath() {
		return imagePath;
	}
	public final void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	


}
