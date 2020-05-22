package com.bootdo.school.config;

import com.bootdo.school.interceptors.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {

	/*@Autowired
	private LoginInterceptor loginInterceptor;*/

	@Bean
	LoginInterceptor localInterceptor() {
		return new LoginInterceptor();
	}




	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//配置这个拦截器其效果 拦截什么路径 
	  	//registry.addInterceptor( localInterceptor()).addPathPatterns("/api/user/**","/vrs/user/**");
		 //registration.addPathPatterns("/**");
		registry.addInterceptor( localInterceptor()).addPathPatterns("/vrs/user/**","/vrs/houses/**","/pro/h5/**","/vrs/pay/**");
		super.addInterceptors(registry);
		
	}
	
	

}
