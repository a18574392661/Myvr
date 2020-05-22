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

import com.bootdo.vr.domain.ProductLeftStyleDO;
import com.bootdo.vr.domain.RoomTypeImgsDO;
import com.bootdo.vr.service.ProductImgsService;
import com.bootdo.vr.service.ProductLeftStyleService;
import com.bootdo.vr.service.RoomTypeImgsService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 房间类型对应的热点图表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-08-11 16:26:12
 */
 
@Controller
@RequestMapping("/vr/roomTypeImgs")
public class RoomTypeImgsController {
	@Autowired
	private RoomTypeImgsService roomTypeImgsService;
	
	@Autowired
	private ProductImgsService productImgsService;
	
	@Autowired
	private ProductLeftStyleService productLeftStyleService;
	
	@GetMapping()
	@RequiresPermissions("vr:roomTypeImgs:roomTypeImgs")
	String RoomTypeImgs(){
	    return "vr/roomTypeImgs/roomTypeImgs";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("vr:roomTypeImgs:roomTypeImgs")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<RoomTypeImgsDO> roomTypeImgsList = roomTypeImgsService.list(query);
		int total = roomTypeImgsService.count(query);
		PageUtils pageUtils = new PageUtils(roomTypeImgsList, total);
		return pageUtils;
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
		
		List<Map<String, Object>> map = productImgsService.queryForRdtTree(lids);
		return map;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("vr:roomTypeImgs:add")
	String add(){
	    return "vr/roomTypeImgs/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("vr:roomTypeImgs:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		RoomTypeImgsDO roomTypeImgs = roomTypeImgsService.get(id);
		model.addAttribute("roomTypeImgs", roomTypeImgs);
	    return "vr/roomTypeImgs/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("vr:roomTypeImgs:add")
	public R save( RoomTypeImgsDO roomTypeImgs){
		if(roomTypeImgsService.save(roomTypeImgs)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("vr:roomTypeImgs:edit")
	public R update( RoomTypeImgsDO roomTypeImgs){
		roomTypeImgsService.update(roomTypeImgs);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("vr:roomTypeImgs:remove")
	public R remove( Integer id){
		if(roomTypeImgsService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("vr:roomTypeImgs:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		roomTypeImgsService.batchRemove(ids);
		return R.ok();
	}
	
}
