package com.hyf.cemap.service.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hyf.cemap.bean.po.base.College;
import com.hyf.cemap.dao.base.CollegeDaoImpl;
import com.hyf.cemap.util.persistence.impl.BaseServiceImpl;


/**
 * 学院Service
*  @author  huyifan
*  @ClassName  CollegeServiceImpl  
*  @date  Mar 21, 2017 10:22:03 AM
 */

@Service("collegeService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class CollegeServiceImpl extends BaseServiceImpl<College>{
    
    @Autowired
    private CollegeDaoImpl collegeDao;
    
    public List<College> listCollegeByinfo(String name,Integer pageNo,Integer pageSize) {
        return collegeDao.listCollegeByinfo(name, pageNo, pageSize);
    }
    
}
