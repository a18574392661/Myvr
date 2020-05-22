package com.bootdo.vr.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 关联小风格的热点图表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-08-11 20:10:52
 */
public class LeftStyleImgsDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private Integer leftId;
	//热点图片地址
	private String img;

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
	public void setLeftId(Integer leftId) {
		this.leftId = leftId;
	}
	/**
	 * 获取：
	 */
	public Integer getLeftId() {
		return leftId;
	}
	/**
	 * 设置：热点图片地址
	 */
	public void setImg(String img) {
		this.img = img;
	}
	/**
	 * 获取：热点图片地址
	 */
	public String getImg() {
		return img;
	}
}
