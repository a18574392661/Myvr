package com.bootdo.school.controller;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.school.dao.UserCouponLogDao;
import com.bootdo.school.domain.UserCouponLogDO;
import com.bootdo.school.service.UserCouponLogService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-08 16:24:33
 */
 
@Controller
@RequestMapping("/school/userCouponLog")
public class UserCouponLogController {
	@Autowired
	private UserCouponLogService userCouponLogService;
	@Autowired
	private UserCouponLogDao userCouponLogDao;
	
	@GetMapping()
	@RequiresPermissions("school:userCouponLog:userCouponLog")
	String UserCouponLog(){
	    return "school/userCouponLog/userCouponLog";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("school:userCouponLog:userCouponLog")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<UserCouponLogDO> userCouponLogList = userCouponLogService.list(query);
		int total = userCouponLogService.count(query);
		PageUtils pageUtils = new PageUtils(userCouponLogList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("school:userCouponLog:add")
	String add(){
	    return "school/userCouponLog/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("school:userCouponLog:edit")
	String edit(@PathVariable("id") Long id,Model model){
		UserCouponLogDO userCouponLog = userCouponLogService.get(id);
		model.addAttribute("userCouponLog", userCouponLog);

		return "school/userCouponLog/edit";
	}


	@GetMapping("/editStatus/{id}")
	@RequiresPermissions("school:userCouponLog:edit")
	String editStatus(@PathVariable("id") Long id,Model model){
		List<String> userCouponLog = userCouponLogDao.queryStatus(id+"");
		model.addAttribute("payStatus", userCouponLog.get(0));
		model.addAttribute("id", id);
		return "school/userCouponLog/edit";
	}

	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("school:userCouponLog:add")
	public R save( UserCouponLogDO userCouponLog){
		if(userCouponLogService.save(userCouponLog)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("school:userCouponLog:edit")
	public R update( UserCouponLogDO userCouponLog){
		userCouponLogService.update(userCouponLog);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("school:userCouponLog:remove")
	public R remove( Long id){
		if(userCouponLogService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("school:userCouponLog:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids){
		userCouponLogService.batchRemove(ids);
		return R.ok();
	}
	
}
