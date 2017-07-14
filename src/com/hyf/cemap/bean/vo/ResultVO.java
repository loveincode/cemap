package com.hyf.cemap.bean.vo;

import java.io.Serializable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/**
 * json返回对象
*  @author  huyifan
*  @ClassName  ResultVO  
*  @date  2017年3月8日 下午3:08:06
 */
public class ResultVO implements Serializable{

	private static final long serialVersionUID = 3148486098477627019L;

	/**
	 * 是否成功
	 */
	private boolean success;
	/**
	 * 错误码
	 */
	private String code;
	/**
	 * 消息
	 */
	private String message;
	/**
	 * 数据包
	 */
	private Object data;
	
	public ResultVO(){
		
	}
	
	public ResultVO(boolean success, String code, String message)
	{
		this.success = success;
		this.code = code;
		this.message = message;
	}
	
	public ResultVO(boolean success, String code, String message, Object data)
	{
		this.success = success;
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		Gson gson = new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		return gson.toJson(this);
	}
	
}
