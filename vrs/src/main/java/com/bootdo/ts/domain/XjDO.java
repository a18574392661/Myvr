package com.bootdo.ts.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 选择第几节课
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-05-13 14:13:54
 */
public class XjDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//第几节课
	private String name;

	private List<Map<String,Object>> list1;

	private  String bs;

	public String getBs() {
		return bs;
	}

	public void setBs(String bs) {
		this.bs = bs;
	}

	public List<Map<String, Object>> getList1() {
		return list1;
	}

	public void setList1(List<Map<String, Object>> list1) {
		this.list1 = list1;
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
	 * 设置：第几节课
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：第几节课
	 */
	public String getName() {
		return name;
	}
}
