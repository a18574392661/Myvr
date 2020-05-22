package com.bootdo.school.common;

import com.bootdo.common.utils.ShiroUtils;
import com.bootdo.system.domain.UserDO;

import java.util.HashMap;
import java.util.Map;



public class BaseController {
	
	 ThreadLocal<Map<String, Object>> threadLocal=new ThreadLocal<Map<String,Object>>();
	 
	 
	
	 public void start() {
		 
		 
		 Map<String, Object> sucessMap =new HashMap<String, Object>();
		 threadLocal.set(sucessMap);
	 }
	 
	 
	
	 public void success(Boolean success) {
		 
		 this.threadLocal.get().put("success", success);
	 }
	 
	
	 public void message(Object message) {
		 
		 this.threadLocal.get().put("message", message);
	 }
	 
	 
	 public void data(Object data) {
		 
		 this.threadLocal.get().put("data", data);
	 }
	 
	
	public Map<String,Object> end(){
		
		return  this.threadLocal.get();
	}


	public UserDO getUser() {
		return ShiroUtils.getUser();
	}




	public Long getUserId() {

		return  getUser().getUserId();
	}



}
