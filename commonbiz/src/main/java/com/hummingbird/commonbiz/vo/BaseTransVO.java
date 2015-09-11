/**
 * 
 * BaseTransOrderVO.java
 * 版本所有 深圳市蜂鸟娱乐有限公司 2013-2014
 */
package com.hummingbird.commonbiz.vo;

import java.io.ByteArrayInputStream;
import java.security.PublicKey;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.annotate.JsonIgnore;

import com.hummingbird.common.exception.DataInvalidException;
import com.hummingbird.common.exception.SignatureException;
import com.hummingbird.common.exception.ValidateException;
import com.hummingbird.common.util.CertificateUtils;
import com.hummingbird.common.util.SignatureUtil;
import com.hummingbird.common.util.ValidateUtil;
import com.hummingbird.common.vo.PainttextAble;

/**
 * @author john huang
 * 2015年2月5日 下午10:17:26
 * 本类主要做为 公用的接口结构
 */
public class BaseTransVO<DETAIL> implements Decidable {

	org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory
			.getLog(this.getClass());
	
	/**
	 * 公钥,验签用,通过
	 */
	protected String publickey;
	
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
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.hummingbird.commonbiz.vo.Decidable#setOtherParam(java.util.Map)
	 */
	@Override
	@JsonIgnore
	public void setOtherParam(Map map) {
		publickey =ObjectUtils.toString(map.get("appPublicKey"));
		
	}

	/* (non-Javadoc)
	 * @see com.hummingbird.commonbiz.vo.Decidable#isAuthed()
	 */
	@Override
	@JsonIgnore
	public boolean isAuthed() throws SignatureException {
		if(tsig!=null)
		{
			String painttext;
			if (body instanceof PainttextAble) {
				PainttextAble pa = (PainttextAble) body;
				painttext =  pa.getPaintText();
				if(!SignatureUtil.validateSignature(tsig.getOrderMD5(),SignatureUtil.SIGNATURE_TYPE_MD5 ,painttext)){
					throw ValidateException.ERROR_SIGNATURE_MD5.clone(null,"body签名不一致");
				}
			}
			else{
				try {
					painttext=ValidateUtil.getPaintText(body);
				} catch (DataInvalidException e) {
					log.error(String.format("获取body的明文失败"),e);
					throw ValidateException.ERROR_SIGNATURE_MD5.clone(e,"body明文验签失败");
				}
				if(!SignatureUtil.validateSignature(tsig.getOrderMD5(),SignatureUtil.SIGNATURE_TYPE_MD5 ,painttext)){
					throw ValidateException.ERROR_SIGNATURE_MD5.clone(null,"body签名不一致");
				}
				
			}
			validateTransOrderSign(painttext);
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
	
	/**
	 * 验证TransOrder的签名
	 * @param transorder
	 * @throws SignatureException 
	 */
	protected void validateTransOrderSign(String painttext) throws SignatureException {
		if (log.isDebugEnabled()) {
			log.debug(String.format("验证TransOrder的签名"));
		}
		
		boolean success;
		if(tsig.getSignature().length()>32||tsig.getSignature().contains("="))
		{//使用rsa签名
			String mingwen = ValidateUtil.sortbyValues(tsig.getTimeStamp(),tsig.getNonce(),tsig.getOrderMD5(),app.getAppId());
			String signature = tsig.getSignature();
			if(StringUtils.isBlank(publickey))
			{
				if (log.isDebugEnabled()) {
					log.debug(String.format("公钥，无法进行验签"));
				}
				throw ValidateException.ERROR_SIGNATURE_RSA;
				//throw new SignatureException(ValidateException.ERRCODE_SIGNATURE_FAIL,"签名验签不通过,app无公钥");
			}
			try {
				PublicKey pkey = CertificateUtils.getPublicKeyFromCer(new ByteArrayInputStream(Base64.decodeBase64(publickey)));
				success = CertificateUtils.verifySignatureByPublicKey(mingwen.getBytes(), signature, pkey);
			} catch (Exception e) {
				if (log.isDebugEnabled()) {
					log.error(String.format("TransOrder请求签名验签出错"),e);
				}
				SignatureException e1 = ValidateException.ERROR_SIGNATURE_RSA.clone(e);
				throw e1;
//				throw new SignatureException(ValidateException.ERRCODE_SIGNATURE_FAIL,"签名验签不通过",e);
			}
			
			if(!success)
			{
				if (log.isDebugEnabled()) {
					log.debug(String.format("TransOrder请求签名验签不通过"));
				}
				SignatureException e1 = ValidateException.ERROR_SIGNATURE_RSA;
				throw e1;
			}
			else{
				if (log.isDebugEnabled()) {
					log.debug(String.format("TransOrder请求签名验签通过"));
				}
				
			}
		}
		else{
			
			success=SignatureUtil.validateSignature(tsig.getSignature(), SignatureUtil.SIGNATURE_TYPE_MD5,tsig.getTimeStamp(),tsig.getNonce(),tsig.getOrderMD5(),app.getAppId() );
			if(!success)
			{
				if (log.isDebugEnabled()) {
					log.debug(String.format("TransOrder请求签名验签不通过"));
				}
				SignatureException e1 = ValidateException.ERROR_SIGNATURE_MD5;
				throw e1;
			}
			else{
				if (log.isDebugEnabled()) {
					log.debug(String.format("TransOrder请求签名验签通过"));
				}
				
			}
		}
	}

}