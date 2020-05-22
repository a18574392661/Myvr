package com.bootdo.vrs.controller;

import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.vrs.common.MessageConstantVrs;
import com.bootdo.vrs.domain.ImgCls4DO;
import com.bootdo.vrs.domain.TitleClsDO;
import com.bootdo.vrs.service.ImgCls4Service;
import com.bootdo.vrs.service.TitleClsService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 第四个分类表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2020-04-14 14:01:20
 */
 
@Controller
@RequestMapping("/vrs/imgCls4")
public class ImgCls4Controller {
	@Autowired
	private ImgCls4Service imgCls4Service;

	@Autowired
	private TitleClsService titleClsService;
	
	@GetMapping()
	@RequiresPermissions("vrs:imgCls4:imgCls4")
	String ImgCls4(){
	    return "vrs/imgCls4/imgCls4";
	}
	
	//获取所属分类
	@ResponseBody
	@GetMapping("/getCategory")
	ImgCls4DO getCategory(Integer id){
		ImgCls4DO imgCls4 = imgCls4Service.getCategory(id);
	    return imgCls4;
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("vrs:imgCls4:imgCls4")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<ImgCls4DO> imgCls4List = imgCls4Service.list(query);
		int total = imgCls4Service.count(query);
		PageUtils pageUtils = new PageUtils(imgCls4List, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("vrs:imgCls4:add")
	String add(Model model){
		//添加所属分类-门窗设计
		TitleClsDO queryTypeId = titleClsService.queryTypeId(MessageConstantVrs.MENGCHUANGSHEJI);
		model.addAttribute("menu", queryTypeId);
	    return "vrs/imgCls4/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("vrs:imgCls4:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		ImgCls4DO imgCls4 = imgCls4Service.get(id);
		//所属分类
		TitleClsDO queryTypeId = titleClsService.queryTypeId(MessageConstantVrs.MENGCHUANGSHEJI);
		model.addAttribute("menu", queryTypeId);
		model.addAttribute("imgCls4", imgCls4);
	    return "vrs/imgCls4/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("vrs:imgCls4:add")
	public R save( ImgCls4DO imgCls4){
		System.out.println( imgCls4 );
		System.out.println( imgCls4.getCid() );
		//设置所属分类-门窗设置
		if(imgCls4Service.save(imgCls4)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("vrs:imgCls4:edit")
	public R update( ImgCls4DO imgCls4){
		imgCls4Service.update(imgCls4);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("vrs:imgCls4:remove")
	public R remove( Integer id){
		
		//分类id下是否存在
		Map<String, Object> rootMap = MessageConstantVrs.getRootMap();
		rootMap.put("id", id);
		//级联查询
		List<Map<String, String>> contains = imgCls4Service.contains(rootMap);
		if(contains.size() > 0) {
			String name = contains.get(0).get("categoryname");
			return R.error(200, name+MessageConstantVrs.WARNINGMSG);
		}
		
		if(imgCls4Service.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("vrs:imgCls4:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		
		List<Map<String, String>> contains = null;
		Map<String, Object> rootMap = MessageConstantVrs.getRootMap();
		boolean flag = true;
		StringBuilder sb = new StringBuilder();
		for (Integer id : ids) {
			//key override
			rootMap.put("id", id);
			//级联查询
			contains = imgCls4Service.contains(rootMap);
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
		
		imgCls4Service.batchRemove(ids);
		return R.ok();
	}
	
}
