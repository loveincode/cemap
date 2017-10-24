package com.hyf.cemap.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hyf.cemap.bean.po.News;
import com.hyf.cemap.dao.NewsDaoImpl;
import com.hyf.cemap.util.persistence.impl.BaseServiceImpl;

@Service("newsService")
@Transactional(propagation = Propagation.REQUIRED)
public class NewsServiceImpl extends BaseServiceImpl<News> {
    
    @Autowired
    private NewsDaoImpl newsDao;  
    
    @Autowired
    private SessionFactory sessionFactory;
    
    public List<News> listNewsByinfo(String title,Integer newsTypeID,Integer pageNo,Integer pageSize) {
        return newsDao.listNewsByinfo(title, newsTypeID, pageNo, pageSize);
    };
    
    public List<News> listNewsByinfosort(String title,Integer newsTypeID,Integer pageNo,Integer pageSize,String sortname,String sortorder) {
    	return newsDao.listNewsByinfosort(title, newsTypeID, pageNo, pageSize, sortname, sortorder);
    }
    
    public List<News> listSixNews(Integer newsTypeID) {
        return newsDao.listSixNews(newsTypeID);
    }
    
    public void deletetx(String uuid) throws InterruptedException {
    	sessionFactory.getCurrentSession().beginTransaction();
        News news = newsDao.getByUuid(News.class, uuid);
        News news1 = news;
        News news2 = news;
        news1.setTitle("11");
        newsDao.save(news1);
       // int a = 1/0;
        news2.setTitle("22");
        newsDao.save(news2);
        
        Thread.sleep(8000);
        System.out.println("sleep 8s");
        sessionFactory.getCurrentSession().getTransaction().rollback();
    }

}
