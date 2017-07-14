package com.hyf.cemap.bean.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

/**
 * 毕业生信息
 * 
 * @author huyifan
 * @ClassName GraduateInformation
 * @date 2017年2月24日 下午9:20:43
 */
@Entity
@Table(name = "t_graduateinformation")
public class GraduateInformation {

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

	// 姓名
	private String name;

	// 性别
	private String sex;

	// 出生日期
	private String birthday;

	// 院系
	private Integer collegeId;

	// 专业
	private Integer professionId;

	// 班级
	private Integer administrativeClassId;

	// 政治面貌 群众 团员 党员
    private String politicalStatus;
	
    // 籍贯
    private String nativePlace;
    
	// 民族
	private String nation;

	// 身份证号码
	private String identificationNumber;

	// 户口性质 （农村 城市）
    private String accountproperty;
	
	// 户口所在地
	private String accountLocation;

	// 生源地 省份
	private String originPlace;

	// 学历 （专科 本科 硕士 博士 博士后）
	private String educationBackground;

	// 手机号码
	private String phone;

	// 电子邮箱
	private String email;

	// 家庭地址
	private String homeAddress;

	// 家庭联系方式
	private String homePhone;
	
	// 毕业届数
    private String graduationSession;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
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

    public Integer getAdministrativeClassId() {
        return administrativeClassId;
    }

    public void setAdministrativeClassId(Integer administrativeClassId) {
        this.administrativeClassId = administrativeClassId;
    }

    public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getPoliticalStatus() {
		return politicalStatus;
	}

	public void setPoliticalStatus(String politicalStatus) {
		this.politicalStatus = politicalStatus;
	}

	public String getNativePlace() {
		return nativePlace;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}

	public String getIdentificationNumber() {
		return identificationNumber;
	}

	public void setIdentificationNumber(String identificationNumber) {
		this.identificationNumber = identificationNumber;
	}

	public String getAccountLocation() {
		return accountLocation;
	}

	public void setAccountLocation(String accountLocation) {
		this.accountLocation = accountLocation;
	}

	public String getAccountproperty() {
		return accountproperty;
	}

	public void setAccountproperty(String accountproperty) {
		this.accountproperty = accountproperty;
	}

	public String getOriginPlace() {
		return originPlace;
	}

	public void setOriginPlace(String originPlace) {
		this.originPlace = originPlace;
	}

	public String getEducationBackground() {
		return educationBackground;
	}

	public void setEducationBackground(String educationBackground) {
		this.educationBackground = educationBackground;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

    public String getGraduationSession() {
        return graduationSession;
    }

    public void setGraduationSession(String graduationSession) {
        this.graduationSession = graduationSession;
    }
	
}
