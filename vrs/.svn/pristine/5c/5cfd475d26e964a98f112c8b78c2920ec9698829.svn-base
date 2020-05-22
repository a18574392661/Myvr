package com.bootdo.vrs.service.impl;

import com.bootdo.vrs.common.MessageConstantVrs;
import com.bootdo.vrs.dao.ProDao;
import com.bootdo.vrs.dao.UserShopDao;
import com.bootdo.vrs.domain.ProDO;
import com.bootdo.vrs.domain.UserShopDO;
import com.bootdo.vrs.service.UserShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



@Service
public class UserShopServiceImpl implements UserShopService {
	@Autowired
	private UserShopDao userShopDao;

	@Autowired
	private ProDao proDao;
	
	@Override
	public UserShopDO get(Long id){
		return userShopDao.get(id);
	}
	
	@Override
	public List<UserShopDO> list(Map<String, Object> map){
		return userShopDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return userShopDao.count(map);
	}
	
	@Override
	public int save(UserShopDO userShop){
		return userShopDao.save(userShop);
	}
	
	@Override
	public int update(UserShopDO userShop){
		return userShopDao.update(userShop);
	}
	
	@Override
	public int remove(Long id){
		return userShopDao.remove(id);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return userShopDao.batchRemove(ids);
	}

	@Override
	public void saveMyShop(UserShopDO userShopDO) {
	 	ProDO pro=proDao.get(userShopDO.getProid());

	 	//占时不查库存
		/*if (pro.getCount()==null||pro.getCount()<=0){
			throw  new RuntimeException(MessageConstantVrs.PRO_COUNT_ERROR);
		}*/

		//先查询是否存在
		int count=0;
		int c=userShopDao.queryUserShop(userShopDO);
		if (c>0){
			//修改数量+1
			//userShopDao.editUserShop(userShopDO);
			//不能加重复
			throw  new RuntimeException(MessageConstantVrs.PRO_SHOP_DISBLEDS);
		}else{
			//新增
			userShopDO.setCount(1);
			userShopDao.save(userShopDO);
		}

	}

}
