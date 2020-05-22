package com.bootdo.vr.service.impl;

import com.bootdo.vr.dao.ProductInfoDao;
import com.bootdo.vr.domain.ProductInfoDO;
import com.bootdo.vr.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



@Service
public class ProductInfoServiceImpl implements ProductInfoService {
	@Autowired
	private ProductInfoDao productInfoDao;
	
	@Override
	public ProductInfoDO get(Integer id){
		return productInfoDao.get(id);
	}
	
	@Override
	public List<ProductInfoDO> list(Map<String, Object> map){

		return productInfoDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return productInfoDao.count(map);
	}
	
	@Override
	public int save(ProductInfoDO productInfo){
		return productInfoDao.save(productInfo);
	}
	
	@Override
	public int update(ProductInfoDO productInfo){
		return productInfoDao.update(productInfo);
	}
	
	@Override
	public int remove(Integer id){
		return productInfoDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return productInfoDao.batchRemove(ids);
	}
	
}
