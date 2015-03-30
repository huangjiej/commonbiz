/**
 * 
 * Notification.java
 * 版本所有 深圳市蜂鸟娱乐有限公司 2013-2014
 */
package com.hummingbird.commonbiz.face;

import java.util.Date;

/**
 * @author huangjiej_2
 * 2015年1月26日 上午11:26:37
 * 本类主要做为通知的顶级接口
 */
public interface PaymentNotification extends Notification {

	/**
	 * 对应的订单
	 * @return
	 */
	String getOrderId();
	
	/**
	 * 支付时间
	 * @return
	 */
	Date getPaytime();
	
	/**
	 * 是否支付成功
	 * @return
	 */
	boolean isPaySuccessed();
}
