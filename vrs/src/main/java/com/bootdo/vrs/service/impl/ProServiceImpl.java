package com.bootdo.vrs.service.impl;

import com.alibaba.fastjson.JSON;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.school.util.RedisUtil;
import com.bootdo.vr.domain.ProductDO;
import com.bootdo.vrs.common.MessageConstantVrs;
import com.bootdo.vrs.dao.IpgetCountDao;
import com.bootdo.vrs.dao.ProDao;
import com.bootdo.vrs.dao.TitleClsDao;
import com.bootdo.vrs.dao.UserImgclsDao;
import com.bootdo.vrs.domain.*;
import com.bootdo.vrs.service.LbimgsService;
import com.bootdo.vrs.service.ProService;
import com.bootdo.vrs.service.TitleClsService;
import com.bootdo.vrs.util.CreaateCode;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;



@Service
@Transactional
public class ProServiceImpl implements ProService {
	@Autowired
	private ProDao proDao;

	@Autowired
	private RedisUtil redisUtil;


	  @Autowired
        private LbimgsService lbimgsService;

        @Autowired
        private TitleClsService titleClsService;

        @Autowired
        private TitleClsDao titleClsDao;

        //海报首页展示3条排序
        @Autowired
        private ProService proService;

        @Autowired
		private UserImgclsDao userImgclsDao;

        @Autowired
		private IpgetCountDao ipgetCountDao;

