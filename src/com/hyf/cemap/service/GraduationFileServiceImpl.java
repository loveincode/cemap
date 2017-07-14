package com.hyf.cemap.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyf.cemap.bean.po.GraduationFile;
import com.hyf.cemap.dao.GraduationFileDaoImpl;
import com.hyf.cemap.util.persistence.impl.BaseServiceImpl;

@Service("graduationFileService")
public class GraduationFileServiceImpl extends BaseServiceImpl<GraduationFile> {
    
    @Autowired
    private GraduationFileDaoImpl graduationFileDao;
    
    public GraduationFile getByStudentId(String studentId) {
        return graduationFileDao.getByStudentId(studentId);
    }
    
}
