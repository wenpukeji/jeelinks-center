/**
 * Copyright &copy; 2012-2016 <a href="http://www.jeelinks.com">JeeSite</a> All rights reserved.
 */
package com.wenpu.jeelinks.modules.center.entity;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wenpu.jeelinks.common.persistence.DataEntity;

/**
 * 投票Entity
 * @author webcat
 * @version 2017-06-27
 */
public class TVoteRecord extends DataEntity<TVoteRecord> {
	
	private static final long serialVersionUID = 1L;
	private EUser user;		// user_id
	private String topicId;		// 投票的对象id
	private Date voteTime;		// 投票时间,不用传
	
	public TVoteRecord() {
		super();
	}

	public TVoteRecord(String id){
		super(id);
	}

	
	public EUser getUser() {
		return user;
	}

	public void setUser(EUser user) {
		this.user = user;
	}

	@Length(min=0, max=30, message="投票的对象id长度必须介于 0 和 30 之间")
	public String getTopicId() {
		return topicId;
	}

	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getVoteTime() {
		return voteTime;
	}

	public void setVoteTime(Date voteTime) {
		this.voteTime = voteTime;
	}
	
}