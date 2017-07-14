package com.hyf.cemap.dao.base;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hyf.cemap.bean.po.base.Profession;
import com.hyf.cemap.bean.vo.ProfessionVO;
import com.hyf.cemap.util.persistence.impl.BaseDAOImpl;

@Repository("professionDao")
public class ProfessionDaoImpl extends BaseDAOImpl<Profession>{
    
    @Autowired
    private SessionFactory sessionFactory;
    
    /**
     * 通过collegeId查询出学院下面的所有专业
     * @param collegeId
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Profession> listByCollegeId(Integer collegeId){
        String sql = "from Profession as p where p.deleted=0  and p.collegeId = :collegeId";
        Query query = this.getSessionFactory().getCurrentSession()
                .createQuery(sql);
        query.setInteger("collegeId", collegeId);
        List<Profession> instances = query.list();
        return instances;
    }
    
    @SuppressWarnings("unchecked")
    public List<ProfessionVO> listAllProfessionVO(Integer pageNo, Integer pageSize) {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery(
                "SELECT"+
                " a.id,a.Uuid,a.name,a.code,b.name as collegeName"+
                " FROM"+
                " t_profession a"+
                " LEFT JOIN t_college b ON a.collegeId = b.id"+
                " WHERE"+
                " a.deleted = 0");
        if(pageSize != -1 && pageNo != -1) {
            query.setFirstResult((pageNo - 1) * pageSize);
            query.setMaxResults(pageSize);
        }
        query.setResultTransformer(Transformers.aliasToBean(ProfessionVO.class));
        return query.list();
    }
    
    /**
     * 专业信息综合查询
     * @param collegeId
     * @param name
     * @param pageNo
     * @param pageSize
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<ProfessionVO> listProfessionVOByInfos(Integer collegeId,String name,Integer pageNo, Integer pageSize) {
        String sql = "";
        String sqltmp =  "SELECT"+
        " a.id,a.Uuid,a.name,a.code,b.name as collegeName"+
        " FROM"+
        " t_profession a"+
        " LEFT JOIN t_college b ON a.collegeId = b.id"+
        " WHERE"+
        " a.deleted = 0";
        String sqlcollegeId = "";
        String sqlname = "";
        if(collegeId != -1){
            sqlcollegeId = " and a.collegeId=:collegeId";
        }
        if(name != null){
            sqlname = " and a.name like :name";
        }
        sql = sqltmp + sqlcollegeId + sqlname;
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery(sql);
        if(collegeId != -1){
            query.setInteger("collegeId", collegeId);
        }
        if(name != null){
            query.setString("name", "%"+name+"%");
        }
        if(pageSize != -1 && pageNo != -1) {
            query.setFirstResult((pageNo - 1) * pageSize);
            query.setMaxResults(pageSize);
        }
        query.setResultTransformer(Transformers.aliasToBean(ProfessionVO.class));
        System.out.println("专业信息 dao 查询语句"+query.toString());
        return query.list();
    }
     
    
}
