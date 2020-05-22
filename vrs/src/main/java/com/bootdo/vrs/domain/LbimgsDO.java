package com.bootdo.vrs.domain;

import java.io.Serializable;



/**
 * 轮播图表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-13 11:12:36
 */
public class LbimgsDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//轮播图标题
	private String title;
	//
	private String img;
	//
	private String texts;
	//排序
	private Integer sort;

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
	 * 设置：轮播图标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：轮播图标题
	 */
	public String getTitle() {
		return title;
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
	public void setTexts(String texts) {
		this.texts = texts;
	}
	/**
	 * 获取：
	 */
	public String getTexts() {
		return texts;
	}
	/**
	 * 设置：排序
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	/**
	 * 获取：排序
	 */
	public Integer getSort() {
		return sort;
	}
}
