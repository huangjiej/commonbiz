/**
 * 
 */
package com.hummingbird.commonbiz.exception;

import com.hummingbird.common.exception.SignatureException;
import com.hummingbird.common.exception.ValidateException;

/**
 * 用户token异常
 * @author huangjiej_2
 * 2014年10月22日 下午2:16:33
 */
public class TokenException extends ValidateException {

	
	
	public TokenException() {
		super();
		super.errcode = 10707;
	}

	public TokenException(int errcode, String message) {
		super(errcode, message);
		super.errcode = 10707;
	}

	public TokenException(String message, Throwable cause) {
		super(message, cause);
		super.errcode = 10707;
	}

	public TokenException(String message) {
		super(message);
		super.errcode = 10707;
	}

	public TokenException(Throwable cause) {
		super(cause);
		super.errcode = 10707;
	}

	/**
	 * 复制一个新的异常
	 * @param e
	 * @return
	 */
	public ValidateException clone(Throwable e){
		return new TokenException(this.errcode, this.getMessage(), e);
	}

	public TokenException(int errcode, String message, Throwable cause) {
		super(errcode, message, cause);
	}
}
