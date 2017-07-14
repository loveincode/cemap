package com.hyf.cemap.service.bi;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyf.cemap.dao.bi.BIDateDaoImpl;

@Service("bIDateService")
public class BIDateServiceImpl {

    @Autowired 
    private BIDateDaoImpl bIDateDao;
    
    public int countall(){
        return bIDateDao.countall();
    }
    
    public List<String> findAllSession(){
        return bIDateDao.findAllSession();
    }
    
    public Integer countallByinfos(String graduationSession,String educationBackground,String sex,Integer collegeId,Integer professionId,Integer administrativeClassId){
        return bIDateDao.countallByinfos(graduationSession,educationBackground, sex, collegeId, professionId, administrativeClassId);
    }
    
    public Integer countemployByinfos(String graduationSession,Integer collegeId,Integer professionId,Integer administrativeClassId){
        return bIDateDao.countemployByinfos(graduationSession, collegeId, professionId, administrativeClassId);
    }
    
    public List<Map<String, Object>> countMethodByinfos(String graduationSession,Integer collegeId,Integer professionId,Integer administrativeClassId){
        return bIDateDao.countMethodByinfos(graduationSession, collegeId, professionId, administrativeClassId);
    }
    
    public List<Map<String, Object>> countNatureByinfos(String graduationSession,Integer collegeId,Integer professionId,Integer administrativeClassId){
        return bIDateDao.countNatureByinfos(graduationSession, collegeId, professionId, administrativeClassId);
    }
    
    public List<Map<String, Object>> countIndustryByinfos(String graduationSession,Integer collegeId,Integer professionId,Integer administrativeClassId){
        return bIDateDao.countIndustryByinfos(graduationSession, collegeId, professionId, administrativeClassId);
    }
    
    public List<Map<String, Object>> countCompanyNameByinfos(String graduationSession,Integer collegeId,Integer professionId,Integer administrativeClassId){
        return bIDateDao.countCompanyNameByinfos(graduationSession, collegeId, professionId, administrativeClassId);
    }
    
    public Integer countPayByinfos(Integer startPay,Integer endPay,String graduationSession,Integer collegeId,Integer professionId,Integer administrativeClassId){
        return bIDateDao.countPayByinfos(startPay, endPay, graduationSession, collegeId, professionId, administrativeClassId);
    }
}
