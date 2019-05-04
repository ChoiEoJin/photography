var api = 
{	/*** for chat *****/
	"cls": [
		{
			"name": "TEST",
			"desc": "sadsadsad",
			"method": [
				{
					"name": "getTestData",
					"label": "warning", 	//success, primary, danger, warning
					"desc": "test",
					"progress":{ 
						"rate":"100",
						"desc":""
					},
					"info": {
						"url": "/sample/test.do",
						"reqParam": [
							{
								"name":"id", "type":"string", "req":"yes", "value":"admin",
								"desc":"아이디"
							},
							{
								"name":"password", "type":"string", "req":"yes", "value":"admin1234",
								"desc":"비밀번호"
							},
						],
						"respParam": [
						]
					},
					"sample": {
						"desc": "ddsdadads",
						"resp": '{"retCode": 0,"errMsg": "","retBody": ""}'
					}
				},
			]
		},
		{
			"name": "UserAPI",
			"desc": "userAPI",
			"method": [
				{//userInfo(S)
					"name": "getUserInfo",
					"label": "warning", 	//success, primary, danger, warning
					"desc": "test",
					"progress":{ 
						"rate":"100",
						"desc":""
					},
					"info": {
						"url": "/sample/test.do",
						"reqParam": [
//							{
//								"name":"id", "type":"string", "req":"yes", "value":"admin",
//								"desc":"아이디"
//							},
//							{
//								"name":"password", "type":"string", "req":"yes", "value":"admin1234",
//								"desc":"비밀번호"
//							},
						],
						"respParam": [
						]
					},
					"sample": {
						"desc": "",
						"resp": '{"retCode": 0,"errMsg": "","retBody": ""}'
					}
				},//userInfo(E)
				{//userList(S)
					"name": "getUserList",
					"label": "success", 	//success, primary, danger, warning
					"desc": "test",
					"progress":{ 
						"rate":"100",
						"desc":""
					},
					"info": {
						"url": "/sample/test.do",
						"reqParam": [
//							{
//								"name":"id", "type":"string", "req":"yes", "value":"admin",
//								"desc":"아이디"
//							},
//							{
//								"name":"password", "type":"string", "req":"yes", "value":"admin1234",
//								"desc":"비밀번호"
//							},
						],
						"respParam": [
						]
					},
					"sample": {
						"desc": "",
						"resp": '{"retCode": 0,"errMsg": "","retBody": ""}'
					}
				},//userList(E)
			]
		},//UserAPI(E)
		{//RegistAPI(S)
			"name": "RegistAPI",
			"desc": "registAPI",
			"method": [
				{//userInfo(S)
					"name": "regist",
					"label": "success", 	//success, primary, danger, warning
					"desc": "test",
					"progress":{ 
						"rate":"100",
						"desc":""
					},
					"info": {
						"url": "/sample/test.do",
						"reqParam": [
//							{
//								"name":"id", "type":"string", "req":"yes", "value":"admin",
//								"desc":"아이디"
//							},
//							{
//								"name":"password", "type":"string", "req":"yes", "value":"admin1234",
//								"desc":"비밀번호"
//							},
						],
						"respParam": [
						]
					},
					"sample": {
						"desc": "",
						"resp": '{"retCode": 0,"errMsg": "","retBody": ""}'
					}
				},//userInfo(E)
			]
		},//RegistAPI(E)
	
	]
};