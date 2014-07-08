
package com.chinasofti.login.controller;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinasofti.login.domain.LoginInfoBean;
import com.chinasofti.login.domain.TestUser;
import com.chinasofti.login.service.LoginService;
import com.chinasofti.login.util.Constants;
import com.chinasofti.login.util.LoginUtil;

import core.spring.RequestMappingName;
import core.utils.ComUtil;


@Controller
public class LoginController {
	private static Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private LoginService loginService;
	
	/**
	 * 跳转到登陆页面
	 * @return
	 */
	@RequestMappingName(value = "跳转到登陆页面")
	@RequestMapping(value = "toLogin", method = RequestMethod.GET)
	public String toLogin() {
		return "logins/login";
	}
	
	/**
	 * 跳转到单点登陆页面
	 * @return
	 */
	@RequestMappingName(value = "跳转到单点登陆页面")
	@RequestMapping(value = "toOauth2login", method = RequestMethod.GET)
	public String toOauth2login() {
		return "logins/oauth2login";
	}
	
	/**
	 * 用户登录
	 * @return
	 */
	@RequestMappingName(value = "用户登录")
	@RequestMapping(value = "login", method = RequestMethod.GET)
	@ResponseBody
	public LoginInfoBean login(Model model,ServletRequest request,ServletResponse response,HttpSession session) {
		
		String loginName = request.getParameter("loginName");//获取用户登录账号
		String loginPwd = request.getParameter("loginPwd");//获取用户登录密码
		LoginInfoBean info = new LoginInfoBean();
		
		if(loginName == null || "".equals(loginName)){//判断如果登录账号为空 
			info.setCode(Constants.LOGIN_VALUE_TYPE.LOGINNAMEEMPTY.value());
			info.setMsg(Constants.LOGIN_VALUE_TYPE.LOGINNAMEEMPTY.desc());
		}else if(loginPwd == null || "".equals(loginPwd)){//判断如果登录密码为空
			info.setCode(Constants.LOGIN_VALUE_TYPE.LOGINPASSWORKEMPTY.value());
			info.setMsg(Constants.LOGIN_VALUE_TYPE.LOGINPASSWORKEMPTY.desc());
		}else{
			TestUser tregUser=loginService.findUserbyPwd(loginName, loginPwd);
			if(tregUser==null){
				info.setCode(Constants.LOGIN_VALUE_TYPE.LOGINNAMEINEXISTENCE.value());
				info.setMsg(Constants.LOGIN_VALUE_TYPE.LOGINNAMEINEXISTENCE.desc());
			}else{
				info.setCode(Constants.LOGIN_VALUE_TYPE.LOGINSUCCESS.value());
				info.setMsg(Constants.LOGIN_VALUE_TYPE.LOGINSUCCESS.desc());
				info.setSkipPath("/index");
				
				//初始用户信息放到session中
				LoginUtil.initUserInfo(session,tregUser);
			}

		}
		return info;
	}
	
	/**
	 * <p>用户注销</p>
	 */
	@RequestMappingName(value = "用户注销")
	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logout(HttpSession session){
		LoginUtil.clearSession();//清空ThreadLocal的session
		session.invalidate();//注销session
		return "redirect:/toLogin";
	}
	
	
}
