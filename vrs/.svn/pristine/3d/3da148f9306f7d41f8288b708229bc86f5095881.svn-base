package com.bootdo.school.service;

import com.bootdo.school.domain.UserCouponDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-08 13:58:02
 */
public interface UserCouponService {
	
	UserCouponDO get(Long id);
	
	List<UserCouponDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(UserCouponDO userCoupon);
	
	int update(UserCouponDO userCoupon);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
	
	
	UserCouponDO queryUserCoupon(Long uid);

	void userCoupon_hx(Map<String, Object> paremt);

	UserCouponDO searchUserLog(Map<String,Object> paremt);
}
