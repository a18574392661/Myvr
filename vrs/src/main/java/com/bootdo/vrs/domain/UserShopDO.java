package com.bootdo.vrs.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 用户加入图片购物车表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-21 22:19:09
 */
public class UserShopDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//昵称
	private String name;
	//
	private Integer proid;
	//
	private Integer uid;
	//
	private Integer count;
	//
	private Date createdate;

	private  ProDO proDO;

	private  UserDO userDO;

	public UserDO getUserDO() {
		return userDO;
	}

	public void setUserDO(UserDO userDO) {
		this.userDO = userDO;
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
	 * 设置：昵称
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：昵称
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：
	 */
	public void setProid(Integer proid) {
		this.proid = proid;
	}
	/**
	 * 获取：
	 */
	public Integer getProid() {
		return proid;
	}
	/**
	 * 设置：
	 */
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	/**
	 * 获取：
	 */
	public Integer getUid() {
		return uid;
	}
	/**
	 * 设置：
	 */
	public void setCount(Integer count) {
		this.count = count;
	}
	/**
	 * 获取：
	 */
	public Integer getCount() {
		return count;
	}
	/**
	 * 设置：
	 */
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	/**
	 * 获取：
	 */
	public Date getCreatedate() {
		return createdate;
	}
}
