
package com.chinasofti.login.service;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chinasofti.login.dao.mybatis.TestUserDao;
import com.chinasofti.login.domain.TestUser;


@Service
@Transactional
public class LoginService {

	private static Logger logger = LoggerFactory.getLogger(LoginService.class);

	@Autowired
	private TestUserDao testUserDao;

	public TestUser findUserbyPwd(String loginName,String loginPwd) {
		HashMap<String, String > map=new HashMap<String, String>();
		map.put("loginName", loginName);
		map.put("loginPwd", loginPwd);
		return testUserDao.findUserbyPwd(map);
	}

}
