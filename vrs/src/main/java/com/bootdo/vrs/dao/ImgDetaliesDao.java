package com.bootdo.vrs.dao;

import com.bootdo.vrs.domain.ImgDetaliesDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-12 13:25:04
 */
@Mapper
public interface ImgDetaliesDao {

	ImgDetaliesDO get(Long id);
	
	List<ImgDetaliesDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ImgDetaliesDO imgDetalies);
	
	int update(ImgDetaliesDO imgDetalies);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
