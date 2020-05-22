package com.bootdo.vr.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.bootdo.common.utils.R;
import com.bootdo.vr.domain.AppVersionDO;
import com.bootdo.vr.domain.GuideDO;
import com.bootdo.vr.domain.ProductActiveCodeDO;
import com.bootdo.vr.service.AppVersionService;
import com.bootdo.vr.service.GuideService;
import com.bootdo.vr.service.ProductActiveCodeService;
import com.bootdo.vr.service.TopStyleService;

/**
 * 
 * 
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2019-04-13 04:52:20
 */
 
@Controller
@RequestMapping("/vr")
public class VrDataController {
	@Autowired
	private TopStyleService topStyleService;
	@Autowired
	private ProductActiveCodeService productActiveCodeService;
	@Autowired
	private GuideService guideService;
	@Autowired
	private AppVersionService appVersionService;
	
	/**
	 * 版本更新检查
	 * version: 客户端当前安装的版本
	 * client: 客户端是IOS,还是Android
	 * @return
	 */
	@ResponseBody
	@GetMapping("/version")
	public Map<String, Object> version(String version, String client){
		
		Map<String, Object> res = new HashMap<String, Object>();
		
		if( StringUtils.isEmpty(version) || StringUtils.isEmpty(client) ) {
			res.put("status", 0);
			res.put("message", "请求失败，请稍候再试");
			return res;
		}
		
		if( client.equalsIgnoreCase(AppVersionDO.ANDROID) ) {
			client = AppVersionDO.ANDROID;
		} else if( client.equalsIgnoreCase(AppVersionDO.IOS) ) {
			client = AppVersionDO.IOS;
		}
		
		//得到当前的版本
		AppVersionDO versionDo = appVersionService.getByClient(client);
		
		if( version.equals(versionDo.getVersion()) ) {
			res.put("status", 0);
			res.put("message", "当前版本已是最新了");
			return res;
		} else {
			res.put("status", 1);
			res.put("version", versionDo.getVersion());
			res.put("url", versionDo.getUrl());			
			return res;//把apk的地址返回
		}
		
	}
	
	/**
	 * 进入APP下载页面下载app
	 * @return
	 * @throws Exception 
	 */
	@GetMapping("/down")
	public String appdown( Model model) throws Exception{
		AppVersionDO version = appVersionService.getByClient(AppVersionDO.ANDROID);
		model.addAttribute("android", version);
		return "vr/download";
	}
	
	/**
	 * 根据激活码获取所有的全景图
	 * deviceUuid: 用户的设备编号
	 * @return
	 */
	@ResponseBody
	@GetMapping("/all")
	public R list(String activeCode, String deviceUuid){
		if( StringUtils.isEmpty(activeCode) || StringUtils.isEmpty(deviceUuid) ) {
			return R.error("参数错误，请重试");
		}
		//根据激活码
		ProductActiveCodeDO activeCodeDo = productActiveCodeService.getProductActiveCodeByCode(activeCode);
		
		if( activeCodeDo == null ) {
			return R.error("激活码不正确");
		}
		
		if( StringUtils.isEmpty(activeCodeDo.getDeviceCode()) && activeCodeDo.getStatus().equals(ProductActiveCodeDO.status_normal)) {//如果 为空则为第一次使用该激活码
			activeCodeDo.setDeviceCode(deviceUuid);
			activeCodeDo.setStatus(ProductActiveCodeDO.status_used);
			activeCodeDo.setActiveTime(new Date());
			
			if( productActiveCodeService.update(activeCodeDo) > 0 ) {
				return R.ok("data", topStyleService.getAllQjt(activeCode));
			}
		} else {//不是第一次使用
			if( !activeCodeDo.getDeviceCode().equals(deviceUuid) ) {
				return R.error("该激活码已被使用");
			} else {
				return R.ok("data", topStyleService.getAllQjt(activeCode));
			}
		}
		return R.error("操作失败，请重试");
	}
	
	@ResponseBody
	@GetMapping("/guides")
	public R guides(){
		List<GuideDO> guideList = guideService.list(new HashMap<String, Object>());
		return R.ok("data", guideList);
	}
	
}
