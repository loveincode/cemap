package com.hyf.cemap.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hyf.cemap.bean.po.EmploymentInformation;
import com.hyf.cemap.bean.po.GraduateInformation;
import com.hyf.cemap.bean.po.GraduationFile;
import com.hyf.cemap.bean.po.base.AdministrativeClass;
import com.hyf.cemap.bean.po.base.College;
import com.hyf.cemap.bean.po.base.Profession;
import com.hyf.cemap.bean.vo.ResultVO;
import com.hyf.cemap.service.EmploymentInformationServiceImpl;
import com.hyf.cemap.service.GraduateInformationServiceImpl;
import com.hyf.cemap.service.GraduationFileServiceImpl;
import com.hyf.cemap.service.UserDetailServiceImpl;
import com.hyf.cemap.service.base.AdministrativeClassServiceImpl;
import com.hyf.cemap.service.base.CollegeServiceImpl;
import com.hyf.cemap.service.base.ProfessionServiceImpl;

@Controller
@RequestMapping(value = "/studentperson")
public class StudentPersonController {
 
    @Autowired
    private GraduateInformationServiceImpl graduateInformationService;

    @Autowired
    private GraduationFileServiceImpl graduationFileService;

    @Autowired
    private EmploymentInformationServiceImpl employmentInformationService;
    
    @Autowired
    private UserDetailServiceImpl userDetailServiceImpl;
    
    @Autowired
    private CollegeServiceImpl collegeService;
    
    @Autowired
    private ProfessionServiceImpl professionService;
    
    @Autowired
    private AdministrativeClassServiceImpl administrativeClassService;

