package com.bootdo.vrs.domain;

import java.io.Serializable;
import java.math.BigDecimal;



/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-25 16:50:07
 */
public class PayvipDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//开通月份
	private Integer month;
	//对应价格
	private BigDecimal price;
	//标注
	private String remker;
	//对应会员表id
	private Integer vipid;
	//0 关闭 1开启 
	private Integer status;

	private boolean checked;


	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
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
	 * 设置：开通月份
	 */
	public void setMonth(Integer month) {
		this.month = month;
	}
	/**
	 * 获取：开通月份
	 */
	public Integer getMonth() {
		return month;
	}
	/**
	 * 设置：对应价格
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	/**
	 * 获取：对应价格
	 */
	public BigDecimal getPrice() {
		return price;
	}
	/**
	 * 设置：标注
	 */
	public void setRemker(String remker) {
		this.remker = remker;
	}
	/**
	 * 获取：标注
	 */
	public String getRemker() {
		return remker;
	}
	/**
	 * 设置：对应会员表id
	 */
	public void setVipid(Integer vipid) {
		this.vipid = vipid;
	}
	/**
	 * 获取：对应会员表id
	 */
	public Integer getVipid() {
		return vipid;
	}
	/**
	 * 设置：0 关闭 1开启 
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：0 关闭 1开启 
	 */
	public Integer getStatus() {
		return status;
	}
}
