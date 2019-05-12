package com.collabo.photography.service.mapper;

import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

	void registerUserInfo(Map<String, Object> regUserMap);
	Map<String, Object> getUUID_By_EMAIL(Map<String, Object> emailCheckMap);

}
