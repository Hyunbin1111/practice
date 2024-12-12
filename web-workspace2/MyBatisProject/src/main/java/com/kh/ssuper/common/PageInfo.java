package com.kh.ssuper.common;

public class PageInfo {
	private int listCount;
	private int currentPage;
	private int pageLimit;
	private int boardLimit;
	
	private int maxPage;
	private int startPage;
	private int endPage;
	
	public PageInfo() {
		super();
	}
	
	public PageInfo(int listCount, int currentPage, int pageLimit, int boardLimit, int maxPage, int startPage,
			int endPage) {
		super();
		this.listCount = listCount;
		this.currentPage = currentPage;
		this.pageLimit = pageLimit;
		this.boardLimit = boardLimit;
		this.maxPage = maxPage;
		this.startPage = startPage;
		this.endPage = endPage;
	}
	
	public final int getListCount() {
		return listCount;
	}
	public final void setListCount(int listCount) {
		this.listCount = listCount;
	}
	public final int getCurrentPage() {
		return currentPage;
	}
	public final void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public final int getPageLimit() {
		return pageLimit;
	}
	public final void setPageLimit(int pageLimit) {
		this.pageLimit = pageLimit;
	}
	public final int getBoardLimit() {
		return boardLimit;
	}
	public final void setBoardLimit(int boardLimit) {
		this.boardLimit = boardLimit;
	}
	public final int getMaxPage() {
		return maxPage;
	}
	public final void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}
	public final int getStartPage() {
		return startPage;
	}
	public final void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public final int getEndPage() {
		return endPage;
	}
	public final void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	
	
	
	
}
