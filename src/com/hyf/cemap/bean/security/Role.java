package com.hyf.cemap.bean.security;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 角色表
*/
@Entity  
@Table(name = "role") 
public class Role {
	
	@Id  
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;  
	
	// uuid
	private String Uuid;
    
    private String code;  
      
    private String name;  
      
//    @ManyToMany(targetEntity = Member.class,fetch = FetchType.EAGER, mappedBy = "roles")
//    private Set<Member> members = new HashSet<Member>();
    
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
	
//	public Set<Member> getMembers() {
//		return members;
//	}
//	public void setMembers(Set<Member> members) {
//		this.members = members;
//	}

}
