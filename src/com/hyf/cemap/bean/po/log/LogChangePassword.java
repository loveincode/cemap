package com.hyf.cemap.bean.po.log;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.google.gson.annotations.Expose;
@Entity
@Table(name = "t_logchangeword")
public class LogChangePassword {
    
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
    
    // 操作者用户名称
    private String username_x;
    
    // 被修改用户名称
    private String username_c;
    
    // 修改类型 重置密码 个人修改
    private String changeType;
    
    // 时间
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "MM/dd/yyyy HH:mm:ss")
    private Date date;
    
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

    public String getUsername_x() {
        return username_x;
    }

    public void setUsername_x(String username_x) {
        this.username_x = username_x;
    }

    public String getUsername_c() {
        return username_c;
    }

    public void setUsername_c(String username_c) {
        this.username_c = username_c;
    }

    public String getChangeType() {
        return changeType;
    }

    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
