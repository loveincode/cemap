package com.hyf.cemap.controller;

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

import com.hyf.cemap.bean.po.PreachingMeeting;
import com.hyf.cemap.bean.vo.ResultVO;
import com.hyf.cemap.service.PreachingMeetingServiceImpl;
import com.hyf.cemap.service.RecruitmentInformationServiceImpl;
import com.hyf.cemap.util.common.DataTableUtil;

@Controller
@RequestMapping(value = "/preachingmeeting")
public class PreachingMeetingController {
	
	
	@Autowired
	private RecruitmentInformationServiceImpl recruitmentInformationService;
	
	@Autowired
	private PreachingMeetingServiceImpl preachingMeetingService;
	
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
		modelAndView.setViewName("preachingmeeting/index");
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
	    String companyName = request.getParameter("companyName");
        if(companyName != null && companyName.equals("")){
            companyName = null;
        }
        String companyType = request.getParameter("companyType");
        if(companyType != null && companyType.equals("")){
            companyType = null;
        }
        if(companyType != null && companyType.equals("-1")){
            companyType = null;
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
			Integer recordsTotal = preachingMeetingService.listAll(-1, -1).size();
			// 关键字过滤后总记录数
			Integer recordsFiltered = preachingMeetingService.listPreachingMeetingByinfo(companyName,companyType,-1, -1).size();
			dataTableUtil.setResult(recordsTotal, recordsFiltered,
			        preachingMeetingService.listPreachingMeetingByinfo(companyName,companyType,dataTableUtil.getPage(),
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
		modelAndView.setViewName("preachingmeeting/add");
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
			@ModelAttribute("preachingMeeting") PreachingMeeting preachingMeeting,
			HttpServletRequest request, HttpServletResponse response) {
		ResultVO resultVO = new ResultVO();
		UUID uuid = UUID.randomUUID();
		try {
		    preachingMeeting.setUuid(uuid.toString());
		    preachingMeetingService.save(preachingMeeting);
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
		PreachingMeeting preachingMeeting;
		try {
		    preachingMeeting = preachingMeetingService.getByUuid(uuid);
			modelAndView.addObject("preachingMeeting", preachingMeeting);
			modelAndView.setViewName("preachingmeeting/modify");
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
			String companyName = request.getParameter("companyName");
			String companyType = request.getParameter("companyType");
			String place = request.getParameter("place");
            String dayDate = request.getParameter("dayDate");
            String period = request.getParameter("period");
	        String description = request.getParameter("description");
	        PreachingMeeting preachingMeeting = preachingMeetingService.getById(Integer.parseInt(id));
	        preachingMeeting.setCompanyName(companyName);
	        preachingMeeting.setCompanyType(companyType);
	        preachingMeeting.setPlace(place);
	        preachingMeeting.setDayDate(dayDate);
	        preachingMeeting.setPeriod(period);
	        preachingMeeting.setDescription(description);
	        preachingMeetingService.update(preachingMeeting);
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
		    PreachingMeeting preachingMeeting = preachingMeetingService.getByUuid(uuid);
		    preachingMeeting.setDeleted(true);
		    preachingMeetingService.update(preachingMeeting);
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
