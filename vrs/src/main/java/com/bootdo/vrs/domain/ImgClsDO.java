package com.bootdo.vrs.domain;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-11 21:32:31
 */
public class ImgClsDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Override
	public String toString() {
		return "ImgClsDO [id=" + id + ", name=" + name + ", title=" + title + ", createdate=" + createdate + ", status="
				+ status + ", updatedate=" + updatedate + ", img=" + img + ", pid=" + pid + ", cid=" + cid + ", childs="
				+ childs + ", titleClsDO=" + titleClsDO + ", checked=" + checked + ", chisClsids="
				+ Arrays.toString(chisClsids) + ", heightColor=" + heightColor + "]";
	}
	//
	private Long id;
	//
	private String name;
	//
	private String title;
	//
	private Date createdate;
	//0 删除 1正常
	private String status;
	//
	private Date updatedate;

	private  String img;
	private  Integer pid;

	private  Integer cid;

	private List<ImgClsDO> childs;

	//所属标签
	private  TitleClsDO titleClsDO;

	//是否选中
	private boolean checked;



	//子分类数组1
	private  String[] chisClsids;

	private  String heightColor;

	private  Integer radios;
	private  String[] names;

	private  ImgClsDO imgClsDO;


	private  String pname;


	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public ImgClsDO getImgClsDO() {
		return imgClsDO;
	}

	public void setImgClsDO(ImgClsDO imgClsDO) {
		this.imgClsDO = imgClsDO;
	}

	public Integer getRadios() {
		return radios;
	}

	public void setRadios(Integer radios) {
		this.radios = radios;
	}

	public String[] getNames() {
		return names;
	}

	public void setNames(String[] names) {
		this.names = names;
	}

	public String getHeightColor() {
		return heightColor;
	}

	public void setHeightColor(String heightColor) {
		this.heightColor = heightColor;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public String[] getChisClsids() {
		return chisClsids;
	}

	public void setChisClsids(String[] chisClsids) {
		this.chisClsids = chisClsids;
	}

	public TitleClsDO getTitleClsDO() {
		return titleClsDO;
	}

	public void setTitleClsDO(TitleClsDO titleClsDO) {
		this.titleClsDO = titleClsDO;
	}

	public List<ImgClsDO> getChilds() {
		return childs;
	}

	public void setChilds(List<ImgClsDO> childs) {
		this.childs = childs;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
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
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：
	 */
	public String getTitle() {
		return title;
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
	 * 设置：0 删除 1正常
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * 获取：0 删除 1正常
	 */
	public String getStatus() {
		return status;
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
}
