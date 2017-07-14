package com.hyf.cemap.dao.base;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hyf.cemap.bean.po.base.College;
import com.hyf.cemap.util.persistence.impl.BaseDAOImpl;

@Repository("collegeDao")
public class CollegeDaoImpl extends BaseDAOImpl<College>{
    
    @Autowired
    private SessionFactory sessionFactory;
    
    /**
     * 高级查询  学院
     * @param name
     * @param pageNo
     * @param pageSize
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<College> listCollegeByinfo(String name,Integer pageNo,Integer pageSize) {
        Query query = null;
        String sql = "";
        String sqltmp = "FROM College WHERE deleted = false";
        String sqlname = "";
        
        if (name != null) {
            sqlname = " and name like :name";
        }
        sql = sqltmp  + sqlname +" Order by id";
        query = sessionFactory.getCurrentSession().createQuery(sql);
        if (name != null) {
            query.setString("name", "%" + name + "%");
        }
        if (pageSize != -1 && pageNo != -1) {
            query.setFirstResult((pageNo - 1) * pageSize);
            query.setMaxResults(pageSize);
        }
        return query.list();
    }
    
}
