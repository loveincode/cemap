package com.hyf.cemap.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hyf.cemap.bean.po.PreachingMeeting;
import com.hyf.cemap.dao.PreachingMeetingDaoImpl;
import com.hyf.cemap.util.persistence.impl.BaseServiceImpl;

@Service("preachingMeetingService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class PreachingMeetingServiceImpl extends BaseServiceImpl<PreachingMeeting> {
    
    @Autowired
    private PreachingMeetingDaoImpl preachingMeetingDao;
    
    public List<PreachingMeeting> listPreachingMeetingByinfo(String companyName,String companyType,Integer pageNo,Integer pageSize) {
        return preachingMeetingDao.listPreachingMeetingByinfo(companyName, companyType, pageNo, pageSize);
    }
    
    public List<PreachingMeeting> flistPreachingMeetingByinfo(String companyName,String companyType,Integer pageNo,Integer pageSize) {
        return preachingMeetingDao.flistPreachingMeetingByinfo(companyName, companyType, pageNo, pageSize);
    }
    
    public List<PreachingMeeting> listSixPreachingMeeting() {
        return preachingMeetingDao.listSixPreachingMeeting();
    }
    
}
