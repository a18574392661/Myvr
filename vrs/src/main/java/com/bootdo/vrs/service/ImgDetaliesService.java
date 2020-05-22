package com.bootdo.vrs.service;

import com.bootdo.vrs.domain.ImgDetaliesDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-12 13:25:04
 */
public interface ImgDetaliesService {
	
	ImgDetaliesDO get(Long id);
	
	List<ImgDetaliesDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ImgDetaliesDO imgDetalies);
	
	int update(ImgDetaliesDO imgDetalies);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
