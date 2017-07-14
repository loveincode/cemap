package com.hyf.cemap.bean.vo;

import java.io.Serializable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/**
 * 
 * @author huyifan
 *
 */
public class LigerGridVO implements Serializable{

	private static final long serialVersionUID = 3148486098477627019L;

	/**
	 * rows
	 */
	private Object Rows;
	/**
	 * Totals
	 */
	private Integer Total;
	
	public LigerGridVO(){
		
	}
	
	
	public LigerGridVO(Object Rows,Integer Total)
	{
		this.Rows = Rows;
		this.Total = Total;
	}

	
	public Object getRows() {
		return Rows;
	}


	public void setRows(Object rows) {
		Rows = rows;
	}


	public Integer getTotal() {
		return Total;
	}


	public void setTotal(Integer total) {
		Total = total;
	}


	@Override
	public String toString() {
		Gson gson = new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		return gson.toJson(this);
	}
	
}
