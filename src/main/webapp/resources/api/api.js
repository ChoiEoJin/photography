//encodingStatus=utf-8
var api = 
{	/*** for chat *****/
	"cls": [

		{
			"name": "API Doc",
			"desc": "photography api doc",
			"method": [
				{
					"name": "회원가입",
					"label": "success", 	//success, primary, danger, warning
					"desc": "/rest/userRegist.do",
					"progress":{ 
						"rate":"100",
						"desc":""
					},
					"info": {
						"url": "/rest/userRegist.do",
						"reqParam": [
							{
								"name":"uuid", "type":"string", "req":"yes", "value":"chldjwlsuuid",
								"desc":"기기ID"
							},
							{
								"name":"email", "type":"string", "req":"yes", "value":"yms3684@naver.com",
								"desc":"이메일"
							},
							{
								"name":"gender", "type":"string", "req":"yes", "value":"M",
								"desc":"성별"
							},
							{
								"name":"birth", "type":"string", "req":"yes", "value":"1992/09/17",
								"desc":"생년월일"
							},
							{
								"name":"grade", "type":"string", "req":"yes", "value":"3",
								"desc":"등급"
							},
						],
						"respParam": [
						]
					},
					"sample": {
						"desc": "",
						"resp": '{"retCode": 0,"errMsg": "","retBody": ""}'
					}
				},
				{
					"name": "이메일중복체크",
					"label": "success", 	//success, primary, danger, warning
					"desc": "/rest/emailDuplicatedCheck.do",
					"progress":{ 
						"rate":"100",
						"desc":""
					},
					"info": {
						"url": "/rest/emailDuplicatedCheck.do",
						"reqParam": [
							{
								"name":"uuid", "type":"string", "req":"yes", "value":"chldjwlsuuid",
								"desc":"기기ID"
							},
							{
								"name":"email", "type":"string", "req":"yes", "value":"yms3684@naver.com",
								"desc":"이메일"
							}
						],
						"respParam": [
							{
								"name":"result", "type":"string",
								"desc":"601:레코드없음,602:다른기기에서 사용중, 603:현재기기에서 사용중"
							},
						]
					},
					"sample": {
						"desc": "",
						"resp": '{"retCode": 0,"errMsg": "","retBody": ""}'
					}
				},
				{
					"name": "이메일인증요청",
					"label": "success", 	//success, primary, danger, warning
					"desc": "/rest/emailAuthRequest.do",
					"progress":{ 
						"rate":"100",
						"desc":""
					},
					"info": {
						"url": "/rest/emailAuthRequest.do",
						"reqParam": [
							{
								"name":"uuid", "type":"string", "req":"yes", "value":"chldjwlsuuid",
								"desc":"기기ID"
							},
							{
								"name":"email", "type":"string", "req":"yes", "value":"yms3684@naver.com",
								"desc":"이메일"
							}
						],
						"respParam": [
							{
								"name":"result", "type":"string",
								"desc":""
							},
						]
					},
					"sample": {
						"desc": "",
						"resp": '{"retCode": 0,"errMsg": "","retBody": ""}'
					}
				},
				{
					"name": "이메일인증",
					"label": "success", 	//success, primary, danger, warning
					"desc": "/rest/emailAuth.do",
					"progress":{ 
						"rate":"100",
						"desc":""
					},
					"info": {
						"url": "/rest/emailAuth.do",
						"reqParam": [
							{
								"name":"uuid", "type":"string", "req":"yes", "value":"chldjwlsuuid",
								"desc":"기기ID"
							},
							{
								"name":"email", "type":"string", "req":"yes", "value":"yms3684@naver.com",
								"desc":"이메일"
							},
							{
								"name":"authNum", "type":"string", "req":"yes", "value":"",
								"desc":"인증번호"
							}
						],
						"respParam": [
							{
								"name":"result", "type":"string",
								"desc":"610:인증성공, 611:인증번호불일치 , 612:인증시간만료"
							},
						]
					},
					"sample": {
						"desc": "",
						"resp": '{"retCode": 0,"errMsg": "","retBody": ""}'
					}
				},
				{
					"name": "로그인",
					"label": "success", 	//success, primary, danger, warning
					"desc": "/rest/emailLogin.do",
					"progress":{ 
						"rate":"100",
						"desc":""
					},
					"info": {
						"url": "/rest/emailLogin.do",
						"reqParam": [
							{
								"name":"uuid", "type":"string", "req":"yes", "value":"chldjwlsuuid",
								"desc":"기기명"
							},
							{
								"name":"email", "type":"string", "req":"yes", "value":"yms3684@naver.com",
								"desc":"이메일"
							},
						],
						"respParam": [
							{
								"name":"result", "type":"string",
								"desc":"601:레코드없음,602:다른기기에서 사용중, 603:현재기기에서 사용중"
							},
						]
					},
					"sample": {
						"desc": "",
						"resp": '{"retCode": 0,"errMsg": "","retBody": ""}'
					}
				},
				{
					"name": "로그아웃",
					"label": "warning", 	//success, primary, danger, warning
					"desc": "/rest/userLogout.do",
					"progress":{ 
						"rate":"100",
						"desc":""
					},
					"info": {
						"url": "/rest/userLogout.do",
						"reqParam": [
							{
								"name":"변수명1", "type":"string", "req":"yes", "value":"변수명1",
								"desc":"변수명1"
							},
							{
								"name":"변수명2", "type":"string", "req":"yes", "value":"변수명2",
								"desc":"변수명2"
							},
						],
						"respParam": [
						]
					},
					"sample": {
						"desc": "",
						"resp": '{"retCode": 0,"errMsg": "","retBody": ""}'
					}
				},
				{
					"name": "업로드페이지가기",
					"label": "warning", 	//success, primary, danger, warning
					"desc": "test link: <a target='_test' href='/upload.do'>test</a>",
					"progress":{ 
						"rate":"100",
						"desc":""
					},
					"info": {
						"url": "/rest/upload.do",
						"reqParam": [
						],
						"respParam": [
						]
					},
					"sample": {
						"desc": "",
						"resp": '{"retCode": 0,"errMsg": "","retBody": ""}'
					}
				},
				{
					"name": "사진등록하기",
					"label": "success", 	//success, primary, danger, warning
					"desc": "/rest/registerMyProfile.do",
					"progress":{ 
						"rate":"100",
						"desc":""
					},
					"info": {
						"url": "/rest/registerMyProfile.do",
						"reqParam": [
							{
								"name":"REGIST_IMAGE1", "type":"string", "req":"yes", "value":"img1_hashCode",
								"desc":"1번이미지"
							},
							{
								"name":"REGIST_IMAGE2", "type":"string", "req":"yes", "value":"img2_hashCode",
								"desc":"2번이미지"
							},
							{
								"name":"REGIST_COMMENT", "type":"string", "req":"yes", "value":"새학기 사진 어떤게좋을까요?",
								"desc":"등록코멘트"
							},
							{
								"name":"SELECTER_CNT", "type":"string", "req":"yes", "value":"3",
								"desc":"원하는 평가자수"
							},
							{
								"name":"SELECTER_OLD1", "type":"string", "req":"yes", "value":"2",
								"desc":"A:누구나, 10대:1, 20대:2 30대:3 "
							},
							{
								"name":"SELECTER_OLD2", "type":"string", "req":"yes", "value":"L",
								"desc":"A:누구나, E:초반, M:중반 , L:후반 "
							},
							{
								"name":"SELECTER_GENDER", "type":"string", "req":"yes", "value":"M",
								"desc":"A:누구나, M:남자, F:여자"
							},

							{
								"name":"END_DATE", "type":"string", "req":"yes", "value":"2019/06/30",
								"desc":"종료일"
							},
						],
						"respParam": [
						]
					},
					"sample": {
						"desc": "",
						"resp": '{"retCode": 0,"errMsg": "","retBody": ""}'
					}
				},
				{
					"name": "사진등록하기옵션가져오기",
					"label": "success", 	//success, primary, danger, warning
					"desc": "/rest/getProfileRegisterOption.do",
					"progress":{ 
						"rate":"100",
						"desc":""
					},
					"info": {
						"url": "/rest/getProfileRegisterOption.do",
						"reqParam": [
						],
						"respParam": [
						]
					},
					"sample": {
						"desc": "",
						"resp": '{"retCode": 0,"errMsg": "","retBody": ""}'
					}
				},
				{
					"name": "미승인리스트가져오기",
					"label": "success", 	//success, primary, danger, warning
					"desc": "/rest/unauthProfileList.do",
					"progress":{ 
						"rate":"100",
						"desc":""
					},
					"info": {
						"url": "/rest/unauthProfileList.do",
						"reqParam": [
						],
						"respParam": [
						]
					},
					"sample": {
						"desc": "",
						"resp": '{"retCode": 0,"errMsg": "","retBody": ""}'
					}
				},
				{
					"name": "프로필검열 승인/비승인",
					"label": "success", 	//success, primary, danger, warning
					"desc": "/rest/censorProfile.do",
					"progress":{ 
						"rate":"100",
						"desc":""
					},
					"info": {
						"url": "/rest/censorProfile.do",
						"reqParam": [
							{
								"name":"REGIST_NO", "type":"int", "req":"yes", "value":"",
								"desc":"등록PK"
							},
							{
								"name":"REGIST_AUTH_CHK", "type":"string", "req":"yes", "value":"Y",
								"desc":"승인,비승인"
							},
						],
						"respParam": [
						]
					},
					"sample": {
						"desc": "",
						"resp": '{"retCode": 0,"errMsg": "","retBody": ""}'
					}
				},
				{
					"name": "목록중하나클릭상세보기",
					"label": "success", 	//success, primary, danger, warning
					"desc": "/rest/detailProfile.do",
					"progress":{ 
						"rate":"100",
						"desc":""
					},
					"info": {
						"url": "/rest/detailProfile.do",
						"reqParam": [
							{
								"name":"REGIST_NO", "type":"int", "req":"yes", "value":"",
								"desc":"등록PK"
							}
						],
						"respParam": [
						]
					},
					"sample": {
						"desc": "",
						"resp": '{"retCode": 0,"errMsg": "","retBody": ""}'
					}
				},
				{
					"name": "투표하기",
					"label": "success", 	//success, primary, danger, warning
					"desc": "/rest/voteRegist.do",
					"progress":{ 
						"rate":"100",
						"desc":""
					},
					"info": {
						"url": "/rest/voteRegist.do",
						"reqParam": [
							{
								"name":"REGIST_NO", "type":"int", "req":"yes", "value":"",
								"desc":"등록PK"
							},
							{
								"name":"VOTE", "type":"int", "req":"yes", "value":"1",
								"desc":"1번사진:1 , 2번사진:2"
							},
						],
						"respParam": [
						]
					},
					"sample": {
						"desc": "",
						"resp": '{"retCode": 0,"errMsg": "","retBody": ""}'
					}
				},
				{
					"name": "미완상태의 골라줘 리스트",
					"label": "success", 	//success, primary, danger, warning
					"desc": "/rest/getChooseList.do",
					"progress":{ 
						"rate":"100",
						"desc":""
					},
					"info": {
						"url": "/rest/getChooseList.do",
						"reqParam": [
						],
						"respParam": [
						]
					},
					"sample": {
						"desc": "",
						"resp": '{"retCode": 0,"errMsg": "","retBody": ""}'
					}
				},
				{
					"name": "내 신청결과",
					"label": "success", 	//success, primary, danger, warning
					"desc": "/rest/getMyResult.do",
					"progress":{ 
						"rate":"100",
						"desc":""
					},
					"info": {
						"url": "/rest/getMyResult.do",
						"reqParam": [
						],
						"respParam": [
						]
					},
					"sample": {
						"desc": "",
						"resp": '{"retCode": 0,"errMsg": "","retBody": ""}'
					}
				},
				{
					"name": "투표종료하기(사용자)",
					"label": "success", 	//success, primary, danger, warning
					"desc": "/rest/terminateMyRegist.do",
					"progress":{ 
						"rate":"100",
						"desc":""
					},
					"info": {
						"url": "/rest/terminateMyRegist.do",
						"reqParam": [
							{
								"name":"REGIST_NO", "type":"int", "req":"yes", "value":"",
								"desc":"TB_REGIST PK"
							},
						],
						"respParam": [
						]
					},
					"sample": {
						"desc": "",
						"resp": '{"retCode": 0,"errMsg": "","retBody": ""}'
					}
				},
				{
					"name": "뱃지만들기",
					"label": "success", 	//success, primary, danger, warning
					"desc": "/rest/insertBadge.do",
					"progress":{ 
						"rate":"100",
						"desc":""
					},
					"info": {
						"url": "/rest/insertBadge.do",
						"reqParam": [
						],
						"respParam": [
						]
					},
					"sample": {
						"desc": "",
						"resp": '{"retCode": 0,"errMsg": "","retBody": ""}'
					}
				},
				{
					"name": "뱃지검색",
					"label": "success", 	//success, primary, danger, warning
					"desc": "/rest/getBadgeVal.do",
					"progress":{ 
						"rate":"100",
						"desc":""
					},
					"info": {
						"url": "/rest/getBadgeVal.do",
						"reqParam": [
							{
								"name":"BADGE_NAME", "type":"int", "req":"yes", "value":"2AM",
								"desc":"뱃지코드명"
							},
						],
						"respParam": [
						]
					},
					"sample": {
						"desc": "",
						"resp": '{"retCode": 0,"errMsg": "","retBody": ""}'
					}
				},
				{//AES256암호화
					"name": "aesEncode.do",
					"label": "success", 	//success, primary, danger, warning
					"desc": "/rest/aesEncode.do",
					"progress":{ 
						"rate":"100",
						"desc":""
					},
					"info": {
						"url": "/rest/aesEncode.do",
						"reqParam": [
							{
								"name":"param1", "type":"string", "req":"yes", "value":"hello!!",
								"desc":"그냥일반문자열"
							},
						],
						"respParam": [
						]
					},
					"sample": {
						"desc": "",
						"resp": '{"retCode": 0,"errMsg": "","retBody": ""}'
					}
				},
				{//AES256복호화
					"name": "aesDecode.do",
					"label": "success", 	//success, primary, danger, warning
					"desc": "/rest/aesDecode.do",
					"progress":{ 
						"rate":"100",
						"desc":""
					},
					"info": {
						"url": "/rest/aesDecode.do",
						"reqParam": [
							{
								"name":"encodedStr", "type":"string", "req":"yes", "value":"",
								"desc":"aes256으로 암호화된 문자열"
							},
						],
						"respParam": [
						]
					},
					"sample": {
						"desc": "",
						"resp": '{"retCode": 0,"errMsg": "","retBody": ""}'
					}
				},
//				{//투표결과
//					"name": "resultVote.do",
//					"label": "success", 	//success, primary, danger, warning
//					"desc": "/rest/resultVote.do",
//					"progress":{ 
//						"rate":"100",
//						"desc":""
//					},
//					"info": {
//						"url": "/rest/resultVote.do",
//						"reqParam": [
//							{
//								"name":"REGIST_NO", "type":"string", "req":"yes", "value":"1",
//								"desc":""
//							},
//						],
//						"respParam": [
//						]
//					},
//					"sample": {
//						"desc": "",
//						"resp": '{"retCode": 0,"errMsg": "","retBody": ""}'
//					}
//				},
			]
		},
//		{
//			"name": "TEST",
//			"desc": "테스트",
//			"method": [
//				{
//					"name": "getTestData",
//					"label": "warning", 	//success, primary, danger, warning
//					"desc": "test",
//					"progress":{ 
//						"rate":"100",
//						"desc":""
//					},
//					"info": {
//						"url": "/rest/test.do",
//						"reqParam": [
//							{
//								"name":"id", "type":"string", "req":"yes", "value":"admin",
//								"desc":"아이디"
//							},
//							{
//								"name":"password", "type":"string", "req":"yes", "value":"admin1234",
//								"desc":"비밀번호"
//							},
//						],
//						"respParam": [
//						]
//					},
//					"sample": {
//						"desc": "ddsdadads",
//						"resp": '{"retCode": 0,"errMsg": "","retBody": ""}'
//					}
//				},
//			]
//		},
	]
};