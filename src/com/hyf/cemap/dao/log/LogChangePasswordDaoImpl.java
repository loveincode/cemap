package com.hyf.cemap.dao.log;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hyf.cemap.bean.po.log.LogChangePassword;
import com.hyf.cemap.util.persistence.impl.BaseDAOImpl;

@Repository("logChangePasswordDao")
public class LogChangePasswordDaoImpl  extends BaseDAOImpl<LogChangePassword>{

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
    public List<LogChangePassword> listLogChangePasswordByinfo(String username_c,Integer pageNo,Integer pageSize) {
        Query query = null;
        String sql = "";
        String sqltmp = "FROM LogChangePassword WHERE deleted = false";
        String sqlname = "";
        
        if (username_c != null) {
            sqlname = " and username_c like :username_c";
        }
        sql = sqltmp  + sqlname +" Order by date desc";
        query = sessionFactory.getCurrentSession().createQuery(sql);
        if (username_c != null) {
            query.setString("username_c", "%" + username_c + "%");
        }
        if (pageSize != -1 && pageNo != -1) {
            query.setFirstResult((pageNo - 1) * pageSize);
            query.setMaxResults(pageSize);
        }
        return query.list();
    }
    
}
