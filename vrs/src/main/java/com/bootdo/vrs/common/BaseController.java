package com.bootdo.vrs.common;

import com.alibaba.fastjson.JSON;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.school.util.RedisUtil;
import com.bootdo.vrs.dao.TitleClsDao;
import com.bootdo.vrs.domain.*;
import com.bootdo.vrs.service.*;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BaseController{
	
	 //ThreadLocal<Map<String, Object>> threadLocal=new ThreadLocal<Map<String,Object>>();
	 Map<String, Object> sucessMap=null;

	 public void start() {

		 sucessMap =new HashMap<String, Object>();
		 //threadLocal.set(sucessMap);
	 }
	 
	 
	
	 public void success(Boolean success) {
		 sucessMap.put("success", success);
		 //this.threadLocal.get().put("success", success);
	 }
	 
	
	 public void message(Object message) {

		 this.sucessMap.put("message", message);
		 //this.threadLocal.get().put("message", message);
	 }
	 
	 
	 public void data(Object data) {

	 	this.sucessMap.put("data",data);
		// this.threadLocal.get().put("data", data);
	 }
	 
	
	public Map<String,Object> end(){

	 	return  this.sucessMap;
		//return  this.threadLocal.get();
	}




	@Autowired
	private RedisUtil redisUtil;

	@Autowired
	private TitleClsService titleClsService;

	@Autowired
	private LbimgsService lbimgsService;

	@Autowired
	private TitleClsDao titleClsDao;

	@Autowired
	private ProService proService;


	@Autowired
	private LogImgsService logImgsService;

	@Autowired
	private ImgClsService imgClsService;
	/**
	 * 
	 * @param list
	 * @param count
	 * @return  拆分集合
	 * @author: wym
	 * @date: 2020年4月16日 上午10:21:29
	 */
    public static <T> List<List<T>> listSplit(List<T> list,int count){
    	
		List<List<T>> listSplit = new ArrayList<>();
		if(list == null) {
			list = new ArrayList<T>();
		}
		List<T> temp = new ArrayList<T>();
		for(int i = 0;i < list.size();i++) {
			temp.add(list.get(i));
			if((i+1) % count == 0) {
				listSplit.add(temp);
				temp =  new ArrayList<T>(); 
			}
		}
		
		//获取后面的数据
		if(list.size() % count != 0) {
			Integer roundUp = list.size() % count;
			List<T> lasttemp = new ArrayList<T>();
			for(int i = list.size();i > (list.size() - roundUp);i--) {
				lasttemp.add( list.get(i-1) );
			}
			listSplit.add(lasttemp);
		}
		
		return listSplit;
    }
    
    /**
     * 返回整数
     * @param size
     * @param count
     * @return  
     * @author: wym
     * @date: 2020年4月16日 上午11:52:09
     */
    public static Integer roundUp(Integer size,Integer count) {
    	
    	if(size == 0) {
    		return size;
    	}
    	Integer len = size % count;
    	len = count - len;
    	
    	return len;
    }
    
   /**
    * @param curentPae 当前页
    * @param sumPages 页数
    * @param pageNum 分页页码
    * @return 
    * @author: wym
    * @date: 2020年4月16日 上午10:16:08
    */
    public static Map<String,Integer> calculatePageNums(Integer curentPae,Integer sumPages,Integer pageNum){
    	
    	Map<String,Integer> map = new HashMap<String, Integer>();
    	if(sumPages == 0) {
    		map.put("start", 0);
    		map.put("end", 0);
    		return map;
    	}
    	
    	if(sumPages < pageNum) {//小于5个页码的不计算
    		map.put("start", 1);
    		map.put("end", sumPages);
    	}else {  //5个及以上的页码计算页码
    		map.put("start", 1);
        	map.put("end", 5);
    		//1-3页显示5个页码
        	if(curentPae <= 3) {
        		return map;
        	}
        	//中间页码显示 n-2 至 n+2
        	if((curentPae + 2) <= sumPages) {
        		map.put("start", curentPae-2);
            	map.put("end", curentPae+2);
        	}else {
        		map.put("start", sumPages - (pageNum - 1));
        		map.put("end", sumPages);
        	}
    	}
    	
    	return map;
    }
    

    public static void PageHelper(Model model,List<ProDO> listPro){
    	
    	//填充集合
	    if(listPro.size() % MessageConstantVrs.PAGECOUNT != 0) {
	    	Integer roundUp = roundUp(listPro.size(), MessageConstantVrs.PAGECOUNT);
	    	for(int i = 0;i < roundUp;i++) {
	    		listPro.add(new ProDO());
	    	}
	    }
	    
	    //生成集合
	    PageInfo<ProDO> pageInfo = new PageInfo<ProDO>(listPro);
	    
	    //param1 当前页 param2 总页数 param3 显示的页数
	    Map<String, Integer> calculatePageNums = calculatePageNums(pageInfo.getPageNum(), pageInfo.getPages(), MessageConstantVrs.PAGENUMS);
	    
	    //拆分集合
		List<List<ProDO>> listSplit = listSplit(listPro, MessageConstantVrs.LISTCOUNT);
		System.out.println( listSplit.size() );
		for (List<ProDO> list : listSplit) {
			System.out.println( list.size() );
			System.out.println(list);
		}
		
		//保存属性
		model.addAttribute("lsitPro", listSplit);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("start", calculatePageNums.get("start"));
		model.addAttribute("end", calculatePageNums.get("end"));
    	
    }
    
	
	public static boolean isImage(String src) {
		
		if(StringUtils.isEmpty(src)) {
			return false;
		}
		if(src.indexOf(".png") > 0 || src.indexOf(".gif") > 0 || src.indexOf(".tif") > 0 ||  src.indexOf(".bmp") > 0 ||  src.indexOf(".jpeg") > 0 || src.indexOf(".jpg") > 0)  {
			return true;
		}
		return false;
	}




    //标题 轮播图
	public Map<String,Object> saveIndex(){

		Map<String, Object> map=new HashMap<String, Object>();
		Jedis jedis=redisUtil.getJedis();
		Map<String,Object> maps=new HashedMap();
		List<LbimgsDO> 	listLb=new ArrayList<LbimgsDO>();
		List<TitleClsDO> listTitle=new ArrayList<TitleClsDO>();
		List<TitleClsDO> listIndeLimt=new ArrayList<TitleClsDO>();
		List<LogImgsDO>  logList=new ArrayList<LogImgsDO>();
		try {
			String lbValue=	jedis.get(MessageConstantVrs.LBTALL);
			String memuList=jedis.get(MessageConstantVrs.MENULIST);
		//	String tileLimit=jedis.get(MessageConstantVrs.INDEXCEN);
			String logValue=jedis.get(MessageConstantVrs.LOGVALUE);
			if (StringUtils.isNotBlank(lbValue)&&!("null".equals(lbValue))){
				listLb= JSON.parseArray(lbValue,LbimgsDO.class);
			}else{
				//数
				listLb=lbimgsService.list(null);
				jedis.set(MessageConstantVrs.LBTALL, JSON.toJSONString(listLb));
			}
			if (StringUtils.isNotBlank(memuList)&&!("null".equals(memuList))){
				listTitle= JSON.parseArray(memuList,TitleClsDO.class);
			}else {
				Map<String, Integer> mapPar=new HashMap<String, Integer>();
				listTitle=titleClsDao.titlePrentAll();
				for (int i = 0; i <listTitle.size() ; i++) {
					//查询下面的子集
					mapPar.put("pid", listTitle.get(i).getId());
					List<TitleClsDO> childList = titleClsService.querylist(mapPar);
					listTitle.get(i).setChildTiles(childList);
				}
				jedis.set(MessageConstantVrs.MENULIST, JSON.toJSONString(listTitle));
			}

			//下面的
		/*	if (StringUtils.isNotBlank(tileLimit)&&!("null".equals(tileLimit))){
				listIndeLimt=JSON.parseArray(tileLimit,TitleClsDO.class);

			}
			else{*/
				for (int i = 0; i <listTitle.size() ; i++) {
					//如果展示到下面
					if (listTitle.get(i).getIsfulls()==1){
						List<ProDo2> proList= proService.queryTitleLimit(listTitle.get(i).getId());
						listTitle.get(i).setProDo2(proList);
						listIndeLimt.add(listTitle.get(i));
					}
				}

				//jedis.set(MessageConstantVrs.INDEXCEN, JSON.toJSONString(listIndeLimt));
			//}


			if (StringUtils.isNotBlank(logValue)&&!("null".equals(logValue))){
				logList=JSON.parseArray(logValue,LogImgsDO.class);
			}
			else {
				logList=logImgsService.queryLimitLog();
				jedis.set(MessageConstantVrs.LOGVALUE, JSON.toJSONString(logList));
			}

			map.put("lbList",listLb);
			map.put("menu",listTitle);
			map.put("indexMenu",listIndeLimt);
			map.put("logList",logList);

			//定制设计三级菜单
			map.put("clsTree",imgClsService.getThreeImgClsDo());


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
    
    
  
}
