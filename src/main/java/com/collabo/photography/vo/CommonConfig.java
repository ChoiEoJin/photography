package com.collabo.photography.vo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class CommonConfig {
	
	private static Map<String, Object> propMap; 
	
	private CommonConfig() {
		try {
			final String path = "/config.properties";
			propMap = new HashMap<String, Object>();
			InputStream is = this.getClass().getResourceAsStream(path);
			
			Properties prop = new Properties();
			prop.load(is);
			Enumeration<Object> enumKey = prop.keys();
			
			while(enumKey.hasMoreElements()) {
				String key = (String) enumKey.nextElement();
				final String value = new String( prop.getProperty(key).getBytes("ISO-8859-1"), "UTF-8");
				propMap.put(key, value);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String getProperty(String name) {
		return (String) propMap.get(name);
	}

}
