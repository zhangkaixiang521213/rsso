package com.chinasofti.oauth2.client;

import java.util.ResourceBundle;

public class PropertiesUtil {
	//oauth2.0属性配置
	private static ResourceBundle resource = ResourceBundle.getBundle("OAuth2-config");
	
	private static String contextPath = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

	/**
	 * 获取OAuth2-config.properties文件属性值
	 * 
	 * @param key
	 * @return
	 */
	public static String getPropByKey(String key) {
		if (key != null && !"".equals(key)) {
			return resource.getString(key);
		} else {
			return "";
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
