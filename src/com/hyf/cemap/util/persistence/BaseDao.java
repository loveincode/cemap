package com.hyf.cemap.util.persistence;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * 
*  @author  huyifan
*  @ClassName  BaseDao  
*  @date  2017年2月25日 上午11:52:31
 */
public abstract class BaseDao<T> extends HibernateDaoSupport {
	
	protected Logger LOG = Logger.getLogger(this.getClass());
	
	/**
     * 将session进行注入
     * @param sessionFactory
     */
	@Resource(name = "sessionFactory")
	public void setMySessionFactory(SessionFactory sessionFactory){  
        super.setSessionFactory(sessionFactory);  
    }
}
