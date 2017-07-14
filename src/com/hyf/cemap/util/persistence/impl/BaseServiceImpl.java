package com.hyf.cemap.util.persistence.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hyf.cemap.util.persistence.BaseService;
import com.hyf.cemap.util.reflex.GenericsUtils;

/**
 * 增删该查Service实现类
*  @author  huyifan
*  @ClassName  BaseServiceImpl  
*  @date  2017年2月25日 上午11:51:03
 */
@Service("baseService")
@Transactional(propagation = Propagation.REQUIRED)
public class BaseServiceImpl<T> implements BaseService<T> {
	
	protected Class<T> entityClass;

	protected Class getEntityClass() {
		this.entityClass = GenericsUtils.getSuperClassGenricType(getClass());
		return entityClass;
	}

	@Autowired
	private BaseDAOImpl<T> baseDAOImpl;

	public T getById(Integer id) throws Exception {
		return (T) baseDAOImpl.getById(getEntityClass(), id);
	}
	
	@Override
	public T getByUuid(String Uuid) throws Exception {
		return (T) baseDAOImpl.getByUuid(getEntityClass(), Uuid);
	}
	
	@Override
    public T getByName(String name) throws Exception {
        return (T) baseDAOImpl.getByName(getEntityClass(), name);
    }

	public List<T> listAll(String order, String sort) throws Exception {
		return baseDAOImpl.listAll(getEntityClass(), order, sort);
	}

	public List<T> listAll(Integer pageNo, Integer pageSize)
			throws Exception {
		return baseDAOImpl.listAll(getEntityClass(), pageNo, pageSize);
	}

	public void save(T entity) throws Exception {
		baseDAOImpl.save(entity);
	}

	public void update(T entity) throws Exception {
		baseDAOImpl.update(entity);
	}

	public void delete(Integer id) throws Exception {
		baseDAOImpl.delete(getEntityClass(), id);
	}

	public void delete(T entity) throws Exception {
		baseDAOImpl.delete(entity);
	}

	public int count(String sql) throws Exception {
		return baseDAOImpl.count(sql);
	}

	
}
