package com.bootdo.vrs.controller;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.vrs.common.MessageConstantVrs;
import com.bootdo.vrs.domain.ImgCls3DO;
import com.bootdo.vrs.domain.TitleClsDO;
import com.bootdo.vrs.service.ImgCls3Service;
import com.bootdo.vrs.service.TitleClsService;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 第二个分类表 高端画册
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-14 11:13:55
 */
 
@Controller
@RequestMapping("/vrs/imgCls3")
public class ImgCls3Controller {
	@Autowired
	private ImgCls3Service imgCls3Service;

	@Autowired
	private TitleClsService titleClsService;
	
	@GetMapping()
	@RequiresPermissions("vrs:imgCls3:imgCls3")
	String ImgCls3(){
	    return "vrs/imgCls3/imgCls3";
	}
	
	//获取所属分类
	@ResponseBody
	@GetMapping("/getCategory")
	ImgCls3DO getCategory(Integer id){
		ImgCls3DO imgCls3 = imgCls3Service.getCategory(id);
	    return imgCls3;
	}
	
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("vrs:imgCls3:imgCls3")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
		String offset = String.valueOf(params.get("offset") );
		String limit = String.valueOf(params.get("limit") );
		Query query = null;
		if(!StringUtils.isEmpty(offset) && !StringUtils.isEmpty(offset)) {
			query = new Query(params);
		}
		List<ImgCls3DO> imgCls3List = imgCls3Service.list(query);
		int total = imgCls3Service.count(query);
		PageUtils pageUtils = new PageUtils(imgCls3List, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("vrs:imgCls3:add")
	String add(Model model){
		//添加所属分类-高端画册
		TitleClsDO queryTypeId = titleClsService.queryTypeId(MessageConstantVrs.HUACEXITON);
		model.addAttribute("menu", queryTypeId);
		
	    return "vrs/imgCls3/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("vrs:imgCls3:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		ImgCls3DO imgCls3 = imgCls3Service.get(id);
		//所属分类
		TitleClsDO queryTypeId = titleClsService.queryTypeId(MessageConstantVrs.HUACEXITON);
		model.addAttribute("menu", queryTypeId);
		model.addAttribute("imgCls3", imgCls3);
	    return "vrs/imgCls3/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("vrs:imgCls3:add")
	public R save( ImgCls3DO imgCls3){

		//设置分类-高端画册
		if(imgCls3Service.save(imgCls3)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("vrs:imgCls3:edit")
	public R update( ImgCls3DO imgCls3){
		imgCls3Service.update(imgCls3);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("vrs:imgCls3:remove")
	public R remove( Integer id){
		
		//分类id下是否存在
		Map<String, Object> rootMap = MessageConstantVrs.getRootMap();
		rootMap.put("id", id);
		//级联查询
		List<Map<String, String>> contains = imgCls3Service.contains(rootMap);
		if(contains.size() > 0) {
			String name = contains.get(0).get("categoryname");
			return R.error(200, name+MessageConstantVrs.WARNINGMSG);
		}
		
		if(imgCls3Service.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("vrs:imgCls3:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		
		List<Map<String, String>> contains = null;
		Map<String, Object> rootMap = MessageConstantVrs.getRootMap();
		boolean flag = true;
		StringBuilder sb = new StringBuilder();
		for (Integer id : ids) {
			//key override
			rootMap.put("id", id);
			//级联查询
			contains = imgCls3Service.contains(rootMap);
			if(contains.size() > 0) {
				flag = false;
				sb.append(contains.get(0).get("categoryname")+"、");
			}
		}
		//截取
		String name = sb.length() == 0 ? "" : sb.substring(0, sb.length()-1);
		if(!flag) {
			return R.error(200, name+MessageConstantVrs.WARNINGMSG);
		}
		
		imgCls3Service.batchRemove(ids);
		return R.ok();
	}
	
}
