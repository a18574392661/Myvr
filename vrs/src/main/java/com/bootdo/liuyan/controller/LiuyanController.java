package com.bootdo.liuyan.controller;

import java.util.List;
import java.util.Map;

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

import com.bootdo.liuyan.domain.LiuyanDO;
import com.bootdo.liuyan.service.LiuyanService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-04-03 11:15:18
 */
 
@Controller
@RequestMapping("/liuyan/liuyan")
public class LiuyanController {
	@Autowired
	private LiuyanService liuyanService;
	
	@GetMapping()
	@RequiresPermissions("liuyan:liuyan:liuyan")
	String Liuyan(){
	    return "liuyan/liuyan/liuyan";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("liuyan:liuyan:liuyan")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<LiuyanDO> liuyanList = liuyanService.list(query);
		int total = liuyanService.count(query);
		PageUtils pageUtils = new PageUtils(liuyanList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("liuyan:liuyan:add")
	String add(){
	    return "liuyan/liuyan/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("liuyan:liuyan:edit")
	String edit(@PathVariable("id") Long id,Model model){
		LiuyanDO liuyan = liuyanService.get(id);
		model.addAttribute("liuyan", liuyan);
	    return "liuyan/liuyan/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("liuyan:liuyan:add")
	public R save( LiuyanDO liuyan){
		if(liuyanService.save(liuyan)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("liuyan:liuyan:edit")
	public R update( LiuyanDO liuyan){
		liuyanService.update(liuyan);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("liuyan:liuyan:remove")
	public R remove( Long id){
		if(liuyanService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("liuyan:liuyan:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		liuyanService.batchRemove(ids);
		return R.ok();
	}
	
}
