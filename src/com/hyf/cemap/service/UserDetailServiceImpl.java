package com.hyf.cemap.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hyf.cemap.bean.security.Member;
import com.hyf.cemap.bean.security.Role;
import com.hyf.cemap.dao.UserDao;

/**
 * 该类用户从数据库获取用户信息和用户权限信息 提供给spring-security使用
 *
 */
@Service("userDetailServiceImpl")
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
    private UserDao userDao ;  

	public UserDetails loadUserByUsername(String username)  
            throws UsernameNotFoundException {  
	    //认证
        Member member = userDao.findUserByUserName(username);  
        UserDetails user = null ;  
        if(member != null){  
            user = new User(username, member.getPassword(), true,  
                    true ,   
                    true,  
                    true,findUserAuthorities(member) );  
        }  
          
        return user;
    }  
  
    /** 
     * 获取用户的权限 
     * @param user 
     * @return 
     */  
    @SuppressWarnings("deprecation")  
    public Collection<GrantedAuthority> findUserAuthorities(Member member){  
        List<GrantedAuthority> autthorities = new ArrayList<GrantedAuthority>();  
        Set<Role> roles =  member.getRoles();  
        for (Role etRole : roles) {  
            autthorities.add(new GrantedAuthorityImpl(etRole.getCode()));  
        }  
        return autthorities ;  
    }
    
    /**
     * 通过用户名获取到用户的信息
     * @param username
     * @return
     */
    public Member findUserByUsername(String username){
    	return userDao.findUserByUserName(username);
    }
    
    /**
     * 根据手机号查询用户信息
     */
    public Member findUserByMobile(String mobile){
    	return userDao.findUserByMobile(mobile);
    }
    
    /**
     * 根据用户角色进行查询
     */
    public List<Member> findUserByRole(String roleCode){
    	return userDao.findUserByRole(roleCode);
    }
    
    /**
     * 根据id查找用户
     */
    public Member findUserById(int id){
    	return userDao.findUserById(id);
    }
    
    /**
     * 更新用户信息
     * @param member
     */
    public void updateUser(Member member){
    	userDao.updateUser(member);
    }
    

}