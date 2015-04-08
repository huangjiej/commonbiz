package com.hummingbird.commonbiz.vo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

import com.hummingbird.common.util.json.JSONException;

/**
 * 支付成功通知接口
 * @author huangjiej_2
 * 2014年9月21日 上午11:03:26
 */
public  class PostPayNotifiable implements Notifiable {

	/**
	 * 存放要获取的field
	 */
	protected List otherparam = new ArrayList();
	
	/**
	 * 商户自定义
	 */
	protected String alias;
	
	/**
	 * 计费商品代码
	 */
	protected String productId;
	/**
	 * 交易金额
	 */
	protected int amount;
	/**
	 * 成功支付时间
	 */
	protected Date payTime;
	
	/**
	 * 计费类型
	 */
	protected String billingType;
	/**
	 * APP指定的通知URL地址
	 */
	protected String notifyUrl;
	/**
	 * 蜂鸟平台订单流水号
	 */
	protected String orderId;
	
	/**
	 * 商户平台订单流水号
	 */
	protected String sellerOrderId;

	private Date createTime;
	
	/**
	 * 手机号码
	 */
	protected String mobile;
	
	/**
	 * 手机号码
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * 手机号码
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	/**
	 * 商户自定义
	 */
	public String getAlias() {
		return alias;
	}
	/**
	 * 商户自定义
	 */
	public void setAlias(String alias) {
		this.alias = alias;
	}
	/**
	 * 计费商品代码
	 */
	public String getProductId() {
		return productId;
	}
	/**
	 * 计费商品代码
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}
	/**
	 * 交易金额
	 */
	public int getAmount() {
		return amount;
	}
	/**
	 * 交易金额
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}
	/**
	 * 成功支付时间
	 */
	public Date getPayTime() {
		return payTime;
	}
	/**
	 * 成功支付时间
	 */
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	
	/**
	 * 计费类型
	 */
	public String getBillingType() {
		return billingType;
	}
	/**
	 * 计费类型
	 */
	public void setBillingType(String billingType) {
		this.billingType = billingType;
	}
	/**
	 * APP指定的通知URL地址
	 */
	@Override
	public String getNotifyUrl() {
		return notifyUrl;
	}
	/**
	 * APP指定的通知URL地址
	 */
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	/**
	 * 蜂鸟平台订单流水号
	 */
	public String getOrderId() {
		return orderId;
	}
	/**
	 * 蜂鸟平台订单流水号
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	/**
	 * 商户平台订单流水号
	 */
	public String getSellerOrderId() {
		return sellerOrderId;
	}
	/**
	 * 商户平台订单流水号
	 */
	public void setSellerOrderId(String sellerOrderId) {
		this.sellerOrderId = sellerOrderId;
	}
	
	/**
	 * 获取请求参数
	 * @return
	 * @throws JSONException 
	 */
	public String toJson() throws JSONException{
		Map jsobj = new HashMap();
		
//		try {
//			if(PayActType.CHANNEL.equals(order.getBillingType())){
//				//使用通道方式时,拿的id应该是第三方提供的id,以后可能要重新封装方法
//				jsobj.put("orderid", order.getSellerOrderId());
//			}
//			else{
//				jsobj.put("orderid", order.getOrderId());
//			}
		
			jsobj.put("orderid", getOrderId());
			jsobj.put("productId", getProductId());
			jsobj.put("amount", getAmount());
			jsobj.put("payTime", getPayTime());
//			jsobj.put("createTime", getCreateTime());
			jsobj.put("alias", getAlias());
			jsobj.put("sellerOrderId", getSellerOrderId());
			jsobj.put("mobile", getMobile());
//			jsobj.put("appId", order.getAppId());
//			jsobj.put("goodsName", order.getGoodsName());
			
			if(!otherparam.isEmpty())
			{
				for (Iterator iterator = otherparam.iterator(); iterator
						.hasNext();) {
					String  otherfieldname = (String ) iterator.next();
					String property;
					try {
						property = BeanUtils.getProperty(this, otherfieldname);
					} catch (Exception e) {
						e.printStackTrace();
						throw new JSONException("生成json,动态获取参数失败:"+e.getMessage());
					}
					jsobj.put(otherfieldname, property);
				}
			}
			ObjectMapper mapper = new ObjectMapper();
			mapper.configure(SerializationConfig.Feature.INDENT_OUTPUT,
					Boolean.TRUE);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
			mapper.setDateFormat(format);
			String output;
			try {
				output = mapper.writeValueAsString(jsobj).replaceAll("\r", "").replaceAll("\n", "");
				return output;
			} catch (Exception e) {
				throw new JSONException("转换出错",e);
			}
//		}
		
	}
	
	public static void main(String[] args) throws JSONException {
		PostPayNotifiable p = new PostPayNotifiable(){

			@Override
			public String getDesc() {
				// TODO Auto-generated method stub
				return null;
			}};
		p.amount=20;
		p.orderId="20";
		p.payTime=new Date();
		
		System.out.println(p.toJson());
	}
	public void setCreateTime(Date createTime) {
		this.createTime=createTime;
		
	}
	public Date getCreateTime() {
		return createTime;
	}
	/* (non-Javadoc)
	 * @see com.hummingbird.commonbiz.vo.Notifiable#getDesc()
	 */
	@Override
	public String getDesc() {
		
		return "支付成功通知";
	}
	
}
