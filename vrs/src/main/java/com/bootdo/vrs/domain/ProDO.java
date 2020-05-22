package com.bootdo.vrs.domain;

import org.springframework.data.annotation.Transient;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 海报价格图
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-13 10:19:40
 */
public class ProDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Integer id;
	//
	private String name;
	//
	private BigDecimal pricce;
	//
	private Long count;
	//
	private Date createdate;
	//0 删除 1 显示
	private Integer status;
	//海报图片详细情况
	private String img;
	//二级分类id
	private Integer cid;
	//区分字典id
	private Integer typeid;



	private  String[] childs;

	private  String code;

	private  String simg;

	private  String[] tids;


	private  Integer payState;

	private  String qjtfile;

	private  Long uid;

	private  String  context;



	private  String uname;
	private  String src;

	private  String sum;


	public String getSum() {
		return sum;
	}

	public void setSum(String sum) {
		this.sum = sum;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}





	@Transient
	private  Integer cid2;

	@Transient
	private  Integer cid3;

	private List<TitleClsDO> ts;

	//批量上传图片
	@Transient
	private String[] imgs;

	private  List<Map<String,Object>> imgDolist;

	public Integer getCid3() {
		return cid3;
	}

	public void setCid3(Integer cid3) {
		this.cid3 = cid3;
	}

	public String getQjtfile() {
		return qjtfile;
	}

	public void setQjtfile(String qjtfile) {
		this.qjtfile = qjtfile;
	}

	private  String cname;


	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String[] getImgs() {
		return imgs;
	}

	public void setImgs(String[] imgs) {
		this.imgs = imgs;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public List<Map<String, Object>> getImgDolist() {
		return imgDolist;
	}

	public void setImgDolist(List<Map<String, Object>> imgDolist) {
		this.imgDolist = imgDolist;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}


	/*private  List<ImgclsImgsDO> imgsDOList;

	public List<ImgclsImgsDO> getImgsDOList() {
		return imgsDOList;
	}

	public void setImgsDOList(List<ImgclsImgsDO> imgsDOList) {
		this.imgsDOList = imgsDOList;
	}*/

	public Integer getPayState() {
		return payState;
	}

	public void setPayState(Integer payState) {
		this.payState = payState;
	}

	public List<TitleClsDO> getTs() {
		return ts;
	}

	public void setTs(List<TitleClsDO> ts) {
		this.ts = ts;
	}

	public Integer getCid2() {
		return cid2;
	}

	public void setCid2(Integer cid2) {
		this.cid2 = cid2;
	}

	public String[] getTids() {
		return tids;
	}

	public void setTids(String[] tids) {
		this.tids = tids;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSimg() {
		return simg;
	}

	public void setSimg(String simg) {
		this.simg = simg;
	}

	public String[] getChilds() {
		return childs;
	}

	public void setChilds(String[] childs) {
		this.childs = childs;
	}
	

	@Override
	public String toString() {
		return "ProDO [id=" + id + ", name=" + name + ", pricce=" + pricce + ", count=" + count + ", createdate="
				+ createdate + ", status=" + status + ", img=" + img + ", cid=" + cid + ", typeid=" + typeid
				+ ", childs=" + Arrays.toString(childs) + ", titleClsDO=" + titleClsDO + ", imgClsDO=" + imgClsDO
				+ ", imgCls2DO=" + imgCls2DO + ", imgCls3DO=" + imgCls3DO + ", imgCls4DO=" + imgCls4DO + "]";
	}

	//一个分类
	private  TitleClsDO titleClsDO;

	//链接每个分类
	private  ImgClsDO   imgClsDO;
	private  ImgCls2DO  imgCls2DO;
	private  ImgCls3DO  imgCls3DO;
	private  ImgCls4DO  imgCls4DO;


	//排序
	private  Integer sort;

	//修改时间
	private  Date updateDate;




	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public TitleClsDO getTitleClsDO() {
		return titleClsDO;
	}

	public void setTitleClsDO(TitleClsDO titleClsDO) {
		this.titleClsDO = titleClsDO;
	}

	public ImgClsDO getImgClsDO() {
		return imgClsDO;
	}

	public void setImgClsDO(ImgClsDO imgClsDO) {
		this.imgClsDO = imgClsDO;
	}

	public ImgCls2DO getImgCls2DO() {
		return imgCls2DO;
	}

	public void setImgCls2DO(ImgCls2DO imgCls2DO) {
		this.imgCls2DO = imgCls2DO;
	}

	public ImgCls3DO getImgCls3DO() {
		return imgCls3DO;
	}

	public void setImgCls3DO(ImgCls3DO imgCls3DO) {
		this.imgCls3DO = imgCls3DO;
	}

	public ImgCls4DO getImgCls4DO() {
		return imgCls4DO;
	}

	public void setImgCls4DO(ImgCls4DO imgCls4DO) {
		this.imgCls4DO = imgCls4DO;
	}

	public Integer getTypeid() {
		return typeid;
	}

	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
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
	public void setPricce(BigDecimal pricce) {
		this.pricce = pricce;
	}
	/**
	 * 获取：
	 */
	public BigDecimal getPricce() {
		return pricce;
	}
	/**
	 * 设置：
	 */
	public void setCount(Long count) {
		this.count = count;
	}
	/**
	 * 获取：
	 */
	public Long getCount() {
		return count;
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
	 * 设置：0 删除 1 显示
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：0 删除 1 显示
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：海报图片详细情况
	 */
	public void setImg(String img) {
		this.img = img;
	}
	/**
	 * 获取：海报图片详细情况
	 */
	public String getImg() {
		return img;
	}
	/**
	 * 设置：二级分类id
	 */
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	/**
	 * 获取：二级分类id
	 */
	public Integer getCid() {
		return cid;
	}
}
