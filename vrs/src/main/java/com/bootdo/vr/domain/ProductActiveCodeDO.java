package com.bootdo.vr.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 产品套餐激活码表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-04-28 19:05:06
 */
public class ProductActiveCodeDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final Integer status_normal = 0;//未使用
	public static final Integer status_used = 1;//已使用
	
	//
	private Integer id;
	//产品id
	private Integer productId;
	//激活码
	private String activeCode;
	//激活码生成时间
	private Date createTime;
	//使用者设备号
	private String deviceCode;
	//第一次使用时间（激活时间）
	private Date activeTime;
	//使用状态 0-未使用 1-已使用
	private Integer status;
	
	private String productName;//产品名称

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：产品id
	 */
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	/**
	 * 获取：产品id
	 */
	public Integer getProductId() {
		return productId;
	}
	/**
	 * 设置：激活码
	 */
	public void setActiveCode(String activeCode) {
		this.activeCode = activeCode;
	}
	/**
	 * 获取：激活码
	 */
	public String getActiveCode() {
		return activeCode;
	}
	/**
	 * 设置：激活码生成时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：激活码生成时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：使用者设备号
	 */
	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}
	/**
	 * 获取：使用者设备号
	 */
	public String getDeviceCode() {
		return deviceCode;
	}
	/**
	 * 设置：第一次使用时间（激活时间）
	 */
	public void setActiveTime(Date activeTime) {
		this.activeTime = activeTime;
	}
	/**
	 * 获取：第一次使用时间（激活时间）
	 */
	public Date getActiveTime() {
		return activeTime;
	}
	/**
	 * 设置：使用状态 0-未使用 1-已使用
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：使用状态 0-未使用 1-已使用
	 */
	public Integer getStatus() {
		return status;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
}
