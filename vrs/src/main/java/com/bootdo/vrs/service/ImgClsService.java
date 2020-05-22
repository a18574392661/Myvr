package com.bootdo.vrs.service;

import com.bootdo.vrs.domain.ImgClsDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-11 21:32:31
 */
public interface ImgClsService {
	
	ImgClsDO get(Long id);
	
	List<ImgClsDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ImgClsDO imgCls);
	
	int update(ImgClsDO imgCls);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
	List<ImgClsDO> imgClsPars(String pid);

	String[] queryProCeebnCls(Integer id);

	List<Map<String, Object>> getAllCategory(Map<String,String> param);
	
	List<Map<String, Object>> getAllCategorySub(Map<String, String> param);
	
	List<Map<String,Object>> qeuryCategoryByProId(Integer id);

	List<ImgClsDO> getThreeImgClsDo();

}
