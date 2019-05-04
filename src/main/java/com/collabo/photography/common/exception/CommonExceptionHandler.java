package com.collabo.photography.common.exception;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.core.Ordered;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CommonExceptionHandler implements HandlerExceptionResolver, Ordered {

	private static final Logger log = Logger.getLogger(CommonExceptionHandler.class);

	private int order;

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}
	
	private Map writeLog(HttpServletRequest request, String classNm, String errorCode, String errorMsg) {
		Map responseMap = new LinkedHashMap();
		responseMap.put("statusCode", 	errorCode);
		responseMap.put("statusMessage",  errorMsg);
		responseMap.put("statusInfo", 	null);
		return responseMap;
	}
	
	/**
	 * Logic Excetion 처리
	 * @param request
	 * @param ex
	 * @return 
	 */
	public Map commonException(HttpServletRequest request, CommonException ex) {	// HttpServletRequest request, HttpServletResponse response, Object object, SnsException ex
		return this.writeLog(request, ex.getClass().getName(), ex.getCode(), ex.getMessage());
	}
	
	/**
	 * Database Value Exception 처리 (8000)
	 * @param request
	 * @param ex
	 * @return
	 */
	public Map databaseException(HttpServletRequest request, Exception ex) {
		// @Repository에서 넘어온 exception..그리고 scan으로 생성한 bean이 아니므로 @ExceptionHandler <- 이거 안 먹힘.
		String errMessage = "";
		if (ex.getClass().getName().contentEquals("DuplicateKeyException")) {
			errMessage = "같은 Data가 존재합니다.";
		} else {
			if (ex.getMessage().indexOf("value too long") > -1) {
				errMessage = "Data가 너무 깁니다.";
			} else if (ex.getMessage().indexOf("violates foreign key constraint") > -1) { // FK 오류.
				if (ex.getMessage().indexOf("is still referenced from table") > -1) {
					errMessage = "해당 Data를 참고하는 Table이 존재합니다.\n\n참고하고 있는 Table에서 Data를 삭제 후 시도하시기 바랍니다.";
				} else {
					errMessage = "원문이 존재하지 않습니다.\n\n목록을 새로고침한 후 수행하시기 바랍니다.";					
				}
			} else if (ex.getLocalizedMessage().indexOf("foreign key exists") > -1) { // FK 오류.
				errMessage = "관련 데이타가 존재합니다. 관련 데이타를 모두 삭제 후 삭제하시기 바랍니다.";
			} else {
//				errMessage = ex.getMessage();
				errMessage = "DB 처리중 장애가 발생하였습니다.\n\n관리자에게 문의하시기 바랍니다.";
			}
		}
		return this.writeLog(request, ex.getClass().getName(), "-8000", errMessage);
	}
	
	/**
	 * 전체 Excetion 처리 (500)
	 * @param request
	 * @param ex
	 * @return
	 */
	public Map baseException(HttpServletRequest request, Exception ex) {
		Map rmap = this.writeLog(request, ex.getClass().getName(), "-9999", "사용자 요청 중 장애가 발생하였습니다.\n\n잠시 후 다시 시도해주시기 바랍니다.");
		return rmap;
	}
	
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object object, Exception ex) {
		int status = 200;
		Map rmap = null;
		if (ex instanceof CommonException) {
			rmap = commonException(request, (CommonException)ex);
		} else if (ex instanceof DataIntegrityViolationException || ex instanceof SQLException || ex instanceof UncategorizedSQLException) {
			rmap = databaseException(request, ex);
		} else { // 제일 마지막이 Exception 임.(순서 중요. 모든 exception이 Exception의 instance 이므로 상속관계 중 가장 위를 가장 아래에서 비교해야 함.
			rmap = baseException(request, ex);
		}
		
		String userAgent = request.getHeader("User-Agent");
		
		// 관련 LINK : http://msdn.microsoft.com/en-us/library/ms537503(v=vs.85).aspx
		boolean isIE = false;
		if (userAgent != null) {// IE 같이 frame으로 exception을 보낼 경우 application/json 으로 넘기면 인식을 못함(file upload에서 exception 내보면 확인 가능, file upload에서 200M 이상 올리면 테스트 가능)
			// rv:11.0 => IE11
			isIE = (userAgent.indexOf("MSIE") > -1 || userAgent.indexOf("rv:") > -1);
			if (isIE) {
				response.setCharacterEncoding("UTF-8");
				response.setHeader("Content-Type", "text/html;UTF-8");

				ObjectMapper mapper = new ObjectMapper();
				String a;
				try {
					a = mapper.writeValueAsString(rmap);
					response.getWriter().write(a);

					response.getWriter().flush();
					response.getWriter().close();
				} catch (JsonProcessingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
			}
		}
		// userAgent 가 null 일 때도 보내야 함.
		response.setStatus(status);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		
		ObjectMapper mapper = new ObjectMapper();
		String a;
		try {
			a = mapper.writeValueAsString(rmap);
			response.getWriter().write(a);
			
			response.getWriter().flush();
			response.getWriter().close();
		} catch (JsonProcessingException e) {
//			e.printStackTrace();
		} catch (IOException e) {
//			e.printStackTrace();
		}
		
		return null;
	}
}

