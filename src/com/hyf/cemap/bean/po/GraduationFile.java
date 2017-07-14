package com.hyf.cemap.bean.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

/**
 * 档案信息
 * 
 * @author huyifan
 * @ClassName GraduationFile
 * @date 2017年2月24日 下午9:20:56
 */
@Entity
@Table(name = "t_graduationfile")
public class GraduationFile {
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
	
	// 学号
	private String studentId;
	
	// 接受档案机构名称
	private String institutionName;

	// 机构地址
	private String institutionPlace;

	// 机构电话
	private String institutionPhone;

	// 转出方式
	private String outWay;

	// 转出时间
	private String outDate;

	// 描述
	private String description;

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
	
	public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getInstitutionName() {
		return institutionName;
	}

	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}

	public String getInstitutionPlace() {
		return institutionPlace;
	}

	public void setInstitutionPlace(String institutionPlace) {
		this.institutionPlace = institutionPlace;
	}

	public String getInstitutionPhone() {
		return institutionPhone;
	}

	public void setInstitutionPhone(String institutionPhone) {
		this.institutionPhone = institutionPhone;
	}

	public String getOutWay() {
		return outWay;
	}

	public void setOutWay(String outWay) {
		this.outWay = outWay;
	}

	public String getOutDate() {
		return outDate;
	}

	public void setOutDate(String outDate) {
		this.outDate = outDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
