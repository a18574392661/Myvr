package com.bootdo.article.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;



/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-11 19:47:36
 */
public class ArticleDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//编号
	private Integer id;
	//标题
	private String title;
	//内容
	private String context;
	//创建时间
	private Date createTime;
	//修改时间
	private Date updateTime;
	//二维码URL
	private String qrcodeUrl;
	//状态
	private Integer state;
	//
	private Integer categoryId;
	//
	private String img;
	//
	private String bz;
	
	//一对一
	private String categoryname;
	
	

	public String getCategoryname() {
		return categoryname;
	}
	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}
	/**
	 * 设置：编号
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：编号
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：标题
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：内容
	 */
	public void setContext(String context) {
		this.context = context;
	}
	/**
	 * 获取：内容
	 */
	public String getContext() {
		return context;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：修改时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：修改时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * 设置：二维码URL
	 */
	public void setQrcodeUrl(String qrcodeUrl) {
		this.qrcodeUrl = qrcodeUrl;
	}
	/**
	 * 获取：二维码URL
	 */
	public String getQrcodeUrl() {
		return qrcodeUrl;
	}
	/**
	 * 设置：状态
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	/**
	 * 获取：状态
	 */
	public Integer getState() {
		return state;
	}
	/**
	 * 设置：
	 */
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	/**
	 * 获取：
	 */
	public Integer getCategoryId() {
		return categoryId;
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
	public void setBz(String bz) {
		this.bz = bz;
	}
	/**
	 * 获取：
	 */
	public String getBz() {
		return bz;
	}
	
	@Override
	public String toString() {
		return "ArticleDO [id=" + id + ", title=" + title + ", context=" + context + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", qrcodeUrl=" + qrcodeUrl + ", state=" + state + ", categoryId="
				+ categoryId + ", img=" + img + ", bz=" + bz + ", categoryname=" + categoryname + "]";
	}
	
}
