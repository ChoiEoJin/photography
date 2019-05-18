package com.collabo.photography.common.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.collabo.photography.common.jwt.JwtUtil;
import com.collabo.photography.common.util.MessageUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
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
//		System.out.println("temp :"+temp);
		logger.debug("temp : "+temp);
		for (int i = 0; i < exclude.length; i++) {
			String ex = exclude[i];
//			System.out.println("ex :"+ex);
			logger.debug("temp : "+temp);
//			System.out.println("temp : "+temp);
			logger.debug("ex :"+ex);
//			System.out.println("ex :"+ex);
			if (temp.indexOf(ex) > -1) {//예외 대상이면,
				return true; // 통과
			}
		}
		
		
		//자동로그인
		// interceptor에서  로그인 입력화면호출 url을 받게되면,
		// DEVICE_ID 를가지고, DB조회(DEVICE_ID,AUTO_LOGIN='Y')정보가 있다면, 1.토큰생성 2.사용자메인화면으로 리다이렉트시켜주고,
		// DB조회(DEVICE_ID,AUTO_LOGIN='Y')정보가 있다면 없다면, return true해서 로그인입력화면창으로 보내야함
		
		
		//검사대상인경우
//		System.out.println("indexOf: "+ temp.indexOf("/web/"));//?

		System.out.println("JsonWebToken검사가 필요한작업입니다!");
		System.out.println("getRequestURL : "+ temp);
		if(request.getHeader("Authorization")==null) {
			System.out.println("(request.getHeader(\"Authorization\")==null");
			Map<String,Object> paramMap  = new HashMap<>();
			
			String test1 = "test1";
			String test2 = "test2";
			String test3= "test3";
			paramMap.put("user_no", test1);
			paramMap.put("user_id", test2);
			paramMap.put("user_email", test3);
			
			String testJWT  = jwt.createJWT(paramMap);
			request.setAttribute("Authorization",testJWT);
			
			//throw new Exception("request.getHeader(\"Authorization\")==null");			
		}else {
			if(!jwt.verification(request.getHeader("Authorization"))) {
				System.out.println("기간만료된 토큰입니다.");
				//throw new Exception("This token is expired.");		
			}else {
				System.out.println("기간만료안됨");
				
				
				//JWT 새로생성하기
				String oldAuthorization = request.getHeader("Authorization");
				System.out.println(oldAuthorization);
				
				Jws<Claims> claims = jwt.getClaims(oldAuthorization);
				String userNo = claims.getBody().get("user_no").toString();
				String userId = claims.getBody().get("user_id").toString();
				String userEmail = claims.getBody().get("user_email").toString();
				Map<String,Object> jwtParam  = new HashMap<>();
				jwtParam.put("user_no",userNo);
				jwtParam.put("user_id",userId);
				jwtParam.put("user_email",userEmail);
				String newJwt = jwt.createJWT(jwtParam);
				
				//request객체에 담아서보내주기
				request.setAttribute("Authorization",newJwt);

				
			}
		}
		System.out.println("도착");
		return true;//검증통과하면 가던길 계속가!
	}
}
