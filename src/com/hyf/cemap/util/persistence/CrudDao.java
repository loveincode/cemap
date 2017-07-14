package com.hyf.cemap.util.persistence;
import java.util.List;

/**
 * 
*  @author  huyifan
*  @ClassName  CrudDao  
*  @date  2017年2月25日 上午11:52:44
 */
public interface CrudDao<T> {
	
	/**
	 * 根据ID获取单条数据
	 * @param id
	 * @return T
	 */
	public T getById(Class clazz , Integer id);
	
	/**
	 * 根据Uuid获取单条数据
	 * @param Uuid
	 * @return T
	 */
	public T getByUuid(Class clazz , String Uuid);
	
	/**
     * 根据name获取单条数据
     * @param name
     * @return T
     */
    public T getByName(Class clazz , String name);
		
	/**
	 * 查询全部数据
	 * @param null
	 * @return List<T>
	 */
	public List<T> listAll(Class clazz , String order , String sort);
	
	/**
	 * 分页查询全部数据  datatable
	 * @param Integer pageNo, Integer pageSize
	 * @return List<T>
	 */
	public List<T> listAll(Class clazz , Integer pageNo, Integer pageSize);
	
	/**
	 * 添加数据
	 * @param entity
	 * @return 
	 */
	public void save(T entity);
	
	/**
	 * 更新数据
	 * @param entity
	 * @return null
	 */
	public void update(T entity);
	
	/**
	 * 根据ID删除数据
	 * @param id
	 * @return null
	 */
	public void delete(Class clazz , Integer id);
	
	/**
	 * 删除数据
	 * @param entity
	 * @return null
	 */
	public void delete(T entity);
	
	/**
	 * 统计sql记录条数
	 * @param sql
	 * @return
	 */
	public int count(String sql);
	
}
