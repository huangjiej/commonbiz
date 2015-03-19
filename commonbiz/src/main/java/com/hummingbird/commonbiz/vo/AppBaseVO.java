/**
 * 
 * AppBaseVO.java
 * 版本所有 深圳市蜂鸟娱乐有限公司 2013-2014
 */
package com.hummingbird.commonbiz.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.hummingbird.common.exception.SignatureException;
import com.hummingbird.common.util.ArrayBuilder;
import com.hummingbird.common.util.SignatureUtil;
import com.hummingbird.commonbiz.vo.Decidable;

/**
 * @author huangjiej_2
 * 2015年1月1日 下午1:34:31
 * 本类主要做为 controller的传入参数，它的格式为
 * {"app":{"appId":"zjhtwallet","timeStamp":"TIMESTAMP", "nonce":"NONCE","signature":"SIGNATURE"},
 * "login":{"mobileNum":"13912345678","smsCode":"4567"}  
 * 有一个app属性，app属性里面包含签名等内容，它可以去进行签名验证
 */
@JsonIgnoreProperties(value = {"appId", "type","authed","businessKeys"})
public class AppBaseVO  implements Decidable{

	/**
	 * app信息
	 */
	protected AppVO app;
	
	/**
	 * 构造函数
	 */
	public AppBaseVO() {
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
		if(map!=null)
		{
			if(map.containsKey("appKey"))
			{
				app.setAppKey(ObjectUtils.toString(map.get("appKey")));
			}
		}
		//其它的业务属性，请overwrite 此方法
	}
	
	/**
	 * 获取其它业务key，这些key可以参与签名的验证
	 * 具体类请overwrite 此方法
	 * @return
	 */
	public String[] getBusinessKeys(){
		return null;
	}

	/* (non-Javadoc)
	 * @see com.hummingbird.commonbiz.vo.Decidable#isAuthed()
	 */
	@Override
	public boolean isAuthed() throws SignatureException {
		
		String[] otherkeys = getBusinessKeys();
		if(otherkeys==null||otherkeys.length==0){
			
			return SignatureUtil.validateSignature(app.getSignature(), SignatureUtil.SIGNATURE_TYPE_MD5, app.getAppId(),app.getAppKey(),app.getNonce(),app.getTimeStamp());
		}
		else{
			ArrayBuilder<String> ab = new ArrayBuilder<String>();
			ab.addAll(otherkeys);
			ab.add(app.getAppId());
			ab.add(app.getAppKey());
			ab.add(app.getNonce());
			ab.add(app.getTimeStamp());
			return SignatureUtil.validateSignature(app.getSignature(), SignatureUtil.SIGNATURE_TYPE_MD5,ab.getArray() );
			
		}
		
	}

}
