package com.bootdo.school.service;

import com.bootdo.school.domain.UserCouponLogDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-08 16:24:33
 */
public interface UserCouponLogService {
	
	UserCouponLogDO get(Long id);
	
	List<UserCouponLogDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(UserCouponLogDO userCouponLog);
	
	int update(UserCouponLogDO userCouponLog);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	List<UserCouponLogDO> queryUserCouponLog(Long uid,String buid);
	UserCouponLogDO userBusStatusShow(Long uid,String busid);
	void userBusStatus(Map map);

}
