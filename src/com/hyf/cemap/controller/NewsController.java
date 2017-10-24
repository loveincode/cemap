package com.hyf.cemap.controller;

import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.hyf.cemap.bean.po.News;
import com.hyf.cemap.bean.po.NewsType;
import com.hyf.cemap.bean.vo.LigerGridVO;
import com.hyf.cemap.bean.vo.ResultVO;
import com.hyf.cemap.service.NewsServiceImpl;
import com.hyf.cemap.service.NewsTypeServiceImpl;
import com.hyf.cemap.util.common.DataTableUtil;

@Controller
@RequestMapping(value = "/news")
public class NewsController {
	
	@Autowired
	private NewsServiceImpl newsService;
	
	@Autowired
	private NewsTypeServiceImpl newsTypeService;
	
	
	
	/**
	 * 主页
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/ligeruitable", method = { RequestMethod.GET })
	public ModelAndView ligeruitable(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		List<NewsType> newsTypes = newsTypeService.listAll("id","ace");
		HttpSession session = request.getSession();
		System.out.println(session.toString());
        modelAndView.addObject("newsTypes", newsTypes);
		modelAndView.setViewName("news/ligeruitable");
		return modelAndView;
	}
	
	/**
	 * 获取数据
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/dataui", method = { RequestMethod.POST }, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String dataui(HttpServletRequest request, HttpServletResponse response) {
		
		Enumeration enu=request.getParameterNames();  
		while(enu.hasMoreElements()){  
		String paraName=(String)enu.nextElement();  
		System.out.println(paraName+": "+request.getParameter(paraName));  
		}  
		
		Integer page = Integer.parseInt(request.getParameter("page"));
		Integer pagesize = Integer.parseInt(request.getParameter("pagesize"));  
		
		System.out.println("page:"+page+"pagesize:"+pagesize);
	    String sortname = request.getParameter("sortname");  
	    String sortorder = request.getParameter("sortorder"); 
	    
	    System.out.println("sortname:"+sortname+"sortorder:"+sortorder);
	    
	    
	    /**
		 * 获取查询参数
		 */
        String title = request.getParameter("newsTitle");
        if(title != null && title.equals("")){
            title = null;
        }
        String newsTypeID = request.getParameter("newsTypeID");
        if(newsTypeID != null &&  newsTypeID.equals("")){
            newsTypeID = null;
        }
        Integer typeid=-1;
        if(newsTypeID!=null){
            typeid = Integer.parseInt(newsTypeID);
        }
        System.out.println("title= "+title+"typeid="+typeid);
	    
	    // 总记录数
	 	Integer recordsTotal = newsService.listNewsByinfo(title, typeid, -1, -1).size();
	    List<News> news =  newsService.listNewsByinfosort(title, typeid,page,pagesize,sortname,sortorder);
	    
	    JsonObject json = new JsonObject();
	    JsonArray newsListJson = new JsonArray();
		for(News onenews : news){
			JsonObject newsjson = new JsonObject();
			
			newsjson.addProperty("id", onenews.getId());
			newsjson.addProperty("title", onenews.getTitle());
			newsjson.addProperty("publishDate", onenews.getPublishDate().toString());
			newsjson.addProperty("newsClick", onenews.getNewsClick());
			
			newsListJson.add(newsjson);
		}
		json.add("Rows", newsListJson);
		//json.add("Rows", newsListJson.);
		json.addProperty("Total", recordsTotal);
		
		LigerGridVO ligerGridVO = new LigerGridVO();
		ligerGridVO.setRows(news);
		ligerGridVO.setTotal(recordsTotal);
	 	//return json.toString();
		return ligerGridVO.toString();
	}
	
	
	
	
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
		List<NewsType> newsTypes = newsTypeService.listAll("id","ace");
		HttpSession session = request.getSession();
		System.out.println(session.toString());
        modelAndView.addObject("newsTypes", newsTypes);
		modelAndView.setViewName("news/index");
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
        String title = request.getParameter("newsTitle");
        if(title != null && title.equals("")){
            title = null;
        }
        String newsTypeID = request.getParameter("newsTypeID");
        if(newsTypeID != null &&  newsTypeID.equals("")){
            newsTypeID = null;
        }
        Integer typeid=-1;
        if(newsTypeID!=null){
            typeid = Integer.parseInt(newsTypeID);
        }
        
		DataTableUtil dataTableUtil = new DataTableUtil(request);
		System.out.println("===================================");
		System.out.println("起始偏移=" + dataTableUtil.getStart());
		System.out.println("页长=" + dataTableUtil.getLength());
		System.out.println("页码=" + dataTableUtil.getPage());
		System.out.println("排序字段=" + dataTableUtil.getOrderColumn());
		System.out.println("排序顺序=" + dataTableUtil.getOrderDirection());
		System.out.println("搜索关键�?=" + dataTableUtil.getSearchValue());
		System.out.println("===================================");
		try {
			// 总记录数
			Integer recordsTotal = newsService.listNewsByinfo(null, -1, -1, -1).size();
			// 关键字过滤后总记录数
			Integer recordsFiltered = newsService.listNewsByinfo(title, typeid, -1, -1).size();
			dataTableUtil.setResult(recordsTotal, recordsFiltered,
					newsService.listNewsByinfo(title, typeid,dataTableUtil.getPage(),
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
		List<NewsType> newsTypes = newsTypeService.listAll("id","ace");
		modelAndView.addObject("newsTypes", newsTypes);
		modelAndView.setViewName("news/add");
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
			@ModelAttribute("news") News news,
			HttpServletRequest request, HttpServletResponse response) {
		ResultVO resultVO = new ResultVO();
		UUID uuid = UUID.randomUUID();
		try {
			String newsTypeID = request.getParameter("newsTypeID");
			NewsType newsType = newsTypeService.getById(Integer.parseInt(newsTypeID));
			news.setPublishDate(new Date());
			news.setNewsType(newsType);
			news.setUuid(uuid.toString());
			newsService.save(news);
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
		try {
			News news = newsService.getByUuid(uuid);
			modelAndView.addObject("news", news);
			List<NewsType> newsTypes = newsTypeService.listAll("id","ace");
			modelAndView.addObject("newsTypes", newsTypes);
			modelAndView.setViewName("news/modify");
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
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String attach = request.getParameter("attach");
			String attachName = request.getParameter("attachName");
			String newsTypeID = request.getParameter("newsTypeID"); 
			
			News news = newsService.getById(Integer.parseInt(id));
			NewsType newsType = newsTypeService.getById(Integer.parseInt(newsTypeID));
			
			news.setTitle(title);
			news.setNewsType(newsType);
			news.setContent(content);
			news.setAttach(attach);
			news.setAttachName(attachName);
			newsService.update(news);
			
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
	@Transactional(propagation=Propagation.REQUIRED)
	public @ResponseBody String delete(
			@RequestParam(value = "uuid", required = true) String uuid,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ResultVO resultVO = new ResultVO();
		
		
		try {
			News news = newsService.getByUuid(uuid);
			news.setDeleted(true);
			newsService.update(news);
//			newsService.deletetx(uuid);
			resultVO.setSuccess(true);
			resultVO.setCode("200");
			resultVO.setMessage("删除成功");
			
		} catch (Exception e) {
			resultVO.setSuccess(false);
			resultVO.setCode("500");
			resultVO.setMessage("删除异常");
			e.printStackTrace();
			throw new RuntimeException();
		}
		return resultVO.toString();
	}

}
