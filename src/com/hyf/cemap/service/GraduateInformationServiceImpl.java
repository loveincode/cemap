package com.hyf.cemap.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hyf.cemap.bean.po.GraduateInformation;
import com.hyf.cemap.bean.vo.GraduateInformationVO;
import com.hyf.cemap.dao.GraduateInformationDaoImpl;
import com.hyf.cemap.util.persistence.impl.BaseServiceImpl;

@Service("graduateInformationService")
public class GraduateInformationServiceImpl extends
        BaseServiceImpl<GraduateInformation> {

    @Autowired
    private GraduateInformationDaoImpl graduateInformationDao;

    public List<GraduateInformationVO> listAllGraduateInformationVO(
            Integer pageNo, Integer pageSize) {
        return graduateInformationDao.listAllGraduateInformationVO(pageNo,
                pageSize);
    }

    public GraduateInformation getByStudentId(String studentId) {
        return graduateInformationDao.getByStudentId(studentId);
    }

    public List<GraduateInformation> listByClassId(Integer classid) {
        return graduateInformationDao.listByClassId(classid);
    }

    public List<GraduateInformationVO> listGraduateInformationVOByInfos(
            Integer collegeId, Integer professionId,
            Integer administrativeClassId, String name, String studentId,
            Integer pageNo, Integer pageSize) {
        return graduateInformationDao.listGraduateInformationVOByInfos(
                collegeId, professionId, administrativeClassId, name,
                studentId, pageNo, pageSize);
    }

    public List<GraduateInformationVO> listGraduateInformationVOByInfos_f(
            List<Integer> classIds, Integer administrativeClassId, String name,
            String studentId, Integer pageNo, Integer pageSize) {
        return graduateInformationDao.listGraduateInformationVOByInfos_f(
                classIds, administrativeClassId, name, studentId, pageNo,
                pageSize);
    }

    public List<GraduateInformationVO> listGraduateInformationVOByInfos2(
            Integer collegeId, Integer professionId,
            Integer administrativeClassId, Integer pageNo, Integer pageSize) {
        return graduateInformationDao.listGraduateInformationVOByInfos2(
                collegeId, professionId, administrativeClassId, pageNo,
                pageSize);
    }

    public List<GraduateInformationVO> listGraduateInformationVOByInfos2_f(
            List<Integer> classIds, Integer administrativeClassId,
            Integer pageNo, Integer pageSize) {
        return graduateInformationDao.listGraduateInformationVOByInfos2_f(
                classIds, administrativeClassId, pageNo, pageSize);
    }

    public List<GraduateInformationVO> listGraduateInformationVOByInfos3(
            Integer collegeId, Integer professionId,
            Integer administrativeClassId, Integer pageNo, Integer pageSize) {
        return graduateInformationDao.listGraduateInformationVOByInfos3(
                collegeId, professionId, administrativeClassId, pageNo,
                pageSize);
    }

    public List<GraduateInformationVO> listGraduateInformationVOByInfos3_f(
            List<Integer> classIds, Integer administrativeClassId,
            Integer pageNo, Integer pageSize) {
        return graduateInformationDao.listGraduateInformationVOByInfos3_f(
                classIds, administrativeClassId, pageNo, pageSize);
    }
}
