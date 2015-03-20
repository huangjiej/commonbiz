/**
 * 
 * SDKAppVO.java
 * 版本所有 深圳市蜂鸟娱乐有限公司 2013-2014
 */
package com.hummingbird.commonbiz.vo;

import java.util.Map;

import com.hummingbird.common.util.ValidateUtil;

/**
 * @author john huang
 * 2015年3月20日 下午1:46:17
 * 本类主要做为
 */
public class SDKAppVO extends com.hummingbird.commonbiz.vo.AppVO {
	
	
	protected String appKeyHash;

	/**
	 * @return the appKeyHash
	 */
	public String getAppKeyHash() {
		return appKeyHash;
	}

	/**
	 * @param appKeyHash the appKeyHash to set
	 */
	public void setAppKeyHash(String appKeyHash) {
		this.appKeyHash = appKeyHash;
	}

	/* (non-Javadoc)
	 * @see com.hummingbird.commonbiz.vo.AppVO#setOtherParam(java.util.Map)
	 */
	@Override
	public void setOtherParam(Map map) {
		
		super.setOtherParam(map);
	}

	/* (non-Javadoc)
	 * @see com.hummingbird.commonbiz.vo.AppVO#getPaintText()
	 */
	@Override
	protected String getPaintText() {
		return ValidateUtil.sortbyValues(appKey,appId,nonce,timeStamp,appKeyHash);
	}
	
	
}
