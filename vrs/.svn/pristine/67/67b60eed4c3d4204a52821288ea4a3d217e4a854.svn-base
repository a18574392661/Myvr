package com.bootdo.vrs.service;

import com.bootdo.vrs.domain.TitleClsDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-13 14:20:14
 */
public interface TitleClsService {
	
	TitleClsDO get(Integer id);
	
	List<TitleClsDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TitleClsDO titleCls);
	
	int update(TitleClsDO titleCls);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);


	List<TitleClsDO> titleAll(String pid);

	TitleClsDO queryTypeId(Integer typeid);

	List<TitleClsDO> queryListType(Map<String,Object> map);

	List<TitleClsDO> querylist(Map map);

}
