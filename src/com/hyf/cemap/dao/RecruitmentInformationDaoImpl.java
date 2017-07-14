package com.hyf.cemap.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hyf.cemap.bean.po.RecruitmentInformation;
import com.hyf.cemap.util.persistence.impl.BaseDAOImpl;

@Repository("recruitmentInformationDao")
public class RecruitmentInformationDaoImpl extends BaseDAOImpl<RecruitmentInformation> {

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
    public List<RecruitmentInformation> flistRecruitmentInformationByinfo(String companyName,String companyType,Integer pageNo,Integer pageSize) {
        Query query = null;
        String sql = "";
        String sqltmp = "FROM RecruitmentInformation WHERE deleted = false";
        String sqlcompanyName = "";
        String sqlcompanyType ="";
        
        if (companyName != null) {
            sqlcompanyName = " and companyName like :companyName";
        }
        if (companyType != null){
            sqlcompanyType = " and companyType = :companyType";
        }
        sql = sqltmp  + sqlcompanyName + sqlcompanyType +" and deadline >=:nowdate  Order by deadline asc";
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
    public List<RecruitmentInformation> listRecruitmentInformationByinfo(String companyName,String companyType,Integer pageNo,Integer pageSize) {
        Query query = null;
        String sql = "";
        String sqltmp = "FROM RecruitmentInformation WHERE deleted = false";
        String sqlcompanyName = "";
        String sqlcompanyType ="";
        
        if (companyName != null) {
            sqlcompanyName = " and companyName like :companyName";
        }
        if (companyType != null){
            sqlcompanyType = " and companyType = :companyType";
        }
        sql = sqltmp  + sqlcompanyName + sqlcompanyType +" Order by deadline desc";
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
        System.out.println("招聘信息 dao 查询 companyType  --"+companyType+"  sql :"+query.toString());
        return query.list();
    }
    
    @SuppressWarnings("unchecked")
    public List<RecruitmentInformation> listSixRecruitmentInformation() {
        Query query = this.sessionFactory.getCurrentSession().createQuery(
                "from RecruitmentInformation where deleted = false and deadline >=:nowdate  Order by deadline asc");
        query.setMaxResults(6);
        query.setDate("nowdate", new Date());
        return query.list();
    }
    
}
