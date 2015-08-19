/**
 * 
 * NotifyUtil.java
 * 版本所有 深圳市蜂鸟娱乐有限公司 2013-2014
 */
package com.hummingbird.commonbiz.util;

import org.apache.commons.lang.StringUtils;

import com.hummingbird.common.util.http.HttpRequester;
import com.hummingbird.common.util.json.JSONException;
import com.hummingbird.common.util.json.JSONObject;
import com.hummingbird.common.vo.ResultModel;
import com.hummingbird.commonbiz.vo.Notifiable;

/**
 * @author john huang
 * 2015年3月5日 下午5:54:14
 * 本类主要做为通知工具类
 */
public class NotifyUtil {

	static org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory
			.getLog(NotifyUtil.class);
	
	/**
	 * 异步通知
	 * @param notifiable
	 */
	public static void asynNotify(final Notifiable notifiable){
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				doNotify(notifiable);
				
			}
		}).start();
		
		
	}
	
	/**
	 * 进行通知,有2次机会
	 * @param notifiable
	 * @return
	 */
	public static boolean doNotify(Notifiable notifiable) {
		if(log.isDebugEnabled()){
			log.debug("执行支付完成通知开始");
		}
		HttpRequester httpreq = new HttpRequester();// 同步URL，向该URL发起同步请求；
		String postJson;
		try {
			postJson = notifiable.toJson();
		} catch (JSONException e) {
			log.error("获取json数据失败",e);
			return false;
		}
		if(log.isDebugEnabled()){
			log.debug(String.format("%s,url=%s,postJson=%s", notifiable.getDesc(),notifiable.getNotifyUrl(),postJson));
		}
		boolean flag = httpreq.isCallBackSuccessByStream(
				notifiable.getNotifyUrl(), postJson);
		if (!flag) {// 尝试第二次
			flag = httpreq.isCallBackSuccessByStream(
					notifiable.getNotifyUrl(), postJson);
		}
		if(log.isDebugEnabled()){
			log.debug("通知第三方平台是否成功....success=" + flag);
		}
		return flag;
	}
	
	/**
	 * 进行通知,有2次机会
	 * @param notifiable
	 * @return
	 */
	public static ResultModel doNotifyWithResult(Notifiable notifiable) {
		if(log.isDebugEnabled()){
			log.debug("执行通知开始");
		}
		ResultModel rm = new ResultModel();
		HttpRequester httpreq = new HttpRequester();// 同步URL，向该URL发起同步请求；
		String postJson;
		try {
			postJson = notifiable.toJson();
		} catch (JSONException e) {
			log.error("获取json数据失败",e);
			if(log.isDebugEnabled()){
				log.debug("执行通知结束");
			}
			rm.setErr(1000, "通知数据组装失败");
			return rm;
		}
		if(log.isDebugEnabled()){
			log.debug(String.format("%s,url=%s,postJson=%s", notifiable.getDesc(),notifiable.getNotifyUrl(),postJson));
		}
		try {
			String returnStr = httpreq.postRequest(notifiable.getNotifyUrl(),postJson); 
			if (log.isDebugEnabled()) {
				log.debug(String.format("返回结果为%s",returnStr));
			}
			if(returnStr==null){
				rm.setErr(1001, "地址"+notifiable.getNotifyUrl()+"访问不通");
				return rm;
			}
			else{
				JSONObject obj;
				try {
					obj = new JSONObject(returnStr);
				} catch (Exception e) {
					log.error(String.format("把结果转换为json对象出错"),e);
					rm.setErr(1002, "结果无法解析");
					return rm;
				}
				if("0".equals(obj.optString("errcode"))){
					rm.setErr(0,StringUtils.defaultIfEmpty(obj.optString("errmsg"), "接收方返回成功") );
					return rm;
				}
				else{
					rm.setErr(1002,StringUtils.defaultIfEmpty(obj.optString("errmsg"), "接收方返回失败") );
					return rm;
					
				}
			}
		} catch (Exception e) {
			log.error("通知失败",e);
			rm.setErr(1004,"通知失败:"+e.getMessage() );
			return rm;
		}
	}
	
	
}
