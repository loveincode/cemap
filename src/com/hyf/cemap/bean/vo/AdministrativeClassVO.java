package com.hyf.cemap.bean.vo;

public class AdministrativeClassVO {
    
    private Integer id;

    private String Uuid;

    // 专业名称
    private String name;

    // 专业Code
    private String code;
    
    // 从college表中name 学院名称
    private String collegeName;
    
    // 从profession表中name 专业名称
    private String professionName;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getProfessionName() {
        return professionName;
    }

    public void setProfessionName(String professionName) {
        this.professionName = professionName;
    }
    
    

}
