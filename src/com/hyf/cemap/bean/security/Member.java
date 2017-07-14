package com.hyf.cemap.bean.security;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * 用户表
*/
@Entity  
@Table(name = "member")
public class Member {
	
	//用户ID号
	@Id  
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	// uuid
	private String Uuid;
	
	//注册用户名
	private	String username;
	
	//注册密码
	private	String password;
	
	//用户状态
	private	boolean deleted;
	
	//用户手机号
	private String phone;
	
	//真实姓名
	private String realname;
	
	//邮箱
	private String email;
	
	/**
	 * 角色权限
	 * 1 管理员
	 * 2 就业处
	 * 3 辅导员
	 * 4 学生
	 */
	private Integer tempRoleId;
	
	//管理班级 辅导员所用属性
	private String manageClass;
	
	//班级Id 学生所用属性
	private Integer classId;
	
	//角色列表
	@ManyToMany(targetEntity = Role.class,fetch = FetchType.EAGER)  
    @JoinTable(name="member_role" , joinColumns = {  
            @JoinColumn(name = "member_id",referencedColumnName="id")  
    }, inverseJoinColumns = {@JoinColumn(name="role_id",referencedColumnName="id")})
	private Set<Role> roles = new HashSet<Role>();	
	
	/*******************SET GET METHOD***********************************************/
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getUuid() {
		return Uuid;
	}

	public void setUuid(String uuid) {
		Uuid = uuid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

    public String getManageClass() {
        return manageClass;
    }

    public void setManageClass(String manageClass) {
        this.manageClass = manageClass;
    }

    public Integer getTempRoleId() {
        return tempRoleId;
    }

    public void setTempRoleId(Integer tempRoleId) {
        this.tempRoleId = tempRoleId;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }
    
}
