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

/**
 * 登录登出日志表
*  @author  huyifan
*  @ClassName  LogInOut  
*  @date  Mar 29, 2017 10:50:09 AM
 */
@Entity
@Table(name = "t_loginout")
public class LogInOut {
    
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
    
    // 用户名称
    private String username;
    
    // 时间
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "MM/dd/yyyy HH:mm:ss")
    private Date date;
    
    // ip
    private String ipAdress;
    
    // 操作 登录 退出
    private String action;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getIpAdress() {
        return ipAdress;
    }

    public void setIpAdress(String ipAdress) {
        this.ipAdress = ipAdress;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
    

}
