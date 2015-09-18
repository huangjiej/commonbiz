/**
 * 
 * PagingnationVO.java
 * 版本所有 深圳市蜂鸟娱乐有限公司 2013-2014
 */
package com.hummingbird.commonbiz.vo;

import com.hummingbird.commonbiz.face.Pagingnation;

/**
 * @author huangjiej_2
 * 2015年1月8日 下午10:18:16
 * 本类主要做为分页的vo
 */
public class PagingnationVO {

	/**
	 * 构造函数
	 */
	public PagingnationVO() {
		// TODO Auto-generated constructor stub
	}
	
	protected int pageSize = 10;
	
	protected int pageIndex=1;

	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return the pageIndex
	 */
	public int getPageIndex() {
		return pageIndex;
	}

	/**
	 * @param pageIndex the pageIndex to set
	 */
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PagingnationVO [pageSize=" + pageSize + ", pageIndex="
				+ pageIndex + "]";
	}
	
	/**
	 * 分页组件
	 * @return
	 */
	public Pagingnation toPagingnation(){
		if(pageIndex<=0){
			pageIndex=1;
		}
		if(pageSize<=0){
			pageSize=10;
		}
		if(pageSize>500){
			pageSize=500;
		}
		return  new Pagingnation(pageIndex, pageSize);
	}

}
