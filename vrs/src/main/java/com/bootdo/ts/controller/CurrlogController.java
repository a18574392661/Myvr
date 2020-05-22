package com.bootdo.ts.controller;

import java.text.SimpleDateFormat;
import java.util.*;

import com.bootdo.common.utils.StringUtils;
import com.bootdo.ts.dao.CurrlogDao;
import com.bootdo.ts.dao.UCidsDao;
import com.bootdo.ts.dao.XjDao;
import com.bootdo.ts.domain.XjDO;
import com.bootdo.ts.service.CurrService;
import com.bootdo.ts.service.XjService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.io.output.TaggedOutputStream;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.ts.domain.CurrlogDO;
import com.bootdo.ts.service.CurrlogService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 添加课程表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-05-13 13:46:13
 */
 
@Controller
@RequestMapping("/ts/currlog")
public class CurrlogController {
	@Autowired
	private CurrlogService currlogService;

	@Autowired
	private CurrService currService;

	@Autowired
	private XjService xjService;

	@Autowired
	private  CurrlogDao currlogDao;

	@Autowired
	private UCidsDao uCidsDao;

	@Autowired
	private XjDao xjDao;



	
	@GetMapping()
	@RequiresPermissions("ts:currlog:currlog")
	String Currlog(ModelMap model){
		Map<String,Object> map=new HashMap();
		map.put("status","1");
		model.addAttribute("clist",currService.list(map));
		return "ts/currlog/currlog";
	}

	public void setCalendar(ModelMap modelMap){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = Calendar.getInstance();
		//过去七天
		c.setTime(new Date());
		c.add(Calendar.DATE, - 7);
		Date d = c.getTime();
		String day = format.format(d);
		modelMap.addAttribute("saveq",day);
		//过去一月
		c.setTime(new Date());
		c.add(Calendar.MONTH, -1);
		Date m = c.getTime();
		String mon = format.format(m);
		modelMap.addAttribute("savem",mon);
		c.setTime(new Date());
		c.add(Calendar.DATE, +7);
		Date d1 = c.getTime();
		String day1 = format.format(d1);
		modelMap.addAttribute("jsaveq",day1);
		//过去一月
		c.setTime(new Date());
		c.add(Calendar.MONTH, +1);
		Date m2 = c.getTime();
		String mon2 = format.format(m2);
		modelMap.addAttribute("jmon2",mon2);

	}


	@ResponseBody
	@GetMapping("/test")
	public  Object test(){
		List<Map<String,Object>> rs=new ArrayList<Map<String,Object>>();
		List<XjDO> xjDOList=xjService.list(null);
		for (int j = 0; j <3 ; j++) {
			int i=0;
			Map jg=new HashMap();
			jg.put("dx","日期的"+j);
			Map xq=new LinkedHashMap();
			//嵌套外循环
			xq.put("预约日期",jg);
			for (XjDO xjDO : xjDOList) {
				Map jg2=new HashMap();
				jg2.put("dx","星期的"+i);
				xq.put(xjDO.getName(),jg2);
				i++;
			}
			rs.add(xq);
		}
		/*Map total=new LinkedHashMap();
		total.put("total",3);
		rs.add(total);
*/



		return rs;
	}


	//只查表头
	@ResponseBody
	@GetMapping("/getTitle")
	@RequiresPermissions("ts:currlog:currlog")
	public List<XjDO> getTitle(){
		List<Map<String,Object>> rs=new ArrayList<Map<String,Object>>();
		List<XjDO> list= xjDao.list(null);
		XjDO s=new XjDO();
		s.setName("预约日期");;
		list.add(0,s);
		Map jg=new LinkedHashMap();
		jg.put("预约日期","");
		for (XjDO xjDO : list) {
			jg.put(xjDO.getName(),"");
		}
		rs.add(jg);

		return list;
	}
	

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("ts:currlog:currlog")
	public Object list(@RequestParam Map<String, Object> params){


		//查询动态列头
		List<Map<String,Object>>  list=currlogService.list(params);



		return list;
	}

