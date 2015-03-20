package com.hummingbird.commonbiz.vo;

import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.hummingbird.common.exception.SignatureException;
import com.hummingbird.common.util.Md5Util;
import com.hummingbird.common.util.SignatureUtil;
import com.hummingbird.common.util.ValidateUtil;
import com.hummingbird.common.vo.Signaturable;
import com.hummingbird.commonbiz.face.AppObj;


/**
 * 应用vo，对应为网站标识
 * @author huangjiej_2
 * 2014年11月11日 上午8:39:44
 */
@JsonIgnoreProperties(value = {"appKey", "appname"})
public class AppVO extends Signaturable implements AppObj,Decidable {
	
	protected String appId;
	
	protected String appKey;
	
	protected String appname;
	
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
	/* (non-Javadoc)
	 * @see com.hummingbird.commonbiz.vo.Decidable#getType()
	 */
	@Override
	public int getType() {
		return 0;
	}
	/* (non-Javadoc)
	 * @see com.hummingbird.commonbiz.vo.Decidable#setOtherParam(java.util.Map)
	 */
	@Override
	public void setOtherParam(Map map) {
		if(map.containsKey("appKey")){
			appKey = ObjectUtils.toString(map.get("appKey"));
		}
	}
	/* (non-Javadoc)
	 * @see com.hummingbird.commonbiz.vo.Decidable#isAuthed()
	 */
	@Override
	public boolean isAuthed() throws SignatureException {
		
		return SignatureUtil.validateSignature(signature,SignatureUtil.SIGNATURE_TYPE_MD5,(getPaintText()));
	}
	
	protected String getPaintText(){
		return ValidateUtil.sortbyValues(appKey,appId,nonce,timeStamp);
	}
	
}
