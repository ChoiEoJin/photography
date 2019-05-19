package com.collabo.photography.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.collabo.photography.common.util.AES256Util;
import com.collabo.photography.common.util.CommonUtils;
import com.collabo.photography.vo.RequestCommand;
import com.google.gson.Gson;

@RestController
@RequestMapping("/rest")
public class AesController {
	
	
	private static final Logger logger = Logger.getLogger(AesController.class);
	
	@RequestMapping(value="/aesEncode.do",method= RequestMethod.POST,produces ="application/text; charset=UTF-8")
	public String aesEncodeTest(RequestCommand reqParam) {
		
		Map<String,Object> resultMap = new HashMap<>();
		Map<String,Object> param  = reqParam.getParameterMap();
		
		String resultCode ="";
		String resultStatus="";
		String resultStr = "";
		
		try {
			
			String param1_Str = param.get("param1").toString();
			logger.debug("암호황이전 값 : " + param1_Str);
			
			String AES256_EncodedStr = AES256Util.aesEncode(param1_Str);
			logger.debug("암호화이후 값 : "+AES256_EncodedStr);
			
			
			resultStr = AES256_EncodedStr;
			
		}catch(Exception e){
			e.printStackTrace();
			
			
		}
		
		resultMap = CommonUtils.createResultMap(resultCode, resultStatus, resultStr);		
		String rst = new Gson().toJson(resultMap);
		
		return rst;
	}
	
	
	@RequestMapping(value="/aesDecode.do",method= RequestMethod.POST,produces ="application/text; charset=UTF-8")
	public String aesDecodeTest(RequestCommand reqParam) {
		
		Map<String,Object> resultMap = new HashMap<>();
		Map<String,Object> param  = reqParam.getParameterMap();
		
		String resultCode ="";
		String resultStatus="";
		String resultStr = "";
		
		try {
			
			String encodedStr  = param.get("encodedStr").toString();
			logger.debug("복호화 이전값 : " + encodedStr);
			
			String AES256_DecodedStr = AES256Util.aesDecode(encodedStr);
			logger.debug("복호화이후 값 : "+AES256_DecodedStr);
			
			
			resultStr = AES256_DecodedStr;
			
		}catch(Exception e){
			e.printStackTrace();
			
			
		}
		
		resultMap = CommonUtils.createResultMap(resultCode, resultStatus, resultStr);		
		String rst = new Gson().toJson(resultMap);
		
		return rst;
	}
}
