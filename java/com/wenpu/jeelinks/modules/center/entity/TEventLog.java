/**
 * Copyright &copy; 2012-2016 <a href="http://www.jeelinks.com">JeeSite</a> All rights reserved.
 */
package com.wenpu.jeelinks.modules.center.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.wenpu.jeelinks.common.persistence.DataEntity;
import com.wenpu.jeelinks.modules.sys.entity.User;

/**
 * 积分日志Entity
 * @author webcat
 * @version 2017-08-12
 */
public class TEventLog extends DataEntity<TEventLog> {
	
	private static final long serialVersionUID = 1L;
	private String eventId;		// event_id
	private int score;		// score
	private EUser user;		// user_id
	private TEvent tEvent;
	
	
	private Date toDay;
	public TEventLog() {
		super();
	}

	
	
	public Date getToDay() {
		return toDay;
	}



	public void setToDay(Date toDay) {
		this.toDay = toDay;
	}



	public TEventLog(String id){
		super(id);
	}

	@Length(min=0, max=32, message="event_id长度必须介于 0 和 32 之间")
	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public EUser getUser() {
		return user;
	}

	public void setUser(EUser user) {
		this.user = user;
	}

	public TEvent gettEvent() {
		return tEvent;
	}

	public void settEvent(TEvent tEvent) {
		this.tEvent = tEvent;
	}
	
}