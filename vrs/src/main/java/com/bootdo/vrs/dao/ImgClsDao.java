package com.bootdo.vrs.dao;

import com.bootdo.vrs.domain.ImgClsDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-11 21:32:31
 */
@Mapper
public interface ImgClsDao {

	ImgClsDO get(Long id);
	
	List<ImgClsDO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(ImgClsDO imgCls);
	
	int update(ImgClsDO imgCls);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);

	@Select("select * from vrs_img_cls where pid=#{pid} and status!=0")
	List<ImgClsDO> imgClsPars(@Param("pid") String pid);

	@Select("\n" +
			"select c1id from vrs_pro_cen_cls \n" +
			"WHERE proid=#{proid}")
	String[] queryProCeebnCls(@Param("proid") Integer id);


 	@Select("select  * from vrs_img_cls where id in ${cids}")
	List<ImgClsDO> queryClsCids(@Param("cids") String cids);

 	
 	@Select("select * from ${tablename} where status = #{status} and pid = 0")
	List<Map<String, Object>> getAllCategory(Map<String, String> param);
 	
 	@Select("select * from ${tablename} where status = #{status} and pid = #{pid}")
	List<Map<String, Object>> getAllCategorySub(Map<String, String> param);

 	/**
 	 * 
 	 * @param id
 	 * @return  返回这个分类的所有分类
 	 * @author: wym
 	 * @date: 2020年4月23日 下午2:26:19
 	 */
 	@Select("select vrs_pro_cen_cls.c1id as cid from vrs_pro \n" + 
 			"left join vrs_pro_cen_cls\n" + 
 			"on vrs_pro.id = vrs_pro_cen_cls.proid\n" + 
 			"where vrs_pro.id = #{id}")
	List<Map<String, Object>> qeuryCategoryByProId(Integer id);

 	@Select("select count(1) FROM vrs_pro_cen_cls\n" +
			"WHERE c1Id=#{cid}\n" +
			"\t\t")
 	int getClsChildPro(@Param("cid") Long cid);

}
