package com.bootdo.school.controller;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.school.common.MessageConstant;
import com.bootdo.school.domain.BusinessDO;
import com.bootdo.school.service.BusinessService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 商家表 封面图 经度纬度 富文本详细信息 等
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-07 20:19:47
 */
 
@Controller
@RequestMapping("/school/business")
public class BusinessController {
	@Autowired
	private BusinessService businessService;
	
	@GetMapping()
	@RequiresPermissions("school:business:business")
	String Business(){
	    return "school/business/business";
	}
	
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("school:business:business")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<BusinessDO> businessList = businessService.list(query);
		int total = businessService.count(query);
		PageUtils pageUtils = new PageUtils(businessList, total);
		return pageUtils;
	}
	
	
	@GetMapping("/add")
	@RequiresPermissions("school:business:add")
	String add(){
	    return "school/business/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("school:business:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		System.out.println(id+"sssssssssssssssssssss");
		BusinessDO business = businessService.get(id);
		model.addAttribute("business", business);
	    return "school/business/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("school:business:add")
	public R save( BusinessDO business){

		business.setCreatedate(new Date());
		business.setStatus(MessageConstant.SUCESS_STATUS);
		business.setName(business.getName());
		try {
			businessService.save(business);
			return 	R.ok();
		}
		catch (RuntimeException e){
			e.printStackTrace();
			return R.error(e.getMessage());

		}
		catch (Exception e){
			e.printStackTrace();
			return R.error(MessageConstant.USER_SAVE_ERROR);
		}


	}
	
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("school:business:edit")
	public R update( BusinessDO business){
		business.setUpdatedate(new Date());

		try {
			businessService.update(business);
		}
		catch (RuntimeException e){

			e.printStackTrace();
			return R.error(e.getMessage());
		}
		catch (Exception e){
			e.printStackTrace();
			return R.error(MessageConstant.UPDATE_BUSINE_ERROR);
		}

		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("school:business:remove")
	public R remove( Integer id){
		if(businessService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("school:business:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		businessService.batchRemove(ids);
		return R.ok();
	}
	
}
