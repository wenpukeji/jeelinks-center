/**
 * Copyright &copy; 2012-2016 <a href="http://www.jeelinks.com">JeeSite</a> All rights reserved.
 */
package com.wenpu.jeelinks.modules.center.entity;

import org.hibernate.validator.constraints.Length;

import com.wenpu.jeelinks.common.persistence.DataEntity;
import com.wenpu.jeelinks.modules.center.CenterUser;

/**
 * 用户中心Entity
 * @author webcat
 * @version 2017-07-08
 */
public class EUser extends DataEntity<EUser> {
	
	private static final long serialVersionUID = 1L;
	private String userName;		// user_name
	private String userPassword;		// user_password
	private String userGroup;		// 1.学生组 2.老师组
	private String nickName;
	private int point;
	
	//查询字段
	private String photo;
	
	
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public EUser() {
		super();
	}

	public EUser(String id){
		super(id);
	}

	@Length(min=0, max=100, message="user_name长度必须介于 0 和 100 之间")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Length(min=0, max=100, message="user_password长度必须介于 0 和 100 之间")
	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(String userGroup) {
		this.userGroup = userGroup;
	}
	
	
	
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public CenterUser toLoginUser() {
		CenterUser lu = new CenterUser();
		lu.setUserId(this.getId());
		lu.setUserName(this.getUserName());
		lu.setGroup(this.getUserGroup());
		lu.setNickName(this.getNickName());
		lu.setPoint(this.getPoint());
		lu.setPhoto(this.getPhoto());
		return lu;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}
	
	
	
}