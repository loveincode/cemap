package com.hyf.cemap.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hyf.cemap.bean.po.GraduateInformation;
import com.hyf.cemap.bean.po.base.AdministrativeClass;
import com.hyf.cemap.bean.vo.GraduateInformationVO;
import com.hyf.cemap.util.persistence.impl.BaseDAOImpl;

@Repository("graduateInformationDao")
public class GraduateInformationDaoImpl extends BaseDAOImpl<GraduateInformation>{
	
	@Autowired
    private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public GraduateInformation getByStudentId(String studentId) {
        String sql = "from GraduateInformation as model where model.deleted=0 and model.studentId='" + studentId+"'";
        Query query = this.getSessionFactory().getCurrentSession()
                .createQuery(sql);
        List<GraduateInformation> instances = query.list();
        if (instances != null && !instances.isEmpty()) {
            return instances.get(0);
        }
        return null;
    }
	
	 /**
     * 通过classid查询出专业下面的所有班级
     * @param classid
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<GraduateInformation> listByClassId(Integer classid){
        String sql = "from GraduateInformation as a where a.deleted=0  and a.administrativeClassId = :classid";
        Query query = this.getSessionFactory().getCurrentSession()
                .createQuery(sql);
        query.setInteger("classid", classid);
        List<GraduateInformation> instances = query.list();
        return instances;
    }
	
	/**
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings("unchecked")
    public List<GraduateInformationVO> listAllGraduateInformationVO(Integer pageNo, Integer pageSize) {
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery(
                "SELECT"+
                " a.id,a.Uuid,a.studentId,a.name,a.phone,a.educationBackground,b.name as administrativeClassName"+
                " FROM"+
                " t_graduateinformation a"+
                " LEFT JOIN t_administrativeclass b ON a.administrativeClassId = b.id"+
                " WHERE"+
                " a.deleted = 0");
        if(pageSize != -1 && pageNo != -1) {
            query.setFirstResult((pageNo - 1) * pageSize);
            query.setMaxResults(pageSize);
        }
        query.setResultTransformer(Transformers.aliasToBean(GraduateInformationVO.class));
        return query.list();
    }
	
	/**
	 * 综合查询
	 * @param collegeId
	 * @param professionId
	 * @param administrativeClassId
	 * @param name
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings("unchecked")
    public List<GraduateInformationVO> listGraduateInformationVOByInfos(Integer collegeId,Integer professionId,Integer administrativeClassId,String name,String studentId,Integer pageNo, Integer pageSize) {
	    String sql = "";
	    String sqltmp =  "SELECT"+
        " a.id,a.Uuid,a.studentId,a.name,a.phone,a.educationBackground,b.name as administrativeClassName"+
        " FROM"+
        " t_graduateinformation a"+
        " LEFT JOIN t_administrativeclass b ON a.administrativeClassId = b.id"+
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
        return query.list();
    }
	
	/**
     * 综合查询_f
     * @param collegeId
     * @param professionId
     * @param administrativeClassId
     * @param name
     * @param pageNo
     * @param pageSize
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<GraduateInformationVO> listGraduateInformationVOByInfos_f(List<Integer> classIds,Integer administrativeClassId,String name,String studentId,Integer pageNo, Integer pageSize) {
        String sql = "";
        String sqltmp =  "SELECT"+
        " a.id,a.Uuid,a.studentId,a.name,a.phone,a.educationBackground,b.name as administrativeClassName"+
        " FROM"+
        " t_graduateinformation a"+
        " LEFT JOIN t_administrativeclass b ON a.administrativeClassId = b.id"+
        " WHERE"+
        " a.deleted = 0";
        String sqlclassIds = "";
        String sqladministrativeClassId = "";
        String sqlname = "";
        String sqlstudentId = "";
        sqlclassIds = " and a.administrativeClassId in :classIds";
        if(administrativeClassId != -1){
            sqladministrativeClassId = " and a.administrativeClassId=:administrativeClassId";
        }
        if(name != null){
            sqlname = " and a.name like :name";
        }
        if(studentId != null){
            sqlstudentId = " and a.studentId =:studentId";
        }
        sql = sqltmp + sqlclassIds + sqladministrativeClassId + sqlname + sqlstudentId;
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery(sql);
        query.setParameterList("classIds", classIds);
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
        return query.list();
    }
	
	/**
	 * 查询没有填写就业信息的毕业生信息
	 * @param collegeId
	 * @param professionId
	 * @param administrativeClassId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
    @SuppressWarnings("unchecked")
    public List<GraduateInformationVO> listGraduateInformationVOByInfos2(Integer collegeId,Integer professionId,Integer administrativeClassId,Integer pageNo, Integer pageSize) {
        String sql = "";
        String sqltmp =  "SELECT"+
        " a.id,a.Uuid,a.studentId,a.name,a.phone,a.educationBackground,b.name as administrativeClassName"+
        " FROM"+
        " t_graduateinformation a"+
        " LEFT JOIN t_administrativeclass b ON a.administrativeClassId = b.id"+
        " WHERE"+
        " a.deleted = 0"+
        " and a.studentId not in"+
        " (select e.studentId from t_employmentinformation e)";
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
        if(pageSize != -1 && pageNo != -1) {
            query.setFirstResult((pageNo - 1) * pageSize);
            query.setMaxResults(pageSize);
        }
        query.setResultTransformer(Transformers.aliasToBean(GraduateInformationVO.class));
        System.out.println("毕业生信息 dao 查询语句"+query.toString());
        return query.list();
    }
    
    /**
     * 查询没有填写就业信息的毕业生信息_f
     * @param collegeId
     * @param professionId
     * @param administrativeClassId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<GraduateInformationVO> listGraduateInformationVOByInfos2_f(List<Integer> classIds,Integer administrativeClassId,Integer pageNo, Integer pageSize) {
        String sql = "";
        String sqltmp =  "SELECT"+
        " a.id,a.Uuid,a.studentId,a.name,a.phone,a.educationBackground,b.name as administrativeClassName"+
        " FROM"+
        " t_graduateinformation a"+
        " LEFT JOIN t_administrativeclass b ON a.administrativeClassId = b.id"+
        " WHERE"+
        " a.deleted = 0"+
        " and a.studentId not in"+
        " (select e.studentId from t_employmentinformation e)";
        String sqlclassIds = " and a.administrativeClassId in :classIds";
        String sqladministrativeClassId = "";
        String sqlname = "";
        String sqlstudentId = "";
        if(administrativeClassId != -1){
            sqladministrativeClassId = " and a.administrativeClassId=:administrativeClassId";
        }
        sql = sqltmp + sqlclassIds + sqladministrativeClassId + sqlname + sqlstudentId;
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery(sql);
        query.setParameterList("classIds", classIds);
        if(administrativeClassId != -1){
            query.setInteger("administrativeClassId", administrativeClassId);
        }
        if(pageSize != -1 && pageNo != -1) {
            query.setFirstResult((pageNo - 1) * pageSize);
            query.setMaxResults(pageSize);
        }
        query.setResultTransformer(Transformers.aliasToBean(GraduateInformationVO.class));
        System.out.println("毕业生信息 dao 查询语句"+query.toString());
        return query.list();
    }
    
    /**
     * 查询没有填写档案信息的毕业生信息
     * @param collegeId
     * @param professionId
     * @param administrativeClassId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<GraduateInformationVO> listGraduateInformationVOByInfos3(Integer collegeId,Integer professionId,Integer administrativeClassId,Integer pageNo, Integer pageSize) {
        String sql = "";
        String sqltmp =  "SELECT"+
        " a.id,a.Uuid,a.studentId,a.name,a.phone,a.educationBackground,b.name as administrativeClassName"+
        " FROM"+
        " t_graduateinformation a"+
        " LEFT JOIN t_administrativeclass b ON a.administrativeClassId = b.id"+
        " WHERE"+
        " a.deleted = 0"+
        " and a.studentId not in"+
        " (select e.studentId from t_graduationfile e)";
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
        if(pageSize != -1 && pageNo != -1) {
            query.setFirstResult((pageNo - 1) * pageSize);
            query.setMaxResults(pageSize);
        }
        query.setResultTransformer(Transformers.aliasToBean(GraduateInformationVO.class));
        System.out.println("毕业生信息 dao 查询语句"+query.toString());
        return query.list();
    }
    
    /**
     * 查询没有填写档案信息的毕业生信息_f
     * @param collegeId
     * @param professionId
     * @param administrativeClassId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<GraduateInformationVO> listGraduateInformationVOByInfos3_f(List<Integer> classIds,Integer administrativeClassId,Integer pageNo, Integer pageSize) {
        String sql = "";
        String sqltmp =  "SELECT"+
        " a.id,a.Uuid,a.studentId,a.name,a.phone,a.educationBackground,b.name as administrativeClassName"+
        " FROM"+
        " t_graduateinformation a"+
        " LEFT JOIN t_administrativeclass b ON a.administrativeClassId = b.id"+
        " WHERE"+
        " a.deleted = 0"+
        " and a.studentId not in"+
        " (select e.studentId from t_graduationfile e)";
        String sqlclassIds = " and a.administrativeClassId in :classIds";
        String sqladministrativeClassId = "";
        String sqlname = "";
        String sqlstudentId = "";
        if(administrativeClassId != -1){
            sqladministrativeClassId = " and a.administrativeClassId=:administrativeClassId";
        }
        sql = sqltmp + sqlclassIds + sqladministrativeClassId + sqlname + sqlstudentId;
        Query query = this.sessionFactory.getCurrentSession().createSQLQuery(sql);
        query.setParameterList("classIds", classIds);
        if(administrativeClassId != -1){
            query.setInteger("administrativeClassId", administrativeClassId);
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
