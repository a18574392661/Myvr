package com.bootdo.vrs.controller;

import java.util.List;
import java.util.Map;

import com.bootdo.vrs.dao.IpgetCountDao;
import com.bootdo.vrs.domain.ProDO;
import com.bootdo.vrs.service.ProService;
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

import com.bootdo.vrs.domain.IpgetCountDO;
import com.bootdo.vrs.service.IpgetCountService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 图片浏览量
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-05-21 11:56:29
 */
 
@Controller
@RequestMapping("/vrs/ipgetCount")
public class IpgetCountController {
	@Autowired
	private IpgetCountService ipgetCountService;

	@Autowired
	private IpgetCountDao ipgetCountDao;

	@Autowired
	private ProService proService;
	
	@GetMapping()
	@RequiresPermissions("vrs:ipgetCount:ipgetCount")
	String IpgetCount(){
	    return "vrs/ipgetCount/ipgetCount";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("vrs:ipgetCount:ipgetCount")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<IpgetCountDO> ipgetCountList = ipgetCountService.list(query);
		int total = ipgetCountService.count(query);
		PageUtils pageUtils = new PageUtils(ipgetCountList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("vrs:ipgetCount:add")
	String add(ModelMap map){
			//查询没有不重复的图片
		List<ProDO> list=ipgetCountDao.queryNotPro();
		map.put("list",list);

	    return "vrs/ipgetCount/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("vrs:ipgetCount:edit")
	String edit(@PathVariable("id") Long id,Model model){
		IpgetCountDO ipgetCount = ipgetCountService.get(id);
		model.addAttribute("ipgetCount", ipgetCount);
	    return "vrs/ipgetCount/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("vrs:ipgetCount:add")
	public R save( IpgetCountDO ipgetCount){
		if(ipgetCountService.save(ipgetCount)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("vrs:ipgetCount:edit")
	public R update( IpgetCountDO ipgetCount){
		ipgetCountService.update(ipgetCount);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("vrs:ipgetCount:remove")
	public R remove( Long id){
		if(ipgetCountService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("vrs:ipgetCount:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		ipgetCountService.batchRemove(ids);
		return R.ok();
	}


	@RequestMapping("/queryProid")
	@ResponseBody
	public  ProDO queryProid(Integer id){

		return  proService.get(id);
	}
	
}
