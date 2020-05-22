package com.bootdo.vr.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 产中选中的热点图表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-08-11 20:10:53
 */
public class ProductLeftStyleImgsDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private Integer productId;
	//
	private Integer leftStyleImgsId;

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
	 * 设置：
	 */
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	/**
	 * 获取：
	 */
	public Integer getProductId() {
		return productId;
	}
	/**
	 * 设置：
	 */
	public void setLeftStyleImgsId(Integer leftStyleImgsId) {
		this.leftStyleImgsId = leftStyleImgsId;
	}
	/**
	 * 获取：
	 */
	public Integer getLeftStyleImgsId() {
		return leftStyleImgsId;
	}
}
