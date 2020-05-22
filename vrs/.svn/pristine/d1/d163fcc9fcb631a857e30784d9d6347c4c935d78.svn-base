package com.bootdo.vrs.dao;

import com.bootdo.vrs.domain.UserpayTxDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 提现记录表
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-26 13:23:43
 */
@Mapper
public interface UserpayTxDao {

	UserpayTxDO get(Long id);
	
	List<UserpayTxDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(UserpayTxDO userpayTx);
	
	int update(UserpayTxDO userpayTx);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}
