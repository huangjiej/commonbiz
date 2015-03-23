/**
 * 
 * BaseTransOrderVO.java
 * 版本所有 深圳市蜂鸟娱乐有限公司 2013-2014
 */
package com.hummingbird.commonbiz.vo;

/**
 * @author john huang
 * 2015年2月5日 下午10:17:26
 * 本类主要做为
 */
public class BaseTransOrderVO {

	/**
	 * 发起应用信息
	 */
	protected AppVO app;
	/**
	 * 签名信息
	 */
	protected TransOrderVOSign tsig;

	/**
	 * 构造函数
	 */
	public BaseTransOrderVO() {
		super();
	}

	/**
	 * @return the app
	 */
	public AppVO getApp() {
		return app;
	}

	/**
	 * @param app the app to set
	 */
	public void setApp(AppVO app) {
		this.app = app;
	}

	/**
	 * @return the tSIG
	 */
	public TransOrderVOSign getTsig() {
		return tsig;
	}

	/**
	 * @param tSIG the tSIG to set
	 */
	public void setTsig(TransOrderVOSign tSIG) {
		this.tsig = tSIG;
	}

}