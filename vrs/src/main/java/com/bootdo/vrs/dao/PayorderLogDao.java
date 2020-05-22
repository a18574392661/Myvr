package com.bootdo.vrs.dao;

import com.bootdo.vrs.domain.PayorderLogDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-26 10:25:17
 */
@Mapper
public interface PayorderLogDao {

	PayorderLogDO get(Long id);
	
	List<PayorderLogDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(PayorderLogDO payorderLog);
	
	int update(PayorderLogDO payorderLog);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	@Update("update vrs_payorder_log set status=1 where ordercode=#{code}")
	int editPaySta(@Param("code") String ordeCode);


	@Update("update vrs_payorder_log set hprice=#{hprice},tradeNo=#{tradeno},context=#{context},payDate=#{payDate},bw=#{bw} where ordercode=#{ordercode}")
	int editPayPrice(PayorderLogDO payorderLogDO);

	@Select("select * from vrs_payorder_log where  orderCode=#{code}")
	PayorderLogDO getPayStatus(@Param("code") String code);

	//返回1用户上一次未支付的订单
	@Select("select * from vrs_payorder_log \n" +
			"where uid=#{uid} and status=0\n" +
			"ORDER BY createDate desc \n" +
			"LIMIT 1")
	PayorderLogDO getMaxOrder(String uid);
}
