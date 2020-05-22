package com.bootdo.school.dao;

import com.bootdo.school.domain.UserCouponDO;
import io.lettuce.core.dynamic.annotation.Param;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-08 13:58:02
 */
@Mapper
public interface UserCouponDao {

	UserCouponDO get(Long id);
	
	List<UserCouponDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(UserCouponDO userCoupon);
	
	int update(UserCouponDO userCoupon);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
	
	UserCouponDO queryUserCoupon(@Param("uid")Long uid);

	String queryUserCouponPrice(Map<String, Object> paremt);
	
	int updateUserCouponPrice(Map<String, Object> paremt);

	int queryUserAndCoupon(@Param("uid") Long uid);

	UserCouponDO queryUserCodeHx(Map<String,Object> map);

	@Select("select count(1) from sc_user_coupon where uid=#{uid}")
	int queryUidDisbles(@Param("uid") String uid);
}
