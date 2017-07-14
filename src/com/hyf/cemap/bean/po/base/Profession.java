package com.hyf.cemap.bean.po.base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

/**
 * 专业实体
 * 
 * @author huyifan
 * @ClassName Profession
 * @date 2017年2月24日 下午8:51:30
 */
@Entity
@Table(name = "t_profession")
public class Profession {

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
	
	// 专业名称
	private String name;
	
	// 专业Code
	private String code;
	
	// 学院 collegeId
	private Integer collegeId;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(Integer collegeId) {
		this.collegeId = collegeId;
	}

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
	
}
