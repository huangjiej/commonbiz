/**
 * 
 * BaseTransOrderVO.java
 * 版本所有 深圳市蜂鸟娱乐有限公司 2013-2014
 */
package com.hummingbird.commonbiz.vo;

import java.util.Map;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.hummingbird.common.exception.SignatureException;

/**
 * @author john huang
 * 2015年2月5日 下午10:17:26
 * 本类主要做为 公用的接口结构
 */
public class BaseTransVO<DETAIL> implements Decidable {

	/**
	 * 发起应用信息
	 */
	protected AppVO app;
	/**
	 * 签名信息
	 */
	protected TransOrderVOSign tsig;
	/**
	 * 主体内容
	 */
	protected  DETAIL body;

	/**
	 * 构造函数
	 */
	public BaseTransVO() {
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

	/* (non-Javadoc)
	 * @see com.hummingbird.commonbiz.vo.Decidable#getType()
	 */
	@Override
	@JsonIgnore
	public int getType() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.hummingbird.commonbiz.vo.Decidable#setOtherParam(java.util.Map)
	 */
	@Override
	@JsonIgnore
	public void setOtherParam(Map map) {
		
		
	}

	/* (non-Javadoc)
	 * @see com.hummingbird.commonbiz.vo.Decidable#isAuthed()
	 */
	@Override
	@JsonIgnore
	public boolean isAuthed() throws SignatureException {
		if(tsig!=null){
			//TODO 待处理
		}
		
		return true;
	}

	/**
	 * @return the body
	 */
	public DETAIL getBody() {
		return body;
	}

	/**
	 * @param body the body to set
	 */
	public void setBody(DETAIL body) {
		this.body = body;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BaseTransVO [app=" + app + ", tsig=" + tsig + ", body=" + body
				+ "]";
	}

}