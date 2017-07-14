package com.hyf.cemap.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hyf.cemap.bean.po.News;
import com.hyf.cemap.util.persistence.impl.BaseDAOImpl;

@Repository("newsDao")
public class NewsDaoImpl extends BaseDAOImpl<News> {
	
	@Autowired
    private SessionFactory sessionFactory;
	
	/**
     * 分页查询
     * @param pageNo
     * @param pageSize
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<News> listNewsByinfo(String title,Integer newsTypeID,Integer pageNo,Integer pageSize) {
        Query query = null;
        String sql = "";
        String sqltmp = "FROM News WHERE deleted = false";
        String sqltitle = "";
        String sqltypeid ="";
        
        if (title != null) {
            sqltitle = " and title like :title";
        }
        if (newsTypeID != -1){
            sqltypeid = " and newsType_id = :newsType_id";
        }
        sql = sqltmp  + sqltitle + sqltypeid +" Order by publishDate desc";
        query = sessionFactory.getCurrentSession().createQuery(sql);
        if (title != null) {
            query.setString("title", "%" + title + "%");
        }
        if (newsTypeID != -1){
            query.setInteger("newsType_id", newsTypeID);
        }
        if (pageSize != -1 && pageNo != -1) {
            query.setFirstResult((pageNo - 1) * pageSize);
            query.setMaxResults(pageSize);
        }
        return query.list();
    }
    
    /**
     * 分页查询
     * @param pageNo
     * @param pageSize
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<News> listNewsByinfosort(String title,Integer newsTypeID,Integer pageNo,Integer pageSize,String sortname,String sortorder) {
        Query query = null;
        String sql = "";
        String sqltmp = "FROM News WHERE deleted = false";
        String sqltitle = "";
        String sqltypeid ="";
        String sqlorder = " Order by publishDate desc";
        if (title != null) {
            sqltitle = " and title like :title";
        }
        if (newsTypeID != -1){
            sqltypeid = " and newsType_id = :newsType_id";
        }
        if (sortname!=null && sortorder != null) {
        	sqlorder = " Order by "+sortname+" "+ sortorder;
        }
        sql = sqltmp  + sqltitle + sqltypeid + sqlorder;
        query = sessionFactory.getCurrentSession().createQuery(sql);
        if (title != null) {
            query.setString("title", "%" + title + "%");
        }
        if (newsTypeID != -1){
            query.setInteger("newsType_id", newsTypeID);
        }
        if (pageSize != -1 && pageNo != -1) {
            query.setFirstResult((pageNo - 1) * pageSize);
            query.setMaxResults(pageSize);
        }
        return query.list();
    }
    
    @SuppressWarnings("unchecked")
    public List<News> listSixNews(Integer newsTypeID) {
        Query query = this.sessionFactory.getCurrentSession().createQuery(
                "from News where deleted = false and newsType_id = :newsType_id Order by publishDate desc");
        query.setInteger("newsType_id", newsTypeID);
        query.setMaxResults(6);
        return query.list();
    }
    
    @SuppressWarnings("unchecked")
    public List<News> listFilePathByNews() {
        Query query = this.sessionFactory.getCurrentSession().createQuery(
                "from News where deleted = false and newsType_id = :newsType_id Order by publishDate desc");
        query.setMaxResults(6);
        return query.list();
    }
	
}
