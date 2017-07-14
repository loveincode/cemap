package com.hyf.cemap.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hyf.cemap.bean.po.News;
import com.hyf.cemap.dao.NewsDaoImpl;
import com.hyf.cemap.util.persistence.impl.BaseServiceImpl;

@Service("newsService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class NewsServiceImpl extends BaseServiceImpl<News> {
    
    @Autowired
    private NewsDaoImpl newsDao;  
    
    public List<News> listNewsByinfo(String title,Integer newsTypeID,Integer pageNo,Integer pageSize) {
        return newsDao.listNewsByinfo(title, newsTypeID, pageNo, pageSize);
    };
    
    public List<News> listNewsByinfosort(String title,Integer newsTypeID,Integer pageNo,Integer pageSize,String sortname,String sortorder) {
    	return newsDao.listNewsByinfosort(title, newsTypeID, pageNo, pageSize, sortname, sortorder);
    }
    
    public List<News> listSixNews(Integer newsTypeID) {
        return newsDao.listSixNews(newsTypeID);
    }

}
