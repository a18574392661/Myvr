package com.bootdo.school.domain;

import com.bootdo.system.domain.UserDO;
import org.springframework.data.annotation.Transient;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-08 16:24:33
 */
public class UserCouponLogDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//用户id 消费的记录(优惠券)
	private Long uid;
	//
	private BigDecimal price;
	//消费的时间
	private Date createdate;

	private UserDO userDO;
	
	@Transient
	private String cname;
	@Transient
	private String cprice;


	//支付状态
	private  Integer payStatus;

	//商家id
	private  Integer busid;
	//一对一商家
	private  BusinessDO businessDO;
	@Transient
	private  String uname;
	@Transient
	private  String bname;
	@Transient
	private  String mobile;

	private  Date quedinDate;



	public Date getQuedinDate() {
		return quedinDate;
	}

	public void setQuedinDate(Date quedinDate) {
		this.quedinDate = quedinDate;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public BusinessDO getBusinessDO() {
		return businessDO;
	}

	public void setBusinessDO(BusinessDO businessDO) {
		this.businessDO = businessDO;
	}

	public Integer getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}

	public Integer getBusid() {
		return busid;
	}

	public void setBusid(Integer busid) {
		this.busid = busid;
	}

	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCprice() {
		return cprice;
	}
	public void setCprice(String cprice) {
		this.cprice = cprice;
	}
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
	 * 设置：用户id 消费的记录(优惠券)
	 */
	public void setUid(Long uid) {
		this.uid = uid;
	}
	/**
	 * 获取：用户id 消费的记录(优惠券)
	 */
	public Long getUid() {
		return uid;
	}
	/**
	 * 设置：
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	/**
	 * 获取：
	 */
	public BigDecimal getPrice() {
		return price;
	}
	/**
	 * 设置：消费的时间
	 */
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	/**
	 * 获取：消费的时间
	 */
	public Date getCreatedate() {
		return createdate;
	}
}
