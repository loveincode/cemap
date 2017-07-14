package com.hyf.cemap.util.persistence.impl;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hyf.cemap.util.persistence.BaseDao;
import com.hyf.cemap.util.persistence.CrudDao;
/**
 * 增删该查Dao实现类
*  @author  huyifan
*  @ClassName  BaseDAOImpl  
*  @date  2017年2月25日 上午11:51:19
 */

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class BaseDAOImpl<T> extends BaseDao implements CrudDao<T> {

	
	public T getById(Class clazz, Integer id) {
		String sql = "from " + clazz.getName()
				+ " as model where model.deleted=0 and model.id=" + id;
		Query query = this.getSessionFactory().getCurrentSession()
				.createQuery(sql);
		List<T> instances = query.list();
		if (instances != null && !instances.isEmpty()) {
			return instances.get(0);
		}
		return null;
		// T load = (T) this.getSession().get(entityClass, id);
		// return load;
	}

	@Override
	public T getByUuid(Class clazz, String Uuid) {
		String sql = "from " + clazz.getName()
				+ " as model where model.deleted=0 and model.Uuid=:Uuid";
		Query query = this.getSessionFactory().getCurrentSession()
				.createQuery(sql);
		query.setString("Uuid", Uuid);
		List<T> instances = query.list();
		if (instances != null && !instances.isEmpty()) {
			return instances.get(0);
		}
		return null;
	}
	
	@Override
    public T getByName(Class clazz, String name) {
	    String sql = "from " + clazz.getName()
                + " as model where model.deleted=0 and model.name=:name";
        Query query = this.getSessionFactory().getCurrentSession()
                .createQuery(sql);
        query.setString("name", name);
        List<T> instances = query.list();
        if (instances != null && !instances.isEmpty()) {
            return instances.get(0);
        }
        return null;
	}
	
	public List<T> listAll(Class clazz, String order, String sort) {
		String sql = "from " + clazz.getName()
				+ " as model where model.deleted=0 ";
		if (order != null
				&& sort != null
				&& order.toLowerCase() != ""
				&& ("asc".equals(sort.toLowerCase()) || "desc".equals(sort
						.toLowerCase()))) {
			sql += " order by model." + order + " " + sort;
		}
		Query query = this.getSessionFactory().getCurrentSession()
				.createQuery(sql);
		List<T> instances = query.list();
		return instances;
	}

	public List<T> listAll(Class clazz, Integer pageNo, Integer pageSize) {
		String sql = "from " + clazz.getName()
				+ " as model where model.deleted=0 ";
		Query query = this.getSessionFactory().getCurrentSession()
				.createQuery(sql);
		if(pageSize != -1 && pageNo != -1) {
			query.setFirstResult((pageNo - 1) * pageSize);
			query.setMaxResults(pageSize);
		}
		List<T> instances = query.list();
		return instances;
	}

	public void save(T entity) {
		this.getSessionFactory().getCurrentSession().save(entity);
		this.getSessionFactory().getCurrentSession().flush();
	}

	public void update(T entity) {
		this.getSessionFactory().getCurrentSession().update(entity);
		this.getSessionFactory().getCurrentSession().flush();
	}

	public void delete(Class clazz, Integer id) {
		T instance = getById(clazz, id);
		if (instance != null) {
			getHibernateTemplate().delete(instance);
		}
		this.getSessionFactory().getCurrentSession().flush();
	}

	public void delete(T entity) {
		this.getSessionFactory().getCurrentSession().delete(entity);
		this.getSessionFactory().getCurrentSession().flush();
	}

	public int count(String sql) {
		Query query = this.getSessionFactory().getCurrentSession()
				.createQuery(sql);
		int sum = 0;
		Iterator it = query.iterate();
		if (it.hasNext()) {
			Object obj = it.next();
			if (obj != null) {
				sum = Integer.parseInt(obj.toString());
			}
		}
		return sum;
	}

	

}
