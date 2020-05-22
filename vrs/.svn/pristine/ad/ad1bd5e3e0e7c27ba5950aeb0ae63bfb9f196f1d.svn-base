package com.bootdo.ts.controller;

import java.util.List;
import java.util.Map;

import com.bootdo.ts.dao.XjDao;
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

import com.bootdo.ts.domain.XjDO;
import com.bootdo.ts.service.XjService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 选择第几节课
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-05-13 14:13:54
 */
 
@Controller
@RequestMapping("/ts/xj")
public class XjController {
	@Autowired
	private XjService xjService;

	@Autowired
	private XjDao xjDao;
	
	@GetMapping()
	@RequiresPermissions("ts:xj:xj")
	String Xj(){
	    return "ts/xj/xj";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("ts:xj:xj")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<XjDO> xjList = xjService.list(query);
		int total = xjService.count(query);
		PageUtils pageUtils = new PageUtils(xjList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("ts:xj:add")
	String add(){
	    return "ts/xj/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("ts:xj:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		XjDO xj = xjService.get(id);
		model.addAttribute("xj", xj);
	    return "ts/xj/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("ts:xj:add")
	public R save( XjDO xj){
		int c=xjDao.queryName(xj);
		if (c>0){
			return  R.error("安排节课名字重复!");
		}
		if(xjService.save(xj)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("ts:xj:edit")
	public R update( XjDO xj){
		int c=xjDao.queryNameid(xj);
		if (c>0){
			return  R.error("安排节课名字重复!");
		}
		xjService.update(xj);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("ts:xj:remove")
	public R remove( Integer id){
		int c=xjDao.queryCountKC(id+"");
		if (c>0){
			return  R.error("该节课下面已经安排课程不能删除!");
		}

		if(xjService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("ts:xj:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		xjService.batchRemove(ids);
		return R.ok();
	}
	
}
