package com.collabo.photography.common.util;

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
	public static Map<String,Object> createResultMap (String resultCode,String resultstatus,Map<String,Object> result){
		
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultCode", resultCode);
		resultMap.put("resultstatus", resultstatus);
		resultMap.put("result", result);
		return resultMap;
		
	}
	
	//result에 맵리스트를넘길때
	public static Map<String,Object> createResultMap (String resultCode,String resultstatus,List<Map<String,Object>> result){
		
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultCode", resultCode);
		resultMap.put("resultstatus", resultstatus);
		resultMap.put("result", result);
		return resultMap;
	}
	
	//result에 문자열담을때
	public static Map<String,Object> createResultMap (String resultCode,String resultstatus,String result){
		
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultCode", resultCode);
		resultMap.put("resultstatus", resultstatus);
		resultMap.put("result", result);
		return resultMap;
	}
	
	//result에 int형담을때
	public static Map<String,Object> createResultMap (String resultCode,String resultstatus,int result){
		
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultCode", resultCode);
		resultMap.put("resultstatus", resultstatus);
		resultMap.put("result", result);
		return resultMap;
	}
	
	//result에 boolean형담을때
	public static Map<String,Object> createResultMap (String resultCode,String resultstatus,boolean result){
		
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("resultCode", resultCode);
		resultMap.put("resultstatus", resultstatus);
		resultMap.put("result", result);
		return resultMap;
	}
}

