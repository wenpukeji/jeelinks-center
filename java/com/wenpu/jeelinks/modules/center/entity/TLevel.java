/**
 * Copyright &copy; 2012-2016 <a href="http://www.jeelinks.com">JeeSite</a> All rights reserved.
 */
package com.wenpu.jeelinks.modules.center.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.wenpu.jeelinks.common.persistence.DataEntity;

/**
 * 用户等级Entity
 * @author webcat
 * @version 2017-08-20
 */
public class TLevel extends DataEntity<TLevel> {
	
	private static final long serialVersionUID = 1L;
	private String levelName;		// 名称
	private String levelScope;		// 适用范围
	private String fromPoint;		// 分值下限
	private String toPoint;		// 分值上限
	private Date upateDate;		// 更新时间
	
	
	//查询参数
	private Integer points;
	public TLevel() {
		super();
	}

	public TLevel(String id){
		super(id);
	}

	@Length(min=0, max=32, message="名称长度必须介于 0 和 32 之间")
	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	
	@Length(min=0, max=2, message="适用范围长度必须介于 0 和 2 之间")
	public String getLevelScope() {
		return levelScope;
	}

	public void setLevelScope(String levelScope) {
		this.levelScope = levelScope;
	}
	
	@Length(min=0, max=11111, message="分值下限长度必须介于 0 和 11 之间")
	public String getFromPoint() {
		return fromPoint;
	}

	public void setFromPoint(String fromPoint) {
		this.fromPoint = fromPoint;
	}
	
	@Length(min=0, max=11111, message="分值上限长度必须介于 0 和 11 之间")
	public String getToPoint() {
		return toPoint;
	}

	public void setToPoint(String toPoint) {
		this.toPoint = toPoint;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpateDate() {
		return upateDate;
	}

	public void setUpateDate(Date upateDate) {
		this.upateDate = upateDate;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}
	
}