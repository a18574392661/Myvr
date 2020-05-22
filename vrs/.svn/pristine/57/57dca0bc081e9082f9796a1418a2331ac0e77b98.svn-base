package com.bootdo.vr.controller;

import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.domain.FileDO;
import com.bootdo.common.service.FileService;
import com.bootdo.common.utils.*;
import com.bootdo.vr.domain.ColorTypeDO;
import com.bootdo.vr.domain.ProductColorsDO;
import com.bootdo.vr.domain.ProductLeftStyleDO;
import com.bootdo.vr.service.*;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-04-13 04:52:19
 */
 
@Controller
@RequestMapping("/vr/colorType")
public class ColorTypeController {
	@Autowired
	private ColorTypeService colorTypeService;
	@Autowired
	private BootdoConfig bootdoConfig;
	@Autowired
	private FileService sysFileService;
	
	@Autowired
	private RomeTypeService romeTypeService;
	@Autowired
	private TopStyleService topStyleService;
	@Autowired
	private LeftStyleService leftStyleService;
	
	@Autowired
	private ProductLeftStyleService productLeftStyleService;
	
	@Autowired
	private ProductColorsService productColorsService;
	
	@GetMapping()
	@RequiresPermissions("vr:colorType:colorType")
	String ColorType(Model model){
		model.addAttribute("topStyle", topStyleService.list(new HashMap<String, Object>()));
	    return "vr/colorType/colorType";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("vr:colorType:colorType")
	public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据

        Query query = new Query(params);
		List<ColorTypeDO> colorTypeList = colorTypeService.list(query);
		int total = colorTypeService.count(query);
		PageUtils pageUtils = new PageUtils(colorTypeList, total);
		return pageUtils;
	}
	
	@ResponseBody
	@GetMapping("/productTree")
	@RequiresPermissions("vr:colorType:colorType")
	public List<Map<String, Object>> queryForProductTree(String leftStyleIds){
		//选择多个 
		String[] ids = leftStyleIds.split(",");
		
		Integer[] lids = new Integer[ids.length];
		for( int i = 0; i < ids.length; i++ ) {
			lids[i] = Integer.parseInt(ids[i]);
		}
		
		List<Map<String, Object>> map = colorTypeService.queryForProductTree(lids);
		return map;
	}
	
	@ResponseBody
	@GetMapping("/productTreeByProductId")
	@RequiresPermissions("vr:colorType:colorType")
	public List<Map<String, Object>> productTreeByProductId(Integer productId){
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("productId", productId);
		List<ProductLeftStyleDO> list = productLeftStyleService.list(params);
		
		Integer[] lids = new Integer[list.size()];
		for( int i = 0; i < list.size(); i++ ) {
			lids[i] = list.get(i).getLeftStyleId();
		}
		
		List<Map<String, Object>> map = colorTypeService.queryForProductTree(lids);
		return map;
	}
	
	@ResponseBody
	@GetMapping("/productColorIds")
	@RequiresPermissions("vr:colorType:colorType")
	public List<ProductColorsDO> productColorIds(Integer productId){
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("productId", productId);
		List<ProductColorsDO> list = productColorsService.list(params);
		return list;
	}
	
