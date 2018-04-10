/**
 * Copyright &copy; 2012-2016 <a href="http://www.jeelinks.com">JeeSite</a> All rights reserved.
 */
package com.wenpu.jeelinks.modules.center.entity;

import org.hibernate.validator.constraints.Length;

import com.wenpu.jeelinks.common.persistence.DataEntity;

/**
 * 投票Entity
 * @author webcat
 * @version 2017-06-27
 */
public class TVoteItem extends DataEntity<TVoteItem> {
	
	private static final long serialVersionUID = 1L;
	private String itemId;		// item_id
	private String topicId;		// 点赞的对象id
	private String voteCount;		// 点赞数量统计
	
	public TVoteItem() {
		super();
	}

	public TVoteItem(String id){
		super(id);
	}

	@Length(min=1, max=11, message="item_id长度必须介于 1 和 11 之间")
	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	
	@Length(min=0, max=30, message="点赞的对象id长度必须介于 0 和 30 之间")
	public String getTopicId() {
		return topicId;
	}

	public void setTopicId(String topicId) {
		this.topicId = topicId;
	}
	
	@Length(min=1, max=11, message="点赞数量统计长度必须介于 1 和 11 之间")
	public String getVoteCount() {
		return voteCount;
	}

	public void setVoteCount(String voteCount) {
		this.voteCount = voteCount;
	}
	
}