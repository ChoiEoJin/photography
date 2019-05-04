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
		for (int i = 0; i < exclude.length; i++) {
			String ex = exclude[i];
			if (temp.indexOf(ex) > -1) {// ex = /login/
				return true; // session 체크 안하고 controller에 바로 전달로 전달.
			}
		}

		System.out.println("indexOf:: "+ temp.indexOf("/web/"));
		if(temp.indexOf("/login.do/")==-1) {
			System.out.println("IF절");
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
