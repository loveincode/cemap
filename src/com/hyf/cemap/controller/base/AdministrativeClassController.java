package com.hyf.cemap.controller.base;

import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hyf.cemap.bean.po.base.AdministrativeClass;
import com.hyf.cemap.bean.po.base.College;
import com.hyf.cemap.bean.po.base.Profession;
import com.hyf.cemap.bean.vo.ResultVO;
import com.hyf.cemap.service.GraduateInformationServiceImpl;
import com.hyf.cemap.service.base.AdministrativeClassServiceImpl;
import com.hyf.cemap.service.base.CollegeServiceImpl;
import com.hyf.cemap.service.base.ProfessionServiceImpl;
import com.hyf.cemap.util.common.DataTableUtil;

@Controller
@RequestMapping(value = "/administrativeclass")
public class AdministrativeClassController {
    
    @Autowired
    private CollegeServiceImpl collegeService;
    
    @Autowired
    private ProfessionServiceImpl professionService;
    
    @Autowired
    private AdministrativeClassServiceImpl administrativeClassService;
    
    @Autowired
    private GraduateInformationServiceImpl graduateInformationService;
    
    
    /**
     * 主页
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception 
     */
    @RequestMapping(value = "/index", method = { RequestMethod.GET })
    public ModelAndView indexPage(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        List<College> colleges = collegeService.listAll("id", "ace");
        modelAndView.addObject("colleges", colleges);
        List<Profession> professions = professionService.listByCollegeId(colleges.get(0).getId());
        modelAndView.addObject("professions", professions);
        modelAndView.setViewName("base/administrativeclass/index");
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
        /**
         * 获取查询参数
         */
        Integer collegeId = -1;
        Integer professionId = -1; 
        
        String scollegeId = request.getParameter("collegeId");
        if(scollegeId != null){
            collegeId = Integer.parseInt(scollegeId);
        }
        String sprofessionId = request.getParameter("professionId");
        if(sprofessionId != null){
            professionId = Integer.parseInt(sprofessionId);
        }
        String name = request.getParameter("name");
        if(name != null &&  name.equals("")){
            name = null;
        }
        DataTableUtil dataTableUtil = new DataTableUtil(request);
        System.out.println("===================================");
        System.out.println("起始偏移=" + dataTableUtil.getStart());
        System.out.println("页长=" + dataTableUtil.getLength());
        System.out.println("页码=" + dataTableUtil.getPage());
        System.out.println("排序字段=" + dataTableUtil.getOrderColumn());
        System.out.println("排序顺序=" + dataTableUtil.getOrderDirection());
        System.out.println("搜索关键字=" + dataTableUtil.getSearchValue());
        System.out.println("===================================");
        try {
            // 总记录数
            Integer recordsTotal = administrativeClassService.listAllAdministrativeClassVO(-1, -1).size();
            // 关键字过滤后总记录数
            Integer recordsFiltered = administrativeClassService.listAdministrativeClassVOByInfos(collegeId, professionId, name, -1, -1).size();
            dataTableUtil.setResult(recordsTotal, recordsFiltered,
                    administrativeClassService.listAdministrativeClassVOByInfos(collegeId, professionId, name,dataTableUtil.getPage(),
                                dataTableUtil.getLength()));
        } catch (Exception e) {
            e.printStackTrace();
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
        modelAndView.setViewName("base/administrativeclass/add");
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
    public @ResponseBody String add(
            @ModelAttribute("administrativeClass") AdministrativeClass administrativeClass,
            HttpServletRequest request, HttpServletResponse response) {
        ResultVO resultVO = new ResultVO();
        UUID uuid = UUID.randomUUID();
        try {
            administrativeClass.setUuid(uuid.toString());
            administrativeClassService.save(administrativeClass);
            resultVO.setSuccess(true);
            resultVO.setCode("200");
            resultVO.setMessage("添加成功");
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
        AdministrativeClass administrativeClass;
        try {
            administrativeClass = administrativeClassService.getByUuid(uuid);
            List<College> colleges = collegeService.listAll("id", "ace");
            List<Profession> professions = professionService.listByCollegeId(administrativeClass.getCollegeId());
            modelAndView.addObject("colleges", colleges);
            modelAndView.addObject("professions", professions);
            modelAndView.addObject("administrativeClass", administrativeClass);
            modelAndView.setViewName("base/administrativeclass/modify");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelAndView;
    }
    
    @RequestMapping(value = "/update", method = { RequestMethod.GET,
            RequestMethod.POST }, produces = "application/json; charset=utf-8")
    public @ResponseBody String update(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ResultVO resultVO = new ResultVO();
        try {
            String id = request.getParameter("id");
            String code = request.getParameter("code");
            String name = request.getParameter("name");
            String collegeId = request.getParameter("collegeId");
            String professionId = request.getParameter("professionId");
            System.out.println("administrativeClassController 更新班级专业id"+professionId);
            AdministrativeClass administrativeClass = administrativeClassService.getById(Integer.parseInt(id));
            administrativeClass.setCode(code);
            administrativeClass.setName(name);
            administrativeClass.setCollegeId(Integer.parseInt(collegeId));
            administrativeClass.setProfessionId(Integer.parseInt(professionId));
            administrativeClassService.update(administrativeClass);
            
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
            AdministrativeClass administrativeClass = administrativeClassService.getByUuid(uuid);
            //该班级下是否绑定存在毕业生
            if(graduateInformationService.listByClassId(administrativeClass.getId()).size() > 0){
                resultVO.setSuccess(true);
                resultVO.setCode("202");
                resultVO.setMessage("删除失败，该专业下有学生信息，不能删除");
            }
            else{
                administrativeClass.setDeleted(true);
                administrativeClassService.update(administrativeClass);
                resultVO.setSuccess(true);
                resultVO.setCode("200");
                resultVO.setMessage("删除成功");
            }
        } catch (Exception e) {
            resultVO.setSuccess(false);
            resultVO.setCode("500");
            resultVO.setMessage("删除异常");
            e.printStackTrace();
        }
        return resultVO.toString();
    }
    
    @RequestMapping(value = "/getprofession", method = { RequestMethod.GET,
            RequestMethod.POST }, produces = "application/json; charset=utf-8")
    public @ResponseBody String getprofession(
            @RequestParam(value = "collegeId", required = true) Integer collegeId,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ResultVO resultVO = new ResultVO();
        try {
            List<Profession> professions = professionService.listByCollegeId(collegeId);
            resultVO.setData(professions);
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
    
    @RequestMapping(value = "/getadministrativeclass", method = { RequestMethod.GET,
            RequestMethod.POST }, produces = "application/json; charset=utf-8")
    public @ResponseBody String getadministrativeclass(
            @RequestParam(value = "professionId", required = true) Integer professionId,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ResultVO resultVO = new ResultVO();
        try {
            List<AdministrativeClass> administrativeClasss = administrativeClassService.listByProfessionId(professionId);
            resultVO.setData(administrativeClasss);
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
    
    
}
