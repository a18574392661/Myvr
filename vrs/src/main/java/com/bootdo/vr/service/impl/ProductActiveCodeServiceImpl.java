package com.bootdo.vr.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.vr.dao.ProductActiveCodeDao;
import com.bootdo.vr.domain.ProductActiveCodeDO;
import com.bootdo.vr.service.ProductActiveCodeService;



@Service
public class ProductActiveCodeServiceImpl implements ProductActiveCodeService {
	@Autowired
	private ProductActiveCodeDao productActiveCodeDao;
	
	@Override
	public ProductActiveCodeDO get(Integer id){
		return productActiveCodeDao.get(id);
	}
	
	@Override
	public List<ProductActiveCodeDO> list(Map<String, Object> map){
		return productActiveCodeDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return productActiveCodeDao.count(map);
	}
	
	@Override
	public int save(ProductActiveCodeDO productActiveCode){
		return productActiveCodeDao.save(productActiveCode);
	}
	
	@Override
	public int update(ProductActiveCodeDO productActiveCode){
		return productActiveCodeDao.update(productActiveCode);
	}
	
	@Override
	public int remove(Integer id){
		return productActiveCodeDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return productActiveCodeDao.batchRemove(ids);
	}

	@Override
	public int saveBatch(Map<String, Object> params) {
		return productActiveCodeDao.saveBatch(params);
	}

	@Override
	public ProductActiveCodeDO getProductActiveCodeByCode(String productActiveCode) {
		return productActiveCodeDao.getProductActiveCodeByCode(productActiveCode);
	}
	
}
