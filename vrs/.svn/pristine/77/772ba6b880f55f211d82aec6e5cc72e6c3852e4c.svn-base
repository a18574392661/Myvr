package com.bootdo.school.service.impl;

import com.bootdo.common.utils.MD5Utils;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.school.common.MessageConstant;
import com.bootdo.school.dao.BusinessDao;
import com.bootdo.school.domain.BusinessDO;
import com.bootdo.school.service.BusinessService;
import com.bootdo.system.dao.UserDao;
import com.bootdo.system.dao.UserRoleDao;
import com.bootdo.system.domain.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Transactional
public class BusinessServiceImpl implements BusinessService {
	@Autowired
	private BusinessDao businessDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	UserRoleDao userRoleMapper;


	@Override
	public BusinessDO get(Integer id){
		return businessDao.get(id);
	}
	
	@Override
	public List<BusinessDO> list(Map<String, Object> map){
		return businessDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return businessDao.count(map);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int save(BusinessDO business){
		Map map=new HashMap();
		map.put("username",business.getTel());

		int disbleUser=userDao.queryUserNameDisble(map);
		if (disbleUser>0){
			throw  new RuntimeException(MessageConstant.USERDISBALETEL);

		}
		//添加用户 账号是手机号码 密码是手机号码后面6位
		UserDO userDO=new UserDO();
		userDO.setName(business.getName());
		userDO.setUsername(business.getTel());
		userDO.setMobile(business.getTel());
		userDO.setStatus(MessageConstant.SUCESS_STATUS);
		userDO.setRid(63L);
		userDO.setGmtCreate(new Date());


		//密码6位数
		userDO.setPassword(MD5Utils.encrypt(userDO.getUsername(),getTelPwd(userDO.getUsername())));
		int ucount=userDao.save(userDO);
		if (ucount<=0){
			throw  new RuntimeException(MessageConstant.USER_SAVE_ERROR);
		}
		//跟用户关联
		business.setUid(userDO.getUserId());
		int buscount=businessDao.save(business);
		if (buscount<=0){
			throw  new RuntimeException(MessageConstant.USER_SAVE_ERROR);
		}
		return 1;
	}
	
	@Override
	public int update(BusinessDO business){
		Map map=new HashMap();
		map.put("username",business.getTel());
		map.put("uid",business.getUid());
		int disbleUser=userDao.queryUserNameDisble(map);
		if (disbleUser>0){
			throw  new RuntimeException(MessageConstant.USERDISBALETEL);

		}

		//根据uid 修改用户手机账号登录账号等信息
		UserDO userDO=new UserDO();
		userDO.setUserId(business.getUid());
		userDO.setUsername(business.getTel());
		userDO.setName(business.getTel());
		userDO.setMobile(business.getTel());
		userDO.setGmtModified(new Date());
		int count=userDao.update(userDO);
		if (count<=0){
			throw  new  RuntimeException(MessageConstant.UPDATE_BUSINE_ERROR);
		}
		if (businessDao.update(business)<=0){
			throw  new  RuntimeException(MessageConstant.UPDATE_BUSINE_ERROR);
		}
		return 1;
	}
	
	@Override
	public int remove(Integer id){
		
		return businessDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return businessDao.batchRemove(ids);
	}

	@Override
	public BusinessDO queryCouponId(String uid) {

		return businessDao.queryCouponId(uid);
	}


	public   String getTelPwd(String tel){
		if (StringUtils.isBlank(tel)){

			return tel;
		}
		tel=tel.substring(5);
		return  tel;


	}



}


