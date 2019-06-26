package com.collabo.photography.test;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.collabo.photography.common.util.CommonUtils;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date now = new Date();
		System.out.println("현재시각:" + sdf.format(now));
		
		
		String badge = CommonUtils.makingBadge(27, "M");
		System.out.println("badge : "+badge);
	}

}
