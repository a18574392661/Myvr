package com.bootdo.ts.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-05-13 13:47:40
 */
public class ZwDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//座位id
	private Integer id;
	//座位编号
	private String code;
	//1 开启 0关闭
	private Integer status;
	//对应的课程id
	private Long cid;

	private  Integer checked;

	private  String uname;

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}


	public Integer getChecked() {
		return checked;
	}

	public void setChecked(Integer checked) {
		this.checked = checked;
	}

	/**
	 * 设置：座位id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：座位id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：座位编号
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取：座位编号
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 设置：1 开启 0关闭
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：1 开启 0关闭
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：对应的课程id
	 */
	public void setCid(Long cid) {
		this.cid = cid;
	}
	/**
	 * 获取：对应的课程id
	 */
	public Long getCid() {
		return cid;
	}
}
