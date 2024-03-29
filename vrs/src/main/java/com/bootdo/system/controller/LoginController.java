package com.bootdo.system.controller;

import com.alibaba.fastjson.JSON;
import com.bootdo.common.annotation.Log;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.domain.FileDO;
import com.bootdo.common.domain.Tree;
import com.bootdo.common.service.FileService;
import com.bootdo.common.utils.MD5Utils;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.school.common.MessageConstant;
import com.bootdo.school.util.CookieUtil;
import com.bootdo.school.util.RedisUtil;
import com.bootdo.school.util.TokenUtil;
import com.bootdo.system.dao.UserDao;
import com.bootdo.system.domain.MenuDO;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.service.MenuService;
import com.bootdo.vrs.common.MessageConstantVrs;
import org.apache.http.HttpResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class LoginController extends BaseController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	MenuService menuService;

	@Autowired
	private UserDao userDao;


	@Autowired
	private RedisUtil redisUtil;




	@Autowired
	FileService fileService;


	@GetMapping({ "/", "" })
	String welcome(Model model) {
		//return "redirect:/company";
		return "redirect:/vrs/houses/index";//"system/liuyan/index";
	}

	@Log("请求访问主页")
	@GetMapping({ "/index" })
	String index(Model model) {
		List<Tree<MenuDO>> menus = menuService.listMenuTree(getUserId());
		model.addAttribute("menus", menus);
		model.addAttribute("name", getUser().getName());
		FileDO fileDO = fileService.get(getUser().getPicId());
		if(fileDO!=null&&fileDO.getUrl()!=null){
			if(fileService.isExist(fileDO.getUrl())){
				model.addAttribute("picUrl",fileDO.getUrl());
			}else {
				model.addAttribute("picUrl","/img/photo_s.jpg");
			}
		}else {
			model.addAttribute("picUrl","/img/photo_s.jpg");
		}
		model.addAttribute("username", getUser().getUsername());
		return "index_v1";
	}

	@GetMapping("/login")
	String login() {
		
	
		
		return "login";
	}

	@Log("登录")
	@PostMapping("/login")
	@ResponseBody
	R ajaxLogin(String username, String password, HttpServletRequest request, HttpServletResponse response) {
		Jedis jedis = redisUtil.getJedis();
		String tokens = "";
		String key = MessageConstantVrs.VRUSERKEE + username + ":" + MD5Utils.encrypt(username, password);
		String verify = request.getParameter("verify") + "";
		String verifyCode = request.getSession().getAttribute("verifyCode") + "";
		if (!verify.toUpperCase().equals(verifyCode)) {
			return R.error("验证码不正确");
		}
		password = MD5Utils.encrypt(username, password);
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
			//判断是不是vr用户 如果是的前台不需要的登录
			UserDO userDO = userDao.queryVRUserName(username);
			if (userDO != null) {
				if ("vr".equals(userDO.getIdfind())) {
					jedis.setex(key, MessageConstantVrs.VRUSERTIME, JSON.toJSONString(userDO));
					new TokenUtil().getUserToken(userDO, request);
					tokens = new TokenUtil().getUserToken(userDO, request);
					if (StringUtils.isNotBlank(tokens)) {
						//token设置过期时间 防止用户退出登录拿到token重复刷新
						jedis.setex("token:" + userDO.getUserId(), 60 * 30, tokens);
						//存到cookie
						CookieUtil.setCookie(request, response,MessageConstant.UserCookieToken, tokens, 60*30, true);
					}
					else {
						 return R.error("用户或密码错误");
					}


				}
			}
			return R.ok();
		} catch (AuthenticationException e) {
			return R.error("用户或密码错误");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
		return R.ok();
	}

	@GetMapping("/logout")
	String logout(HttpServletRequest request, HttpServletResponse resp) {
		ShiroUtils.logout();
		Jedis jedis=redisUtil.getJedis();
		try {
			CookieUtil.deleteCookie(request,resp,MessageConstant.UserCookieToken);
			jedis.del("token:"+getUserId()+"");
			ShiroUtils.logout();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		finally {
			jedis.close();
		}




		return "redirect:/login";
	}

	@GetMapping("/main")
	String main() {
		return "main";
	}

}