    /**
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/saveorupdateInformationPage", method = { RequestMethod.GET })
    public ModelAndView saveorupdateInformationPage(
            HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder
                    .getContext().getAuthentication().getPrincipal();
            String username = userDetails.getUsername();
            //Member member = userDetailServiceImpl.findUserByUsername(username);
            GraduateInformation graduateInformation = graduateInformationService.getByStudentId(username);
            College college = collegeService.getById(graduateInformation.getCollegeId());
            Profession profession = professionService.getById(graduateInformation.getProfessionId());
            AdministrativeClass administrativeClass = administrativeClassService.getById(graduateInformation.getAdministrativeClassId());
            modelAndView.addObject("college", college);
            modelAndView.addObject("profession", profession);
            modelAndView.addObject("administrativeClass", administrativeClass);
            modelAndView.addObject("graduateInformation", graduateInformation);
            modelAndView.setViewName("studentperson/modifyinformation");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelAndView;
    }
    
    /**
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/saveorupdateFilePage", method = { RequestMethod.GET })
    public ModelAndView saveorupdateFilePage(
            HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder
                    .getContext().getAuthentication().getPrincipal();
            String username = userDetails.getUsername();
            //Member member = userDetailServiceImpl.findUserByUsername(username);
            GraduationFile graduationFile = graduationFileService.getByStudentId(username);
            modelAndView.addObject("graduationFile", graduationFile);
            modelAndView.setViewName("studentperson/modifyfile");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelAndView;
    }
    
    /**
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/saveorupdateFile", method = { RequestMethod.GET,
            RequestMethod.POST }, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String saveorupdateFile(
            @ModelAttribute("graduationFile") GraduationFile graduationFile,
            HttpServletRequest request, HttpServletResponse response) {
        ResultVO resultVO = new ResultVO();
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder
                    .getContext().getAuthentication().getPrincipal();
            //学号
            String username = userDetails.getUsername();
            if(graduationFile.getStudentId() !=null && graduationFile.getStudentId().equals("")){
                graduationFile.setStudentId(username);
                graduationFile.setUuid(UUID.randomUUID().toString());
                graduationFileService.save(graduationFile);
                resultVO.setSuccess(true);
                resultVO.setCode("200");
                resultVO.setMessage("添加成功");
            }
            else{
                GraduationFile graduationFile1 = graduationFileService.getByStudentId(username);
                graduationFile.setUuid(graduationFile1.getUuid());
                graduationFileService.update(graduationFile);
                resultVO.setSuccess(true);
                resultVO.setCode("200");
                resultVO.setMessage("更新成功");
            }
            //Member member = userDetailServiceImpl.findUserByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultVO.toString();
    }
    
    /**
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/saveorupdateEmployPage", method = { RequestMethod.GET })
    public ModelAndView saveorupdateEmployPage(
            HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder
                    .getContext().getAuthentication().getPrincipal();
            String username = userDetails.getUsername();
            //Member member = userDetailServiceImpl.findUserByUsername(username);
            EmploymentInformation employmentInformation = employmentInformationService.getByStudentId(username);
            modelAndView.addObject("employmentInformation", employmentInformation);
            modelAndView.setViewName("studentperson/modifyemploy");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelAndView;
    }
    
    /**
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/saveorupdateEmploy", method = { RequestMethod.GET,
            RequestMethod.POST }, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String saveorupdateEmploy(
            @ModelAttribute("employmentInformation") EmploymentInformation employmentInformation,
            HttpServletRequest request, HttpServletResponse response) {
        ResultVO resultVO = new ResultVO();
        try {
            UserDetails userDetails = (UserDetails) SecurityContextHolder
                    .getContext().getAuthentication().getPrincipal();
            //学号
            String username = userDetails.getUsername();
            if(employmentInformation.getStudentId() !=null && employmentInformation.getStudentId().equals("")){
                employmentInformation.setStudentId(username);
                employmentInformation.setUuid(UUID.randomUUID().toString());
                employmentInformationService.save(employmentInformation);
                resultVO.setSuccess(true);
                resultVO.setCode("200");
                resultVO.setMessage("添加成功");
            }
            else{
                EmploymentInformation employmentInformation1 = employmentInformationService.getByStudentId(username);
                employmentInformation.setUuid(employmentInformation1.getUuid());
                
              //如果是 签就业协议书 签合同
                if (employmentInformation.getEmploymentMethod().equals("签就业协议书") || employmentInformation.getEmploymentMethod().equals("签合同")) {
                    System.out.println("签三方 合同");
                    employmentInformation.setMajor(null);
                    employmentInformation.setUniversity(null);
                }
                //如果是 升学
                else if (employmentInformation.getEmploymentMethod().equals("升学")) {
                    System.out.println("升学");
                    employmentInformation.setCompanyContactName(null);
                    employmentInformation.setCompanyContactPhone(null);
                    employmentInformation.setCompanyFullName(null);
                    employmentInformation.setCompanyPlace(null);
                    employmentInformation.setCompanyProvince(null);
                    employmentInformation.setEmail(null);
                    employmentInformation.setEmploymentPay(null);
                    employmentInformation.setIndustryType(null);
                    employmentInformation.setNature(null);
                    employmentInformation.setOccupationType(null);
                    employmentInformation.setOrganizationCode(null);
                    employmentInformation.setPostcode(null);
                }
                //如果是出国 自主创业 参军
                else{
                    employmentInformation.setMajor(null);
                    employmentInformation.setUniversity(null);
                    
                    employmentInformation.setCompanyContactName(null);
                    employmentInformation.setCompanyContactPhone(null);
                    employmentInformation.setCompanyFullName(null);
                    employmentInformation.setCompanyPlace(null);
                    employmentInformation.setCompanyProvince(null);
                    employmentInformation.setEmail(null);
                    employmentInformation.setEmploymentPay(null);
                    employmentInformation.setIndustryType(null);
                    employmentInformation.setNature(null);
                    employmentInformation.setOccupationType(null);
                    employmentInformation.setOrganizationCode(null);
                    employmentInformation.setPostcode(null);
                }
                
                employmentInformationService.update(employmentInformation);
                resultVO.setSuccess(true);
                resultVO.setCode("200");
                resultVO.setMessage("更新成功");
            }
            //Member member = userDetailServiceImpl.findUserByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultVO.toString();
    }
    
}