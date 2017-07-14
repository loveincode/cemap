package com.hyf.cemap.controller.member;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hyf.cemap.bean.po.log.LogChangePassword;
import com.hyf.cemap.bean.po.log.LogInOut;
import com.hyf.cemap.bean.security.Member;
import com.hyf.cemap.bean.security.Role;
import com.hyf.cemap.bean.vo.ResultVO;
import com.hyf.cemap.dao.UserDao;
import com.hyf.cemap.service.MemberServiceImpl;
import com.hyf.cemap.service.UserDetailServiceImpl;
import com.hyf.cemap.service.log.LogChangePasswordServiceImpl;
import com.hyf.cemap.util.common.CEMAPConstants;
import com.hyf.cemap.util.common.DataTableUtil;

@Controller
@RequestMapping(value = "/adminmember")
public class AdminMemberController {
    
    @Autowired
    MemberServiceImpl memberService;
    
    @Autowired
    private UserDao userDao;
    
    @Autowired
    private LogChangePasswordServiceImpl logChangePasswordService;
    
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
            HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("member/admin/index");
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
        String username = request.getParameter("username");
        if(username != null && username.equals("")){
            username = null;
        }
        String realname = request.getParameter("realname");
        if(realname != null && realname.equals("")){
            realname = null;
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
            Integer recordsTotal = memberService.listMemberByinfo(null,null,1,-1,-1).size();
            // 关键字过滤后总记录数
            Integer recordsFiltered = memberService.listMemberByinfo(username,realname,1,-1, -1).size();
            dataTableUtil.setResult(recordsTotal, recordsFiltered,
                    memberService.listMemberByinfo(username,realname,1,dataTableUtil.getPage(),
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
        modelAndView.setViewName("member/admin/add");
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
            @ModelAttribute("member") Member member,
            HttpServletRequest request, HttpServletResponse response) {
        ResultVO resultVO = new ResultVO();
        UUID uuid = UUID.randomUUID();
        try {
            if(memberService.listMemberByinfo(member.getUsername(),null,-1,-1,-1).size()>0){
                resultVO.setSuccess(false);
                resultVO.setCode("501");
                resultVO.setMessage("添加失败，用户名存在，请重新输入用户名");
                return resultVO.toString();
            }
            member.setUuid(uuid.toString());
            Set<Role> roles = new HashSet<Role>();
            Role role = new Role();
            role = userDao.findRoleByRoleCode(CEMAPConstants.CEMAP_ROLE_CODE_ADMIN);
            roles.add(role);
            member.setRoles(roles);
            member.setTempRoleId(1);
            memberService.save(member);
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
        Member member;
        try {
            member = memberService.getByUuid(uuid);
            modelAndView.addObject("member", member);
            modelAndView.setViewName("member/admin/modify");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return modelAndView;
    }
    
    @RequestMapping(value = "/update", method = { RequestMethod.GET,
            RequestMethod.POST }, produces = "application/json; charset=utf-8")
    public @ResponseBody String update(
            @ModelAttribute("member") Member member,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ResultVO resultVO = new ResultVO();
        try {
            Member omember = memberService.getById(member.getId());
            omember.setRealname(member.getRealname());
            omember.setPhone(member.getPhone());
            omember.setEmail(member.getEmail());
            memberService.update(omember);
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
            Member member = memberService.getByUuid(uuid);
            member.setDeleted(true);
            memberService.update(member);
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
     * 打开修改页面
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/passwordModifyPage", method = { RequestMethod.GET })
    public ModelAndView passwordModifyPage(
            @RequestParam(value = "uuid", required = true) String uuid,
            HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
            try {
                Member member = memberService.getByUuid(uuid);
                modelAndView.addObject("member", member);
                modelAndView.setViewName("member/admin/passwordmodify");
               
            } catch (Exception e) {
                e.printStackTrace();
            }
        return modelAndView;
    }
    
    @RequestMapping(value = "/passwordupdate", method = { RequestMethod.GET,
            RequestMethod.POST }, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String passwordupdate(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ResultVO resultVO = new ResultVO();
        try {
            String id = request.getParameter("id");
            String password = request.getParameter("password");
            Member member = memberService.getById(Integer.parseInt(id));
            //修改密码 进行加密处理
//            Md5PasswordEncoder md5 = new Md5PasswordEncoder();
//            md5.setEncodeHashAsBase64(false);
//            String pwd = md5.encodePassword(password, null);
            member.setPassword(password);
            memberService.update(member);
            resultVO.setSuccess(true);
            resultVO.setCode("200");
            resultVO.setMessage("更新成功");
            
            UserDetails userDetails = (UserDetails) SecurityContextHolder
                    .getContext().getAuthentication().getPrincipal();
            String usernameonline = userDetails.getUsername();
            Member memberonline = userDetailServiceImpl.findUserByUsername(usernameonline);
            //日志记录
            LogChangePassword logChangePassword = new LogChangePassword();
            //获得操作的用户名
            logChangePassword.setUsername_x(memberonline.getUsername());
            //获取被修改密码的用户名
            logChangePassword.setUsername_c(member.getUsername());
            //记录登录时间
            logChangePassword.setDate(new Date());
            //修改类型
            logChangePassword.setChangeType("重置密码");
            logChangePasswordService.save(logChangePassword);
            
        } catch (Exception e) {
            resultVO.setSuccess(false);
            resultVO.setCode("500");
            resultVO.setMessage("更新异常");
            e.printStackTrace();
        }
        return resultVO.toString();
    }


}
