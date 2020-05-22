package com.bootdo.article.dao;

import com.bootdo.article.domain.ArticleDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-11 19:47:36
 */
@Mapper
public interface ArticleDao {

	ArticleDO get(Integer id);
	
	List<ArticleDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ArticleDO article);
	
	int update(ArticleDO article);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	ArticleDO selectOne(Map<String, Object> params);

	List<ArticleDO> batchList(String[] ids);
}
