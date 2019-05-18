package com.collabo.photography.service.mapper;

import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface LoginMapper {
	Map<String, Object> getUserInfoByUUID(Map<String, Object> param);
}
