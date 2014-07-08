package com.chinasofti.oauth2.client;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * Servlet Filter implementation class OAuth2Filter
 */
@WebFilter("/OAuth2Filter")
public class OAuth2Filter implements Filter {
	private static Logger logger = LoggerFactory.getLogger(OAuth2Filter.class);
	
	private static OAuth2Config oauth2Config;

    /**
     * Default constructor. 
     */
    public OAuth2Filter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		OAuth2Client client=new OAuth2Client(oauth2Config,(HttpServletRequest)request,(HttpServletResponse)response);
		
		HttpServletRequest re = (HttpServletRequest) request;
		String uri = re.getRequestURI();//请求URI
		
		uri = uri.substring(re.getContextPath().length());//把请求URI去掉工程根路径
		logger.info(uri);
		//logger.info((String)res.getSession().getAttribute("_sso_user_id"));
		try{
			/*
			HttpServletRequest requests = (HttpServletRequest) request;
			HttpServletResponse responses = (HttpServletResponse) response;
			String uri = requests.getRequestURI();//请求URI
			
			uri = uri.substring(requests.getContextPath().length());//把请求URI去掉工程根路径
			logger.info(uri);
			*/
			if(client.isLogin()){
				chain.doFilter(client.request(), response);
				return;
			}
			/*
			else if(client.isExcluded("")){
				logger.debug("OAuth2Filter isExcluded");
				chain.doFilter(request, response);
				return;
			}else if(client.isCallback()){
				logger.debug("OAuth2Filter isCallback");
				chain.doFilter(request, response);
				return;
			}
			*/
			String code=client.getAuthrizationCode();
			logger.debug("OAuth2Filter code:"+code);
			if(StringUtils.isEmpty(code)){
				client.toGetAuthrizationCode();
				return;
			}
			chain.doFilter(client.request(), response);
		}catch(Exception e){
			e.printStackTrace();
			throw new ServletException(e.getMessage());
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		try {
			//clentId 客户ID,clientSecret 客户密码,accessTokenUrl accessToken地址,userInfoUrl 用户信息地址,redirectUrl 跳转到客户端地址,loginUrl 登陆地址
			oauth2Config=new OAuth2Config(PropertiesUtil.getPropByKey("client_id"),
											PropertiesUtil.getPropByKey("client_secret"),
											PropertiesUtil.getPropByKey("access_token_url"),
											PropertiesUtil.getPropByKey("user_info_url"),
											PropertiesUtil.getPropByKey("redirect_url"),
											PropertiesUtil.getPropByKey("login_url"));
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}

}
