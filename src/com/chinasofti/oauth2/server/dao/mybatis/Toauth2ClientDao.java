
package com.chinasofti.oauth2.server.dao.mybatis;

import java.util.Map;

import com.chinasofti.oauth2.server.domain.Toauth2Client;

import core.mybatis.MyBatisRepository;

/**
 * 通过@MapperScannerConfigurer扫描目录中的所有接口, 动态在Spring Context中生成实现.
 * 方法名称必须与Mapper.xml中保持一致.
 * 
 */
@MyBatisRepository
public interface Toauth2ClientDao {
	public Toauth2Client findOauthByUserId(String userId);
	public Toauth2Client findOauthByAccessToken(String accessToken);
	public Toauth2Client findOauthByAuthCode(String authCode);
	public void save(Toauth2Client toauth2Client);
	/**
	 * 通过Id删除一条记录
	 * @param id
	 */
	void delete(String accessToken);
	/**
	 * 修改记录
	 * @param user
	 */
	void update(Map<String,Object> params);
	
}
