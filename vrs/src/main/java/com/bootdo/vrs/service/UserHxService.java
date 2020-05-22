package com.bootdo.vrs.service;

import com.bootdo.vrs.domain.UserHxDO;

import java.util.List;
import java.util.Map;

/**
 * 角色核销提成设置表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-24 14:04:40
 */
public interface UserHxService {
	
	UserHxDO get(Integer id);
	
	List<UserHxDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(UserHxDO userHx);
	
	int update(UserHxDO userHx);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);
}
