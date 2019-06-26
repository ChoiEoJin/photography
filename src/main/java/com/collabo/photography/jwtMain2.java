package com.collabo.photography;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

public class jwtMain2 {
	
	private static final Logger logger = LoggerFactory.getLogger(jwtMain2.class);

	
	private static String jwtSecretKey ="secret";//properties에서 읽어서 만들기
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		

		jwtMain2 obj = new jwtMain2();
		//생성
		String jwt = obj.createJWT();
		logger.debug("jwt(HEADER.PAYLOAD.SIGNATURE) :  " + jwt);
		
		String Area1 = jwt.substring(0, jwt.indexOf("."));
		String Area2 = jwt.substring(jwt.indexOf(".")+1,jwt.lastIndexOf("."));
		String Area3 = jwt.substring(jwt.lastIndexOf(".")+1);

		logger.debug("HEADER : " + Area1);	
		logger.debug("PAYLOAD : " + Area2);		
		logger.debug("SIGNATURE : "+ Area3);
		//검증
		Jws<Claims> validatedObj = obj.validateJWT(jwt);
		logger.debug("getSignature : " + validatedObj.getSignature());
		logger.debug("getBody : " + validatedObj.getBody());
		logger.debug("getBody.getId : " + validatedObj.getBody().getId());
		logger.debug("getBody.getSubject : " + validatedObj.getBody().getSubject());
		logger.debug("getBody.getIssuer : " + validatedObj.getBody().getIssuer());
		logger.debug("getBody.getExpiration : " + validatedObj.getBody().getExpiration());
	}

	
	//JWT 생성메소드
	public String createJWT () throws UnsupportedEncodingException {	
		
	    long nowMillis = System.currentTimeMillis(); //현재시간을 1970년 시간으로부터 1/1000초로 계산된 값
	    Date now = new Date(nowMillis);
	    
	    long expMillis = nowMillis + 36000000; //1시간 360만밀리세컨즈 =  3600초 = 1시간 
	    Date exp = new Date(expMillis);

		String jwt = Jwts.builder()
				  .setHeaderParam("type","JWT")
				  .setSubject("testSubject")
				  .setId("yms3684")
				  .setIssuer("selpic_admin")
				  .setIssuedAt(now)
				  .setExpiration(exp)
				  .claim("name", "choi")
				  .claim("address", "PajuSi SongHak 1 Gil 158-48")
				  .claim("gender", "male")
				  .claim("country", "Republic of Korea")
				  .claim("job", "IT developer")
				  .claim("age", "28")
				  .claim("group", "The Crew DCE")
				  .signWith(
				    SignatureAlgorithm.HS256,
				    jwtSecretKey.getBytes("UTF-8")
				  )
				  .compact();
		return jwt;
	}
	
	
	//검증메소드
	public Jws<Claims> validateJWT(String jwt) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException, SignatureException, IllegalArgumentException, UnsupportedEncodingException {
		Jws<Claims> claims = Jwts.parser()
				  .setSigningKey(jwtSecretKey.getBytes("UTF-8"))
				  .parseClaimsJws(jwt);
		
		Date dt = new Date();
		
		if(dt.compareTo(claims.getBody().getExpiration()) != -1) {
			logger.debug("기간이 만료되었습니다");
		}else {
			logger.debug("기간이 유효합니다 ");
		}
		
		return claims;
	}
	 
}

