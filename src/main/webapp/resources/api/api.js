var api = 
{	/*** for chat *****/
	"cls": [

		{
			"name": "TEST",
			"desc": "test",
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