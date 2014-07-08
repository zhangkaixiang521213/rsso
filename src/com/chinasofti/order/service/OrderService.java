
package com.chinasofti.order.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chinasofti.order.dao.mybatis.OrderDao;
import com.chinasofti.order.dao.mybatis.OrderItemDao;
import com.chinasofti.order.domain.Order;
import com.chinasofti.order.domain.OrderItem;

import core.utils.ComUtil;

@Service
@Transactional
public class OrderService {

	private static Logger logger = LoggerFactory.getLogger(OrderService.class);

	@Autowired
	private OrderDao orderDao;
	@Autowired
	private OrderItemDao orderItemDao;

	/**
	 * 按条件分页查询记录
	 * @param searchParams 条件
	 * @return
	 */
	public List<Order> searchByPage(Map<String,Object> searchParams) {
		return (List<Order>) orderDao.searchByPage(searchParams);
	}
	
	/**
	 * 按条件分页查询子表记录
	 * @param searchParams 条件
	 * @return
	 */
	public List<OrderItem> searchBySublistId(Map<String,Object> searchParams) {
		return (List<OrderItem>) orderItemDao.searchByPage(searchParams);
	}
	
	/**
	 * 按主表ID查询记录
	 * @param orderId 主表ID
	 * @return
	 */
	public List<OrderItem> searchByForeignKeyId(String orderId) {
		return (List<OrderItem>) orderItemDao.searchByForeignKeyId(orderId);
	}
	
	/**
	 * 按条件查询记录
	 * @param searchParams 条件
	 * @return
	 */
	public List<Order> search(Map<String,Object> searchParams) {
		return (List<Order>) orderDao.search(searchParams);
	}

	/**
	 * 获取所有记录
	 * @return
	 */
	public List<Order> findAll() {
		return (List<Order>) orderDao.findAll();
	}
	/**
	 * 通过Id获取一条记录
	 * @param id 主键
	 * @return
	 */
	public Order findById(String id) {
		return orderDao.findById(id);
	}


	/**
	 * 保存一条记录
	 * @param user
	 */
	public void save(Order order) {
		order.setOrderId(ComUtil.getId());
		orderDao.save(order);
		saveOrderItem(order);
	}


	/**
	 * 通过Id删除一条记录
	 * @param id
	 */
	public void delete(String id) {
		orderItemDao.deleteByForeignKeyId(id);
		orderDao.delete(id);
	}
	/**
	 * 删除子表操作
	 * @param ids 多外ID串用,号隔开
	 */
	public void deleteSublist(String ids) {
		String[] idArr=ids.split(",");
		for(String id:idArr){
			orderItemDao.delete(id);
		}
	}
	
	/**
	 * 修改记录
	 * @param order
	 */
	public void update(Order order) {
		orderDao.update(order);
		saveOrderItem(order);
	}
	
	/**
	 * 保存子表
	 * @param order
	 */
	private void saveOrderItem(Order order){
		List<OrderItem>  list=order.getOrderItems();
		for(OrderItem item:list){
			if(item.getItemId()!=null && !"".equals(item.getItemId())){
				orderItemDao.update(item);
			}else{
				item.setItemId(ComUtil.getId());
				item.setOrderId(order.getOrderId());
				orderItemDao.save(item);
			}
		}
	}

}
