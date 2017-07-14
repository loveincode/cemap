package com.hyf.cemap.dao.bi;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository("bIDateDao")
@Transactional(propagation = Propagation.REQUIRED)
public class BIDateDaoImpl{
    
    @Autowired
    private SessionFactory sessionFactory;
    
    /**
     *   select count(*) from t_graduateinformation g 
     *      where g.graduationSession = 2017 and g.sex ='男'
     * @return
     */
    public Integer countall(){
        Query query = null;
        String sql = "";
        String sqltemp = "select count(*) from t_graduateinformation g"
                + " where g.deleted = 0"
                + " graduationSession = '2017'";
        
        query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        return ((Number)query.uniqueResult()).intValue();
    }
    
    /**
     * 获取现在系统中存在的毕业届数 
     * @return
     */
    public List<String> findAllSession(){
        Query query = null;
        String sql = "select distinct(a.graduationSession) from t_graduateinformation a"
                + " where a.deleted = false";
        query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        return query.list();
    }
    
    /**
     * 计算人数分布 男女人数
     * @param graduationSession
     * @param educationBackground
     * @param sex
     * @param collegeId
     * @param professionId
     * @param administrativeClassId
     * @return
     */
    public Integer countallByinfos(String graduationSession,String educationBackground,String sex,Integer collegeId,Integer professionId,Integer administrativeClassId){
        Query query = null;
        String sql = "";
        String sqltemp = "select count(*) from t_graduateinformation a"
                + " where a.deleted = 0 ";
        String sqlgraduationSession = "";
        String sqleducationBackground = "";
        String sqlsex = "";
        String sqlcollegeId = "";
        String sqlprofessionId = "";
        String sqladministrativeClassId = "";
        if(graduationSession != null){
            sqlgraduationSession = " and a.graduationSession =:graduationSession";
        }
        if(educationBackground != null){
            sqleducationBackground = " and a.educationBackground =:educationBackground";
        }
        if(sex != null){
            sqlsex = " and a.sex =:sex";
        }
        if(collegeId != -1){
            sqlcollegeId = " and a.collegeId=:collegeId";
        }
        if(professionId != -1){
            sqlprofessionId = " and a.professionId=:professionId";
        }
        if(administrativeClassId != -1){
            sqladministrativeClassId = " and a.administrativeClassId=:administrativeClassId";
        }
        sql = sqltemp + sqlgraduationSession + sqleducationBackground + sqlsex + sqlcollegeId + sqlprofessionId + sqladministrativeClassId;
        query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        if(graduationSession != null){
            query.setString("graduationSession", graduationSession);
        }
        if(educationBackground != null){
            query.setString("educationBackground", educationBackground);
        }
        if(sex != null){
            query.setString("sex", sex);
        }
        if(collegeId != -1){
            query.setInteger("collegeId", collegeId);
        }
        if(professionId != -1){
            query.setInteger("professionId", professionId);
        }
        if(administrativeClassId != -1){
            query.setInteger("administrativeClassId", administrativeClassId);
        }
        return ((Number)query.uniqueResult()).intValue();
    }
    
    /**
     * 计算就业人数 未就业人数
     * @param graduationSession
     * @param collegeId
     * @param professionId
     * @param administrativeClassId
     * @return
     */
    public Integer countemployByinfos(String graduationSession,Integer collegeId,Integer professionId,Integer administrativeClassId){
        Query query = null;
        String sql = "";
        String sqltemp = "select count(*) from t_graduateinformation a"
                + " where a.deleted = 0 ";
        String sqlgraduationSession = "";
        String sqlcollegeId = "";
        String sqlprofessionId = "";
        String sqladministrativeClassId = "";
        if(graduationSession != null){
            sqlgraduationSession = " and a.graduationSession =:graduationSession";
        }
        if(collegeId != -1){
            sqlcollegeId = " and a.collegeId=:collegeId";
        }
        if(professionId != -1){
            sqlprofessionId = " and a.professionId=:professionId";
        }
        if(administrativeClassId != -1){
            sqladministrativeClassId = " and a.administrativeClassId=:administrativeClassId";
        }
        String sqlemploy = " and a.studentId in (select b.studentId from t_employmentinformation b)";
        sql = sqltemp + sqlgraduationSession + sqlcollegeId + sqlprofessionId + sqladministrativeClassId + sqlemploy;
        query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        if(graduationSession != null){
            query.setString("graduationSession", graduationSession);
        }
        if(collegeId != -1){
            query.setInteger("collegeId", collegeId);
        }
        if(professionId != -1){
            query.setInteger("professionId", professionId);
        }
        if(administrativeClassId != -1){
            query.setInteger("administrativeClassId", administrativeClassId);
        }
        return ((Number)query.uniqueResult()).intValue();
    }
    
