package com.bootdo.vrs.controller;

import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.vrs.domain.HxLogDO;
import com.bootdo.vrs.service.HxLogService;
import com.bootdo.vrs.service.UserAllotService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-24 21:35:02
 */
 
@Controller
@RequestMapping("/vrs/hxLog")
public class HxLogController extends BaseController {
	@Autowired
	private HxLogService hxLogService;

	@Autowired
	private UserAllotService userAllotService;
	
	@GetMapping()
	@RequiresPermissions("vrs:hxLog:hxLog")
	String HxLog(ModelMap map)

	{
		map.put("ulist",userAllotService.list(null));
	    return "vrs/hxLog/hxLog";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("vrs:hxLog:hxLog")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
		if (getUserId()!=1&&getUserId()!=138){
			params.put("pid",getUserId());
		}
        Query query = new Query(params);
		List<HxLogDO> hxLogList = hxLogService.list(query);
		int total = hxLogService.count(query);
		PageUtils pageUtils = new PageUtils(hxLogList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("vrs:hxLog:add")
	String add(){
	    return "vrs/hxLog/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("vrs:hxLog:edit")
	String edit(@PathVariable("id") Long id,Model model){
		HxLogDO hxLog = hxLogService.get(id);
		model.addAttribute("hxLog", hxLog);
	    return "vrs/hxLog/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("vrs:hxLog:add")
	public R save( HxLogDO hxLog){
		if(hxLogService.save(hxLog)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("vrs:hxLog:edit")
	public R update( HxLogDO hxLog){
		hxLogService.update(hxLog);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("vrs:hxLog:remove")
	public R remove( Long id){
		if(hxLogService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("vrs:hxLog:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		hxLogService.batchRemove(ids);
		return R.ok();
	}
	
}
