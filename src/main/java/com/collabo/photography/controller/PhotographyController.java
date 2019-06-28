package com.collabo.photography.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.collabo.photography.common.jwt.JwtUtil;
import com.collabo.photography.common.util.AES256Util;
import com.collabo.photography.common.util.AuthUtil;
import com.collabo.photography.common.util.CommonUtils;
import com.collabo.photography.common.util.SendMail;
import com.collabo.photography.dao.TestDao;
import com.collabo.photography.service.mapper.AuthMapper;
import com.collabo.photography.service.mapper.LoginMapper;
import com.collabo.photography.service.mapper.RegisterMapper;
import com.collabo.photography.service.mapper.UserMapper;
import com.collabo.photography.vo.RequestCommand;
import com.google.gson.Gson;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

@RestController
@RequestMapping("/rest")
public class PhotographyController {
	private static final Logger logger = Logger.getLogger(PhotographyController.class);

	
	@Resource
	TestDao testDao;
	
	@Inject
	private AuthMapper authService;
	
	@Inject
	private UserMapper userService;
	
	@Autowired
	private RegisterMapper registerService;
	
	@Autowired
	private AuthUtil authUtil;

	@Autowired
	private LoginMapper loginService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	//1.회원가입요청
	@RequestMapping(value = "/userRegist.do", method = RequestMethod.POST, produces = "application/text; charset=utf8" )
	public String userRegist(RequestCommand reqParam, HttpSession session) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> param = reqParam.getParameterMap();
		String resultCode ="0";
		String resultstatus  ="";
		
		try {
			logger.debug("회원가입 들어옴");
			
			String uuid = param.get("uuid").toString().trim();
			String email = param.get("email").toString().trim();
			String gender = param.get("gender").toString().trim();
			String birth= param.get("birth").toString().trim();
			String grade = param.get("grade").toString().trim();
			String USER_NO ="";
			logger.debug("uuid : "+ uuid);
			logger.debug("email : "+ email);
			logger.debug("gender : "+ gender);
			logger.debug("birth : "+ birth); 
			logger.debug("grade : "+ grade);
			
			Map<String,Object> regUserMap = new HashMap<>();
			regUserMap.put("userid", email);
			regUserMap.put("uuid", uuid);
			regUserMap.put("email", email);
			regUserMap.put("gender", gender);
			regUserMap.put("birth", birth);
			regUserMap.put("grade", grade);
			
			
			regUserMap.put("created",System.currentTimeMillis());
			regUserMap.put("updated",System.currentTimeMillis());
			
			userService.registerUserInfo(regUserMap);
			USER_NO = regUserMap.get("USER_NO").toString();
			logger.debug("USER_NO : "+ USER_NO);
			regUserMap.put("USER_NO", USER_NO);		
			resultMap= CommonUtils.createResultMap("200", "success", regUserMap);
			
			
			
			
			
			
		} catch(Exception e) {
			String messageFlag= "";
			String message="";
			messageFlag= e.getMessage();
			if(messageFlag.equals("500")) {
				
			}else if(messageFlag.equals("501")) {
				
			}else {
				
			}
			
			e.printStackTrace();
			resultMap= CommonUtils.createResultMap(resultCode, resultstatus, message);
			
		}
	
	
		String rst = new Gson().toJson(resultMap);
		
