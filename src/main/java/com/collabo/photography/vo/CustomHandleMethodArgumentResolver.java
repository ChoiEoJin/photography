package com.collabo.photography.vo;

import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;


public class CustomHandleMethodArgumentResolver implements HandlerMethodArgumentResolver {
    private static final Logger logger = LoggerFactory.getLogger(CustomHandleMethodArgumentResolver.class);
	
	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
		HashMap<String, Object> parameterMap = new HashMap<String, Object>();
		String tblN = request.getParameter("tblN");
		
		@SuppressWarnings("unchecked")
		Enumeration<String> parameterNames = request.getParameterNames();
		
//		logger.error("==============================");
//		logger.error("CustomHandleMethodArg tableName=" + tblN);

		while (parameterNames.hasMoreElements()) {
			String key = parameterNames.nextElement();
			String[] values = request.getParameterValues(key);
			
			parameterMap.put(key, values[0]);
//			try {
//				int value = Integer.parseInt(values[0]);
//				parameterMap.put(key, value);
//			} catch(NumberFormatException e) {
//				parameterMap.put(key, values[0]);
//			}
		}
		
		
//		while (parameterNames.hasMoreElements()) {
//			String key = parameterNames.nextElement();
//			String[] values = request.getParameterValues(key);
//			
//
//			if(tblN != null) 
//			{				
////				if(Ivalues != 0 || values[0] == "")continue;				
//				//문자열 들은 그대로 맵에 저장
//				if (values != null) {
//					if (values.length == 1) {
//						parameterMap.put(key, values[0]);
//					} else {
//						parameterMap.put(key, values);
//					}
//				}
//			}
//			else {
//				//문자열 들은 그대로 맵에 저장
//				if (values != null) {
//					if (values.length == 1) {
//						parameterMap.put(key, values[0]);
//					} else {
//						parameterMap.put(key, values);
//					}
//				}
//			}
//			
//		}
	
		// Controller에서 매게변수로 받을 수 있게 커맨드를 생성해 데이터를 set하여 리턴한다.
		return new RequestCommand(parameterMap);
	}

	@Override
	public boolean supportsParameter(MethodParameter methodParameter) {
		return RequestCommand.class.isAssignableFrom(methodParameter.getParameterType());
	}

}

