package com.hyf.cemap.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hyf.cemap.bean.security.Member;
import com.hyf.cemap.bean.security.Role;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class UserDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	/** 
     * 根据用户名查询用户 
     * @param username 
     * @return 
     */  
    @SuppressWarnings("unchecked")  
    public Member findUserByUserName(String username){   
        List<Member> result =  this.sessionFactory.getCurrentSession().createQuery("from Member e where e.username = \'" + username + "\'").list();  
        if(result != null && !result.isEmpty())      
            return result.get(0);  
        return null ;  
    }  
    
    
    /**
     * 根据手机号查询用户
     */
    @SuppressWarnings("unchecked")  
    public Member findUserByMobile(String mobile){  
        List<Member> result =  this.sessionFactory.getCurrentSession().createQuery("from Member e where e.mobile = \'" + mobile + "\'").list();  
        if(result != null && !result.isEmpty())      
            return result.get(0);  
        return null ;  
    }
    
    /**
     * 根据id查找用户
     */
    @SuppressWarnings("unchecked")  
    public Member findUserById(int id){
        List<Member> result =  this.sessionFactory.getCurrentSession().createQuery("from Member e where e.id = " + id).list();  
        if(result != null && !result.isEmpty())      
            return result.get(0);  
        return null ;  
    }
    
    /**
     * 根据用户角色查找用户
     */
    @SuppressWarnings("unchecked")  
    public List<Member> findUserByRole(String roleCode){
    	List<Member> rolemembers = new ArrayList<Member>();
    	List<Member> allmembers =  this.sessionFactory.getCurrentSession().createQuery("from Member").list();
    	for(Member member: allmembers){
    		Set<Role> mRoles = member.getRoles();
    		Iterator<Role> it = mRoles.iterator();
    		while(it.hasNext()){
    			Role role = it.next();
    			if(role.getCode().equalsIgnoreCase(roleCode)){
    				rolemembers.add(member);
    				break;
    			}
    		}
    	}
    	return rolemembers;
    }
  
    /** 
     * 根据用户名和密码查询用户 
     * @param username 
     * @param password 
     * @return 
     */  
    @SuppressWarnings("unchecked")  
    public Member findUserBuNameAndPwd(String username , String password){  
        List<Member> result =  this.sessionFactory.getCurrentSession().createQuery("from Member e where e.username = \'" + username + "\' and e.password = \'" + password + "\'").list();  
        if(result != null && !result.isEmpty())    
            return result.get(0);  
        return null ;  
    } 
    
  
    /**
     * 注册用户
     * @param member
     */
    public void saveUser(Member member){
    	sessionFactory.getCurrentSession().saveOrUpdate(member);
    }
    
    /**
     * 更新用户信息
     * @param member
     */
    public void updateUser(Member member){
    	sessionFactory.getCurrentSession().update(member);
    	sessionFactory.getCurrentSession().flush();
    }
    
    /**
     * 查询角色
     * @param roleCode
     * @return
     */
    @SuppressWarnings("unchecked")  
    public Role findRoleByRoleCode(String roleCode){
    	List<Role> result = this.sessionFactory.getCurrentSession().createQuery("from Role e where e.code = \'" +  roleCode + "\'").list();
        if(result != null && !result.isEmpty())    
            return result.get(0);  
        return null ; 
    }

}