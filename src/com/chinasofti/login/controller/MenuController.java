
package com.chinasofti.login.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.chinasofti.login.service.ResourceXMLAnalyse.ModuleConfig;
import com.chinasofti.login.service.ResourceXMLAnalyse.XmlPa;

import core.spring.RequestMappingName;


@Controller
public class MenuController {
	private static Logger logger = LoggerFactory.getLogger(MenuController.class);

	/**
	 * 跳转到主页面
	 * @return
	 */
	@RequestMappingName(value = "跳转到主页面")
	@RequestMapping(value = "index", method = RequestMethod.GET)
	public String toLogin() {
		return "menus/main";
	}
	
	/**
	 * <p>跳转到top页面</p>
	 * 
	 */
	@RequestMappingName(value = "跳转到主头页面")
	@RequestMapping(value = "toTop", method = RequestMethod.GET)
	public String top() {
		return "menus/top";
	}
	
	/**
	 * <p>主页面左侧页面菜单加载</p>
	 * 
	 */
	@RequestMappingName(value = "跳转到主左页面")
	@RequestMapping(value = "toLeft", method = RequestMethod.GET)
	public String left(Model model,HttpServletRequest request, HttpServletResponse response) {
		String filePath=request.getSession().getServletContext().getRealPath("WEB-INF"+File.separator+"classes"+File.separator+"architect.xml");
		
		File file=new File(filePath);
		try {
			List<ModuleConfig> list=XmlPa.getMenuList(file);
			model.addAttribute("menus", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "menus/left";
	}
	
}
