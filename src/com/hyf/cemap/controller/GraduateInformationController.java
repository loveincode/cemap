package com.hyf.cemap.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hyf.cemap.bean.po.EmploymentInformation;
import com.hyf.cemap.bean.po.GraduateInformation;
import com.hyf.cemap.bean.po.GraduationFile;
import com.hyf.cemap.bean.po.base.AdministrativeClass;
import com.hyf.cemap.bean.po.base.College;
import com.hyf.cemap.bean.po.base.Profession;
import com.hyf.cemap.bean.security.Member;
import com.hyf.cemap.bean.security.Role;
import com.hyf.cemap.bean.vo.ResultVO;
import com.hyf.cemap.dao.UserDao;
import com.hyf.cemap.service.EmploymentInformationServiceImpl;
import com.hyf.cemap.service.GraduateInformationServiceImpl;
import com.hyf.cemap.service.GraduationFileServiceImpl;
import com.hyf.cemap.service.MemberServiceImpl;
import com.hyf.cemap.service.UserDetailServiceImpl;
import com.hyf.cemap.service.base.AdministrativeClassServiceImpl;
import com.hyf.cemap.service.base.CollegeServiceImpl;
import com.hyf.cemap.service.base.ProfessionServiceImpl;
import com.hyf.cemap.util.common.CEMAPConstants;
import com.hyf.cemap.util.common.DataTableUtil;
/**
 * 毕业生信息Controller
*  @author  huyifan
*  @ClassName  GraduateInformationController  
*  @date  2017年3月8日 下午4:56:09
 */
@Controller
@RequestMapping(value = "/graduateinformation")
public class GraduateInformationController {
	
	@Autowired
    private CollegeServiceImpl collegeService;
    
    @Autowired
    private ProfessionServiceImpl professionService;
    
    @Autowired
    private AdministrativeClassServiceImpl administrativeClassService;
	
	@Autowired
	private GraduateInformationServiceImpl graduateInformationService;
	
	@Autowired
    private GraduationFileServiceImpl graduationFileService;

    @Autowired
    private EmploymentInformationServiceImpl employmentInformationService;
	
	@Autowired
    private UserDao userDao;
	
	@Autowired
	private MemberServiceImpl memberService;
	
