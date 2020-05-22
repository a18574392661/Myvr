package com.bootdo.ts.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 添加课程表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-05-13 13:45:22
 */
public class CurrDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private String cname;
	//1 启动 2关闭
	private Integer status;

	private String img;


	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

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
	public void setCname(String cname) {
		this.cname = cname;
	}
	/**
	 * 获取：
	 */
	public String getCname() {
		return cname;
	}
	/**
	 * 设置：1 启动 2关闭
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：1 启动 2关闭
	 */
	public Integer getStatus() {
		return status;
	}
}
