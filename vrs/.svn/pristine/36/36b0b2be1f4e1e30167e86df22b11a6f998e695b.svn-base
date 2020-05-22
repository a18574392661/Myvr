package com.bootdo.vrs.service;

import com.bootdo.vrs.domain.ImgsSytDO;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * 缩月图标
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-18 09:59:38
 */
public interface ImgsSytService {
	
	ImgsSytDO get(Integer id);
	
	List<ImgsSytDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ImgsSytDO imgsSyt);
	
	int update(ImgsSytDO imgsSyt);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids,Integer cid);


	PageInfo<ImgsSytDO> querySyImg(Integer proid,Integer size,Integer page);
}
