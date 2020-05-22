package com.bootdo.vr.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-08-11 17:21:44
 */
public class ProductImgsDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//产品id
	private Integer productId;
	//热点图id
	private Integer roomTypeImgsId;

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
	 * 设置：热点图id
	 */
	public void setRoomTypeImgsId(Integer roomTypeImgsId) {
		this.roomTypeImgsId = roomTypeImgsId;
	}
	/**
	 * 获取：热点图id
	 */
	public Integer getRoomTypeImgsId() {
		return roomTypeImgsId;
	}
}
