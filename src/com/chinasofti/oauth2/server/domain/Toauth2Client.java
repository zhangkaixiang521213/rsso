package com.chinasofti.oauth2.server.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import core.domain.DomainBase;

/**
 * <p>Toauth2Client: Zhang Kaitao
 * <p>Date: 14-2-17
 * <p>Version: 1.0
 */
public class Toauth2Client extends DomainBase {
    private String userId; //用户id
    private String clientId; //客户端ID
    private String clientSecret; //客户端安全KEY
    private String accessToken; //口令
    private Timestamp authTime; //授权时间
    private String authCode; //授权码
    
    
    
	/**
	 * 
	 */
	public Toauth2Client() {
		super();
	}


	/**
	 * @param userId
	 * @param authCode
	 */
	public Toauth2Client(String userId, String authCode) {
		super();
		this.userId = userId;
		this.authCode = authCode;
	}
	
	
	public String getClientSecret() {
		return clientSecret;
	}


	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}


	public String getAuthCode() {
		return authCode;
	}
	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public Timestamp getAuthTime() {
		return authTime;
	}
	public void setAuthTime(Timestamp authTime) {
		this.authTime = authTime;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((accessToken == null) ? 0 : accessToken.hashCode());
		result = prime * result
				+ ((authTime == null) ? 0 : authTime.hashCode());
		result = prime * result
				+ ((clientId == null) ? 0 : clientId.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Toauth2Client other = (Toauth2Client) obj;
		if (accessToken == null) {
			if (other.accessToken != null)
				return false;
		} else if (!accessToken.equals(other.accessToken))
			return false;
		if (authTime == null) {
			if (other.authTime != null)
				return false;
		} else if (!authTime.equals(other.authTime))
			return false;
		if (clientId == null) {
			if (other.clientId != null)
				return false;
		} else if (!clientId.equals(other.clientId))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Toauth2Client [userId=" + userId + ", clientId=" + clientId
				+ ", accessToken=" + accessToken + ", authTime=" + authTime
				+ "]";
	}

    
}
