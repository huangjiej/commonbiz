package com.hummingbird.commonbiz.vo;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.hummingbird.common.vo.Signaturable;
import com.hummingbird.commonbiz.face.AppObj;


/**
 * 应用vo，对应为网站标识
 * @author huangjiej_2
 * 2014年11月11日 上午8:39:44
 */
@JsonIgnoreProperties(value = {"appKey", "appname"})
public class AppVO extends Signaturable implements AppObj {
	
	private String appId;
	
	private String appKey;
	
	private String appname;
	
	@JsonIgnore
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}	
	
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	@Override
	public String toString() {
		return "AppVo [appId=" + appId + ", appKey=" + appKey
				+ ", getTimeStamp()=" + getTimeStamp() + ", getNonce()="
				+ getNonce() + ", getSignature()=" + getSignature() + "]";
	}
	/**
	 * @return the appname
	 */
	@JsonIgnore
	public String getAppname() {
		return appname;
	}
	/**
	 * @param appname the appname to set
	 */
	public void setAppname(String appname) {
		this.appname = appname;
	}
	
}
