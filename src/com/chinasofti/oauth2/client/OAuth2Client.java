/**
 * 
 */
package com.chinasofti.oauth2.client;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthBearerClientRequest;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthAccessTokenResponse;
import org.apache.oltu.oauth2.client.response.OAuthResourceResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.message.types.GrantType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;


/**
 * @author zhangkaixiang
 *
 */
public class OAuth2Client {

	private static Logger logger=LoggerFactory.getLogger(OAuth2Client.class);
	
	private HttpServletResponse response;
	private HttpServletRequest request;
	private OAuth2Config config;
	//oauth2 authc code参数名
    private String authcCodeParam = "code";
    static final String AUTH_CODE = "auth_code";
    static final String ACCESS_TOKEN = "access_token";
    static final String SSOUSERID = "_sso_user_id";
    static final String SSOUSER = "_sso_user";
    
    public OAuth2Client() {
		super();
	}

	public OAuth2Client(OAuth2Config config, HttpServletRequest request2,
			HttpServletResponse response2) {
		this.config=config;
		this.request=request2;
		this.response=response2;
	}

	/**
	 * 获取授权码
	 * @return
	 */
	public String getAuthrizationCode() {
	    String code = this.request.getParameter(authcCodeParam);
	    if(!StringUtils.isEmpty(code)){
	    	this.request.getSession().setAttribute(AUTH_CODE, code);
	    	UPerson person=new UPerson(extractUserId(code));
	    	request.getSession().setAttribute(SSOUSER,person);
	    	request.getSession().setAttribute(SSOUSERID,person.getPersonUuid());
	    }
	    
	    return code;
	}
	
	/**
	 * 获取用户信息
	 * @param code
	 * @return userId
	 */
	private String extractUserId(String code) {

        try {
            OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());

            OAuthClientRequest accessTokenRequest = OAuthClientRequest
                    .tokenLocation(config.getAccessTokenUrl())
                    .setGrantType(GrantType.AUTHORIZATION_CODE)
                    .setClientId(config.getClientId())
                    .setClientSecret(config.getClientSecret())
                    .setCode(code)
                    .setRedirectURI(config.getRedirectUrl())
                    .buildQueryMessage();

            String accessToken=(String)this.request.getSession().getAttribute(ACCESS_TOKEN);
            if(accessToken==null || "".equals(accessToken)){
	            OAuthAccessTokenResponse oAuthResponse = oAuthClient.accessToken(accessTokenRequest, OAuth.HttpMethod.POST);
	
	            accessToken = oAuthResponse.getAccessToken();
	            this.request.getSession().setAttribute(ACCESS_TOKEN, accessToken);
	            Long expiresIn = oAuthResponse.getExpiresIn();
            }

            OAuthClientRequest userInfoRequest = new OAuthBearerClientRequest(config.getUserInfoUrl())
                    .setAccessToken(accessToken).buildQueryMessage();

            OAuthResourceResponse resourceResponse = oAuthClient.resource(userInfoRequest, OAuth.HttpMethod.GET, OAuthResourceResponse.class);
            String userId = resourceResponse.getBody();
            return userId;
        } catch (Exception e) {
            e.printStackTrace();
            throw new OAuth2Exception(e);
        }
    }
	
	public void oauthServerLoginOut(){
		try {
		OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());

		//logger.info((String)this.request.getSession().getAttribute(SSOAUTHTOKEN));
       // OAuthClientRequest userInfoRequest = new OAuthBearerClientRequest("http://localhost:8086/platform/ssoLoginOut")
        //.setAccessToken((String)this.request.getSession().getAttribute(SSOAUTHTOKEN)).buildQueryMessage();
        OAuthClientRequest userInfoRequest = new OAuthBearerClientRequest("http://localhost:8086/platform/ssoLoginOut").buildQueryMessage();
        
        OAuthResourceResponse resourceResponse = oAuthClient.resource(userInfoRequest, OAuth.HttpMethod.GET, OAuthResourceResponse.class);
        String msg = resourceResponse.getBody();
        logger.info(msg);
        
		} catch (Exception e) {
            e.printStackTrace();
            throw new OAuth2Exception(e);
        }
	}


	/**
	 * 登陆并获取用户授权
	 * @throws IOException 
	 */
	public void toGetAuthrizationCode() throws IOException {
		response.sendRedirect(config.getLoginUrl());
	}

	/**
	 * 验证是否登陆
	 * @return
	 */
	public boolean isLogin() {
		String userid=this.getUserId();
		logger.debug("current user:"+userid);
		return userid!=null && !userid.equals("");
	}

	/**
	 * 获取当前用户ID
	 * @return
	 */
	public String getUserId() {
		logger.info((String)request.getSession().getAttribute(SSOUSERID));
		return (String)request.getSession().getAttribute(SSOUSERID);
	}

	/**
	 * 是否从OAuth2认证服务器返回的请求，一般情况为出错了
	 * @return
	 */
	public boolean isCallback() {
		String error=request.getParameter("error");
		if(error!=null){
			return true;
		}
		return false;
	}


	public HttpServletRequest request() {
		SystemUtil.setCurThreadAttribute(SSOUSER, request.getSession().getAttribute(SSOUSER));
		SystemUtil.setCurThreadAttribute(SSOUSERID, request.getSession().getAttribute(SSOUSERID));
		SystemUtil.setCurThreadAttribute(AUTH_CODE, request.getSession().getAttribute(AUTH_CODE));
		SystemUtil.setCurThreadAttribute(ACCESS_TOKEN, request.getSession().getAttribute(ACCESS_TOKEN));
		return new SSORequestWrapper(request);
	}
	
	class SSORequestWrapper extends HttpServletRequestWrapper {
	    public SSORequestWrapper(HttpServletRequest request) {
	        super(request);
	    }
	    
	    public String getRemoteUser() {
	        return getUserId();
	    }
	}
	


}
