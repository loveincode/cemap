package com.hyf.cemap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyf.cemap.bean.po.EmploymentInformation;
import com.hyf.cemap.dao.EmploymentInformationDaoImpl;
import com.hyf.cemap.util.persistence.impl.BaseServiceImpl;

@Service("employmentInformationService")
public class EmploymentInformationServiceImpl extends BaseServiceImpl<EmploymentInformation>{
    
    @Autowired
    private EmploymentInformationDaoImpl employmentInformationDao;
    
    public EmploymentInformation getByStudentId(String studentId) {
        return employmentInformationDao.getByStudentId(studentId);
    }
    
}
