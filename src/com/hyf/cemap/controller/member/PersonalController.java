package com.hyf.cemap.controller.member;

import java.util.Date;

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

import com.hyf.cemap.bean.po.log.LogChangePassword;
import com.hyf.cemap.bean.security.Member;
import com.hyf.cemap.bean.vo.ResultVO;
import com.hyf.cemap.service.MemberServiceImpl;
import com.hyf.cemap.service.UserDetailServiceImpl;
import com.hyf.cemap.service.log.LogChangePasswordServiceImpl;

@Controller
@RequestMapping(value = "/personal")
public class PersonalController {
    
    @Autowired
    private UserDetailServiceImpl userDetailServiceImpl;
    
    @Autowired
    MemberServiceImpl memberService;
    
    @Autowired
    private LogChangePasswordServiceImpl logChangePasswordService;
    
    
    /**
     * 打开修改页面
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/modifyPage", method = { RequestMethod.GET })
    public ModelAndView modifyPage(
            HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        /**
         * 获取用户信息
         */
        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        String usernameonline = userDetails.getUsername();
        Member member = userDetailServiceImpl.findUserByUsername(usernameonline);
        modelAndView.addObject("member", member);
        modelAndView.setViewName("member/personal/modify");
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
     * 打开修改页面
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/passwordModifyPage", method = { RequestMethod.GET })
    public ModelAndView passwordModifyPage(
            HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView();
        /**
         * 获取用户信息
         */
        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        String usernameonline = userDetails.getUsername();
        Member member = userDetailServiceImpl.findUserByUsername(usernameonline);
        modelAndView.addObject("member", member);
        modelAndView.setViewName("member/personal/passwordmodify");
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
            String password1 = request.getParameter("password1");
           
            Member member = memberService.getById(Integer.parseInt(id));
            //原密码输入正确
            if(password.equals(member.getPassword())){
                member.setPassword(password1);
                memberService.update(member);
                
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
                logChangePassword.setChangeType("个人修改");
                logChangePasswordService.save(logChangePassword);
                
                resultVO.setSuccess(true);
                resultVO.setCode("200");
                resultVO.setMessage("更新密码成功");
            }
            else{
                resultVO.setSuccess(false);
                resultVO.setCode("202");
                resultVO.setMessage("原密码输入错误，请重新输入");
            }
            //修改密码 进行加密处理
//            Md5PasswordEncoder md5 = new Md5PasswordEncoder();
//            md5.setEncodeHashAsBase64(false);
//            String pwd = md5.encodePassword(password, null);
            
        } catch (Exception e) {
            resultVO.setSuccess(false);
            resultVO.setCode("500");
            resultVO.setMessage("更新异常");
            e.printStackTrace();
        }
        return resultVO.toString();
    }
    
    @RequestMapping(value = "/checkusername", method = { RequestMethod.GET,
            RequestMethod.POST }, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String checkusername(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ResultVO resultVO = new ResultVO();
        try {
            String username = request.getParameter("username");
            if(memberService.getByUsername(username)!=null){
                resultVO.setSuccess(false);
                resultVO.setCode("501");
                resultVO.setMessage("用户名存在，请重新输入用户名");
                System.out.println(username+"用户名存在，请重新输入用户名");
                return resultVO.toString();
            }
            resultVO.setSuccess(true);
            resultVO.setCode("200");
            resultVO.setMessage("可以使用");
        } catch (Exception e) {
            resultVO.setSuccess(false);
            resultVO.setCode("500");
            resultVO.setMessage("异常");
            e.printStackTrace();
        }
        return resultVO.toString();
    }

}
