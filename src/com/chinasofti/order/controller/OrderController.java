
package com.chinasofti.order.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springside.modules.web.Servlets;

import com.chinasofti.order.domain.Order;
import com.chinasofti.order.service.OrderService;

import core.mybatis.PageParameter;
import core.spring.RequestMappingName;


@Controller
@RequestMapping(value = "/order")
public class OrderController {
	private static Logger logger = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	private OrderService orderService;
	
	/**
	 * 分页查询
	 * @param pageNumber
	 * @param pageSize
	 * @param sortType
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMappingName(value = "查询列表")
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String list(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "page.size", defaultValue = PageParameter.DEFAULT_PAGE_SIZE+"") int pageSize,Model model,
			ServletRequest request) {
		Map<String, Object> searchParams = Servlets.getParametersStartingWith(request, "search_");
		
		PageParameter page = new PageParameter();
		page.setCurrentPage(pageNumber);
		page.setPageSize(pageSize);
		searchParams.put("page", page);
		
		List<Order> orders = orderService.searchByPage(searchParams);
		model.addAttribute("orders", orders);
		model.addAttribute("page", (Page)page);
		model.addAttribute("paginationSize", pageSize);
		

		// 将搜索条件编码成字符串，用于排序，分页的URL
		model.addAttribute("searchParams", Servlets.encodeParameterStringWithPrefix(searchParams, "search_"));
		return "com/chinasofti/order/orderList";
	}
	/**
	 * 跳转到增加页面
	 * @return
	 */
	@RequestMappingName(value = "打开增加页面")
	@RequestMapping(value = "toAddPage", method = RequestMethod.GET)
	public String toAddPage() {
		return "com/chinasofti/order/orderAdd";
	}
	/**
	 * 执行保存操作
	 * @param user
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMappingName(value = "保存")
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public String save(@Valid Order order, RedirectAttributes redirectAttributes) {
		orderService.save(order);
		return "redirect:/order/list";
	}
	/**
	 * 跳转到修改页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMappingName(value = "打开修改页面")
	@RequestMapping(value = "toUpdatePage/{id}", method = RequestMethod.GET)
	public String toUpdatePage(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "page.size", defaultValue = PageParameter.DEFAULT_PAGE_SIZE+"") int pageSize,@PathVariable("id") String id, 
			Model model,ServletRequest request) {
		Map<String, Object> searchParams = new HashMap<String,Object>();
		searchParams.put("orderId", id);
		
		PageParameter page = new PageParameter();
		page.setCurrentPage(pageNumber);
		page.setPageSize(pageSize);
		searchParams.put("page", page);
		
		model.addAttribute("page", (Page)page);
		model.addAttribute("paginationSize", pageSize);
		
		model.addAttribute("order", orderService.findById(id));
		model.addAttribute("orderItems", orderService.searchBySublistId(searchParams));

		return "com/chinasofti/order/orderUpdate";
	}
	/**
	 * 执行修改操作
	 * @param user
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMappingName(value = "修改")
	@RequestMapping(value = "update", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute("user") Order order, RedirectAttributes redirectAttributes) {
		orderService.update(order);
		redirectAttributes.addFlashAttribute("message", "更新成功");
		return "redirect:/order/list";
	}
	/**
	 * 跳转详细页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMappingName(value = "打开详细页面")
	@RequestMapping(value = "toDetailPage/{id}", method = RequestMethod.GET)
	public String toDetailPage(@RequestParam(value = "page", defaultValue = "1") int pageNumber,
			@RequestParam(value = "page.size", defaultValue = PageParameter.DEFAULT_PAGE_SIZE+"") int pageSize,@PathVariable("id") String id,
			Model model,ServletRequest request) {
		
		Map<String, Object> searchParams = new HashMap<String,Object>();
		searchParams.put("orderId", id);
		
		PageParameter page = new PageParameter();
		page.setCurrentPage(pageNumber);
		page.setPageSize(pageSize);
		searchParams.put("page", page);
		
		model.addAttribute("page", (Page)page);
		model.addAttribute("paginationSize", pageSize);
		
		model.addAttribute("order", orderService.findById(id));
		model.addAttribute("orderItems", orderService.searchBySublistId(searchParams));
		
		return "com/chinasofti/order/orderDetail";
	}
	/**
	 * 执行删除操作
	 * @param id
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMappingName(value = "删除")
	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") String id, RedirectAttributes redirectAttributes) {
		orderService.delete(id);
		redirectAttributes.addFlashAttribute("message", "删除成功");
		return "redirect:/order/list";
	}
	
	/**
	 * 执行删除子表操作
	 * @param id
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMappingName(value = "删除子表")
	@RequestMapping(value = "deleteSublist/{id}")
	@ResponseBody
	public String deleteSublist(@PathVariable("id") String ids, RedirectAttributes redirectAttributes) {
		try{
			orderService.deleteSublist(ids);
			return "{\"msg\":\"删除记录成功！\"}";
		}catch(Exception e){
			return "{\"msg\":\"删除记录失败！\"}";
		}
	}
}
