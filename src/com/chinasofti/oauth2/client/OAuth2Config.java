/**
 * 
 */
package com.chinasofti.oauth2.client;


/**
 * @author zhangkaixiang
 *
 */
public class OAuth2Config {
	
	private String clientId;
	private String clientSecret;
	private String accessTokenUrl;
	private String userInfoUrl;
	private String redirectUrl;
	private String loginUrl;
	
	public OAuth2Config() {
		super();
	}
	
	/*
	 * @params clentId 客户ID,clientSecret 客户密码,accessTokenUrl accessToken地址,userInfoUrl 用户信息地址,redirectUrl 跳转到客户端地址,loginUrl 登陆地址
	 */
	public OAuth2Config(String clientId,String clientSecret,String accessTokenUrl,String userInfoUrl,String redirectUrl,String loginUrl) {
		this.clientId=clientId;
		this.clientSecret=clientSecret;
		this.accessTokenUrl=accessTokenUrl;
		this.userInfoUrl=userInfoUrl;
		this.redirectUrl=redirectUrl;
		this.loginUrl=loginUrl;
	}


	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public String getAccessTokenUrl() {
		return accessTokenUrl;
	}

	public void setAccessTokenUrl(String accessTokenUrl) {
		this.accessTokenUrl = accessTokenUrl;
	}

	public String getUserInfoUrl() {
		return userInfoUrl;
	}

	public void setUserInfoUrl(String userInfoUrl) {
		this.userInfoUrl = userInfoUrl;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	public String getLoginUrl() {
		return loginUrl;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	
}
