package com.hyf.cemap.controller;

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

import com.alibaba.fastjson.JSONObject;
import com.hyf.cemap.bean.po.GraduateInformation;
import com.hyf.cemap.bean.po.NewsType;
import com.hyf.cemap.bean.vo.ResultVO;
import com.hyf.cemap.service.GraduateInformationServiceImpl;
import com.hyf.cemap.service.NewsTypeServiceImpl;
import com.hyf.cemap.util.common.DataTableUtil;
/**
 * 就业信息Controller
*  @author  huyifan
*  @ClassName  employmentInformationController  
*  @date  2017年3月8日 下午4:59:41
 */
@Controller
@RequestMapping(value = "/employmentinformation")
public class EmploymentInformationController {
	
	@Autowired
	private NewsTypeServiceImpl newsTypeService;
	
	@Autowired 
	private GraduateInformationServiceImpl graduateInformationServiceImpl;
	
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
//		modelAndView.setViewName("newstype/index");
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
			Integer recordsTotal = graduateInformationServiceImpl.listAll(-1, -1).size();
			// 关键字过滤后总记录数
			Integer recordsFiltered = graduateInformationServiceImpl.listAll(-1, -1).size();
			dataTableUtil.setResult(recordsTotal, recordsFiltered,
					graduateInformationServiceImpl.listAll(dataTableUtil.getPage(),
								dataTableUtil.getLength()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
		modelAndView.setViewName("employmentinformation/add");
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
		JSONObject json = new JSONObject();
		try {
			graduateInformationServiceImpl.save(graduateInformation);
			json.put("resultCode", 200);
		} catch (Exception e) {
			json.put("resultCode", 500);
			e.printStackTrace();
		}
		return json.toJSONString();
	}
	
	/**
	 * 打开修改页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/modifyPage", method = { RequestMethod.GET })
	public ModelAndView modifyPage(
			@RequestParam(value = "id", required = true) Integer id,
			HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView();
		NewsType newsType;
		try {
			newsType = newsTypeService.getById(id);
			modelAndView.addObject("newsType", newsType);
			modelAndView.setViewName("newstype/modify");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return modelAndView;
	}
	
	@RequestMapping(value = "/update", method = { RequestMethod.GET,
			RequestMethod.POST }, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String update(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		JSONObject json = new JSONObject();
		try {
			String id = request.getParameter("id");
			String code = request.getParameter("code");
			String name = request.getParameter("name");
			NewsType newsType = newsTypeService.getById(Integer.parseInt(id));
			newsType.setCode(code);
			newsType.setName(name);
			newsTypeService.save(newsType);
			json.put("resultCode", 200);
		} catch (Exception e) {
			json.put("resultCode", 500);
			e.printStackTrace();
		}
		return json.toJSONString();
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
			@RequestParam(value = "id", required = true) Integer id,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ResultVO resultVO = new ResultVO();
		try {
			NewsType newsType = newsTypeService.getById(id);
			newsType.setDeleted(true);
			newsTypeService.update(newsType);
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
