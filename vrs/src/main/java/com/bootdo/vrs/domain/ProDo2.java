package com.bootdo.vrs.domain;

import java.math.BigDecimal;
import java.util.Date;

public class ProDo2 {

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


private  String context;

    private  String code;

    private  String simg;

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

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPricce() {
        return pricce;
    }

    public void setPricce(BigDecimal pricce) {
        this.pricce = pricce;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
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
}
