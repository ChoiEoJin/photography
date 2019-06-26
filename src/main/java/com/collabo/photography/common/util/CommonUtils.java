package com.collabo.photography.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;

import org.apache.log4j.Logger;


public class CommonUtils {
	
	private static final Logger log = Logger.getLogger(CommonUtils.class);
	
	public static String getRandomString(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	public static void printMap(Map<String,Object> map){
		Iterator<Entry<String,Object>> iterator = map.entrySet().iterator();
		Entry<String,Object> entry = null;
		log.debug("--------------------printMap--------------------\n");
		while(iterator.hasNext()){
			entry = iterator.next();
			log.debug("key : "+entry.getKey()+",\tvalue : "+entry.getValue());
		}
		log.debug("");
		log.debug("------------------------------------------------\n");
	}
	
	//result에 맵을담을때
	public static Map<String,Object> createResultMap (String resultCode,String resultStatus,Map<String,Object> result){
		
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultCode", resultCode);
		resultMap.put("resultStatus", resultStatus);
		resultMap.put("result", result);
		return resultMap;
		
	}
	
	//result에 맵리스트를넘길때
	public static Map<String,Object> createResultMap (String resultCode,String resultStatus,List<Map<String,Object>> result){
		
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultCode", resultCode);
		resultMap.put("resultStatus", resultStatus);
		resultMap.put("result", result);
		return resultMap;
	}
	
	//result에 문자열담을때
	public static Map<String,Object> createResultMap (String resultCode,String resultStatus,String result){
		
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultCode", resultCode);
		resultMap.put("resultStatus", resultStatus);
		resultMap.put("result", result);
		return resultMap;
	}
	
	//result에 int형담을때
	public static Map<String,Object> createResultMap (String resultCode,String resultStatus,int result){
		
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultCode", resultCode);
		resultMap.put("resultStatus", resultStatus);
		resultMap.put("result", result);
		return resultMap;
	}
	
	//result에 boolean형담을때
	public static Map<String,Object> createResultMap (String resultCode,String resultStatus,boolean result){
		
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultCode", resultCode);
		resultMap.put("resultStatus", resultStatus);
		resultMap.put("result", result);
		return resultMap;
	}
	
	
	//나이계산기(윤년때문에 차이나는 일수 처리X)
	public static int calAge(String birth) throws ParseException {

		Date date = new SimpleDateFormat("yyyy-MM-dd").parse(birth);
		//long gap = (new Date().getTime())-(date.getTime()+32400000);// 1970/01/01 - 1970/01/01 :00:00:00 하니까 -32400000(9시간) 차이가나던데..
		long gap = (new Date().getTime())-(date.getTime());
		long seconds = gap/1000;
		long days = seconds/(60*60*24);
		int age = (int) (days/365);

		return age;
	}
	
	//뱃지만들기
	public static String makingBadge(int age,String gender) {

		String badge = "AAA,AA"+gender;

		int ageQ  = age / 10;
		int R = age % 10;
		String ageR = "";

		if(0<= R && R < 4){
		  ageR="E";
		}else if( 4<= R && R <7){
		  ageR="M";
		}else{
		  ageR="L";
		}

		//집합
		int[] arrAgeQ = {ageQ};
		String[] arrAgeR = {"A",ageR};
		String[] arrGender = {"A",gender};
		
		//뱃지만들기
		for (int i = 0 ; i<arrAgeQ.length ; i++){
		   for (int j = 0 ; j<arrAgeR.length ; j++){
		       for (int k = 0 ; k<arrGender.length ; k++){
		       badge+=","+arrAgeQ[i];
		       badge+=arrAgeR[j];
		       badge+=arrGender[k];
		           }
		      }
		}

		return badge;
	}

	public static String parameterVarchar(String str) {
		
		String varchar = "'"+str+"'";
		
		return varchar;
	}
	
}

