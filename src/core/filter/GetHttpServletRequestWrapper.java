package core.filter;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class GetHttpServletRequestWrapper extends HttpServletRequestWrapper {
  
	  private HttpServletRequest request;
	  private String encoding;
	  
	  public GetHttpServletRequestWrapper(HttpServletRequest request,String encoding) {
		   super(request);
		   this.request = request;
		   this.encoding=encoding;
	  }
	  
	  /**
	   * 重載getparamtert的方法
	   */
	  @Override
	  public String getParameter(String name) {
		   	String value = request.getParameter(name);
			   try {
				value = value == null ? null : new String(value.getBytes("ISO8859-1"), encoding);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}   
		    return value;
	  }
	  
	  /**
	   * 重載getParameterValues的方法
	   */
	  @Override
	  public String[] getParameterValues(String name){
		  String[] values = request.getParameterValues(name);
		  String[] rvalues = null;
		  if(values!=null){
			  rvalues = new String[values.length];
			  for(int i=0;i<values.length;i++){
				  try {
					rvalues[i]=values[i] == null ? null : new String(values[i].getBytes("ISO8859-1"), encoding);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			  }
		  }
		  
		 return rvalues;
		  
	  }
}
