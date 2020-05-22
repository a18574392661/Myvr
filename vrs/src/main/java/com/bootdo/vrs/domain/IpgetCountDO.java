package com.bootdo.vrs.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 图片浏览量
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-05-21 11:56:29
 */
public class IpgetCountDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//
	private Long proid;
	//
	private Long count;
	//
	private String ip;

	private  ProDO proDO;

	private  String pname;
	private  String code;
	private  String simg;

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSimg() {
		return simg;
	}

	public void setSimg(String simg) {
		this.simg = simg;
	}

	public ProDO getProDO() {
		return proDO;
	}

	public void setProDO(ProDO proDO) {
		this.proDO = proDO;
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
	public void setCount(Long count) {
		this.count = count;
	}
	/**
	 * 获取：
	 */
	public Long getCount() {
		return count;
	}
	/**
	 * 设置：
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	/**
	 * 获取：
	 */
	public String getIp() {
		return ip;
	}
}
