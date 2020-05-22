package com.bootdo.school.domain;

import com.bootdo.system.domain.UserDO;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-08 13:58:02
 */
public class UserCouponDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//优惠券码
	private String code;
	//用户id(一对一用户的优惠券)
	private Long uid;
	//优惠券价格(可以分配)
	private BigDecimal price;
	//
	private String name;
	//
	private Date createdate;
	//修改时间
	private Date updatedate;
	//1 正常 0 删除
	private Integer status;

	private UserDO userDO;
	



	
	
	public UserDO getUserDO() {
		return userDO;
	}
	public void setUserDO(UserDO userDO) {
		this.userDO = userDO;
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
	 * 设置：优惠券码
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取：优惠券码
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 设置：用户id(一对一用户的优惠券)
	 */
	public void setUid(Long uid) {
		this.uid = uid;
	}
	/**
	 * 获取：用户id(一对一用户的优惠券)
	 */
	public Long getUid() {
		return uid;
	}
	/**
	 * 设置：优惠券价格(可以分配)
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	/**
	 * 获取：优惠券价格(可以分配)
	 */
	public BigDecimal getPrice() {
		return price;
	}
	/**
	 * 设置：
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：
	 */
	public String getName() {
		return name;
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
	/**
	 * 设置：修改时间
	 */
	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}
	/**
	 * 获取：修改时间
	 */
	public Date getUpdatedate() {
		return updatedate;
	}
	/**
	 * 设置：1 正常 0 删除
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：1 正常 0 删除
	 */
	public Integer getStatus() {
		return status;
	}
}
