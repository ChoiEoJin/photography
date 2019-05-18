package com.collabo.photography.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.collabo.photography.common.jwt.JwtUtil;
import com.collabo.photography.common.util.AuthUtil;
import com.collabo.photography.common.util.CommonUtils;
import com.collabo.photography.common.util.SendMail;
import com.collabo.photography.service.mapper.AuthMapper;
import com.collabo.photography.service.mapper.LoginMapper;
import com.collabo.photography.service.mapper.UserMapper;
import com.collabo.photography.vo.RequestCommand;
import com.google.gson.Gson;

@RestController
@RequestMapping("/rest")
public class PhotographyController {
	private static final Logger logger = LoggerFactory.getLogger(PhotographyController.class);

	
	@Inject
	private AuthMapper authService;
	
	@Inject
	private UserMapper userService;
	
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
			System.out.println("회원가입 드렁옴");
			
			String uuid = param.get("uuid").toString().trim();
			String email = param.get("email").toString().trim();
			String gender = param.get("gender").toString().trim();
			String birth= param.get("birth").toString().trim();
			String grade = param.get("grade").toString().trim();
			String USER_NO ="";
			System.out.println("uuid : "+ uuid);
			System.out.println("email : "+ email);
			System.out.println("gender : "+ gender);
			System.out.println("birth : "+ birth); 
			System.out.println("grade : "+ grade);
			
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
			System.out.println("USER_NO : "+ USER_NO);
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
			System.out.println("uuid : "+uuid);
			System.out.println("email : "+email);
			
			Map<String,Object> emailCheckMap = new HashMap<>();
			emailCheckMap.put("uuid", uuid);
			emailCheckMap.put("email", email);
			Map<String,Object> userInfoMap = userService.getUUID_By_EMAIL(emailCheckMap);
			
			String checkFlag = "";
			if(userInfoMap.isEmpty()) {
				System.out.println("601 : 이메일없음");
				checkFlag="601";				
			}else {
				String rstUUID =  userInfoMap.get("UUID").toString();
				if(uuid.equals(rstUUID)==false) {
					System.out.println("다른기기에서 사용중입니다.");
					
					checkFlag="602";
				}else {				
					System.out.println("현재기기에서  사용중입니다.");
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
			
			System.out.println("uuid : "+uuid);
			System.out.println("email : "+email);
			
			//1.인증키생성
			String tempKey = authUtil.generateKey(8);
			System.out.println("authKey:"+tempKey);
			
			//2.uuid,email로 tb_auth 를 조회해서,
			Map<String,Object> searchAuthMap =  new HashMap<>();
			searchAuthMap.put("uuid",uuid);
			
			System.out.println(authService.getAuthPK_BY_UUID(searchAuthMap));

				
			
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
				System.out.println("AUTH테이블에 기존데이터없음 ");
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
				System.out.println("AUTH테이블에 기존데이터있음 ");
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
				System.out.println("인증번호불일치");
				rstFlag = "611";
			}else {
				long authExpired = Long.parseLong(authRstMap.get("AUTH_EXPIRED").toString());
				long curTimeMill = System.currentTimeMillis();
				
				if(authExpired<curTimeMill) {
					System.out.println("유효기간만료");
					rstFlag = "612";
				}else {
					System.out.println("인증성공");
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
				  System.out.println("해당기기로 등록된 유저정보없음");
				  rstFlag="UNREGISTERD_01";
			  }else {
				  
				  int rstUserNo = Integer.parseInt(rstMap.get("USER_NO").toString());
				  String rstUserId  = rstMap.get("USER_ID").toString();
				  String rstEmail = rstMap.get("USER_EMAIL").toString();
//				  String rstUserGender = rstMap.get("USER_GENDER").toString();
//				  String rstUserBirth = rstMap.get("USER_BIRTH").toString();//date
//				  int rstUserGrade = Integer.parseInt(rstMap.get("USER_GRADE").toString());
				  
				  if(rstEmail.trim().equals("")) {
					  System.out.println("기기변경후 아직 이메일없음");
					  rstFlag="UNREGISTERD_02";
					  
				  }else if(p_email.equals(rstEmail)==false) {
					  System.out.println("이메일이 일치하지 않습니다");
					  rstFlag="MISMATCH_02";
					  
				  }else {//4.있으면 토큰생성한다. 
					  System.out.println("로그인성공");
					  
					  Map<String,Object> jwtParamMap =  new HashMap<>();
					  jwtParamMap.put("user_no", rstUserNo);
					  jwtParamMap.put("user_id", rstUserId);
					  jwtParamMap.put("user_email", rstEmail);
					  
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
	
	//4.메인화면데이터요청 
	@RequestMapping(value = "/getMainData.do", method = RequestMethod.POST, produces = "application/text; charset=utf8" )
	public String getMainData(RequestCommand reqParam, HttpSession session ,HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> param = reqParam.getParameterMap();
		String resultCode ="0";
		String resultstatus  ="";
		
		try {
			
			String testJWT =  request.getAttribute("Authorization").toString();
			System.out.println("Authorization : "+testJWT);
			
			String var1 = param.get("var1").toString();
			String var2 = param.get("var2").toString();
			System.out.println("var1 : "+var1);
			System.out.println("var2 : "+var2);
			
			
			
			
			
			
			
			
			
			
			
			
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
	
	//5.골라줘 리스트 (미완상태의 골라줘 리스트) 요청 
	@RequestMapping(value = "/getChooseList.do", method = RequestMethod.POST, produces = "application/text; charset=utf8" )
	public String getChooseList(RequestCommand reqParam, HttpSession session) {
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
		
	//6.마이데이타 ?
	@RequestMapping(value = "/getMyData.do", method = RequestMethod.POST, produces = "application/text; charset=utf8" )
	public String getMyData(RequestCommand reqParam, HttpSession session) {
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
	
	
	//7.내 결과보기?
	@RequestMapping(value = "/getMyResult.do", method = RequestMethod.POST, produces = "application/text; charset=utf8" )
	public String getMyResult(RequestCommand reqParam, HttpSession session) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> param = reqParam.getParameterMap();
		String resultCode ="0";
		String resultstatus  ="";
			System.out.println("getMyResult");
		try {
			List<Map<String,Object>> list = new ArrayList<>();
			

			
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
	
	//8.리턴맵테스트
	@RequestMapping(value = "/returnMap.do", method = RequestMethod.POST, produces = "application/text; charset=utf8" )
	public String returnMap(RequestCommand reqParam, HttpSession session) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> param = reqParam.getParameterMap();
		String resultCode ="0";
		String resultstatus  ="";
			System.out.println("getMyResult");
		try {
			List<Map<String,Object>> list = new ArrayList<>();
			
			System.out.println("list : "+list);
			System.out.println("list.size()"+list.size());
			System.out.println("list.isEmpty() : "+list.isEmpty());
			
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
	
	//암호화
	@RequestMapping(value = "/encryptTest.do", method = RequestMethod.POST, produces = "application/text; charset=utf8" )
	public String encryptTest(RequestCommand reqParam, HttpSession session) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> param = reqParam.getParameterMap();
		String resultCode ="0";
		String resultstatus  ="";
		
		try {
			
			//변수받는부분
			System.out.println(param.get("var1").toString());
			
			//암호화하기
			
			
			
			
			
			
			
			
			
			
			
			
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
	


}
