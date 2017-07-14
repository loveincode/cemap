package com.hyf.cemap.util.common;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

public class DataTableUtil {

	//偏移量
	private int start;
	//页码
	private int page;
	//页长
	private int length;
	//搜索字
	private String searchValue;
	//排序字段名
	private String orderColumn;
	//排序方式(asc,desc)
	private String orderDirection;
	/*****
	 * 以下参数用于返回
	 ******/
	//记录条数
	@Expose
	private int recordsTotal;   
	//过滤后记录条数
	@Expose
	private int recordsFiltered;   
	//请求次数
	@Expose
	private int draw;   
	//数据记录
	@Expose
	private List data;
	

	public DataTableUtil(HttpServletRequest request)
	{
		this.draw = Integer.valueOf(request.getParameter("draw"));
		this.searchValue = request.getParameter("search[value]");
		this.start = Integer.valueOf(request.getParameter("start"));
		this.length = Integer.valueOf(request.getParameter("length"));
		this.page = this.start/this.length + 1;
		this.orderDirection = request.getParameter("order[0][dir]");
		String orderColumnNum = request.getParameter("order[0][column]");
		this.orderColumn = request.getParameter("columns["+orderColumnNum+"][data]");
	}

	/**
	 * 设置返回参数
	 * @param recordsTotal
	 * @param recordsFiltered
	 * @param data
	 */
	public void setResult(int recordsTotal,int recordsFiltered,List data)
	{
		this.recordsTotal = recordsTotal;
		this.recordsFiltered = recordsFiltered;
		this.data = data;
	}
	
	/**
	 * 获取json格式的返回体
	 * @return
	 */
	public String result(){
//		return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(this);
		return new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create().toJson(this);
	}

	public int getDraw() {
		return draw;
	}

	public int getStart() {
		return start;
	}

	public int getPage() {
		return page;
	}

	public int getLength() {
		return length;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public String getOrderColumn() {
		return orderColumn;
	}

	public String getOrderDirection() {
		return orderDirection;
	}

}
