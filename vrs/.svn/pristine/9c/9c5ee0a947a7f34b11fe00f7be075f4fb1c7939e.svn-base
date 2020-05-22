package com.bootdo.vrs.service;

import com.bootdo.common.domain.Tree;
import com.bootdo.vrs.domain.UserDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-21 09:51:24
 */
public interface UserAllotService {
	
	UserDO get(Long userId);
	
	List<UserDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(UserDO user);
	
	int update(UserDO user);
	
	int remove(Long userId);
	
	int batchRemove(Long[] userIds);

	List<UserDO> saveMyUserName(Map<String,Object> paremts);

	Tree<UserDO> userTree(Long uid);

	Map getUserStatus(long uid);

	long getVipUserStatus(Long uid);

	int editUserVip();
}
