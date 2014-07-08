package com.chinasofti.login.util;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.chinasofti.login.domain.TestUser;

/**
 * 登录工具类
 */
public class LoginUtil {
	private static Logger logger=Logger.getLogger(LoginUtil.class);
	// 通过把session放到ThreadLocal中，实现人员信息的static方式获取
	public static ThreadLocal<HttpSession> sessions = new ThreadLocal<HttpSession>();
	public static String USER_INFO_KEY = "USER_INFO_KEY";//session属性：存入session的用户信息
	
	
	
	/**
	 * 设置session到ThreadLocal中
	 * @param session
	 */
	public static void setSession(HttpSession session){
		sessions.set(session);
	}
	
	/**
	 * 初始用户信息、用户权限到当前用户session中
	 * @param session
	 * @param tregUser
	 * @param resMap
	 */
	public static void initUserInfo(HttpSession session,TestUser tregUser){
		session.setAttribute(LoginUtil.USER_INFO_KEY, tregUser);//把用户信息放到session中
	}

	/**
	 * 清空ThreadLocal中的session
	 */
	public static void clearSession(){
		sessions.set(null);
	}
	
	/**
	 * 获取当前登陆用户信息
	 * @return
	 */
	public static TestUser getUser(){
		HttpSession session = sessions.get();
		if(session == null) return null;
		return (TestUser) session.getAttribute(USER_INFO_KEY);
	}
}
