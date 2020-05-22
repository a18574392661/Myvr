package com.bootdo.vrs.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-26 10:25:17
 */
public class PayorderLogDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//订单编号
	private String ordercode;
	//支付金额
	private BigDecimal price;
	//核销后的金额
	private BigDecimal hprice;
	//支付状态
	private Integer status;
	//支付备注
	private String remark;
	//1 微信 2支付宝
	private Integer paysta;
	//用户id
	private Long uid;
	//创建订单时间
	private Date createdate;

	private String tradeNo;
	private  UserDO userDO;

	private Integer month;

	private  Date payDate;
	private  String context;

	private  String tradeno;

	private  String bw;




	public String getBw() {
		return bw;
	}

	public void setBw(String bw) {
		this.bw = bw;
	}

	public String getTradeno() {
		return tradeno;
	}

	public void setTradeno(String tradeno) {
		this.tradeno = tradeno;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrdercode() {
		return ordercode;
	}

	public void setOrdercode(String ordercode) {
		this.ordercode = ordercode;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getHprice() {
		return hprice;
	}

	public void setHprice(BigDecimal hprice) {
		this.hprice = hprice;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getPaysta() {
		return paysta;
	}

	public void setPaysta(Integer paysta) {
		this.paysta = paysta;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public UserDO getUserDO() {
		return userDO;
	}

	public void setUserDO(UserDO userDO) {
		this.userDO = userDO;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}
}
