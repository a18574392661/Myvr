package com.bootdo.vrs.domain;

import java.io.Serializable;
import java.math.BigDecimal;



/**
 * 支付介绍详情价格表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-13 10:19:09
 */
public class PaydetailesDO implements Serializable {
	private static final long serialVersionUID = 1L;

	//
	private Integer id;
	//
	private String texts;
	//
	private BigDecimal price;
	//支付状态 开启1支付宝支付
	private Integer paystate;

	//支付状态 开启1微信
	private Integer paywxstate;
	//标题
	private String title;

	private  Integer count;

	private String usertext;
	private  String vipusertext;

	private  String[] vips;

	public String getUsertext() {
		return usertext;
	}

	public void setUsertext(String usertext) {
		this.usertext = usertext;
	}

	public String getVipusertext() {
		return vipusertext;
	}

	public void setVipusertext(String vipusertext) {
		this.vipusertext = vipusertext;
	}

	public String[] getVips() {
		return vips;
	}

	public void setVips(String[] vips) {
		this.vips = vips;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Integer getPaywxstate() {
		return paywxstate;
	}

	public void setPaywxstate(Integer paywxstate) {
		this.paywxstate = paywxstate;
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
	 * 设置：支付状态 1 支付宝 2扫码支付
	 */
	public void setPaystate(Integer paystate) {
		this.paystate = paystate;
	}
	/**
	 * 获取：支付状态 1 支付宝 2扫码支付
	 */
	public Integer getPaystate() {
		return paystate;
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
}
