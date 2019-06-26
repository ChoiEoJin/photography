package com.collabo.photography.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
@Service
public class TestDao {
	@Inject
	private SqlSessionTemplate sqlSession;

	private static final String NAME_SPACE = "com.spring.test.mapper.test";
	
	public List<Map<String, Object>> getTestListMap() {		
		return sqlSession.selectList(NAME_SPACE + ".getTestListMap");
	}

}
