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

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
//import com.collabo.photography.common.util.SessionUtil;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	protected Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

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

			logger.debug("ex :"+ex);

			if (temp.indexOf(ex) > -1) {//예외 대상이면,
				return true; // 통과
			}
		}
		
		
		//자동로그인
		// interceptor에서  로그인 입력화면호출 url을 받게되면,
		// DEVICE_ID 를가지고, DB조회(DEVICE_ID,AUTO_LOGIN='Y')정보가 있다면, 1.토큰생성 2.사용자메인화면으로 리다이렉트시켜주고,
		// DB조회(DEVICE_ID,AUTO_LOGIN='Y')정보가 있다면 없다면, return true해서 로그인입력화면창으로 보내야함
		
		
		//검사대상인경우
//		logger.debug.println("indexOf: "+ temp.indexOf("/web/"));//?

		logger.debug("JsonWebToken검사가 필요한작업입니다!");
		logger.debug("getRequestURL : "+ temp);
		if(request.getHeader("jwt")==null) { //로그인안했을때
			Map<String,Object> paramMap  = new HashMap<>();
			
			//강제로그인시키는효과땜에 이렇게해둠. 나중에 고쳐야함
			int t_user_no = 1;
			String t_user_id = "yms3684@naver.com";
			String t_user_email= "yms3684@naver.com";
			paramMap.put("user_no", t_user_no);
			paramMap.put("user_id", t_user_id);
			paramMap.put("user_email", t_user_email);
			
			String testJWT  = jwt.createJWT(paramMap);
			request.setAttribute("jwt",testJWT);
			
			//throw new Exception("request.getHeader(\"Authorization\")==null");			
		}else {
			if(!jwt.verification(request.getHeader("jwt"))) {
				logger.debug("기간만료된 토큰입니다.");
				//throw new Exception("This token is expired.");		
			}else {
				logger.debug("기간만료안됨");
				
				
				//JWT 새로생성하기
				String oldAuthorization = request.getHeader("jwt");
				logger.debug(oldAuthorization);
				
				Jws<Claims> claims = jwt.getClaims(oldAuthorization);
				String userNo = claims.getBody().get("user_no").toString();
				String userId = claims.getBody().get("user_id").toString();
				String userEmail = claims.getBody().get("user_email").toString();
				String userBadge = claims.getBody().get("user_badge").toString();
				Map<String,Object> jwtParam  = new HashMap<>();
				jwtParam.put("user_no",userNo);
				jwtParam.put("user_id",userId);
				jwtParam.put("user_email",userEmail);
				jwtParam.put("user_badge",userBadge);
				String newJwt = jwt.createJWT(jwtParam);
				
				//request객체에 담아서보내주기
				request.setAttribute("jwt",newJwt);

				
			}
		}
		logger.debug("도착");
		return true;//검증통과하면 가던길 계속가!
	}
}
