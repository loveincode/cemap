package com.hyf.cemap.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hyf.cemap.bean.po.PreachingMeeting;
import com.hyf.cemap.util.persistence.impl.BaseDAOImpl;

@Repository("preachingMeetingDao")
public class PreachingMeetingDaoImpl extends BaseDAOImpl<PreachingMeeting> {

    @Autowired
    private SessionFactory sessionFactory;
    
    /**
     * 高级查询分页查询
     * @param companyName
     * @param companyType
     * @param pageNo
     * @param pageSize
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<PreachingMeeting> flistPreachingMeetingByinfo(String companyName,String companyType,Integer pageNo,Integer pageSize) {
        Query query = null;
        String sql = "";
        String sqltmp = "FROM PreachingMeeting WHERE deleted = false";
        String sqlcompanyName = "";
        String sqlcompanyType ="";
        
        if (companyName != null) {
            sqlcompanyName = " and companyName like :companyName";
        }
        if (companyType != null){
            sqlcompanyType = " and companyType = :companyType";
        }
        sql = sqltmp  + sqlcompanyName + sqlcompanyType +" and dayDate >=:nowdate  Order by dayDate asc";
        query = sessionFactory.getCurrentSession().createQuery(sql);
        if (companyName != null) {
            query.setString("companyName", "%" + companyName + "%");
        }
        if (companyType != null){
            query.setString("companyType", companyType);
        }
        if (pageSize != -1 && pageNo != -1) {
            query.setFirstResult((pageNo - 1) * pageSize);
            query.setMaxResults(pageSize);
        }
        query.setDate("nowdate", new Date());
        System.out.println("宣讲会信息 dao 查询 companyType  --"+companyType+"  sql :"+query.toString());
        return query.list();
    }
    
    /**
     * 高级查询分页查询
     * @param companyName
     * @param companyType
     * @param pageNo
     * @param pageSize
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<PreachingMeeting> listPreachingMeetingByinfo(String companyName,String companyType,Integer pageNo,Integer pageSize) {
        Query query = null;
        String sql = "";
        String sqltmp = "FROM PreachingMeeting WHERE deleted = false";
        String sqlcompanyName = "";
        String sqlcompanyType ="";
        
        if (companyName != null) {
            sqlcompanyName = " and companyName like :companyName";
        }
        if (companyType != null){
            sqlcompanyType = " and companyType = :companyType";
        }
        sql = sqltmp  + sqlcompanyName + sqlcompanyType +" Order by dayDate asc";
        query = sessionFactory.getCurrentSession().createQuery(sql);
        if (companyName != null) {
            query.setString("companyName", "%" + companyName + "%");
        }
        if (companyType != null){
            query.setString("companyType", companyType);
        }
        if (pageSize != -1 && pageNo != -1) {
            query.setFirstResult((pageNo - 1) * pageSize);
            query.setMaxResults(pageSize);
        }
        return query.list();
    }
    
    @SuppressWarnings("unchecked")
    public List<PreachingMeeting> listSixPreachingMeeting() {
        Query query = this.sessionFactory.getCurrentSession().createQuery(
                "from PreachingMeeting where deleted = false  and dayDate >=:nowdate  Order by dayDate asc");
        query.setMaxResults(6);
        query.setDate("nowdate", new Date());
        return query.list();
    }
    
}
