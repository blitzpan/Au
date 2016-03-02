package com.au.utils;
import java.util.HashMap;
import java.util.Map;
public class Page{
	private static int DEFAULT_PAGE_SIZE = 2;
	private int pageSize = DEFAULT_PAGE_SIZE; // 每页的记录数
	private int pageNo = 1;// 当前页
	private int pageSum = 1;// 总页数
	private boolean hasNext = false;// 是否有下一页
	private boolean hasPrevious = false;// 是否有上一页
	private int start = 0; // 当前页第一条数据在List中的位置,从0开始
	private int totalCount; // 总记录数
	private int startPage=1;
	private int endPage=1;
	private Object result;// 记录集
	private Map<String, Object> params = new HashMap<String, Object>();
	private String paramsStr="";
	private Object data; // 当前页中存放的记录,类型一般为List
	@Override
	public String toString() {
		return "Page [pageSize=" + pageSize + ", pageNo=" + pageNo + ", pageSum=" + pageSum + ", hasNext=" + hasNext
				+ ", hasPrevious=" + hasPrevious + ", start=" + start + ", totalCount=" + totalCount + ", startPage="
				+ startPage + ", endPage=" + endPage + ", result=" + result + ", params=" + params + ", paramsStr="
				+ paramsStr + ", data=" + data + "]";
	}
	
	public void setTotalCount(int totalCount){
		this.totalCount = totalCount;
		//总页数
		if (totalCount % pageSize == 0){
			pageSum = totalCount / pageSize;
		} else{
			pageSum = totalCount / pageSize + 1;
		}
		pageNo = pageNo < 1? 1:pageNo;
		pageNo = pageNo > pageSum? pageSum:pageNo;
		if(pageNo == 0){
			pageNo = 1;
		}
		//上一页
		hasPrevious = pageNo>1;
		hasNext = pageNo<pageSum;
		//起始记录
		start = (pageNo - 1) * pageSize;
		//计算翻页栏的起始页和结束页
		startPage = pageNo - 4;
		endPage = pageNo + 5;
		startPage = startPage<1 ? 1:startPage;
		endPage = endPage>pageSum ? pageSum:endPage;
	}
	/**
	 * 取总记录数.
	 */
	public long getTotalCount() {
		return this.totalCount;
	}
	/**
	 * 取每页数据容量.
	 */
	public int getPageSize() {
		return pageSize;
	}
	public int getStart(){
		return start;
	}
	public int getStartPage() {
		return startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public boolean getHasNext() {
		return hasNext;
	}
	public boolean getHasPrevious() {
		return hasPrevious;
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}