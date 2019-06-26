package com.collabo.photography.service.mapper;

import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface AuthMapper {

	Map<String, Object> getAuthPK_BY_UUID(Map<String, Object> searchAuthMap);
	int insertAuthInfo(Map<String, Object> searchAuthMap);
	void updateAuthInfo(Map<String, Object> rstMap);
	Map<String,Object> getAuthInfoByUUID_EMAIL(Map<String, Object> authParamMap);

}
