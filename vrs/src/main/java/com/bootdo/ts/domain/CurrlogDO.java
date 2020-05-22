package com.bootdo.ts.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;



/**
 * 添加课程表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-05-13 13:46:13
 */
public class CurrlogDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//课程id
	private Integer cid;
	//节数id
	private Integer ji;
	//哪天
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date daydate;
	//课程开始时间
	private Date startdate;
	//课程结束时间
	private Date enddate;

	private  String cname;

	private  String jname;

	private String uname;

	private  Integer checked;

	private  ZwDO zwDO;


	public ZwDO getZwDO() {
		return zwDO;
	}

	public void setZwDO(ZwDO zwDO) {
		this.zwDO = zwDO;
	}

	public Integer getChecked() {
		return checked;
	}

	public void setChecked(Integer checked) {
		this.checked = checked;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	//判断日期
	private  String bs;

	public String getBs() {
		return bs;
	}

	public void setBs(String bs) {
		this.bs = bs;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getJname() {
		return jname;
	}

	public void setJname(String jname) {
		this.jname = jname;
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
	 * 设置：课程id
	 */
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	/**
	 * 获取：课程id
	 */
	public Integer getCid() {
		return cid;
	}
	/**
	 * 设置：节数id
	 */
	public void setJi(Integer ji) {
		this.ji = ji;
	}
	/**
	 * 获取：节数id
	 */
	public Integer getJi() {
		return ji;
	}
	/**
	 * 设置：哪天
	 */
	public void setDaydate(Date daydate) {
		this.daydate = daydate;
	}
	/**
	 * 获取：哪天
	 */
	public Date getDaydate() {
		return daydate;
	}
	/**
	 * 设置：课程开始时间
	 */
	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}
	/**
	 * 获取：课程开始时间
	 */
	public Date getStartdate() {
		return startdate;
	}
	/**
	 * 设置：课程结束时间
	 */
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	/**
	 * 获取：课程结束时间
	 */
	public Date getEnddate() {
		return enddate;
	}
}
