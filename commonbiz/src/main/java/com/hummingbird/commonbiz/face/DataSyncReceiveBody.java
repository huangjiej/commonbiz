/**
 * 
 * DataSyncReceiveBody.java
 * 版本所有 深圳市蜂鸟娱乐有限公司 2013-2014
 */
package com.hummingbird.commonbiz.face;

import java.util.Map;

/**
 * @author john huang
 * 2015年8月13日 下午2:17:25
 * 本类主要做为
 */
public interface DataSyncReceiveBody {

	/**
	 * @return the dataType
	 */
	public abstract String getDataType();

	/**
	 * @return the data
	 */
	public abstract Map getData();

	/**
	 * @return the attrs
	 */
	public abstract Map getAttrs();

}