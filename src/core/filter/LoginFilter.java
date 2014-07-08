package core.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasofti.login.domain.TestUser;
import com.chinasofti.login.util.LoginUtil;
/**
 * 用户登录过滤器
 * @author BizFoundation
 * @version 1.0
 * @since 1.0
 */
public class LoginFilter implements Filter{
	
	/**
	 * 不需过滤访问请求
	 */
	private static String loginURL1 = "/login";//用户登录请求路径
	private static String loginURL2 = "/toLogin";//跳转到登陆     
	private static String loginURL3 = "/authorize";//授权     
	private static String loginURL4 = "/accessToken";//认证token    
	private static String loginURL5 = "/userInfo";//用户信息   
	private static String loginURL6 = "/toOauth2login";//用户信息   
	private static String loginURL7 = "/ssoLoginOut";//用户信息   
	
	public Pattern excludePage = Pattern
			.compile("(.*\\.css|.*\\.js|.*\\.jpg|.*\\.gif|.*\\.png|.*\\.bmp|.*\\.ico|.*\\.jpeg|.*\\.gzjs|.*\\.gzcss|.*\\.htm?|.*\\.html?|.*\\.xml|.*\\.jspa|.*\\.json|/pages/login/.*/login\\.jsp)");//设置不过滤url请求的正则表达式
	public Pattern extExcludePage = Pattern.compile("/pages/hall/index\\.jsp");//扩展不过滤url
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		String uri = request.getRequestURI();//请求URI
		
		uri = uri.substring(request.getContextPath().length());//把请求URI去掉工程根路径
		//System.out.println(uri);
		if("".equals(uri) 
				|| "/".equals(uri) 
				|| uri.equals(loginURL1)
				|| uri.equals(loginURL2)
				|| uri.equals(loginURL3)
				|| uri.equals(loginURL4)
				|| uri.equals(loginURL5)
				|| uri.equals(loginURL6)
				|| uri.equals(loginURL7)
				){//如果是登录页面或者登录请求，不过滤
 			chain.doFilter(req, res);
			return;	
		}else if (excludePage.matcher(uri).matches()) {//如果请求的uri是不过滤的页面，不过滤
			chain.doFilter(request, response);
			return;
		}else if (extExcludePage.matcher(uri).matches()) {//如果请求的uri是不过滤的页面，不过滤
			chain.doFilter(request, response);
			return;
		}else{
			TestUser user = LoginUtil.getUser();//获取当前登陆人信息
			if(user == null){//没有从session中获取当前登陆人信息，跳转到登录页面
				//response.sendRedirect(request.getContextPath()+"/login");
				
				 request.setCharacterEncoding("UTF-8");  
                 response.setCharacterEncoding("GBK");  
                 PrintWriter out = response.getWriter();  
                 String loginPage =request.getContextPath()+"/";  
                 
                 StringBuilder builder = new StringBuilder();  
                 builder.append("<script type=\"text/javascript\">");  
                 builder.append("alert('网页过期，请重新登录！');");  
                 builder.append("window.top.location.href='");  
                 builder.append(loginPage);  
                 builder.append("';");  
                 builder.append("</script>");  
                 out.print(builder.toString()); 
				return;
				
			}
			chain.doFilter(req, res);
		}
		
	}
	
	public void destroy() {
		// TODO Auto-generated method stub
	}

}
