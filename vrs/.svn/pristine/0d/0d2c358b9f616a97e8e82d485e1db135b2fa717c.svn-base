package com.bootdo.vrs.service.impl;

import com.bootdo.system.dao.UserDao;
import com.bootdo.vrs.dao.HxLogDao;
import com.bootdo.vrs.dao.PayorderLogDao;
import com.bootdo.vrs.domain.PayorderLogDO;
import com.bootdo.vrs.service.PayorderLogService;
import com.bootdo.vrs.util.UserVerificationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;



@Service
@Transactional
public class PayorderLogServiceImpl implements PayorderLogService {
	@Autowired
	private PayorderLogDao payorderLogDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private HxLogDao hxLogDao;

	@Autowired
	private UserVerificationUtil userVerificationUtil;

	@Override
	public PayorderLogDO get(Long id){
		return payorderLogDao.get(id);
	}
	
	@Override
	public List<PayorderLogDO> list(Map<String, Object> map){
		return payorderLogDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return payorderLogDao.count(map);
	}
	
	@Override
	public int save(PayorderLogDO payorderLog){
		return payorderLogDao.save(payorderLog);
	}
	
	@Override
	public int update(PayorderLogDO payorderLog){
		return payorderLogDao.update(payorderLog);
	}
	
	@Override
	public int remove(Long id){
		return payorderLogDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return payorderLogDao.batchRemove(ids);
	}

	@Override
	public PayorderLogDO getPayStatus(String orderCode) {
		return payorderLogDao.getPayStatus(orderCode);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int editPayStatus(PayorderLogDO payorderLogDO) {
		//修改 状态
		payorderLogDao.editPaySta(payorderLogDO.getOrdercode());

		//避免重复执行
		if (hxLogDao.queryHxOrderCode(payorderLogDO.getOrdercode())<=0) {
			//修改成会员还有一个时间
			userDao.editUserVip(payorderLogDO);

			BigDecimal bigDecimal = userVerificationUtil.setUserPrice(payorderLogDO.getUid(), payorderLogDO.getPrice(), payorderLogDO.getRemark(), payorderLogDO.getOrdercode());
			payorderLogDO.setHprice(bigDecimal);
			//剩余金额
			payorderLogDao.editPayPrice(payorderLogDO);
		}

		return 1;
	}

}
