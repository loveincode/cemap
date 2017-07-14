package com.hyf.cemap.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hyf.cemap.bean.po.GraduationFile;
import com.hyf.cemap.bean.security.Member;
import com.hyf.cemap.util.persistence.impl.BaseDAOImpl;

@Repository("memberDao")
public class MemberDaoImpl extends BaseDAOImpl<Member>{
    
    @Autowired
    private SessionFactory sessionFactory;
    
    @SuppressWarnings("unchecked")
    public Member getByUsername(String studentId) {
        String sql = "from Member as model where model.deleted=0 and model.username='" + studentId+"'";
        Query query = this.getSessionFactory().getCurrentSession()
                .createQuery(sql);
        List<Member> instances = query.list();
        if (instances != null && !instances.isEmpty()) {
            return instances.get(0);
        }
        return null;
    }
    
    /**
     * 分页查询 member
     * @param username
     * @param realname
     * @param tempRoleId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Member> listMemberByinfo(String username,String realname,Integer tempRoleId,Integer pageNo,Integer pageSize) {
        Query query = null;
        String sql = "";
        String sqltmp = "FROM Member WHERE deleted = false";
        String sqlusername = "";
        String sqlrealname = "";
        String sqltempRoleId ="";
        
        if (username != null) {
            sqlusername = " and username like :username";
        }
        if (realname != null) {
            sqlrealname = " and realname like :realname";
        }
        if (tempRoleId != -1){
            sqltempRoleId = " and tempRoleId = :tempRoleId";
        }
        sql = sqltmp  + sqlusername + sqlrealname + sqltempRoleId +" Order by id";
        query = sessionFactory.getCurrentSession().createQuery(sql);
        if (username != null) {
            query.setString("username", "%" + username + "%");
        }
        if (realname != null) {
            query.setString("realname", "%" + realname + "%");
        }
        if (tempRoleId != -1){
            query.setInteger("tempRoleId", tempRoleId);
        }
        if (pageSize != -1 && pageNo != -1) {
            query.setFirstResult((pageNo - 1) * pageSize);
            query.setMaxResults(pageSize);
        }
        return query.list();
    }
    
    /**
     * 分页查询 member
     * @param username
     * @param realname
     * @param tempRoleId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<Member> listMemberByinfo2(String username,String realname,List<Integer> classIds,Integer administrativeClassId,Integer tempRoleId,Integer pageNo,Integer pageSize) {
        Query query = null;
        String sql = "";
        String sqltmp = "FROM Member WHERE deleted = false";
        String sqlusername = "";
        String sqlrealname = "";
        String sqlclassIds = "";
        String sqltempRoleId ="";
        String sqladministrativeClassId = "";
        
        if (username != null) {
            sqlusername = " and username like :username";
        }
        if (realname != null) {
            sqlrealname = " and realname like :realname";
        }
        if (tempRoleId != -1){
            sqltempRoleId = " and tempRoleId = :tempRoleId";
        }
        sqlclassIds = " and classId in :classIds";
        if(administrativeClassId != -1){
            sqladministrativeClassId = " and classId=:administrativeClassId";
        }
        sql = sqltmp  + sqlusername + sqlrealname + sqltempRoleId + sqlclassIds+sqladministrativeClassId+" Order by id";
        query = sessionFactory.getCurrentSession().createQuery(sql);
        if (username != null) {
            query.setString("username", "%" + username + "%");
        }
        if (realname != null) {
            query.setString("realname", "%" + realname + "%");
        }
        if (tempRoleId != -1){
            query.setInteger("tempRoleId", tempRoleId);
        }
        query.setParameterList("classIds", classIds);
        if(administrativeClassId != -1){
            query.setInteger("administrativeClassId", administrativeClassId);
        }
        if (pageSize != -1 && pageNo != -1) {
            query.setFirstResult((pageNo - 1) * pageSize);
            query.setMaxResults(pageSize);
        }
        return query.list();
    }
    
}