	@Autowired
    private UserDetailServiceImpl userDetailServiceImpl;
	
	
	/**
	 * 主页
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/index", method = { RequestMethod.GET })
	public ModelAndView indexPage(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		List<College> colleges = collegeService.listAll("id", "ace");
        modelAndView.addObject("colleges", colleges);
        
        /**
         * 获取用户权限
         */
        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        String usernameonline = userDetails.getUsername();
        Member memberonline = userDetailServiceImpl.findUserByUsername(usernameonline);
        //只有辅导员需要区分开 辅导员查到自己所管的班级的学生信息
        if (userDetailServiceImpl.findUserAuthorities(memberonline).toString().equals("[ROLE_TEACHER]")) {
            try {
                String sclassId = memberonline.getManageClass();
                String[] sclassIds = sclassId.split(",");
                List<Integer> classIds =new ArrayList<Integer>();
                for(int i=0;i<sclassIds.length;i++){
                    classIds.add(Integer.parseInt(sclassIds[i]));
                }
                List<AdministrativeClass> administrativeClasss = administrativeClassService.listByClassIds(classIds);
                modelAndView.addObject("administrativeClasss", administrativeClasss);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
//        List<Profession> professions = professionService.listByCollegeId(colleges.get(0).getId());
//        modelAndView.addObject("professions", professions);
//        List<AdministrativeClass> administrativeClasss = administrativeClassService.listByProfessionId(professions.get(0).getId());
//        modelAndView.addObject("administrativeClasss", administrativeClasss);
		modelAndView.setViewName("graduateinformation/index");
		return modelAndView;
	}
	
	/**
	 * 获取数据
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/data", method = { RequestMethod.POST }, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String data(HttpServletRequest request, HttpServletResponse response) {
		/**
		 * 获取查询参数
		 */
	    Integer collegeId = -1;
	    Integer professionId = -1; 
	    Integer administrativeClassId = -1;
	    Integer administrativeClassId_f = -1;
	    
	    String scollegeId = request.getParameter("scollegeId");
	    if(scollegeId != null){
	        collegeId = Integer.parseInt(scollegeId);
	    }
        String sprofessionId = request.getParameter("sprofessionId");
        if(sprofessionId != null){
            professionId = Integer.parseInt(sprofessionId);
        }
        String sadministrativeClassId = request.getParameter("sadministrativeClassId");
        if(sadministrativeClassId != null){
            administrativeClassId = Integer.parseInt(sadministrativeClassId);
        }
        String sadministrativeClassId_f = request.getParameter("f_sadministrativeClassId");
        if(sadministrativeClassId_f != null){
            administrativeClassId_f = Integer.parseInt(sadministrativeClassId_f);
        }
        
        String name = request.getParameter("sname");
        if(name != null &&  name.equals("")){
            name = null;
        }
        String studentId = request.getParameter("sstudentId");
        if(studentId != null &&  studentId.equals("")){
            studentId = null;
        }
        
		DataTableUtil dataTableUtil = new DataTableUtil(request);
		/**
         * 获取用户权限
         */
        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        String usernameonline = userDetails.getUsername();
        Member memberonline = userDetailServiceImpl.findUserByUsername(usernameonline);
        //只有辅导员需要区分开 辅导员查到自己所管的班级的学生信息
        if (userDetailServiceImpl.findUserAuthorities(memberonline).toString().equals("[ROLE_TEACHER]")) {
            try {
                String sclassId = memberonline.getManageClass();
                String[] sclassIds = sclassId.split(",");
                List<Integer> classIds =new ArrayList<Integer>();
                for(int i=0;i<sclassIds.length;i++){
                    classIds.add(Integer.parseInt(sclassIds[i]));
                }
                // 总记录数
                Integer recordsTotal = graduateInformationService.listGraduateInformationVOByInfos_f(classIds,-1, null, null, -1, -1).size();
                // 关键字过滤后总记录数
                Integer recordsFiltered = graduateInformationService.listGraduateInformationVOByInfos_f(classIds,administrativeClassId_f, name, studentId,-1, -1).size();
                dataTableUtil.setResult(recordsTotal, recordsFiltered,
                        graduateInformationService.listGraduateInformationVOByInfos_f(classIds,administrativeClassId_f, name, studentId,dataTableUtil.getPage(),
                                    dataTableUtil.getLength()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else{
    		try {
    		    System.out.println(dataTableUtil.getLength());
    			// 总记录数
    			Integer recordsTotal = graduateInformationService.listGraduateInformationVOByInfos(-1, -1, -1, null, null, -1, -1).size();
    			// 关键字过滤后总记录数
    			Integer recordsFiltered = graduateInformationService.listGraduateInformationVOByInfos
    			        (collegeId, professionId, administrativeClassId, name, studentId, -1,-1).size();
    			dataTableUtil.setResult(recordsTotal, recordsFiltered,
    			        graduateInformationService.listGraduateInformationVOByInfos
    			        (collegeId, professionId, administrativeClassId, name, studentId, dataTableUtil.getPage(),dataTableUtil.getLength()));
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        }
		return dataTableUtil.result();
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/addPage", method = { RequestMethod.GET })
	public ModelAndView addPage(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		List<College> colleges = collegeService.listAll("id", "ace");
        modelAndView.addObject("colleges", colleges);
        List<Profession> professions = professionService.listByCollegeId(colleges.get(0).getId());
        modelAndView.addObject("professions", professions);
        List<AdministrativeClass> administrativeClasss = administrativeClassService.listByProfessionId(professions.get(0).getId());
        modelAndView.addObject("administrativeClasss", administrativeClasss);
		modelAndView.setViewName("graduateinformation/add");
		return modelAndView;
	}
	
	/**
	 * 保存
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/add", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String add(
			@ModelAttribute("graduateInformation") GraduateInformation graduateInformation,
			HttpServletRequest request, HttpServletResponse response) {
	    ResultVO resultVO = new ResultVO();
	    UUID uuid = UUID.randomUUID();
		try {
		    /**
		     * 1 检查学号是否重复 
		     */
		    if(graduateInformationService.getByStudentId(graduateInformation.getStudentId())!=null){
		        resultVO.setSuccess(false);
	            resultVO.setCode("401");
	            resultVO.setMessage("添加异常，该学号已存在，请确认学号输入正确");
		    }
		    else{
    		    graduateInformation.setUuid(uuid.toString());
    		    graduateInformationService.save(graduateInformation);
    		    /**
    		     * 2 保存完成后
    		     *   会创建一个学生用户 
    		     */
    		    Set<Role> roles = new HashSet<Role>();
                Role role = new Role();
    		    role = userDao.findRoleByRoleCode(CEMAPConstants.CEMAP_ROLE_CODE_STUDENT);
    		    roles.add(role);
    		    Member member = new Member();
    		    // 学号 为 用户登录名 密码 为 默认123456
    		    member.setUsername(graduateInformation.getStudentId());
    		    member.setRealname(graduateInformation.getName());
    		    member.setPhone(graduateInformation.getPhone());
    		    member.setEmail(graduateInformation.getEmail());
    		    member.setClassId(graduateInformation.getAdministrativeClassId());
    		    member.setUuid(UUID.randomUUID().toString());
    		    member.setPassword("123456");
    		    member.setRoles(roles);
    		    member.setTempRoleId(4);
    		    memberService.save(member);
    		    resultVO.setSuccess(true);
                resultVO.setCode("200");
                resultVO.setMessage("添加成功");
            }
        } catch (Exception e) {
            resultVO.setSuccess(false);
            resultVO.setCode("500");
            resultVO.setMessage("添加异常");
            e.printStackTrace();
        }
		return resultVO.toString();
	}
	
	/**
	 * 打开修改页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/modifyPage", method = { RequestMethod.GET })
	public ModelAndView modifyPage(
			@RequestParam(value = "uuid", required = true) String uuid,
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		GraduateInformation graduateInformation;
		try {
			graduateInformation = graduateInformationService.getByUuid(uuid);
			List<College> colleges = collegeService.listAll("id", "ace");
            List<Profession> professions = professionService.listByCollegeId(graduateInformation.getCollegeId());
            List<AdministrativeClass> administrativeClasss = administrativeClassService.listByProfessionId(graduateInformation.getProfessionId());
            modelAndView.addObject("colleges", colleges);
            modelAndView.addObject("professions", professions);
            modelAndView.addObject("administrativeClasss", administrativeClasss);
			modelAndView.addObject("graduateInformation", graduateInformation);
			
			//修改后要更新班级信息到member表中
			
			modelAndView.setViewName("graduateinformation/modify");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/update", method = { RequestMethod.GET,
            RequestMethod.POST }, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String update(
            @ModelAttribute("graduateInformation") GraduateInformation graduateInformation,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ResultVO resultVO = new ResultVO();
        try {
            GraduateInformation oldgraduateInformation = graduateInformationService.getById(graduateInformation.getId());
            graduateInformation.setUuid(oldgraduateInformation.getUuid());
            
            //判断班级id是否有变化
            if(!oldgraduateInformation.getAdministrativeClassId().equals(graduateInformation.getAdministrativeClassId())){
                Member member = memberService.getByUsername(oldgraduateInformation.getStudentId());
                member.setClassId(graduateInformation.getAdministrativeClassId());
                memberService.update(member);
            }
            
            //如果学号变化
            if(!oldgraduateInformation.getStudentId().equals(graduateInformation.getStudentId())){
                Member omember = memberService.getByUsername(oldgraduateInformation.getStudentId());
                memberService.delete(omember);
                
                Set<Role> roles = new HashSet<Role>();
                Role role = new Role();
                role = userDao.findRoleByRoleCode(CEMAPConstants.CEMAP_ROLE_CODE_STUDENT);
                roles.add(role);
                Member member = new Member();
                // 学号 为 用户登录名 密码 为 默认123456
                member.setUsername(graduateInformation.getStudentId());
                member.setRealname(graduateInformation.getName());
                member.setPhone(graduateInformation.getPhone());
                member.setEmail(graduateInformation.getEmail());
                member.setClassId(graduateInformation.getAdministrativeClassId());
                member.setUuid(UUID.randomUUID().toString());
                member.setPassword("123456");
                member.setRoles(roles);
                member.setTempRoleId(4);
                memberService.save(member);
                
                //更新就业信息和档案信息中的学号
                EmploymentInformation employmentInformation = employmentInformationService.getByStudentId(oldgraduateInformation.getStudentId());
                    if(employmentInformation!=null){
                    employmentInformation.setStudentId(graduateInformation.getStudentId());
                    employmentInformationService.update(employmentInformation);
                }
                GraduationFile graduationFile = graduationFileService.getByStudentId(oldgraduateInformation.getStudentId());
                    if(graduationFile!=null){
                    graduationFile.setStudentId(graduateInformation.getStudentId());
                    graduationFileService.update(graduationFile);
                }
            }
            
            graduateInformationService.update(graduateInformation);
            resultVO.setSuccess(true);
            resultVO.setCode("200");
            resultVO.setMessage("更新成功");
        } catch (Exception e) {
            resultVO.setSuccess(false);
            resultVO.setCode("500");
            resultVO.setMessage("更新异常");
            e.printStackTrace();
        }
        return resultVO.toString();
    }
	
	/**
     * 打开就业页面
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/modifyEmployPage", method = { RequestMethod.GET })
    public ModelAndView modifyEmployPage(
            @RequestParam(value = "uuid", required = true) String uuid,
            HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        GraduateInformation graduateInformation;
        try {
            graduateInformation = graduateInformationService.getByUuid(uuid);
            modelAndView.addObject("studentId1",graduateInformation.getStudentId());
            EmploymentInformation employmentInformation = employmentInformationService.getByStudentId(graduateInformation.getStudentId());
            modelAndView.addObject("employmentInformation", employmentInformation);
            modelAndView.setViewName("graduateinformation/modifyemploy");
        } catch (Exception e) {
            // TODO Auto-generated catch block
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
            String studentId1 = request.getParameter("studentId1");
            if(employmentInformation.getStudentId() !=null && employmentInformation.getStudentId().equals("")){
                employmentInformation.setStudentId(studentId1);
                employmentInformation.setUuid(UUID.randomUUID().toString());
                employmentInformationService.save(employmentInformation);
                resultVO.setSuccess(true);
                resultVO.setCode("200");
                resultVO.setMessage("添加成功");
            }
            else{
                EmploymentInformation employmentInformation1 = employmentInformationService.getByStudentId(studentId1);
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
    
    /**
     * 打开档案页面
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/modifyFilePage", method = { RequestMethod.GET })
    public ModelAndView modifyFilePage(
            @RequestParam(value = "uuid", required = true) String uuid,
            HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        GraduateInformation graduateInformation;
        try {
            graduateInformation = graduateInformationService.getByUuid(uuid);
            modelAndView.addObject("studentId1",graduateInformation.getStudentId());
            GraduationFile graduationFile = graduationFileService.getByStudentId(graduateInformation.getStudentId());
            modelAndView.addObject("graduationFile", graduationFile);
            modelAndView.setViewName("graduateinformation/modifyfile");
        } catch (Exception e) {
            // TODO Auto-generated catch block
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
            String studentId1 = request.getParameter("studentId1");
            if(graduationFile.getStudentId() !=null && graduationFile.getStudentId().equals("")){
                graduationFile.setStudentId(studentId1);
                graduationFile.setUuid(UUID.randomUUID().toString());
                graduationFileService.save(graduationFile);
                resultVO.setSuccess(true);
                resultVO.setCode("200");
                resultVO.setMessage("添加成功");
            }
            else{
                GraduationFile graduationFile1 = graduationFileService.getByStudentId(studentId1);
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
	 * 删除
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/delete", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json; charset=utf-8")
	public @ResponseBody String delete(
			@RequestParam(value = "uuid", required = true) String uuid,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ResultVO resultVO = new ResultVO();
		try {
			GraduateInformation graduateInformation = graduateInformationService.getByUuid(uuid);
			
			if(memberService.getByUsername(graduateInformation.getStudentId())!=null){
			    resultVO.setSuccess(true);
			    resultVO.setCode("501");
			    resultVO.setMessage("删除失败，该信息绑定了学生用户");
			    return resultVO.toString();
			}
			
			graduateInformation.setDeleted(true);
			graduateInformationService.update(graduateInformation);
			resultVO.setSuccess(true);
			resultVO.setCode("200");
			resultVO.setMessage("删除成功");
			
		} catch (Exception e) {
			resultVO.setSuccess(false);
			resultVO.setCode("500");
			resultVO.setMessage("删除异常");
			e.printStackTrace();
		}
		return resultVO.toString();
	}
	
	/**
     * 删除
     * @param uuid
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/deleteemploy", method = { RequestMethod.GET,
            RequestMethod.POST }, produces = "application/json; charset=utf-8")
    public @ResponseBody String deleteemploy(
            @RequestParam(value = "uuid", required = true) String uuid,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ResultVO resultVO = new ResultVO();
        try {
            EmploymentInformation employmentInformation = employmentInformationService.getByUuid(uuid);
            if(employmentInformation!=null){
                employmentInformation.setDeleted(true);
                employmentInformationService.update(employmentInformation);
                resultVO.setSuccess(true);
                resultVO.setCode("200");
                resultVO.setMessage("删除成功");
                return resultVO.toString();
            }
            resultVO.setSuccess(true);
            resultVO.setCode("501");
            resultVO.setMessage("删除失败，没填写信息");
            
        } catch (Exception e) {
            resultVO.setSuccess(false);
            resultVO.setCode("500");
            resultVO.setMessage("删除异常");
            e.printStackTrace();
        }
        return resultVO.toString();
    }
	
	/**
	 * 未完成登记信息
	 * 1 就业信息
	 * 2 档案信息
	 * 
	 */
    @RequestMapping(value = "/notemploy", method = { RequestMethod.GET })
    public ModelAndView notemploy(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        List<College> colleges = collegeService.listAll("id", "ace");
        modelAndView.addObject("colleges", colleges);
        /**
         * 获取用户权限
         */
        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        String usernameonline = userDetails.getUsername();
        Member memberonline = userDetailServiceImpl.findUserByUsername(usernameonline);
        //只有辅导员需要区分开 辅导员查到自己所管的班级的学生信息
        if (userDetailServiceImpl.findUserAuthorities(memberonline).toString().equals("[ROLE_TEACHER]")) {
            try {
                String sclassId = memberonline.getManageClass();
                String[] sclassIds = sclassId.split(",");
                List<Integer> classIds =new ArrayList<Integer>();
                for(int i=0;i<sclassIds.length;i++){
                    classIds.add(Integer.parseInt(sclassIds[i]));
                }
                List<AdministrativeClass> administrativeClasss = administrativeClassService.listByClassIds(classIds);
                modelAndView.addObject("administrativeClasss", administrativeClasss);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        modelAndView.setViewName("graduateinformation/notemploy");
        return modelAndView;
    }
    
    /**
     * 获取数据
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/notemploydata", method = { RequestMethod.POST }, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String notemploydata(HttpServletRequest request, HttpServletResponse response) {
        /**
         * 获取查询参数
         */
        Integer collegeId = -1;
        Integer professionId = -1; 
        Integer administrativeClassId = -1;
        Integer administrativeClassId_f = -1;
        
        String scollegeId = request.getParameter("scollegeId");
        if(scollegeId != null){
            collegeId = Integer.parseInt(scollegeId);
        }
        String sprofessionId = request.getParameter("sprofessionId");
        if(sprofessionId != null){
            professionId = Integer.parseInt(sprofessionId);
        }
        String sadministrativeClassId = request.getParameter("sadministrativeClassId");
        if(sadministrativeClassId != null){
            administrativeClassId = Integer.parseInt(sadministrativeClassId);
        }
        String sadministrativeClassId_f = request.getParameter("f_sadministrativeClassId");
        if(sadministrativeClassId_f != null){
            administrativeClassId_f = Integer.parseInt(sadministrativeClassId_f);
        }
        
        String name = request.getParameter("sname");
        if(name != null &&  name.equals("")){
            name = null;
        }
        String studentId = request.getParameter("sstudentId");
        if(studentId != null &&  studentId.equals("")){
            studentId = null;
        }
        
        DataTableUtil dataTableUtil = new DataTableUtil(request);
        /**
         * 获取用户权限
         */
        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        String usernameonline = userDetails.getUsername();
        Member memberonline = userDetailServiceImpl.findUserByUsername(usernameonline);
        //只有辅导员需要区分开 辅导员查到自己所管的班级的学生信息
        if (userDetailServiceImpl.findUserAuthorities(memberonline).toString().equals("[ROLE_TEACHER]")) {
            try {
                String sclassId = memberonline.getManageClass();
                String[] sclassIds = sclassId.split(",");
                List<Integer> classIds =new ArrayList<Integer>();
                for(int i=0;i<sclassIds.length;i++){
                    classIds.add(Integer.parseInt(sclassIds[i]));
                }
                // 总记录数
                Integer recordsTotal = graduateInformationService.listGraduateInformationVOByInfos2_f(classIds,-1, -1, -1).size();
                // 关键字过滤后总记录数
                Integer recordsFiltered = graduateInformationService.listGraduateInformationVOByInfos2_f(classIds,administrativeClassId_f,-1, -1).size();
                dataTableUtil.setResult(recordsTotal, recordsFiltered,
                        graduateInformationService.listGraduateInformationVOByInfos2_f(classIds,administrativeClassId_f,dataTableUtil.getPage(),
                                    dataTableUtil.getLength()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else{
            try {
                // 总记录数
                Integer recordsTotal = graduateInformationService.listGraduateInformationVOByInfos2(-1, -1, -1, -1, -1).size();
                // 关键字过滤后总记录数
                Integer recordsFiltered = graduateInformationService.listGraduateInformationVOByInfos2
                        (collegeId, professionId, administrativeClassId,-1,-1).size();
                dataTableUtil.setResult(recordsTotal, recordsFiltered,
                        graduateInformationService.listGraduateInformationVOByInfos2
                        (collegeId, professionId, administrativeClassId, dataTableUtil.getPage(),dataTableUtil.getLength()));
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return dataTableUtil.result();
    }
    
    @RequestMapping(value = "/notfile", method = { RequestMethod.GET })
    public ModelAndView notfile(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        List<College> colleges = collegeService.listAll("id", "ace");
        modelAndView.addObject("colleges", colleges);
        /**
         * 获取用户权限
         */
        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        String usernameonline = userDetails.getUsername();
        Member memberonline = userDetailServiceImpl.findUserByUsername(usernameonline);
        //只有辅导员需要区分开 辅导员查到自己所管的班级的学生信息
        if (userDetailServiceImpl.findUserAuthorities(memberonline).toString().equals("[ROLE_TEACHER]")) {
            try {
                String sclassId = memberonline.getManageClass();
                String[] sclassIds = sclassId.split(",");
                List<Integer> classIds =new ArrayList<Integer>();
                for(int i=0;i<sclassIds.length;i++){
                    classIds.add(Integer.parseInt(sclassIds[i]));
                }
                List<AdministrativeClass> administrativeClasss = administrativeClassService.listByClassIds(classIds);
                modelAndView.addObject("administrativeClasss", administrativeClasss);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        modelAndView.setViewName("graduateinformation/notfile");
        return modelAndView;
    }
    
    /**
     * 获取数据
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/notfiledata", method = { RequestMethod.POST }, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String notfiledata(HttpServletRequest request, HttpServletResponse response) {
        /**
         * 获取查询参数
         */
        Integer collegeId = -1;
        Integer professionId = -1; 
        Integer administrativeClassId = -1;
        Integer administrativeClassId_f = -1;
        
        String scollegeId = request.getParameter("scollegeId");
        if(scollegeId != null){
            collegeId = Integer.parseInt(scollegeId);
        }
        String sprofessionId = request.getParameter("sprofessionId");
        if(sprofessionId != null){
            professionId = Integer.parseInt(sprofessionId);
        }
        String sadministrativeClassId = request.getParameter("sadministrativeClassId");
        if(sadministrativeClassId != null){
            administrativeClassId = Integer.parseInt(sadministrativeClassId);
        }
        String sadministrativeClassId_f = request.getParameter("f_sadministrativeClassId");
        if(sadministrativeClassId_f != null){
            administrativeClassId_f = Integer.parseInt(sadministrativeClassId_f);
        }
        
        DataTableUtil dataTableUtil = new DataTableUtil(request);
        /**
         * 获取用户权限
         */
        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        String usernameonline = userDetails.getUsername();
        Member memberonline = userDetailServiceImpl.findUserByUsername(usernameonline);
        //只有辅导员需要区分开 辅导员查到自己所管的班级的学生信息
        if (userDetailServiceImpl.findUserAuthorities(memberonline).toString().equals("[ROLE_TEACHER]")) {
            try {
                String sclassId = memberonline.getManageClass();
                String[] sclassIds = sclassId.split(",");
                List<Integer> classIds =new ArrayList<Integer>();
                for(int i=0;i<sclassIds.length;i++){
                    classIds.add(Integer.parseInt(sclassIds[i]));
                }
                // 总记录数
                Integer recordsTotal = graduateInformationService.listGraduateInformationVOByInfos3_f(classIds,-1, -1, -1).size();
                // 关键字过滤后总记录数
                Integer recordsFiltered = graduateInformationService.listGraduateInformationVOByInfos3_f(classIds,administrativeClassId_f,-1, -1).size();
                dataTableUtil.setResult(recordsTotal, recordsFiltered,
                        graduateInformationService.listGraduateInformationVOByInfos3_f(classIds,administrativeClassId_f,dataTableUtil.getPage(),
                                    dataTableUtil.getLength()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                // 总记录数
                Integer recordsTotal = graduateInformationService.listGraduateInformationVOByInfos3(-1, -1, -1, -1, -1).size();
                // 关键字过滤后总记录数
                Integer recordsFiltered = graduateInformationService.listGraduateInformationVOByInfos3
                        (collegeId, professionId, administrativeClassId, -1,-1).size();
                dataTableUtil.setResult(recordsTotal, recordsFiltered,
                        graduateInformationService.listGraduateInformationVOByInfos3
                        (collegeId, professionId, administrativeClassId, dataTableUtil.getPage(),dataTableUtil.getLength()));
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return dataTableUtil.result();
    }
	

}
