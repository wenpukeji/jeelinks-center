/**
 * Copyright &copy; 2012-2016 <a href="http://www.jeelinks.com">JeeSite</a> All rights reserved.
 */
package com.wenpu.jeelinks.modules.center.entity;

import org.hibernate.validator.constraints.Length;

import com.wenpu.jeelinks.common.persistence.DataEntity;
import com.wenpu.jeelinks.modules.sys.entity.User;

/**
 * 积分事件Entity
 * @author webcat
 * @version 2017-08-13
 */
public class TEvent extends DataEntity<TEvent> {
	
	private static final long serialVersionUID = 1L;
	private String eventName;		// 事件名称
	private String eventCode;		// 事件编码
	private int eventScore;		// 积分
	private int dayLimit;       //积分每日上限
	private String enable;		// 是否有效
	private String eventScope;		// 适用范围
	
	
	public TEvent() {
		super();
	}

	

	public TEvent(String id){
		super(id);
	}

	@Length(min=0, max=100, message="事件名称长度必须介于 0 和 100 之间")
	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	
	@Length(min=0, max=32, message="事件编码长度必须介于 0 和 32 之间")
	public String getEventCode() {
		return eventCode;
	}

	public void setEventCode(String eventCode) {
		this.eventCode = eventCode;
	}
	
	
	public int getEventScore() {
		return eventScore;
	}

	public void setEventScore(int eventScore) {
		this.eventScore = eventScore;
	}
	
	@Length(min=0, max=2, message="是否有效长度必须介于 0 和 2 之间")
	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}
	
	@Length(min=0, max=2, message="适用范围长度必须介于 0 和 2 之间")
	public String getEventScope() {
		return eventScope;
	}

	public void setEventScope(String eventScope) {
		this.eventScope = eventScope;
	}

	public int getDayLimit() {
		return dayLimit;
	}

	public void setDayLimit(int dayLimit) {
		this.dayLimit = dayLimit;
	}
	
}