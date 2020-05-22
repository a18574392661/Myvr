package com.bootdo.ts.controller;

import java.util.List;
import java.util.Map;

import com.bootdo.ts.dao.CurrDao;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.ts.domain.CurrDO;
import com.bootdo.ts.service.CurrService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 添加课程表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-05-13 13:45:22
 */
 
@Controller
@RequestMapping("/ts/curr")
public class CurrController {
	@Autowired
	private CurrService currService;

	@Autowired
	private CurrDao currDao;
	
	@GetMapping()
	@RequiresPermissions("ts:curr:curr")
	String Curr(){
	    return "ts/curr/curr";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("ts:curr:curr")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<CurrDO> currList = currService.list(query);
		int total = currService.count(query);
		PageUtils pageUtils = new PageUtils(currList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("ts:curr:add")
	String add(){
	    return "ts/curr/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("ts:curr:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		CurrDO curr = currService.get(id);
		model.addAttribute("curr", curr);
	    return "ts/curr/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("ts:curr:add")
	public R save( CurrDO curr){
		int c=currDao.queryName(curr);
		if (c>0){
			return  R.error("课程名字重复!");
		}
		if(currService.save(curr)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("ts:curr:edit")
	public R update( CurrDO curr){
		int c=currDao.queryNameid(curr);
		if (c>0){
			return  R.error("课程名字重复!");
		}
		currService.update(curr);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("ts:curr:remove")
	public R remove( Integer id){
		int c=currDao.queryCountKC(id+"");
		if (c>0){
			return  R.error("该课程已经设置可以预约不能删除!");
		}
		if(currService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("ts:curr:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		currService.batchRemove(ids);
		return R.ok();
	}
	
}
