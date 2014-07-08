
package com.chinasofti.order.dao.mybatis;

import java.util.List;
import java.util.Map;

import com.chinasofti.order.domain.Order;

import core.mybatis.MyBatisRepository;

/**
 * 通过@MapperScannerConfigurer扫描目录中的所有接口, 动态在Spring Context中生成实现.
 * 方法名称必须与Mapper.xml中保持一致.
 * 
 */
@MyBatisRepository
public interface OrderDao {
	

	/**
	 * 按条件分页查询记录
	 * @param searchParams 条件
	 * @return
	 */
	List<Order> searchByPage(Map<String,Object> searchParams);
	/**
	 * 按条件查询记录
	 * @param searchParams 条件
	 * @return
	 */
	List<Order> search(Map<String,Object> searchParams);
	/**
	 * 获取所有记录
	 * @return
	 */
	List<Order> findAll();
	/**
	 * 通过Id获取一条记录
	 * @param id 主键
	 * @return
	 */
	Order findById(String id);
	
	/**
	 * 保存一条记录
	 * @param user
	 */
	void save(Order user);
	/**
	 * 通过Id删除一条记录
	 * @param id
	 */
	void delete(String id);
	/**
	 * 修改记录
	 * @param user
	 */
	void update(Order user);
}
