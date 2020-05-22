package com.bootdo.vrs.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 色卡对应全景图
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-29 18:09:16
 */
public class ColorImgclsDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//
	private Long proid;
	//
	private String colorimg;
	//
	private String img;
	//
	private Long sort;
	//
	private Date updatedate;


	private  String qjt;

	public String getQjt() {
		return qjt;
	}

	public void setQjt(String qjt) {
		this.qjt = qjt;
	}

	/**
	 * 设置：
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：
	 */
	public void setProid(Long proid) {
		this.proid = proid;
	}
	/**
	 * 获取：
	 */
	public Long getProid() {
		return proid;
	}
	/**
	 * 设置：
	 */
	public void setColorimg(String colorimg) {
		this.colorimg = colorimg;
	}
	/**
	 * 获取：
	 */
	public String getColorimg() {
		return colorimg;
	}
	/**
	 * 设置：
	 */
	public void setImg(String img) {
		this.img = img;
	}
	/**
	 * 获取：
	 */
	public String getImg() {
		return img;
	}
	/**
	 * 设置：
	 */
	public void setSort(Long sort) {
		this.sort = sort;
	}
	/**
	 * 获取：
	 */
	public Long getSort() {
		return sort;
	}
	/**
	 * 设置：
	 */
	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}
	/**
	 * 获取：
	 */
	public Date getUpdatedate() {
		return updatedate;
	}
}
