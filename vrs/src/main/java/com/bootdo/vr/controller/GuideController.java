package com.bootdo.vr.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.multipart.MultipartFile;

import com.bootdo.vr.domain.GuideDO;
import com.bootdo.vr.service.GuideService;
import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.domain.FileDO;
import com.bootdo.common.service.FileService;
import com.bootdo.common.utils.FileType;
import com.bootdo.common.utils.FileUtil;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 引导图
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-05-04 14:16:25
 */
 
@Controller
@RequestMapping("/vr/guide")
public class GuideController {
	@Autowired
	private GuideService guideService;
	@Autowired
	private BootdoConfig bootdoConfig;
	@Autowired
	private FileService sysFileService;
	@GetMapping()
	@RequiresPermissions("vr:guide:guide")
	String Guide(){
	    return "vr/guide/guide";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("vr:guide:guide")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<GuideDO> guideList = guideService.list(query);
		int total = guideService.count(query);
		PageUtils pageUtils = new PageUtils(guideList, total);
		return pageUtils;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("vr:guide:add")
	String add(){
	    return "vr/guide/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("vr:guide:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		GuideDO guide = guideService.get(id);
		model.addAttribute("guide", guide);
	    return "vr/guide/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("vr:guide:add")
	public R save( GuideDO guide){
		if(guideService.save(guide)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("vr:guide:edit")
	public R update( GuideDO guide){
		guideService.update(guide);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("vr:guide:remove")
	public R remove( Integer id){
		if(guideService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("vr:guide:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		guideService.batchRemove(ids);
		return R.ok();
	}
	
	@ResponseBody
	@PostMapping("/uploadGuideLogo")
	@RequiresPermissions("vr:guide:add")
	public R uploadColorLogo(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		
		String fileName = file.getOriginalFilename();
		fileName = FileUtil.renameToUUID(fileName).replaceAll("\\-", "");
		FileDO sysFile = new FileDO(FileType.fileType(fileName), "/files/guide/" + fileName, new Date());
		try {
			FileUtil.uploadFile(file.getBytes(), bootdoConfig.getUploadPath() + "guide/", fileName);
		} catch (Exception e) {
			return R.error();
		}

		if (sysFileService.save(sysFile) > 0) {
			return R.ok().put("fileName",sysFile.getUrl());
		}
		return R.error();
	}
	
}
