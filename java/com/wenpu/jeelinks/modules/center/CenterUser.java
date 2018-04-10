package com.wenpu.jeelinks.modules.center;

import com.wenpu.jeelinks.common.utils.StringUtils;

public class CenterUser {
	private String userId;
	private String userName;
	private String group;
	private String nickName;
	private int point;
	private String photo;
	
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getNickName() {
		if(StringUtils.isNotBlank(nickName)){
			return nickName;
		}
		return userName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	
	
}
