package com.bootdo.school.service.impl;

import com.bootdo.common.utils.StringUtils;
import com.bootdo.school.common.MessageConstant;
import com.bootdo.school.dao.UserCouponLogDao;
import com.bootdo.school.domain.UserCouponLogDO;
import com.bootdo.school.service.UserCouponLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;



@Service
@Transactional
public class UserCouponLogServiceImpl implements UserCouponLogService {
	@Autowired
	private UserCouponLogDao userCouponLogDao;
	
	@Override
	public UserCouponLogDO get(Long id){
		return userCouponLogDao.get(id);
	}
	
	@Override
	public List<UserCouponLogDO> list(Map<String, Object> map){
		return userCouponLogDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return userCouponLogDao.count(map);
	}
	
	@Override
	public int save(UserCouponLogDO userCouponLog){
		return userCouponLogDao.save(userCouponLog);
	}
	
	@Override
	public int update(UserCouponLogDO userCouponLog){
		return userCouponLogDao.update(userCouponLog);
	}
	
	@Override
	public int remove(Long id){
		return userCouponLogDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return userCouponLogDao.batchRemove(ids);
	}

	@Override
	public List<UserCouponLogDO> queryUserCouponLog(Long uid,String buid) {
		Long bid=null;
			if (StringUtils.isNotBlank(buid)){
				uid=null;
				bid=Long.parseLong(buid);

			}

		return userCouponLogDao.queryUserCouponLog(uid,bid);
	}

	@Override
	public UserCouponLogDO userBusStatusShow(Long uid, String busid) {

		if (StringUtils.isBlank(busid)){
			throw  new RuntimeException(MessageConstant.ERROR);

		}

		UserCouponLogDO userCouponLogDO=userCouponLogDao.userBusStatusShow(uid,Long.parseLong(busid));
		return userCouponLogDO;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void userBusStatus(Map map) {
		if (map==null){
			throw  new RuntimeException(MessageConstant.ERRORNMESSAGE);

		}
		int count=userCouponLogDao.userBusStatus(map);
		if (count<=0){
			throw  new RuntimeException(MessageConstant.ERRORNMESSAGE);
		}
		//用户确定了(学员)
		if ("1".equals(map.get("status"))){
			int count2=userCouponLogDao.updateUserCouponPrice2(map);
			if (count2<=0){
				throw  new RuntimeException(MessageConstant.ERRORNMESSAGE);
			}
		}



	}

}
