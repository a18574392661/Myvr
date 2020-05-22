package com.bootdo.vrs.dao;

import com.bootdo.vrs.domain.ImgCls2DO;
import com.bootdo.vrs.domain.ImgClsDO;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 第二个分类表
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-14 09:53:32
 */
@Mapper
public interface ImgCls2Dao {

	ImgCls2DO get(Integer id);
	
	List<ImgCls2DO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ImgCls2DO imgCls2);
	
	int update(ImgCls2DO imgCls2);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
	
	ImgCls2DO getCategory(Integer id);


	@Select("select * from ${tableName} where status!=0 ")
	List<Map<String,Object>> queryTableCls(@Param("tableName") String tableName);


	List<Map<String, String>> getMenu();
	
	List<Map<String, String>> contains(Map<String, Object> rootMap);

	//分类查询
	@Select("select * from vrs_img_cls2 where pid = 0 order by sort,updateDate desc limit ${offset},${limit}")
	List<ImgCls2DO> listTopThree(Map<String, Object> params);

	@Select("select * from vrs_img_cls2 where pid=#{pid}")
	List<ImgClsDO> imgClsPars(String pid);

	@Select("select * from vrs_img_cls2 where pid=#{pid} and status!=0")
	List<ImgCls2DO> queryParentCls(@Param("pid") String pid);

	@Select("select * from vrs_img_cls2 where pid!=0 and status!=0")
	List<ImgCls2DO> querynotParentCls();
}
