package com.hyf.cemap.bean.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

/**
 * 就业信息
*  @author  huyifan
*  @ClassName  EmploymentInformation  
*  @date  2017年2月24日 下午9:20:24
 */
@Entity
@Table(name = "t_employmentinformation")
public class EmploymentInformation {
	
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
	
	//就业方式 （签就业协议书 签合同 升学 出国 自主创业 参军）
    private String employmentMethod;
    
	// 用人单位名称（全称）
	private String companyFullName;
	
	// 性质  
	private String nature;
	
	// 联系人    
    private String companyContactName;
    
    // 电话            
    private String companyContactPhone;
    
    // 单位地址
    private String companyPlace;
    
    // 单位省份
    private String companyProvince;
   
    // 邮政编码
    private String postcode;
    
    // 单位email
    private String email;

    /**组织机构码（9位）或者工商注册号（15位）或社会信用代码（18位数）
     * (查询方法1、http://www.nacao.org.cn/；
     * 2、http://gsxt.gdgs.gov.cn/aiccips/index；
     * 3、百度“组织机构代码查询”，进入百度应用即可查询)
     */
    private String organizationCode;
    
    // 职位类别
    private String occupationType;
    
    // 单位行业类别
    private String industryType;
	
    //就业薪资     
    private Integer employmentPay;
    
	//升学学校
    private String university;
    
    //升学专业
    private String major;

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

    public String getEmploymentMethod() {
        return employmentMethod;
    }

    public void setEmploymentMethod(String employmentMethod) {
        this.employmentMethod = employmentMethod;
    }

    public String getCompanyFullName() {
        return companyFullName;
    }

    public void setCompanyFullName(String companyFullName) {
        this.companyFullName = companyFullName;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getCompanyContactName() {
        return companyContactName;
    }

    public void setCompanyContactName(String companyContactName) {
        this.companyContactName = companyContactName;
    }

    public String getCompanyContactPhone() {
        return companyContactPhone;
    }

    public void setCompanyContactPhone(String companyContactPhone) {
        this.companyContactPhone = companyContactPhone;
    }

    public String getCompanyPlace() {
        return companyPlace;
    }

    public void setCompanyPlace(String companyPlace) {
        this.companyPlace = companyPlace;
    }
    
    public String getCompanyProvince() {
        return companyProvince;
    }

    public void setCompanyProvince(String companyProvince) {
        this.companyProvince = companyProvince;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    public String getOccupationType() {
        return occupationType;
    }

    public void setOccupationType(String occupationType) {
        this.occupationType = occupationType;
    }

    public String getIndustryType() {
        return industryType;
    }

    public void setIndustryType(String industryType) {
        this.industryType = industryType;
    }

    public Integer getEmploymentPay() {
        return employmentPay;
    }

    public void setEmploymentPay(Integer employmentPay) {
        this.employmentPay = employmentPay;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
	
}
