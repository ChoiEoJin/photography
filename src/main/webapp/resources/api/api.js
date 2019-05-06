var api = 
{	/*** for chat *****/
	"cls": [

		{
			"name": "API Doc",
			"desc": "photography api doc",
			"method": [
				{
					"name": "회원가입",
					"label": "warning", 	//success, primary, danger, warning
					"desc": "회원가입",
					"progress":{ 
						"rate":"100",
						"desc":""
					},
					"info": {
						"url": "/rest/userRegist.do",
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
					"name": "로그인",
					"label": "warning", 	//success, primary, danger, warning
					"desc": "로그인",
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
					"desc": "로그아웃",
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
					"desc": "메인화면데이터요청",
					"progress":{ 
						"rate":"100",
						"desc":""
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
					"desc": "",
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
					"name": "내결과가져오기",
					"label": "warning", 	//success, primary, danger, warning
					"desc": "",
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
					"desc": "",
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
					"desc": "returnMap",
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