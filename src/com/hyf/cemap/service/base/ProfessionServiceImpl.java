package com.hyf.cemap.service.base;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hyf.cemap.bean.po.base.Profession;
import com.hyf.cemap.bean.vo.ProfessionVO;
import com.hyf.cemap.dao.base.ProfessionDaoImpl;
import com.hyf.cemap.util.persistence.impl.BaseServiceImpl;

/**
 * 专业Service
*  @author  huyifan
*  @ClassName  ProfessionServiceImpl  
*  @date  Mar 21, 2017 10:22:17 AM
 */
@Service("professionService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class ProfessionServiceImpl extends BaseServiceImpl<Profession>{

    @Autowired
    private ProfessionDaoImpl professionDao;
    
    public List<Profession> listByCollegeId(Integer collegeId){
        return professionDao.listByCollegeId(collegeId);
    }
    
    public List<ProfessionVO> listAllProfessionVO(Integer pageNo, Integer pageSize){
        return professionDao.listAllProfessionVO(pageNo, pageSize);
    }
    
    public List<ProfessionVO> listProfessionVOByInfos(Integer collegeId,String name,Integer pageNo, Integer pageSize) {
        return professionDao.listProfessionVOByInfos(collegeId, name, pageNo, pageSize);
    }
    
}
