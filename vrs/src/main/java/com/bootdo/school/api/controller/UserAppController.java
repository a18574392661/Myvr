package com.bootdo.school.api.controller;

import com.alibaba.fastjson.JSON;
import com.bootdo.common.utils.MD5Utils;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.school.annotations.LoginRequired;
import com.bootdo.school.common.BaseController;
import com.bootdo.school.common.MessageConstant;
import com.bootdo.school.common.QRCodeUtil;
import com.bootdo.school.domain.BusinessDO;
import com.bootdo.school.domain.UserCouponDO;
import com.bootdo.school.domain.UserCouponLogDO;
import com.bootdo.school.service.BusinessService;
import com.bootdo.school.service.UserCouponLogService;
import com.bootdo.school.service.UserCouponService;
import com.bootdo.school.util.JwtUtil;
import com.bootdo.school.util.RedisUtil;
import com.bootdo.system.dao.UserDao;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.service.UserService;
import org.apache.commons.collections.map.HashedMap;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/api/user")
public class UserAppController extends BaseController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserCouponService userCouponService;

	@Autowired
	private UserCouponLogService userCouponLogService;


	@Autowired
	private RedisUtil redisUtil;


	@Autowired
	private RedissonClient redissonClient;

	@Autowired
	private BusinessService businessService;

	@Autowired
	private UserDao userDao;

	public final String path = "school/apiuser/";

	//去学员登陆页面 
	@RequestMapping("/to_stuLogin")
	public String to_stuLogin() {

		return path + "stuLogin";
	}


	@RequestMapping("/to_busLogin")
	public String to_busLogin() {

		return path + "busLogin";
	}


	@RequestMapping("/to_userCoupon")
	@LoginRequired
	public String to_userCoupon(HttpServletRequest request, ModelMap map) {
		/*String uid = request.getAttribute("uid") + "";
		if (StringUtils.isBlank(uid)) {
			return path + MessageConstant.ERROR_PAGE;
		}*/

		Long uid=getUserId();


		UserCouponDO couponDO = userCouponService.queryUserCoupon(Long.parseLong(uid+""));
		map.put("ucoupon", couponDO);
		return path + "user_info"; //显示优惠券页面
	}

	//
	@RequestMapping("/to_CouponPage")
	@LoginRequired
	public String to_CouponPage(HttpServletRequest request, ModelMap map) {


		//System.out.println(getUserId()+"用户id的啊");

		//String uid = request.getAttribute("uid") + "";

	/*	if (StringUtils.isBlank(uid)) {
			return path + MessageConstant.ERROR_PAGE;
		}*/
		Long uid=getUserId();
		if (uid==null){
			return  path + MessageConstant.ToLoginUrl;
		}

		List<BusinessDO> list = businessService.list(null);
		map.put("busineList", list);

		return path + "shop_list"; //商家
	}

	//商家登录成功 显示所有学员的优惠券 可以核销
	@RequestMapping("/to_showUserCoupon")
	@LoginRequired
	public  String to_showUserCoupon(HttpServletRequest request,ModelMap map,String sta,String uuid){
			String message=null;
		try {
			/*String uid = request.getAttribute("uid") + "";
			if (StringUtils.isBlank(uid)) {
				return path + MessageConstant.ERROR_PAGE;
			}*/

			Long uid=getUserId();
			if (uid==null){
				return  path + MessageConstant.ToLoginUrl;
			}


			//查询商家对应的id
			BusinessDO businessDO=businessService.queryCouponId(uid+"");
			map.put("busid",businessDO.getId());
			//查询所有拥有优惠券的学员

			List<UserCouponDO> list=userCouponService.list(null);
			map.put("list",list);
			if (StringUtils.isNotBlank(sta)){
				if ("2".equals(sta)) {
					message=MessageConstant.SEARECH_STU_COUPON_ERROR;
				}else if ("3".equals(sta)){
					message=MessageConstant.ERROR_BUS_NOT;
				}

			}
			map.put("sta",sta);
			map.put("message",message);
			map.put("uuid",uuid);
		}
		catch (Exception e){
			e.printStackTrace();
		}

		return  path+"userCoumponList";
		}

	@RequestMapping("/login")
	@ResponseBody
	public Map<String, Object> login(HttpServletRequest request, String tel, String pwd, Long rid, String sta) {
		String token = "";
		UserDO userdoSucess = null;
		Jedis jedis = null;
		Map<String, Object> sucessMap = new HashMap<String, Object>();
		sucessMap.put("token", token);
		try {
			start();
			if (StringUtils.isBlank(tel) || StringUtils.isBlank(pwd) || rid == null) {
				message(MessageConstant.APP_USERLOGIN_DISBLE);
				success(false);
				return end();
			}
			//先查redis
			jedis = redisUtil.getJedis();
			//tel就是账号 学员的 商家的
			String key = MessageConstant.USER_REDIS + tel + ":" + MD5Utils.encrypt(tel, pwd) + ":rid:" + rid;
			String userVal = jedis.get(key);
			if (StringUtils.isNotBlank(userVal)&&!("null".equals(userVal))) {
				//直接赋值返回
				userdoSucess = JSON.parseObject(userVal, UserDO.class);
			} else {
				//封装用户信息
				UserDO userDO = new UserDO();
				userDO.setUsername(tel);
				userDO.setPassword(MD5Utils.encrypt(tel, pwd));
				userDO.setRid(rid);
				userDO.setMobile(tel);
				userdoSucess = userService.appLogin(userDO);
				if (userdoSucess!=null){
					jedis.setex(key, MessageConstant.USER_REDIS_TIME, JSON.toJSONString(userdoSucess));
				}

			}
			//null的话跑异常
			if (userdoSucess != null) {
				success(true);
				//不使用jwt方式不需要了
				//token = new TokenUtil().getUserToken(userdoSucess, request);

				//如果是登录就保存用户信息 修改密码共用
				if (StringUtils.isBlank(sta)) {

				//改了之后的
					 UsernamePasswordToken tokens = new UsernamePasswordToken(userdoSucess.getUsername(), userdoSucess.getPassword());

					Subject subject = SecurityUtils.getSubject();
					try {
						subject.login(tokens);
						success(true);
						message(MessageConstant.APP_USERLOGIN_SUCESS);

						return end();
					} catch (AuthenticationException e) {
						success(false);
						message("用户名或者密码错误!");
						return end();
					}
			}


			}
			/*if (StringUtils.isNotBlank(token)){
				message(MessageConstant.APP_USERLOGIN_SUCESS);
			}
			else {
				message(MessageConstant.APP_USERLOGIN_ERROR);
			}*/

			//sucessMap.put("token", token);

			//data(sucessMap);
			data(userdoSucess);
		} catch (RuntimeException e) {
			message(e.getMessage());
			success(false);
			e.printStackTrace();
		} catch (Exception e) {
			message(MessageConstant.APP_USERLOGIN_ERROR);
			success(false);
			e.printStackTrace();
		} finally {
			if (jedis != null)
				jedis.close();
		}

		return end();

	}




	//解析token
	@GetMapping("/verify")
	@ResponseBody
	public String verify(String token, String currentIp, HttpServletRequest request) {
		// 通过jwt校验token真假
		Map<String, Object> map = new HashMap<>();

		Map<String, Object> decode = JwtUtil.decode(token, MessageConstant.JwyKey, currentIp);
		if (decode != null) {
			map.put("status", "success");
			map.put("uid", decode.get("uid"));
			map.put("username", decode.get("username"));
		} else {
			map.put("status", "error");
		}
		return JSON.toJSONString(map);

	}

	//核销优惠券
	@RequestMapping("/userCoupon_hx")
	@ResponseBody
	@LoginRequired
	public Map<String, Object> userCoupon_hx(HttpServletRequest request, String couid,String price,String busid,String sid) {
		start();
		if (StringUtils.isBlank(couid)||StringUtils.isBlank(price)||StringUtils.isBlank(sid)){
			success(false);
			message(MessageConstant.USER_COUPON_ERROR);
			return  end();

		}

		//防止多个同样商家同样学员添加
		Map<String, Object> paremt = new HashMap<String, Object>();
		Map<String, Object> sucessMap = new HashMap<String, Object>();
		RLock lock = redissonClient.getLock(getUserId()+"lock");
		try {

			boolean b = lock.tryLock(10, TimeUnit.SECONDS);
			if (b == false) {
				Thread.sleep(3000);
				return this.userCoupon_hx(request, couid, price,busid,sid);
			}
			/*String uid = request.getAttribute("uid") + "";
			if (StringUtils.isBlank(uid)) {
				message(MessageConstant.USER_COUPON_ERROR);
				success(false);
				return end();
			}*/

			Long uid=getUserId();
			if (uid==null){
				message(MessageConstant.USER_COUPON_ERROR);
				success(false);
				return end();
			}



			paremt.put("uid", uid+""); //商家的用户编号

			paremt.put("couid", couid);
			paremt.put("price", price);
			paremt.put("busid", busid);
			paremt.put("sid", sid); //反正只有一条
			userCouponService.userCoupon_hx(paremt);
			success(true);
			message(MessageConstant.USER_COUPON_SUCESS);
			sucessMap.put("price", price);
			sucessMap.put("createDate", new Date());
			data(sucessMap);
		} catch (RuntimeException e) {
			message(e.getMessage());
			success(false);
			e.printStackTrace();
		} catch (Exception e) {
			message(MessageConstant.USER_COUPON_ERROR);
			success(false);
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		return end();
	}

	//去成功页面
	@RequestMapping("to_hxSuccess")
	@LoginRequired
	public  String to_hxSuccess(ModelMap map,HttpServletRequest request,String price,String createDate,String quedinDate){
		Long uid=getUserId();
		if (uid==null){

			return path+MessageConstant.ToLoginUrl;
		}



		map.put("price",price);
		map.put("createDate",createDate);
		map.put("quedinDate",quedinDate);
		return path+"success";
	}

	//用户的消费记录
	@RequestMapping("userBusPayLog")
	@LoginRequired
	public String userBusPayLog(HttpServletRequest request, ModelMap map,String busid) {
		String page="consumption";//默认是当前学员的核销记录
		/*String uid = request.getAttribute("uid") + "";
		if (StringUtils.isBlank(uid)) {
			return path + MessageConstant.ERROR_PAGE;

		}*/

		Long uid=getUserId();
		if (uid==null){
			return path+MessageConstant.ToLoginUrl;
		}

		//查看某个商家的用户消费记录
		if (StringUtils.isNotBlank(busid)){
			page="busConsumption";

		}
		//查询当前用户的消费记录
		List<UserCouponLogDO> list = userCouponLogService.queryUserCouponLog(Long.parseLong(uid+""),busid);
		BigDecimal bigDecimal=new BigDecimal(0);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getPayStatus()==1){
				bigDecimal=bigDecimal.add(new BigDecimal(list.get(i).getPrice()+""));
			}

		}
		map.put("list",list);
		map.put("sum",bigDecimal);

		return  path+page;
	}

	//当前用户的优惠券页面
	@RequestMapping("to_userBusStatusShowPage")
	@LoginRequired
	public  String to_userBusStatusShowPage(HttpServletRequest request,ModelMap map,String busid,String sid){
		String uuid= UUID.randomUUID().toString();
		Jedis jedis=redisUtil.getJedis();
		boolean sucess=false;
		String sta=null;
		if (StringUtils.isBlank(busid)||StringUtils.isBlank(sid)){
			//重定向到控制器
				sucess=true;
				sta="1";
		}
		try {
			/*String uid = request.getAttribute("uid") + "";
			if (StringUtils.isBlank(uid)) {
				return path + MessageConstant.ERROR_PAGE;
			}*/
			Long uid=getUserId();
			if (uid==null){
				return path+MessageConstant.ToLoginUrl;
			}
			//防止修改参数
			if (businessService.get(Integer.parseInt(busid))==null){
				sta="3";
				sucess=true;

			};
			//查询当前自己优惠券
			UserCouponDO couponDO = userCouponService.queryUserCoupon(Long.parseLong(sid));
			if (couponDO==null){
				sta="2";
				sucess=true;
			}

			//参数异常
			if (sucess==true){
				//防止重复刷新以及多个用户共享一个

				jedis.setex(MessageConstant.NOT_DIBEL+sta+":"+uuid,60,"val"+sta);
				return  "redirect:/api/user/to_showUserCoupon?sta="+sta+"&uuid="+uuid;
			}
				map.put("ucoupon", couponDO);//该用户的优惠券
				map.put("busid",busid);// 哪个商家
		}
		catch (Exception e){
				jedis.setex(MessageConstant.NOT_DIBEL+"2"+":"+uuid,60*30,"val2");
				return  "redirect:/api/user/to_showUserCoupon?sta=2&uuid="+uuid;
		}
		finally {
				if (jedis!=null)
					jedis.close();
		}

		return path + "bus_info"; //显示优惠券页面
	}

	//查询学员核销页面
	@RequestMapping("to_userHxPage")
	@LoginRequired
	public  String to_userHxPage(HttpServletRequest request,ModelMap map,String sid,String busid){

		try {
			if (StringUtils.isBlank(busid)){

				return "redirect:/api/user/to_CouponPage";
			}
			//乱输字符串异常去原来页面
			BusinessDO businessDO=businessService.get(Integer.parseInt(busid));
			if (businessDO==null){
				return "redirect:/api/user/to_CouponPage";
			}

			/*String uid = request.getAttribute("uid") + "";
			if (StringUtils.isBlank(uid)) {
				return path + MessageConstant.ERROR_PAGE;
			}*/
			Long uid=getUserId();
			//查询选择当前学员
			UserCouponDO couponDO = userCouponService.queryUserCoupon(Long.parseLong(uid+""));
			couponDO.setCode(null);
			map.put("ucoupon", couponDO);//该用户的优惠券
			map.put("busid",busid);// 哪个商家
			map.put("sid",sid);// 哪个商家
		}
		catch (Exception e){
			e.printStackTrace();
			return "redirect:/api/user/to_CouponPage";
		}

		return path + "user_info"; //显示优惠券页面
	}



	//去监听当前 商家当前用户的扣费状态页面
	@RequestMapping("userBusStatusShow")
	@ResponseBody
	@LoginRequired
	public  Map<String,Object> userBusStatusShow(HttpServletRequest request,ModelMap map,String busid){
		//String uid = request.getAttribute("uid") + "";
		Long uid=getUserId();
		UserCouponLogDO userCouponLogDO=null;
		try {
			start();
			/*if (StringUtils.isBlank(uid)) {
					success(false);
					message(MessageConstant.ERROR);
					return end();
			}*/
			//根据用户id跟商家id去查询状态
			//Long.parseLong(uid)
			userCouponLogDO=userCouponLogService.userBusStatusShow(Long.parseLong(uid+""),busid);
			if (userCouponLogDO!=null){
				success(true);
				message(MessageConstant.USER_LINSTER_BUS_MSG+userCouponLogDO.getPrice());
				System.out.println(userCouponLogDO.getPrice());

				data(userCouponLogDO);//根据状态判断 一次一条信息
			}

		}
		catch (RuntimeException e){
			success(false);
			message(e.getMessage());
			e.printStackTrace();
		}catch (Exception e){
			success(false);
			message(MessageConstant.ERROR);
			e.printStackTrace();
		}



		return end();
	}


	@ResponseBody
	@RequestMapping("userBusStatus")
	@LoginRequired
	public  Map<String,Object> userBusStatus(HttpServletRequest request,String busid,String status,String price,String id) {
		//String uid = request.getAttribute("uid") + "";
		Long uid=getUserId();

		Map<String, Object> parmet = new HashedMap();
		try {
			start();
			/*if (StringUtils.isBlank(uid)) {
				success(false);
				message(MessageConstant.ERROR);
				return end();
			}*/
			//改
			parmet.put("id", id);
			parmet.put("uid", uid+"");
			parmet.put("busid", busid);
			parmet.put("status", status);
			parmet.put("price", price);
			Date d=new Date();
			SimpleDateFormat sta=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			parmet.put("quedinDate", sta.format(d));
			userCouponLogService.userBusStatus(parmet);
			success(true);
			message(MessageConstant.SUCESSMESSAGE);
			data(parmet);
		} catch (RuntimeException e) {
			e.printStackTrace();
			success(false);
			message(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			success(false);
			message(MessageConstant.ERROR);


		}

			return  end();
	}


	@ResponseBody
	@RequestMapping("searchUserLog")
	@LoginRequired
	public  Map<String,Object> searchUserLog(HttpServletRequest request,String tel,String code){
	//	String uid=request.getAttribute("uid")+"";
		Long uid=getUserId();

		Map<String,Object> paremt=new HashedMap();
		try {
			    start();
			/*if (StringUtils.isBlank(uid)){
				success(false);
				message(MessageConstant.LoginMessageError);
				return  end();
			}*/
			if (StringUtils.isBlank(tel)||StringUtils.isBlank(code)){
				success(false);
				message(MessageConstant.APP_USERLOGIN_DISBLE);
				return  end();
			}
			paremt.put("code",code);
			paremt.put("tel",tel);
			UserCouponDO userCouponDO=userCouponService.searchUserLog(paremt);
			success(true);
			data(userCouponDO);
		}
		catch (RuntimeException e){
			e.printStackTrace();
			success(false);
			message(e.getMessage());

		}
		catch (Exception e){
			success(false);
			message(MessageConstant.SEARECH_STU_COUPON_ERROR);
			e.printStackTrace();
		}

		return  end();



	}


		//测试页面 二维码扫码
	@RequestMapping("/")
	public  String test(){

		return  path+"test";
	}




		@GetMapping("/qrcode")

	public 	void createQRCode(HttpServletResponse response,String url,HttpServletRequest request)
		{

			try {
				QRCodeUtil.getBufferedImage( request.getRequestURI() +":"+request.getServerPort()+"/"+url, 300,"E:/code.jpg");
			}catch (Exception e){
				e.printStackTrace();
			}




		}





	@ResponseBody
	@RequestMapping("/toNotLoad")
	public Map<String,Object> toNotLoad(String sta,String uuid) {
		Jedis jedis = redisUtil.getJedis();
		String vals="val"+sta;
		start();
		try {
			String val = jedis.get(MessageConstant.NOT_DIBEL + sta + ":" + uuid);
			if (StringUtils.isNotBlank(val) && vals.equals(val)) {
				//删除键 可以重复
				success(true);
				jedis.del(MessageConstant.NOT_DIBEL + sta + ":" + uuid);
			}else{
				success(false);
			}

		} catch (Exception e) {
			success(false);
			e.printStackTrace();

		} finally {
			if (jedis != null)
				jedis.close();
		}
		return  end();


	}



		//去修改密码的页面
		@RequestMapping("/to_editPwd")
		public  String to_editPwd(ModelMap map,String rid){
				map.put("rid",rid);

				return  path+"editPwd";
		}

		//确定修改密码

		@ResponseBody
		@RequestMapping("/editPwd")
		public  Map<String,Object> editPwd(String username,String pwd,String uid){
			start();
			UserDO userDO=new UserDO();
			Jedis jedis=null;
			try {
				jedis=redisUtil.getJedis();
				if (StringUtils.isBlank(username)||StringUtils.isBlank(pwd)||StringUtils.isBlank(uid)){
					success(false);
					message(MessageConstant.EDIT_USER_PWD_ERROR);
				}
				else{
					//去修改
					userDO.setUserId(Long.parseLong(uid));
					userDO.setPassword(MD5Utils.encrypt(username,pwd));
					int c=userDao.update(userDO);
					if (c>0){
						//模糊匹配key
						String key= MessageConstant.USER_REDIS + username;
						Set<String> vals=jedis.keys("*"+key+"*");
						jedis.del(vals.toArray()[0]+"");
						success(true);
						message(MessageConstant.EDIT_USER_PWD_SUCESS);
					}else{
						success(true);
						message(MessageConstant.EDIT_USER_PWD_SUCESS);
					}

				}

			}catch (Exception e){
				success(false);
				message(MessageConstant.EDIT_USER_PWD_ERROR);

			}
			finally {
				if (jedis!=null)
					jedis.close();
			}
			return end();

		}

}
