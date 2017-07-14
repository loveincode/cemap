package com.hyf.cemap.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hyf.cemap.bean.po.GraduationFile;
import com.hyf.cemap.util.persistence.impl.BaseDAOImpl;

@Repository("graduationFileDao")
public class GraduationFileDaoImpl extends BaseDAOImpl<GraduationFile>{
    
    @Autowired
    private SessionFactory sessionFactory;
    
    @SuppressWarnings("unchecked")
    public GraduationFile getByStudentId(String studentId) {
        String sql = "from GraduationFile as model where model.deleted=0 and model.studentId='" + studentId+"'";
        Query query = this.getSessionFactory().getCurrentSession()
                .createQuery(sql);
        List<GraduationFile> instances = query.list();
        if (instances != null && !instances.isEmpty()) {
            return instances.get(0);
        }
        return null;
    }

}
