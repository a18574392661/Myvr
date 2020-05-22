package com.bootdo.vr.controller;

import java.util.HashMap;
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

import com.bootdo.vr.domain.LeftStyleImgsDO;
import com.bootdo.vr.domain.ProductLeftStyleDO;
import com.bootdo.vr.service.LeftStyleImgsService;
import com.bootdo.vr.service.ProductLeftStyleImgsService;
import com.bootdo.vr.service.ProductLeftStyleService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 关联小风格的热点图表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-08-11 20:10:52
 */
 
@Controller
@RequestMapping("/vr/leftStyleImgs")
public class LeftStyleImgsController {
	@Autowired
	private LeftStyleImgsService leftStyleImgsService;
	
	@Autowired
	private ProductLeftStyleService productLeftStyleService;
	
	@Autowired
	private ProductLeftStyleImgsService productLeftStyleImgsService;
	
	@GetMapping()
	@RequiresPermissions("vr:leftStyleImgs:leftStyleImgs")
	String LeftStyleImgs(){
	    return "vr/leftStyleImgs/leftStyleImgs";
	}
	
	@ResponseBody
	@GetMapping("/rdtTreeByProductId")
	@RequiresPermissions("vr:colorType:colorType")
	public List<Map<String, Object>> rdtTreeByProductId(Integer productId){
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("productId", productId);
		List<ProductLeftStyleDO> list = productLeftStyleService.list(params);
		
		Integer[] lids = new Integer[list.size()];
		for( int i = 0; i < list.size(); i++ ) {
			lids[i] = list.get(i).getLeftStyleId();
		}
		
		List<Map<String, Object>> map = productLeftStyleImgsService.queryForLeftRdtTree(lids);
		return map;
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("vr:leftStyleImgs:leftStyleImgs")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<LeftStyleImgsDO> leftStyleImgsList = leftStyleImgsService.list(query);
		int total = leftStyleImgsService.count(query);
		PageUtils pageUtils = new PageUtils(leftStyleImgsList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("vr:leftStyleImgs:add")
	String add(){
	    return "vr/leftStyleImgs/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("vr:leftStyleImgs:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		LeftStyleImgsDO leftStyleImgs = leftStyleImgsService.get(id);
		model.addAttribute("leftStyleImgs", leftStyleImgs);
	    return "vr/leftStyleImgs/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("vr:leftStyleImgs:add")
	public R save( LeftStyleImgsDO leftStyleImgs){
		if(leftStyleImgsService.save(leftStyleImgs)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("vr:leftStyleImgs:edit")
	public R update( LeftStyleImgsDO leftStyleImgs){
		leftStyleImgsService.update(leftStyleImgs);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("vr:leftStyleImgs:remove")
	public R remove( Integer id){
		if(leftStyleImgsService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("vr:leftStyleImgs:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		leftStyleImgsService.batchRemove(ids);
		return R.ok();
	}
	
}
