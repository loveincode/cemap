package com.hyf.cemap.bean.po.base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

/**
 * 行政班级
 * 
 * @author huyifan
 * @ClassName AdministrativeClass
 * @date 2017年2月24日 下午8:56:51
 */
@Entity
@Table(name = "t_administrativeclass")
public class AdministrativeClass {

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

	// 班级名称
	private String name;
	
	// 班级Code
	private String code;

	// 学院 collegeId
	private Integer collegeId;

	// 专业 ProfessionId
	private Integer professionId;

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

	public Integer getProfessionId() {
		return professionId;
	}

    public void setProfessionId(Integer professionId) {
        this.professionId = professionId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
	
}
