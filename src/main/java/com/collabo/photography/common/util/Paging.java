package com.collabo.photography.common.util;

public class Paging {
	
	private int totalPage;
	private int limit=8;
	private int currentPage;
	private int offset;
	private int count;
	
	private String searchOption;
	private String keyword;
	
	public Paging() {
		
	}
	
	public Paging(String pageNumber,int count) {
		this.count=count;
		if(pageNumber==null) {
			this.currentPage=1;
		}else {
			this.currentPage=Integer.parseInt(pageNumber);
		}
		this.offset=(currentPage-1)*limit;
		this.totalPage=(int)Math.ceil((double)count/this.limit);

	}
	
	public Paging(String pageNumber,int count,String searchOption,String keyword) {
		this.count=count;
		if(pageNumber==null) {
			this.currentPage=1;
		}else {
			this.currentPage=Integer.parseInt(pageNumber);
		}
		this.offset=(currentPage-1)*limit;
		this.totalPage=(int)Math.ceil((double)count/this.limit);

			this.searchOption=searchOption;
			this.keyword=keyword;

		

	}
		




	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public String getSearchOption() {
		return searchOption;
	}

	public void setSearchOption(String searchOption) {
		this.searchOption = searchOption;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
}
