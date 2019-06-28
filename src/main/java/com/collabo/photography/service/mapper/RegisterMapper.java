package com.collabo.photography.service.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public interface RegisterMapper {

	int registerProfile(Map<String, Object> insertParam);

	List<Map<String, Object>> getUnauthProfileList();

	void updateRegistAuthChk(Map<String, Object> updateRegistAuthChkParamMap);

	void registVOTE(Map<String, Object> voteParam);

	Map<String, Object> getProfileInfo(int registNo);

	List<Map<String, Object>> getAgeCode();

	List<Map<String, Object>> getGenderCode();

	List<Map<String, Object>> getChooseList(String query);

	List<Map<String, Object>> getVoteList(int registNo);//테스트용

	int getMyVote(Map<String, Object> subParamMap);

	List<Map<String, Object>> getMyRegistList(int userNo);

	int getBeingVotedCnt(int registNo);

}
