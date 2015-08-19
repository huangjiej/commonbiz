/**
 * 
 * DataSyncEvent.java
 * 版本所有 深圳市蜂鸟娱乐有限公司 2013-2014
 */
package com.hummingbird.commonbiz.event;

import com.hummingbird.common.event.BusinessEvent;
import com.hummingbird.commonbiz.face.DataSyncReceiveBody;

/**
 * @author john huang
 * 2015年8月6日 下午8:46:48
 * 本类主要做为
 */
public interface DataSyncEvent extends BusinessEvent{

	/**
	 * 获取数据同步对象
	 * @return
	 */
	public DataSyncReceiveBody getDataSyncBody();
	
	
}
