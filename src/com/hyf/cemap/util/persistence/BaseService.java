package com.hyf.cemap.util.persistence;
import java.util.List;


/**
 * 
*  @author  huyifan
*  @ClassName  BaseService  
*  @date  2017年2月25日 上午11:52:11
 */
public interface BaseService<T> {
	
	/**
	 * 根据ID获取单条数据
	 * @param id
	 * @return T
	 */
	public T getById(Integer id) throws Exception;
	
	/**
	 * 根据Uuid获取单条数据
	 * @param Uuid
	 * @return T
	 */
	public T getByUuid(String Uuid) throws Exception;
	
	/**
     * 根据name获取单条数据
     * @param Uuid
     * @return T
     */
    public T getByName(String name) throws Exception;
		
	/**
	 * 查询全部数据
	 * @param null
	 * @return List<T>
	 */
	public List<T> listAll(String order , String sort) throws Exception;
	
	/**
	 * 分页查询全部数据
	 * @param int start,int size
	 * @return List<T>
	 */
	public List<T> listAll(Integer pageNo, Integer pageSize) throws Exception;
	
	/**
	 * 添加数据
	 * @param entity
	 * @return null
	 */
	public void save(T entity) throws Exception;
	
	/**
	 * 更新数据
	 * @param entity
	 * @return null
	 */
	public void update(T entity) throws Exception;
	
	/**
	 * 根据ID删除数据
	 * @param id
	 * @return null
	 */
	public void delete(Integer id) throws Exception;
	
	/**
	 * 删除数据
	 * @param entity
	 * @return null
	 */
	public void delete(T entity) throws Exception;
	
	/**
	 * 统计sql记录条数
	 * @param sql
	 * @return
	 */
	public int count(String sql) throws Exception;
	
}
