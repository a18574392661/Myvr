package com.bootdo.school.interceptors;

import com.alibaba.fastjson.JSON;
import com.bootdo.school.annotations.LoginRequired;
import com.bootdo.school.common.MessageConstant;
import com.bootdo.school.util.CookieUtil;
import com.bootdo.school.util.HttpclientUtil;
import com.bootdo.school.util.IpUtis;
import com.bootdo.school.util.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;



@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {


	@Autowired
	private  RedisUtil redisUtil;

	@Autowired
	private  IpUtis ipUtis;

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

	    	//拿到请求的路径
		String toUrl=request.getRequestURI();

		String disKey=toUrl+":"+ipUtis.getIp(request);
		//IpUtis.
		//System.out.println("真实ip"+ipUtis.getIp(request));
			//避免重复执行
			boolean dis=this.isNotDisble(disKey);
			if (dis==false){
				delNotDisble(disKey);
				return  dis;
			}

	//	String stateParent=request.getParameter("stateParent");
		//拿到请求路径的注解 
		//如果是null的不需要拦截(没定义)
		if(!(handler instanceof HandlerMethod)){
			this.delNotDisble(disKey);
			return  true;
		}
		 HandlerMethod hm = (HandlerMethod) handler;
		 LoginRequired methodAnnotation = hm.getMethodAnnotation(LoginRequired.class);
		//定义了 有不需要登陆 或者一定要登陆
		if (methodAnnotation==null) {
				//删除键
			this.delNotDisble(disKey);
			return true;
		}

		//反正登录成功两个值都会保存到token跟cookie
		String userCookieToken=CookieUtil.getCookieValue(request, MessageConstant.UserCookieToken, true);

		String token=request.getParameter("token");//用户登录验证的token
		String loginSucessJwt="";
		//拿到请求的路径 如果没登录 没拿到token 那就跳到登录 穿过去这个请求的路径
		//判断是否登录覆盖变量 
		if (StringUtils.isNotBlank(userCookieToken)) {
			loginSucessJwt=userCookieToken;
		}
		if (StringUtils.isNotBlank(token)) {
			loginSucessJwt=token;
		}



	 if (StringUtils.isBlank(loginSucessJwt)) {
		//没登录
			if (methodAnnotation.loginSuccess()==false) {
			//不需要登录
				this.delNotDisble(disKey);
				return true;
			}
				//必须登录的话 那就驳回去登陆的页面

			//根据请求参数 判断是驾校项目还是vr
			String url=MessageConstant.ToLoginUrl;

		/*if (StringUtils.isNotBlank(toUrl)&&!("null".equals(toUrl))){
			if (toUrl.contains("/vrs/pay/to_pay")){
				response.sendRedirect(url+"?url=/pro/h5/queryVip");
			}

		}*/

		 System.out.println("请求路"+toUrl);
		 this.delNotDisble(disKey);
		response.sendRedirect(url+"?url="+toUrl);

			/*if(StringUtils.isNotBlank(stateParent)){
				url= MessageConstantVrs.ToLoginUrl;
			}*/


		 return false;
	}
	else {

		 //不为null 放行 把token传过去解析 (其实这里写就行了)
		  String ip = request.getHeader("x-forwarded-for");// 通过nginx转发的客户端ip
          if(StringUtils.isBlank(ip)){
              ip = request.getRemoteAddr();// 从request中获取ip
              if(StringUtils.isBlank(ip)){
                  ip = "127.0.0.1";
              }
          }
          //MessageConstant.URL
		//动态获取ip
          	String host = request.getRequestURL().toString();
          	String uri = request.getRequestURI().toString();

          	String url = host.replace(uri, "");

		    String successJson  = HttpclientUtil.doGet(url+"/api/user/verify?token=" + loginSucessJwt+"&currentIp="+ip);


			Map<String, Object> map=JSON.parseObject(successJson, Map.class);

          //验证成功
          if ("success".equals(map.get("status"))) {
          		String uid=map.get("uid")+"";
				String username=map.get("username")+"";
				if (this.getJedisToken(loginSucessJwt,uid)==false){
					this.delNotDisble(disKey);
					//无效token
					return true;
				}

			  //登陆了放行
			  request.setAttribute("uid", map.get("uid"));
			  request.setAttribute("username", map.get("username"));


        	  CookieUtil.setCookie(request, response,MessageConstant.UserCookieToken, loginSucessJwt, 60*30, true);

			 // String userCookieToken2=CookieUtil.getCookieValue(request, MessageConstant.UserCookieToken, true);
			  //退出时候验证
			  this.setJedisToken(loginSucessJwt,map.get("uid")+"");
			  this.delNotDisble(disKey);
			  return true;
          }
          else {
        	  System.out.println("验证失败 ");
        	//  String toUrl=request.getRequestURI();
			  this.delNotDisble(disKey);
    		  response.sendRedirect(MessageConstant.ToLoginUrl);

				return false;
		}

	}

	
	}

	public void setJedisToken(String token,String uid){
		Jedis jedis= redisUtil.getJedis();
		try {

			jedis.setex("token:"+uid,60*30,token);
		}
		catch (Exception e){
			e.printStackTrace();
		}
		finally {
			if (jedis!=null)
				jedis.close();

		}


	}

	public boolean getJedisToken(String token,String uid){
			Jedis jedis= redisUtil.getJedis();
			try {

				String val=jedis.get("token:"+uid);
				if (StringUtils.isBlank(val)||!(token.equals(val))){
					return  false;
				}

			}
			catch (Exception e){
				e.printStackTrace();
			}
			finally {
				if (jedis!=null)
					jedis.close();

			}
		return  true;

	}


	//为了1防止多个用户同时访问请求
	public  boolean isNotDisble(String reqRrl){
		Jedis jedis=redisUtil.getJedis();
		boolean b=true;
		String[] sz=reqRrl.split(":");




		try {
			if ("/pro/h5/proDetailed".equals(sz[0])||"/pro/h5/proHuance".equals(sz[0])||"/pro/h5/proMengchuang".equals(sz[0])||"/pro/h5/proHuace".equals(sz[0])) {
				boolean count = jedis.exists(reqRrl);
				if (count == true) {
					String c = jedis.get(reqRrl);
					int sum = Integer.parseInt(c);
					++sum;
					jedis.setex(reqRrl, 60 * 2, sum + "");

					b = false;
				} else {
					//保存进去 过期时间1 2分钟
					jedis.setex(reqRrl, 60 * 2, 1 + "");

				}
			}


		}
		catch (Exception e){
			e.printStackTrace();
		}
		finally {
			if (jedis!=null)
				jedis.close();

		}
		return  b;
	}


	public  void delNotDisble(String reqRrl){
		Jedis jedis=redisUtil.getJedis();
		boolean b=true;
		try {
			String c=jedis.get(reqRrl);
			if (StringUtils.isNotBlank(c)){
				int sum=Integer.parseInt(c);
				if (sum>=2){
					//删除键 这个时候才能重复执行
					jedis.del(reqRrl);
				}
			}

		}
		catch (Exception e){
			e.printStackTrace();
		}
		finally {
			if (jedis!=null)
				jedis.close();

		}

	}




}
