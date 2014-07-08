package core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class CharacterEncodingFilter implements Filter {

	private FilterConfig filterConfig;
    private String encoding = null;
    
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * post,get式提交乱码处理
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
    HttpServletRequest rqt = (HttpServletRequest) request;
    
    	String method = rqt.getMethod();
        if(this.encoding!=null){
            if(method.equalsIgnoreCase("post")){
	            request.setCharacterEncoding(encoding);
	            response.setCharacterEncoding(encoding);
            }else{
            	request=new GetHttpServletRequestWrapper((HttpServletRequest) request,encoding);
            }
        }
        chain.doFilter(request,response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
        this.filterConfig=arg0;
        this.encoding=filterConfig.getInitParameter("encoding");

	}

}
