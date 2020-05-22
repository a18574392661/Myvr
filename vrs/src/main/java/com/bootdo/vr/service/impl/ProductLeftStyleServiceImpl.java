package com.bootdo.vr.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.vr.dao.ProductLeftStyleDao;
import com.bootdo.vr.domain.ProductLeftStyleDO;
import com.bootdo.vr.service.ProductLeftStyleService;



@Service
public class ProductLeftStyleServiceImpl implements ProductLeftStyleService {
	@Autowired
	private ProductLeftStyleDao productLeftStyleDao;
	
	@Override
	public ProductLeftStyleDO get(Integer id){
		return productLeftStyleDao.get(id);
	}
	
	@Override
	public List<ProductLeftStyleDO> list(Map<String, Object> map){
		return productLeftStyleDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return productLeftStyleDao.count(map);
	}
	
	@Override
	public int save(ProductLeftStyleDO productLeftStyle){
		return productLeftStyleDao.save(productLeftStyle);
	}
	
	@Override
	public int update(ProductLeftStyleDO productLeftStyle){
		return productLeftStyleDao.update(productLeftStyle);
	}
	
	@Override
	public int removeByProductId(Integer productId) {
		return productLeftStyleDao.removeByProductId(productId);
	}
	
	@Override
	public int remove(Integer id){
		return productLeftStyleDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return productLeftStyleDao.batchRemove(ids);
	}

	@Override
	public int saveBatch(Map<String, Object> params) {
		return productLeftStyleDao.saveBatch(params);
	}
	
}
