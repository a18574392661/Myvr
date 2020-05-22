package com.bootdo.vrs.service;

import com.bootdo.vrs.domain.PayorderLogDO;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-26 10:25:17
 */
public interface PayorderLogService {
	
	PayorderLogDO get(Long id);
	
	List<PayorderLogDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(PayorderLogDO payorderLog);
	
	int update(PayorderLogDO payorderLog);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	PayorderLogDO getPayStatus(String orderCode);
	int editPayStatus(PayorderLogDO orderCode);
}
