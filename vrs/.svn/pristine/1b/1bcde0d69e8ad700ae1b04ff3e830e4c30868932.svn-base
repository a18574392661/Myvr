package com.bootdo.vrs.service.impl;

import com.bootdo.vrs.common.MessageConstantVrs;
import com.bootdo.vrs.dao.PaydetailesDao;
import com.bootdo.vrs.dao.PayvipDao;
import com.bootdo.vrs.domain.PaydetailesDO;
import com.bootdo.vrs.service.PaydetailesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;



@Service
@Transactional
public class PaydetailesServiceImpl implements PaydetailesService {
	@Autowired
	private PaydetailesDao paydetailesDao;

	@Autowired
	private PayvipDao payvipDao;


	@Override
	public PaydetailesDO get(Integer id){
		return paydetailesDao.get(id);
	}
	
	@Override
	public List<PaydetailesDO> list(Map<String, Object> map){
		return paydetailesDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return paydetailesDao.count(map);
	}
	
	@Override
	public int save(PaydetailesDO paydetailes){
		return paydetailesDao.save(paydetailes);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int update(PaydetailesDO paydetailes){
		//删除在修改
		payvipDao.editVipStatus("update vrs_payVip set status=0 where vipId="+ MessageConstantVrs.GET_VIPID);
		String[] vips=paydetailes.getVips();
		if (vips!=null&&vips.length>0){
			StringBuilder paremt=new StringBuilder("(");
			for (int i = 0; i < vips.length; i++) {
				paremt.append(vips[i]);
				if (vips.length-1>i){
					paremt.append(",");
				}

			}
			paremt.append(")");
			payvipDao.editVipStatus("update vrs_payVip set status=1 where id in "+paremt);
		}


		return paydetailesDao.update(paydetailes);
	}
	
	@Override
	public int remove(Integer id){
		return paydetailesDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return paydetailesDao.batchRemove(ids);
	}
	
}
