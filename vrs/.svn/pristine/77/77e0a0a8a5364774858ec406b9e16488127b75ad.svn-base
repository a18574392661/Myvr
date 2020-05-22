package com.bootdo.vr.dao;

import com.bootdo.vr.domain.LeftStyleImgsDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 关联小风格的热点图表
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-08-11 20:10:52
 */
@Mapper
public interface LeftStyleImgsDao {

	LeftStyleImgsDO get(Integer id);
	
	List<LeftStyleImgsDO> list(Map<String,Object> map);
	
	int saveBatch(Map<String,Object> map);
	
	int removeByLeftId(Integer leftId);
	
	int count(Map<String,Object> map);
	
	int save(LeftStyleImgsDO leftStyleImgs);
	
	int update(LeftStyleImgsDO leftStyleImgs);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
