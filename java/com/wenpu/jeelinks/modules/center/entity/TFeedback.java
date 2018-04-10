/**
 * Copyright &copy; 2012-2016 <a href="http://www.jeelinks.com">JeeSite</a> All rights reserved.
 */
package com.wenpu.jeelinks.modules.center.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.wenpu.jeelinks.common.persistence.DataEntity;
import com.wenpu.jeelinks.modules.sys.entity.User;

import javax.validation.constraints.NotNull;

/**
 * 用户反馈Entity
 * @author webcat
 * @version 2017-08-25
 */
public class TFeedback extends DataEntity<TFeedback> {
	
	private static final long serialVersionUID = 1L;
	private String userId;		// 用户ID，为空表示未登录用户
	private String userName;		// 姓名
	private String email;		// 邮箱
	private String phone;		// 电话
	private String content;		// 反馈内容
	private Date createTime;		// 反馈时间
	private String comefrom;		// 来源
	private String status;		// 处理状态 0-未处理 1-已处理
	private String memo;		// 备注
	private User handleUserId;		// 处理人
	private String qq;		// qq
	private String contentType;		// 处理类型
	private String handleResult;		// 处理结果
	private Date hanleDate;		// 处理日期
	private Date beginCreateTime;		// 开始 反馈时间
	private Date endCreateTime;		// 结束 反馈时间
	
	public TFeedback() {
		super();
	}

	public TFeedback(String id){
		super(id);
	}

	@Length(min=0, max=20, message="用户ID，为空表示未登录用户长度必须介于 0 和 20 之间")
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Length(min=0, max=60, message="姓名长度必须介于 0 和 60 之间")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Length(min=0, max=80, message="邮箱长度必须介于 0 和 80 之间")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Length(min=0, max=13, message="电话长度必须介于 0 和 13 之间")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Length(min=1, max=500, message="反馈内容长度必须介于 1 和 500 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="反馈时间不能为空")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Length(min=0, max=6, message="来源长度必须介于 0 和 6 之间")
	public String getComefrom() {
		return comefrom;
	}

	public void setComefrom(String comefrom) {
		this.comefrom = comefrom;
	}
	
	@Length(min=1, max=4, message="处理状态 0-未处理 1-已处理长度必须介于 1 和 4 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=200, message="备注长度必须介于 0 和 200 之间")
	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	public User getHandleUserId() {
		return handleUserId;
	}

	public void setHandleUserId(User handleUserId) {
		this.handleUserId = handleUserId;
	}
	
	@Length(min=0, max=10, message="qq长度必须介于 0 和 10 之间")
	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}
	
	@Length(min=0, max=50, message="处理类型长度必须介于 0 和 50 之间")
	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	@Length(min=0, max=1000, message="处理结果长度必须介于 0 和 1000 之间")
	public String getHandleResult() {
		return handleResult;
	}

	public void setHandleResult(String handleResult) {
		this.handleResult = handleResult;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getHanleDate() {
		return hanleDate;
	}

	public void setHanleDate(Date hanleDate) {
		this.hanleDate = hanleDate;
	}
	
	public Date getBeginCreateTime() {
		return beginCreateTime;
	}

	public void setBeginCreateTime(Date beginCreateTime) {
		this.beginCreateTime = beginCreateTime;
	}
	
	public Date getEndCreateTime() {
		return endCreateTime;
	}

	public void setEndCreateTime(Date endCreateTime) {
		this.endCreateTime = endCreateTime;
	}
		
}