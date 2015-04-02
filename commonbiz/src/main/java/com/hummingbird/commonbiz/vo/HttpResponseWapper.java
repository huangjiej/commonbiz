/**
 * 
 * HttpResponseWapper.java
 * 版本所有 深圳市蜂鸟娱乐有限公司 2013-2014
 */
package com.hummingbird.commonbiz.vo;

import javax.servlet.http.HttpServletResponse;

import com.hummingbird.commonbiz.face.NotificationResponsor;

/**
 * @author john huang
 * 2015年4月1日 下午11:04:06
 * 本类主要做为http response输出器
 */
public class HttpResponseWapper implements NotificationResponsor{

	private HttpServletResponse response;

	public HttpResponseWapper(HttpServletResponse response){
		this.response = response;
	}

	/**
	 * @return the response
	 */
	public HttpServletResponse getResponse() {
		return response;
	}

	/**
	 * @param response the response to set
	 */
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	
	
}
