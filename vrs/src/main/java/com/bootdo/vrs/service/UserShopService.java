package com.bootdo.vrs.service;

import com.bootdo.vrs.domain.UserShopDO;

import java.util.List;
import java.util.Map;

/**
 * 用户加入图片购物车表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-21 22:19:09
 */
public interface UserShopService {
	
	UserShopDO get(Long id);
	
	List<UserShopDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(UserShopDO userShop);
	
	int update(UserShopDO userShop);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	void saveMyShop(UserShopDO userShopDO);
}
