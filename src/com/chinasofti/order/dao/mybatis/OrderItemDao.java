
package com.chinasofti.order.dao.mybatis;

import java.util.List;
import java.util.Map;

import com.chinasofti.order.domain.OrderItem;

import core.mybatis.MyBatisRepository;

/**
 * 通过@MapperScannerConfigurer扫描目录中的所有接口, 动态在Spring Context中生成实现.
 * 方法名称必须与Mapper.xml中保持一致.
 * 
 */
@MyBatisRepository
public interface OrderItemDao {
	

	/**
	 * 按条件分页查询记录
	 * @param searchParams 条件
	 * @return
	 */
	List<OrderItem> searchByPage(Map<String,Object> searchParams);
	/**
	 * 按主表ID查询记录
	 * @param orderId 主表ID
	 * @return
	 */
	List<OrderItem> searchByForeignKeyId(String orderId);
	/**
	 * 获取所有记录
	 * @return
	 */
	List<OrderItem> findAll();
	/**
	 * 通过Id获取一条记录
	 * @param id 主键
	 * @return
	 */
	OrderItem findById(String id);
	
	/**
	 * 保存一条记录
	 * @param user
	 */
	void save(OrderItem orderItem);
	/**
	 * 通过Id删除一条记录
	 * @param id
	 */
	void delete(String id);
	/**
	 * 通过外键Id删除记录
	 * @param ForeignKeyId
	 */
	void deleteByForeignKeyId(String orderId);
	/**
	 * 修改记录
	 * @param user
	 */
	void update(OrderItem orderItem);
}
