package com.hyf.cemap.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hyf.cemap.bean.po.NewsType;
import com.hyf.cemap.util.persistence.impl.BaseDAOImpl;

@Repository("newsTypeDao")
public class NewsTypeDaoImpl extends BaseDAOImpl<NewsType> {
	
	@Autowired
    private SessionFactory sessionFactory;
	
}
