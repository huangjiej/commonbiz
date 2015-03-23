/**
 * 
 * TransOrderVOSign.java
 * 版本所有 深圳市蜂鸟娱乐有限公司 2013-2014
 */
package com.hummingbird.commonbiz.vo;

import com.hummingbird.common.vo.Signaturable;

/**
 * @author huangjiej_2
 * 2014年12月25日 下午6:05:13
 * 本类主要做为对象签名
 */
public class TransOrderVOSign extends Signaturable {

	/**
	 * 订单md5签名
	 */
	private String orderMD5;

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TransOrderVOSign [orderMD5=" + orderMD5 + ", timeStamp="
				+ timeStamp + ", nonce=" + nonce + ", signature=" + signature
				+ "]";
	}

	/**
	 * @return the orderMD5
	 */
	public String getOrderMD5() {
		return orderMD5;
	}

	/**
	 * @param orderMD5 the orderMD5 to set
	 */
	public void setOrderMD5(String orderMD5) {
		this.orderMD5 = orderMD5;
	}
	
	
	
}
