package com.kh.ssuper.board.model.vo;

public class Category {
	
	private int categoryNo;
	private String categoryName;
	
	public Category() {
		super();
	}
	
	public Category(int categoryNo, String categoryName) {
		super();
		this.categoryNo = categoryNo;
		this.categoryName = categoryName;
	}
	
	public final int getCategoryNo() {
		return categoryNo;
	}
	public final void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
	}
	public final String getCategoryName() {
		return categoryName;
	}
	public final void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	
	
}
