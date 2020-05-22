package com.bootdo.vrs.service;

import com.bootdo.vrs.domain.LogImgsDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-18 16:09:22
 */
public interface LogImgsService {
	
	LogImgsDO get(Integer id);
	
	List<LogImgsDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(LogImgsDO logImgs);
	
	int update(LogImgsDO logImgs);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	List<LogImgsDO> queryLimitLog();
}
