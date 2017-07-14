package com.hyf.cemap.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hyf.cemap.bean.po.RecruitmentInformation;
import com.hyf.cemap.dao.RecruitmentInformationDaoImpl;
import com.hyf.cemap.util.persistence.impl.BaseServiceImpl;

@Service("recruitmentInformationService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class RecruitmentInformationServiceImpl extends BaseServiceImpl<RecruitmentInformation>{
    
    @Autowired
    private RecruitmentInformationDaoImpl recruitmentInformationDao;
    
    public List<RecruitmentInformation> listRecruitmentInformationByinfo(String companyName,String companyType,Integer pageNo,Integer pageSize) {
        return recruitmentInformationDao.listRecruitmentInformationByinfo(companyName, companyType, pageNo, pageSize);
    }
    
    public List<RecruitmentInformation> flistRecruitmentInformationByinfo(String companyName,String companyType,Integer pageNo,Integer pageSize) {
        return recruitmentInformationDao.flistRecruitmentInformationByinfo(companyName, companyType, pageNo, pageSize);
    }
    
    public List<RecruitmentInformation> listSixRecruitmentInformation() {
        return recruitmentInformationDao.listSixRecruitmentInformation();
    }
    
}
