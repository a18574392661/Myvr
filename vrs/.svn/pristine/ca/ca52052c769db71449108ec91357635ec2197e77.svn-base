package com.bootdo.system.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.R;
import com.bootdo.liuyan.domain.LiuyanDO;
import com.bootdo.liuyan.service.LiuyanService;

@RequestMapping("/company")
@Controller
public class CompanyController extends BaseController {
	private String prefix="system/liuyan"  ;
	@Autowired
	private LiuyanService liuyanService;
	
	@GetMapping("")
	String user(Model model) {
		return prefix + "/index";
	}

	@ResponseBody
	@PostMapping("/add")
	public String addLy(LiuyanDO ly) {
		
		ly.setOptime(new Date());
		if( liuyanService.save(ly) > 0 ) {
			return "留言成功";
		}
		return "留言失败";
	}
	
	
}
