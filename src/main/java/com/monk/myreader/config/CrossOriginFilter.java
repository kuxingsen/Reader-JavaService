package com.monk.myreader.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/*
*//**
 * 前后端分离，允许跨域请求的过滤器
 * @Author 何亮
 * @date 2019/4/3
 *//*
@Component
@WebFilter(urlPatterns= {"/*"}, filterName="crossOriginFilter")
@Order(1)
public class CrossOriginFilter implements Filter{
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}
	@Override
	public void doFilter(ServletRequest srequest, ServletResponse sresponse, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("CrossOriginFilter");
		HttpServletResponse response = (HttpServletResponse) sresponse;
		HttpServletRequest request = (HttpServletRequest) srequest;

		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
		//是否允许浏·览器携带用户身份信息（cookie）
		response.setHeader("Access-Control-Allow-Credentials","true");
		response.addHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With");

		chain.doFilter(srequest, sresponse);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}
}*/
@Configuration
public class CrossOriginFilter extends WebMvcConfigurerAdapter{

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		String mapping = "/**"; // 所有请求，也可配置成特定请求，如/api/**
		String origins = "*"; // 所有来源，也可以配置成特定的来源才允许跨域，如http://www.xxxx.com
		String methods = "*"; // 所有方法，GET、POST、PUT等
		registry.addMapping(mapping).allowedOrigins(origins).allowedMethods(methods)
				.allowCredentials(true) ;
	}
}