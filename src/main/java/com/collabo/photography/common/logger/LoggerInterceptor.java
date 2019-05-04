package com.collabo.photography.common.logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class LoggerInterceptor extends HandlerInterceptorAdapter{

	private static final Logger logger = Logger.getLogger(LoggerInterceptor.class);
	
	@Override
	 public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
  
		logger.debug("======================================          START         ======================================");
        logger.debug(" Request URI \t:  " + request.getRequestURI());

        return super.preHandle(request, response, handler);
    }
     
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

      logger.debug("======================================           END          ======================================\n");

    }

}
