package com.bootdo.school.service.impl;

import com.bootdo.common.utils.StringUtils;
import com.bootdo.school.common.MessageConstant;
import com.bootdo.school.dao.UserCouponDao;
import com.bootdo.school.dao.UserCouponLogDao;
import com.bootdo.school.domain.UserCouponDO;
import com.bootdo.school.domain.UserCouponLogDO;
import com.bootdo.school.service.UserCouponService;
import com.bootdo.school.util.GenSerial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;



@Service
@Transactional
public class UserCouponServiceImpl implements UserCouponService {
	@Autowired
	private UserCouponDao userCouponDao;
	@Autowired
	private UserCouponLogDao userCouponLogDao;
	
	@Override
	public UserCouponDO get(Long id){
		return userCouponDao.get(id);
	}
	
	@Override
	public List<UserCouponDO> list(Map<String, Object> map){
		return userCouponDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return userCouponDao.count(map);
	}
	
	@Override
	public int save(UserCouponDO userCoupon){
		GenSerial genSerial = new GenSerial(0, 0);

		userCoupon.setCode(genSerial.nextId()+"");
		userCoupon.setCreatedate(new Date());
		userCoupon.setStatus(1);
		return userCouponDao.save(userCoupon);
	}
	
	@Override
	public int update(UserCouponDO userCoupon){
		return userCouponDao.update(userCoupon);
	}
	
	@Override
	public int remove(Long id){
		return userCouponDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return userCouponDao.batchRemove(ids);
	}

	@Override
	public UserCouponDO queryUserCoupon(Long uid) {
		UserCouponDO userCouponDO=null;
		try {
			
			userCouponDO=userCouponDao.queryUserCoupon(uid);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		return userCouponDO;
	}

	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void userCoupon_hx(Map<String, Object> paremt) {
		//先查询总的金额不能从页面上传总金额 
		//用户的卷的价格 一对一
		String price=userCouponDao.queryUserCouponPrice(paremt);
		if (StringUtils.isBlank(price)) {
			throw new RuntimeException(MessageConstant.USER_COUPON_ERROR);
		}
		
		BigDecimal myPrice=new BigDecimal(paremt.get("price")+"");//传的金额
		BigDecimal cPrice=new BigDecimal(price); //总金额
		if (myPrice.compareTo(cPrice)==1) {
			throw new RuntimeException(MessageConstant.USER_COUPON_PRICE_ERROR);
		}

		//先查询是否有当前 商家学员的 状态等待
		int cum=userCouponLogDao.queryUserCouplogDisble(paremt);
		if (cum>0){
			throw new RuntimeException(MessageConstant.USER_COUPON_ERROR);
		}

		//根据couid修改 uid 添加一条
		/*int count1=userCouponDao.updateUserCouponPrice(paremt);
		if (count1<=0) {
			throw new RuntimeException(MessageConstant.USER_COUPON_ERROR);
		}*/

		//添加日志 
		UserCouponLogDO userCouponLogDO=new UserCouponLogDO();
		//sid是选中学的学员id
		userCouponLogDO.setUid(Long.parseLong(paremt.get("sid")+""));
		userCouponLogDO.setPrice(myPrice);
		userCouponLogDO.setCreatedate(new Date());
		userCouponLogDO.setBusid(Integer.parseInt(paremt.get("busid")+""));
		userCouponLogDO.setPayStatus(0);
		int count2=userCouponLogDao.save(userCouponLogDO);
		if (count2<=0) {
			throw new RuntimeException(MessageConstant.USER_COUPON_ERROR);
		}
		
	}

	@Override
	public UserCouponDO searchUserLog(Map<String, Object> paremt) {

		UserCouponDO	userCouponDO=userCouponDao.queryUserCodeHx(paremt);
		if (userCouponDO==null){
			throw  new  RuntimeException(MessageConstant.SEARECH_STU_COUPON_ERROR);

		}

		return userCouponDO;
	}

}
