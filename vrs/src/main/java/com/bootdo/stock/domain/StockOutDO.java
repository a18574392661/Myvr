package com.bootdo.stock.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;



/**
 * 库存管理 - 商品出库(卖出)表
 * 
 * @author shilic
 * @email chensl09@139.com
 * @date 2019-11-01 20:14:39
 */
public class StockOutDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//出库订单编号
	private String ordercode;
	//商品id
	private Integer goodsId;
	//出库数量
	private Integer num;
	//出库单价
	private BigDecimal price;
	//出库时间
	private Date createTime;
	//出库备注
	private String remarks;

	private String goodsName;
	
	private String typePath;
	
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
	 * 设置：出库订单编号
	 */
	public void setOrdercode(String ordercode) {
		this.ordercode = ordercode;
	}
	/**
	 * 获取：出库订单编号
	 */
	public String getOrdercode() {
		return ordercode;
	}
	/**
	 * 设置：商品id
	 */
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	/**
	 * 获取：商品id
	 */
	public Integer getGoodsId() {
		return goodsId;
	}
	/**
	 * 设置：出库数量
	 */
	public void setNum(Integer num) {
		this.num = num;
	}
	/**
	 * 获取：出库数量
	 */
	public Integer getNum() {
		return num;
	}
	/**
	 * 设置：出库单价
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	/**
	 * 获取：出库单价
	 */
	public BigDecimal getPrice() {
		return price;
	}
	/**
	 * 设置：出库时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：出库时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：出库备注
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/**
	 * 获取：出库备注
	 */
	public String getRemarks() {
		return remarks;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getTypePath() {
		return typePath;
	}
	public void setTypePath(String typePath) {
		this.typePath = typePath;
	}
}
