package com.chinasofti.oauth2.server.controller;

import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.oltu.oauth2.as.issuer.MD5Generator;
import org.apache.oltu.oauth2.as.issuer.OAuthIssuerImpl;
import org.apache.oltu.oauth2.as.request.OAuthAuthzRequest;
import org.apache.oltu.oauth2.as.response.OAuthASResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.oltu.oauth2.common.message.OAuthResponse;
import org.apache.oltu.oauth2.common.message.types.ResponseType;
import org.apache.oltu.oauth2.common.utils.OAuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chinasofti.login.domain.LoginInfoBean;
import com.chinasofti.login.domain.TestUser;
import com.chinasofti.login.service.LoginService;
import com.chinasofti.login.util.Constants;
import com.chinasofti.oauth2.server.domain.Toauth2Client;
import com.chinasofti.oauth2.server.service.Toauth2ClientService;

/**
 * <p>Toauth2Client: Zhang Kaitao
 * <p>Date: 14-2-16
 * <p>Version: 1.0
 */
@Controller
public class AuthorizeController {

    //@Autowired
    //private OAuthService oAuthService;
	@Autowired
	private Toauth2ClientService toauth2ClientService;
    
    @Autowired
	private LoginService loginService;
    //@Autowired
    //private ClientService clientService;

    @RequestMapping("/authorize")
    public Object authorize(
            Model model,
            HttpServletRequest request,HttpSession session)
            throws URISyntaxException,OAuthSystemException {
    	
        try {
            //构建OAuth 授权请求
            OAuthAuthzRequest oauthRequest = new OAuthAuthzRequest(request);

            //检查传入的客户端id是否正确
            /*
            if (!oAuthService.checkClientId(oauthRequest.getClientId())) {
                OAuthResponse response =
                        OAuthASResponse.errorResponse(HttpServletResponse.SC_BAD_REQUEST)
                                .setError(OAuthError.TokenResponse.INVALID_CLIENT)
                                .setErrorDescription(Constants.INVALID_CLIENT_DESCRIPTION)
                                .buildJSONMessage();
                return new ResponseEntity(response.getBody(), HttpStatus.valueOf(response.getResponseStatus()));
            }
            */

            //TestUser user = LoginUtil.getUser();
            //如果用户没有登录，跳转到登陆页面
            String loginName = request.getParameter("loginName");//获取用户登录账号
            String loginPwd = request.getParameter("loginPwd");//获取用户登录密码
            
            Toauth2Client client = null;
            if(StringUtils.isEmpty(loginName) && StringUtils.isEmpty(loginPwd)){
            	client=toauth2ClientService.findOauthByUserId(loginName);
            }
            if(client == null && !login(loginName,loginPwd)){
                return "logins/oauth2login";
            }
            

            //String username = user.getLoginName();
            //生成授权码
            String authorizationCode = null;
            //responseType目前仅支持CODE，另外还有TOKEN
            String responseType = oauthRequest.getParam(OAuth.OAUTH_RESPONSE_TYPE);
            if (responseType.equals(ResponseType.CODE.toString())) {
            	Toauth2Client toauth2Client = toauth2ClientService.findOauthByUserId(loginName);
            	if(toauth2Client!=null){
            		toauth2ClientService.delete(toauth2Client.getAccessToken());
            		//OAuthIssuerImpl oauthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());
            		//authorizationCode = oauthIssuerImpl.authorizationCode();
            		//authorizationCode = toauth2Client.getAccessToken();
            	}
        		OAuthIssuerImpl oauthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());
        		authorizationCode = oauthIssuerImpl.authorizationCode();
        	
            //oAuthService.addAuthCode(authorizationCode, loginName);
            
                String clientId = oauthRequest.getParam(OAuth.OAUTH_CLIENT_ID);
                String clientSecret = oauthRequest.getParam(OAuth.OAUTH_CLIENT_SECRET);
                toauth2Client = new Toauth2Client(loginName,authorizationCode);
                toauth2Client.setClientId(clientId);;
                toauth2Client.setClientSecret(clientSecret);
                toauth2ClientService.save(toauth2Client);
        	
            }

            //进行OAuth响应构建
            OAuthASResponse.OAuthAuthorizationResponseBuilder builder =
                    OAuthASResponse.authorizationResponse(request, HttpServletResponse.SC_FOUND);
            //设置授权码
            builder.setCode(authorizationCode);
            //得到到客户端重定向地址
            String redirectURI = oauthRequest.getParam(OAuth.OAUTH_REDIRECT_URI);

            //构建响应
            final OAuthResponse response = builder.location(redirectURI).buildQueryMessage();

            //根据OAuthResponse返回ResponseEntity响应
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(new URI(response.getLocationUri()));
            return new ResponseEntity(headers, HttpStatus.valueOf(response.getResponseStatus()));
        } catch (OAuthProblemException e) {

            //出错处理
            String redirectUri = e.getRedirectUri();
            if (OAuthUtils.isEmpty(redirectUri)) {
                //告诉客户端没有传入redirectUri直接报错
                return new ResponseEntity("OAuth callback url needs to be provided by client!!!", HttpStatus.NOT_FOUND);
            }

            //返回错误消息（如?error=）
            final OAuthResponse response =
                    OAuthASResponse.errorResponse(HttpServletResponse.SC_FOUND)
                            .error(e).location(redirectUri).buildQueryMessage();
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(new URI(response.getLocationUri()));
            return new ResponseEntity(headers, HttpStatus.valueOf(response.getResponseStatus()));
        }
    }
    /**
     * 登陆
     * @param request
     * @param session
     * @return
     */
    private boolean login(String loginName,String loginPwd) {
		LoginInfoBean info = new LoginInfoBean();
		
		boolean valid=false;
		TestUser tregUser=loginService.findUserbyPwd(loginName, loginPwd);
		if(tregUser==null){
			info.setCode(Constants.LOGIN_VALUE_TYPE.LOGINNAMEINEXISTENCE.value());
			info.setMsg(Constants.LOGIN_VALUE_TYPE.LOGINNAMEINEXISTENCE.desc());
		}else{
			info.setCode(Constants.LOGIN_VALUE_TYPE.LOGINSUCCESS.value());
			info.setMsg(Constants.LOGIN_VALUE_TYPE.LOGINSUCCESS.desc());
			
			//初始用户信息放到session中
			//LoginUtil.initUserInfo(session,tregUser);
			valid = true;
		}
		return valid;
    }
    
}