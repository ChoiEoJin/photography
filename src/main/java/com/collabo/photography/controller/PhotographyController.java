package com.collabo.photography.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.collabo.photography.common.util.CommonUtils;
import com.collabo.photography.dao.TestDao;
import com.collabo.photography.vo.RequestCommand;

@RestController
@RequestMapping("/rest")
public class PhotographyController {
	private static final Logger logger = Logger.getLogger(PhotographyController.class);

	
	@Resource
	TestDao testDao;
	
	//1.회원가입요청
	@RequestMapping(value = "/userRegist.do", method = RequestMethod.POST, produces = "application/text; charset=utf8" )
	public String userRegist(RequestCommand reqParam, HttpSession session) {
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
	
	//2.로그인요청
	@RequestMapping(value = "/userLogin.do", method = RequestMethod.POST, produces = "application/text; charset=utf8" )
	public String userLogin(RequestCommand reqParam, HttpSession session) {
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
	public String getMainData(RequestCommand reqParam, HttpSession session) {
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
	
	//7.내 결과보기?
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
	@RequestMapping("/dbJsonList.do")
	public Map<String,Object> cc(){
		Map<String,Object> rst = new HashMap<>();	
		List<Map<String,Object>> list = new ArrayList<>();	
		list= testDao.getTestListMap();	
		rst.put("resultData", list);	
		logger.debug(rst);
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

}
