package com.collabo.photography.dao;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
@Repository
public class AnalystDao {
	@Inject
	private SqlSessionTemplate sqlSession;

	private static final String NAME_SPACE = "com.spring.choosebetter.mapper.analyst";
	
	
	
}
