package com.hyf.cemap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hyf.cemap.bean.po.NewsType;
import com.hyf.cemap.dao.NewsTypeDaoImpl;
import com.hyf.cemap.util.persistence.impl.BaseServiceImpl;

@Service("newsTypeService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class NewsTypeServiceImpl extends BaseServiceImpl<NewsType>{
	
	@Autowired
    private NewsTypeDaoImpl newsTypeDao;


}
