package com.hyf.cemap.dao.log;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hyf.cemap.bean.po.log.LogInOut;
import com.hyf.cemap.util.persistence.impl.BaseDAOImpl;

@Repository("logInOutDao")
public class LogInOutDaoImpl extends BaseDAOImpl<LogInOut>{

    @Autowired
    private SessionFactory sessionFactory;
    
    /**
     * 高级查询  
     * @param name
     * @param pageNo
     * @param pageSize
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<LogInOut> listLogInOutByinfo(String username,Integer pageNo,Integer pageSize) {
        Query query = null;
        String sql = "";
        String sqltmp = "FROM LogInOut WHERE deleted = false";
        String sqlname = "";
        
        if (username != null) {
            sqlname = " and username like :username";
        }
        sql = sqltmp  + sqlname +" Order by date desc";
        query = sessionFactory.getCurrentSession().createQuery(sql);
        if (username != null) {
            query.setString("username", "%" + username + "%");
        }
        if (pageSize != -1 && pageNo != -1) {
            query.setFirstResult((pageNo - 1) * pageSize);
            query.setMaxResults(pageSize);
        }
        return query.list();
    }
    
}
