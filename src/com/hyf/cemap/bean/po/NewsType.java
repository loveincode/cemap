package com.hyf.cemap.bean.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity
@Table(name = "t_newstype")
public class NewsType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Expose
	protected Integer id;
	
	// uuid
	private String Uuid;
	
	// 是否逻辑删除(0 未删除 1已删除)
	@Column(columnDefinition = "bit NOT NULL DEFAULT 0")
	@Expose
	protected boolean deleted = false;
    
	//类型代码
    private String code;  
      
    //类型名称
    private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUuid() {
		return Uuid;
	}

	public void setUuid(String uuid) {
		Uuid = uuid;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
