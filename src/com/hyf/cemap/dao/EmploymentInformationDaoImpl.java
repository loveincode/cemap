package com.hyf.cemap.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hyf.cemap.bean.po.EmploymentInformation;
import com.hyf.cemap.bean.vo.EmploymentInformationVO;
import com.hyf.cemap.bean.vo.GraduateInformationVO;
import com.hyf.cemap.util.persistence.impl.BaseDAOImpl;

@Repository("employmentInformationDao")
public class EmploymentInformationDaoImpl extends BaseDAOImpl<EmploymentInformation>{
    
    @Autowired
    private SessionFactory sessionFactory;
    
    @SuppressWarnings("unchecked")
    public EmploymentInformation getByStudentId(String studentId) {
        String sql = "from EmploymentInformation as model where model.deleted=0 and model.studentId='" + studentId+"'";
        Query query = this.getSessionFactory().getCurrentSession()
                .createQuery(sql);
        List<EmploymentInformation> instances = query.list();
        if (instances != null && !instances.isEmpty()) {
            return instances.get(0);
        }
        return null;
    }
    
    /**
     * 
     * @param collegeId
     * @param professionId
     * @param administrativeClassId
     * @param name
     * @param studentId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<EmploymentInformationVO> listEmploymentInformationVOByInfos(Integer collegeId,Integer professionId,Integer administrativeClassId,String name,String studentId,Integer pageNo, Integer pageSize) {
        String sql = "";
        String sqltmp =  "SELECT"+
        " a.id,a.Uuid,a.studentId,b.name as administrativeClassName"+
        " FROM"+
        " t_employmentinformation a"+
        " LEFT JOIN t_graduateinformation b ON a.studentId = b.studentId"+
        " WHERE"+
        " a.deleted = 0";
        String sqlcollegeId = "";
        String sqlprofessionId = "";
        String sqladministrativeClassId = "";
        String sqlname = "";
        String sqlstudentId = "";
        if(collegeId != -1){
            sqlcollegeId = " and a.collegeId=:collegeId";
        }
        if(professionId != -1){
            sqlprofessionId = " and a.professionId=:professionId";
        }
        if(administrativeClassId != -1){
            sqladministrativeClassId = " and a.administrativeClassId=:administrativeClassId";
        }
        if(name != null){
            sqlname = " and a.name like :name";
        }
        if(studentId != null){
            sqlstudentId = " and a.studentId =:studentId";
        }
        sql = sqltmp + sqlcollegeId + sqlprofessionId + sqladministrativeClassId + sqlname + sqlstudentId;
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery(sql);
        if(collegeId != -1){
            query.setInteger("collegeId", collegeId);
        }
        if(professionId != -1){
            query.setInteger("professionId", professionId);
        }
        if(administrativeClassId != -1){
            query.setInteger("administrativeClassId", administrativeClassId);
        }
        if(name != null){
            query.setString("name", "%"+name+"%");
        }
        if(studentId != null){
            query.setString("studentId", studentId);
        }
        if(pageSize != -1 && pageNo != -1) {
            query.setFirstResult((pageNo - 1) * pageSize);
            query.setMaxResults(pageSize);
        }
        query.setResultTransformer(Transformers.aliasToBean(GraduateInformationVO.class));
        System.out.println("毕业生信息 dao 查询语句"+query.toString());
        return query.list();
    }
    
}
