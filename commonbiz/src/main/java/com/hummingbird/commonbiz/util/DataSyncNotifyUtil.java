/**
 * 
 * DataSyncNotifyUtil.java
 * 版本所有 深圳市蜂鸟娱乐有限公司 2013-2014
 */
package com.hummingbird.commonbiz.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.math.NumberUtils;

import com.hummingbird.common.exception.DataInvalidException;
import com.hummingbird.common.util.JsonUtil;
import com.hummingbird.common.util.Md5Util;
import com.hummingbird.common.util.PropertiesUtil;
import com.hummingbird.common.util.StrUtil;
import com.hummingbird.common.util.ValidateUtil;
import com.hummingbird.common.util.json.JSONException;
import com.hummingbird.commonbiz.event.DataSyncEvent;
import com.hummingbird.commonbiz.face.DataSyncReceiveBody;
import com.hummingbird.commonbiz.vo.AppVO;
import com.hummingbird.commonbiz.vo.BaseTransVO;
import com.hummingbird.commonbiz.vo.Notifiable;
import com.hummingbird.commonbiz.vo.TransOrderVOSign;

/**
 * @author john huang
 * 2015年8月18日 上午9:34:49
 * 本类主要做为 数据同步通知工具
 */
public class DataSyncNotifyUtil {

	static org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory
			.getLog(DataSyncNotifyUtil.class);
	
	public static void notify(DataSyncEvent event){
		if (log.isDebugEnabled()) {
			log.debug(String.format("数据同步开始"));
		}
		final PropertiesUtil pu = new PropertiesUtil();
		final BaseTransVO<DataSyncReceiveBody> trans = new BaseTransVO<DataSyncReceiveBody>();
		AppVO app = new AppVO();
		app.setAppId(pu.getProperty("datasync.app.appid"));
		app.setAppKey(pu.getProperty("datasync.app.appkey"));
		app.setNonce( StrUtil.genRandomCode(6));
		app.setTimeStamp(String.valueOf(System.currentTimeMillis()));
		app.setSignature(Md5Util.Encrypt(ValidateUtil.sortbyValues(app.getAppId(),app.getAppKey(),app.getNonce(),app.getTimeStamp())));
		trans.setApp(app );
		trans.setBody(event.getDataSyncBody());
		TransOrderVOSign tSIG = new TransOrderVOSign();
		Map data = trans.getBody().getData();
		Collection values = data.values();
		
		List<String> list = new ArrayList<String>();
		for (Iterator iterator = values.iterator(); iterator.hasNext();) {
			String str = (String) ObjectUtils.toString(iterator.next());
			list.add(str);
		}
		list.add(trans.getBody().getDataType());
		
		String sortbyValues = ValidateUtil.sortbyValues(list);
		tSIG.setOrderMD5(Md5Util.Encrypt(sortbyValues));
		tSIG.setNonce( StrUtil.genRandomCode(6));
		tSIG.setTimeStamp(String.valueOf(System.currentTimeMillis()));
		tSIG.setSignature(Md5Util.Encrypt(ValidateUtil.sortbyValues(tSIG.getOrderMD5(),app.getAppId(),tSIG.getNonce(),tSIG.getTimeStamp())));
		trans.setTsig(tSIG );
		NotifyUtil.doNotify(new Notifiable() {
			
			@Override
			public String toJson() throws JSONException {
				try {
					return JsonUtil.convert2Json(trans);
				} catch (DataInvalidException e) {
					log.error(String.format("转换内容为json出错"),e);
					throw new JSONException("转换内容为json出错",e);
				}
			}
			
			@Override
			public String getNotifyUrl() {
				return pu.getProperty("datasync.notify.url");
			}
			
			@Override
			public String getDesc() {
				return "数据同步通知";
			}
		});
		if (log.isDebugEnabled()) {
			log.debug(String.format("数据同步完成"));
		}
	}
	
}
