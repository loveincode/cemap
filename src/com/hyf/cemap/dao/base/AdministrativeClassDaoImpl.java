package com.hyf.cemap.dao.base;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hyf.cemap.bean.po.base.AdministrativeClass;
import com.hyf.cemap.bean.vo.AdministrativeClassVO;
import com.hyf.cemap.util.persistence.impl.BaseDAOImpl;

@Repository("administrativeClassDao")
public class AdministrativeClassDaoImpl extends BaseDAOImpl<AdministrativeClass>{

    @Autowired
    private SessionFactory sessionFactory;
    
    /**
     * 通过classIds查询出专业下面的所有班级 辅导员管理的班级
     * @param classIds
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<AdministrativeClass> listByClassIds(List<Integer> classIds){
        String sql = "from AdministrativeClass as a where a.deleted=0  "
                + " and id in :classIds";
        Query query = this.getSessionFactory().getCurrentSession()
                .createQuery(sql);
        query.setParameterList("classIds", classIds);
        List<AdministrativeClass> instances = query.list();
        return instances;
    }
    
    
    /**
     * 通过professionId查询出专业下面的所有班级
     * @param professionId
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<AdministrativeClass> listByProfessionId(Integer professionId){
        String sql = "from AdministrativeClass as a where a.deleted=0  and a.professionId = :professionId";
        Query query = this.getSessionFactory().getCurrentSession()
                .createQuery(sql);
        query.setInteger("professionId", professionId);
        List<AdministrativeClass> instances = query.list();
        return instances;
    }
    
    @SuppressWarnings("unchecked")
    public List<AdministrativeClassVO> listAllAdministrativeClassVO(Integer pageNo, Integer pageSize) {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery(
                "SELECT"+
                " a.id,a.Uuid,a.name,a.code,b.name as professionName,c.name as collegeName"+
                " FROM"+
                " t_administrativeclass a,t_profession b,t_college c"+
                " WHERE"+
                " a.professionId = b.id AND a.collegeId = c.id AND "+
                " a.deleted = 0");
        if(pageSize != -1 && pageNo != -1) {
            query.setFirstResult((pageNo - 1) * pageSize);
            query.setMaxResults(pageSize);
        }
        query.setResultTransformer(Transformers.aliasToBean(AdministrativeClassVO.class));
        return query.list();
    }
    
    /**
     * 班级信息综合查询
     * @param collegeId
     * @param name
     * @param pageNo
     * @param pageSize
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<AdministrativeClassVO> listAdministrativeClassVOByInfos(Integer collegeId,Integer professionId,String name,Integer pageNo, Integer pageSize) {
        String sql = "";
        String sqltmp =  "SELECT"+
            " a.id,a.Uuid,a.name,a.code,b.name as professionName,c.name as collegeName"+
            " FROM"+
            " t_administrativeclass a,t_profession b,t_college c"+
            " WHERE"+
            " a.professionId = b.id AND a.collegeId = c.id AND "+
            " a.deleted = 0";
        String sqlcollegeId = "";
        String sqlprofessionId = "";
        String sqlname = "";
        if(collegeId != -1){
            sqlcollegeId = " and a.collegeId=:collegeId";
        }
        if(professionId != -1){
            sqlprofessionId = " and a.professionId=:professionId";
        }
        if(name != null){
            sqlname = " and a.name like :name";
        }
        sql = sqltmp + sqlcollegeId + sqlprofessionId + sqlname;
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery(sql);
        if(collegeId != -1){
            query.setInteger("collegeId", collegeId);
        }
        if(professionId != -1){
            query.setInteger("professionId", professionId);
        }
        if(name != null){
            query.setString("name", "%"+name+"%");
        }
        if(pageSize != -1 && pageNo != -1) {
            query.setFirstResult((pageNo - 1) * pageSize);
            query.setMaxResults(pageSize);
        }
        query.setResultTransformer(Transformers.aliasToBean(AdministrativeClassVO.class));
        System.out.println("班级信息 dao 查询语句"+query.toString());
        return query.list();
    }
    
}
