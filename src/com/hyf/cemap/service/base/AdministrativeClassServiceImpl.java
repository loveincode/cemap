package com.hyf.cemap.service.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hyf.cemap.bean.po.base.AdministrativeClass;
import com.hyf.cemap.bean.vo.AdministrativeClassVO;
import com.hyf.cemap.dao.base.AdministrativeClassDaoImpl;
import com.hyf.cemap.util.persistence.impl.BaseServiceImpl;

/**
 * 行政班级Service
*  @author  huyifan
*  @ClassName  AdministrativeClassServiceImpl  
*  @date  Mar 21, 2017 10:21:49 AM
 */
@Service("administrativeClassService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class AdministrativeClassServiceImpl extends BaseServiceImpl<AdministrativeClass>{

    @Autowired
    private AdministrativeClassDaoImpl administrativeClassDao;
    
    public List<AdministrativeClass> listByClassIds(List<Integer> classIds){
        return administrativeClassDao.listByClassIds(classIds);
    }
    
    public List<AdministrativeClass> listByProfessionId(Integer professionId){
        return administrativeClassDao.listByProfessionId(professionId);
    }
    
    public List<AdministrativeClassVO> listAllAdministrativeClassVO(Integer pageNo, Integer pageSize) {
        return administrativeClassDao.listAllAdministrativeClassVO(pageNo, pageSize);
    }
    
    public List<AdministrativeClassVO> listAdministrativeClassVOByInfos(Integer collegeId,Integer professionId,String name,Integer pageNo, Integer pageSize) {
        return administrativeClassDao.listAdministrativeClassVOByInfos(collegeId, professionId, name, pageNo, pageSize);
    }
    
}

