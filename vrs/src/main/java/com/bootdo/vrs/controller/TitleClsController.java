package com.bootdo.vrs.controller;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.vrs.common.MessageConstantVrs;
import com.bootdo.vrs.domain.TitleClsDO;
import com.bootdo.vrs.service.TitleClsService;
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
 * @date 2020-04-13 14:20:14
 */
 
@Controller
@RequestMapping("/vrs/titleCls")
public class TitleClsController {
	@Autowired
	private TitleClsService titleClsService;
	
	@GetMapping()
	@RequiresPermissions("vrs:titleCls:titleCls")
	String TitleCls(){
	    return "vrs/titleCls/titleCls";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("vrs:titleCls:titleCls")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<TitleClsDO> titleClsList = titleClsService.list(query);
		int total = titleClsService.count(query);
		PageUtils pageUtils = new PageUtils(titleClsList, total);
		return pageUtils;
	}


	@ResponseBody
	@RequestMapping("/titleAll")
	@RequiresPermissions("vrs:titleCls:titleCls")
	public List<TitleClsDO> titleAll(String pid){
		//查询列表数据

		List<TitleClsDO> titleClsList = titleClsService.titleAll(pid);


		return titleClsList;
	}





	@GetMapping("/add")
	@RequiresPermissions("vrs:titleCls:add")
	String add(){
	    return "vrs/titleCls/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("vrs:titleCls:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		TitleClsDO titleCls = titleClsService.get(id);
		model.addAttribute("titleCls", titleCls);
	    return "vrs/titleCls/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("vrs:titleCls:add")
	public R save( TitleClsDO titleCls){
		if(titleClsService.save(titleCls)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("vrs:titleCls:edit")
	public R update( TitleClsDO titleCls){
		titleClsService.update(titleCls);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("vrs:titleCls:remove")
	public R remove( Integer id){

		return R.error(MessageConstantVrs.ADMIN_ERROR);
		/*if(titleClsService.remove(id)>0){
		return R.ok();
		}
		return R.error();*/
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("vrs:titleCls:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		titleClsService.batchRemove(ids);
		return R.ok();
	}


	@GetMapping("/getTypeid/{id}")
	@RequiresPermissions("vrs:titleCls:edit")
	@ResponseBody
	TitleClsDO getTypeid(@PathVariable("id") Integer id){
		TitleClsDO titleCls = titleClsService.get(id);

		return titleCls;
	}




	
}