	@Override
	public ProDO get(Integer id){
		return proDao.get(id);
	}
	@Override
	public List<ProDO> list(Map<String, Object> map){

		List<ProDO> list= proDao.list(map);
		for (ProDO proDO : list) {
			//查询放到集合
			List<TitleClsDO> titleClsDOS=proDao.queryAndTitles(proDO.getId());
			proDO.setTs(titleClsDOS);

		}


		return list;
	}
	@Override
	public int count(Map<String, Object> map){
		System.out.println(map.get("payState"));
		return proDao.count(map);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int save(ProDO pro){
		//先查询编号是否重复
		if (StringUtils.isNotBlank(pro.getCode())){
			//查询是否重复
			int proCount=proDao.queryProCode(pro.getCode());
			if (proCount>0){
				throw  new RuntimeException(MessageConstantVrs.PRO_ERROR_CODE);
			}
		}
		else {
			//生成一个code直
			while (true){
				//避免重复
				String code=CreaateCode.createOrderId();
				int proCrCount=proDao.queryProCode(pro.getCode());
				if (proCrCount<=0){
					pro.setCode(code);

					break;
				}

			}

		}

		//是选了二级分类
		if (pro.getCid2()!=null){
			pro.setCid(pro.getCid2());

		}
		int count=proDao.save(pro);
		if (count<=0){
			throw  new RuntimeException(MessageConstantVrs.PRO_ERROR);
		}
		//判断数组是否选中 如果选中 则添加中间表
		if (setPerCenCls(pro.getId(),pro.getChilds())==false){
			//抛出异常
			throw  new RuntimeException(MessageConstantVrs.PRO_ERROR);

		}

		if (this.savePrrtitle(pro.getId(),pro.getTids())==false){
			throw  new RuntimeException(MessageConstantVrs.PRO_ERROR);

		}
		//如果不是管理员
		//if (pro.getUid()!=1&&pro.getUid()!=138){
			//添加我的云视图
			UserImgclsDO userImgclsDO=new UserImgclsDO();
			userImgclsDO.setCid(pro.getId());
			userImgclsDO.setCreeatedate(new Date());
			userImgclsDO.setName(pro.getName());
			userImgclsDO.setUid(Integer.parseInt(pro.getUid()+""));
			userImgclsDao.save(userImgclsDO);

		//}

		delRedis();
		return 1;
	}



	//添加图片标签
	public  boolean savePrrtitle(Integer proid,String[] tits){
			//先删除
		   proDao.delProCenTiles(proid);

		  //添加
		if (tits!=null&&tits.length>0){
			for (int i = 0; i < tits.length; i++) {
				System.out.println(tits[i]+"aaaaaa");
				Map<String,Object> ps=new HashedMap();
				ps.put("proid",proid);
				ps.put("tid",tits[i]);
				int count=proDao.saveProTile(ps);
				if (count<=0){

					return  false;

				}
			}

		}

		return  true;


	}


	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public int update(ProDO pro){
		//判断编号是否重复
		//先查询编号是否重复
		if (StringUtils.isNotBlank(pro.getCode())){
			//查询是否重复
			int proCount=proDao.queryProCode1(pro.getCode(),pro.getId()+"");
			if (proCount>0){
				throw  new RuntimeException(MessageConstantVrs.PRO_ERROR_CODE);
			}
		}
		else {
			//生成一个code直
			while (true){
				//避免重复
				String code=CreaateCode.createOrderId();
				int proCrCount=proDao.queryProCode(pro.getCode());
				if (proCrCount<=0){
					pro.setCode(code);
					break;
				}

			}

		}




		pro.setUpdateDate(new Date());
		if (pro.getChilds()!=null&&pro.getChilds().length>0){
			pro.setCid(null);

		}

		int count=proDao.update(pro);

		if (count<=0){
			throw  new RuntimeException(MessageConstantVrs.PRO_EDIT_ERROR);
		}

		//更新标签
		if (this.savePrrtitle(pro.getId(),pro.getTids())==false){

			throw  new RuntimeException(MessageConstantVrs.PRO_EDIT_ERROR);
		}

		//批量删除 添加
		boolean b=setPerCenCls(pro.getId(),pro.getChilds());
		if (b==false){
			throw  new RuntimeException(MessageConstantVrs.PRO_EDIT_ERROR);
		}

		delRedis();
		return 1;
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int remove(Integer id){

		delRedis();
		//删除 我的云石表
		proDao.delUsetImg(id+"");
		return proDao.remove(id);
	}
	
	@Override
	public int batchRemove(Integer[] ids){
		delRedis();
		return proDao.batchRemove(ids);
	}

	@Override
	public Map<String, Object> queryIndex() {
		Jedis jedis=redisUtil.getJedis();

		Map<String,Object> map=new HashedMap();

		List<LbimgsDO> 	listLb=new ArrayList<LbimgsDO>();

		List<TitleClsDO> listTitle=new ArrayList<TitleClsDO>();

		List<TitleClsDO> listIndeLimt=new ArrayList<TitleClsDO>();

		try {
			String lbValue=	jedis.get(MessageConstantVrs.LBTALL);
			String memuList=jedis.get(MessageConstantVrs.MENULIST);
			String tileLimit=jedis.get(MessageConstantVrs.INDEXCEN);
			if (StringUtils.isNotBlank(lbValue)){
				listLb= JSON.parseArray(lbValue,LbimgsDO.class);
			}else{
				//数
				 listLb=lbimgsService.list(null);
				 jedis.set(MessageConstantVrs.LBTALL, JSON.toJSONString(listLb));
			}


			if (StringUtils.isNotBlank(memuList)){
				listTitle= JSON.parseArray(memuList,TitleClsDO.class);
			}else {

				listTitle=titleClsDao.titlePrentAll();
				for (int i = 0; i <listTitle.size() ; i++) {

					//查询下面的子集
					map.put("pid", listTitle.get(i).getId());
					List<TitleClsDO> childList = titleClsService.querylist(map);
					listTitle.get(i).setChildTiles(childList);
				}
				jedis.set(MessageConstantVrs.MENULIST, JSON.toJSONString(listLb));

			}


			if (StringUtils.isNotBlank(tileLimit)){
				listIndeLimt=JSON.parseArray(tileLimit,TitleClsDO.class);

			}

			map.put("lbList",listLb);
			map.put("menu",listTitle);
			map.put("indexMenu",listIndeLimt);

		}
		catch (Exception e){
			e.printStackTrace();
		}
		finally {
			if (jedis!=null)
				jedis.close();
		}
		return map;
	}

	@Override
	public List<ProDo2> queryTitleLimit(Integer id) {
		//默认第一页 显示三条
   		PageHelper.startPage(1,3);


		List<ProDo2> proDOList= proDao.queryTitleLimit(id);
		for (ProDo2 proDO : proDOList) {
			IpgetCountDO ipgetCountDO=ipgetCountDao.queryPro( proDO.getId());
			proDO.setSum("0");
			if (ipgetCountDO!=null){
				Long count=ipgetCountDO.getCount();
				if (count>=10000){

					double f1 = new BigDecimal((float)count/10000).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
					proDO.setSum(f1+"万");

				}
				else {
					proDO.setSum(count+"");
				}

			}
		}

		return proDOList;
	}


	//标签
	public  boolean setPerCenCls(Integer proid,String[] childs) {
		if (childs == null || childs.length < 0) {
			return true;
		}
		proDao.deletePerCenCls(proid);
		//添加中间表
		for (int i = 0; i < childs.length; i++) {
			Map<String, Object> map = new HashedMap();
			map.put("proid", proid);
			map.put("c1id", childs[i]);
			int count = proDao.savePerCenCls(map);
			if (count <= 0) {
				return false;
			}
		}

		return  true;
	}

	@Override
	public List<ProDO> getAll(Map<String, String> map) {
		List<ProDO> list=proDao.getAll(map);
		for (ProDO pageInfo : list) {
			String img=pageInfo.getImg().substring(pageInfo.getImg().lastIndexOf("/")+1,pageInfo.getImg().indexOf("."));
			pageInfo.setQjtfile(img+".tiles");
		}

		return list;
	}

	@Override
	public List<Map<String, String>> getAllMune(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return proDao.getAllMune(map);
	}

	@Override
	public PageInfo<ProDO> queryTypeProCls(String parenCls, String typeid,Integer page) {
		 PageHelper.startPage(page,9);
		if (StringUtils.isNotBlank(parenCls)){
			parenCls="("+parenCls+")";

		}
		List<ProDO> list=proDao.queryTypeProCls(parenCls,typeid);
		PageInfo<ProDO> pageInfo=new PageInfo<ProDO>(list);
		for (int i = 0; i <pageInfo.getList().size() ; i++) {
				ProDO proDO=pageInfo.getList().get(i);
				String img=proDO.getImg().substring(proDO.getImg().lastIndexOf("/")+1,proDO.getImg().indexOf("."));
				pageInfo.getList().get(i).setQjtfile(img+".tiles");
		}
		return pageInfo;
	}

	@Override
	public PageInfo<ProDO> quertSearchPro(Integer size, Integer page, String name) {
		PageHelper.startPage(page,size);
		List<ProDO> list=proDao.quertSearchPro(name);
		PageInfo<ProDO> pageInfo=new PageInfo<ProDO>(list);


		return pageInfo;
	}

	@Override
	public PageInfo<ImgclsImgsDO> queryServiceDetali(Integer proid, Integer page, Integer size) {
		PageHelper.startPage(page,size);
		List<ImgclsImgsDO> list=proDao.queryDetali(proid);
		PageInfo<ImgclsImgsDO> pageInfo=new PageInfo<ImgclsImgsDO>(list);
		if (pageInfo.getNextPage()<=0){
			pageInfo.setNextPage(pageInfo.getPages());
		}
		if (pageInfo.getPrePage()<=0){
			pageInfo.setPrePage(1);
		}


		return pageInfo;
	}

	@Override
	public List<ProDO> listProTie(Map<String, Object> map) {
		
		Object primary = map.get("catory2");//主分类
		Object sub = map.get("catory1");//子分类
		if (primary==null){
			primary="";
		}
		if (sub==null){
			sub="";
		}
		List<Integer> subId = new ArrayList<Integer>();

		//主分类有值，子分类没有值，查询主分类下所有的子分类
		if(!StringUtils.isEmpty(String.valueOf(primary)) && StringUtils.isEmpty(String.valueOf(sub))) {
			subId =  proDao.getSub(primary);
			map.put("subids", subId);
		//主分类与子分类都有值，精确到子分类
			//!StringUtils.isEmpty(String.valueOf(primary)) && !StringUtils.isEmpty(String.valueOf(sub))
		}
		else if(!StringUtils.isEmpty(String.valueOf(primary)) && !StringUtils.isEmpty(String.valueOf(sub))){
			subId.add(Integer.parseInt(sub.toString()));
			map.put("subids", subId);
		}
		
		return  proDao.listProTie(map);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int saves(ProDO proDO) {
		String[] imgs=proDO.getImgs();
		boolean b=false;
		if (imgs!=null&&imgs.length>0){
			//批量添加
			for (String img : imgs) {
				//批量生成对象 名字
				ProDO pro=new ProDO();
				while (true){
					//避免重复
					String code=CreaateCode.createOrderId();
					int proCrCount=proDao.queryProCode(code);
					if (proCrCount<=0){
						String[] ims=img.split(",");
						pro.setCode(code);
						pro.setName(code);
						pro.setImg(ims[0]);
						pro.setCreatedate(new Date());
						pro.setStatus(proDO.getStatus());
						pro.setPayState(proDO.getPayState());
						pro.setTypeid(proDO.getTypeid());
						pro.setCid(proDO.getCid());
						if (proDO.getCid3()!=null){
							pro.setCid(proDO.getCid3());
							proDO.setCid2(null);
						}
						pro.setSimg(ims[0]);
						//3037802.html
						if (ims.length>1){

							pro.setSimg(ims[1]);
						}
						//避免只选择一张
						else{
							pro.setSimg(imgs[1]);
							b=true;
						}

						pro.setSort(1);

						pro.setUid(proDO.getUid());
						break;
					}

				}
				if (proDO.getCid2()!=null){
					pro.setCid(pro.getCid2());
				}
				int count=proDao.save(pro);
				if (count<=0){
					throw  new RuntimeException(MessageConstantVrs.PRO_ERROR);
				}
				//判断数组是否选中 如果选中 则添加中间表
				if (setPerCenCls(pro.getId(),proDO.getChilds())==false){
					//抛出异常
					throw  new RuntimeException(MessageConstantVrs.PRO_ERROR);

				}

				if (this.savePrrtitle(pro.getId(),proDO.getTids())==false){
					throw  new RuntimeException(MessageConstantVrs.PRO_ERROR);

				}

		//		if (proDO.getUid()!=1&&proDO.getUid()!=138){
					//添加我的云视图
					UserImgclsDO userImgclsDO=new UserImgclsDO();
					userImgclsDO.setCid(pro.getId());
					userImgclsDO.setCreeatedate(new Date());
					userImgclsDO.setName(pro.getName());
					userImgclsDO.setUid(Integer.parseInt(pro.getUid()+""));
					userImgclsDao.save(userImgclsDO);

		//		}

				if (b==true){
					break;
				}

			}
			delRedis();
		}

		return 0;
	}

	@Override
	public PageInfo<ProDO> getTitleLimt(Integer page, String tid) {
		//判断总条数 如果
		int count=proDao.queryTitleCount(Integer.parseInt(tid));
		int sum=count/3;
		if (count%3!=0){
			sum+=1;
		}

		if (page>count){
			//又从第一页开始
			page=1;
		}


		 PageHelper.startPage(page,3);
		 List<ProDO> list=proDao.queryTitleLimit2(Integer.parseInt(tid));
		 PageInfo<ProDO> pageInfo=new PageInfo<ProDO>(list);
		 if (pageInfo.getNextPage()<=0){
		 	pageInfo.setNextPage(1);

		 }
		 if (pageInfo.getPrePage()<=0){
		 	pageInfo.setPrePage(pageInfo.getPages());
		 }
		List<ProDO> proDOList=pageInfo.getList();
		for (ProDO proDO : proDOList) {
		 	IpgetCountDO ipgetCountDO=ipgetCountDao.queryPro( proDO.getId());
		 	proDO.setSum("0");
		 	if (ipgetCountDO!=null){
				Long sums=ipgetCountDO.getCount();
				if (sums>=10000){
					double f1 = new BigDecimal((float)sums/10000).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
					proDO.setSum(f1+"万");
				}
				else {
					proDO.setSum(sums+"");
				}
			}
		}
		pageInfo.setList(proDOList);
		return pageInfo;
	}


	//同步缓存
	public  void delRedis(){
		Jedis jedis=redisUtil.getJedis();

		try {
			jedis.del(MessageConstantVrs.INDEXCEN);

		}
		catch (Exception e){
			e.printStackTrace();
		}
		finally {
			if (jedis!=null)
				jedis.close();
		}

	}

	public static void main(String[] args) {
		Long a=50000l;
		Long b=10000l;
		double f1 = new BigDecimal((float)a/b).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		System.out.println(f1);
	}

}
