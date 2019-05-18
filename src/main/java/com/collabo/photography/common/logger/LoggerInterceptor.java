package com.collabo.photography.common.logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.collabo.photography.common.interceptor.LoginInterceptor;



public class LoggerInterceptor extends HandlerInterceptorAdapter{

	protected Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
	
	@Override
	 public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
  
		logger.debug("======================================          START         ======================================");
		logger.debug(" Request URI \t:  " + request.getRequestURI());

//        return true;
        return super.preHandle(request, response, handler);
    }
     
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    	logger.debug("======================================           END          ======================================\n");

    }

}
