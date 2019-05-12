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
					"label": "warning", 	//success, primary, danger, warning
					"desc": "/rest/userLogin.do",
					"progress":{ 
						"rate":"100",
						"desc":""
					},
					"info": {
						"url": "/rest/userLogin.do",
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
					"name": "메인화면데이터요청",
					"label": "warning", 	//success, primary, danger, warning
					"desc": "/rest/getMainData.do",
					"progress":{ 
						"rate":"100",
						"desc":"/rest/getMainData.do"
					},
					"info": {
						"url": "/rest/getMainData.do",
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
					"name": "선택목록가져오기",
					"label": "warning", 	//success, primary, danger, warning
					"desc": "/rest/getChooseList.do",
					"progress":{ 
						"rate":"100",
						"desc":""
					},
					"info": {
						"url": "/rest/getChooseList.do",
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
					"name": "나의데이터가져오기",
					"label": "warning", 	//success, primary, danger, warning
					"desc": "/rest/getMyData.do",
					"progress":{ 
						"rate":"100",
						"desc":""
					},
					"info": {
						"url": "/rest/getMyData.do",
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
					"name": "내결과가져오기",
					"label": "warning", 	//success, primary, danger, warning
					"desc": "/rest/getMyResult.do",
					"progress":{ 
						"rate":"100",
						"desc":""
					},
					"info": {
						"url": "/rest/getMyResult.do",
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
					"name": "암호화테스트",
					"label": "warning", 	//success, primary, danger, warning
					"desc": "/rest/encryptTest.do",
					"progress":{ 
						"rate":"100",
						"desc":""
					},
					"info": {
						"url": "/rest/encryptTest.do",
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
				{//리턴맵테스트
					"name": "returnMap.do",
					"label": "warning", 	//success, primary, danger, warning
					"desc": "/rest/returnMap.do",
					"progress":{ 
						"rate":"100",
						"desc":""
					},
					"info": {
						"url": "/rest/returnMap.do",
						"reqParam": [
							{
								"name":"var1", "type":"string", "req":"yes", "value":"hello!!",
								"desc":"var1"
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