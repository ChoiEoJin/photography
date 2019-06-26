package com.collabo.photography.common.jwt;

import java.io.UnsupportedEncodingException;
import java.security.SignatureException;
import java.util.Date;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;


public class JwtUtil {

	private static final Logger log = Logger.getLogger(JwtUtil.class);
	
	private String secretKey;
	
//	@Autowired
//	SessionUtil sessionUtil;
	
	public String createJWT(Map<String, Object> paramMap) throws UnsupportedEncodingException {

	    long nowMillis = System.currentTimeMillis(); 
	    
	    long expMillis = nowMillis + 36000000 * 12; //1시간 360만밀리세컨즈 =  3600초 = 1시간 
	    Date exp = new Date(expMillis);
	    log.debug("user_no : "+paramMap.get("user_no"));
	    log.debug("user_id : "+paramMap.get("user_id"));
	    log.debug("user_email : "+paramMap.get("user_email"));
	    log.debug("user_badge : "+paramMap.get("user_badge"));
	    
		String jwt = Jwts.builder()
				  .setHeaderParam("type","JWT")
				  .setSubject("ChooseBetter")
				  .setExpiration(exp)
				  .claim("user_no", paramMap.get("user_no"))
				  .claim("user_id", paramMap.get("user_id"))
				  .claim("user_email", paramMap.get("user_email"))
				  .claim("user_badge", paramMap.get("user_badge"))//(2019.6.26)
				  .signWith(
				    SignatureAlgorithm.HS256,
				    secretKey.getBytes("UTF-8")
				  )
				  .compact();
		
		return jwt;

	}
	
	public boolean verification(String token) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException, UnsupportedEncodingException {
		
		boolean result = true;
		
		Jws<Claims> claims = Jwts.parser()
				  .setSigningKey(secretKey.getBytes("UTF-8"))
				  .parseClaimsJws(token);
		
		Date dt = new Date();
		
		if(dt.compareTo(claims.getBody().getExpiration()) != -1) {
			log.debug("기간 만료되었습니다");
			result = false;
		}else {
			log.debug("기간 정상");
			result = true;
		}
		
//		if(claims.getBody().get("usr_id").equals(((Map)sessionUtil.getSession().getAttribute("userInfo")).get("usr_id"))
//			&& claims.getBody().get("usr_nm").equals(((Map)sessionUtil.getSession().getAttribute("userInfo")).get("usr_nm"))
//			){		
//			result = true;
//		}else {
//			result = false;
//		}
		
		return result;
	}
	
	
	public Jws<Claims> getClaims(String token) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException, UnsupportedEncodingException{
		Jws<Claims> claims = Jwts.parser()
				  .setSigningKey(secretKey.getBytes("UTF-8"))
				  .parseClaimsJws(token);		
		return claims;
		
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}


	
}

