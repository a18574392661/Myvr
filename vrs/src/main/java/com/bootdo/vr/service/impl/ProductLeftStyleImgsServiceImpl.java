package com.bootdo.vr.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.bootdo.vr.dao.ProductLeftStyleImgsDao;
import com.bootdo.vr.domain.ProductLeftStyleImgsDO;
import com.bootdo.vr.service.ProductLeftStyleImgsService;



@Service
public class ProductLeftStyleImgsServiceImpl implements ProductLeftStyleImgsService {
	@Autowired
	private ProductLeftStyleImgsDao productLeftStyleImgsDao;
	
	@Override
	public ProductLeftStyleImgsDO get(Integer id){
		return productLeftStyleImgsDao.get(id);
	}
	
	@Override
	public List<ProductLeftStyleImgsDO> list(Map<String, Object> map){
		return productLeftStyleImgsDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return productLeftStyleImgsDao.count(map);
	}
	
	@Override
	public int save(ProductLeftStyleImgsDO productLeftStyleImgs){
		return productLeftStyleImgsDao.save(productLeftStyleImgs);
	}
	
	@Override
	public int update(ProductLeftStyleImgsDO productLeftStyleImgs){
		return productLeftStyleImgsDao.update(productLeftStyleImgs);
	}
	
	@Override
	public int remove(Integer id){
		return productLeftStyleImgsDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return productLeftStyleImgsDao.batchRemove(ids);
	}

	@Override
	public List<Map<String, Object>> queryForLeftRdtTree(Integer[] ids) {
		return productLeftStyleImgsDao.queryForLeftRdtTree(ids);
	}

	@Override
	public int saveBatch(Map<String, Object> map) {
		return productLeftStyleImgsDao.saveBatch(map);
	}

	@Override
	public int removeByProductId(Integer productId) {
		return productLeftStyleImgsDao.removeByProductId(productId);
	}
	
}
