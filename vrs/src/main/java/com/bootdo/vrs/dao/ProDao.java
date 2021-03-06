package com.bootdo.vrs.dao;

import com.bootdo.vrs.domain.ImgclsImgsDO;
import com.bootdo.vrs.domain.ProDO;
import com.bootdo.vrs.domain.ProDo2;
import com.bootdo.vrs.domain.TitleClsDO;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * 海报价格图
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-13 10:19:40
 */
@Mapper
public interface ProDao {

	ProDO get(Integer id);
	
	List<ProDO> list(Map<String,Object> map);
	List<ProDO>  getAll(Map<String,String> map);


	@Select("select * from vrs_title_cls")
	List<Map<String,String>> getAllMune(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(ProDO pro);
	int update(ProDO pro);
	int remove(Integer id);
	int batchRemove(Integer[] ids);

	@Insert("insert into vrs_pro_cen_cls values(null,#{proid},#{c2id},#{c1id})")
	int savePerCenCls(Map<String,Object> map);

	@Delete("delete from vrs_pro_cen_cls where proid=#{proid}")
	int deletePerCenCls(@Param("proid") Integer id);


	@Select("select  count(1) from vrs_pro where  id!=#{id} and code=#{code} ")
	int queryProCode1(@Param("code") String code,@Param("id") String id);


	//一个图片多个标签
	@Select("select u.`name` as 'uname',u.src as 'src',p.* from vrs_pro p left join\n" +
			"vrs_pro_cen_tit ti\n" +
			"on p.id=ti.proid\n" +
			"left join sys_user u\n" +
			"on p.uid=u.user_id\n" +
			"where ti.tid=#{typeid} and p.status=1 \n" +
			"ORDER BY\n" +
			"p.sort ASC,p.updateDate  DESC")
	List<ProDo2> queryTitleLimit(@Param("typeid") Integer id);


	@Select("select u.`name` as 'uname',u.src as 'src',p.* from vrs_pro p left join\n" +
			"vrs_pro_cen_tit ti\n" +
			"on p.id=ti.proid\n" +
			"left join sys_user u\n" +
			"on p.uid=u.user_id\n" +

			"where ti.tid=#{typeid} and p.status=1 \n" +
			"ORDER BY\n" +
			"p.sort ASC,p.updateDate  DESC")
	List<ProDO> queryTitleLimit2(@Param("typeid") Integer tid);


	@Select("select count(1) from vrs_pro p left join\n" +
			"vrs_pro_cen_tit ti\n" +
			"on p.id=ti.proid\n" +
			"left join sys_user u\n" +
			"on p.uid=u.user_id\n" +
			"where ti.tid=#{typeid} and p.status=1 \n" +
			"ORDER BY\n" +
			"p.sort ASC,p.updateDate  DESC")
	int queryTitleCount(@Param("typeid") Integer tid);
	//List<Map<String,Object>> queryTitleLimit(@Param("typeid") Integer id);
	
	
	

	@Select("select count(1) from vrs_pro where  code=#{code} ")
	int queryProCode(@Param("code") String code);

	@Delete("delete from vrs_pro_cen_tit where proid=#{proid}")
	int delProCenTiles(@Param("proid") Integer proid);

	@Insert("insert into vrs_pro_cen_tit values (null,#{proid},#{tid})")
	int saveProTile(Map<String,Object> pstile);


	@Select("select tid from vrs_pro_cen_tit WHERE proid=#{id}")
	List<Integer> queryProAndTitle(Integer id);

	@Select("\n" +
            "SELECT tit.name from vrs_title_cls tit inner join \n" +
            "vrs_pro_cen_tit cen\n" +
            "on tit.id=cen.tid\n" +
            "WHERE cen.proid=#{id}")

 	List<TitleClsDO> queryAndTitles(Integer id);
	List<ProDO> queryTypeProCls(@Param("cids") String parent,@Param("typeid") String typeid);


	@Select("select * from vrs_imgcls_imgs\n" +
			"\t\t\tWHERE cid=#{proid} \n" +
			"\t\t\tORDER BY sort")
	List<ImgclsImgsDO> queryDetali(@Param("proid") Integer proid);



	List<ProDO> quertSearchPro(@Param("name") String name);

	//List<ProDO> getAll(Map<String, String> map);


	List<ProDO>   listProTie(Map<String,Object> map);

	int countlistProTie(Map<String,Object> map);

	/**
	 * 
	 * @param primary
	 * @return  二级联动查询分类下的子分类
	 * @author: wym
	 * @date: 2020年4月23日 下午5:18:52
	 */
	@Select("select id from vrs_img_cls where pid = #{primary}")
	@ResultType(Integer.class)
	List<Integer> getSub(Object primary);


	int updateStatus(@Param("tj") String tj,@Param("status") String status,@Param("paystatus") String paystatus);

	@Delete("delete from vrs_user_imgcls where cid=#{cid}")
	int  delUsetImg(@Param("cid") String id);


}
