/**
 * $Id: SystemUtil.java,v 1.8 2013/11/27 08:52:22 zhangwei Exp $
 *
 * Copyright (c) 2006 ChinaSoft International, Ltd. All rights reserved
 * ResourceOne Framework V4 Project
 *
 */
package com.chinasofti.oauth2.client;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;


/**
 * @Title: SystemUtil.java
 * @Description: <br>
 *               <br>
 * @Company: ICSS
 * @Created on 2013-8-13 上午11:23:08
 * @author zhangwei
 * @version $Revision: 1.8 $
 * @since 1.0
 */
public class SystemUtil {
	
	 private static final ThreadLocal threadsAttributes = new ThreadLocal();
	 public static final String TOKE="TKEN";
	 public static final String USER="USER";
	 public static final String USER_ID="USER_ID";
	 public static final String BEARER="bearer";
	    /**
	     * 设置当前线程属性
	     * @param attName
	     * @param attValue
	     */
	    public static void setCurThreadAttribute(String attName, Object attValue){
	        HashMap atts = (HashMap) threadsAttributes.get();
	        if (atts == null){
	            atts = new HashMap();

	            threadsAttributes.set(atts);
	        }

	        atts.put(attName, attValue);
	    }

	    /**
	     * 系统需要调用这个方法去掉当前线程的纪录，重要，这个方法一定要在处理完成后调用！
	     */
	    public static void removeCurThreadAllAttribute(){        
	        threadsAttributes.set(null);
	    }

	    /**
	     * 根据属性得到当前线程的属性
	     * @param attName
	     * @return
	     */
	    public static Object getCurThreadAttribute(String attName){
	        HashMap atts = (HashMap) threadsAttributes.get();
	        if (atts == null){
	            return null;
	        }
	        else{
	            return atts.get(attName);
	        }
	    }

	    /**
	     * 根据属性删除线程的某个属性，返回被删除的属性值
	     * @param attName
	     * @return
	     */
	    public static Object removeCurThreadAttribute(String attName){
	        HashMap atts = (HashMap) threadsAttributes.get();
	        if (atts == null) {
	            return null;
	        }

	        Object old = atts.get(attName);
	        atts.remove(attName);
	        return old;
	    }
		
		/**
		 * 获取异常中的信息，对引号回车换行进行转义，用于json输出
		 * @param e
		 * @return
		 */
		public static String getErrorMessge(Throwable e){
			String error="";
			/*
			if(e instanceof BizException){
				error=((BizException) e).getErrorDescription();
				error=error==null?e.toString():error;
			}else{
				error=e.getMessage()==null?e.toString():e.getMessage();
			}
			error=error.replaceAll("'", "\\\\\'");
			error=error.replaceAll("\"", "\\\\\"");
			error=error.replaceAll("\r\n", "");
			error=error.replaceAll("\n", "");
			*/
			return error;
		}
		

		/**
		 * 获取当前线程中绑定的HttpServletRequest
		 * @return
		 */
		public static HttpServletRequest request() {
			return (HttpServletRequest)getCurThreadAttribute("_j2ee_request_");
		}

		/**
		 * 获取当前线程中绑定的HttpServletResponse
		 * @return
		 */
		public static HttpServletResponse response() {
			return (HttpServletResponse)getCurThreadAttribute("_j2ee_response_");
		}

		/**
		 * 将request，response对象绑定到当前线程中
		 * @param request
		 * @param response
		 */
		public static void bind(HttpServletRequest request,
				HttpServletResponse response) {
			setCurThreadAttribute("_j2ee_request_", request);
			setCurThreadAttribute("_j2ee_response_", response);
			
		}
		
		/**
		 * 加密
		 * @param code
		 * @return
		 */
		public static String encode(String code){
		    	Base64 base64 = new  Base64();
		        byte[] bytecode = base64.encode(code.getBytes());
		    	return new String(bytecode);
		}
		
		/**
		 * 解密
		 * @param code
		 * @return
		 */
		public static String decode(String code){
			Base64 base64 = new  Base64();
	        byte[] bytecode = base64.decode(code.getBytes());
	    	return new String(bytecode);
		}
	
}
