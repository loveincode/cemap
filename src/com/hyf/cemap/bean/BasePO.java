package com.hyf.cemap.bean;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.google.gson.annotations.Expose;

@MappedSuperclass
public class BasePO {

	@Id  
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Expose  
	protected int id;
	
	//创建日期
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "MM/dd/yyyy HH:mm:ss")
	@Column(columnDefinition="datetime NOT NULL DEFAULT CURRENT_TIMESTAMP")
	@Expose
	protected Date create_date = new Date();
		
	// 更新日期
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "MM/dd/yyyy HH:mm:ss")
	@Column(columnDefinition="datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	@Expose
	protected Date update_date = new Date();
		
	//创建人
	@Column(columnDefinition="varchar(20) NOT NULL DEFAULT 'sys'")
	protected String create_man="sys";
		
	//修改人
	@Column(columnDefinition="varchar(20) NOT NULL DEFAULT 'sys'")
	protected String update_man = "sys";
		
	//是否逻辑删除(0 未删除 1已删除)
	@Column(columnDefinition="bit NOT NULL DEFAULT 0")
	@Expose  
	protected boolean deleted = false;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public Date getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}

	public String getCreate_man() {
		return create_man;
	}

	public void setCreate_man(String create_man) {
		this.create_man = create_man;
	}

	public String getUpdate_man() {
		return update_man;
	}

	public void setUpdate_man(String update_man) {
		this.update_man = update_man;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	
}
