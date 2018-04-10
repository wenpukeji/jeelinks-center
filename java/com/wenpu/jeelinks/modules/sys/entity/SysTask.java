/**
 * Copyright &copy; 2012-2016 <a href="http://www.jeelinks.com">JeeSite</a> All rights reserved.
 */
package com.wenpu.jeelinks.modules.sys.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.wenpu.jeelinks.common.persistence.DataEntity;

/**
 * 系统任务调度Entity
 * @author webcat
 * @version 2017-09-08
 */
public class SysTask extends DataEntity<SysTask> {
	
	private static final long serialVersionUID = 1L;
	private String taskName;		// 任务名称
	private String taskDesc;		// 任务描述
	private String taskPeriod;		// 周期
	private Date startDate;		// 开始日期
	private Date lastDate;		// 最后一次日期
	private String isNormal;		// 是否正常
	
	public SysTask() {
		super();
	}

	public SysTask(String id){
		super(id);
	}

	@Length(min=0, max=100, message="任务名称长度必须介于 0 和 100 之间")
	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	
	@Length(min=0, max=1000, message="任务描述长度必须介于 0 和 1000 之间")
	public String getTaskDesc() {
		return taskDesc;
	}

	public void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc;
	}
	
	@Length(min=0, max=32, message="周期长度必须介于 0 和 32 之间")
	public String getTaskPeriod() {
		return taskPeriod;
	}

	public void setTaskPeriod(String taskPeriod) {
		this.taskPeriod = taskPeriod;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getLastDate() {
		return lastDate;
	}

	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}
	
	@Length(min=0, max=2, message="是否正常长度必须介于 0 和 2 之间")
	public String getIsNormal() {
		return isNormal;
	}

	public void setIsNormal(String isNormal) {
		this.isNormal = isNormal;
	}
	
}