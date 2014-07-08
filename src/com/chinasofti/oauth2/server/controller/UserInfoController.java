package com.chinasofti.oauth2.server.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.error.OAuthError;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.common.message.types.ParameterStyle;
import org.apache.oltu.oauth2.common.utils.OAuthUtils;
import org.apache.oltu.oauth2.rs.request.OAuthAccessResourceRequest;
import org.apache.oltu.oauth2.rs.response.OAuthRSResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chinasofti.oauth2.server.domain.Toauth2Client;
import com.chinasofti.oauth2.server.service.Toauth2ClientService;
import com.chinasofti.oauth2.server.util.Constants;

/**
 * <p>Toauth2Client: Zhang Kaitao
 * <p>Date: 14-2-16
 * <p>Version: 1.0
 */
@Controller
public class UserInfoController {
	private static Logger logger = LoggerFactory.getLogger(UserInfoController.class);
    //@Autowired
    //private OAuthService oAuthService;
    
    @Autowired
	private Toauth2ClientService toauth2ClientService;

    @RequestMapping("/userInfo")
    @ResponseBody
    public HttpEntity userInfo(HttpServletRequest request) throws OAuthSystemException {
        try {

            //构建OAuth资源请求
            OAuthAccessResourceRequest oauthRequest = new OAuthAccessResourceRequest(request, ParameterStyle.QUERY);
            //获取Access Token
            String accessToken = oauthRequest.getAccessToken();

            Toauth2Client toauth2Client = toauth2ClientService.findOauthByAccessToken(accessToken);
            //验证Access Token
            if (toauth2Client==null) {
                // 如果不存在/过期了，返回未验证错误，需重新验证
                OAuthResponse oauthResponse = OAuthRSResponse
                        .errorResponse(HttpServletResponse.SC_UNAUTHORIZED)
                        .setRealm(Constants.RESOURCE_SERVER_NAME)
                        .setError(OAuthError.ResourceResponse.INVALID_TOKEN)
                        .buildHeaderMessage();

                HttpHeaders headers = new HttpHeaders();
                headers.add(OAuth.HeaderType.WWW_AUTHENTICATE, oauthResponse.getHeader(OAuth.HeaderType.WWW_AUTHENTICATE));
                return new ResponseEntity(headers, HttpStatus.UNAUTHORIZED);
            }
            //返回用户名
            //String username = oAuthService.getUsernameByAccessToken(accessToken);
            String username = toauth2Client.getUserId();
            return new ResponseEntity(username, HttpStatus.OK);
        } catch (OAuthProblemException e) {
            //检查是否设置了错误码
            String errorCode = e.getError();
            if (OAuthUtils.isEmpty(errorCode)) {
                OAuthResponse oauthResponse = OAuthRSResponse
                        .errorResponse(HttpServletResponse.SC_UNAUTHORIZED)
                        .setRealm(Constants.RESOURCE_SERVER_NAME)
                        .buildHeaderMessage();

                HttpHeaders headers = new HttpHeaders();
                headers.add(OAuth.HeaderType.WWW_AUTHENTICATE, oauthResponse.getHeader(OAuth.HeaderType.WWW_AUTHENTICATE));
                return new ResponseEntity(headers, HttpStatus.UNAUTHORIZED);
            }

            OAuthResponse oauthResponse = OAuthRSResponse
                    .errorResponse(HttpServletResponse.SC_UNAUTHORIZED)
                    .setRealm(Constants.RESOURCE_SERVER_NAME)
                    .setError(e.getError())
                    .setErrorDescription(e.getDescription())
                    .setErrorUri(e.getUri())
                    .buildHeaderMessage();

            HttpHeaders headers = new HttpHeaders();
            headers.add(OAuth.HeaderType.WWW_AUTHENTICATE, oauthResponse.getHeader(OAuth.HeaderType.WWW_AUTHENTICATE));
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
    
    @RequestMapping("/ssoLoginOut")
    @ResponseBody
    public HttpEntity ssoLoginOut(HttpServletRequest request) throws OAuthSystemException {
        try {
        	String accessToken=request.getHeader("accessToken");
        	toauth2ClientService.delete(accessToken);
        	/*
        	TestUser tregUser=LoginUtil.getUser();
        	if(tregUser!=null){
        		logger.info(tregUser.getLoginName());
        	}else{
        		logger.info("tregUser is null");
        	}
        	LoginUtil.clearSession();
        	*/
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity("sso clear session success!", HttpStatus.OK);
    }
}
