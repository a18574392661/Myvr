package com.bootdo.vr.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.vr.dao.ProductColorsDao;
import com.bootdo.vr.domain.ProductColorsDO;
import com.bootdo.vr.service.ProductColorsService;



@Service
public class ProductColorsServiceImpl implements ProductColorsService {
	@Autowired
	private ProductColorsDao productColorsDao;
	
	@Override
	public ProductColorsDO get(Integer id){
		return productColorsDao.get(id);
	}
	
	@Override
	public List<ProductColorsDO> list(Map<String, Object> map){
		return productColorsDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return productColorsDao.count(map);
	}
	
	@Override
	public int save(ProductColorsDO productColors){
		return productColorsDao.save(productColors);
	}
	
	@Override
	public int update(ProductColorsDO productColors){
		return productColorsDao.update(productColors);
	}
	
	@Override
	public int remove(Integer id){
		return productColorsDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return productColorsDao.batchRemove(ids);
	}

	@Override
	public int saveBatch(Map<String, Object> params) {
		return productColorsDao.saveBatch(params);
	}

	@Override
	public int removeByProductId(Integer productId) {
		return productColorsDao.removeByProductId(productId);
	}
	
}
