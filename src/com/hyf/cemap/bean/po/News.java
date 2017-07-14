package com.hyf.cemap.bean.po;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

/**
 * 公告 资讯 文件
 * 
 * @author huyifan
 * @ClassName News
 * @date 2017年2月24日 下午10:32:46
 */
@Entity
@Table(name = "t_news")
public class News {

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

	// 标题
	private String title;

	// 内容
	@Column(columnDefinition = "longtext")
	private String content;

	// 时间
	private Date publishDate;

	// 新闻类型
	@OneToOne(cascade = CascadeType.ALL, optional = true)
	@JoinColumn(name = "newsType_id")
	private NewsType newsType;

	// 点击次数
	private Integer newsClick = 0;
	
    // 附件
    private String attach;
    
    // 附件原名称
    private String attachName;

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public NewsType getNewsType() {
		return newsType;
	}

	public void setNewsType(NewsType newsType) {
		this.newsType = newsType;
	}

	public Integer getNewsClick() {
		return newsClick;
	}

	public void setNewsClick(Integer newsClick) {
		this.newsClick = newsClick;
	}

	public String getAttach() {
		return attach;
	}

	public void setAttach(String attach) {
		this.attach = attach;
	}

	public String getAttachName() {
		return attachName;
	}

	public void setAttachName(String attachName) {
		this.attachName = attachName;
	}
	
}