	@ResponseBody
	@GetMapping("/total")
	@RequiresPermissions("ts:currlog:currlog")
	public PageInfo total(@RequestParam Map<String, Object> map){



		Integer page=1;
		//方法1一
		Map<String,Object> results=new HashMap<>();
		StringBuilder tj=new StringBuilder(",");
		List<XjDO> xjList=xjDao.list(null);
		for (int i = 0; i<xjList.size() ; i++) {
			XjDO xjDOS=xjList.get(i);
			//查询出 当前日期对应的下标
			tj.append("MAX(CASE j.name WHEN "+"'"+xjDOS.getName()+"'"+" THEN  c.cname ELSE null END ) g"+xjList.get(i).getId());
			if (i<xjList.size()-1){
				tj.append(",");
			}
		}
		if (map==null){
			map=new HashMap<>();
		}
		map.put("tj",tj.toString());

		String sj=map.get("page")+"";
		if (!("null".equals(sj))&&null!=sj&&!("".equals(sj))&&sj.length()>0){
			page=Integer.parseInt(sj);
		}
		System.out.println(page+"页数");
		//总条数
		int total=currlogDao.total(map);
		PageHelper.startPage(page,10);
		List<Map<String,Object>> list=currlogDao.list2(map);
		PageInfo pageInfo=new PageInfo(list);
		System.out.println(pageInfo);

		return pageInfo;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("ts:currlog:add")
	String add(ModelMap modelMap,String jid,String sj){
		Map<String,Object> map=new HashMap();
		map.put("status","1");
	    modelMap.put("clist",currService.list(map));
	    modelMap.put("dlist",xjService.list(null));

		modelMap.put("sj",sj);
		if (StringUtils.isNotBlank(jid)&&StringUtils.isNotBlank(sj)&&!("null".equals(sj))&&!("null".equals(jid))){
			//点击了安排课程
			modelMap.put("dx",xjDao.get(Integer.parseInt(jid)));
			return "ts/currlog/add2";
		}
		return "ts/currlog/add";
	}

	@GetMapping("/edit")
	@RequiresPermissions("ts:currlog:edit")
	String edit(String id,Model model,String rq){
		//CurrlogDO cs=currlogDao.queryRqAndJid(id+"",rq);
		//当前课程被预约无法修改
		CurrlogDO currlog = currlogService.get(Long.parseLong(id));
		model.addAttribute("currlog", currlog);
		Map<String,Object> map=new HashMap();
		map.put("status","1");
		model.addAttribute("clist",currService.list(map));
		model.addAttribute("dlist",xjService.list(null));
	    return "ts/currlog/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("ts:currlog:add")
	public R save( CurrlogDO currlog){
		try {
			if(currlogService.save(currlog)>0){
				return R.ok();
			}
		}
		catch (RuntimeException e){
			e.printStackTrace();
			return  R.error(e.getMessage());
		}
		catch (Exception e){
			e.printStackTrace();
		}

		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("ts:currlog:edit")
	public R update( CurrlogDO currlog){




		try {
			int c=uCidsDao.queryKcCount(currlog.getId()+"");
			if (c>0){

				return  R.error("当前课程已经被预约不能修改!");
			}

			if(currlogService.update(currlog)>0){
				return R.ok();
			}
		}
		catch (RuntimeException e){
			e.printStackTrace();
			return  R.error(e.getMessage());
		}
		catch (Exception e){
			e.printStackTrace();
		}


		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("ts:currlog:remove")
	public R remove( String jid,String sj,String id){
		//CurrlogDO cs=currlogDao.queryRqAndJid(jid+"",sj);
	/*	if (cs==null){
			return  R.error();
		}*/
		//查询当前课程 是否又被预约
		//int c=uCidsDao.queryKcCount(cs.getId()+"");
		int c=uCidsDao.queryKcCount(id);
		if (c>0){

			return  R.error("当前课程已经被预约不能修改!");
		}

		if(currlogService.remove(Long.parseLong(id))>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("ts:currlog:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		currlogService.batchRemove(ids);
		return R.ok();
	}



	public static void main(String[] args) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = Calendar.getInstance();
		//过去七天
		c.setTime(new Date());
		c.add(Calendar.DATE, - 7);
		Date d = c.getTime();
		String day = format.format(d);
		System.out.println("过去七天："+day);

		//过去一月
		c.setTime(new Date());
		c.add(Calendar.MONTH, -1);
		Date m = c.getTime();
		String mon = format.format(m);
		System.out.println("过去一个月："+mon);


		c.setTime(new Date());
		c.add(Calendar.DATE, +7);
		Date d1 = c.getTime();
		String day1 = format.format(d1);
		System.out.println("后面7天："+day1);

		//过去一月
		c.setTime(new Date());
		c.add(Calendar.MONTH, +1);
		Date m2 = c.getTime();
		String mon2 = format.format(m2);
		System.out.println("下个月："+mon2);


	}

}
