package com.bootdo.vr.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bootdo.vr.domain.ProductColorsDO;
import com.bootdo.vr.domain.ProductImgsDO;
import com.bootdo.vr.domain.ProductInfoDO;
import com.bootdo.vr.domain.ProductLeftStyleImgsDO;
import com.bootdo.vr.service.ProductActiveCodeService;
import com.bootdo.vr.service.ProductColorsService;
import com.bootdo.vr.service.ProductImgsService;
import com.bootdo.vr.service.ProductInfoService;
import com.bootdo.vr.service.ProductLeftStyleImgsService;
import com.bootdo.vr.service.ProductLeftStyleService;
import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.domain.FileDO;
import com.bootdo.common.service.FileService;
import com.bootdo.common.utils.FileType;
import com.bootdo.common.utils.FileUtil;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * 产品套餐基本信息表
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-04-28 15:59:55
 */
 
@Controller
@RequestMapping("/vr/productInfo")
public class ProductInfoController {
	@Autowired
	private ProductInfoService productInfoService;
	@Autowired
	private ProductLeftStyleService productLeftStyleService;
	@Autowired
	private BootdoConfig bootdoConfig;
	@Autowired
	private FileService sysFileService;
	@Autowired
	private ProductActiveCodeService productActiveCodeService;
	@Autowired
	private ProductImgsService productImgsService;
	@Autowired
	private ProductColorsService productColorsService;
	@Autowired
	private ProductLeftStyleImgsService productLeftStyleImgsService;
	@GetMapping()
	@RequiresPermissions("vr:topStyle:topStyle")
	String ProductInfo(){
	    return "vr/productInfo/productInfo";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("vr:topStyle:topStyle")
	public PageUtils list(@RequestParam Map<String, Object> params){
		
		if( params.get("status") == null || params.get("status").equals("") ) {
			params.put("status", null);
		}
		
		//查询列表数据
        Query query = new Query(params);
		List<ProductInfoDO> productInfoList = productInfoService.list(query);
		int total = productInfoService.count(query);
		PageUtils pageUtils = new PageUtils(productInfoList, total);
		return pageUtils;
	}
	
	@GetMapping("/lookDetails/{id}")
	@RequiresPermissions("vr:topStyle:topStyle")
	String lookDetails(@PathVariable("id") Integer id,Model model) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("productId", id);
		//得到所有的产品信息
		model.addAttribute("selectProductLeftStyles", productLeftStyleService.list(map));
		model.addAttribute("id", id);
		return "vr/productInfo/productDetails";
	}
	
	/**
	 * 查询激活码
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/activeCode/{id}")
	@RequiresPermissions("vr:topStyle:topStyle")
	String activeCode(@PathVariable("id") Integer id,Model model) {
		model.addAttribute("productId", id);
		return "vr/productActiveCode/productActiveCode";
	}
	
	
	@GetMapping("/add/{leftIds}")
	@RequiresPermissions("vr:topStyle:add")
	String add(@PathVariable("leftIds") String leftIds,Model model){
		model.addAttribute("leftStyleIds", leftIds);
	    return "vr/buildProduct";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("vr:topStyle:edit")
	String edit(@PathVariable("id") Integer id,Model model){
		ProductInfoDO productInfo = productInfoService.get(id);
		model.addAttribute("productInfo", productInfo);
	    return "vr/productInfo/edit";
	}
	
	@GetMapping("/editRdt/{id}")
	@RequiresPermissions("vr:topStyle:edit")
	String editRdt(@PathVariable("id") Integer id,Model model){
		//查询产品的热点图		
		ProductInfoDO productInfo = productInfoService.get(id);
		model.addAttribute("productInfo", productInfo);
	    return "vr/productInfo/editRdt";
	}
	
	/**
	 * 修改热点图
	 */
	@Transactional
	@ResponseBody
	@RequestMapping("/updateRdt")
	@RequiresPermissions("vr:topStyle:edit")
	public R updateRdt( ProductInfoDO productInfo, @RequestParam("romeImgs") Integer[] romeImgs){
		productInfoService.update(productInfo);
		
		//移除所有的热点图
		productImgsService.removeByProductId(productInfo.getId());
		
		//保存所有的颜色信息
		if( romeImgs.length > 0) {
			Map<String, Object> params2 = new HashMap<String, Object>();
			params2.put("productId", productInfo.getId());
			params2.put("imgIds", romeImgs);
			productImgsService.saveBatch(params2);
		}
		
		return R.ok();
	}
	
