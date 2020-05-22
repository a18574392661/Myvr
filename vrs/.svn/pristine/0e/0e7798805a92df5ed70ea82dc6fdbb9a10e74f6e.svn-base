package com.bootdo.ts.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import com.bootdo.ts.dao.CurrlogDao;
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

import com.bootdo.ts.domain.ZwDO;
import com.bootdo.ts.service.ZwService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-05-13 13:47:40
 */
 
@Controller
@RequestMapping("/ts/zw")
public class ZwController {
	@Autowired
	private ZwService zwService;

	@Autowired
	private CurrlogDao currlogDao;
	
	@GetMapping()
	@RequiresPermissions("ts:zw:zw")
	String Zw(ModelMap map,String cid)

	{
		map.put("cid",cid);
		//查属于哪个日期 哪个课程
		map.put("clog",currlogDao.getCurrCnames(cid));
	    return "ts/zw/zw";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("ts:zw:zw")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ZwDO> zwList = zwService.list(query);
		int total = zwService.count(query);
		PageUtils pageUtils = new PageUtils(zwList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("ts:zw:add")
	String add(ModelMap map,String cid)
	{

		map.put("cid",cid);
	    return "ts/zw/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("ts:zw:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		ZwDO zw = zwService.get(id);
		model.addAttribute("zw", zw);
	    return "ts/zw/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("ts:zw:add")
	public R save( ZwDO zw){
		if(zwService.save(zw)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("ts:zw:edit")
	public R update( ZwDO zw){
		zwService.update(zw);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("ts:zw:remove")
	public R remove( Integer id){
	int c=	currlogDao.queryCountzw(id);
	if(c<=1){
		return  R.error("最少要留一个座位");
	}

		if(zwService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("ts:zw:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		zwService.batchRemove(ids);
		return R.ok();
	}



	public static void main(String[] args) {
		try {

			Date date = new Date();
			Locale locale = Locale.CHINA;
			DateFormat shortDf = DateFormat.getDateTimeInstance(DateFormat.FULL,DateFormat.FULL, locale);
			String sj=shortDf.format(date);

			System.out.println("中国格式："+shortDf.format(date));

		}
		catch (Exception e){
			e.printStackTrace();
		}


	}


}
