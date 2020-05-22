package com.bootdo.school.dao;

import com.bootdo.school.domain.UserCouponLogDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-08 16:24:33
 */
@Mapper
public interface UserCouponLogDao {

	UserCouponLogDO get(@Param("uid") Long id);
	
	List<UserCouponLogDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(UserCouponLogDO userCouponLog);
	
	int update(UserCouponLogDO userCouponLog);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
	List<UserCouponLogDO> queryUserCouponLog(@Param("uid")Long uid,@Param("busid") Long buid);
	UserCouponLogDO userBusStatusShow(@Param("uid") Long uid,@Param("busid")Long busid);
	/*ssss*/
	int userBusStatus(Map<String,Object> map);

	int updateUserCouponPrice2(Map<String,Object> map);

	int queryUserCouplogDisble(Map<String,Object> paremt);

	List<String> queryStatus(@Param("id") String id);
}