    // 统计就业方式人数
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> countMethodByinfos(String graduationSession,Integer collegeId,Integer professionId,Integer administrativeClassId){
        Query query = null;
        String sql = "";
        String sqltemp = "select e.employmentMethod as method,count(*) as counts from t_graduateinformation a"
                + " ,t_employmentinformation e where "
                + " a.studentId = e.studentId"
                + " and a.deleted = 0 ";
        String sqlgraduationSession = "";
        String sqlcollegeId = "";
        String sqlprofessionId = "";
        String sqladministrativeClassId = "";
        if(graduationSession != null){
            sqlgraduationSession = " and a.graduationSession =:graduationSession";
        }
        if(collegeId != -1){
            sqlcollegeId = " and a.collegeId=:collegeId";
        }
        if(professionId != -1){
            sqlprofessionId = " and a.professionId=:professionId";
        }
        if(administrativeClassId != -1){
            sqladministrativeClassId = " and a.administrativeClassId=:administrativeClassId";
        }
        String sqlemthod = " GROUP BY e.employmentMethod ";
        sql = sqltemp + sqlgraduationSession + sqlcollegeId + sqlprofessionId + sqladministrativeClassId + sqlemthod;
        query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        if(graduationSession != null){
            query.setString("graduationSession", graduationSession);
        }
        if(collegeId != -1){
            query.setInteger("collegeId", collegeId);
        }
        if(professionId != -1){
            query.setInteger("professionId", professionId);
        }
        if(administrativeClassId != -1){
            query.setInteger("administrativeClassId", administrativeClassId);
        }
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return query.list();
    }
    
    // 统计单位性质方式人数
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> countNatureByinfos(String graduationSession,Integer collegeId,Integer professionId,Integer administrativeClassId){
        Query query = null;
        String sql = "";
        String sqltemp = "select e.nature as method,count(*) as counts from t_graduateinformation a"
                + " ,t_employmentinformation e where "
                + " a.studentId = e.studentId"
                + " and a.deleted = 0 "
                + " and e.employmentMethod in ('签就业协议书','签合同')";
        String sqlgraduationSession = "";
        String sqlcollegeId = "";
        String sqlprofessionId = "";
        String sqladministrativeClassId = "";
        if(graduationSession != null){
            sqlgraduationSession = " and a.graduationSession =:graduationSession";
        }
        if(collegeId != -1){
            sqlcollegeId = " and a.collegeId=:collegeId";
        }
        if(professionId != -1){
            sqlprofessionId = " and a.professionId=:professionId";
        }
        if(administrativeClassId != -1){
            sqladministrativeClassId = " and a.administrativeClassId=:administrativeClassId";
        }
        String sqlnature = " GROUP BY e.nature ";
        sql = sqltemp + sqlgraduationSession + sqlcollegeId + sqlprofessionId + sqladministrativeClassId + sqlnature;
        query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        if(graduationSession != null){
            query.setString("graduationSession", graduationSession);
        }
        if(collegeId != -1){
            query.setInteger("collegeId", collegeId);
        }
        if(professionId != -1){
            query.setInteger("professionId", professionId);
        }
        if(administrativeClassId != -1){
            query.setInteger("administrativeClassId", administrativeClassId);
        }
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return query.list();
    }
    
    // 统计行业类型方式人数
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> countIndustryByinfos(String graduationSession,Integer collegeId,Integer professionId,Integer administrativeClassId){
        Query query = null;
        String sql = "";
        String sqltemp = "select e.industryType as method,count(*) as counts from t_graduateinformation a"
                + " ,t_employmentinformation e where "
                + " a.studentId = e.studentId"
                + " and a.deleted = 0 "
                + " and e.employmentMethod in ('签就业协议书','签合同')";
        String sqlgraduationSession = "";
        String sqlcollegeId = "";
        String sqlprofessionId = "";
        String sqladministrativeClassId = "";
        if(graduationSession != null){
            sqlgraduationSession = " and a.graduationSession =:graduationSession";
        }
        if(collegeId != -1){
            sqlcollegeId = " and a.collegeId=:collegeId";
        }
        if(professionId != -1){
            sqlprofessionId = " and a.professionId=:professionId";
        }
        if(administrativeClassId != -1){
            sqladministrativeClassId = " and a.administrativeClassId=:administrativeClassId";
        }
        String sqlindustryType = " GROUP BY e.industryType ";
        sql = sqltemp + sqlgraduationSession + sqlcollegeId + sqlprofessionId + sqladministrativeClassId + sqlindustryType;
        query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        if(graduationSession != null){
            query.setString("graduationSession", graduationSession);
        }
        if(collegeId != -1){
            query.setInteger("collegeId", collegeId);
        }
        if(professionId != -1){
            query.setInteger("professionId", professionId);
        }
        if(administrativeClassId != -1){
            query.setInteger("administrativeClassId", administrativeClassId);
        }
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return query.list();
    }
    
