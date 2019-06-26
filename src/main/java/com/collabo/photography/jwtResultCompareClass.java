package com.collabo.photography;

public class jwtResultCompareClass {

	public static void main(String[] args) {
		
		
		//jwtMain2에서 출력된 결과를 붙여넣어주세요.
		String HEADER="eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFMyNTYifQ";
		String PAYLOAD="eyJzdWIiOiJ0ZXN0U3ViamVjdCIsImp0aSI6InltczM2ODQiLCJpc3MiOiJzZWxwaWNfYWRtaW4iLCJpYXQiOjE1NTQ2MzEzOTIsImV4cCI6MTU1NDY2NzM5MiwibmFtZSI6ImNob2kiLCJhZGRyZXNzIjoiUGFqdVNpIFNvbmdIYWsgMSBHaWwgMTU4LTQ4IiwiZ2VuZGVyIjoibWFsZSIsImNvdW50cnkiOiJSZXB1YmxpYyBvZiBLb3JlYSIsImpvYiI6IklUIGRldmVsb3BlciIsImFnZSI6IjI4IiwiZ3JvdXAiOiJUaGUgQ3JldyBEQ0UifQ";
		String SIGNATURE="XErhEGri7qSvY_YZ9GE3OKNTaowQble5dRh20LS9Ccw";
		
		
		//jwt.io 홈페이지에서 비밀키 입력후 Vaild된 값을 넣어주세요.
		String jwtioHEADER="eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFMyNTYifQ";
		String jwtioPAYLOAD="eyJzdWIiOiJ0ZXN0U3ViamVjdCIsImp0aSI6InltczM2ODQiLCJpc3MiOiJzZWxwaWNfYWRtaW4iLCJpYXQiOjE1NTQ2MzEzOTIsImV4cCI6MTU1NDY2NzM5MiwibmFtZSI6ImNob2kiLCJhZGRyZXNzIjoiUGFqdVNpIFNvbmdIYWsgMSBHaWwgMTU4LTQ4IiwiZ2VuZGVyIjoibWFsZSIsImNvdW50cnkiOiJSZXB1YmxpYyBvZiBLb3JlYSIsImpvYiI6IklUIGRldmVsb3BlciIsImFnZSI6IjI4IiwiZ3JvdXAiOiJUaGUgQ3JldyBEQ0UifQ";
		String jwtioSIGNATURE="XErhEGri7qSvY_YZ9GE3OKNTaowQble5dRh20LS9Ccw";
		
		System.out.println("HEADER 비교결과   : "+HEADER.equals(jwtioHEADER));
		System.out.println("PAYLOAD 비교결과   : "+PAYLOAD.equals(jwtioPAYLOAD));
		System.out.println("SIGNATURE 비교결과   : "+SIGNATURE.equals(jwtioSIGNATURE));
		

	}

}
