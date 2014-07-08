
package com.chinasofti.oauth2.server.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chinasofti.oauth2.server.dao.mybatis.Toauth2ClientDao;
import com.chinasofti.oauth2.server.domain.Toauth2Client;

@Service
@Transactional
public class Toauth2ClientService {

	private static Logger logger = LoggerFactory.getLogger(Toauth2ClientService.class);

	@Autowired
	private Toauth2ClientDao toauth2ClientDao;

	public Toauth2Client findOauthByUserId(String userId){
		return toauth2ClientDao.findOauthByUserId(userId);
	}
	public Toauth2Client findOauthByAccessToken(String accessToken){
		return toauth2ClientDao.findOauthByAccessToken(accessToken);
	}
	public Toauth2Client findOauthByAuthCode(String authCode){
		return toauth2ClientDao.findOauthByAuthCode(authCode);
	}
	public void save(Toauth2Client toauth2Client){
		toauth2ClientDao.save(toauth2Client);
	}
	/**
	 * 通过Id删除一条记录
	 * @param id
	 */
	public void delete(String accessToken){
		toauth2ClientDao.delete(accessToken);
	}
	/**
	 * 修改记录
	 * @param user
	 */
	public void update(String accessToken,String authCode){
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("accessToken", accessToken);
		params.put("authCode", authCode);
		toauth2ClientDao.update(params);
	}

}
