package com.hyf.cemap.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyf.cemap.bean.security.Member;
import com.hyf.cemap.dao.MemberDaoImpl;
import com.hyf.cemap.util.persistence.impl.BaseServiceImpl;

@Service("memberService")
public class MemberServiceImpl extends BaseServiceImpl<Member>{

    @Autowired
    private MemberDaoImpl memberDao;
    
    public Member getByUsername(String studentId) {
        return memberDao.getByUsername(studentId);
    }
    
    public List<Member> listMemberByinfo(String username,String realname,Integer tempRoleId,Integer pageNo,Integer pageSize) {
        return memberDao.listMemberByinfo(username, realname, tempRoleId, pageNo, pageSize);
    }
    
    public List<Member> listMemberByinfo2(String username,String realname,List<Integer> classIds,Integer administrativeClassId,Integer tempRoleId,Integer pageNo,Integer pageSize) {
        return memberDao.listMemberByinfo2(username, realname, classIds,administrativeClassId, tempRoleId, pageNo, pageSize);
    }
    
}

