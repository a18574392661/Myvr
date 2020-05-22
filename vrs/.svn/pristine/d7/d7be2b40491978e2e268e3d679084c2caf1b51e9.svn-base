package com.bootdo.vrs.controller;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.vrs.domain.ImgsSytDO;
import com.bootdo.vrs.service.ImgsSytService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 缩月图标
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-18 09:59:38
 */
 
@Controller
@RequestMapping("/vrs/imgsSyt")
public class ImgsSytController {
	@Autowired
	private ImgsSytService imgsSytService;
	
	@GetMapping()
	@RequiresPermissions("vrs:imgsSyt:imgsSyt")
	String ImgsSyt(Integer cid, ModelMap mpp){
		mpp.put("cid",cid);
	    return "vrs/imgsSyt/imgsSyt";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("vrs:imgsSyt:imgsSyt")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<ImgsSytDO> imgsSytList = imgsSytService.list(query);
		System.out.println(imgsSytList.size());
		int total = imgsSytService.count(query);
		System.out.println(total);
		PageUtils pageUtils = new PageUtils(imgsSytList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("vrs:imgsSyt:add")
	String add(ModelMap map,Integer cid)

	{
		map.put("cid",cid);
	    return "vrs/imgsSyt/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("vrs:imgsSyt:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		ImgsSytDO imgsSyt = imgsSytService.get(id);
		model.addAttribute("imgsSyt", imgsSyt);
	    return "vrs/imgsSyt/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("vrs:imgsSyt:add")
	public R save( ImgsSytDO imgsSyt){
		if(imgsSytService.save(imgsSyt)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("vrs:imgsSyt:edit")
	public R update( ImgsSytDO imgsSyt){
		imgsSytService.update(imgsSyt);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("vrs:imgsSyt:remove")
	public R remove( Integer id){
		if(imgsSytService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("vrs:imgsSyt:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids,Integer cid){
		try {
			imgsSytService.batchRemove(ids,cid);
		}
		catch (RuntimeException e){

			e.printStackTrace();
			return R.error(e.getMessage());
		}
		catch (Exception e){
			e.printStackTrace();
		}

		 return R.ok();
	}
	
}
