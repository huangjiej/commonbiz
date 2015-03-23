/**
 * 
 */
package com.hummingbird.commonbiz.vo;

import java.util.List;

import org.apache.commons.lang.StringUtils;


/**
 * 设备信息
 * @author huangjiej_2
 * 2014年10月18日 下午10:07:53
 */
public class DeviceInfo {
//mac":"3ere:eee:3434:34434","deviceId":"设备标识","imsi":"SIM卡设备号
	/**
     * sim卡设备号
     */
    protected String imsi;

    /**
     * imsi对应的手机号码
     */
    protected String mobileNum;

    /**
     * sim所属设备的设备号
     */
    protected String imei;

    /**
     * sim所属设备的mac地址
     */
    protected String mac;

    
	/**
	 * 构造函数
	 */
	public DeviceInfo() {
		
	}

	/**
     * @return sim卡设备号
     */
    public String getImsi() {
        return imsi;
    }

    /**
     * @param imsi 
	 *            sim卡设备号
     */
    public void setImsi(String imsi) {
        this.imsi = imsi == null ? null : imsi.trim();
    }

    /**
     * @return imsi对应的手机号码
     */
    public String getMobileNum() {
        return mobileNum;
    }

    /**
     * @param mobilenum 
	 *            imsi对应的手机号码
     */
    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum == null ? null : mobileNum.trim();
    }

    /**
     * @return sim所属设备的设备号
     */
    public String getImei() {
        return imei;
    }

    /**
     * @param imei 
	 *            sim所属设备的设备号
     */
    public void setImei(String imei) {
        this.imei = imei == null ? null : imei.trim();
    }

    /**
     * @return sim所属设备的mac地址
     */
    public String getMac() {
        return mac;
    }

    /**
     * @param mac 
	 *            sim所属设备的mac地址
     */
    public void setMac(String mac) {
        this.mac = mac == null ? null : mac.trim();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        DeviceInfo other = (DeviceInfo) that;
        return (this.getImsi() == null ? other.getImsi() == null : this.getImsi().equals(other.getImsi()))
            && (this.getMobileNum() == null ? other.getMobileNum() == null : this.getMobileNum().equals(other.getMobileNum()))
            && (this.getImei() == null ? other.getImei() == null : this.getImei().equals(other.getImei()))
            && (this.getMac() == null ? other.getMac() == null : this.getMac().equals(other.getMac()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getImsi() == null) ? 0 : getImsi().hashCode());
        result = prime * result + ((getMobileNum() == null) ? 0 : getMobileNum().hashCode());
        result = prime * result + ((getImei() == null) ? 0 : getImei().hashCode());
        result = prime * result + ((getMac() == null) ? 0 : getMac().hashCode());
        return result;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DeviceInfo [imsi=" + imsi + ", mobileNum=" + mobileNum
				+ ", imei=" + imei + ", mac=" + mac + "]";
	}
	
	/**
	 * 获得imei和mac的组合
	 * @return
	 */
	public String getIMEIMac(){
		if(StringUtils.isNotBlank(imei)&&StringUtils.isNotBlank(mac)){
			return imei+"_"+mac;
		}
		return null;
	}
	
	
}