		return rst;
	}
	
	
	//이메일중복체크	
	@RequestMapping(value = "/emailDuplicatedCheck.do", method = RequestMethod.POST, produces = "application/text; charset=utf8" )
	public String emailDuplicatedCheck(RequestCommand reqParam, HttpSession session) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> param = reqParam.getParameterMap();
		String resultCode ="0";
		String resultstatus  ="";
		String returnFlag ="";
		
		try {
			
			String email="";
			String uuid="";
			uuid = param.get("uuid").toString().trim();
			email = param.get("email").toString().trim();
			logger.debug("uuid : "+uuid);
			logger.debug("email : "+email);
			
			Map<String,Object> emailCheckMap = new HashMap<>();
			emailCheckMap.put("uuid", uuid);
			emailCheckMap.put("email", email);
			Map<String,Object> userInfoMap = userService.getUUID_By_EMAIL(emailCheckMap);
			
			String checkFlag = "";
			if(userInfoMap.isEmpty()) {
				logger.debug("601 : 이메일없음");
				checkFlag="601";				
			}else {
				String rstUUID =  userInfoMap.get("UUID").toString();
				if(uuid.equals(rstUUID)==false) {
					logger.debug("다른기기에서 사용중입니다.");
					
					checkFlag="602";
				}else {				
					logger.debug("현재기기에서  사용중입니다.");
					checkFlag="603";
				}
			}

			resultMap= CommonUtils.createResultMap("200", "success", checkFlag);	
		} catch(Exception e) {
			String messageFlag= "";
			String message="";
			messageFlag= e.getMessage();
			if(messageFlag.equals("601")) {
				
			}else if(messageFlag.equals("602")) {
				
			}else {
				
			}
			
			e.printStackTrace();
			resultMap= CommonUtils.createResultMap(resultCode, resultstatus, message);
			
		}
	
	
		String rst = new Gson().toJson(resultMap);
		
		return rst;
	}
	
	//인증요청메소드
	@RequestMapping(value = "/emailAuthRequest.do", method = RequestMethod.POST, produces = "application/text; charset=utf8" )
	public String emailAuthRequest(RequestCommand reqParam, HttpSession session) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> param = reqParam.getParameterMap();
		String resultCode ="0";
		String resultstatus  ="";		
		try {
			String uuid = "";
			String email="";
			
			uuid= param.get("uuid").toString();
			email= param.get("email").toString();
			
			logger.debug("uuid : "+uuid);
			logger.debug("email : "+email);
			
			//1.인증키생성
			String tempKey = authUtil.generateKey(8);
			logger.debug("authKey:"+tempKey);
			
			//2.uuid,email로 tb_auth 를 조회해서,
			Map<String,Object> searchAuthMap =  new HashMap<>();
			searchAuthMap.put("uuid",uuid);
			
			logger.debug(authService.getAuthPK_BY_UUID(searchAuthMap));

				
			
			if(authService.getAuthPK_BY_UUID(searchAuthMap)==null) {
				Map<String,Object> rstMap = new HashMap<>();
				rstMap.put("uuid", uuid);
				rstMap.put("authNum", tempKey);
				rstMap.put("uuid", uuid);
				rstMap.put("authExpired",System.currentTimeMillis()+60*10);
				rstMap.put("created",System.currentTimeMillis()+60*10);
				rstMap.put("createdBy",email);
				rstMap.put("updated",System.currentTimeMillis()+60*10);
				rstMap.put("updatedBy",email);
				
				
				//(i)정보가없으면 insert
				logger.debug("AUTH테이블에 기존데이터없음 ");
				int insertCnt = authService.insertAuthInfo(rstMap);
				if(insertCnt==0) {
					throw new Exception("");
				}
			}else {
				Map<String,Object> rstMap = authService.getAuthPK_BY_UUID(searchAuthMap);
				rstMap.put("uuid", uuid);
				rstMap.put("authNum", tempKey);
				rstMap.put("uuid", uuid);
				rstMap.put("authExpired",System.currentTimeMillis()+60*10*1000);
				rstMap.put("updated",System.currentTimeMillis());
				rstMap.put("updatedBy",email);
				rstMap.put("authPK",rstMap.get("AUTH_PK"));
				
				//(ii)정보가있으면 update 
				logger.debug("AUTH테이블에 기존데이터있음 ");
				authService.updateAuthInfo(rstMap);
			}
			
			
			//3.이메일전송
			SendMail.sendAuthMail(email, "[인증메일]", "이메일인증번호입니다 : "+tempKey);
			
			resultMap= CommonUtils.createResultMap("200", "success", "");	
		} catch(Exception e) {
			String messageFlag= "";
			String message="";
			messageFlag= e.getMessage();
			if(messageFlag.equals("500")) {
				
			}else if(messageFlag.equals("501")) {
				
			}else {
				
			}		
			e.printStackTrace();
			resultMap= CommonUtils.createResultMap(resultCode, resultstatus, message);	
		}
	
		String rst = new Gson().toJson(resultMap);	
		return rst;
	}
	
	//이메일인증
	@RequestMapping(value = "/emailAuth.do", method = RequestMethod.POST, produces = "application/text; charset=utf8" )
	public String emailAuth(RequestCommand reqParam, HttpSession session) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> param = reqParam.getParameterMap();
		String resultCode ="0";
		String resultstatus  ="";		
		try {
			String uuid = param.get("uuid").toString();
			String email = param.get("email").toString();
			String p_authNum = param.get("authNum").toString();
			String rstFlag="";
			
			//1.UUID랑 USER_EMAIL 을 이용하여 AUTH_NUM,UPDATED,AUTH_EXPIRED를 가져온다.
			Map<String,Object> authParamMap = new HashMap<>();
			authParamMap.put("uuid", uuid);
			authParamMap.put("email", email);
			
			//2.AUTH_NUM 과 p_AUTH_NUM을 비교해서 일치하는지여부 -> 일치하지않는경우 611코드리턴
			Map<String,Object> authRstMap = authService.getAuthInfoByUUID_EMAIL(authParamMap);
			String db_AuthNum = authRstMap.get("AUTH_NUM").toString();
			
			if(p_authNum.equals(db_AuthNum)==false) {
				logger.debug("인증번호불일치");
				rstFlag = "611";
			}else {
				long authExpired = Long.parseLong(authRstMap.get("AUTH_EXPIRED").toString());
				long curTimeMill = System.currentTimeMillis();
				
				if(authExpired<curTimeMill) {
					logger.debug("유효기간만료");
					rstFlag = "612";
				}else {
					logger.debug("인증성공");
					rstFlag = "610";
				}
			}			
			resultMap= CommonUtils.createResultMap("200", "success",rstFlag);	
		} catch(Exception e) {
			String messageFlag= "";
			String message="";
			messageFlag= e.getMessage();
			if(messageFlag.equals("500")) {
				
			}else if(messageFlag.equals("501")) {
				
			}else {
				
			}		
			e.printStackTrace();
			resultMap= CommonUtils.createResultMap(resultCode, resultstatus, message);	
		}
	
		String rst = new Gson().toJson(resultMap);	
		return rst;
	}
	
	
	//2.로그인요청
	@RequestMapping(value = "/emailLogin.do", method = RequestMethod.POST, produces = "application/text; charset=utf8" )
	public String emailLogin(RequestCommand reqParam, HttpSession session) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> param = reqParam.getParameterMap();
		String resultCode ="0";
		String resultstatus  ="";
		String rstFlag = "";
		Map<String,Object> tempMap = new HashMap<>();
		String Authorization="";
		try {
			
			
			
			//1.uuid,email 받는다.
			  Object p_uuid = param.get("uuid").toString();
			  Object p_email = param.get("email").toString();
			  if(p_uuid==null || p_email==null) throw new Exception("501");
			 
			  //2.uuid tb_user를 조회하여, 있는지확인한다.
			  Map<String,Object> rstMap = loginService.getUserInfoByUUID(param);
			  
			//3.없으면  예외처리한다.
			  if(rstMap==null) {
				  logger.debug("해당기기로 등록된 유저정보없음");
				  rstFlag="UNREGISTERD_01";
			  }else {
				  
				  int rstUserNo = Integer.parseInt(rstMap.get("USER_NO").toString());
				  String rstUserId  = rstMap.get("USER_ID").toString();
				  String rstEmail = rstMap.get("USER_EMAIL").toString();
//				  String rstUserGender = rstMap.get("USER_GENDER").toString();
//				  String rstUserBirth = rstMap.get("USER_BIRTH").toString();//date
//				  int rstUserGrade = Integer.parseInt(rstMap.get("USER_GRADE").toString());
				  
				  if(rstEmail.trim().equals("")) {
					  logger.debug("기기변경후 아직 이메일없음");
					  rstFlag="UNREGISTERD_02";
					  
				  }else if(p_email.equals(rstEmail)==false) {
					  logger.debug("이메일이 일치하지 않습니다");
					  rstFlag="MISMATCH_02";
					  
				  }else {//4.있으면 토큰생성한다. 
					  logger.debug("로그인성공");
					  
					  //뱃지만들기(2019.6.26)
					  int age = CommonUtils.calAge(rstMap.get("USER_BIRTH").toString());
					  String gender = rstMap.get("USER_GENDER").toString();
					  String badge = CommonUtils.makingBadge(age, gender);
					  
					  Map<String,Object> jwtParamMap =  new HashMap<>();
					  jwtParamMap.put("user_no", rstUserNo);
					  jwtParamMap.put("user_id", rstUserId);
					  jwtParamMap.put("user_email", rstEmail);
					  jwtParamMap.put("user_badge", badge);//jwt에 뱃지넣기(2019.6.26)
					  
					  rstFlag= "200";
					  Authorization = jwtUtil.createJWT(jwtParamMap);
					  
				  }
				  
			  }
			
			  
			tempMap.put("rstFlag", rstFlag);
			tempMap.put("Authorization", Authorization);
			
			resultMap= CommonUtils.createResultMap("200", "success",tempMap);	
			
		} catch(Exception e) {
			String messageFlag= "";
			String message="";
			messageFlag= e.getMessage();
			if(messageFlag.equals("500")) {
				
			}else if(messageFlag.equals("501")) {
				
			}else {
				
			}		
			e.printStackTrace();
			resultMap= CommonUtils.createResultMap(resultCode, resultstatus, message);	
		}
	
		String rst = new Gson().toJson(resultMap);	
		return rst;
	}
	
	
	//3.로그아웃 요청 
	@RequestMapping(value = "/userLogout.do", method = RequestMethod.POST, produces = "application/text; charset=utf8" )
	public String userLogout(RequestCommand reqParam, HttpSession session) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> param = reqParam.getParameterMap();
		String resultCode ="0";
		String resultstatus  ="";
		
		try {
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			resultMap= CommonUtils.createResultMap("200", "success", "");	
		} catch(Exception e) {
			String messageFlag= "";
			String message="";
			messageFlag= e.getMessage();
			if(messageFlag.equals("500")) {
				
			}else if(messageFlag.equals("501")) {
				
			}else {
				
			}
			
			e.printStackTrace();
			resultMap= CommonUtils.createResultMap(resultCode, resultstatus, message);
			
		}
	
	
		String rst = new Gson().toJson(resultMap);
		
		return rst;
	}

	//프로필등록 옵션가져오기(경우의수 따져서 다 생성하고 완성된코드를 DB에 넣을까?)
	@RequestMapping(value="/getProfileRegisterOption.do",method = RequestMethod.POST, produces = "application/text; charset=utf8" )
	public String getProfileRegisterOption(RequestCommand reqParam, HttpSession session,HttpServletRequest request) {
		
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> param = reqParam.getParameterMap();
		String resultCode ="0";
		String resultstatus  ="";
				
		logger.debug("사진등록옵션가져오기 : 연령대,성별 ");
		try {

			List<Map<String,Object>> ageCodeInfo = registerService.getAgeCode();
			List<Map<String,Object>> genderCodeInfo = registerService.getGenderCode();
			
			
			Map<String,Object> tempMap = new HashMap<>();			
			tempMap.put("ageCodeInfo", ageCodeInfo);
			tempMap.put("genderCodeInfo", genderCodeInfo);
			
			resultMap= CommonUtils.createResultMap("200", "success", tempMap);	

		} catch(Exception e) {
			String messageFlag= "";
			String message="";
			messageFlag= e.getMessage();
			if(messageFlag.equals("500")) {
				
			}else if(messageFlag.equals("501")) {
				
			}else {
				
			}
			
			e.printStackTrace();
			resultMap= CommonUtils.createResultMap(resultCode, resultstatus, message);			
		}
		String rst = new Gson().toJson(resultMap);
		return rst;
	}
	
	
	//내프로필등록하기 =
	@RequestMapping(value="/registerMyProfile.do",method = RequestMethod.POST, produces = "application/text; charset=utf8" )
	public String registerMyProfile(RequestCommand reqParam, HttpSession session,HttpServletRequest request) {
		
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> param = reqParam.getParameterMap();
		String resultCode ="0";
		String resultstatus  ="";
				
		logger.debug("사진등록도착!");
		try {
			
//			String jwtObj = request.getHeader("jwt");
			String jwtObj = (String) request.getAttribute("jwt");
			logger.debug("jwtObj : "+jwtObj);
			Jws<Claims> claims = jwtUtil.getClaims(jwtObj);
			logger.debug("claims :" +claims);
			int userNo = Integer.parseInt(claims.getBody().get("user_no").toString()); 
			String userId = claims.getBody().get("user_id").toString();
			String userEmail = claims.getBody().get("user_email").toString();
			logger.debug("3");
			
			String encodedBase64Hash1 = param.get("REGIST_IMAGE1").toString();
			logger.debug("encodedBase64Hash1 : "+encodedBase64Hash1);
			String encodedBase64Hash2 = param.get("REGIST_IMAGE2").toString();
			logger.debug("encodedBase64Hash2 :" + encodedBase64Hash2);
			
			String registCmt =  param.get("REGIST_COMMENT").toString();
			logger.debug("registCmt :" + registCmt);
			int selCnt = Integer.parseInt(param.get("SELECTER_CNT").toString()) ;
			logger.debug("selCnt :" + selCnt);
			String selGen =  param.get("SELECTER_GENDER").toString();
			logger.debug("selGen :" + selGen);
			String selOld1 =  param.get("SELECTER_OLD1").toString();
			logger.debug("selOld1 :" + selOld1);
			String selOld2 =  param.get("SELECTER_OLD2").toString();
			logger.debug("selOld2 :" + selOld2);
			String selBadge = selOld1+selOld2+selGen;
			logger.debug("selBadge :" + selBadge);
			String endDate =  param.get("END_DATE").toString();
			logger.debug("endDate :" + endDate);
			
			Map<String,Object> insertParam = new HashMap<String,Object>();
			insertParam.put("REGIST_USER",userNo);//2
			insertParam.put("REGIST_IMAGE1", AES256Util.aesEncode(encodedBase64Hash1));//3
			insertParam.put("REGIST_IMAGE2", AES256Util.aesEncode(encodedBase64Hash2));//4
			insertParam.put("REGIST_COMMENT", registCmt);//5
//			insertParam.put("REGIST_AUTH_CHK", "N");//6
			insertParam.put("SELECTER_CNT", selCnt);//7
//			insertParam.put("SELECTER_GENDER", selGen);//8
//			insertParam.put("SELECTER_OLD", selOld);//9
			insertParam.put("SELECTER_BADGE", selBadge);//9
			insertParam.put("VOTE_END_YN", "N");//10
			insertParam.put("END_DATE", endDate);//11
			insertParam.put("CREATED_BY", userEmail);//12
			insertParam.put("CREATED", System.currentTimeMillis());//13
			insertParam.put("UPDATED_BY", userEmail);//14
			insertParam.put("UPDATED", System.currentTimeMillis());//15
			
			
			
			logger.debug(insertParam.get("REGIST_USER"));
			logger.debug(insertParam.get("REGIST_IMAGE1"));
			logger.debug(insertParam.get("REGIST_IMAGE2"));
			logger.debug(insertParam.get("REGIST_COMMENT"));
			logger.debug(insertParam.get("REGIST_AUTH_CHK"));
			logger.debug(insertParam.get("SELECTER_CNT"));
			logger.debug(insertParam.get("SELECTER_BADGE"));
			logger.debug(insertParam.get("VOTE_END_YN"));
			logger.debug(insertParam.get("END_DATE"));
			logger.debug(insertParam.get("CREATED_BY"));
			logger.debug(insertParam.get("CREATED"));
			logger.debug(insertParam.get("UPDATED_BY"));
			logger.debug(insertParam.get("UPDATED"));
			
			
			
			
			//1.DB에넣기 끝!
			int cnt = registerService.registerProfile(insertParam);
			
//			String encodedName1 = AES256Util.aesEncode(encodedBase64Hash1);
//			logger.debug("encodedName1 : "+encodedName1);
//			String encodedName2 = AES256Util.aesEncode(encodedBase64Hash2);
//			logger.debug("encodedName2 : "+encodedName2);
//			
//			String decodedName1 = AES256Util.aesDecode(encodedName1);
//			logger.debug("decodedName1 : "+decodedName1);
//			
//			String decodedName2 = AES256Util.aesDecode(encodedName2);
//			logger.debug("decodedName2 : "+decodedName2);
			
			
		
			
			
			
			
			resultMap= CommonUtils.createResultMap("200", "success", "");	

		} catch(Exception e) {
			String messageFlag= "";
			String message="";
			messageFlag= e.getMessage();
			if(messageFlag.equals("500")) {
				
			}else if(messageFlag.equals("501")) {
				
			}else {
				
			}
			
			e.printStackTrace();
			resultMap= CommonUtils.createResultMap(resultCode, resultstatus, message);
			
		}
	
	
		String rst = new Gson().toJson(resultMap);
		
		return rst;
	
	}
	
	
	
	
	//8.리턴맵테스트
	@RequestMapping(value = "/returnMap.do", method = RequestMethod.POST, produces = "application/text; charset=utf8" )
	public String returnMap(RequestCommand reqParam, HttpSession session) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> param = reqParam.getParameterMap();
		String resultCode ="0";
		String resultstatus  ="";
			logger.debug("getMyResult");
		try {
			List<Map<String,Object>> list = new ArrayList<>();
			
			logger.debug("list : "+list);
			logger.debug("list.size()"+list.size());
			logger.debug("list.isEmpty() : "+list.isEmpty());
			
			resultMap= CommonUtils.createResultMap("200", "success", list);	
		} catch(Exception e) {
			String messageFlag= "";
			String message="";
			messageFlag= e.getMessage();
			if(messageFlag.equals("500")) {
				
			}else if(messageFlag.equals("501")) {
				
			}else {
				
			}
			
			e.printStackTrace();
			resultMap= CommonUtils.createResultMap(resultCode, resultstatus, message);
			
		}
	
	
		String rst = new Gson().toJson(resultMap);
		
		return rst;
	}
	
	//제이슨테스트
	@RequestMapping(value="/dbJsonList.do", method = RequestMethod.GET)
	public Map<String,Object> cc(){
		Map<String,Object> rst = new HashMap<>();	
//		List<Map<String,Object>> list = new ArrayList<>();	
//		list= testDao.getTestListMap();	
//		rst.put("resultData", list);	
//		logger.debug(rst);
		logger.debug("gdgd");
		return rst;
	}
	
	//test
	@RequestMapping(value = "/test.do", method = RequestMethod.POST, produces = "application/text; charset=utf8" )
	public String get(RequestCommand reqParam, HttpSession session) {
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> header= new HashMap<String, Object>();
		Map<String, Object> body= new HashMap<String, Object>();
		Map<String, Object> param = reqParam.getParameterMap();
		
		header.put("retCode", 0);
		header.put("errMsg", "");
		
		try {
			List<Map<String, Object>> data =  testDao.getTestListMap();	
			body.put("data", data);
		} catch(Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			header.put("retCode", 404);
			header.put("errMsg", "error");
		}
		
		result.put("header", header);
		result.put("body", body);
		
		String rst = new Gson().toJson(result);
		
		return rst;
	}
		
	//미승인리스트가져오기(관리자)
	@RequestMapping(value = "/unauthProfileList.do", method = RequestMethod.POST, produces = "application/text; charset=utf8" )
	public String unauthProfileList(RequestCommand reqParam, HttpSession session,HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> header= new HashMap<String, Object>();
		Map<String, Object> body= new HashMap<String, Object>();
		Map<String, Object> param = reqParam.getParameterMap();
		header.put("retCode", 0);
		header.put("errMsg", "");
		try {			
			List<Map<String,Object>> unauthProfileList  = registerService.getUnauthProfileList();
			//복호화 
			for(int i=0;i<unauthProfileList.size();i++) {
				Map<String,Object> profileMap = unauthProfileList.get(i);
				String decodedImg1 = AES256Util.aesDecode(profileMap.get("REGIST_IMAGE1").toString());
				String decodedImg2 = AES256Util.aesDecode(profileMap.get("REGIST_IMAGE2").toString());
				
				profileMap.put("REGIST_IMAGE1", decodedImg1);
				profileMap.put("REGIST_IMAGE2", decodedImg2);
				
			}
			body.put("unauthProfileList", unauthProfileList);
		} catch(Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			header.put("retCode", 404);
			header.put("errMsg", "error");
		}
		result.put("header", header);
		result.put("body", body);	
		String rst = new Gson().toJson(result);
		return rst;
	}
	
	//프로필검열하기(관리자)
	@RequestMapping(value = "/censorProfile.do", method = RequestMethod.POST, produces = "application/text; charset=utf8" )
	public String censorProfile(RequestCommand reqParam, HttpSession session,HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> header= new HashMap<String, Object>();
		Map<String, Object> body= new HashMap<String, Object>();
		Map<String, Object> param = reqParam.getParameterMap();
		header.put("retCode", 0);
		header.put("errMsg", "");
		try {			
			int registerNo =Integer.parseInt(param.get("REGIST_NO").toString());
			String registAuthChk  = param.get("REGIST_AUTH_CHK").toString();
			Map<String,Object> updateRegistAuthChkParamMap  = new HashMap<>();
			updateRegistAuthChkParamMap.put("registerNo", registerNo);
			updateRegistAuthChkParamMap.put("registAuthChk", registAuthChk);
			registerService.updateRegistAuthChk(updateRegistAuthChkParamMap);	
		} catch(Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			header.put("retCode", 404);
			header.put("errMsg", "error");
		}
		result.put("header", header);
		result.put("body", body);
		String rst = new Gson().toJson(result);	
		return rst;
	}
	//상세보기
	@RequestMapping(value = "/detailProfile.do", method = RequestMethod.POST, produces = "application/text; charset=utf8" )
	public String detailProfile(RequestCommand reqParam, HttpSession session,HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> header= new HashMap<String, Object>();
		Map<String, Object> body= new HashMap<String, Object>();
		Map<String, Object> param = reqParam.getParameterMap();
		header.put("retCode", 0);
		header.put("errMsg", "");
		try {
			//REGIST_NO으로 조회해서 등록정보중 사진가져오기 
			int registNo =  Integer.parseInt(param.get("REGIST_NO").toString());
			logger.debug("상세보기: registNo :"+registNo);
			Map<String,Object> profileInfo = registerService.getProfileInfo(registNo);
			body.put("profileInfo", profileInfo);
			
			
		} catch(Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			header.put("retCode", 404);
			header.put("errMsg", "error");
		}
		result.put("header", header);
		result.put("body", body);
		String rst = new Gson().toJson(result);	
		return rst;
	}
	
	//투표하기
	@RequestMapping(value = "/voteRegist.do", method = RequestMethod.POST, produces = "application/text; charset=utf8" )
	public String voteRegist(RequestCommand reqParam, HttpSession session,HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> header= new HashMap<String, Object>();
		Map<String, Object> body= new HashMap<String, Object>();
		Map<String, Object> param = reqParam.getParameterMap();
		header.put("retCode", 0);
		header.put("errMsg", "");
		try {
			
			//로그인유저정보
			String jwtObj = (String) request.getAttribute("jwt");
			Jws<Claims> claims = jwtUtil.getClaims(jwtObj);
			int userNo = Integer.parseInt(claims.getBody().get("user_no").toString()); 
			String userId = claims.getBody().get("user_id").toString();
			String userEmail = claims.getBody().get("user_email").toString();
			
			String registNo =  param.get("REGIST_NO").toString();
			int VOTE =  Integer.parseInt(param.get("VOTE").toString());
			Map<String,Object> voteParam = new HashMap<>();
			voteParam.put("userNo", userNo);
			voteParam.put("userEmail", userEmail);
			voteParam.put("registNo", registNo);
			voteParam.put("registVote", VOTE);
			voteParam.put("created", System.currentTimeMillis());//13
			voteParam.put("updated", System.currentTimeMillis());//13
			registerService.registVOTE(voteParam);

		} catch(Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			header.put("retCode", 404);
			header.put("errMsg", "error");
		}
		result.put("header", header);
		result.put("body", body);
		String rst = new Gson().toJson(result);	
		return rst;
	}
	
	//골라줘리스트(*) //페이징처리 필요
	@RequestMapping(value = "/getChooseList.do", method = RequestMethod.POST, produces = "application/text; charset=utf8" )
	public String getChooseList(RequestCommand reqParam, HttpSession session,HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> header= new HashMap<String, Object>();
		Map<String, Object> body= new HashMap<String, Object>();
		Map<String, Object> param = reqParam.getParameterMap();
		Map<String, Object> resultMap = new HashMap<String,Object>();
		header.put("retCode", 0);
		header.put("errMsg", "");
		try {
			
			//1.로그인유저정보
			String jwtObj = (String) request.getAttribute("jwt");
			Jws<Claims> claims = jwtUtil.getClaims(jwtObj);
			int userNo = Integer.parseInt(claims.getBody().get("user_no").toString()); 
			String userId = claims.getBody().get("user_id").toString();
			String userEmail = claims.getBody().get("user_email").toString();
//			String userBadge = claims.getBody().get("user_badge").toString();
			String userBadge = "AAA,AAM,2AA,2AM,2LA,2LM";
			 
			String[] arrBadge = userBadge.split(",");
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			
			//2.query문작성 (뱃지,종료일)
			String query="select REGIST_NO,REGIST_USER,REGIST_IMAGE1,REGIST_IMAGE2,REGIST_COMMENT from TB_REGIST";
			String whereQuery =" where ";
			String andQuery =" and ";
			
			//종료일 현재날짜 
			String endDateQuery ="END_DATE >" + CommonUtils.parameterVarchar(sdf.format(new Date())); //이게먹혀야되는데 DB에서 테스트해봐야겠다.
			String badgeQuery = "SELECTER_BADGE = ";
			
			//뱃지
			for(int i=0; i< arrBadge.length ; i++) {
				if(i==0) {
					badgeQuery+= CommonUtils.parameterVarchar(arrBadge[i])+" ";
				}else {
					badgeQuery+= "or SELECTER_BADGE = " + CommonUtils.parameterVarchar(arrBadge[i])+" ";
				}
			}
			
			//선택자수
			String selCntQuery = "SELECTER_CNT > (select count(REGIST_NO) from TB_VOTE where REGIST_NO=REGIST_NO)";
			
			String adminChkQuery = "REGIST_AUTH_CHK = 'Y'"; 
			
			query+= whereQuery +
					endDateQuery +
					andQuery +
					"("+badgeQuery+")"+
					andQuery +
					selCntQuery+
					andQuery +
					adminChkQuery;
			
			
			logger.debug(query);

			List<Map<String,Object>> profileList = registerService.getChooseList(query);
			
			
			
			//경우1)이미 투표한 케이스는 빼내는작업을할지 ?
			//  리스트에서 REGIST_NO를가지고,  select REGIST_NO from TB_VOTE where REGIST_NO=#{registNo} and VOTE_WHO = #{userNO} 결과가있으면 ,
			//  List에서 빼기

			int unfilteredSize = profileList.size();
			logger.debug("내가 투표한거 빼기전 갯수  : "+unfilteredSize+"개");		
			
			for(int i=0;i<unfilteredSize;i++) {
				Map<String,Object> profileMap = profileList.get(i);
				
				int registNo  = Integer.parseInt(profileMap.get("REGIST_NO").toString());
				Map<String,Object> subParamMap =  new HashMap<String,Object>();
				subParamMap.put("registNo", registNo);
				subParamMap.put("userNo", userNo);
				int myvote = registerService.getMyVote(subParamMap);
				
				logger.debug("myvote : "+myvote);
				
				if(myvote==userNo) {
					profileList.remove(i);
				}
				
			}
			
			int filteredSize = profileList.size();
			logger.debug("내가 투표한거 뺀 후 갯수 : "+filteredSize+"개");
			
			//복호화 
			for(int i=0;i<filteredSize;i++) {
				Map<String,Object> profileMap = profileList.get(i);
				String decodedImg1 = AES256Util.aesDecode(profileMap.get("REGIST_IMAGE1").toString());
				String decodedImg2 = AES256Util.aesDecode(profileMap.get("REGIST_IMAGE2").toString());
				
				profileMap.put("REGIST_IMAGE1", decodedImg1);
				profileMap.put("REGIST_IMAGE2", decodedImg2);
				
			}
			
			
						
			
			Map<String,Object> tempMap = new HashMap<String,Object>();
			tempMap.put("rstFlag", "200");
			tempMap.put("profileList", profileList);
			
			resultMap=CommonUtils.createResultMap("200", "success", tempMap);
			body.put("resultMap",resultMap);
			
		} catch(Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			header.put("retCode", 404);
			header.put("errMsg", "error");
		}
		result.put("header", header);
		result.put("body", body);
		String rst = new Gson().toJson(result);	
		return rst;
	}
	
	//내 신청결과
	@RequestMapping(value = "/getMyResult.do", method = RequestMethod.POST, produces = "application/text; charset=utf8" )
	public String getMyResult(RequestCommand reqParam, HttpSession session,HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> header= new HashMap<String, Object>();
		Map<String, Object> body= new HashMap<String, Object>();
		Map<String, Object> param = reqParam.getParameterMap();
		header.put("retCode", 0);
		header.put("errMsg", "");
		try {
			
			//로그인유저정보
			String jwtObj = (String) request.getAttribute("jwt");
			Jws<Claims> claims = jwtUtil.getClaims(jwtObj);
			int userNo = Integer.parseInt(claims.getBody().get("user_no").toString()); 
			String userId = claims.getBody().get("user_id").toString();
			String userEmail = claims.getBody().get("user_email").toString();
			
			Map<String,Object> subParam  = new HashMap<String,Object>();

			//1.신청목록이 있는지 없는지
			List<Map<String,Object>> myRegistList =  registerService.getMyRegistList(userNo);
		
			if(myRegistList.size()==0) {	//1-1) 신청목록이 없는경우 
				logger.debug("신청목록이 없습니다.");
				
				//FLAG를 던져주자
				
			}else {//1-2) 신청목록이 있는경우
				logger.debug("신청목록이 있습니다.");
				
				//2-1)끝난 목록
				// myRegistList중에서   타겟테이블: 
				// i)만료되었거나, ii)인원수를 모두채웠거나
				
				//2-2)진행중인목록
				// 내꺼중에서 만료되지도 않았고, 인원수도 다안찬 목록들 
				
			}
			
			
		} catch(Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			header.put("retCode", 404);
			header.put("errMsg", "error");
		}
		result.put("header", header);
		result.put("body", body);
		String rst = new Gson().toJson(result);	
		return rst;
	}	
	
	
	
	