    //单位前十
    @SuppressWarnings("unchecked")
    public List<Map<String, Object>> countCompanyNameByinfos(String graduationSession,Integer collegeId,Integer professionId,Integer administrativeClassId){
        Query query = null;
        String sql = "";
        String sqltemp = "select e.companyFullName as method,count(*) as counts from t_graduateinformation a"
                + " ,t_employmentinformation e where "
                + " a.studentId = e.studentId"
                + " and a.deleted = 0 "
                + " and e.employmentMethod in ('签就业协议书','签合同')";
        String sqlgraduationSession = "";
        String sqlcollegeId = "";
        String sqlprofessionId = "";
        String sqladministrativeClassId = "";
        if(graduationSession != null){
            sqlgraduationSession = " and a.graduationSession =:graduationSession";
        }
        if(collegeId != -1){
            sqlcollegeId = " and a.collegeId=:collegeId";
        }
        if(professionId != -1){
            sqlprofessionId = " and a.professionId=:professionId";
        }
        if(administrativeClassId != -1){
            sqladministrativeClassId = " and a.administrativeClassId=:administrativeClassId";
        }
        String sqlindustryType = " GROUP BY e.companyFullName order by counts asc ";
        sql = sqltemp + sqlgraduationSession + sqlcollegeId + sqlprofessionId + sqladministrativeClassId + sqlindustryType;
        query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        if(graduationSession != null){
            query.setString("graduationSession", graduationSession);
        }
        if(collegeId != -1){
            query.setInteger("collegeId", collegeId);
        }
        if(professionId != -1){
            query.setInteger("professionId", professionId);
        }
        if(administrativeClassId != -1){
            query.setInteger("administrativeClassId", administrativeClassId);
        }
        query.setMaxResults(10);
        query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return query.list();
    }
    
    /**
     * 计算薪资区间
     * @param graduationSession
     * @param collegeId
     * @param professionId
     * @param administrativeClassId
     * @return
     */
    public Integer countPayByinfos(Integer startPay,Integer endPay,String graduationSession,Integer collegeId,Integer professionId,Integer administrativeClassId){
        Query query = null;
        String sql = "";
        String sqltemp = "select count(*) from t_graduateinformation a "
                + " ,t_employmentinformation e where"
                + " a.deleted = 0 "
                + " and a.studentId = e.studentId "
                + " and e.employmentPay*13 >:startPay"
                + " and e.employmentMethod in ('签就业协议书','签合同')";
        String sqlgraduationSession = "";
        String sqlcollegeId = "";
        String sqlprofessionId = "";
        String sqladministrativeClassId = "";
        String sqlendPay = "";
        if(graduationSession != null){
            sqlgraduationSession = " and a.graduationSession =:graduationSession";
        }
        if(collegeId != -1){
            sqlcollegeId = " and a.collegeId=:collegeId";
        }
        if(professionId != -1){
            sqlprofessionId = " and a.professionId=:professionId";
        }
        if(administrativeClassId != -1){
            sqladministrativeClassId = " and a.administrativeClassId=:administrativeClassId";
        }
        if(endPay != -1){
            sqlendPay = " and e.employmentPay*13 <:endPay";
        }
        sql = sqltemp + sqlgraduationSession + sqlcollegeId + sqlprofessionId + sqladministrativeClassId + sqlendPay;
        query = sessionFactory.getCurrentSession().createSQLQuery(sql);
        query.setInteger("startPay", startPay);
        if(graduationSession != null){
            query.setString("graduationSession", graduationSession);
        }
        if(collegeId != -1){
            query.setInteger("collegeId", collegeId);
        }
        if(professionId != -1){
            query.setInteger("professionId", professionId);
        }
        if(administrativeClassId != -1){
            query.setInteger("administrativeClassId", administrativeClassId);
        }
        if(endPay != -1){
            query.setInteger("endPay", endPay);
        }
        return ((Number)query.uniqueResult()).intValue();
    }
    
}
