package com.chinasofti.oauth2.client;


/**
 * <p>model</p>
 * 
 * @author BizFoundation
 * @version 5.0
 * @since 1.0
 */
public class UPerson{
	// Fields    
	private String personUuid; 
	private String tenantId; 
	private String personName; 
	private String personCode; 
	private String deptUuid;
	private String deptName;
	private String account; 
	private String password; 
	private String email; 
	private String phone; 
	private String createTime; 
	private String memo; 
	private String showOrder; 
	private String personFlag; 
	private String markDelete; 
	
	// QueryOnlyFields
	private String positionUUID;	//岗位ID
	private String positionName;	//岗位名称
	private String isMaster;		//是否主部门
	private String appAuthId;		//应用授权UUID
	//default constructor
    public UPerson() {
    	super();
    }
    public UPerson(String personUuid) {
    	this.personUuid=personUuid;
    }
	public String getPersonUuid() {
		return personUuid;
	}
	public void setPersonUuid(String personUuid) {
		this.personUuid = personUuid;
	}
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getPersonCode() {
		return personCode;
	}
	public void setPersonCode(String personCode) {
		this.personCode = personCode;
	}
	public String getDeptUuid() {
		return deptUuid;
	}
	public void setDeptUuid(String deptUuid) {
		this.deptUuid = deptUuid;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getShowOrder() {
		return showOrder;
	}
	public void setShowOrder(String showOrder) {
		this.showOrder = showOrder;
	}
	public String getPersonFlag() {
		return personFlag;
	}
	public void setPersonFlag(String personFlag) {
		this.personFlag = personFlag;
	}
	public String getMarkDelete() {
		return markDelete;
	}
	public void setMarkDelete(String markDelete) {
		this.markDelete = markDelete;
	}
	public String getPositionUUID() {
		return positionUUID;
	}
	public void setPositionUUID(String positionUUID) {
		this.positionUUID = positionUUID;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public String getIsMaster() {
		return isMaster;
	}
	public void setIsMaster(String isMaster) {
		this.isMaster = isMaster;
	}
	public String getAppAuthId() {
		return appAuthId;
	}
	public void setAppAuthId(String appAuthId) {
		this.appAuthId = appAuthId;
	}
}