//	//특정프로필투표결과
//	@RequestMapping(value = "/resultVote.do", method = RequestMethod.POST, produces = "application/text; charset=utf8" )
//	public String resultVote(RequestCommand reqParam, HttpSession session,HttpServletRequest request) {
//		Map<String, Object> result = new HashMap<String, Object>();
//		Map<String, Object> header= new HashMap<String, Object>();
//		Map<String, Object> body= new HashMap<String, Object>();
//		Map<String, Object> param = reqParam.getParameterMap();
//		header.put("retCode", 0);
//		header.put("errMsg", "");
//		try {
//			
//			//로그인유저정보
////			String jwtObj = (String) request.getAttribute("jwt");
////			Jws<Claims> claims = jwtUtil.getClaims(jwtObj);
//				int userNo = Integer.parseInt(claims.getBody().get("user_no").toString()); 
////			String userId = claims.getBody().get("user_id").toString();
////			String userEmail = claims.getBody().get("user_email").toString();
//			
//			int registNo =  Integer.parseInt(param.get("REGIST_NO").toString());
//			List<Map<String,Object>> resultVoteList = registerService.getVoteList(registNo);
//
//			logger.debug("list size : "+resultVoteList.size());
//			
//			resultVoteList.remove(1);
//			logger.debug("list size : "+resultVoteList.size());
//			
//		} catch(Exception e) {
//			logger.error(e.getMessage());
//			e.printStackTrace();
//			header.put("retCode", 404);
//			header.put("errMsg", "error");
//		}
//		result.put("header", header);
//		result.put("body", body);
//		String rst = new Gson().toJson(result);	
//		return rst;
//	}
}
