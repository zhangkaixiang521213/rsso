package com.chinasofti.login.util;


public class Constants {
	
	public static String lockstatus = "3"; //超过六次输入密码错误，账户锁定
	/**
	 * 登陆信息字典
	 * @author zhangkaixiang
	 *
	 */
	public enum LOGIN_VALUE_TYPE{
		LOGINSUCCESS("1","登录成功，欢迎您!"),
		LOGINNAMEEMPTY("2","登录名不能为空！"),
		LOGINPASSWORKEMPTY("3","密码不能为空！"),
		LOGINNAMEINEXISTENCE("4","登陆错误！"),
		LOGINPASSWORKERROR("5","密码错误！"),
		LOGINPASSWORKRESET("6","密码连续错误超过6次,请重置密码"),
		LOGINRANDERROR("7","验证码不正确"),
		LOGINRANDACTIVATE("8","用户未激活，请激活后重新登录"),
		LOGINRANDOUTAGE("9","用户已停用"),
		LOGINRANLOCK("10","用户已锁定");
		
		private String value;
		private String desc;
		LOGIN_VALUE_TYPE(String value,String desc){
			this.value=value;
			this.desc=desc;
		}
		public String value(){
			return this.value;
		}
		public String desc(){
			return this.desc;
		}
	}
	
	/**
	 * 读取字典用例
	 * @param args
	 */
	public static void main(String[] args){
		System.out.println(Constants.LOGIN_VALUE_TYPE.LOGINNAMEEMPTY.value());
		System.out.println(Constants.LOGIN_VALUE_TYPE.LOGINNAMEEMPTY.desc());
	}

}
