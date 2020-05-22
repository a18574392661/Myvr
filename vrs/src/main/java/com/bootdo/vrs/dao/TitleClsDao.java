package com.bootdo.vrs.dao;

import com.bootdo.vrs.domain.ImgClsDO;
import com.bootdo.vrs.domain.TitleClsDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-13 14:20:14
 */
@Mapper
public interface TitleClsDao {

	TitleClsDO get(Integer id);
	
	List<TitleClsDO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(TitleClsDO titleCls);
	
	int update(TitleClsDO titleCls);
	
	int remove(Integer id);
	
	int batchRemove(Integer[] ids);

	@Select("select par.`id`,par.`name`,par.`pid`,par.`status`,par.`hrefs`,par.`isfulls` from vrs_title_cls par where pid=#{pid}")
	List<TitleClsDO> titleAll(@Param(
			"pid"
	) String pid);

	@Select("select max(typeid)+1 from vrs_title_cls")
	Integer queryTieleMaxTypeId();

	@Select("select * from vrs_title_cls where typeid=#{typeid}")
	TitleClsDO queryTypeId(@Param("typeid") Integer typeid);

	@Select("\n" +
			"select * from vrs_title_cls where typeid!=#{typeid}\n" +
			"and isfulls=#{isfull} and `status`=#{status} and pid=#{pid}")
	List<TitleClsDO> queryListType(Map<String,Object> map);


	List<TitleClsDO> querylist(Map<String,Object> map);


	@Select("select * from vrs_title_cls where pid=0 and status!=0")
	List<TitleClsDO> titlePrentAll();

	@Select("SELECT * from vrs_title_cls \n" +
			"where id = (select typeid from vrs_pro WHERE id=#{porid})")
	TitleClsDO queryProTypeid(@Param("porid") String porid);



	@Select("\n" +
			"SELECT tit.typeid FROM vrs_title_cls tit WHERE tit.id \n" +
			"=(select typeid from vrs_pro p where id=#{proid})")
	public  Integer getProTypeidPage(@Param("proid") String proid);


	@Select("select * from ${tabname} where id=#{id}")
	public ImgClsDO getClsName(@Param("tabname")String tabname, @Param("id") String id);

	@Select("select cls.* from vrs_pro_cen_cls cen \n" +
			"\t\tleft join vrs_img_cls cls\n" +
			"\t\ton cls.id=cen.c1id \n" +
			"\t\tWHERE cen.proid=#{proid}")
	public  List<ImgClsDO> queryGet(@Param("proid") Integer proid);

	@Select("SELECT cls.* from vrs_pro_cen_tit cen\n" +
			"left join  vrs_title_cls cls\n" +
			"on cen.tid=cls.id\n" +
			"WHERE cen.proid=#{proid}")
	List<TitleClsDO> getproAll(@Param("proid") String proid);

}
