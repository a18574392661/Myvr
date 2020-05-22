package com.bootdo.vrs.controller;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.system.dao.RoleDao;
import com.bootdo.vrs.domain.UserHxDO;
import com.bootdo.vrs.service.UserHxService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 角色核销提成设置表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-24 14:04:40
 */
 
@Controller
@RequestMapping("/vrs/userHx")
public class UserHxController {
	@Autowired
	private UserHxService userHxService;
	
	@GetMapping()
	@RequiresPermissions("vrs:userHx:userHx")
	String UserHx(){
	    return "vrs/userHx/userHx";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("vrs:userHx:userHx")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<UserHxDO> userHxList = userHxService.list(query);
		int total = userHxService.count(query);
		PageUtils pageUtils = new PageUtils(userHxList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("vrs:userHx:add")
	String add(Model model){
model.addAttribute("roles",roleDao.getVrRole("(70,71,59)"));


		return "vrs/userHx/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("vrs:userHx:edit")
	String edit(@PathVariable("id") Integer id,Model model){

		UserHxDO userHx = userHxService.get(id);
		model.addAttribute("role",roleDao.get(Long.parseLong(userHx.getRid()+"")));
		model.addAttribute("userHx", userHx);
	    return "vrs/userHx/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("vrs:userHx:add")
	public R save( UserHxDO userHx){
		if(userHxService.save(userHx)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("vrs:userHx:edit")
	public R update( UserHxDO userHx){
		userHxService.update(userHx);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("vrs:userHx:remove")
	public R remove( Integer id){
		//不能删除
		return R.error("系统设置不能删除!");
		
	/*	if(userHxService.remove(id)>0){
		return R.ok();
		}*/
		//return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("vrs:userHx:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		userHxService.batchRemove(ids);
		return R.ok();
	}


	@Autowired
	private RoleDao roleDao;



}
