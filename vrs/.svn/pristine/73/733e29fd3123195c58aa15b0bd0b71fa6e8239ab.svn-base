package com.bootdo.vr.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.vr.dao.ProductImgsDao;
import com.bootdo.vr.domain.ProductImgsDO;
import com.bootdo.vr.service.ProductImgsService;



@Service
public class ProductImgsServiceImpl implements ProductImgsService {
	@Autowired
	private ProductImgsDao productImgsDao;
	
	@Override
	public ProductImgsDO get(Integer id){
		return productImgsDao.get(id);
	}
	
	@Override
	public List<ProductImgsDO> list(Map<String, Object> map){
		return productImgsDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return productImgsDao.count(map);
	}
	
	@Override
	public int save(ProductImgsDO productImgs){
		return productImgsDao.save(productImgs);
	}
	
	@Override
	public int update(ProductImgsDO productImgs){
		return productImgsDao.update(productImgs);
	}
	
	@Override
	public int remove(Integer id){
		return productImgsDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return productImgsDao.batchRemove(ids);
	}

	@Override
	public int saveBatch(Map<String, Object> map) {
		return productImgsDao.saveBatch(map);
	}

	@Override
	public int removeByProductId(Integer productId) {
		return productImgsDao.removeByProductId(productId);
	}

	@Override
	public List<Map<String, Object>> queryForRdtTree(Integer[] ids) {
		return productImgsDao.queryForRdtTree(ids);
	}
	
}
