package com.bootdo.article.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.bootdo.article.dao.ArticleDao;
import com.bootdo.article.domain.ArticleDO;
import com.bootdo.article.service.ArticleService;
import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.utils.ZxingEAN13Code;



@Service
public class ArticleServiceImpl implements ArticleService {
	@Autowired
	private ArticleDao articleDao;


	
	
	@Override
	public ArticleDO get(Integer id){
		return articleDao.get(id);
	}
	
	@Override
	public List<ArticleDO> list(Map<String, Object> map){
		return articleDao.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return articleDao.count(map);
	}
	
	
	@Override
	public int save(ArticleDO article){
		return articleDao.save(article);
	}
	
	@Override
	public int update(ArticleDO article){
		return articleDao.update(article);
	}
	
	@Override
	public int remove(Integer id){
		return articleDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		return articleDao.batchRemove(ids);
	}

	@Override
	public ArticleDO selectOne(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return articleDao.selectOne(params);
	}

	@Override
	public List<ArticleDO> batchList(String[] ids) {
		// TODO Auto-generated method stub
		
		return articleDao.batchList(ids);
	}
	
}