	/**
	 * 保存
	 */
	@Transactional
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("vr:topStyle:add")
	public R save( ProductInfoDO productInfo, @RequestParam("leftStyleIds") Integer[] leftStyleIds, 
			@RequestParam("colorStyleIds") Integer[] colorStyleIds){
		
		productInfo.setCreateTime(new Date());
		if(productInfoService.save(productInfo)>0){
			Integer productId = productInfo.getId();
			//保存产品信息
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("productId", productId);
			params.put("leftIds", leftStyleIds);
			
			productLeftStyleService.saveBatch(params);
			
			//保存所有的颜色信息
			if( colorStyleIds.length > 0 ) {
				Map<String, Object> params2 = new HashMap<String, Object>();
				params2.put("productId", productId);
				params2.put("colorIds", colorStyleIds);
				productColorsService.saveBatch(params2);
			}
			
			return R.ok();
		}
		return R.error();
	}
	
	@ResponseBody
	@GetMapping("/productImgIds")
	@RequiresPermissions("vr:colorType:colorType")
	public List<ProductImgsDO> productImgIds(Integer productId){
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("productId", productId);
		List<ProductImgsDO> list = productImgsService.list(params);
		return list;
	}
	
	@ResponseBody
	@GetMapping("/productLeftImgIds")
	@RequiresPermissions("vr:colorType:colorType")
	public List<ProductLeftStyleImgsDO> productLeftImgIds(Integer productId){
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("productId", productId);
		List<ProductLeftStyleImgsDO> list = productLeftStyleImgsService.list(params);
		return list;
	}
	
	/**
	 * 修改热点图
	 */
	@Transactional
	@ResponseBody
	@RequestMapping("/updateLeftRdt")
	@RequiresPermissions("vr:topStyle:edit")
	public R updateLeftRdt( ProductInfoDO productInfo, @RequestParam("leftStyleImgs") Integer[] leftStyleImgs){
		productInfoService.update(productInfo);
		
		//移除所有的热点图
		productLeftStyleImgsService.removeByProductId(productInfo.getId());
		
		//保存所有的颜色信息
		if( leftStyleImgs.length > 0 ) {
			Map<String, Object> params2 = new HashMap<String, Object>();
			params2.put("productId", productInfo.getId());
			params2.put("imgIds", leftStyleImgs);
			productLeftStyleImgsService.saveBatch(params2);
		}
		
		return R.ok();
	}
	
	/**
	 * 保存
	 */
	@Transactional
	@ResponseBody
	@PostMapping("/editProduct")
	@RequiresPermissions("vr:topStyle:edit")
	public R editProduct( Integer id, @RequestParam("leftStyleIds") Integer[] leftStyleIds){
		
		if( id == null || leftStyleIds == null || leftStyleIds.length <= 0 ) {
			return R.error("参数错误");
		}
		
		//把原来的产品信息移除
		productLeftStyleService.removeByProductId(id);
		//保存产品信息
		if( leftStyleIds.length > 0 ) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("productId", id);
			params.put("leftIds", leftStyleIds);
			productLeftStyleService.saveBatch(params);
		}
		
		return R.ok();
	}
	
	
	/**
	 * 修改
	 */
	@Transactional
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("vr:topStyle:edit")
	public R update( ProductInfoDO productInfo, @RequestParam("colorStyleIds") Integer[] colorStyleIds){
		productInfoService.update(productInfo);
		
		//移除所有的产品颜色分类
		productColorsService.removeByProductId(productInfo.getId());
		
		//保存所有的颜色信息
		if( colorStyleIds.length > 0 ) {
			Map<String, Object> params2 = new HashMap<String, Object>();
			params2.put("productId", productInfo.getId());
			params2.put("colorIds", colorStyleIds);
			productColorsService.saveBatch(params2);
		}
		
		return R.ok();
	}
	
	
	
	/**
	 * 删除
	 */
	@PostMapping( "/remove")
	@ResponseBody
	@RequiresPermissions("vr:topStyle:remove")
	public R remove( Integer id){
		if(productInfoService.remove(id)>0){
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
	public R remove(@RequestParam("ids[]") Integer[] ids){
		productInfoService.batchRemove(ids);
		return R.ok();
	}
	
	@ResponseBody
	@PostMapping("/uploadProductInfo")
	@RequiresPermissions("vr:topStyle:add")
	public R uploadColorLogo(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		
		String fileName = file.getOriginalFilename();
		fileName = FileUtil.renameToUUID(fileName).replaceAll("\\-", "");
		FileDO sysFile = new FileDO(FileType.fileType(fileName), "/files/productInfo/" + fileName, new Date());
		try {
			FileUtil.uploadFile(file.getBytes(), bootdoConfig.getUploadPath() + "productInfo/", fileName);
		} catch (Exception e) {
			return R.error();
		}

		if (sysFileService.save(sysFile) > 0) {
			return R.ok().put("fileName",sysFile.getUrl());
		}
		return R.error();
	}
	
}
