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

import com.hyf.cemap.bean.po.base.College;
import com.hyf.cemap.bean.po.base.Profession;
import com.hyf.cemap.bean.vo.ResultVO;
import com.hyf.cemap.service.base.CollegeServiceImpl;
import com.hyf.cemap.service.base.ProfessionServiceImpl;
import com.hyf.cemap.util.common.DataTableUtil;

@Controller
@RequestMapping(value = "/college")
public class CollegeController {
    
    @Autowired
    private CollegeServiceImpl collegeService;
    
    @Autowired
    private ProfessionServiceImpl professionService;
    
    
    /**
     * 主页
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/index", method = { RequestMethod.GET })
    public ModelAndView indexPage(HttpServletRequest request,
            HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("base/college/index");
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
        String name = request.getParameter("name");
        if(name != null && name.equals("")){
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
            Integer recordsTotal = collegeService.listAll(-1, -1).size();
            // 关键字过滤后总记录数
            Integer recordsFiltered = collegeService.listCollegeByinfo(name,-1, -1).size();
            dataTableUtil.setResult(recordsTotal, recordsFiltered,
                    collegeService.listCollegeByinfo(name,dataTableUtil.getPage(),
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
     */
    @RequestMapping(value = "/addPage", method = { RequestMethod.GET })
    public ModelAndView addPage(HttpServletRequest request,
            HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("base/college/add");
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
            @ModelAttribute("college") College college,
            HttpServletRequest request, HttpServletResponse response) {
        ResultVO resultVO = new ResultVO();
        UUID uuid = UUID.randomUUID();
        try {
            college.setUuid(uuid.toString());
            collegeService.save(college);
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
        College college;
        try {
            college = collegeService.getByUuid(uuid);
            modelAndView.addObject("college", college);
            modelAndView.setViewName("base/college/modify");
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
            College college = collegeService.getById(Integer.parseInt(id));
            college.setCode(code);
            college.setName(name);
            collegeService.update(college);
            
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
            College college = collegeService.getByUuid(uuid);
            //该学院下面存在专业
            List<Profession> professions = professionService.listByCollegeId(college.getId());
            if(professions.size() > 0){
                resultVO.setSuccess(true);
                resultVO.setCode("201");
                resultVO.setMessage("删除失败，该学院下还下属专业，不能删除");
            }
            else{
                college.setDeleted(true);
                collegeService.update(college);
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

}
