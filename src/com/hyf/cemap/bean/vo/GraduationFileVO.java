package com.hyf.cemap.bean.vo;

public class GraduationFileVO {
    
    private Integer id;

    // 存放档案信息的UUid
    private String Uuid;

    // 学号
    private String studentId;
    
    // 姓名 从GraduateInformation表中 name
    private String name;
    
    // 从administrative表中 name 班级名称
    private String className;
    
    // 从profession表中name 专业名称
    private String professionName;
    
    // 从college表中name 学院名称
    private String collegeName;
    
    // 档案信息 1表示存在 2表示不存在
    private Integer state;

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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getProfessionName() {
        return professionName;
    }

    public void setProfessionName(String professionName) {
        this.professionName = professionName;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
    

}
