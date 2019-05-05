package com.collabo.photography.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.sun.xml.internal.bind.v2.TODO;

import com.collabo.photography.common.exception.CommonException;
import com.collabo.photography.common.jwt.JwtUtil;
import com.collabo.photography.common.util.MessageUtil;
//import com.collabo.photography.common.util.SessionUtil;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	protected Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

	@Autowired
	MessageUtil messageUtil;

	@Autowired
	private JwtUtil jwt;
	
	private String[] exclude;// /login/ 등 우회할 묵록, 와일드 카드 안됨.

	public String[] getExclude() {
		return exclude;
	}

	public void setExclude(String[] exclude) {
		this.exclude = exclude;
	}

	/*
	 * @Autowired SnsUserMapper snsUserMapper;
	 * 
	 * @Autowired SnsRequestHistoryMapper snsRequestHistoryMapper;
	 */

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
//		String[] str = new String[1];
//		str[0] = "/rest/ifTest001.do";
//		setExclude(str);
		String temp = request.getRequestURL().toString();
		logger.debug("temp : "+temp);
		for (int i = 0; i < exclude.length; i++) {
			String ex = exclude[i];
			logger.debug("temp : "+temp);
			System.out.println("temp : "+temp);
			logger.debug("ex :"+ex);
			System.out.println("ex :"+ex);
			if (temp.indexOf(ex) > -1) {//예외 대상이면,
				return true; // 통과
			}
		}
		
		//검사대상인경우
//		System.out.println("indexOf: "+ temp.indexOf("/web/"));//?
		if(temp.indexOf("/login.do/")>-1) {
			System.out.println("IF절");
			if(request.getHeader("Authorization")==null) {
				System.out.println("(request.getHeader(\"Authorization\")==null");				
			}else {
				if(!jwt.verification(request.getHeader("Authorization"))) {
					System.out.println("기간만료된 토큰입니다.");
				}
				
				
				
				
				
			}
//			if(!jwt.verification(request.getHeader("Authorization"))) {
//				//인증실패했을때 오는 구간
//				
//				
//				
//				
////				throw new CommonException(messageUtil.getMessage("ERROR.CODE.EXCEPTION_LOGIC"),
////						messageUtil.getMessage("LOGIN.MSG.REQUIRE_LOGIN"));
//			}
		}
		return true;
	}
}
