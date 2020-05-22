package com.bootdo.vrs.service;

import com.bootdo.vrs.domain.LbimgsDO;

import java.util.List;
import java.util.Map;

/**
 * 轮播图表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-13 11:12:36
 */
public interface LbimgsService {
	
	LbimgsDO get(Integer id);
	
	List<LbimgsDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(LbimgsDO lbimgs);
	
	int update(LbimgsDO lbimgs);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