	@GetMapping("/add")
	@RequiresPermissions("vr:colorType:add")
	String add(Model model){
		model.addAttribute("topStyles", topStyleService.list(new HashMap<String, Object>()));
	    return "vr/colorType/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("vr:colorType:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		ColorTypeDO colorType = colorTypeService.get(id);
		model.addAttribute("colorType", colorType);
		model.addAttribute("topStyles", topStyleService.list(new HashMap<String, Object>()));
		model.addAttribute("leftStyles", leftStyleService.queryByTopStyleId(colorType.getTopStyleId()));
		model.addAttribute("roomTypes", romeTypeService.queryByLeftStyleId(colorType.getLeftStyleId()));
	    return "vr/colorType/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("vr:colorType:add")
	public R save( ColorTypeDO colorType){
		if(colorTypeService.save(colorType)>0){
			return R.ok();
		}
		return R.error();
	}
	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("vr:colorType:edit")
	public R update( ColorTypeDO colorType){
		colorTypeService.update(colorType);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("vr:colorType:remove")
	public R remove( Integer id){
		if(colorTypeService.remove(id)>0){
		return R.ok();
		}
		return R.error();
	}
	
	/**
	 * 删除
	 */
	@PostMapping( "/batchRemove")
	@ResponseBody
	@RequiresPermissions("vr:colorType:batchRemove")
	public R remove(@RequestParam("ids[]") Integer[] ids){
		colorTypeService.batchRemove(ids);
		return R.ok();
	}
	
	@ResponseBody
	@PostMapping("/uploadColorLogo")
	@RequiresPermissions("vr:leftStyle:add")
	public R uploadColorLogo(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		
		String fileName = file.getOriginalFilename();
		fileName = FileUtil.renameToUUID(fileName).replaceAll("\\-", "");
		FileDO sysFile = new FileDO(FileType.fileType(fileName), "/files/colorlogo/" + fileName, new Date());
		try {
			FileUtil.uploadFile(file.getBytes(), bootdoConfig.getUploadPath() + "colorlogo/", fileName);
		} catch (Exception e) {
			return R.error();
		}

		if (sysFileService.save(sysFile) > 0) {
			return R.ok().put("fileName",sysFile.getUrl());
		}
		return R.error();
	}
	
	@ResponseBody
	@PostMapping("/uploadQjt")
	@RequiresPermissions("vr:leftStyle:add")
	public R uploadQjt(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		String fileName = file.getOriginalFilename();
		fileName = FileUtil.renameToUUID(fileName).replaceAll("\\-", "");
		FileDO sysFile = new FileDO(FileType.fileType(fileName), "/files/qjt/" + fileName, new Date());
		try {
			FileUtil.uploadFile(file.getBytes(), bootdoConfig.getUploadPath() + "qjt/", fileName);
		}
		catch (Exception e){
			return  R.error();
		}
		if (sysFileService.save(sysFile) > 0) {
			//图片压缩上传小图片
			String imgUrl=saveMinImg(bootdoConfig.getUploadPath() + "qjt/",sysFile.getUrl());
			//运行java，bat切全景图
			runbat(bootdoConfig.getUploadPath() + "qjt/" + fileName);
			Map<String,Object> result=new HashMap();
			result.put("simg",imgUrl);
			result.put("fileName",sysFile.getUrl());
			return R.ok(result);
		}
		return R.error();
	}

	public  String  saveMinImg(String path,String url){
		String imgUrl="";
		int end = url.lastIndexOf("/");
		String result=url.substring(0,end);
		String newName="s"+url.substring(end+1,url.length());
		// 缩小
		try {
			Thumbnails.of(path+url.substring(end+1,url.length())).size(300,300).toFile(path+"/"+newName);

			imgUrl=result+"/"+newName;
		}
		catch (Exception e){
				e.printStackTrace();
		}


		//Thumbnails.of(result + "qjt.jpg").scale(0.5f).toFile(result + "image_0.5f.jpg");
	//	r.put("simg",simg)

		return imgUrl;

	}


	public static void main(String[] args) {
		try {
			 String result = "src/main/resources/images/";
			// 缩小	 Thumbnails.of(result + "qjt.jpg").size(300, 300).toFile(result+"image_200x300.jpg");

			//按照比例压缩
			Thumbnails.of(result + "e53339cd11f54c0a8887536d604140b2.jpg").size(3000,3000).toFile(result + "image_0.5f.jpg");
		} catch (IOException e) {
			e.printStackTrace();
		}


	}

	private void runbat(String picUrl) {
		try {
			String os = System.getProperty("os.name"); 
			if(os.toLowerCase().startsWith("win")){ 
				String cmd = "cmd /c start d:/var/krpano-1.19-pr13/droplet.bat " + picUrl;
				FileUtil.deleteFile(bootdoConfig.getUploadPath() + "qjt/vtour/tour.xml");
				Runtime.getRuntime().exec(cmd);
				FileUtil.deleteFile(bootdoConfig.getUploadPath() + "qjt/vtour/tour.xml");
			} else {
				//String linuxCmd = "/opt/project/dcsys/krpano-1.19-pr16/krpanotools makepano -config=templates/vtour-normal.config " + picUrl;
				String linuxCmd = "/home/krpano-1.19-pr16/krpanotools makepano -config=templates/vtour-normal.config " + picUrl;
				Runtime.getRuntime().exec(linuxCmd);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
