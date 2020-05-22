package com.bootdo.vr.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.domain.FileDO;
import com.bootdo.common.service.FileService;
import com.bootdo.common.utils.FileType;
import com.bootdo.common.utils.FileUtil;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.vr.domain.TopStyleDO;
import com.bootdo.vr.service.TopStyleService;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-04-13 04:52:20
 */
 
@Controller
@RequestMapping("/vr/topStyle")
public class TopStyleController {
	@Autowired
	private TopStyleService topStyleService;
	@Autowired
	private BootdoConfig bootdoConfig;
	@Autowired
	private FileService sysFileService;
	@GetMapping()
	@RequiresPermissions("vr:topStyle:topStyle")
	String TopStyle(){
	    return "vr/topStyle/topStyle";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("vr:topStyle:topStyle")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<TopStyleDO> topStyleList = topStyleService.list(query);
		int total = topStyleService.count(query);
		PageUtils pageUtils = new PageUtils(topStyleList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("vr:topStyle:add")
	String add(){
	    return "vr/topStyle/add";
	}

	@GetMapping("/edit/{topId}")
	@RequiresPermissions("vr:topStyle:edit")
	String edit(@PathVariable("topId") Integer topId,Model model){
		TopStyleDO topStyle = topStyleService.get(topId);
		model.addAttribute("topStyle", topStyle);
	    return "vr/topStyle/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("vr:topStyle:add")
	public R save( TopStyleDO topStyle){
		if(topStyleService.save(topStyle)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("vr:topStyle:edit")
	public R update( TopStyleDO topStyle){
		topStyleService.update(topStyle);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("vr:topStyle:remove")
	public R remove( Integer topId){
		if(topStyleService.remove(topId)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("vr:topStyle:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] topIds){
		topStyleService.batchRemove(topIds);
		return R.ok();
	}
	
	@ResponseBody
	@PostMapping("/uploadBigLog")
	@RequiresPermissions("vr:topStyle:add")
	public R uploadPic(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		
		String fileName = file.getOriginalFilename();
		fileName = FileUtil.renameToUUID(fileName).replaceAll("\\-", "");
		FileDO sysFile = new FileDO(FileType.fileType(fileName), "/files/toplogo/" + fileName, new Date());
		try {
			FileUtil.uploadFile(file.getBytes(), bootdoConfig.getUploadPath() + "toplogo/", fileName);
		} catch (Exception e) {
			return R.error();
		}

		if (sysFileService.save(sysFile) > 0) {
			return R.ok().put("fileName",sysFile.getUrl());
		}
		return R.error();
	}
}
