package com.chinasofti.login.domain;

import java.sql.Timestamp;

public class TregUser {
	
	private static final long serialVersionUID = -705560416857544212L;
	
   private String unid;
   private String loginName;
   private String loginPwd;
   private String userName;
   private String cardNumber;
   private String telphone;
   private Timestamp createTime;
   private String type;
   private String tenantId;
   private String status;
	   
	   
   public TregUser() {
		super();
	}
	
	/**
	 * @param loginName
	 * @param loginPwd
	 */
	public TregUser(String loginName, String loginPwd) {
		super();
		this.loginName = loginName;
		this.loginPwd = loginPwd;
	}

	
	public String getUnid() {
		return unid;
	}
	public void setUnid(String unid) {
		this.unid = unid;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getLoginPwd() {
		return loginPwd;
	}
	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	 
	   
}
