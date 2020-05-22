package com.bootdo.school.domain;

import com.bootdo.system.domain.UserDO;

import java.io.Serializable;
import java.util.Date;



/**
 * 商家表 封面图 经度纬度 富文本详细信息 等
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-07 20:19:47
 */
public class BusinessDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//详细地址
	private String name;
	//商家地址
	private String addres;
	//创建时间
	private Date createdate;
	//
	private Date updatedate;
	//经度
	private Double longitude;
	//纬度
	private Double latitude;
	//0 删除 1正常
	private Integer status;
	//商家介绍
	private String detailed;
	//商家首页图
	private String img;

	private  Long uid;

	//游离态 添加到用户手机号码
	private  String tel;

	private UserDO userDO;

	public UserDO getUserDO() {
		return userDO;
	}

	public void setUserDO(UserDO userDO) {
		this.userDO = userDO;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public void setUid(Long uid) {

		this.uid = uid;
	}
	/**
	 * 获取：
	 */
	public Long getUid() {

		return uid;
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
	 * 设置：详细地址
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：详细地址
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：商家地址
	 */
	public void setAddres(String addres) {
		this.addres = addres;
	}
	/**
	 * 获取：商家地址
	 */
	public String getAddres() {
		return addres;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreatedate() {
		return createdate;
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
	/**
	 * 设置：经度
	 */
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	/**
	 * 获取：经度
	 */
	public Double getLongitude() {
		return longitude;
	}
	/**
	 * 设置：纬度
	 */
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	/**
	 * 获取：纬度
	 */
	public Double getLatitude() {
		return latitude;
	}
	/**
	 * 设置：0 删除 1正常
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：0 删除 1正常
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：商家介绍
	 */
	public void setDetailed(String detailed) {
		this.detailed = detailed;
	}
	/**
	 * 获取：商家介绍
	 */
	public String getDetailed() {
		return detailed;
	}
	/**
	 * 设置：商家首页图
	 */
	public void setImg(String img) {
		this.img = img;
	}
	/**
	 * 获取：商家首页图
	 */
	public String getImg() {
		return img;
	}
}
